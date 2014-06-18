package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class CustomerContactModel {

	// CreatedTime: "2014-06-14 15:41:57"
	// ModifiedTime: "2014-06-17 16:00:02"
	// assigned_user_id: "19"
	// z_cuc_crmno: "CUSTC0000003"
	// z_cuc_firstname: "CustomerContact1"
	// z_cuc_lastname: "CustomerContactL1"
	// z_cuc_position: "Owner"
	// z_cuc_mobileno: "23456"
	// z_cuc_birthday: "2014-06-14"
	// z_cuc_email: ""
	// z_cuc_customer: "405"
	// z_cuc_isactive: "0"
	// z_cuc_daysunchanged: "4"
	// record_id: 406
	// record_module: "XCCPerson"

	@SerializedName("z_cuc_crmno")
	private String crm_no;

	@SerializedName("z_cuc_firstname")
	private String firstname;

	@SerializedName("z_cuc_lastname")
	private String lastname;

	@SerializedName("z_cuc_position")
	private String position;

	@SerializedName("z_cuc_mobileno")
	private String mobileno;

	@SerializedName("z_cuc_birthday")
	private String birthday;

	@SerializedName("z_cuc_email")
	private String email;

	@SerializedName("z_cuc_customer")
	private String customer;

	@SerializedName("z_cuc_isactive")
	private String isactive;

	@SerializedName("z_cuc_daysunchanged")
	private String daysunchanged;

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

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobileno() {
		return this.mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getIsActive() {
		return this.isactive;
	}

	public void setIsActive(String isActive) {
		this.isactive = isActive;
	}

	public String getDaysunchanged() {
		return this.daysunchanged;
	}

	public void setDaysunchanged(String daysunchanged) {
		this.daysunchanged = daysunchanged;
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
