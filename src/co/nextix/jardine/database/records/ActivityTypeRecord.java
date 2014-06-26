package co.nextix.jardine.database.records;

public class ActivityTypeRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// activity_type_categorization ( activity type category table / picklist)
	// is_active (checkbox)
	// user(assigned_to / user table)

	private long id;
	private String no;
//	private long activity_type;
	private long activity_type_categorization;
	private int is_active;
	private long user; // User Table
	
	private String crm_no;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ActivityTypeRecord(long id, String no, String crmNo,
			long activityTypeCategorization, int isActive, long user) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
//		this.activity_type = activityType;
		this.activity_type_categorization = activityTypeCategorization;
		this.is_active = isActive;
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
	
	public void setCrm(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getCrm() {
		return this.crm_no;
	}

	public void setActivityTypeCategorization(long activityTypeCategorization) {
		this.activity_type_categorization = activityTypeCategorization;
	}

	public long getActivityTypeCategorization() {
		return this.activity_type_categorization;
	}

	public void setIsActive(int isActive) {
		this.is_active = isActive;
	}

	public int getIsActive() {
		return this.is_active;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getUser() {
		return this.user;
	}
}
