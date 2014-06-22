package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PcustomerSizeModel {
	@SerializedName("z_cu_customersize")
	private String z_cu_customersize;

	public String getValue() {
		return this.z_cu_customersize;
	}

	public void setValue(String z_cu_customersize) {
		this.z_cu_customersize = z_cu_customersize;
	}
}
