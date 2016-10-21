package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Manager;
import ua.service.ManagerService;

public class ManagerEditor extends PropertyEditorSupport{

	private final ManagerService managerService;

	public ManagerEditor(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Manager manager = managerService.findById(Integer.valueOf(text));
		setValue(manager);
	}
	
}
