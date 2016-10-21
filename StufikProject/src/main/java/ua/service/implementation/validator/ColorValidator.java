package ua.service.implementation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Color;
import ua.service.ColorService;

public class ColorValidator implements Validator{

	@Autowired
	private ColorService colorService;
	
	public ColorValidator(ColorService colorService) {
		this.colorService = colorService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Color.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Color color = (Color) target;
		if(color.getId()==0)if(colorService.findByName(color.getName())!=null){
			errors.rejectValue("name", "", "Color with such name is already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "This field can`t be empty");
	}

}
