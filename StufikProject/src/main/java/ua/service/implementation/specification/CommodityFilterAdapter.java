package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Commodity;
import ua.form.filter.CommodityFilterForm;

public class CommodityFilterAdapter implements Specification<Commodity>{
	
	private final CommodityFilterForm filter;
	
	private final List<Specification<Commodity>> filters = new ArrayList<>();
	
	public CommodityFilterAdapter(CommodityFilterForm filter) {
		if(filter!=null){
		this.filter = filter;
		}else{
			this.filter=new CommodityFilterForm();
		}
	}

	private void findByPrice(){
		if(filter.getMinInt()!=0&&filter.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("price");
				return cb.between(exp, filter.getMinInt(), filter.getMaxInt());
			});
		}else if(filter.getMinInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("price");
				return cb.ge(exp, filter.getMinInt());
			});
		}else if(filter.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("price");
				return cb.le(exp, filter.getMaxInt());
			});
		}
	}
	
	private void findByBrand(){
		if(!filter.getBrandIds().isEmpty()){
			filters.add((root, query, cb)->root.get("brand").in(filter.getBrandIds()));
		}
	}
	
	private void findByColor(){
		if(!filter.getColorIds().isEmpty()){
			filters.add((root, query, cb)->root.get("color").in(filter.getColorIds()));
		}
	}
	
	private void findBySubCategory(){
		if(!filter.getSubCategoryIds().isEmpty()){
			filters.add((root, query, cb)->root.get("subCategory").in(filter.getSubCategoryIds()));
		}
	}
	
	private void findByMaterial(){
		if(!filter.getMaterialIds().isEmpty()){
			filters.add((root, query, cb)->root.get("material").in(filter.getMaterialIds()));
		}
	}
	

	@Override
	public Predicate toPredicate(Root<Commodity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class && query.getResultType()!=long.class){
			root.fetch("brand", JoinType.LEFT);
			root.fetch("subCategory", JoinType.LEFT);
			root.fetch("color", JoinType.LEFT);
			root.fetch("material", JoinType.LEFT);
			root.fetch("modelName", JoinType.LEFT);
		}
		findByBrand();
		findByColor();
		findByMaterial();
		findBySubCategory();
		findByPrice();
		if(!filters.isEmpty()){
			Specifications<Commodity> spec = Specifications.where(filters.get(0));
			for (Specification<Commodity> s : filters.subList(1, filters.size())) {
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
