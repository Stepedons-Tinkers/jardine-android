package co.nextix.jardine.web.requesters;

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

	@SerializedName("details")
	private Details details;

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

	public Details getDetails() {
		return this.details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public class Details {
		// "user_name": "test_smr1",
		// "first_name": "test_smr1",
		// "last_name": "test_smr1",
		// "email1": "weng.mayo@jardinedistribution.com",
		// "z_area":
		// "CENTRAL LUZON AREA |##| NORTHEAST LUZON AREA |##| NORTHWEST LUZON AREA |##| SOUTHEAST LUZON"
		@SerializedName("user_name")
		private String user_name;

		@SerializedName("first_name")
		private String first_name;

		@SerializedName("last_name")
		private String last_name;

		@SerializedName("email1")
		private String email1;

		@SerializedName("z_area")
		private String z_area;

		public String getUserName() {
			return this.user_name;
		}

		public void setUserName(String userName) {
			this.user_name = userName;
		}

		public String getFirstName() {
			return this.first_name;
		}

		public void setFirstName(String firstName) {
			this.first_name = firstName;
		}

		public String getLastName() {
			return this.last_name;
		}

		public void setLastName(String lastName) {
			this.last_name = lastName;
		}

		public String getEmail() {
			return this.email1;
		}

		public void setEmail(String email) {
			this.email1 = email;
		}

		public String getArea() {
			return this.z_area;
		}

		public void setArea(String area) {
			this.z_area = area;
		}
	}
}
