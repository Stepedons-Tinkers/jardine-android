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
	private String crm_no;
	// private long customer; // Customer table
	private String date;
	private long status; // workplan entry status table
	private long area;
	private long province; // province table
	private long city; // citytown table
	private long activity_type; // activity type table
	private String remarks;
	private long workplan; // workplan table
	private int activity_quantity;
	private long business_unit; // business unit table
	private String created_time;
	private String modified_time;
	private long created_by; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public WorkplanEntryRecord() {
	}

	public WorkplanEntryRecord(long id, String no, String crmNo, String date,
			long status, long area, long province, long city,
			long activityType, String remarks, long workplan,
			int activityQuantity, long businessUnit, String createdTime,
			String modifiedTime, long createdBy) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		// this.customer = customer;
		this.date = date;
		this.status = status;
		this.area = area;
		this.province = province;
		this.city = city;
		this.activity_type = activityType;
		this.remarks = remarks;
		this.workplan = workplan;
		this.activity_quantity = activityQuantity;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.created_by = createdBy;
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

	public void setCity(long city) {
		this.city = city;
	}

	public long getCity() {
		return this.city;
	}

	public void setActivityType(long activityType) {
		this.activity_type = activityType;
	}

	public long getActivityType() {
		return this.activity_type;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setWorkplan(long workplan) {
		this.workplan = workplan;
	}

	public long getWorkplan() {
		return this.workplan;
	}

	public void setActivityQuantity(int activityQuantity) {
		this.activity_quantity = activityQuantity;
	}

	public int getActivityQuantity() {
		return this.activity_quantity;
	}

	public void setBusinessUnit(long businessUnit) {
		this.business_unit = businessUnit;
	}

	public long getBusinessUnit() {
		return this.business_unit;
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

	public void setCreatedBy(long createdBy) {
		this.created_by = createdBy;
	}

	public long getCreatedBy() {
		return this.created_by;
	}

	@Override
	public String toString() {
		return this.crm_no;
	}
}
