package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Color;
import ua.form.filter.ColorFilterForm;

public interface ColorService {
	
	void save(Color color);
	
	void delete(int id);
	
	Color findByName(String name);
	
	Color findById(int id);
	
	List<Color> findAll();

	Page<Color> findAll(Pageable pageable);
	
	Page<Color> findAll(Pageable pageable, ColorFilterForm filter);

}
