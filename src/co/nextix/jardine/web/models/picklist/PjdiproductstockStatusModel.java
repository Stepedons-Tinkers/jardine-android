package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PjdiproductstockStatusModel {
	@SerializedName("z_jps_stockstatus")
	private String z_jps_stockstatus;

	public String getValue() {
		return this.z_jps_stockstatus;
	}

	public void setValue(String z_jps_stockstatus) {
		this.z_jps_stockstatus = z_jps_stockstatus;
	}
}
