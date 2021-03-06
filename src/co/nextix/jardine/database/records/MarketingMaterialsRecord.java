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
	private String crm_no;
	private String description;
	private String last_update;
	private String tags;
	private long business_unit;
	private int is_new;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long created_by; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public MarketingMaterialsRecord() {
	}

	public MarketingMaterialsRecord(long id, String no, String crmNo,
			String description, String lastUdpate, String tags,
			long businessUnit, int isNew, int isActive, String createdTime,
			String modifiedTime, long created_by) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.description = description;
		this.last_update = lastUdpate;
		this.tags = tags;
		this.business_unit = businessUnit;
		this.is_new = isNew;
		this.is_active = isActive;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.created_by = created_by;
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

	public void setBusinessUnit(long businessUnit) {
		this.business_unit = businessUnit;
	}

	public long getBusinessUnit() {
		return business_unit;
	}

	public void setIsNew(int isNew) {
		this.is_new = isNew;
	}

	public int getIsNew() {
		return is_new;
	}

	public void setIsActive(int isActive) {
		this.is_active = isActive;
	}

	public int getIsActive() {
		return is_active;
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

	public void setCreatedBy(long created_by) {
		this.created_by = created_by;
	}

	public long getCreatedBy() {
		return this.created_by;
	}
	
	@Override
	public String toString() {
		return this.crm_no;
	}
}
