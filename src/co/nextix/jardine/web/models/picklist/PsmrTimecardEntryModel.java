package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PsmrTimecardEntryModel {

	@SerializedName("z_stc_picklist")
	private String z_stc_picklist;

	public String getValue() {
		return this.z_stc_picklist;
	}

	public void setValue(String z_stc_picklist) {
		this.z_stc_picklist = z_stc_picklist;
	}
}
