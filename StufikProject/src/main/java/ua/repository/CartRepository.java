package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import ua.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	@Query("SELECT c FROM Cart c LEFT JOIN c.commodities m"
			+ " WHERE m.id=:id")
	void deleteCommodity(@PathVariable int id);

}
