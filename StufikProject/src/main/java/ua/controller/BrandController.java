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

@Controller
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@ModelAttribute("brand")
	public Brand getBrand(){
		return new Brand();
	}
	
	@ModelAttribute("filter")
	public BrandFilterForm getBrandFilterForm(){
		return new BrandFilterForm();
	}
	
	@InitBinder("brand")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new BrandValidator(brandService));
	}
	
	@RequestMapping("/admin/brand")
	public String showBrand(Model model,
							@PageableDefault(5) Pageable pageable,
							@ModelAttribute("filter") BrandFilterForm filter){
		model.addAttribute("brands", brandService.findAll(pageable, filter));
		return "brand";
	}
	
	@RequestMapping(value="/admin/brand", method=RequestMethod.POST)
	public String save(@ModelAttribute("brand") @Valid Brand brand,
						BindingResult br,
						Model model,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") BrandFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("brands", brandService.findAll(pageable, filter));
			return "brand";
		}
		brandService.save(brand);
		return "redirect:/admin/brand" + getParams(pageable, filter);
	}
	
	@RequestMapping(value="/admin/brand/update/{id}")
	public String update(Model model,
						@PathVariable int id,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") BrandFilterForm filter){
		model.addAttribute("brand", brandService.findOne(id));
		model.addAttribute("brands", brandService.findAll(pageable, filter));
		return "brand";
	}
	
	@RequestMapping(value="/admin/brand/delete/{id}")
	public String delete(@PathVariable int id,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") BrandFilterForm filter){
		brandService.delete(id);
		return "redirect:/admin/brand" + getParams(pageable, filter);
	}
	

	private String getParams(Pageable pageable, BrandFilterForm filter) {
		StringBuilder bilder = new StringBuilder();
		bilder.append("?page=");
		bilder.append(String.valueOf(pageable.getPageNumber()+1));
		bilder.append("&size=");
		bilder.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			bilder.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				bilder.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC){
					bilder.append(",desc");
				}
			});
		}
		bilder.append("&search=");
		if(filter.getSearch()!=null)
		bilder.append(filter.getSearch());
		return bilder.toString();
	}
}
