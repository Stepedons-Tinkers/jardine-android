package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PactivitytypeCategoryModel {
	@SerializedName("z_act_acttypcat")
	private String z_act_acttypcat;

	public String getValue() {
		return this.z_act_acttypcat;
	}

	public void setValue(String z_act_acttypcat) {
		this.z_act_acttypcat = z_act_acttypcat;
	}
}
