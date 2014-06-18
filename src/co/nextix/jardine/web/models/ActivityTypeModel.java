package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class ActivityTypeModel {
	// CreatedTime: "2014-06-05 23:19:15"
	// ModifiedTime: "2014-06-05 23:19:15"
	// assigned_user_id: "7"
	// z_act_crmno: "ATY000003"
	// z_act_acttypcat: "Visit"
	// z_act_active: "1"
	// z_act_activitytype: "Retail Visits (Traditional Hardware)"
	// record_id: 351
	// record_module: "XActivityType"

	@SerializedName("z_act_crmno")
	private String crm_no;

	@SerializedName("z_act_acttypcat")
	private String activitytype_category;

	@SerializedName("z_act_active")
	private String isactive;

	@SerializedName("z_act_activitytype")
	private String activitytype;

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

	public String getActivitytypeCategory() {
		return this.activitytype_category;
	}

	public void setActivitytypeCategory(String activitytypeCategory) {
		this.activitytype_category = activitytypeCategory;
	}

	public String getActivitytype() {
		return this.activitytype;
	}

	public void setActivitytype(String activitytype) {
		this.activitytype = activitytype;
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
