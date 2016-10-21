package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Discount;
import ua.form.filter.DiscountFilterForm;

public interface DiscountService {

	void save(Discount discount);
	
	void delete(int id);
	
	Discount findById(int id);
	
	List<Discount> findAll();
	
	Page<Discount> findAll(Pageable pageable);

	Page<Discount> findAll(Pageable pageable, DiscountFilterForm filter);
} 
