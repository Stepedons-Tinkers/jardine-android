package co.nextix.jardine.database.records;

public class JDImerchandisingCheckRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// activity (activity table)
	// product_brand (product_brand table)
	// status
	// created_by (created_by table)

	private long id;
	private String no;
	private String crm_no;
	private long activity;
	private long product_brand; // brand from Product Table
	// private int is_active;
	private long status;
	private String created_time;
	private String modified_time;
	private long created_by; // created_by Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public JDImerchandisingCheckRecord() {
	}

	public JDImerchandisingCheckRecord(long id, String no, String crmNo,
			long activity, long productBrand, long status, String createdTime,
			String modifiedTime, long created_by) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.activity = activity;
		this.product_brand = productBrand;
		this.status = status;
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

	public void setProductBrand(long productBrand) {
		this.product_brand = productBrand;
	}

	public long getProductBrand() {
		return this.product_brand;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getStatus() {
		return this.status;
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
