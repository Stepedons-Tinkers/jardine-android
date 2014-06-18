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
	private long project_requirement_type;
	private String date_needed;
	private String square_meters;
	private String products_used;
	private String other_details;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ProjectRequirementRecord(long id, String no,
			long projectRequirementType, String dateNeeded, String squareMeters,
			String productsUsed, String otherDetails, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.project_requirement_type = projectRequirementType;
		this.date_needed = dateNeeded;
		this.square_meters = squareMeters;
		this.products_used = productsUsed;
		this.other_details = otherDetails;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.user = user;
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

	public void setProductsUsed(String productsUsed) {
		this.products_used = productsUsed;
	}

	public String getProductsUsed() {
		return this.products_used;
	}

	public void setOtherDetails(String otherDetails) {
		this.other_details = otherDetails;
	}

	public String getOtherDetails() {
		return this.other_details;
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

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return this.created_time;
	}
}
