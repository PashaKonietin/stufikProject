package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Commodity;
import ua.service.CommodityService;

@Controller// вказуЇмо спр≥нгу що даний клас Ї б≥ном ≥ його необх≥дно п≥дгрузити при старт≥ програми
public class ItemController {

	@Autowired//вказуЇмо спр≥нгу що екземпл€р цього б≥на ми будемо використовувати в даному клас≥
	// ≥ дане поле класу потребуЇ заповненн€ ≥нЇкц≥Їю залежност≥ спр≥нга
	private CommodityService commodityService;
	
	@RequestMapping("/item/{id}")//задаэмо методу контролера адресу по €к≥й цей метод буде доступний на кл≥Їнт≥
	public String showItem(@PathVariable int id, Model model){
		Commodity commodity = commodityService.findCommodityInited(id); // знаходимо товар по id €ке ми отримали в≥д кл≥Їнта
		model.addAttribute("commodity", commodity);//передаЇмо в модель атрибут типу "ключ-значенн€" €кий буде доступний на кл≥Їнт≥ по ключу
		return "item";//повертаЇмо представленн€(JSP)
	}
}
