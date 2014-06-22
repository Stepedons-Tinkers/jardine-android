package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PworkplanentryStatusModel {
	@SerializedName("z_wpe_status")
	private String z_wpe_status;

	public String getValue() {
		return this.z_wpe_status;
	}

	public void setValue(String z_wpe_status) {
		this.z_wpe_status = z_wpe_status;
	}
}
