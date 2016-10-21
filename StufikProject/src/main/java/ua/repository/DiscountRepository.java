package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer>, JpaSpecificationExecutor<Discount>{
	

}
