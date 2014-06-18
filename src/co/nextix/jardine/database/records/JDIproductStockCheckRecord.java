package co.nextix.jardine.database.records;

public class JDIproductStockCheckRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// activity (activity table)
	// product (product table)
	// stock_status (stock table / picklist)
	// quantity
	// loaded_on_shelves (checkbox)
	// supplier (supplier table)
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private long activity; // Activity table
	private long product; // product table
	private long stock_status;
	private int quantity;
	private int loaded_on_shelves;
	private long supplier; //supplier table
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public JDIproductStockCheckRecord(long id, String no, long activity, long product,
			long stockStatus, int quantity, int loadedOnShelves, long supplier,
			String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.activity = activity;
		this.product = product;
		this.stock_status = stockStatus;
		this.quantity = quantity;
		this.loaded_on_shelves = loadedOnShelves;
		this.supplier = supplier;
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

	public void setActivity(long activity) {
		this.activity = activity;
	}

	public long getActivity() {
		return this.activity;
	}

	public void setProduct(long product) {
		this.product = product;
	}

	public long getProduct() {
		return this.product;
	}

	public void setStockStatus(long stockStatus) {
		this.stock_status = stockStatus;
	}

	public long getStockStatus() {
		return this.stock_status;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

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

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return this.created_time;
	}
}
