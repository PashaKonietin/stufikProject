package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Description;
import ua.service.DescriptionService;

public class DescriptionEditor extends PropertyEditorSupport{

	private final DescriptionService descriptionService;

	public DescriptionEditor(DescriptionService descriptionService) {
		this.descriptionService = descriptionService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Description description = descriptionService.findById(Integer.valueOf(text));
		setValue(description);
	}

	
}
