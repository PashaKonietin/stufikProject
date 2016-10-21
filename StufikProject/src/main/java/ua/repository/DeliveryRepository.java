package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>, JpaSpecificationExecutor<Delivery> {

	Delivery findByDeliveryMethod(String deliveryMethod);
	
	Delivery findByDeliveryCompany(String deliveryCompany);
}
