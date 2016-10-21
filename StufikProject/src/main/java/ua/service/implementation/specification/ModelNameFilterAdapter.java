package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.ModelName;
import ua.form.filter.ModelNameFilterForm;

public class ModelNameFilterAdapter implements Specification<ModelName>{

	private String search = "";
	
	public ModelNameFilterAdapter(ModelNameFilterForm filter) {
		if(filter.getSearch()!=null)
		this.search = filter.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<ModelName> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase() + "%");
	}

}
