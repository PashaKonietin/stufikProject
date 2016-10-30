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

import ua.entity.Brand;
import ua.entity.Color;
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
public class CommodityController {

	
	@Autowired
	private CommodityService commodityService;
	
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
	
	@ModelAttribute("commodity")
	public CommodityForm getDescriptionForm(){
		return new CommodityForm(); 
	}
	
	@ModelAttribute("filter")
	public CommodityFilterForm getCommodityFilterForm(){
		return new CommodityFilterForm();
	}
	
	@InitBinder("commodity")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(Color.class, new ColorEditor(colorService));
		binder.registerCustomEditor(Material.class, new MaterialEditor(materialService));
		binder.registerCustomEditor(SubCategory.class, new SubCategoryEditor(subCategoryService));
		binder.registerCustomEditor(Manager.class, new ManagerEditor(managerService));
		binder.registerCustomEditor(ModelName.class, new ModelNameEditor(modelNameService));
	}
	
	@RequestMapping("/admin/commodity")
	public String showCommodity(Model model,
								@PageableDefault(8) Pageable pageable,
								@ModelAttribute("filter") CommodityFilterForm filter){
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("subCategoryes", subCategoryService.findAll());
		model.addAttribute("colors", colorService.findAll());
		model.addAttribute("materials", materialService.findAll());
		model.addAttribute("managers", managerService.findAll());
		model.addAttribute("modelNames", modelNameService.findAll());
		model.addAttribute("commodities", commodityService.findAll(pageable, filter));
		return "commodity";
	}
	
	@RequestMapping(value="/admin/commodity", method=RequestMethod.POST)
	public String save(@ModelAttribute("commodity") CommodityForm commodity,
					   Model model,
					   @PageableDefault(5) Pageable pageable,
					   @ModelAttribute("filter") CommodityFilterForm filter){
		commodityService.save(commodity);
		return "redirect:/admin/commodity" + getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/commodity/update/{id}")
	public String update(@PathVariable int id,
						 Model model,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") CommodityFilterForm filter){
		model.addAttribute("commodity", commodityService.findForForm(id));
		model.addAttribute("subCategoryes", subCategoryService.findAll());
		model.addAttribute("colors", colorService.findAll());
		model.addAttribute("materials", materialService.findAll());
		model.addAttribute("managers", managerService.findAll());
		model.addAttribute("modelNames", modelNameService.findAll());
		model.addAttribute("commodities", commodityService.findAll(pageable, filter));
		return "commodity";
	}
	
	@RequestMapping("/admin/commodity/delete/{id}")
	public String delete(@PathVariable int id,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") CommodityFilterForm filter){
		commodityService.delete(id);
		return "redirect:/admin/commodity" + getParams(pageable, filter);
	}
					   
					   
	private String getParams(Pageable pageable, CommodityFilterForm filter) {
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
				if(order.getDirection()!=Direction.ASC)
					builder.append(",desc");
			});
		}
		builder.append("&min=");
		builder.append(filter.getMin());
		builder.append("&max=");
		builder.append(filter.getMax());
		for(Integer i : filter.getBrandIds()){
			builder.append("&brandsIds=");
			builder.append(i.toString());
		}
		for(Integer i : filter.getSubCategoryIds()){
			builder.append("&subCategoryIds=");
			builder.append(i.toString());
		}
		for(Integer i : filter.getColorIds()){
			builder.append("&colorIds=");
			builder.append(i.toString());
		}
		for(Integer i : filter.getMaterialIds()){
			builder.append("&materialIds=");
			builder.append(i.toString());
		}
		return builder.toString();
	}
}
