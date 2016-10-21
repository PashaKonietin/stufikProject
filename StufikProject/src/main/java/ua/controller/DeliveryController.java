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

import ua.entity.Delivery;
import ua.form.filter.DeliveryFilterForm;
import ua.service.DeliveryService;
import ua.service.implementation.validator.DeliveryValidator;

@Controller// вказуємо спрінгу що даний клас є біном і його необхідно підгрузити при старті програми
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	@ModelAttribute("delivery")
	public Delivery getDelivery(){
		return new Delivery();
	}
	
	@ModelAttribute("filter")
	public DeliveryFilterForm getDeliveryForm(){
		return new DeliveryFilterForm();
	}
	
	@InitBinder("delivery")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new DeliveryValidator(deliveryService));
	}
	
	@RequestMapping("/admin/delivery")
	public String showDelivery(Model model,
							   @PageableDefault(5) Pageable pageable,
							   @ModelAttribute("filter") DeliveryFilterForm filter){
		model.addAttribute("page", deliveryService.findAll(pageable, filter));
		return "delivery";
	}
	
	@RequestMapping(value="/admin/delivery", method=RequestMethod.POST)
	public String save(@ModelAttribute("delivery") @Valid Delivery delivery,
					   BindingResult br,
					   Model model,
					   @PageableDefault(5) Pageable pageable,
					   @ModelAttribute("filter") DeliveryFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("page", deliveryService.findAll(pageable, filter));
			return "delivery";
		}
		deliveryService.save(delivery);
		return "redirect:/admin/delivery" + getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/delivery/update/{id}")
	public String update(@PathVariable int id,
						 Model model,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") DeliveryFilterForm filter){
		model.addAttribute("delivery", deliveryService.findById(id));
		model.addAttribute("page", deliveryService.findAll(pageable, filter));
		return "delivery";
	}
	
	@RequestMapping("/admin/delivery/delete/{id}")
	public String delete(@PathVariable int id,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") DeliveryFilterForm filter){
		deliveryService.delete(id);
		return "redirect:/admin/delivery" + getParams(pageable, filter);
	}

	private String getParams(Pageable pageable, DeliveryFilterForm filter){
		StringBuilder builder = new StringBuilder();
		builder.append("?page=");
		builder.append(String.valueOf(pageable.getPageNumber()+1));
		builder.append("&size=");
		builder.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			builder.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				builder.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC){
					builder.append(",desc");
				}
			});
		}
		builder.append("&search=");
		if(filter.getDeliveryCompanySearch()!=null && filter.getDeliveryMethodSearch()!=null){
			builder.append(filter.getDeliveryCompanySearch());
			builder.append(filter.getDeliveryMethodSearch());
		}
		return builder.toString();
	}
}
