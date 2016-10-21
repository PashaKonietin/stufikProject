package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.form.filter.CommodityFilterForm;
import ua.service.CategoryService;
import ua.service.CommodityService;
import ua.service.SubCategoryService;


@Controller// вказуємо спрінгу що даний клас є біном і його необхідно підгрузити при старті програми
public class IndexController {
	
	@Autowired//вказуємо спрінгу що екземпляр цього біна ми будемо використовувати в даному класі
	// і дане поле класу потребує заповнення інєкцією залежності спрінга
	private SubCategoryService subCategoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CommodityService commodityService;
	
	@ModelAttribute("filter")// вказуємо спрінгу що значення яке повертається методом повинно автоматично поміщатися в модель
	public CommodityFilterForm getCommodityFilterForm(){//створюємо метод який повертає фільтр
		return new CommodityFilterForm();
	}
	
	@RequestMapping("/")//задаэмо методу контролера адресу по якій цей метод буде доступний на клієнті
	public String showindex(Model model,
							@PageableDefault(8) Pageable pageable,
							@ModelAttribute("filter") CommodityFilterForm filter){
		model.addAttribute("categoryes", categoryService.findAll(pageable));//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		model.addAttribute("commodities", commodityService.findAll(pageable, filter));//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		return "index";//повертаємо представлення(JSP)
	}
	
	@RequestMapping("/admin")//задаэмо методу контролера адресу по якій цей метод буде доступний на клієнті
	public String adminPanel(){
		return "adminPanel";//повертаємо представлення(JSP)
	}
	
	@RequestMapping("/login")//задаэмо методу контролера адресу по якій цей метод буде доступний на клієнті
	public String showLogin() {
		return "login";//повертаємо представлення(JSP)
	}
}
