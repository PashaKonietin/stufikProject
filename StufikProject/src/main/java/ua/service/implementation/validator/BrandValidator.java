package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Brand;
import ua.service.BrandService;

public class BrandValidator implements Validator {

	private final BrandService brandService;

	public BrandValidator(BrandService brandService) {
		this.brandService = brandService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Brand.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Brand brand = (Brand) target;
		if(brand.getId()==0)if(brandService.findByName(brand.getName())!=null){
			errors.rejectValue("name", "", "Brand with such name is aleady exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "this field can`t be empty");
	}

}
