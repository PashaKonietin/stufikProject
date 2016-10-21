package ua.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.City;
import ua.entity.Client;
import ua.entity.Commodity;
import ua.entity.Delivery;
import ua.form.MyOrderForm;
import ua.service.CityService;
import ua.service.ClientService;
import ua.service.CommodityService;
import ua.service.DeliveryService;
import ua.service.OrderService;
import ua.service.implementation.editor.CityEditor;
import ua.service.implementation.editor.ClientEditor;
import ua.service.implementation.editor.CommodityEditor;
import ua.service.implementation.editor.DeliveryEditor;

@Controller
public class OrderPageController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CommodityService commodityService;
	
	@ModelAttribute("order")
	public MyOrderForm getMyOrderForm(){
		return new MyOrderForm();
	}
	
	@InitBinder("order")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Delivery.class, new DeliveryEditor(deliveryService));
		binder.registerCustomEditor(City.class, new CityEditor(cityService));
		binder.registerCustomEditor(Client.class, new ClientEditor(clientService));
		binder.registerCustomEditor(Commodity.class, new CommodityEditor(commodityService));
	}
	
	@RequestMapping("/buy/{id}")
	public String show(Model model, @PathVariable int id){
		model.addAttribute("deliveries", deliveryService.findAll());
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("commodity", commodityService.findOne(id));
		return "order";
	}
	
	@RequestMapping(value="/buy/{id}", method=RequestMethod.POST) 
	public String save(Model model, @ModelAttribute("order") MyOrderForm order, @PathVariable int id){ 
		Commodity commodity = commodityService.findOne(id);
		List<Commodity> commodities = new ArrayList<Commodity>();
		commodities.add(commodity); 
		String userId = SecurityContextHolder.getContext().getAuthentication().getName(); 
		Client client = clientService.findById(Integer.valueOf(userId)); 
		orderService.save(order, commodities, client); 
	return "redirect:/commodity"; 
	}



}
