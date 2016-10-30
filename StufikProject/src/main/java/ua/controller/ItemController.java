package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Commodity;
import ua.service.CommodityService;

@Controller
public class ItemController {

	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping("/item/{id}")
	public String showItem(@PathVariable int id, Model model){
		Commodity commodity = commodityService.findCommodityInited(id);
		model.addAttribute("commodity", commodity);
		return "item";
	}
}
