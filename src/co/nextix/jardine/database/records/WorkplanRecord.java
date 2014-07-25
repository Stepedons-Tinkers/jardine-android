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
	// created_by (assignedTo)

	private long id;
	private String no;
	private String crm_no;
	private String from_date;
	private String to_date;
	// private int status;
	private String created_time;
	private String modified_time;
	private long created_by; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public WorkplanRecord() {
	}

	public WorkplanRecord(long id, String no, String crmNo, String fromDate,
			String toDate, String createdTime, String modifiedTime,
			long createdBy) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.from_date = fromDate;
		this.to_date = toDate;
		// this.status = status;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.created_by = createdBy;
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

	public void setFromDate(String fromDate) {
		this.from_date = fromDate;
	}

	public String getFromDate() {
		return this.from_date;
	}

	public void setToDate(String toDate) {
		this.to_date = toDate;
	}

	public String getToDate() {
		return this.to_date;
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

	public void setCreatedBy(long createdBy) {
		this.created_by = createdBy;
	}

	public long getCreatedBy() {
		return this.created_by;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.crm_no;
	}

	// public void setStatus(int status) {
	// this.status = status;
	// }
	//
	// public int getStatus() {
	// return this.status;
	// }

}
