package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PactivityProjectcategoryModel {
	@SerializedName("z_ac_projectcategory")
	private String z_ac_projectcategory;

	public String getValue() {
		return this.z_ac_projectcategory;
	}

	public void setValue(String z_ac_projectcategory) {
		this.z_ac_projectcategory = z_ac_projectcategory;
	}
}
