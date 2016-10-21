package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.ModelName;
import ua.form.filter.ModelNameFilterForm;


public interface ModelNameService {

	void save(ModelName modelName);
	
	void delete(int id);
	
	ModelName findByName(String name);
	
	ModelName findOne(int id);
	
	List<ModelName> findAll();
	
	Page<ModelName> findAll(Pageable pageable);
	
	Page<ModelName> findAll(Pageable pageable, ModelNameFilterForm filter);
}
