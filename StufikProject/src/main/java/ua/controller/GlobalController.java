package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.entity.Client;
import ua.service.ClientService;

@ControllerAdvice 
public class GlobalController {
	
	@Autowired
	private ClientService clientService;;
	
	@ModelAttribute("authUser")
	public Client getClient(){
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!"anonymousUser".equals(id)){
			return clientService.findById(Integer.valueOf(id));
		}
		return null;
	}
}
