package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PactivityProjectstageModel {
	@SerializedName("z_ac_projectstage")
	private String z_ac_projectstage;

	public String getValue() {
		return this.z_ac_projectstage;
	}

	public void setValue(String z_ac_projectstage) {
		this.z_ac_projectstage = z_ac_projectstage;
	}
}
