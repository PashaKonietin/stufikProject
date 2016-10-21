package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class ManagerFilterForm {

	private String companySearch;
	
	private List<Integer> deliveryIds = new ArrayList<Integer>();

	public String getCompanySearch() {
		return companySearch;
	}

	public void setCompanySearch(String companySearch) {
		this.companySearch = companySearch;
	}

	public List<Integer> getDeliveryIds() {
		return deliveryIds;
	}

	public void setDeliveryIds(List<Integer> deliveryIds) {
		this.deliveryIds = deliveryIds;
	}
	
}
