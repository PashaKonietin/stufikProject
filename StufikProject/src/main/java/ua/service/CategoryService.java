package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Category;
import ua.form.CategoryForm;
import ua.form.filter.CategoryFilterForm;

public interface CategoryService {

	void save(CategoryForm categoryForm);
	
	void delete(int id);
	
	Category findByName(String name);
	
	Category findOne(int id);
	
	List<Category> findAll();
	
	Page<Category> findAll(Pageable pageable, CategoryFilterForm filter);

	Page<Category> findAll(Pageable pageable);
}
