package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Client;
import ua.entity.Commodity;
import ua.entity.MyOrder;
import ua.form.MyOrderForm;

public interface OrderService {
		
	void processOrder(int productId, int quantity);
	
	void save(MyOrderForm order);
	
	MyOrderForm findForForm(int id);
	
	void save(MyOrderForm order, List<Commodity> commodities, Client client);
	
	void delete(int id);
	
	Page<MyOrder> findAll(Pageable pageable);
}
