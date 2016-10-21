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

import ua.entity.ModelName;
import ua.form.filter.ModelNameFilterForm;
import ua.service.ModelNameService;
import ua.service.implementation.validator.ModelNameValidator;

@Controller
public class ModelNameController {

	@Autowired
	private ModelNameService modelNameService;
	
	@ModelAttribute("modelName")
	public ModelName getModelName(){
		return new ModelName(); 	
	}
	
	@ModelAttribute("filter")
	public ModelNameFilterForm getModelNameFilterForm(){
		return new ModelNameFilterForm();
	}
	
	@InitBinder("modelName")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ModelNameValidator(modelNameService));
	}
	
	@RequestMapping("/admin/modelName")
	public String showModelName(Model model,
								@PageableDefault(5) Pageable pageable,
								@ModelAttribute("filter") ModelNameFilterForm filter){
		model.addAttribute("modelNames", modelNameService.findAll(pageable, filter));
		return "modelName";
	}
	
	@RequestMapping(value="/admin/modelName", method=RequestMethod.POST)
	public String save(@ModelAttribute("modelName") @Valid ModelName modelName,
					   BindingResult br,
					   Model model,
					   @PageableDefault(5) Pageable pageable,
					   @ModelAttribute("filter") ModelNameFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("modelNames", modelNameService.findAll(pageable, filter));
			return "modelName";
		}
		modelNameService.save(modelName);
		return "redirect:/admin/modelName" + getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/modelName/update/{id}")
	public String update(@PathVariable int id,
						Model model,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") ModelNameFilterForm filter){
		model.addAttribute("modelName", modelNameService.findOne(id));
		model.addAttribute("modelNames", modelNameService.findAll(pageable, filter));
		return "modelName";
	}
					   
	@RequestMapping("/admin/modelName/delete/{id}")
	public String delete(@PathVariable int id,
						 @PageableDefault(5) Pageable pageable,
						 @ModelAttribute("filter") ModelNameFilterForm filter){
		modelNameService.delete(id);
		return "redirect:/admin/modelName" + getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, ModelNameFilterForm filter) {
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
