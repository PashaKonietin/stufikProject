package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class ClientFilterForm {
	
	private String loginSearch;
	
	private String emailSearch;
	
	private List<Integer> cityIds = new ArrayList<Integer>();

	public String getLoginSearch() {
		return loginSearch;
	}

	public void setLoginSearch(String loginSearch) {
		this.loginSearch = loginSearch;
	}

	public String getEmailSearch() {
		return emailSearch;
	}

	public void setEmailSearch(String emailSearch) {
		this.emailSearch = emailSearch;
	}

	public List<Integer> getCityIds() {
		return cityIds;
	}

	public void setCityIds(List<Integer> cityIds) {
		this.cityIds = cityIds;
	}
	
}
