package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.CategoryForm;
import ua.service.CategoryService;

public class CategoryValidator implements Validator{
	
	private final CategoryService categoryService;

	public CategoryValidator(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoryForm category = (CategoryForm) target;
		if(category.getId()==0)if(categoryService.findByName(category.getName())!=null){
			errors.rejectValue("name", "", "Category with such name is already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "This field can`t be empty");
	}
	
	

}
