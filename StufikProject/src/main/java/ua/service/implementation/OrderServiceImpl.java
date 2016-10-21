package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Client;
import ua.entity.Commodity;
import ua.entity.MyOrder;
import ua.form.MyOrderForm;
import ua.repository.CommodityRepository;
import ua.repository.OrderRepository;
import ua.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CommodityRepository commodityRepository;

	public void processOrder(int commodityId, int quantity) {
		Commodity commodityById = commodityRepository.findOne(commodityId);

//		if(commodityById.getComoditiesInStock() < quantity){
//			throw new IllegalArgumentException("Out of stock. Available units in stock is "+ commodityById.getComoditiesInStock());
//		}
//		commodityById.setComoditiesInStock(commodityById.getComoditiesInStock()-quantity);
	}

//	@Override
//	public void save(MyOrderForm order, Commodity commodity, Client client, int id) {
//		orderRepository.save(order, commodity, client, id);
//	}

	@Override
	public void delete(int id) {
		orderRepository.delete(id);
	}

	@Override
	public Page<MyOrder> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	@Override
	public void save(MyOrderForm order) {
		MyOrder myOrder = new MyOrder();
		myOrder.setCityOrder(order.getCityOrder());
		myOrder.setClient(order.getClient());
		myOrder.setDelivery(order.getDelivery());
		myOrder.setId(order.getId());
		myOrder.setPrice(order.getPrice());
		myOrder.setQuantity(Integer.valueOf(myOrder.getQuantity()));
		orderRepository.save(myOrder);
	}

	@Override
	public MyOrderForm findForForm(int id) {
		MyOrder myOrder = new MyOrder();
		MyOrderForm order = new MyOrderForm();
		order.setCityOrder(myOrder.getCityOrder());
		order.setClient(myOrder.getClient());
		order.setDelivery(myOrder.getDelivery());
		order.setId(myOrder.getId());
		order.setPrice(myOrder.getPrice());
		order.setQuantity(String.valueOf(myOrder.getQuantity()));
		return order;
	}

	@Override
	public void save(MyOrderForm orderForm, List<Commodity> commodity, Client client) {
			MyOrder order = new MyOrder(); 
			order.setCommodities(commodity); 
			order.setClient(client); 
			order.setDelivery(orderForm.getDelivery()); 
			order.setCityOrder(orderForm.getCityOrder());
			order.setQuantity(Integer.valueOf(orderForm.getQuantity())); 
			order.setPrice(commodity.get(0).getPrice());
			orderRepository.saveAndFlush(order); 
	}


}
