package ua.form.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommodityFilterForm {

	private String min = "";
	
	private String max = "";
	
	private int minInt = 0;
	
	private int maxInt = 0;
	
	private static final Pattern p = Pattern.compile("^[0-9]{1,9}$");
	
	private List<Integer> brandIds = new ArrayList<>(); 
	
	private List<Integer> colorIds = new ArrayList<>();
	
	private List<Integer> subCategoryIds = new ArrayList<>();
	
	private List<Integer> materialIds = new ArrayList<>();
	

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(p.matcher(min).matches()) minInt = Integer.valueOf(min);
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(p.matcher(max).matches()) maxInt = Integer.valueOf(max);
		this.max = max;
	}

	public int getMinInt() {
		return minInt;
	}

	public void setMinInt(int minInt) {
		this.minInt = minInt;
	}

	public int getMaxInt() {
		return maxInt;
	}

	public void setMaxInt(int maxInt) {
		this.maxInt = maxInt;
	}

	public List<Integer> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Integer> brandIds) {
		this.brandIds = brandIds;
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
