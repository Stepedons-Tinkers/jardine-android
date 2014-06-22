package co.nextix.jardine.web;

import com.google.gson.annotations.SerializedName;

public class PicklistRequester<T> {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private T result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public T getResult() {
		return this.result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
