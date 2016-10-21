package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Brand;
import ua.form.filter.BrandFilterForm;
import ua.service.BrandService;
import ua.service.implementation.validator.BrandValidator;

@Controller// ������� ������ �� ����� ���� � ���� � ���� ��������� ��������� ��� ����� ��������
public class BrandController {
	
	@Autowired//������� ������ �� ��������� ����� ��� �� ������ ��������������� � ������ ����
	// � ���� ���� ����� ������� ���������� ������� ��������� ������
	private BrandService brandService;
	
	@ModelAttribute("brand")// ������� ������ �� �������� ��� ����������� ������� ������� ����������� ��������� � ������
	public Brand getBrand(){//��������� ����� ���� ������� entity
		return new Brand();
	}
	
	@ModelAttribute("filter")// ������� ������ �� �������� ��� ����������� ������� ������� ����������� ��������� � ������
	public BrandFilterForm getBrandFilterForm(){//��������� ����� ���� ������� ������
		return new BrandFilterForm();
	}
	
	@InitBinder("brand")// //�������� �������� ��� entity Brand
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new BrandValidator(brandService));
	}
	
	@RequestMapping("/admin/brand")//������� ������ ���������� ������ �� ��� ��� ����� ���� ��������� �� �볺��
	public String showBrand(Model model,//�������� ������
							@PageableDefault(5) Pageable pageable,//�������� ��� ������������ �������� �� ������������� ��� ������� ����������� �������� � ����� ����������
							@ModelAttribute("filter") BrandFilterForm filter){
		model.addAttribute("brands", brandService.findAll(pageable, filter));//�������� � ������ ������� ���� "����-��������" ���� ���� ��������� �� �볺�� �� �����
		return "brand";//��������� �������������(JSP)
	}
	
	@RequestMapping(value="/admin/brand", method=RequestMethod.POST)//������� ������ ���������� ������ �� ��� ��� ����� ���� ��������� �� �볺��
	public String save(@ModelAttribute("brand") @Valid Brand brand,
						BindingResult br,
						Model model,//�������� ������
						@PageableDefault(5) Pageable pageable,//�������� ��� ������������ �������� �� ������������� ��� ������� ����������� �������� � ����� ����������
						@ModelAttribute("filter") BrandFilterForm filter){
		if(br.hasErrors()){//���� ��� �������� ��'���� ��������� ������� �� �� ����� ������ ������� �볺��� �� ��������
			model.addAttribute("brands", brandService.findAll(pageable, filter));//�������� � ������ ������� ���� "����-��������" ���� ���� ��������� �� �볺�� �� �����
			return "brand";
		}
		brandService.save(brand);
		return "redirect:/admin/brand" + getParams(pageable, filter);// ��� ���� ��� �� �������� �� ��������� ��� ������� ������������� ����� getParams
	}
	
	@RequestMapping(value="/admin/brand/update/{id}")//������� ������ ���������� ������ �� ��� ��� ����� ���� ��������� �� �볺��
	public String update(Model model,//�������� ������
						@PathVariable int id,// �������������� ����� � ������
						@PageableDefault(5) Pageable pageable,//�������� ��� ������������ �������� �� ������������� ��� ������� ����������� �������� � ����� ����������
						@ModelAttribute("filter") BrandFilterForm filter){
		model.addAttribute("brand", brandService.findOne(id));// ��������� ����� ���� �� ���������
		model.addAttribute("brands", brandService.findAll(pageable, filter));//�������� � ������ ������� ���� "����-��������" ���� ���� ��������� �� �볺�� �� �����
		return "brand";//��������� �������������(JSP)
	}
	
	@RequestMapping(value="/admin/brand/delete/{id}")//������� ������ ���������� ������ �� ��� ��� ����� ���� ��������� �� �볺��
	public String delete(@PathVariable int id,// �������������� ����� � ������
						 @PageableDefault(5) Pageable pageable,//�������� ��� ������������ �������� �� ������������� ��� ������� ����������� �������� � ����� ����������
						 @ModelAttribute("filter") BrandFilterForm filter){
		brandService.delete(id);
		return "redirect:/admin/brand" + getParams(pageable, filter);// ��� ���� ��� �� �������� �� ��������� ��� ������� ������������� ����� getParams
	}
	

	private String getParams(Pageable pageable, BrandFilterForm filter) {
		StringBuilder bilder = new StringBuilder();// ��������� ����� �����
		bilder.append("?page=");//������ �������� page
		bilder.append(String.valueOf(pageable.getPageNumber()+1));//������ �������� ������ ������� �� pageable  � ������ 1 ���� ��������� ������� ���������� � 0
		bilder.append("&size=");//������ �������� size
		bilder.append(String.valueOf(pageable.getPageSize()));//������ �������� ������ ������� �� pageable
		if(pageable.getSort()!=null){//���������� �� ���������� �� �� ��������� �������� ���� ���� ���� �� ���� ����� �������� ��� ��������
			bilder.append("&sort=");//������ �������� sort
			Sort sort = pageable.getSort();//������ ��������� ���������� � �������� � Sort
			sort.forEach((order)->{
				bilder.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC){
					bilder.append(",desc");
				}
			});
		}
		bilder.append("&search=");// ������ �������� ��� ������
		if(filter.getSearch()!=null)//���������� �� ��� �� �볺��� ������� ����� �� �����,
			//��� ���� ������� ��� ������� ������� � ��� � �� �������� ���� ������������� null
		bilder.append(filter.getSearch());
		return bilder.toString();//��������� ������ � ���� ������ �����������
	}
}
