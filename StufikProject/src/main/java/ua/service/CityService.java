package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.City;
import ua.form.filter.CityFilterForm;

public interface CityService {

	void save(City city);
	
	void delete(int id);
	
	City findByName(String name);
	
	City findOne(int id);
	
	List<City> findAll();
	
	Page<City> findAll(Pageable pageable);
	
	Page<City> findAll(Pageable pageable, CityFilterForm filter);
}
