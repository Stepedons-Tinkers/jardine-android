package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PcompetitorProductStockStatusModel {
	@SerializedName("z_cps_stockstatus")
	private String z_cps_stockstatus;

	public String getValue() {
		return this.z_cps_stockstatus;
	}

	public void setValue(String z_cps_stockstatus) {
		this.z_cps_stockstatus = z_cps_stockstatus;
	}
}
