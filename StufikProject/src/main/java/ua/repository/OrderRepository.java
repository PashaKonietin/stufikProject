package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.MyOrder;

public interface OrderRepository extends JpaRepository<MyOrder, Integer>, JpaSpecificationExecutor<MyOrder>{


	@Query("SELECT o FROM MyOrder o LEFT JOIN  o.client b"
			+ " LEFT JOIN  o.delivery d"
			+ " LEFT JOIN  o.cityOrder r"
			+ " LEFT JOIN  o.commodities c")
	Page<MyOrder> findAll(Pageable pageable);
}
