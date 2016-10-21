package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Client;
import ua.form.filter.ClientFilterForm;

public class ClientFilterAdapter implements Specification<Client>{

	private String loginSearch = "";
	
	private String emailSearch = "";
	
	private final ClientFilterForm filter;
	
	private final List<Specification<Client>> filters = new ArrayList<>();
	
	public ClientFilterAdapter(ClientFilterForm filter) {
		if(filter!=null){
			this.filter=filter;
		}else{
			this.filter = new ClientFilterForm();
		}
		if(filter.getEmailSearch()!=null){
			this.emailSearch = filter.getEmailSearch();
		}
		if(filter.getLoginSearch()!=null){
			this.loginSearch = filter.getLoginSearch();
		}
	}

	private void findByLogin(){
		if(filter.getLoginSearch()!=null){
			filters.add((root, query, cb)->{
				return cb.like(cb.upper(root.get("login")), loginSearch.toUpperCase() + "%");
			});
		}
	}
	
	private void findByEmail(){
		if(filter.getEmailSearch()!=null){
			filters.add((root, query, cb)->{
				return cb.like(cb.upper(root.get("email")), emailSearch.toUpperCase() + "%");
			});
		}
	}
	
	private void findByCity(){
		if(!filter.getCityIds().isEmpty()){
			filters.add((root, query, cb)->root.get("city").in(filter.getCityIds()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class && query.getResultType()!=long.class){
			root.fetch("city", JoinType.LEFT);
		}
		findByEmail();
		findByLogin();
		findByCity();
		if(!filters.isEmpty()){
			Specifications<Client> spec = Specifications.where(filters.get(0));
			for (Specification<Client> s : filters.subList(1, filters.size())) {
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
