package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PjdimerchcheckStatusModel {
	@SerializedName("z_jmc_status")
	private String z_jmc_status;

	public String getValue() {
		return this.z_jmc_status;
	}

	public void setValue(String z_jmc_status) {
		this.z_jmc_status = z_jmc_status;
	}
}
