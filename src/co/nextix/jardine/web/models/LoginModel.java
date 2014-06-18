package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
	// sessionName: "3378af8953a04d3eb342f"
	// userId: "19x18"
	// version: "0.22"
	// vtigerVersion: "5.4.0"
	@SerializedName("sessionName")
	private String sessionName;

	@SerializedName("userId")
	private String userId;

	@SerializedName("version")
	private String version;

	@SerializedName("vtigerVersion")
	private String vtigerVersion;

	public String getSessionName() {
		return this.sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVtigerVersion() {
		return this.vtigerVersion;
	}

	public void setVtigerVersion(String vtigerVersion) {
		this.vtigerVersion = vtigerVersion;
	}
}
