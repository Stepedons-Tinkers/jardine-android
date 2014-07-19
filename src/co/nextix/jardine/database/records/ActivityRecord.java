package co.nextix.jardine.database.records;

public class ActivityRecord {

	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	private long id;
	private String no;
	private String crm_no;
	private String smr_user_id;
	private long activity_type; // activity type table
	private String check_in;
	private String check_out;
	private double longitude;
	private double latitude;
	private long created_by; // User Table
	private String created_time;
	private String modified_time;

	// Travel or waiting
	private String reason_remarks;

	// with CoSMRSs
	private String work_with;

	// Admin work details
	private String admin_work_details;

	// Activity details and notes
	private long customer; // customer table
	private long area; // area table
	private long province; // province table
	private long city; // city table
	private long workplan_entry; // workplan entry table
	private String objective_of_activity;
	private int first_time_visit;
	private int planned_visit;
	private String notes;
	private String highlights;
	private String next_steps;
	private String follow_up_commitment_date;

	// customer contact person
	private long contact_person; // customer contact table

	// jdi product stock check
	private long jdi_product_stock_check; // jdi product stock check table

	// product supplier
	private long product_supplier; // product supplier table

	// jdi merchandising check
	private long jdi_merchandising_check; // jdi merchandising check table

	// jdi competitor product stock check
	private long jdi_competitor_product_stock_check; // jdi competitor product
														// stock check table

	// marketing intel
	private long marketing_intel; // marketing intel table

	// project visit details
	private String project_visit_details; // project visit details table

	// project requirements
	private String project_requirements; // project requirements table

	// trainings
	private long trainings;

	// identify product focus
	private String identify_product_focus;

	// full brand activation
	private String full_brand_activation;

	// activity photos and attachments
	private String activity_photos_and_attachments;

	// private String start_time;
	// private String end_time;
	// private String objectives;
	// private long business_unit;
	// private long source;
	// private long smr; // smr table
	// private String issues_identified;
	// private String feedback_from_customer;
	// private String ongoing_campaigns;
	// private String last_delivery;
	// private String promo_stubs_details;
	// private String project_name;
	// private String project_category;
	// private String project_stage;
	// private String date;
	// private String time;
	// private String venue;
	// private String no_of_attendees;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ActivityRecord() {
	}

	public ActivityRecord(long id, String no, String crmNo, String smrUserId,
			long activityType, String checkIn, String checkOut,
			double longitude, double latitude, long createdBy,
			String createdTime, String modifiedTime, String reasonRemarks,
			String workWith, String adminWorkDetails, long customer, long area,
			long province, long city, long workplanEntry,
			String objectiveOfActivity, int firstTimeVisit, int plannedVisit,
			String notes, String highlights, String nextSteps,
			String followUpCommitmentDate, long contactPerson,
			long jdiProductStockCheck, long productSupplier,
			long jdiMerchandising, long jdiCompetitorProductStockCheck,
			long marketingIntel, String projectVisitDetails,
			String projectRequirements, long trainings,
			String identifyProductFocus, String fullBrandActivation,
			String activityPhotosAttachment) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.smr_user_id = smrUserId;
		this.activity_type = activityType;
		this.check_in = checkIn;
		this.check_out = checkOut;
		this.longitude = longitude;
		this.latitude = latitude;
		this.created_by = createdBy;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.reason_remarks = reasonRemarks;
		this.work_with = workWith;
		this.admin_work_details = adminWorkDetails;
		this.customer = customer;
		this.area = area;
		this.province = province;
		this.city = city;
		this.workplan_entry = workplanEntry;
		this.objective_of_activity = objectiveOfActivity;
		this.first_time_visit = firstTimeVisit;
		this.planned_visit = plannedVisit;
		this.notes = notes;
		this.highlights = highlights;
		this.next_steps = nextSteps;
		this.follow_up_commitment_date = followUpCommitmentDate;
		this.contact_person = contactPerson;
		this.jdi_product_stock_check = jdiProductStockCheck;
		this.product_supplier = productSupplier;
		this.jdi_merchandising_check = jdiMerchandising;
		this.jdi_competitor_product_stock_check = jdiCompetitorProductStockCheck;
		this.marketing_intel = marketingIntel;
		this.project_visit_details = projectVisitDetails;
		this.project_requirements = projectRequirements;
		this.trainings = trainings;
		this.identify_product_focus = identifyProductFocus;
		this.full_brand_activation = fullBrandActivation;
		this.activity_photos_and_attachments = activityPhotosAttachment;
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
	
	public void setSmrUserId(String smrUserId) {
		this.smr_user_id = smrUserId;
	}

	public String getSmrUserId() {
		return this.smr_user_id;
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

	public void setCreatedBy(long createdBy) {
		this.created_by = createdBy;
	}

	public long getCreatedBy() {
		return this.created_by;
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
	
	public void setWorkWith(String workWith) {
		this.work_with = workWith;
	}

	public String getWorkWith() {
		return this.work_with;
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
	
	public void setObjectiveOfActivity(String objectiveOfActivity) {
		this.objective_of_activity = objectiveOfActivity;
	}

	public String getObjectiveOfActivity() {
		return this.objective_of_activity;
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

	public void setContactPerson(long contactPerson) {
		this.contact_person = contactPerson;
	}

	public long getContactPerson() {
		return this.contact_person;
	}

	public void setJdiProductStockCheck(long jdiProductStockCheck) {
		this.jdi_product_stock_check = jdiProductStockCheck;
	}

	public long getJdiProductStockCheck() {
		return this.jdi_product_stock_check;
	}

	public void setProductSupplier(long productSupplier) {
		this.product_supplier = productSupplier;
	}

	public long getProductSupplier() {
		return this.product_supplier;
	}
	
	public void setJdiMerchandisingCheck(long jdiMerchandisingCheck) {
		this.jdi_merchandising_check = jdiMerchandisingCheck;
	}

	public long getJdiMerchandisingCheck() {
		return this.jdi_merchandising_check;
	}
	
	public void setBusinessUnit(long businessUnit) {
		this.business_unit = businessUnit;
	}

	public long getBusinessUnit() {
		return this.business_unit;
	}

	public void setSource(long source) {
		this.source = source;
	}

	public long getSource() {
		return this.source;
	}

}
