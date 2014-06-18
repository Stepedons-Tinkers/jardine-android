package co.nextix.jardine.database.records;

public class MarketingMaterialsRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// description
	// last_update (yyyy-mm-dd)
	// tags
	// is_active
	// created_time (yyyy-mm-dd hh:mm:ss)
	// modified_time (yyyy-mm-dd hh:mm:ss)
	// user (assigned_to)

	private long id;
	private String no;
	private String description;
	private String last_update;
	private String tags;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public MarketingMaterialsRecord(long id, String no, String description,
			String lastUdpate, String tags, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.description = description;
		this.last_update = lastUdpate;
		this.tags = tags;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setLastUpdate(String lastUpdate) {
		this.last_update = lastUpdate;
	}

	public String getLastUpdate() {
		return this.last_update;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags() {
		return this.tags;
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
