package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Client;
import ua.form.ClientForm;
import ua.form.filter.ClientFilterForm;


public interface ClientService {

	Client findByLogin(String login);
	
	Client findById(int id);
	
	Client findByVerification(String verification);
	
	void save(ClientForm form);
	
	void saveUser(Client client);
	
	void saveAuthenticationUser(Client client);
	
	void delete(int id);
	
	ClientForm findForForm(int id);
	
	List<Client> findAll();
	
//	List<Commodity> findAllCommodity(int id);
	
	Page<Client> findAll(ClientFilterForm filter, Pageable pageable);
}
