package ua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Brand;
import ua.entity.Color;
import ua.entity.Commodity;
import ua.entity.Manager;
import ua.entity.Material;
import ua.entity.ModelName;
import ua.entity.SubCategory;
import ua.form.CommodityForm;
import ua.form.filter.CommodityFilterForm;
import ua.service.BrandService;
import ua.service.ColorService;
import ua.service.CommodityService;
import ua.service.ManagerService;
import ua.service.MaterialService;
import ua.service.ModelNameService;
import ua.service.SubCategoryService;
import ua.service.implementation.editor.BrandEditor;
import ua.service.implementation.editor.ColorEditor;
import ua.service.implementation.editor.ManagerEditor;
import ua.service.implementation.editor.MaterialEditor;
import ua.service.implementation.editor.ModelNameEditor;
import ua.service.implementation.editor.SubCategoryEditor;

@Controller
public class SubCategoryProductsController {
	

	@Autowired
	private CommodityService commodityService;
	
	@ModelAttribute("commodity")
	public CommodityForm getDescriptionForm(){
		return new CommodityForm(); 
	}
	
	@ModelAttribute("filter")
	public CommodityFilterForm getCommodityFilterForm(){
		return new CommodityFilterForm();
	}
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private ModelNameService modelNameService;
	
	@InitBinder("commodity")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(Color.class, new ColorEditor(colorService));
		binder.registerCustomEditor(Material.class, new MaterialEditor(materialService));
		binder.registerCustomEditor(SubCategory.class, new SubCategoryEditor(subCategoryService));
		binder.registerCustomEditor(Manager.class, new ManagerEditor(managerService));
		binder.registerCustomEditor(ModelName.class, new ModelNameEditor(modelNameService));
	}
	
	@RequestMapping("/commodity")
	public String showItemBySub(Model model,
								@PageableDefault(6) Pageable pageable,
								@ModelAttribute("filter") CommodityFilterForm filter){
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("subCategoryes", subCategoryService.findAll());
		model.addAttribute("colors", colorService.findAll());
		model.addAttribute("materials", materialService.findAll());
		model.addAttribute("managers", managerService.findAll());
		model.addAttribute("modelNames", modelNameService.findAll());
		model.addAttribute("commodities", commodityService.findAll(pageable, filter));
		return "subCategoryProducts";
	}
	
	@RequestMapping("/commodity/{id}") 
	public String showProdSubcategory(@PathVariable int id){ 
		return "redirect:/commodity?_brandIds=on&_brandIds=on&search=&subcategoryIds=1&_materialIds=on&_subCategoryIds=on&_colorIds=on&min=&max=&_brandIds=on&_colorIds=on&_materialIds=on&subCategoryIds="+id+"&_subCategoryIds=on"; 
	}
	
//	@RequestMapping("/commodity/{id}")
//	public String showSubCategoryItems(@PathVariable int id,  Model model,
//										@PageableDefault(5) Pageable pageable){
//		model.addAttribute("page", commodityService.findSub(id, pageable));
//		return "subCategoryProducts";
//	}

}
