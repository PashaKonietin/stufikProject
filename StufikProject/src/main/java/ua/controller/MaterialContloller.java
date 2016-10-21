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

import ua.entity.Material;
import ua.form.filter.MaterialFilterForm;
import ua.service.MaterialService;
import ua.service.implementation.validator.MaterialValidator;

@Controller
public class MaterialContloller {

	@Autowired
	private MaterialService materialService;
	
	@ModelAttribute("material")
	public Material getMAterial(){
		return new Material();
	}
	
	@ModelAttribute("filter")
	public MaterialFilterForm getMaterialFilterForm(){
		return new MaterialFilterForm();
	}
	
	@InitBinder("material")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new MaterialValidator(materialService));
	}
	
	@RequestMapping("/admin/material")
	public String showMaterial(Model model,
							   @PageableDefault(5) Pageable pageable,
							   @ModelAttribute("filter") MaterialFilterForm filter){
		model.addAttribute("materials", materialService.findAll(pageable, filter));
		return "material";
	}
	
	@RequestMapping(value="/admin/material", method=RequestMethod.POST)
	public String save(@ModelAttribute("material") @Valid Material material,
					   BindingResult br,
					   Model model,
					   @PageableDefault(5) Pageable pageable,
					   @ModelAttribute("filter") MaterialFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("materials", materialService.findAll(pageable, filter));
			return "material";
		}
		materialService.save(material);
		return "redirect:/admin/material" + getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/material/update/{id}")
	public String update(@PathVariable int id,
						 Model model,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") MaterialFilterForm filter){
		model.addAttribute("material", materialService.findOne(id));
		model.addAttribute("materials", materialService.findAll(pageable, filter));
		return "material";
	}
	
	@RequestMapping("/admin/material/delete/{id}")
	public String delete(@PathVariable int id,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") MaterialFilterForm filter){
		materialService.delete(id);
		return "redirect:/admin/material" + getParams(pageable, filter);
	}
			
	
	private String getParams(Pageable pageable, MaterialFilterForm filter){
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
