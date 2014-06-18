package co.nextix.jardine.database.records;

public class SMRtimeCardRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no (crm_no)
	// date
	// timestamp
	// entry_type (entry type table / picklist)
	// created_time
	// modified_time
	// user (assigned_to)

	private long id;
	private String no;
	private String date;
	private String timestamp;
	private long entry_type;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public SMRtimeCardRecord(long id, String no, String date, String timestamp,
			long entryType, String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.date = date;
		this.timestamp = timestamp;
		this.entry_type = entryType;
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

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setEntryType(long entryType) {
		this.entry_type = entryType;
	}

	public long getEntryType() {
		return this.entry_type;
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
