package ua.service.implementation;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.entity.Client;
import ua.entity.Role;
import ua.form.ClientForm;
import ua.form.filter.ClientFilterForm;
import ua.repository.ClientRepository;
import ua.service.ClientService;
import ua.service.MailSender;
import ua.service.implementation.specification.ClientFilterAdapter;

@Service("userDetailsService")
public class ClientServiceImpl implements ClientService, UserDetailsService{

	private static final String CONTENT = "We are glad that you successfully registered. Now please confirm your email";
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
    private BCryptPasswordEncoder encoder;
	
	@Autowired
    private MailSender mailSender;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		if(Pattern.matches("^[0-9]{1,12}$", login)){
			return clientRepository.findOne(Integer.valueOf(login));
		}
		return clientRepository.findByLogin(login);
	}

	@Override
	public Client findByLogin(String login) {
		return clientRepository.findByLogin(login);
	}

	@Override
	public Client findById(int id) {
		return clientRepository.findOne(id);
	}

	@Override
	public void save(ClientForm form) {
		Client client = new Client();
		client.setAdress(form.getAdress());
		client.setCity(form.getCity());
		client.setEmail(form.getEmail());
		client.setLogin(form.getLogin());
		client.setName(form.getName());
		client.setCart(form.getCart());
		client.setPassword(encoder.encode(form.getPassword()));
		client.setId(form.getId());
		client.setRole(Role.ROLE_USER);
		
		UUID uuid = UUID.randomUUID();
		String verification = uuid.toString();
		client.setVerification(verification);
		mailSender.sendMail(CONTENT, form.getEmail(), "http://localhost:8080/verification/" + verification);
		clientRepository.save(client);
	}

	@Override
	public void saveUser(Client client) {
		client.setRole(Role.ROLE_USER);
		client.setPassword(encoder.encode(client.getPassword()));
		clientRepository.saveAndFlush(client);
	}
	
	@PostConstruct
	public void saveAdmin(){
		Client client = clientRepository.findOne(10);
		if(client==null){
			client = new Client();
			client.setRole(Role.ROLE_ADMIN);
			client.setPassword(encoder.encode("admin"));
			client.setLogin("admin");
			client.setConfirmed(true);
			client.setId(1);
			clientRepository.save(client);
		}
	}
	
	@Override
	public ClientForm findForForm(int id) {
		Client client = clientRepository.findOne(id);
		ClientForm form = new ClientForm();
		form.setAdress(client.getAdress());
		form.setCity(client.getCity());
		form.setEmail(client.getEmail());
		form.setId(client.getId());
		form.setLogin(client.getLogin());
		form.setName(client.getName());
		form.setPassword(client.getPassword());
		return form;
	}
	
	@Override
	public void delete(int id) {
		clientRepository.delete(id);
	}

	@Override
	public Page<Client> findAll(ClientFilterForm filter, Pageable pageable) {
		return clientRepository.findAll(new ClientFilterAdapter(filter), pageable);
	}

	@Override
	public Client findByVerification(String verification) {
		return clientRepository.findByVerification(verification);
	}

	@Override
	public void saveAuthenticationUser(Client client) {
		clientRepository.saveAndFlush(client);
	}

	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

//	@Override
//	public List<Commodity> findAllCommodity(int id) {
//		return clientRepository.findAllCommodity(id);
//	}

}
