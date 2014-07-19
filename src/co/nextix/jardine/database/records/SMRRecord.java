package co.nextix.jardine.database.records;

public class SMRRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no (crm_no)
	// firstname
	// lastname
	// region (region table / picklist)
	// area (area table / picklist)
	// is_active (checkbox)
	// created_time
	// modified_time
	// user (assigned_to)

	private long id;
	private String no;
	private String firstname;
	private String lastname;
	// private long region; removed
	private long area; // area table
	private int is_active;
	private String created_time;
	private String modified_time;
	private long created_by; // User Table
	private long business_unit; // business unit table

	private String crm_no;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public SMRRecord(long id, String no, String crmNo, String firstname,
			String lastname, long area, int isActive, String createdTime,
			String modifiedTime, long created_by, long business_unit) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.firstname = firstname;
		this.lastname = lastname;
		// this.region = region;
		this.area = area;
		this.is_active = isActive;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.created_by = created_by;
		this.business_unit = business_unit;
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

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return this.lastname;
	}

	// public void setRegion(long region) {
	// this.region = region;
	// }
	//
	// public long getRegion() {
	// return this.region;
	// }

	public void setArea(long area) {
		this.area = area;
	}

	public long getArea() {
		return this.area;
	}

	public void setIsActive(int isActive) {
		this.is_active = isActive;
	}

	public int getIsActive() {
		return this.is_active;
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

	public void setCreatedBy(long user) {
		this.created_by = user;
	}

	public long getCreatedBy() {
		return this.created_by;
	}

	public long getBusinessUnit() {
		return business_unit;
	}

	public void setBusinessUnit(long business_unit) {
		this.business_unit = business_unit;
	}
}
