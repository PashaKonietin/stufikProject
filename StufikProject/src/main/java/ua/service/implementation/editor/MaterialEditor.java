package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Material;
import ua.service.MaterialService;

public class MaterialEditor extends PropertyEditorSupport{
	
	private final MaterialService materialService;

	public MaterialEditor(MaterialService materialService) {
		this.materialService = materialService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Material material = materialService.findOne(Integer.valueOf(text));
		setValue(material);
	}

}
