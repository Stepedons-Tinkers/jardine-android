package co.nextix.jardine.database.records;

public class EventProtocolRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no (crm_no)
	// description
	// last_update
	// tags
	// event_type (event type table / picklist)
	// is_active
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private String description;
	private String last_update;
	private String tags;
	private long event_type;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public EventProtocolRecord(long id, String no, String description,
			String lastUpdate, String tags, long eventType, int isActive,
			String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.description = description;
		this.last_update = lastUpdate;
		this.tags = tags;
		this.event_type = eventType;
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

	public void setEventType(long eventType) {
		this.event_type = eventType;
	}

	public long getEventType() {
		return this.event_type;
	}

	public void setIsActive(int isActive) {
		this.is_active = isActive;
	}

	public int getIsActive() {
		return this.is_active;
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
