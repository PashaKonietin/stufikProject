package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.City;
import ua.service.CityService;

public class CityValidator implements Validator{

	private final CityService cityService;
	
	public CityValidator(CityService cityService) {
		this.cityService = cityService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return City.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		City city = (City) target;
		if(city.getId()==0)if(cityService.findByName(city.getName())!=null){
			errors.rejectValue("name", "", "City with such name is already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "This field can`t be empty");
	}

}
