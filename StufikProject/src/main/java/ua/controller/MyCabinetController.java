package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyCabinetController {

	@RequestMapping("/myCabinet")
	public String showMyCabinet(){
		return "myCabinet";
	}
}
