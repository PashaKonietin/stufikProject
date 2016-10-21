package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Delivery;
import ua.form.ManagerForm;
import ua.form.filter.ManagerFilterForm;
import ua.service.DeliveryService;
import ua.service.ManagerService;
import ua.service.implementation.editor.DeliveryEditor;

@Controller// ������� ������ �� ����� ���� � ���� � ���� ��������� ��������� ��� ����� ��������
public class ManagerController {
	
	@Autowired//������� ������ �� ��������� ����� ��� �� ������ ��������������� � ������ ����
	// � ���� ���� ����� ������� ���������� ������� ��������� ������
	private ManagerService managerService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@ModelAttribute("manager")// ������� ������ �� �������� ��� ����������� ������� ������� ����������� ��������� � ������
	public ManagerForm getManagerForm(){//��������� ����� ���� ������� �����
		return new ManagerForm();
	}
	
	@ModelAttribute("filter")// ������� ������ �� �������� ��� ����������� ������� ������� ����������� ��������� � ������
	public ManagerFilterForm getManagerFilterForm(){//��������� ����� ���� ������� ������
		return new ManagerFilterForm();
	}
	
	@InitBinder("manager")//�������� ����� ��������, ���� �� �������� �� ����������� �� integer a string
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Delivery.class, new DeliveryEditor(deliveryService));
	}
	
	@RequestMapping("/admin/manager")//������� ������ ���������� ������ �� ��� ��� ����� ���� ��������� �� �볺��
	public String showManager(Model model,//�������� ������
							  @PageableDefault(5) Pageable pageable,//�������� ��� ������������ �������� �� ������������� ��� ������� ����������� �������� � ����� ����������
							  @ModelAttribute("filter") ManagerFilterForm filter){
		model.addAttribute("deliveryes", deliveryService.findAll());//�������� � ������ ������� ���� "����-��������" ���� ���� ��������� �� �볺�� �� �����
		model.addAttribute("managers", managerService.findAll(pageable, filter));//�������� � ������ ������� ���� "����-��������" ���� ���� ��������� �� �볺�� �� �����
		return "manager";//��������� �������������(JSP)
	}
	
	@RequestMapping(value="/admin/manager", method=RequestMethod.POST)//������� ������ ���������� ������ �� ��� ��� ����� ���� ��������� �� �볺��
	public String save(@ModelAttribute("manager") ManagerForm manager,
						Model model,//�������� ������
						@PageableDefault(5) Pageable pageable,//�������� ��� ������������ �������� �� ������������� ��� ������� ����������� �������� � ����� ����������
						@ModelAttribute("filter") ManagerFilterForm filter){//�������� � ������ ������� ���� "����-��������" ���� ���� ��������� �� �볺�� �� �����
		managerService.save(manager);
		return "redirect:/admin/manager" + getParams(pageable, filter);// ��� ���� ��� �� �������� �� ��������� ��� ������� ������������� ����� getParams
	}
	
	@RequestMapping("/admin/manager/update/{id}")//������� ������ ���������� ������ �� ��� ��� ����� ���� ��������� �� �볺��
	public String update(@PathVariable int id,// �������������� ����� � ������
						 Model model,//�������� ������
						 @PageableDefault(5) Pageable pageable,//�������� ��� ������������ �������� �� ������������� ��� ������� ����������� �������� � ����� ����������
						 @ModelAttribute("filter") ManagerFilterForm filter){
		model.addAttribute("manager", managerService.findForForm(id));
		model.addAttribute("deliveryes", deliveryService.findAll());//�������� � ������ ������� ���� "����-��������" ���� ���� ��������� �� �볺�� �� �����
		model.addAttribute("managers", managerService.findAll(pageable, filter));//�������� � ������ ������� ���� "����-��������" ���� ���� ��������� �� �볺�� �� �����
		return "manager";//��������� �������������(JSP)
	}
	
	@RequestMapping("/admin/manger/delete/{id}")//������� ������ ���������� ������ �� ��� ��� ����� ���� ��������� �� �볺��
	public String delete(@PathVariable int id, // �������������� ����� � ������
						 @PageableDefault(5) Pageable pageable, //�������� ��� ������������ �������� �� ������������� ��� ������� ����������� �������� � ����� ����������
						 @ModelAttribute("filter") ManagerFilterForm filter){
		managerService.delete(id);
		return "redirect:/admin/manager" + getParams(pageable, filter);// ��� ���� ��� �� �������� �� ��������� ��� ������� ������������� ����� getParams
	}

	
	private String getParams(Pageable pageable, ManagerFilterForm filter) {
		StringBuilder buffer = new StringBuilder();// ��������� ����� �����
		buffer.append("?page=");//������ �������� page
		buffer.append(String.valueOf(pageable.getPageNumber()+1));//������ �������� ������ ������� �� pageable  � ������ 1 ���� ��������� ������� ���������� � 0
		buffer.append("&size=");//������ �������� size
		buffer.append(String.valueOf(pageable.getPageSize()));//������ �������� ������ ������� �� pageable
		if(pageable.getSort()!=null){//���������� �� ���������� �� �� ��������� �������� ���� ���� ���� �� ���� ����� �������� ��� ��������
			buffer.append("&sort=");//������ �������� sort
			Sort sort = pageable.getSort();//������ ��������� ���������� � �������� � Sort
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");// ������ �������� ��� ������
		if(filter.getCompanySearch()!=null)//���������� �� ��� �� �볺��� ������� ����� �� �����, 
			//��� ���� ������� ��� ������� ������� � ��� � �� �������� ���� ������������� null
		buffer.append(filter.getCompanySearch());
		for(Integer i : filter.getDeliveryIds()){//������� ��� id ������� Delivery
			buffer.append("&deliveryIds=");//������ ��������
			buffer.append(i.toString());
		}
		return buffer.toString();//��������� ������ � ���� ������ �����������
	}
}
