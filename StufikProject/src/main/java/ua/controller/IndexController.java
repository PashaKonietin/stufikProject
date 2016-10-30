package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.form.filter.CommodityFilterForm;
import ua.service.CategoryService;
import ua.service.CommodityService;
import ua.service.SubCategoryService;


@Controller
public class IndexController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CommodityService commodityService;
	
	@ModelAttribute("filter")
	public CommodityFilterForm getCommodityFilterForm(){
		return new CommodityFilterForm();
	}
	
	@RequestMapping("/")
	public String showindex(Model model,
							@PageableDefault(8) Pageable pageable,
							@ModelAttribute("filter") CommodityFilterForm filter){
		model.addAttribute("categoryes", categoryService.findAll(pageable));
		model.addAttribute("commodities", commodityService.findAll(pageable, filter));
		return "index";
	}
	
	@RequestMapping("/admin")
	public String adminPanel(){
		return "adminPanel";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
}
