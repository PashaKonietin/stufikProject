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

import ua.entity.SubCategory;
import ua.form.filter.SubCategoryFilterForm;

public class SubCategoryFilterAdapter implements Specification<SubCategory>{

	private String search = "";
	
	private final SubCategoryFilterForm filter;
	
	private final List<Specification<SubCategory>> filters = new ArrayList<>();
	
	public SubCategoryFilterAdapter(SubCategoryFilterForm filter){
		if(filter!=null){
			this.filter=filter;
		}else{
			this.filter=new SubCategoryFilterForm();
		}
		if(filter.getSearch()!=null){
			this.search = filter.getSearch();
		}
	}

	
	private void findBySearch(){
		if(filter.getSearch()!=null){
			filters.add((root, query, cb)->{
				return cb.like(cb.upper(root.get("name")), search.toUpperCase() + "%");
			});
		}
	}
	
	private void findByCategory(){
		if(!filter.getCategoryIds().isEmpty()){
			filters.add((root, query, cb)->root.get("category").in(filter.getCategoryIds()));
		}
	}
	
	@Override
	public Predicate toPredicate(Root<SubCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class && query.getResultType()!=long.class){
			root.fetch("category", JoinType.LEFT);
		}
		findBySearch();
		findByCategory();
		if(!filters.isEmpty()){
			Specifications<SubCategory> spec = Specifications.where(filters.get(0));
			for (Specification<SubCategory> s : filters.subList(1, filters.size())) {
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
