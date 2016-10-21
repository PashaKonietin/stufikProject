package ua.service.implementation.specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Brand;
import ua.form.filter.BrandFilterForm;

public class BrandFilterAdapter implements Specification<Brand>{

	private String search = "";
	
	
	public BrandFilterAdapter(BrandFilterForm form) {
		if(form.getSearch()!=null)
		search = form.getSearch();
	}

	public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Expression<String> exp = root.get("name");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}
