package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport{

	private final CategoryService service;

	public CategoryEditor(CategoryService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(service.findOne(Integer.valueOf(text)));
	}
	
}
