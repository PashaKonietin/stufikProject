package ua.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(indexes={@Index(columnList="guarantee"),
		   @Index(columnList="price")})
public class Commodity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Brand brand;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private ModelName modelName;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	private Discount discount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Manager manager;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private SubCategory subCategory;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Color color;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Cart cart;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Material material;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="Commodity_Client", joinColumns =
	@JoinColumn(name = "commodity_id"), inverseJoinColumns =
	@JoinColumn(name = "client_id"))
	private List<Client> clients = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="Commodities_MyOrders", joinColumns =
	@JoinColumn(name = "commodity_id"), inverseJoinColumns =
	@JoinColumn(name = "myOrder_id"))
	private List<MyOrder> orders;
	
	private int guarantee;
	
	private BigDecimal price;
	
//	private int commoditiesInStock;
	
	private int version;
	
	private String path;
	private String description;

	public Commodity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public ModelName getModelName() {
		return modelName;
	}

	public List<MyOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<MyOrder> orders) {
		this.orders = orders;
	}

//	public int getComoditiesInStock() {
//		return commoditiesInStock;
//	}
//
//	public void setComoditiesInStock(int comoditiesInStock) {
//		this.commoditiesInStock = comoditiesInStock;
//	}

	public void setModelName(ModelName modelName) {
		this.modelName = modelName;
	}

//	public Discount getDiscount() {
//		return discount;
//	}
//
//	public void setDiscount(Discount discount) {
//		this.discount = discount;
//	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public int getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(int guarantee) {
		this.guarantee = guarantee;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Color getColor() {
		return color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
		Commodity other = (Commodity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", brand=" + brand + ", modelName="
				+ modelName + ", manager=" + manager
				+ ", guarantee=" + guarantee
				+ ", price=" + price + "]";
	}
	
}
