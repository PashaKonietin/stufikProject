package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.DescriptionForm;
import ua.service.DescriptionService;

public class DescriptionValidator implements Validator{

	@Autowired
	private DescriptionService descriptionService;
	
	private static final Pattern p = Pattern.compile("^[0-9]{1,1}$");
	
	public DescriptionValidator(DescriptionService descriptionService) {
		this.descriptionService = descriptionService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return DescriptionForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DescriptionForm form = (DescriptionForm) target;
		Matcher m = p.matcher(form.getWeight());
		if(!m.matches()){
			errors.rejectValue("weight", "", "Weight format is from 1 - 9");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "", "Can`t be empty");
	}

}
