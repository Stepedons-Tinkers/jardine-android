package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class ProjectRequirementModel {

	// CreatedTime: "2014-06-18 00:06:34"
	// ModifiedTime: "2014-06-18 00:06:34"
	// assigned_user_id: "18"
	// z_pr_crmno: "PRQ0000002"
	// z_pr_activity: "423"
	// z_pr_prtype: "Waterproofing"
	// z_pr_dateneeded: "2014-06-18"
	// z_pr_squaremtrs: "2"
	// z_pr_prodused: "d"
	// z_pr_otherdet: "d"
	// record_id: 426
	// record_module: "XProjectRequirement"

	@SerializedName("z_pr_crmno")
	private String crm_no;

	@SerializedName("z_pr_activity")
	private String activity;

	@SerializedName("z_pr_prtype")
	private String prjct_requirmnt_type;

	@SerializedName("z_pr_dateneeded")
	private String date_needed;

	@SerializedName("z_pr_squaremtrs")
	private String squaremeters;

	@SerializedName("z_pr_prodused")
	private String products_used;

	@SerializedName("z_pr_otherdet")
	private String other_details;

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

	public String getProjectReqType() {
		return this.prjct_requirmnt_type;
	}

	public void setProjectReqType(String projectReqType) {
		this.prjct_requirmnt_type = projectReqType;
	}

	public String getDateNeeded() {
		return this.date_needed;
	}

	public void setDateNeeded(String dateNeeded) {
		this.date_needed = dateNeeded;
	}

	public String getSquaremeters() {
		return this.squaremeters;
	}

	public void setSquaremeters(String squaremeters) {
		this.squaremeters = squaremeters;
	}

	public String getProductsUsed() {
		return this.products_used;
	}

	public void setProductsUsed(String productsUsed) {
		this.products_used = productsUsed;
	}

	public String getOtherDetails() {
		return this.other_details;
	}

	public void setOtherDetails(String otherDetails) {
		this.other_details = otherDetails;
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
