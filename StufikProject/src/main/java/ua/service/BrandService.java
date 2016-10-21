package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Brand;
import ua.form.filter.BrandFilterForm;

public interface BrandService {
	
	void save(Brand brand);
	
	void delete(int id);
	
	Brand findByName(String name);
	
	Brand findOne(int id);
	
	List<Brand> findAll();
	
	Page<Brand> findAll(Pageable pageable);
	
	Page<Brand> findAll(Pageable pageable, BrandFilterForm filter);

}
