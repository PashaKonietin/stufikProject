package ua.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(indexes={@Index(columnList = "deliveryMethod"), 
				@Index(columnList = "deliveryCompany"),
		})
public class Delivery {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String deliveryMethod;
	
	private String deliveryCompany;
	
	@OneToMany(mappedBy="delivery")
	private List<MyOrder> orders;

	public int getId() {
		return id;
	}
	
	public Delivery() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDeliveryCompany() {
		return deliveryCompany;
	}

	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	
	public List<MyOrder> getMyOrders() {
		return orders;
	}

	public void setMyOrders(List<MyOrder> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", deliveryMethod=" + deliveryMethod
				+ ", deliveryCompany=" + deliveryCompany + "]";
	}
	
}
