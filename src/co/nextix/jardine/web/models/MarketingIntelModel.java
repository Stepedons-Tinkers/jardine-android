package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class MarketingIntelModel {

	// "createdtime": "2014-06-23 00:40:09",
	// "modifiedtime": "2014-07-21 17:52:13",
	// "smownerid": "18",
	// "z_min_activity": "423",
	// "z_min_competitorprod": "409",
	// "z_min_crmno": "MIN0000003",
	// "z_min_details": "sa",
	// "xmarketingintelid": "438",
	// "deleted": "0"

	@SerializedName("z_min_crmno")
	private String crm_no;

	@SerializedName("z_min_activity")
	private String activity;

	@SerializedName("z_min_competitorprod")
	private String competitor;

	@SerializedName("z_min_details")
	private String details;

	@SerializedName("xmarketingintelid")
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
