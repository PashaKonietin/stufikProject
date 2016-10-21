package ua.service.implementation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.ModelName;
import ua.service.ModelNameService;

public class ModelNameValidator implements Validator{

	@Autowired 
	private ModelNameService modelNameService;
	
	public ModelNameValidator(ModelNameService modelNameService) {
		this.modelNameService = modelNameService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ModelName.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ModelName modelName = (ModelName) target;
		if(modelName.getId()==0)if(modelNameService.findByName(modelName.getName())!=null){
			errors.rejectValue("name", "", "Model with such name is already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "This field can`t be empty");
	}

}
