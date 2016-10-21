package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// цей котроллер являється просто демо версією моїх спроб щось зробити
@Controller
public class DemoController {

	@RequestMapping("/demo")
	public String show(){
		return "demo";
	}
}
