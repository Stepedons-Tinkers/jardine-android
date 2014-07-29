package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class UserModel {
	// "id": "1",
	// "user_name": "admin",
	// "first_name": "",
	// "last_name": "Administrator"

	@SerializedName("id")
	private String crmid;

	@SerializedName("user_name")
	private String username;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("last_name")
	private String lastName;

	public String getCrmId() {
		return this.crmid;
	}

	public void setCrmId(String crmid) {
		this.crmid = crmid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
