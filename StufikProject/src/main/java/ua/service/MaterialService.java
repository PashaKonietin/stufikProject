package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Material;
import ua.form.filter.MaterialFilterForm;

public interface MaterialService {

	void save(Material material);
	
	void delete(int id);
	
	Material findByName(String name);
	
	Material findOne(int id);
	
	List<Material> findAll();
	
	Page<Material> findAll(Pageable pageable);

	Page<Material> findAll(Pageable pageable, MaterialFilterForm filter);
}
