package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class ActivityTypeModel {
	// "createdtime": "2014-06-25 18:50:25",
	// "modifiedtime": "2014-06-25 18:52:14",
	// "smownerid": "1",
	// "z_act_active": "1",
	// "z_act_activitytype": "On-site/Project Visit",
	// "z_act_acttypcat": "Visit",
	// "z_act_crmno": "ATY000050",
	// "xactivitytypeid": "495",
	// "deleted": "0"

	@SerializedName("z_act_crmno")
	private String crm_no;

	@SerializedName("z_act_acttypcat")
	private String activitytype_category;

	@SerializedName("z_act_active")
	private String isactive;

	@SerializedName("z_act_activitytype")
	private String activitytype;

	@SerializedName("xactivitytypeid")
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

	@Override
	public String toString() {
		return activitytype;
		// return super.toString();
	}

}
