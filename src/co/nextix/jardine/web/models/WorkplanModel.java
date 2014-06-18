package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class WorkplanModel {
	// CreatedTime: "2014-06-15 23:24:17"
	// ModifiedTime: "2014-06-15 23:24:17"
	// assigned_user_id: "18"
	// z_wp_crmno: "WP000002"
	// z_wp_fromdate: "2014-06-15"
	// z_wp_todate: "2014-06-15"
	// record_id: 422
	// record_module: "XWorkplan"

	@SerializedName("z_wp_crmno")
	private String crm_no;

	@SerializedName("z_wp_fromdate")
	private String from_date;

	@SerializedName("z_wp_todate")
	private String to_date;

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

	public String getFromDate() {
		return this.from_date;
	}

	public void setFromDate(String fromDate) {
		this.from_date = fromDate;
	}

	public String getToDate() {
		return this.to_date;
	}

	public void setToDate(String toDate) {
		this.to_date = toDate;
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
