package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.City;
import ua.form.filter.CityFilterForm;

public class CityFilterAdapter implements Specification<City> {

	private String search = "";
	
	
	public CityFilterAdapter(CityFilterForm filter) {
		if(filter.getSearch()!=null){
			this.search = filter.getSearch();
		}
	}

	@Override
	public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase() + "%");
	}

}
