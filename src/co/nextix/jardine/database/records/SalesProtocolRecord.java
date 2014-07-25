package co.nextix.jardine.database.records;

public class SalesProtocolRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	private long id;
	private String no;
	private String crm_no;
	private long business_unit; // Business Unit table
	private String description;
	private String last_update;
	private String tags;
	private long protocol_type; // sales protocol type table
	private int is_active;
	private long created_by; // User Table
	private String created_time;
	private String modified_time;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public SalesProtocolRecord(long id, String no, String crmNo,
			long businessUnit, String description, String lastUpdate,
			String tags, long protocolType, int isActive, long createdBy,
			String createdTime, String modifiedTime) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.business_unit = businessUnit;
		this.description = description;
		this.last_update = lastUpdate;
		this.tags = tags;
		this.protocol_type = protocolType;
		this.is_active = isActive;
		this.created_by = createdBy;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
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

	public void setBusinessUnit(long businessUnit) {
		this.business_unit = businessUnit;
	}

	public long getBusinessUnit() {
		return business_unit;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setLastUpdate(String lastUpdate) {
		this.last_update = lastUpdate;
	}

	public String getLastUpdate() {
		return last_update;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags() {
		return tags;
	}

	public void setProtocolType(long protocolType) {
		this.protocol_type = protocolType;
	}

	public long getProtocolType() {
		return protocol_type;
	}

	public void setIsActive(int isActive) {
		this.is_active = isActive;
	}

	public int getIsActive() {
		return this.is_active;
	}

	public void setCreatedBy(long createdBy) {
		this.created_by = createdBy;
	}

	public long getCreatedBy() {
		return created_by;
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

	@Override
	public String toString() {
		return this.description;
	}
}
