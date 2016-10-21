package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Delivery;
import ua.form.filter.DeliveryFilterForm;


public interface DeliveryService {
	
	void save(Delivery delivery);
	
	void delete(int id);
	
	Delivery findById(int id);
	
	Delivery findByDeliveryMethod(String deliveryMethod);
	
	Delivery findByDeliveryCompany(String deliveryCompany);
	
	List<Delivery> findAll();

	Page<Delivery> findAll(Pageable pageable);
	
	Page<Delivery> findAll(Pageable pageable, DeliveryFilterForm filter);

}
