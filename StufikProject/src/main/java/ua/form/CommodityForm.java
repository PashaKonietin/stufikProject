package ua.form;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Brand;
import ua.entity.Client;
import ua.entity.Color;
import ua.entity.Manager;
import ua.entity.Material;
import ua.entity.ModelName;
import ua.entity.SubCategory;

public class CommodityForm {

	private int id;
	private String guarantee;
	private BigDecimal price;
	private String path;
	private int version;
	private String description;
	
	private MultipartFile multipartFile;
	
	private Brand brand;
	private ModelName modelName;
	private Manager manager;
	private Color color;
	private Material material;
	private SubCategory subCategory;
	private List<Client> clients;
	
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

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public ModelName getModelName() {
		return modelName;
	}
	
	public void setModelName(ModelName modelName) {
		this.modelName = modelName;
	}
	
	public Manager getManager() {
		return manager;
	}
	
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public List<Client> getClients() {
		return clients;
	}
	
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	public String getGuarantee() {
		return guarantee;
	}
	
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
}
