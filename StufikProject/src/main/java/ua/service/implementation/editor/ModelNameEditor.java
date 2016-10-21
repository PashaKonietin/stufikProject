package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.ModelName;
import ua.service.ModelNameService;

public class ModelNameEditor extends PropertyEditorSupport{
	
	private final ModelNameService modelNameService;

	public ModelNameEditor(ModelNameService modelNameService) {
		this.modelNameService = modelNameService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ModelName modelName = modelNameService.findOne(Integer.valueOf(text));
		setValue(modelName);
	}

}
