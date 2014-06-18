package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class ProductModel {
	@SerializedName("z_prd_crmno")
	private String crm_no;

	@SerializedName("z_prd_prodnum")
	private String prodnum;

	@SerializedName("z_prd_prodbrand")
	private String prodbrand;

	@SerializedName("z_prd_description")
	private String description;

	@SerializedName("z_prd_prodsize")
	private String prodsize;

	@SerializedName("z_prd_bunit")
	private String bunit;

	@SerializedName("z_prd_isactive")
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

	public String getProdNum() {
		return this.prodnum;
	}

	public void setProdNum(String prodNum) {
		this.prodnum = prodNum;
	}

	public String getProdBrand() {
		return this.prodbrand;
	}

	public void setProdBrand(String prodBrand) {
		this.prodbrand = prodBrand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProdSize() {
		return this.prodsize;
	}

	public void setProdSize(String prodSize) {
		this.prodsize = prodSize;
	}

	public String getBusinessUnit() {
		return this.bunit;
	}

	public void setBusinessUnit(String bUnit) {
		this.bunit = bUnit;
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
