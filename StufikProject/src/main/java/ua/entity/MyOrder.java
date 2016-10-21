package ua.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class MyOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int quantity;
	private BigDecimal price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private City cityOrder;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Delivery delivery;
	
	@ManyToMany
	@JoinTable(name="Commodities_MyOrders", joinColumns =
	@JoinColumn(name = "myOrder_id"), inverseJoinColumns =
	@JoinColumn(name = "commodity_id"))
	private List<Commodity> commodities;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Client client;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public List<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public City getCityOrder() {
		return cityOrder;
	}

	public void setCityOrder(City cityOrder) {
		this.cityOrder = cityOrder;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
