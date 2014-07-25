package co.nextix.jardine.database.records;

public class BusinessUnitRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// business_unit_name
	// is_active (checkbox)
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private String business_unit_name;
	private String business_unit_code;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long created_by; // User Table

	private String crm_no;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public BusinessUnitRecord(long id, String no, String crmNo,
			String businessUnitName, String businessUnitCode, int isActive,
			String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.business_unit_name = businessUnitName;
		this.business_unit_code = businessUnitCode;
		this.is_active = isActive;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.created_by = user;
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

	public void setBusinessUnitName(String businessUnitName) {
		this.business_unit_name = businessUnitName;
	}

	public String getBusinessUnitName() {
		return this.business_unit_name;
	}

	public void setBusinessUnitCode(String businessUnitCode) {
		this.business_unit_code = businessUnitCode;
	}

	public String getBusinessUnitCode() {
		return this.business_unit_code;
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

	public void setCreatedBy(long user) {
		this.created_by = user;
	}

	public long getCreatedBy() {
		return this.created_by;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return this.created_time;
	}

	@Override
	public String toString() {
		return this.business_unit_name;
	}

}
