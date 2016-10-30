package ua.controller;

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

import ua.entity.Cart;
import ua.entity.Commodity;
import ua.form.CommodityForm;
import ua.service.CartService;
import ua.service.ClientService;
import ua.service.CommodityService;
import ua.service.implementation.editor.CommodityEditor;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CommodityService commodityService;
	
	@ModelAttribute("cart")
	public Cart getCart(){
		return new Cart();
	}
	
	@InitBinder("cart")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(CommodityForm.class, new CommodityEditor(commodityService));
	}
	
	@RequestMapping("/cart")
	public String showCart(Model model){
		String clientId = SecurityContextHolder.getContext().getAuthentication().getName(); 
//		Client client = clientService.findById(Integer.valueOf(clientId));
		List<Commodity> commodities = commodityService.findAllCommoditiesByClient(Integer.valueOf(clientId));
		model.addAttribute("totalPrice", cartService.totalPrice(Integer.valueOf(clientId)));
		model.addAttribute("commodities", commodities);
		return "cart"; 
	}
	
	@RequestMapping("/cart/delete/{id}")
	public String deleteCommodity(@PathVariable int id){
		String clientId = SecurityContextHolder.getContext().getAuthentication().getName();
		cartService.deleteCommodity(id, Integer.valueOf(clientId));
		return "redirect:/cart";
	}
	
	@RequestMapping("/cart/addCommodity/{id}")
	public String addCommodity(@PathVariable int id, Model model){
		String clientId = SecurityContextHolder.getContext().getAuthentication().getName(); 
		cartService.addCommodity(id, Integer.valueOf(clientId));
		return "cart";
	}
	
}
