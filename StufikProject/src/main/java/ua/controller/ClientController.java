package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import ua.form.ClientForm;
import ua.form.filter.ClientFilterForm;
import ua.service.CityService;
import ua.service.ClientService;
import ua.service.implementation.editor.CityEditor;

@Controller// вказуємо спрінгу що даний клас є біном і його необхідно підгрузити при старті програми
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CityService cityService;
	
	@ModelAttribute("client")
	public ClientForm getClientForm(){
		return new ClientForm();
	}
	
	@ModelAttribute("filter")
	public ClientFilterForm getClientFilterForm(){
		return new ClientFilterForm();
	}
	
	@InitBinder("client")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(City.class, new CityEditor(cityService));
		
	}
	
	@RequestMapping("/registration")
	public String register(){
		return "registration";
    }
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String save(@ModelAttribute("client") ClientForm client){
		clientService.save(client);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/verification/{pathVerification}", method=RequestMethod.GET)
    public String verificationUser(@PathVariable String pathVerification){
    	Client client = clientService.findByVerification(pathVerification);
    	client.setConfirmed(true);
    	clientService.saveAuthenticationUser(client);
    	return "redirect:/login";
    }
	
	@RequestMapping("/admin/client")
	public String showClients(Model model,
							  @PageableDefault(5) Pageable pageable,
							  @ModelAttribute("filter") ClientFilterForm filter){
		model.addAttribute("cityes", cityService.findAll());
		model.addAttribute("clients", clientService.findAll(filter, pageable));
		return "client";
	}
	
	 @RequestMapping(value="/admin/client", method=RequestMethod.POST)
	 public String save(@ModelAttribute("client") Client client, 
			 			Model model, 
			 			@PageableDefault(3) Pageable pageable, 
			 			@ModelAttribute(value="filter") ClientFilterForm filter){
	    clientService.saveUser(client);
	    return "redirect:/admin/client" +getParams(pageable, filter);
	}
	 
	 @RequestMapping("/admin/client/delete/{id}")
	 public String delete(@PathVariable int id,
			 			  @PageableDefault(5) Pageable pageable,
			 			  @ModelAttribute("filter") ClientFilterForm filter){
		 clientService.delete(id);
		 return "redirect:/admin/client" + getParams(pageable, filter);
	 }
	 
	 @RequestMapping("/admin/client/update/{id}")
	 public String update(@PathVariable int id,
			 			  Model model,
			 			  @PageableDefault(5) Pageable pageable,
			 			  @ModelAttribute("filter") ClientFilterForm filter){
		 model.addAttribute("client", clientService.findForForm(id));
		 model.addAttribute("cityes", cityService.findAll());
		 model.addAttribute("clients", clientService.findAll(filter, pageable));
		 return "client";
	 }
	 
	 private String getParams(Pageable pageable, ClientFilterForm filter){
			StringBuilder buffer = new StringBuilder();
			buffer.append("?page=");
			buffer.append(String.valueOf(pageable.getPageNumber()+1));
			buffer.append("&size=");
			buffer.append(String.valueOf(pageable.getPageSize()));
			if(pageable.getSort()!=null){
				buffer.append("&sort=");
				Sort sort = pageable.getSort();
				sort.forEach((order)->{
					buffer.append(order.getProperty());
					if(order.getDirection()!=Direction.ASC)
					buffer.append(",desc");
				});
			}
			buffer.append("&emailSearch=");
			if(filter.getEmailSearch()!=null)
			buffer.append(filter.getEmailSearch());
			buffer.append("&loginSearch=");
			if(filter.getLoginSearch()!=null)
			buffer.append(filter.getLoginSearch());
			for(Integer i : filter.getCityIds()){
				buffer.append("&cityIds=");
				buffer.append(i.toString());
			}
			return buffer.toString();
	    }
}
