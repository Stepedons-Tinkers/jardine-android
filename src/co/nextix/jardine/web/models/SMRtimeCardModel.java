package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class SMRtimeCardModel {
	// CreatedTime: "2014-06-18 21:59:47"
	// ModifiedTime: "2014-06-18 21:59:47"
	// assigned_user_id: "1"
	// z_stc_crmno: "STC0000002"
	// z_stc_date: "2014-06-17"
	// z_stc_time: "09:00:00"
	// z_stc_picklist: "Check-in"
	// record_id: 430
	// record_module: "XSMRTimeCard"

	@SerializedName("z_stc_crmno")
	private String crm_no;

	@SerializedName("z_stc_date")
	private String date;

	@SerializedName("z_stc_time")
	private String time;

	@SerializedName("z_stc_picklist")
	private String entry;

	@SerializedName("xsmrtimecardid")
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

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEntry() {
		return this.entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
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
