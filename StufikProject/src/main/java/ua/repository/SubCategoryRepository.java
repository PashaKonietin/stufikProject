package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Commodity;
import ua.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer>, JpaSpecificationExecutor<SubCategory> {

	@Query("SELECT s FROM SubCategory s LEFT JOIN FETCH s.category c WHERE s.id=:id")
	SubCategory findOneSubCategoryInited(@Param("id") int id);
	
	@Query("SELECT s FROM SubCategory s JOIN s.commodities c WHERE s.id=:id")
	Page<Commodity> findCommodityBySub(@Param("id") int id, Pageable pageable);
}
