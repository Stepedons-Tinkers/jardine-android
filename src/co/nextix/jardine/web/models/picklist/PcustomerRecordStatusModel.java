package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PcustomerRecordStatusModel {
	@SerializedName("z_cu_customerrecstat")
	private String z_cu_customerrecstat;

	public String getValue() {
		return this.z_cu_customerrecstat;
	}

	public void setValue(String z_cu_customerrecstat) {
		this.z_cu_customerrecstat = z_cu_customerrecstat;
	}
}
