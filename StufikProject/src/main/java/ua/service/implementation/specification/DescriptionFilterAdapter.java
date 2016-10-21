//package ua.service.implementation.specification;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.JoinType;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.domain.Specifications;
//
//import ua.entity.Description;
//import ua.form.filter.DescriptionFilterForm;
//
//public class DescriptionFilterAdapter implements Specification<Description>{
//
//	private String weightSearch = "";
//	
//	private final DescriptionFilterForm filter;
//
//	private final List<Specification<Description>> filters = new ArrayList<>();
//	
//	public DescriptionFilterAdapter(DescriptionFilterForm filter) {
//		if(filter!=null){
//			this.filter= filter;
//		}else{
//			this.filter=new DescriptionFilterForm();
//		}
//		if(filter.getWeightSearch()!=null){
//			this.weightSearch = filter.getWeightSearch();
//		}
//	}
//
//	private void findByWeight(){
//		if(filter.getWeightSearch()!=null){
//			filters.add((root, query, cb)->{
//				return cb.like(cb.upper(root.get("weight")), weightSearch.toUpperCase() + "%");
//			});
//		}
//	}
//	
//	private void findByColor(){
//		if(!filter.getColorIds().isEmpty()){
//			filters.add((root, query, cb)->root.get("color").in(filter.getColorIds()));
//		}
//	}
//	
//	private void findBySubCategory(){
//		if(!filter.getSubCategoryIds().isEmpty()){
//			filters.add((root, query, cb)->root.get("subCategory").in(filter.getSubCategoryIds()));
//		}
//	}
//	
//	private void findByMaterial(){
//		if(!filter.getMaterialIds().isEmpty()){
//			filters.add((root, query, cb)->root.get("material").in(filter.getMaterialIds()));
//		}
//	}
//
//	@Override
//	public Predicate toPredicate(Root<Description> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//		if(query.getResultType()!=Long.class && query.getResultType()!=long.class){
//			root.fetch("subCategory", JoinType.LEFT);
//			root.fetch("color", JoinType.LEFT);
//			root.fetch("material", JoinType.LEFT);
//		}
//		findBySubCategory();
//		findByColor();
//		findByMaterial();
//		findByWeight();
//		if(!filters.isEmpty()){
//			Specifications<Description> spec = Specifications.where(filters.get(0));
//			for (Specification<Description> s : filters.subList(1, filters.size())) {
//				spec = spec.and(s);
//			}
//			return spec.toPredicate(root, query, cb);
//		}
//		return null;
//	}
//
//}
