package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class SMRModel {
	// CreatedTime: "2014-06-14 17:04:00"
	// ModifiedTime: "2014-06-14 17:05:53"
	// assigned_user_id: "18"
	// z_smr_crmno: "SMR0000002"
	// z_smr_firstname: "test_smr1"
	// z_smr_lastname: "test_smr1"
	// z_area: "NORTHEAST LUZON AREA"
	// z_smr_issmrv: "1"
	// record_id: 410
	// record_module: "XSMR"

	@SerializedName("z_smr_crmno")
	private String crm_no;

	@SerializedName("z_smr_firstname")
	private String firstname;

	@SerializedName("z_smr_lastname")
	private String lastname;

	@SerializedName("z_area")
	private String area;

	@SerializedName("z_smr_isactv")
	private String isactv;

	@SerializedName("record_id")
	private long record_id;

	@SerializedName("record_module")
	private String record_module;

	@SerializedName("CreatedTime")
	private String created_time;

	@SerializedName("ModifiedTime")
	private String modified_time;

	@SerializedName("assigned_user_id")
	private String user_id;

	public String getCrmNo() {
		return this.crm_no;
	}

	public void setCrmNo(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getIsActive() {
		return this.isactv;
	}

	public void setIsActive(String isActive) {
		this.isactv = isActive;
	}

	public long getRecordId() {
		return this.record_id;
	}

	public void setRecordId(long recordId) {
		this.record_id = recordId;
	}

	public String getRecordModule() {
		return this.record_module;
	}

	public void setRecordModule(String recordModule) {
		this.record_module = recordModule;
	}

	public String getCreatedTime() {
		return this.created_time;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getModifiedTime() {
		return this.modified_time;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modified_time = modifiedTime;
	}

	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String userId) {
		this.user_id = userId;
	}
}
