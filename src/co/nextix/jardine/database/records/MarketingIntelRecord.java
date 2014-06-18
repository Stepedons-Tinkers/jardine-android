package co.nextix.jardine.database.records;

public class MarketingIntelRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// activity (activity table)
	// competitor (competitor table)
	// details
	// remarks
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private long activity; // activity table
	private long competitor; // competitor table
	private String details;
	private String remarks;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public MarketingIntelRecord(long id, String no, long activity,
			long competitor, String details, String remarks,
			String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.activity = activity;
		this.competitor = competitor;
		this.details = details;
		this.remarks = remarks;
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

	public void setActivity(long activity) {
		this.activity = activity;
	}

	public long getActivity() {
		return this.activity;
	}

	public void setCompetitor(long competitor) {
		this.competitor = competitor;
	}

	public long getCompetitor() {
		return this.competitor;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return this.details;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
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
