package co.nextix.jardine.database.records;

public class JDIproductStockCheckRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// activity (activity table)
	// product_brand (product_brand table)
	// stock_status (stock table / picklist)
	// quantity
	// loaded_on_shelves (checkbox)
	// supplier (supplier table)
	// created_time
	// modified_time
	// created_by(assigned_to / created_by table)

	private long id;
	private String no;
	private String crm_no;
	private long activity; // Activity table
	private long product_brand; // product_brand table
	private long stock_status;
	// private int quantity;
	private int loaded_on_shelves;
	private long supplier; // customer type table
	private String other_remarks;
	private String created_time;
	private String modified_time;
	private long created_by; // created_by Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public JDIproductStockCheckRecord(long id, String no, String crmNo,
			long activity, long productBrand, long stockStatus,
			int loadedOnShelves, long supplier, String otherRemarks,
			String createdTime, String modifiedTime, long createdBy) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.activity = activity;
		this.product_brand = productBrand;
		this.stock_status = stockStatus;
		// this.quantity = quantity;
		this.loaded_on_shelves = loadedOnShelves;
		this.supplier = supplier;
		this.other_remarks = otherRemarks;
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

	public void setStockStatus(long stockStatus) {
		this.stock_status = stockStatus;
	}

	public long getStockStatus() {
		return this.stock_status;
	}

	// public void setQuantity(int quantity) {
	// this.quantity = quantity;
	// }
	//
	// public int getQuantity() {
	// return this.quantity;
	// }

	public void setLoadedOnShelves(int loadedOnShelves) {
		this.loaded_on_shelves = loadedOnShelves;
	}

	public int getLoadedOnShelves() {
		return this.loaded_on_shelves;
	}

	public void setSupplier(long supplier) {
		this.supplier = supplier;
	}

	public long getSupplier() {
		return this.supplier;
	}

	public void setOtherRemarks(String otherRemarks) {
		this.other_remarks = otherRemarks;
	}

	public String getOtherRemarks() {
		return this.other_remarks;
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
		return this.crm_no;
	}
}
