package co.nextix.jardine.web.requesters;

import com.google.gson.annotations.SerializedName;

public class ErrorModel {
	@SerializedName("code")
	private String code;

	@SerializedName("message")
	private String message;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
