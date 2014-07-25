package co.nextix.jardine.database.records;

public class ActivityRecord {

	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	private long id;
	private String no;
	private String crm_no;
	private long activity_type; // activity type table
	private String check_in;
	private String check_out;
	private long business_unit; // business unit table
	private long created_by; // User Table

	private double longitude;
	private double latitude;
	private String created_time;
	private String modified_time;

	// Travel or waiting
	private String reason_remarks;

	// with CoSMRSs
	private long smr; // smr table

	// Admin work details
	private String admin_work_details;

	// Activity details and Notes
	private long customer; // customer table
	private long area; // area table
	private long province; // province table
	private long city; // city table
	private long workplan_entry; // workplan entry table
	private String objective;
	private int first_time_visit;
	private int planned_visit;
	private String notes;
	private String highlights;
	private String next_steps;
	private String follow_up_commitment_date;

	// Project visit
	private String project_name;
	private String project_stage;
	private String project_category;

	// Trainings
	private String venue;
	private int number_of_attendees;

	// Full brand activitation
	private String end_user_activity_types;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ActivityRecord() {
	}

	public ActivityRecord(long id, String no, String crmNo, long activityType,
			String checkIn, String checkOut, long businessUnit, long createdBy,
			double longitude, double latitude, String createdTime,
			String modifiedTime, String reasonsRemarks, long smr,
			String adminDetails, long customer, long area, long province,
			long city, long workplanEntry, String objective,
			int firstTimeVisit, int plannedVisit, String notes,
			String highlights, String nextSteps, String followUpCommitmentDate,
			String projectName, String projectStage, String projectCategory,
			String venue, int numberOfAttendees, String endUserActivityTypes) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.activity_type = activityType;
		this.check_in = checkIn;
		this.check_out = checkOut;
		this.business_unit = businessUnit;
		this.created_by = createdBy;
		this.longitude = longitude;
		this.latitude = latitude;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.reason_remarks = reasonsRemarks;
		this.smr = smr;
		this.admin_work_details = adminDetails;
		this.customer = customer;
		this.area = area;
		this.province = province;
		this.city = city;
		this.workplan_entry = workplanEntry;
		this.objective = objective;
		this.first_time_visit = firstTimeVisit;
		this.planned_visit = plannedVisit;
		this.notes = notes;
		this.highlights = highlights;
		this.next_steps = nextSteps;
		this.follow_up_commitment_date = followUpCommitmentDate;
		this.project_name = projectName;
		this.project_stage = projectStage;
		this.project_category = projectCategory;
		this.venue = venue;
		this.number_of_attendees = numberOfAttendees;
		this.end_user_activity_types = endUserActivityTypes;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public long getId() {
		return this.id;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getNo() {
		return this.no;
	}

	public void setCrm(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getCrm() {
		return this.crm_no;
	}

	public void setActivityType(long activityType) {
		this.activity_type = activityType;
	}

	public long getActivityType() {
		return this.activity_type;
	}

	public void setCheckIn(String checkIn) {
		this.check_in = checkIn;
	}

	public String getCheckIn() {
		return this.check_in;
	}

	public void setCheckOut(String checkOut) {
		this.check_out = checkOut;
	}

	public String getCheckOut() {
		return this.check_out;
	}

	public long getBusinessUnit() {
		return business_unit;
	}

	public void setBusinessUnit(long businessUnit) {
		this.business_unit = businessUnit;
	}

	public void setCreatedBy(long createdBy) {
		this.created_by = createdBy;
	}

	public long getCreatedBy() {
		return this.created_by;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return this.created_time;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modified_time = modifiedTime;
	}

	public String getModifiedTime() {
		return this.modified_time;
	}

	public void setReasonRemarks(String reasonRemarks) {
		this.reason_remarks = reasonRemarks;
	}

	public String getReasonRemarks() {
		return this.reason_remarks;
	}

	public void setSmr(long smr) {
		this.smr = smr;
	}

	public long getSmr() {
		return this.smr;
	}

	public void setAdminWorkDetails(String adminWorkDetails) {
		this.admin_work_details = adminWorkDetails;
	}

	public String getAdminWorkDetails() {
		return this.admin_work_details;
	}

	public void setCustomer(long customer) {
		this.customer = customer;
	}

	public long getCustomer() {
		return this.customer;
	}

	public void setArea(long area) {
		this.area = area;
	}

	public long getArea() {
		return this.area;
	}

	public void setProvince(long province) {
		this.province = province;
	}

	public long getProvince() {
		return this.province;
	}

	public void setCity(long city) {
		this.city = city;
	}

	public long getCity() {
		return this.city;
	}

	public void setWorkplanEntry(long workplanEntry) {
		this.workplan_entry = workplanEntry;
	}

	public long getWorkplanEntry() {
		return this.workplan_entry;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getObjective() {
		return this.objective;
	}

	public void setFirstTimeVisit(int firstTimeVisit) {
		this.first_time_visit = firstTimeVisit;
	}

	public int getFirstTimeVisit() {
		return this.first_time_visit;
	}

	public void setPlannedVisit(int plannedVisit) {
		this.planned_visit = plannedVisit;
	}

	public int getPlannedVisit() {
		return this.planned_visit;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setHighlights(String highlights) {
		this.highlights = highlights;
	}

	public String getHighlights() {
		return this.highlights;
	}

	public void setNextSteps(String nextSteps) {
		this.next_steps = nextSteps;
	}

	public String getNextSteps() {
		return this.next_steps;
	}

	public void setFollowUpCommitmentDate(String followUpCommitmentDate) {
		this.follow_up_commitment_date = followUpCommitmentDate;
	}

	public String getFollowUpCommitmentDate() {
		return this.follow_up_commitment_date;
	}

	public void setProjectName(String projectName) {
		this.project_name = projectName;
	}

	public String getProjectName() {
		return project_name;
	}

	public void setProjectStage(String projectStage) {
		this.project_stage = projectStage;
	}

	public String getProjectStage() {
		return this.project_stage;
	}

	public void setProjectCategory(String projectCategory) {
		this.project_category = projectCategory;
	}

	public String getProjectCategory() {
		return project_category;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getVenue() {
		return venue;
	}

	public void setNumberOfAttendees(int numberOfAttendees) {
		this.number_of_attendees = numberOfAttendees;
	}

	public int getNumberOfAttendees() {
		return number_of_attendees;
	}

	public void setEndUserActivityTypes(String endUserActivityTypes) {
		this.end_user_activity_types = endUserActivityTypes;
	}

	public String getEndUserActivityTypes() {
		return end_user_activity_types;
	}

	@Override
	public String toString() {
		return crm_no;
	}

}
