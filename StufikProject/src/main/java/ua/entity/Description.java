package ua.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes={@Index(columnList = "weight")})
public class Description {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
//	@OneToMany(mappedBy="description")
//	private List<Commodity> commodityes;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	private SubCategory subCategory;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	private Color color;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	private Material material;
	
	private int weight;

	public Description() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Material getMaterial() {
//		return material;
//	}
//
//	public void setMaterial(Material material) {
//		this.material = material;
//	}
//
//	public List<Commodity> getCommodityes() {
//		return commodityes;
//	}
//
//	public void setCommodityes(List<Commodity> commodityes) {
//		this.commodityes = commodityes;
//	}
//
//	public SubCategory getSubCategory() {
//		return subCategory;
//	}
//
//	public void setSubCategory(SubCategory subCategory) {
//		this.subCategory = subCategory;
//	}
//
//	public Color getColor() {
//		return color;
//	}
//
//	public void setColor(Color color) {
//		this.color = color;
//	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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
		Description other = (Description) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
