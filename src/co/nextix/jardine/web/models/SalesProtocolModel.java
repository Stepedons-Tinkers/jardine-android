package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class SalesProtocolModel {
	// "createdtime": "2014-06-23 14:27:30",
	// "modifiedtime": "2014-07-25 23:20:55",
	// "smownerid": "12",
	// "z_sp_businessunit": "402",
	// "z_sp_crmno": "SP00003",
	// "z_sp_description": "Sales Force Automation (SFA) Project Update",
	// "z_sp_isactive": "0",
	// "z_sp_lastupdate": "2014-06-23",
	// "z_sp_protocoltype": "Work with Protocol (Dealer Salesman)",
	// "z_sp_tags": "Sales Force Automation (SFA) Project Update",
	// "xsalesprotocolsid": "469",
	// "deleted": "0"

	@SerializedName("xsalesprotocolsid")
	private String record_id;

	@SerializedName("z_sp_crmno")
	private String crm_no;

	@SerializedName("z_sp_businessunit")
	private String businessunit;

	@SerializedName("z_sp_description")
	private String description;

	@SerializedName("z_sp_isactive")
	private String isactive;

	@SerializedName("z_sp_lastupdate")
	private String lastupdate;

	@SerializedName("z_sp_protocoltype")
	private String protocoltype;

	@SerializedName("z_sp_tags")
	private String tags;

	@SerializedName("createdtime")
	private String created_time;

	@SerializedName("modifiedtime")
	private String modified_time;

	@SerializedName("smownerid")
	private String user_id;

	public void setRecordId(String recordId) {
		this.record_id = recordId;
	}

	public String getRecordId() {
		return record_id;
	}

	public void setCrmNo(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getCrmNo() {
		return crm_no;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessunit = businessUnit;
	}

	public String getBusinessUnit() {
		return businessunit;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags() {
		return tags;
	}

	public void setProtocoltype(String protocoltype) {
		this.protocoltype = protocoltype;
	}

	public String getProtocoltype() {
		return protocoltype;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setUserId(String userId) {
		this.user_id = userId;
	}

	public String getUserId() {
		return user_id;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return created_time;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modified_time = modifiedTime;
	}

	public String getModifiedTime() {
		return modified_time;
	}
}
