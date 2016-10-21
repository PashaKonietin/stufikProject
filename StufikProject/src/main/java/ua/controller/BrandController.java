package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Brand;
import ua.form.filter.BrandFilterForm;
import ua.service.BrandService;
import ua.service.implementation.validator.BrandValidator;

@Controller// вказуємо спрінгу що даний клас є біном і його необхідно підгрузити при старті програми
public class BrandController {
	
	@Autowired//вказуємо спрінгу що екземпляр цього біна ми будемо використовувати в даному класі
	// і дане поле класу потребує заповнення інєкцією залежності спрінга
	private BrandService brandService;
	
	@ModelAttribute("brand")// вказуємо спрінгу що значення яке повертається методом повинно автоматично поміщатися в модель
	public Brand getBrand(){//створюємо метод який повертає entity
		return new Brand();
	}
	
	@ModelAttribute("filter")// вказуємо спрінгу що значення яке повертається методом повинно автоматично поміщатися в модель
	public BrandFilterForm getBrandFilterForm(){//створюємо метод який повертає фільтр
		return new BrandFilterForm();
	}
	
	@InitBinder("brand")// //реєструємо валідатор для entity Brand
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new BrandValidator(brandService));
	}
	
	@RequestMapping("/admin/brand")//задаэмо методу контролера ссилку по якій цей метод буде доступний на клієнті
	public String showBrand(Model model,//передаємо модель
							@PageableDefault(5) Pageable pageable,//анотація для установлення значення за замовчуванням при введенні посторіночної розбивки в метод контролера
							@ModelAttribute("filter") BrandFilterForm filter){
		model.addAttribute("brands", brandService.findAll(pageable, filter));//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		return "brand";//повертаємо представлення(JSP)
	}
	
	@RequestMapping(value="/admin/brand", method=RequestMethod.POST)//задаэмо методу контролера ссилку по якій цей метод буде доступний на клієнті
	public String save(@ModelAttribute("brand") @Valid Brand brand,
						BindingResult br,
						Model model,//передаємо модель
						@PageableDefault(5) Pageable pageable,//анотація для установлення значення за замовчуванням при введенні посторіночної розбивки в метод контролера
						@ModelAttribute("filter") BrandFilterForm filter){
		if(br.hasErrors()){//якщо при створенні об'єтку виникнуть помилки то ми знову просто виведем клієнту всі значення
			model.addAttribute("brands", brandService.findAll(pageable, filter));//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
			return "brand";
		}
		brandService.save(brand);
		return "redirect:/admin/brand" + getParams(pageable, filter);// для того щоб не втратити всі параметри при редіректі використовуємо метод getParams
	}
	
	@RequestMapping(value="/admin/brand/update/{id}")//задаэмо методу контролера ссилку по якій цей метод буде доступний на клієнті
	public String update(Model model,//передаємо модель
						@PathVariable int id,// автозаповнення змінної з запиту
						@PageableDefault(5) Pageable pageable,//анотація для установлення значення за замовчуванням при введенні посторіночної розбивки в метод контролера
						@ModelAttribute("filter") BrandFilterForm filter){
		model.addAttribute("brand", brandService.findOne(id));// знаходимо бренд який ми апдейтимо
		model.addAttribute("brands", brandService.findAll(pageable, filter));//передаємо в модель атрибут типу "ключ-значення" який буде доступний на клієнті по ключу
		return "brand";//повертаємо представлення(JSP)
	}
	
	@RequestMapping(value="/admin/brand/delete/{id}")//задаэмо методу контролера ссилку по якій цей метод буде доступний на клієнті
	public String delete(@PathVariable int id,// автозаповнення змінної з запиту
						 @PageableDefault(5) Pageable pageable,//анотація для установлення значення за замовчуванням при введенні посторіночної розбивки в метод контролера
						 @ModelAttribute("filter") BrandFilterForm filter){
		brandService.delete(id);
		return "redirect:/admin/brand" + getParams(pageable, filter);// для того щоб не втратити всі параметри при редіректі використовуємо метод getParams
	}
	

	private String getParams(Pageable pageable, BrandFilterForm filter) {
		StringBuilder bilder = new StringBuilder();// створюємо стрінг білдер
		bilder.append("?page=");//додаємо параметр page
		bilder.append(String.valueOf(pageable.getPageNumber()+1));//дістаємо значення номеру сторінки із pageable  і додаєио 1 адже номерація сторінок починається з 0
		bilder.append("&size=");//додаємо параметр size
		bilder.append(String.valueOf(pageable.getPageSize()));//дістаємо значення розміру сторінки із pageable
		if(pageable.getSort()!=null){//перевіряємо чи сортування не має нульового значення адже якзо нуль то немає сунсу додавати цей параметр
			bilder.append("&sort=");//додаємо параметр sort
			Sort sort = pageable.getSort();//дістаємо параметри сортування і передаємо в Sort
			sort.forEach((order)->{
				bilder.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC){
					bilder.append(",desc");
				}
			});
		}
		bilder.append("&search=");// додаємо параметр для пошуку
		if(filter.getSearch()!=null)//перевіряємо чи нам від клієнта прийшов запит на пошук,
			//без цієї провірки при кожному редіректі в нас в це значення буде присвоюватися null
		bilder.append(filter.getSearch());
		return bilder.toString();//повертаємо стрічку з всіма нашими параметрами
	}
}
