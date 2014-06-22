package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PcustomercontactPositionModel {
	@SerializedName("z_cuc_position")
	private String z_cuc_position;

	public String getValue() {
		return this.z_cuc_position;
	}

	public void setValue(String z_cuc_position) {
		this.z_cuc_position = z_cuc_position;
	}
}
