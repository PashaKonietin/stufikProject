package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Delivery;
import ua.form.filter.DeliveryFilterForm;

public class DeliveryFilterAdapter implements Specification<Delivery>{

	private String deliveryMethodSearch;
	
	private String deliveryCompanySearch;
	
	private DeliveryFilterForm filter;
	
	private List<Specification<Delivery>> filters = new ArrayList<>();

	public DeliveryFilterAdapter(DeliveryFilterForm filter) {
		if(filter!=null){
			this.filter = filter;
		}else{
			this.filter = new DeliveryFilterForm();
		}
		if(filter.getDeliveryCompanySearch()!=null){
			this.deliveryCompanySearch = filter.getDeliveryCompanySearch();
		}
		if(filter.getDeliveryMethodSearch()!=null){
			this.deliveryMethodSearch = filter.getDeliveryMethodSearch();
		}
	}

	private void findByDeliveryCompany(){
		if(filter.getDeliveryCompanySearch()!=null){
			filters.add((root, query, cb)->{
				return cb.like(cb.upper(root.get("deliveryCompany")), deliveryCompanySearch.toUpperCase() + "%");
			});
		}
	}
	
	private void findDeliveryMethod(){
		if(filter.getDeliveryMethodSearch()!=null){
			filters.add((root, query, cb)->{
				return cb.like(cb.upper(root.get("deliveryMethod")), deliveryMethodSearch.toUpperCase() + "%");
			});
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Delivery> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByDeliveryCompany();
		findDeliveryMethod();
		if(!filters.isEmpty()){
			Specifications<Delivery> spec = Specifications.where(filters.get(0));
			for(Specification<Delivery> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}
	


}
