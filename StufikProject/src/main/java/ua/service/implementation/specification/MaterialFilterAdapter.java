package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Material;
import ua.form.filter.MaterialFilterForm;

public class MaterialFilterAdapter implements Specification<Material>{

	private String search = "";
	
	public MaterialFilterAdapter(MaterialFilterForm filter) {
		if(filter.getSearch()!=null)
		this.search = filter.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase() + "%");
	}

}
