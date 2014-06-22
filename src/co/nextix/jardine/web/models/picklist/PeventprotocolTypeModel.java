package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PeventprotocolTypeModel {
	@SerializedName("z_evp_evtype")
	private String z_evp_evtype;

	public String getValue() {
		return this.z_evp_evtype;
	}

	public void setValue(String z_evp_evtype) {
		this.z_evp_evtype = z_evp_evtype;
	}
}
