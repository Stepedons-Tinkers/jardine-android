package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class SupplierModel {
	// CreatedTime: "2014-06-14 17:15:06"
	// ModifiedTime: "2014-06-14 17:15:16"
	// assigned_user_id: "11"
	// z_sup_crmno: "SUP0000004"
	// z_sup_sname: "Supplier1"
	// z_sup_slandline: "234567"
	// z_sup_sadd: "SupplierA1"
	// z_sup_cline: "sdf"
	// z_sup_cterm: "0.00"
	// z_sup_cperson: "SupplierP1"
	// z_sup_isactive: "1"
	// record_id: 411
	// record_module: "XSupplier"

	@SerializedName("z_sup_crmno")
	private String crm_no;

	@SerializedName("z_sup_sname")
	private String name;

	@SerializedName("z_sup_slandline")
	private String landline;

	@SerializedName("z_sup_sadd")
	private String address;

	@SerializedName("z_sup_cline")
	private String credit_line;

	@SerializedName("z_sup_cterm")
	private String credit_term;

	@SerializedName("z_sup_cperson")
	private String contact_person;

	@SerializedName("z_sup_isactive")
	private String isactive;

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditLine() {
		return this.credit_line;
	}

	public void setCreditLine(String creditLine) {
		this.credit_line = creditLine;
	}

	public String getCreditTerm() {
		return this.credit_term;
	}

	public void setCreditTerm(String creditTerm) {
		this.credit_term = creditTerm;
	}

	public String getContactPerson() {
		return this.contact_person;
	}

	public void setContactPerson(String contactPerson) {
		this.contact_person = contactPerson;
	}

	public String getIsActive() {
		return this.isactive;
	}

	public void setIsActive(String isActive) {
		this.isactive = isActive;
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
