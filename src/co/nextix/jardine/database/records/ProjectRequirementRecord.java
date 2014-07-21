package co.nextix.jardine.database.records;

public class ProjectRequirementRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// project_requirement_type (project requirement type table / picklist)
	// date_needed
	// square_meters
	// products_used
	// other_details
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private String crm_no;
	private long activity;
	private long project_requirement_type;
	private String date_needed;
	private String square_meters;
	// private String products_used;
	private String products_brand;
	private String other_details;
	private String created_time;
	private String modified_time;
	private long created_by; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ProjectRequirementRecord(long id, String no, String crmNo,
			long activity, long projectRequirementType, String dateNeeded,
			String squareMeters, String productsBrand, String otherDetails,
			String createdTime, String modifiedTime, long created_by) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.activity = activity;
		this.project_requirement_type = projectRequirementType;
		this.date_needed = dateNeeded;
		this.square_meters = squareMeters;
		// this.products_used = productsUsed;
		this.products_brand = productsBrand;
		this.other_details = otherDetails;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.created_by = created_by;
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

	public void setActivity(long activity) {
		this.activity = activity;
	}

	public long getActivity() {
		return this.activity;
	}

	public void setProjectRequirementType(long projectRequirementType) {
		this.project_requirement_type = projectRequirementType;
	}

	public long getProjectRequirementType() {
		return this.project_requirement_type;
	}

	public void setDateNeeded(String dateNeeded) {
		this.date_needed = dateNeeded;
	}

	public String getDateNeeded() {
		return this.date_needed;
	}

	public void setSquareMeters(String squareMeters) {
		this.square_meters = squareMeters;
	}

	public String getSquareMeters() {
		return this.square_meters;
	}

	// public void setProductsUsed(String productsUsed) {
	// this.products_used = productsUsed;
	// }
	//
	// public String getProductsUsed() {
	// return this.products_used;
	// }

	public void setProductsBrand(String products_brand) {
		this.products_brand = products_brand;
	}

	public String getProductsBrand() {
		return products_brand;
	}

	public void setOtherDetails(String otherDetails) {
		this.other_details = otherDetails;
	}

	public String getOtherDetails() {
		return this.other_details;
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

	public void setCreatedBy(long created_by) {
		this.created_by = created_by;
	}

	public long getCreatedBy() {
		return this.created_by;
	}

}
