package ua.service;

import java.math.BigDecimal;


public interface CartService {

	void deleteCommodity(int id, int clientId);
	
	void addCommodity(int id, int clientId);
	
	BigDecimal totalPrice(int clientId);
}
