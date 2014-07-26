package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PsalesProtocolTypeModel {
	@SerializedName("z_sp_protocoltype")
	private String z_sp_protocoltype;

	public String getValue() {
		return this.z_sp_protocoltype;
	}

	public void setValue(String z_sp_protocoltype) {
		this.z_sp_protocoltype = z_sp_protocoltype;
	}
}
