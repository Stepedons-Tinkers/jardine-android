package co.nextix.jardine.database.records;

public class ProductRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// product_number
	// product_brand
	// product_description
	// product_size
	// business_unit (business unit table)
	// is_active (checkbox)
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private String product_number;
	private String product_brand;
	private String product_description;
	private String product_size;
	private long business_unit; //business unit table
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ProductRecord(long id, String no, String productNumber,
			String productBrand, String productDescription, String productSize,
			long businessUnit, int isActive, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.product_number = productNumber;
		this.product_brand = productBrand;
		this.product_description = productDescription;
		this.product_size = productSize;
		this.business_unit = businessUnit;
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

	public void setProductNumber(String productNumber) {
		this.product_number = productNumber;
	}

	public String getProductNumber() {
		return this.product_number;
	}

	public void setProductBrand(String productBrand) {
		this.product_brand = productBrand;
	}

	public String getProductBrand() {
		return this.product_brand;
	}

	public void setProductDescription(String productDescription) {
		this.product_description = productDescription;
	}

	public String getProductDescription() {
		return this.product_description;
	}

	public void setProductSize(String productSize) {
		this.product_size = productSize;
	}

	public String getProductSize() {
		return this.product_size;
	}

	public void setBusinessUnit(long businessUnit) {
		this.business_unit = businessUnit;
	}

	public long getBusinessUnit() {
		return this.business_unit;
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

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return this.created_time;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getUser() {
		return this.user;
	}
}
