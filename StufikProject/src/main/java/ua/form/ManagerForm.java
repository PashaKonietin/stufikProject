package ua.form;


public class ManagerForm {

	private int id;
	
	private String name;
	private String surname;
	private String middleName;
	private String email;
	private String company;
	
	public ManagerForm() {

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ManagerForm [id=" + id + ", name=" + name + ", surname="
				+ surname + ", middleName=" + middleName + ", email=" + email
				+ ", company=" + company + "]";
	}

}
