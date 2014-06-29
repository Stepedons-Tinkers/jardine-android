package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class ProductModel {
	// "createdtime": "2014-06-14 16:58:51",
	// "modifiedtime": "2014-06-14 16:59:32",
	// "smownerid": "12",
	// "z_prd_bunit": "402",
	// "z_prd_crmno": "PRD0000002",
	// "z_prd_description": "ProductDesc1",
	// "z_prd_isactive": "1",
	// "z_prd_prodbrand": "ProductBrand1",
	// "z_prd_prodnum": "ProductNo1",
	// "z_prd_prodsize": "ProductSize1",
	// "xproductid": "409",
	// "deleted": "0"

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

	@SerializedName("xproductid")
	private String record_id;

	@SerializedName("record_module")
	private String record_module;

	@SerializedName("createdtime")
	private String created_time;

	@SerializedName("modifiedtime")
	private String modified_time;

	@SerializedName("smownerid")
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

	public String getRecordId() {
		return this.record_id;
	}

	public void setRecordId(String recordId) {
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
