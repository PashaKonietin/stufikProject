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

import ua.entity.Manager;
import ua.form.filter.ManagerFilterForm;

public class ManagerFilterAdapter implements Specification<Manager>{

	private String companySearch = "";
	
	private final ManagerFilterForm filter;
	
	private List<Specification<Manager>> filters = new ArrayList<>();
	
	
	public ManagerFilterAdapter(ManagerFilterForm filter) {
		if(filter!=null){
			this.filter = filter;
		}else{
			this.filter = new ManagerFilterForm();
		}
		if(filter.getCompanySearch()!=null){
			this.companySearch = filter.getCompanySearch();
		}
	}

	private void findByCompany(){
		if(filter.getCompanySearch()!=null){
			filters.add((root, query, cb)->{
				return cb.like(cb.upper(root.get("company")), companySearch.toUpperCase() + "%");
			});
		}
	}
	
	private void findByDelivery(){
		if(!filter.getDeliveryIds().isEmpty()){
			filters.add((root, query, cb)->root.get("color").in(filter.getDeliveryIds()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Manager> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class && query.getResultType()!=long.class){
			root.fetch("delivery", JoinType.LEFT);
		}
		findByCompany();
		findByDelivery();
		if(!filters.isEmpty()){
			Specifications<Manager> spec = Specifications.where(filters.get(0));
			for (Specification<Manager> s : filters.subList(1, filters.size())) {
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

	
}
