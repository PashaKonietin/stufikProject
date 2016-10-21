package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.entity.Client;
import ua.service.ClientService;

@ControllerAdvice // вказуємо спрінгу що даний клас є глобальним контролером і його методи повертатимуть значення в будь яке представлення
public class GlobalController {
	
	@Autowired// вказуємо спрінгу що даний клас є біном і його необхідно підгрузити при старті програми
	private ClientService clientService;;
	
	@ModelAttribute("authUser")// вказуємо спрінгу що значення яке повертається методом повинно автоматично поміщатися в модель
	public Client getClient(){
		String id = SecurityContextHolder.getContext().getAuthentication().getName();//витягуємо айді юзера
		if(!"anonymousUser".equals(id)){// якщо айді юзера співпадає з тим який містить спрінг секюріті,
			return clientService.findById(Integer.valueOf(id));// то шукаємо юзера і повертаємо його на представлення
		}
		return null;// якщо ні то повертаємо null
	}
}
