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

import ua.entity.Delivery;
import ua.form.ManagerForm;
import ua.form.filter.ManagerFilterForm;
import ua.service.DeliveryService;
import ua.service.ManagerService;
import ua.service.implementation.editor.DeliveryEditor;

@Controller// вказуємо спрінгу що даний клас є біном і його необхідно підгрузити при старті програми
public class ManagerController {
	
	@Autowired//вказуємо спрінгу що екземпляр цього біна ми будемо використовувати в даному класі
	// і дане поле класу потребує заповнення інєкцією залежності спрінга
	private ManagerService managerService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@ModelAttribute("manager")// вказуємо спрінгу що значення яке повертається методом повинно автоматично поміщатися в модель
	public ManagerForm getManagerForm(){//створюємо метод який повертає форму
		return new ManagerForm();
	}
	
	@ModelAttribute("filter")// вказуємо спрінгу що значення яке повертається методом повинно автоматично поміщатися в модель
	public ManagerFilterForm getManagerFilterForm(){//створюємо метод який повертає фільтр
		return new ManagerFilterForm();
	}
	
	@InitBinder("manager")//реєструємо даний редактор, адже ми отримуємо від користувача не integer a string
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Delivery.class, new DeliveryEditor(deliveryService));
	}
	
	@RequestMapping("/admin/manager")//задаэмо методу контролера ссилку по якій цей метод буде доступний на клієнті
	public String showManager(Model model,//передаємо модель
							  @PageableDefault(5) Pageable pageable,//анотація для установлення значення за замовчуванням при введенні посторіночної розбивки в метод контролера
							  @ModelAttribute("filter") ManagerFilterForm filter){
		model.addAttribute("deliveryes", deliveryService.findAll());//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		model.addAttribute("managers", managerService.findAll(pageable, filter));//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		return "manager";//повертаємо представлення(JSP)
	}
	
	@RequestMapping(value="/admin/manager", method=RequestMethod.POST)//задаэмо методу контролера ссилку по якій цей метод буде доступний на клієнті
	public String save(@ModelAttribute("manager") ManagerForm manager,
						Model model,//передаємо модель
						@PageableDefault(5) Pageable pageable,//анотація для установлення значення за замовчуванням при введенні посторіночної розбивки в метод контролера
						@ModelAttribute("filter") ManagerFilterForm filter){//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		managerService.save(manager);
		return "redirect:/admin/manager" + getParams(pageable, filter);// для того щоб не втратити всі параметри при редіректі використовуємо метод getParams
	}
	
	@RequestMapping("/admin/manager/update/{id}")//задаэмо методу контролера ссилку по якій цей метод буде доступний на клієнті
	public String update(@PathVariable int id,// автозаповнення змінної з запиту
						 Model model,//передаємо модель
						 @PageableDefault(5) Pageable pageable,//анотація для установлення значення за замовчуванням при введенні посторіночної розбивки в метод контролера
						 @ModelAttribute("filter") ManagerFilterForm filter){
		model.addAttribute("manager", managerService.findForForm(id));
		model.addAttribute("deliveryes", deliveryService.findAll());//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		model.addAttribute("managers", managerService.findAll(pageable, filter));//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		return "manager";//повертаємо представлення(JSP)
	}
	
	@RequestMapping("/admin/manger/delete/{id}")//задаэмо методу контролера ссилку по якій цей метод буде доступний на клієнті
	public String delete(@PathVariable int id, // автозаповнення змінної з запиту
						 @PageableDefault(5) Pageable pageable, //анотація для установлення значення за замовчуванням при введенні посторіночної розбивки в метод контролера
						 @ModelAttribute("filter") ManagerFilterForm filter){
		managerService.delete(id);
		return "redirect:/admin/manager" + getParams(pageable, filter);// для того щоб не втратити всі параметри при редіректі використовуємо метод getParams
	}

	
	private String getParams(Pageable pageable, ManagerFilterForm filter) {
		StringBuilder buffer = new StringBuilder();// створюємо стрінг білдер
		buffer.append("?page=");//додаємо параметр page
		buffer.append(String.valueOf(pageable.getPageNumber()+1));//дістаємо значення номеру сторінки із pageable  і додаєио 1 адже номерація сторінок починається з 0
		buffer.append("&size=");//додаємо параметр size
		buffer.append(String.valueOf(pageable.getPageSize()));//дістаємо значення розміру сторінки із pageable
		if(pageable.getSort()!=null){//перевіряємо чи сортування не має нульового значення адже якзо нуль то немає сунсу додавати цей параметр
			buffer.append("&sort=");//додаємо параметр sort
			Sort sort = pageable.getSort();//дістаємо параметри сортування і передаємо в Sort
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");// додаємо параметр для пошуку
		if(filter.getCompanySearch()!=null)//перевіряємо чи нам від клієнта прийшов запит на пошук, 
			//без цієї провірки при кожному редіректі в нас в це значення буде присвоюватися null
		buffer.append(filter.getCompanySearch());
		for(Integer i : filter.getDeliveryIds()){//гортаємо ліст id сутності Delivery
			buffer.append("&deliveryIds=");//додаємо параметр
			buffer.append(i.toString());
		}
		return buffer.toString();//повертаємо стрічку з всіма нашими параметрами
	}
}
