package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class DescriptionFilterForm {
	
	private String weightSearch;
	
	private List<Integer> colorIds = new ArrayList<>();
	
	private List<Integer> subCategoryIds = new ArrayList<>();
	
	private List<Integer> materialIds = new ArrayList<>();

	public String getWeightSearch() {
		return weightSearch;
	}

	public void setWeightSearch(String weightSearch) {
		this.weightSearch = weightSearch;
	}

	public List<Integer> getColorIds() {
		return colorIds;
	}

	public void setColorIds(List<Integer> colorIds) {
		this.colorIds = colorIds;
	}

	public List<Integer> getSubCategoryIds() {
		return subCategoryIds;
	}

	public void setSubCategoryIds(List<Integer> subCategoryIds) {
		this.subCategoryIds = subCategoryIds;
	}

	public List<Integer> getMaterialIds() {
		return materialIds;
	}

	public void setMaterialIds(List<Integer> materialIds) {
		this.materialIds = materialIds;
	}
	
}
