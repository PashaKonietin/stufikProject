package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Client;
import ua.service.ClientService;

public class ClientEditor extends PropertyEditorSupport{
	
	private final ClientService clientService;

	public ClientEditor(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Client client = clientService.findById(Integer.valueOf(text));
		setValue(client);
	}
	
	

}
