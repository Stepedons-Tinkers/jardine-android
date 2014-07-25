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
	private String crm_no;
	private long activity; // activity table
	// private long competitor; // competitor table
	private long competitor_product; // brand // competitor product table
	private String details;
	// private String remarks;
	private String created_time;
	private String modified_time;
	private long created_by; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public MarketingIntelRecord(long id, String no, String crmNo,
			long activity, long competitor_product, String details,
			String createdTime, String modifiedTime, long created_by) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.activity = activity;
		// this.competitor = competitor;
		this.competitor_product = competitor_product;
		this.details = details;
		// this.remarks = remarks;
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

	public void setActivity(long activity) {
		this.activity = activity;
	}

	public long getActivity() {
		return this.activity;
	}

	// public void setCompetitor(long competitor) {
	// this.competitor = competitor;
	// }
	//
	// public long getCompetitor() {
	// return this.competitor;
	// }

	public void setCompetitorProduct(long competitor_product) {
		this.competitor_product = competitor_product;
	}

	public long getCompetitorProduct() {
		return competitor_product;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return this.details;
	}

	// public void setRemarks(String remarks) {
	// this.remarks = remarks;
	// }
	//
	// public String getRemarks() {
	// return this.remarks;
	// }

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

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return this.created_time;
	}
	
	@Override
	public String toString() {
		return this.details;
	}
}
