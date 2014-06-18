package co.nextix.jardine.database.records;

public class ActivityRecord {

	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no (crm_no)
	// workplan (workplan table)
	// start_time
	// end_time
	// latitude
	// longitude
	// objective
	// notes
	// highlights
	// next_steps
	// follow_up_commitment_date
	// activity_type (activity type table)
	// workplan_entry (workplan entry table)
	// customer (customer table)
	// first_time_visit (checkbox)
	// planned_visit (checkbox)
	// created_time
	// modified_time
	// user (assigned_to)
	//
	// *With CoSMRs
	//
	// smr (smr table)
	//
	// *DIY or Supermarket
	//
	// issues_identified
	// feedback_from_customer
	// ongoing_campaigns
	//
	// *Retail visit
	//
	// last_delivery
	// promo_stubs_details
	//
	// *project visit
	//
	// project_name
	// project_category
	// project_stage
	//
	// *all training types
	//
	// project_name
	// project_category
	// project_stage

	private long id;
	private String no;
	private long workplan; // workplan table
	private String start_time;
	private String end_time;
	private double longitude;
	private double latitude;
	private String objectives;
	private String notes;
	private String highlights;
	private String next_steps;
	private String follow_up_commitment_date;
	private long activity_type; // activity type table
	private long workplan_entry; // workplan entry table
	private long customer; // customer table
	private int first_time_visit;
	private int planned_visit;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	private long smr; // smr table

	private String issues_identified;
	private String feedback_from_customer;
	private String ongoing_campaigns;

	private String last_delivery;
	private String promo_stubs_details;

	private String project_name;
	private String project_category;
	private String project_stage;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ActivityRecord(long id, String no, long workplan, String startTime,
			String endTime, double longitude, double latitude,
			String objectives, String notes, String highlights,
			String nextSteps, String followUpCommitmentDate, long activityType,
			long workplanEntry, long customer, int firstTimeVisit,
			int plannedVisit, String createdTime, String modifiedTime,
			long user, long smr, String issuesIdentified,
			String feedBackFromCustomer, String ongoingCampaigns,
			String lastDelivery, String promoStubsDetails, String projectName,
			String projectCategory, String projectStage) {

		this.id = id;
		this.no = no;
		this.workplan = workplan;
		this.start_time = startTime;
		this.end_time = endTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.objectives = objectives;
		this.notes = notes;
		this.highlights = highlights;
		this.next_steps = nextSteps;
		this.follow_up_commitment_date = followUpCommitmentDate;
		this.activity_type = activityType;
		this.workplan_entry = workplanEntry;
		this.customer = customer;
		this.first_time_visit = firstTimeVisit;
		this.planned_visit = plannedVisit;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.user = user;
		this.smr = smr;
		this.issues_identified = issuesIdentified;
		this.feedback_from_customer = feedBackFromCustomer;
		this.ongoing_campaigns = ongoingCampaigns;
		this.last_delivery = lastDelivery;
		this.promo_stubs_details = promoStubsDetails;
		this.project_name = projectName;
		this.project_category = projectCategory;
		this.project_stage = projectStage;
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

	public void setWorkplan(long workplan) {
		this.workplan = workplan;
	}

	public long getWorkplan() {
		return this.workplan;
	}

	public void setStartTime(String startTime) {
		this.start_time = startTime;
	}

	public String getStartTime() {
		return this.start_time;
	}

	public void setEndTime(String endTime) {
		this.end_time = endTime;
	}

	public String getEndTime() {
		return this.end_time;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getObjectives() {
		return this.objectives;
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

	public void setActivityType(long activityType) {
		this.activity_type = activityType;
	}

	public long getActivityType() {
		return this.activity_type;
	}

	public void setWorkplanEntry(long workplanEntry) {
		this.workplan_entry = workplanEntry;
	}

	public long getWorkplanEntry() {
		return this.workplan_entry;
	}

	public void setCustomer(long customer) {
		this.customer = customer;
	}

	public long getCustomer() {
		return this.customer;
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

	public void setUser(long user) {
		this.user = user;
	}

	public long getUser() {
		return this.user;
	}

	public void setSMR(long smr) {
		this.smr = smr;
	}

	public long getSMR() {
		return this.smr;
	}

	public void setIssuesIdentified(String issuesIdentified) {
		this.issues_identified = issuesIdentified;
	}

	public String getIssuesIdentified() {
		return this.issues_identified;
	}

	public void setFeedbackFromCustomer(String feedBackFromCustomer) {
		this.feedback_from_customer = feedBackFromCustomer;
	}

	public String getFeedbackFromCustomer() {
		return this.feedback_from_customer;
	}

	public void setOngoingCampaigns(String ongoingCampaigns) {
		this.ongoing_campaigns = ongoingCampaigns;
	}

	public String getOngoingCampaigns() {
		return this.ongoing_campaigns;
	}

	public void setLastDelivery(String lastDelivery) {
		this.last_delivery = lastDelivery;
	}

	public String getLastDelivery() {
		return this.last_delivery;
	}

	public void setPromoStubsDetails(String promoStubsDetails) {
		this.promo_stubs_details = promoStubsDetails;
	}

	public String getPromoStubsDetails() {
		return this.promo_stubs_details;
	}

	public void setProjectName(String projectName) {
		this.project_name = projectName;
	}

	public String getProjectName() {
		return this.project_name;
	}

	public void setProjectCategory(String projectCategory) {
		this.project_category = projectCategory;
	}

	public String getProjectCategory() {
		return this.project_category;
	}

	public void setProjectStage(String projectStage) {
		this.project_stage = projectStage;
	}

	public String getProjectStage() {
		return this.project_stage;
	}
}
