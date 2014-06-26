package co.nextix.jardine.database.records;

public class CompetitorRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// competitor_name
	// is_active (checkbox)
	// created_time
	// modified_time
	// user (assigned to / user table)

	private long id;
	private String no;
	private String competitor_name;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	private String crm_no;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public CompetitorRecord(long id, String no, String crmNo,
			String competitorName, int isActive, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.competitor_name = competitorName;
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

	public void setCrm(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getCrm() {
		return this.crm_no;
	}

	public void setCompetitorName(String competitorName) {
		this.competitor_name = competitorName;
	}

	public String getCompetitorName() {
		return this.competitor_name;
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
