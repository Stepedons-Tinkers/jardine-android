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
	private long region; /**TODO removed*/
	private long area;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public SMRRecord(long id, String no, String firstname, String lastname,
			long region, long area, int isActive, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.firstname = firstname;
		this.lastname = lastname;
		this.region = region;
		this.area = area;
		this.is_active = isActive;
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

	public void setRegion(long region) {
		this.region = region;
	}

	public long getRegion() {
		return this.region;
	}

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

	public void setUser(long user) {
		this.user = user;
	}

	public long getUser() {
		return this.user;
	}
}
