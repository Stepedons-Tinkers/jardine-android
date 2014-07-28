package co.nextix.jardine.web;

import java.util.List;

import co.nextix.jardine.web.requesters.WebCreateModel;

import com.google.gson.annotations.SerializedName;

public class CreateResult {
	@SerializedName("create")
	private List<WebCreateModel> create;

	public List<WebCreateModel> getCreate() {
		return this.create;
	}

	public void setCreate(List<WebCreateModel> create) {
		this.create = create;
	}
}
