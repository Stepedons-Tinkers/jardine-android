package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class MarketingMaterialsModel {

	// "createdtime": "2014-06-14 16:51:49",
	// "modifiedtime": "2014-06-25 17:04:35",
	// "smownerid": "12",
	// "z_mm_businessunit": "",
	// "z_mm_crmno": "MKM0000002",
	// "z_mm_desc": "sdfgh",
	// "z_mm_isactv": "1",
	// "z_mm_lastup": "2014-06-14",
	// "z_mm_tags": "sdfgh",
	// "xmarketingmatid": "408",
	// "deleted": "0"

	@SerializedName("z_mm_crmno")
	private String crm_no;

	@SerializedName("z_mm_desc")
	private String description;

	@SerializedName("z_mm_lastup")
	private String last_updat;

	@SerializedName("z_mm_tags")
	private String tags;

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
