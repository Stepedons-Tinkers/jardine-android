package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PactivityEndUserActivityTypes {
	@SerializedName("z_ac_enduseractypeid")
	private String id;

	@SerializedName("z_ac_enduseractype")
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
