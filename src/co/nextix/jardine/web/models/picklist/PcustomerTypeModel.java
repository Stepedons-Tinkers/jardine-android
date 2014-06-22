package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PcustomerTypeModel {
	@SerializedName("z_cu_customertype")
	private String z_cu_customertype;

	public String getValue() {
		return this.z_cu_customertype;
	}

	public void setValue(String z_cu_customertype) {
		this.z_cu_customertype = z_cu_customertype;
	}
}
