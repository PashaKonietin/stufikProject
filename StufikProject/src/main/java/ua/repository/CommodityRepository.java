package ua.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Commodity;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>, JpaSpecificationExecutor<Commodity> {

	
	@Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.brand b"
			+ " LEFT JOIN FETCH c.subCategory s"
			+ " LEFT JOIN FETCH c.color r"
			+ " LEFT JOIN FETCH c.material t"
			+ " LEFT JOIN FETCH c.modelName m"
			+ " LEFT JOIN FETCH c.manager n"
			+ " WHERE c.id=:id")
	Commodity findOneCommodityInited(@Param("id") int id);
	
	@Query("SELECT c FROM Commodity c LEFT JOIN  c.brand b"
			+ " LEFT JOIN  c.subCategory s"
			+ " LEFT JOIN  c.color r"
			+ " LEFT JOIN  c.material t"
			+ " LEFT JOIN  c.modelName m"
			+ " LEFT JOIN  c.manager n"
			+ " WHERE s.id=:id")
	Page<Commodity> findBySub(@Param("id") int id, Pageable pageable);
	
	@Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.brand b"
			+ " LEFT JOIN FETCH c.subCategory s"
			+ " LEFT JOIN FETCH c.color r"
			+ " LEFT JOIN FETCH c.material m"
			+ " LEFT JOIN FETCH c.modelName m"
			+ " LEFT JOIN FETCH c.manager n")
	List<Commodity> findAll();
	
	@Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.clients l"
			+ " LEFT JOIN FETCH c.brand b"
			+ " LEFT JOIN FETCH c.subCategory s"
			+ " LEFT JOIN FETCH c.color r"
			+ " LEFT JOIN FETCH c.material m"
			+ " LEFT JOIN FETCH c.modelName m"
			+ " LEFT JOIN FETCH c.manager n"
			+ " WHERE l.id=:id")
	List<Commodity> findAllCommoditiesByClient(@Param("id") int id);
	
}
