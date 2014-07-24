package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class MarketingMaterialsModel {

	// "createdtime": "2014-07-21 21:41:39",
	// "modifiedtime": "2014-07-21 21:41:39",
	// "smownerid": "1",
	// "z_mm_businessunit": "402",
	// "z_mm_crmno": "MKM0000004",
	// "z_mm_desc": "asd",
	// "z_mm_isactv": "0",
	// "z_mm_lastup": "2014-07-21",
	// "z_mm_new": "1",
	// "z_mm_tags": "sdas",
	// "xmarketingmatid": "524",
	// "deleted": "0"

	@SerializedName("z_mm_crmno")
	private String crm_no;

	@SerializedName("z_mm_desc")
	private String description;

	@SerializedName("z_mm_lastup")
	private String last_updat;

	@SerializedName("z_mm_tags")
	private String tags;
	
	@SerializedName("z_mm_businessunit")
	private String business_unit;
	
	@SerializedName("z_mm_new")
	private String is_new;

	@SerializedName("z_evp_isactv")
	private String isactive;

	@SerializedName("xmarketingmatid")
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastUpdat() {
		return this.last_updat;
	}

	public void setLastUpdat(String lastUpdat) {
		this.last_updat = lastUpdat;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public String getBusinessUnit() {
		return business_unit;
	}
	
	public void setBusinessUnit(String businessUnit) {
		this.business_unit = businessUnit;
	}
	
	public String getIsNew() {
		return is_new;
	}
	
	public void setIsNew(String isNew) {
		this.is_new = isNew;
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
