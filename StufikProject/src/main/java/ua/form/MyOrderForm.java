package ua.form;

import java.math.BigDecimal;

import ua.entity.City;
import ua.entity.Client;
import ua.entity.Delivery;

public class MyOrderForm {

	private int id;
	private BigDecimal price;
	private String quantity;
	
	
	private City cityOrder;
	private Client client;
	private Delivery delivery;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Delivery getDelivery() {
		return delivery;
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	
	public City getCityOrder() {
		return cityOrder;
	}
	
	public void setCityOrder(City cityOrder) {
		this.cityOrder = cityOrder;
	}
}