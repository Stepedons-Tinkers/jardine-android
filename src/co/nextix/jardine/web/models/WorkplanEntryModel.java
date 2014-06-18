package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class WorkplanEntryModel {
	// CreatedTime: "2014-06-17 23:09:57"
	// ModifiedTime: "2014-06-17 23:09:57"
	// assigned_user_id: "18"
	// z_wpe_crmno: "WPE000002"
	// z_wpe_date: "2014-06-18"
	// z_wpe_status: "Pending For Approval"
	// z_province: "MANDALUYONG"
	// z_city: "MANDALUYONG CITY"
	// z_wpe_othersremarks: ""
	// z_wpe_activitytype: "352"
	// z_wpe_workplan: "422"
	// z_area: "CENTRAL NCR AREA"
	// z_wpe_activityquantity: "2"
	// record_id: 425
	// record_module: "XWorkplanEntry"

	@SerializedName("z_wpe_crmno")
	private String crm_no;

	@SerializedName("z_wpe_date")
	private String date;

	@SerializedName("z_wpe_status")
	private String status;

	@SerializedName("z_province")
	private String province;

	@SerializedName("z_city")
	private String city;

	@SerializedName("z_wpe_othersremarks")
	private String othersremarks;

	@SerializedName("z_wpe_activitytype")
	private String activitytype;

	@SerializedName("z_wpe_workplan")
	private String workplan;

	@SerializedName("z_area")
	private String area;

	@SerializedName("z_wpe_activityquantity")
	private String activityquantity;

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

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOthersRemarks() {
		return this.othersremarks;
	}

	public void setOthersRemarks(String othersRemarks) {
		this.othersremarks = othersRemarks;
	}

	public String getActivityType() {
		return this.activitytype;
	}

	public void setActivityType(String activityType) {
		this.activitytype = activityType;
	}

	public String getWorkplan() {
		return this.workplan;
	}

	public void setWorkplan(String workplan) {
		this.workplan = workplan;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getActivityQuantity() {
		return this.activityquantity;
	}

	public void setActivityQuantity(String activityQuantity) {
		this.activityquantity = activityQuantity;
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
