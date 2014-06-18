package co.nextix.jardine.database.records;

public class WorkplanEntryRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// id
	// no (crm no)
	// customer (customer table)
	// date - (yyyy-mm-dd)
	// status (w/ picklist ) table
	// province (w/ picklist) table
	// cityOrTown (w/ picklist) table
	// remarks (others remarks)
	// activity type (activity table)
	// workplan (workplan table)
	// createdTime (yyyy-mm-dd hh:mm:ss)
	// modifiedTime (yyyy-mm-dd hh:mm:ss)
	// user (assignedTo) user table

	private long id;
	private String no;
	private long customer; // Customer table
	private String date;
	private long status; // workplan entry status table
	private long area;
	private long province; // province table
	private long city_town; // citytown table
	private String remarks;
	private long activity_type; // activity type table
	private long workplan; // workplan table
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public WorkplanEntryRecord(long id, String no, long customer, String date,
			long status,long area, long province, long cityTown, String remarks,
			long activityType, long workplan, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.customer = customer;
		this.date = date;
		this.status = status;
		this.area = area;
		this.province = province;
		this.city_town = cityTown;
		this.remarks = remarks;
		this.activity_type = activityType;
		this.workplan = workplan;
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

	public void setCustomer(long customer) {
		this.customer = customer;
	}

	public long getCustomer() {
		return this.customer;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getStatus() {
		return this.status;
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

	public void setCityTown(long cityTown) {
		this.city_town = cityTown;
	}

	public long getCityTown() {
		return this.city_town;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setActivityType(long activityType) {
		this.activity_type = activityType;
	}

	public long getActivityType() {
		return this.activity_type;
	}

	public void setWorkplan(long workplan) {
		this.workplan = workplan;
	}

	public long getWorkplan() {
		return this.workplan;
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
