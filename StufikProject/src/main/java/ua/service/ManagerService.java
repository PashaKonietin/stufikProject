package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Manager;
import ua.form.ManagerForm;
import ua.form.filter.ManagerFilterForm;

public interface ManagerService {

	void save(ManagerForm form);
	
	ManagerForm findForForm(int id);
	
	void delete(int id);
	
	Manager findById(int id);
	
	List<Manager> findAll();
	
	Page<Manager> findAll(Pageable pageable);
	
	Page<Manager> findAll(Pageable pageable, ManagerFilterForm filter);
}
