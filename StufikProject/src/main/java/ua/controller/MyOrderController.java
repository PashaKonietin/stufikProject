package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import ua.entity.Delivery;
import ua.form.MyOrderForm;
import ua.service.CityService;
import ua.service.ClientService;
import ua.service.DeliveryService;
import ua.service.OrderService;
import ua.service.implementation.editor.CityEditor;
import ua.service.implementation.editor.ClientEditor;
import ua.service.implementation.editor.DeliveryEditor;

@Controller
public class MyOrderController {

	@Autowired
	private OrderService myOrderService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private ClientService clientService;
	
	@ModelAttribute("order")
	public MyOrderForm getMyOrderForm(){
		return new MyOrderForm();
	}
	
	@InitBinder("order")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Delivery.class, new DeliveryEditor(deliveryService));
		binder.registerCustomEditor(City.class, new CityEditor(cityService));
		binder.registerCustomEditor(Client.class, new ClientEditor(clientService));
	}
	
	@RequestMapping("/admin/order")
	public String showOrders(Model model, 
							 @PageableDefault(5) Pageable pageable){
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("deliveries", deliveryService.findAll());
		model.addAttribute("clients", clientService.findAll());
		model.addAttribute("myOrders", myOrderService.findAll(pageable));
		return "myOrder";
	}
	
	@RequestMapping(value="/admin/order", method=RequestMethod.POST)
	public String save(@ModelAttribute("order") MyOrderForm order, Model model){
		myOrderService.save(order);
		return "redirect:/admin/order";
	}
	
//	@RequestMapping("/admin/order/delete/{id}")
//	public String delete(@PathVariable int id){
//		myOrderService.delete(id);
//		return "redirect/admin/order";
//	}
}
