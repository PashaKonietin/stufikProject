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

import ua.entity.Category;
import ua.form.SubCategoryForm;
import ua.form.filter.SubCategoryFilterForm;
import ua.service.CategoryService;
import ua.service.SubCategoryService;
import ua.service.implementation.editor.CategoryEditor;


@Controller
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("subCategory")
	public SubCategoryForm getSubCategory(){
		return new SubCategoryForm(); 	
	}
	
	@ModelAttribute("filter")
	public SubCategoryFilterForm getSubCategoryFilterForm(){
		return new SubCategoryFilterForm();
	}
	
	@InitBinder("subCategory")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	}
	
	@RequestMapping("/admin/subCategory")
	public String showSubCategory(Model model,
								@PageableDefault(5) Pageable pageable,
								@ModelAttribute("filter") SubCategoryFilterForm filter){
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("subCategoryes", subCategoryService.findAll(pageable, filter));
		return "subCategory";
	}
	
	@RequestMapping(value="/admin/subCategory", method=RequestMethod.POST)
	public String save(@ModelAttribute("subCategory") SubCategoryForm subCategory,
//					   BindingResult br,
					   Model model,
					   @PageableDefault(5) Pageable pageable,
					   @ModelAttribute("filter") SubCategoryFilterForm filter){
//		if(br.hasErrors()){
//			model.addAttribute("subCategoryes", subCategoryService.findAll(pageable, filter));
//			return "subCategory";
//		}
		subCategoryService.save(subCategory);
		return "redirect:/admin/subCategory" + getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/subCategory/update/{id}")
	public String update(@PathVariable int id,
						Model model,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") SubCategoryFilterForm filter){
		model.addAttribute("subCategory", subCategoryService.findFormForm(id));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("subCategoryes", subCategoryService.findAll(pageable, filter));
		return "subCategory";
	}
					   
	@RequestMapping("/admin/subCategory/delete/{id}")
	public String delete(@PathVariable int id,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") SubCategoryFilterForm filter){
		subCategoryService.deleteById(id);
		return "redirect:/admin/subCategory" + getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, SubCategoryFilterForm filter) {
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
		for(Integer i : filter.getCategoryIds()){
			bilder.append("&categoryIds=");
			bilder.append(i.toString());
		}
		return bilder.toString();
	}
	
}
