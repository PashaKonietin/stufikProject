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

import ua.form.CategoryForm;
import ua.form.filter.CategoryFilterForm;
import ua.service.CategoryService;
import ua.service.implementation.validator.CategoryValidator;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@InitBinder("category")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new CategoryValidator(categoryService));
	}
	
	@ModelAttribute("category")
	public CategoryForm getCategoryForm(){
		return new CategoryForm();
	}
	
	@ModelAttribute("filter")
	public CategoryFilterForm getCategoryFilterForm(){
		return new CategoryFilterForm();
	}
	
	@RequestMapping("/category")
	public String showCategory(Model model,
							   @PageableDefault(5) Pageable pageable){
		model.addAttribute("categoryes", categoryService.findAll(pageable));
		return "categoryPage";
	}
	
	@RequestMapping("/admin/category")
	public String show(Model model,
					   @PageableDefault(5) Pageable pageable,
					   @ModelAttribute("filter") CategoryFilterForm filter){
		model.addAttribute("categoryes", categoryService.findAll(pageable, filter));
		return "category";
	}
	
	@RequestMapping(value = "/admin/category", method=RequestMethod.POST)
	public String save(@ModelAttribute("category") @Valid CategoryForm category,
					   BindingResult br,
					   Model model,
					   @PageableDefault(5) Pageable pageable,
					   @ModelAttribute("filter") CategoryFilterForm filter){
		
		if(br.hasErrors()){
			model.addAttribute("categoryes", categoryService.findAll(pageable, filter));
			return "category";
		}
		categoryService.save(category);
		return "redirect:/admin/category" + getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/category/update/{id}")
	public String update(Model model,
						@PathVariable int id,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") CategoryFilterForm filter){
		model.addAttribute("category", categoryService.findOne(id));
		model.addAttribute("categoryes", categoryService.findAll(pageable, filter));
		return "category";
	}
	
	@RequestMapping("/admin/category/delete/{id}")
	public String delete(@PathVariable int id,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") CategoryFilterForm filter){
		categoryService.delete(id);
		return "redirect:/admin/category" + getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, CategoryFilterForm filter) {
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
