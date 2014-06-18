package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class MarketingIntelModel {

	// CreatedTime: "2014-06-14 17:42:40"
	// ModifiedTime: "2014-06-14 18:13:56"
	// assigned_user_id: "18"
	// z_min_crmno: "MIN0000002"
	// z_min_activity: ""
	// z_min_competitor: "403"
	// z_min_details: "test"
	// record_id: 412
	// record_module: "XMarketingIntel"

	@SerializedName("z_min_crmno")
	private String crm_no;

	@SerializedName("z_min_activity")
	private String activity;

	@SerializedName("z_min_competitor")
	private String competitor;

	@SerializedName("z_min_details")
	private String details;

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

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getCompetitor() {
		return this.competitor;
	}

	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
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
