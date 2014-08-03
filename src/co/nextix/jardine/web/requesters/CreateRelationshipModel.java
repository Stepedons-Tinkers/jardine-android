package co.nextix.jardine.web.requesters;

import com.google.gson.annotations.SerializedName;

public class CreateRelationshipModel {
	@SerializedName("create")
	private String create;

	public String getCreate() {
		return this.create;
	}

	public void setCreate(String create) {
		this.create = create;
	}
}
