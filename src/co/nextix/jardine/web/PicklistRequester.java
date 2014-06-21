package co.nextix.jardine.web;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PicklistRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private List<PicklistModel> result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public List<PicklistModel> getResult() {
		return this.result;
	}

	public void setResult(List<PicklistModel> result) {
		this.result = result;
	}
}
