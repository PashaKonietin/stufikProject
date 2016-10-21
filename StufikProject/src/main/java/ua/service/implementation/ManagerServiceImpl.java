package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Manager;
import ua.form.ManagerForm;
import ua.form.filter.ManagerFilterForm;
import ua.repository.ManagerRepository;
import ua.service.ManagerService;
import ua.service.implementation.specification.ManagerFilterAdapter;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public void save(ManagerForm form) {
		Manager manager = new Manager();
		manager.setCompany(form.getCompany());
		manager.setEmail(form.getEmail());
		manager.setName(form.getName());
		manager.setSurname(form.getSurname());
		manager.setMiddleName(form.getMiddleName());
		manager.setId(form.getId());
		managerRepository.save(manager);
	}

	@Override
	public ManagerForm findForForm(int id) {
		Manager manager = managerRepository.findOne(id);
		ManagerForm form = new ManagerForm();
		form.setCompany(manager.getCompany());
		form.setEmail(manager.getEmail());
		form.setId(manager.getId());
		form.setMiddleName(manager.getMiddleName());
		form.setName(manager.getName());
		form.setSurname(manager.getSurname());
		return form;
	}

	@Override
	public void delete(int id) {
		managerRepository.delete(id);
	}

	@Override
	public Manager findById(int id) {
		return managerRepository.findOne(id);
	}

	@Override
	public List<Manager> findAll() {
		return managerRepository.findAll();
	}

	@Override
	public Page<Manager> findAll(Pageable pageable) {
		return managerRepository.findAll(pageable);
	}

	@Override
	public Page<Manager> findAll(Pageable pageable, ManagerFilterForm filter) {
		return managerRepository.findAll(new ManagerFilterAdapter(filter), pageable);
	}

}
