package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class ActivityModel {

	// CreatedTime: "2014-06-17 16:13:55"
	// ModifiedTime: "2014-06-17 16:13:55"
	// assigned_user_id: "18"
	// z_ac_crmno: "ACT000002"
	// z_ac_workplan: "422"
	// z_ac_starttime: "2014-06-17 16:08:48"
	// z_ac_endtime: "2014-06-17 16:08:48"
	// z_ac_latitude: "0.000000000000"
	// z_ac_longitude: "0.000000000000"
	// z_ac_objective: "sa"
	// z_ac_notes: "sa"
	// z_ac_highlights: ""
	// z_ac_nextsteps: ""
	// z_ac_followupcomdate: "2014-06-17"
	// z_ac_activitytype: "351"
	// z_ac_workplanentry: ""
	// z_ac_customer: ""
	// z_ac_firsttimevisit: "1"
	// z_ac_plannedvisit: "1"
	// z_ac_smr: ""
	// z_ac_issuesidentified: ""
	// z_ac_feedbackfromcu: ""
	// z_ac_ongoingcampaigns: ""
	// z_ac_lastdelivery: ""
	// z_ac_promostubsdetails: ""
	// z_ac_projectname: ""
	// z_ac_projectstage: "- Select -"
	// z_ac_projectcategory: "- Select -"
	// z_ac_date: "2014-06-17"
	// z_ac_time: ""
	// z_ac_venue: ""
	// z_ac_noofattenees: "0"
	// z_ac_source: "Web"
	// z_area: "NORTHERN NCR AREA"
	// z_province: "BULACAN - 2"
	// z_city: "ANGAT"
	// z_ac_othersacttypermrk: ""
	// z_ac_attendees: ""
	// z_ac_attendancesheet: ""
	// record_id: 423
	// record_module: "XActivity"

	@SerializedName("z_ac_crmno")
	private String crm_no;

	@SerializedName("z_ac_workplan")
	private String workplan;

	@SerializedName("z_ac_starttime")
	private String starttime;

	@SerializedName("z_ac_endtime")
	private String endtime;

	@SerializedName("z_ac_latitude")
	private String latitude;

	@SerializedName("z_ac_longitude")
	private String longitude;

	@SerializedName("z_ac_objective")
	private String objective;

	@SerializedName("z_ac_notes")
	private String notes;

	@SerializedName("z_ac_highlights")
	private String highlights;

	@SerializedName("z_ac_nextsteps")
	private String nextsteps;

	@SerializedName("z_ac_followupcomdate")
	private String followupcomdate;

	@SerializedName("z_ac_activitytype")
	private String activitytype;

	@SerializedName("z_ac_workplanentry")
	private String workplanentry;

	@SerializedName("z_ac_customer")
	private String customer;

	@SerializedName("z_ac_firsttimevisit")
	private String firsttimevisit;

	@SerializedName("z_ac_plannedvisit")
	private String plannedvisit;

	@SerializedName("z_ac_smr")
	private String smr;

	@SerializedName("z_ac_issuesidentified")
	private String issuesidentified;

	@SerializedName("z_ac_feedbackfromcu")
	private String feedbackfromcu;

	@SerializedName("z_ac_ongoingcampaigns")
	private String ongoingcampaigns;

	@SerializedName("z_ac_lastdelivery")
	private String lastdelivery;

	@SerializedName("z_ac_promostubsdetails")
	private String promostubsdetails;

	@SerializedName("z_ac_projectname")
	private String projectname;

	@SerializedName("z_ac_projectstage")
	private String projectstage;

	@SerializedName("z_ac_projectcategory")
	private String projectcategory;

	@SerializedName("z_ac_date")
	private String date;

	@SerializedName("z_ac_time")
	private String time;

	@SerializedName("z_ac_venue")
	private String venue;

	@SerializedName("z_ac_noofattenees")
	private String noofattenees;

	@SerializedName("z_ac_source")
	private String source;

	@SerializedName("z_area")
	private String area;

	@SerializedName("z_province")
	private String province;

	@SerializedName("z_city")
	private String city;

	@SerializedName("z_ac_othersacttypermrk")
	private String othersacttypermrk;

	@SerializedName("z_ac_attendees")
	private String attendees;

	@SerializedName("z_ac_attendancesheet")
	private String attendancesheet;

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

	public String getWorkplan() {
		return this.workplan;
	}

	public void setWorkplan(String workplan) {
		this.workplan = workplan;
	}

	public String getStartTime() {
		return this.starttime;
	}

	public void setStartTime(String startTime) {
		this.starttime = startTime;
	}

	public String getEndTime() {
		return this.endtime;
	}

	public void setEndTime(String endTime) {
		this.endtime = endTime;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getObjective() {
		return this.objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getHighlights() {
		return this.highlights;
	}

	public void setHighlights(String highlights) {
		this.highlights = highlights;
	}

	public String getNextsteps() {
		return this.nextsteps;
	}

	public void setNextsteps(String nextsteps) {
		this.nextsteps = nextsteps;
	}

	public String getFollowupcomdate() {
		return this.followupcomdate;
	}

	public void setFollowupcomdate(String followupcomdate) {
		this.followupcomdate = followupcomdate;
	}

	public String getActivityType() {
		return this.activitytype;
	}

	public void setActivityType(String activityType) {
		this.activitytype = activityType;
	}

	public String getWorkplanEntry() {
		return this.workplanentry;
	}

	public void setWorkplanEntry(String workplanEntry) {
		this.workplanentry = workplanEntry;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getFirstTimeVisit() {
		return this.firsttimevisit;
	}

	public void setFirstTimeVisit(String firsttimevisit) {
		this.firsttimevisit = firsttimevisit;
	}

	public String getPlannedvisit() {
		return this.plannedvisit;
	}

	public void setPlannedvisit(String plannedvisit) {
		this.plannedvisit = plannedvisit;
	}

	public String getSmr() {
		return this.smr;
	}

	public void setSmr(String smr) {
		this.smr = smr;
	}

	public String getIssuesIdentified() {
		return this.issuesidentified;
	}

	public void setIssuesIdentified(String issuesIdentified) {
		this.issuesidentified = issuesIdentified;
	}

	public String getFeedbackFromCu() {
		return this.feedbackfromcu;
	}

	public void setFeedbackFromCu(String feedbackFromCu) {
		this.feedbackfromcu = feedbackFromCu;
	}

	public String getOngoingCampaigns() {
		return this.ongoingcampaigns;
	}

	public void setOngoingCampaigns(String ongoingCampaigns) {
		this.ongoingcampaigns = ongoingCampaigns;
	}

	public String getLastDelivery() {
		return this.lastdelivery;
	}

	public void setLastDelivery(String lastDelivery) {
		this.lastdelivery = lastDelivery;
	}

	public String getPromoStubsDetails() {
		return this.promostubsdetails;
	}

	public void setPromoStubsDetails(String promoStubsDetails) {
		this.promostubsdetails = promoStubsDetails;
	}

	public String getProjectName() {
		return this.projectname;
	}

	public void setProjectName(String projectName) {
		this.projectname = projectName;
	}

	public String getProjectStage() {
		return this.projectstage;
	}

	public void setProjectStage(String projectStage) {
		this.projectstage = projectStage;
	}

	public String getProjectCategory() {
		return this.projectcategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectcategory = projectCategory;
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

	public String getVenue() {
		return this.venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getNoofattenees() {
		return this.noofattenees;
	}

	public void setNoofattenees(String noofattenees) {
		this.noofattenees = noofattenees;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getOthersActTypeRmrk() {
		return this.othersacttypermrk;
	}

	public void setOthersActTypeRmrk(String othersActTypeRmrk) {
		this.othersacttypermrk = othersActTypeRmrk;
	}

	public String getAttendees() {
		return this.attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}

	public String getAttendanceSheet() {
		return this.attendancesheet;
	}

	public void setAttendanceSheet(String attendanceSheet) {
		this.attendancesheet = attendanceSheet;
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
