package co.nextix.jardine.web.requesters;

import com.google.gson.annotations.SerializedName;

public class DefaultRequester<T> {
	@SerializedName("success")
	private String success;

	@SerializedName("error")
	private ErrorModel error;

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

	public ErrorModel getError() {
		return this.error;
	}

	public void setError(ErrorModel error) {
		this.error = error;
	}
}
