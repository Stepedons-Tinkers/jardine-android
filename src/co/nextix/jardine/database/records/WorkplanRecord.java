package co.nextix.jardine.database.records;

public class WorkplanRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// id
	// no (crm no)
	// startDate (from) - (yyyy-mm-dd)
	// endDate (to)
	// status
	// createdTime
	// modifiedTime
	// user (assignedTo)

	private long id;
	private String no;
	private String start_date;
	private String end_date;
	private int status;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public WorkplanRecord(long id, String no, String startDate, String endDate,
			int status, String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.start_date = startDate;
		this.end_date = endDate;
		this.status = status;
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

	public void setStartDate(String startDate) {
		this.start_date = startDate;
	}

	public String getStartDate() {
		return this.start_date;
	}

	public void setEndDate(String endDate) {
		this.end_date = endDate;
	}

	public String getEndDate() {
		return this.end_date;
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

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return this.status;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return this.created_time;
	}
}
