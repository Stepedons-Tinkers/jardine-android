package co.nextix.jardine.database.records;

public class CompetitorProductStockCheckRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// activity (activity table)
	// competitor_product (competitor product table)
	// stock_status (stock table / picklist)
	// loaded_on_shelves (checkbox)
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private String crm_no;
	private long activity;
	private long competitor_product; // product brand // competitorproduct table
	private long stock_status;
	private int loaded_on_shelves;
	private String created_time;
	private String modified_time;
	private long created_by; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public CompetitorProductStockCheckRecord(long id, String no, String crmNo,
			long activity, long competitorProduct, long stockStatus,
			int loadedOnShelves, String createdTime, String modifiedTime,
			long created_by) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.activity = activity;
		this.competitor_product = competitorProduct;
		this.stock_status = stockStatus;
		this.loaded_on_shelves = loadedOnShelves;
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

	public void setCompetitorProduct(long competitorProduct) {
		this.competitor_product = competitorProduct;
	}

	public long getCompetitorProduct() {
		return this.competitor_product;
	}

	public void setStockStatus(long stockStatus) {
		this.stock_status = stockStatus;
	}

	public long getStockStatus() {
		return this.stock_status;
	}

	public void setLoadedOnShelves(int loadedOnShelves) {
		this.loaded_on_shelves = loadedOnShelves;
	}

	public int getLoadedOnShelves() {
		return this.loaded_on_shelves;
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

}
