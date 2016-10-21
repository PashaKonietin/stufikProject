package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Description;
import ua.form.DescriptionForm;
import ua.form.filter.DescriptionFilterForm;

public interface DescriptionService {

	void save(DescriptionForm form);
	
	DescriptionForm findForForm(int id);
	
	Description findByName(String name);
	
	void delete(int id);
	
	Description findById(int id);
	
	List<Description> findAll();
	
	Page<Description> findAll(Pageable pageable);
	
	Page<Description> findAll(Pageable pageable, DescriptionFilterForm filter);
}
