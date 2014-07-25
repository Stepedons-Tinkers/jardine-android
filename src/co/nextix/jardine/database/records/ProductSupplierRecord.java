package co.nextix.jardine.database.records;

public class ProductSupplierRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	private long id;
	private String no;
	private String crm_no;
	private String product_brand;
	private long supplier; // Customer table
	private String others_remarks;
	private long activity; // Activity table
	private long created_by; // User Table
	private String created_time;
	private String modified_time;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ProductSupplierRecord(long id, String no, String crmNo,
			String productBrand, long supplier, String othersRemarks,
			long activity, long createdBy, String createdTime,
			String modifiedTime) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.product_brand = productBrand;
		this.supplier = supplier;
		this.others_remarks = othersRemarks;
		this.activity = activity;
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

	public void setProductBrand(String productBrand) {
		this.product_brand = productBrand;
	}

	public String getProductBrand() {
		return product_brand;
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
	
	public void setSupplier(long supplier) {
		this.supplier = supplier;
	}
	
	public long getSupplier() {
		return supplier;
	}
	
	public void setOthersRemarks(String othersRemarks) {
		this.others_remarks = othersRemarks;
	}
	
	public String getOthersRemarks() {
		return others_remarks;
	}
	
	public void setActivity(long activity) {
		this.activity = activity;
	}
	
	public long getActivity() {
		return activity;
	}

	@Override
	public String toString() {
		return this.product_brand;
	}
}
