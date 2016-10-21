package ua.service.implementation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Material;
import ua.service.MaterialService;

public class MaterialValidator implements Validator{

	@Autowired
	private MaterialService materialService;
	
	
	public MaterialValidator(MaterialService materialService) {
		this.materialService = materialService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Material.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Material material = (Material) target;
		if(material.getId()==0)if(materialService.findByName(material.getName())!=null){
			errors.rejectValue("name", "", "Material with such name is already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "This field can`t be empty");
	}

}
