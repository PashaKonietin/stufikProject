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

import ua.entity.Color;
import ua.form.filter.ColorFilterForm;
import ua.service.ColorService;
import ua.service.implementation.validator.ColorValidator;

@Controller
public class ColorController {

	@Autowired
	private ColorService colorService;
	
	@ModelAttribute("color")
	public Color getColor(){
		return new Color();
	}

	@ModelAttribute("filter")
	public ColorFilterForm getColorFilterForm(){
		return new ColorFilterForm();
	}
	
	@InitBinder("color")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ColorValidator(colorService));
	}
	
	@RequestMapping("/admin/color")
	public String showColor(Model model,
							@PageableDefault(5) Pageable pageable,
							@ModelAttribute("filter") ColorFilterForm filter){
		model.addAttribute("colors", colorService.findAll(pageable, filter));
		return "color";
	}
	
	@RequestMapping(value="/admin/color", method=RequestMethod.POST)
	public String save(@ModelAttribute("color") @Valid Color color,
					   BindingResult br,
					   Model model,
					   @PageableDefault(5) Pageable pageable,
					   @ModelAttribute("filter") ColorFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("colors", colorService.findAll(pageable, filter));
			return "color";
		}
		colorService.save(color);
		return "redirect:/admin/color" + getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/color/update/{id}")
	public String update(@PathVariable int id,
						 Model model,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") ColorFilterForm filter){
		model.addAttribute("color", colorService.findById(id));
		model.addAttribute("colors", colorService.findAll(pageable, filter));
		return "color";
	}
	
	@RequestMapping("/admin/color/delete/{id}")
	public String delete(@PathVariable int id,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") ColorFilterForm filter){
		colorService.delete(id);
		return "redirect:/admin/color" + getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, ColorFilterForm filter){
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
