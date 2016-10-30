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

@Controller
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@ModelAttribute("manager")
	public ManagerForm getManagerForm(){
		return new ManagerForm();
	}
	
	@ModelAttribute("filter")
	public ManagerFilterForm getManagerFilterForm(){
		return new ManagerFilterForm();
	}
	
	@InitBinder("manager")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Delivery.class, new DeliveryEditor(deliveryService));
	}
	
	@RequestMapping("/admin/manager")
	public String showManager(Model model,
							  @PageableDefault(5) Pageable pageable,
							  @ModelAttribute("filter") ManagerFilterForm filter){
		model.addAttribute("deliveryes", deliveryService.findAll());
		model.addAttribute("managers", managerService.findAll(pageable, filter));
		return "manager";
	}
	
	@RequestMapping(value="/admin/manager", method=RequestMethod.POST)
	public String save(@ModelAttribute("manager") ManagerForm manager,
						Model model,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") ManagerFilterForm filter){
		managerService.save(manager);
		return "redirect:/admin/manager" + getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/manager/update/{id}")
	public String update(@PathVariable int id,
						 Model model,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") ManagerFilterForm filter){
		model.addAttribute("manager", managerService.findForForm(id));
		model.addAttribute("deliveryes", deliveryService.findAll());
		model.addAttribute("managers", managerService.findAll(pageable, filter));
		return "manager";
	}
	
	@RequestMapping("/admin/manger/delete/{id}")
	public String delete(@PathVariable int id, 
						 @PageableDefault(5) Pageable pageable, 
						 @ModelAttribute("filter") ManagerFilterForm filter){
		managerService.delete(id);
		return "redirect:/admin/manager" + getParams(pageable, filter);
	}

	
	private String getParams(Pageable pageable, ManagerFilterForm filter) {
		StringBuilder buffer = new StringBuilder();// 
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		if(filter.getCompanySearch()!=null) 
		buffer.append(filter.getCompanySearch());
		for(Integer i : filter.getDeliveryIds()){
			buffer.append("&deliveryIds=");
			buffer.append(i.toString());
		}
		return buffer.toString();
	}
}
