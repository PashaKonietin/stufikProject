package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.SubCategory;
import ua.form.SubCategoryForm;
import ua.form.filter.SubCategoryFilterForm;

public interface SubCategoryService {

	void save(SubCategoryForm subCategory);
	
	SubCategoryForm findFormForm(int id);
	
	void deleteById(int id);
	
	SubCategory findOne(int id);
	
	List<SubCategory> findAll();
	
	Page<SubCategory> findAll(Pageable pageable);
	
	Page<SubCategory> findAll(Pageable pageable, SubCategoryFilterForm filter);
}
