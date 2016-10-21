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

import ua.entity.City;
import ua.form.filter.CityFilterForm;
import ua.service.CityService;
import ua.service.implementation.validator.CityValidator;

@Controller// вказуємо спрінгу, що даний клас є біном і його необхідно підгрузити при старті програми
public class CityController {

	@Autowired
	private CityService cityService;
	
	@ModelAttribute("city")
	public City getCity(){
		return new City();
	}
	
	@ModelAttribute("filter")
	public CityFilterForm getCityFilterForm(){
		return new CityFilterForm();
	}
	
	@InitBinder("city")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new CityValidator(cityService));
	}
	
	@RequestMapping("/admin/city")
	public String showCity(Model model,
							@PageableDefault(5) Pageable pageable,
							@ModelAttribute("filter") CityFilterForm filter){
		model.addAttribute("cityes", cityService.findAll(pageable, filter));
		return "city";
	}
	
	@RequestMapping(value="/admin/city", method=RequestMethod.POST)
	public String save(@ModelAttribute("city") @Valid City city,
						BindingResult br,
						Model model,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") CityFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("cityes", cityService.findAll(pageable, filter));
			return "city";
		}
		cityService.save(city);
		return "redirect:/admin/city" + getParams(pageable, filter);
	}
	
	@RequestMapping(value="/admin/city/update/{id}")
	public String update(@PathVariable int id,
						 Model model,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") CityFilterForm filter){
		model.addAttribute("city", cityService.findOne(id));
		model.addAttribute("cityes", cityService.findAll(pageable, filter));
		return "city";
	}
	
	@RequestMapping(value="/admin/city/delete/{id}")
	public String delete(@PathVariable int id,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") CityFilterForm filter){
		cityService.delete(id);
		return "redirect:/admin/city" + getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, CityFilterForm filter){
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
		if(filter.getSearch()!=null)
		builder.append(filter.getSearch());
		return builder.toString();
	}
}
