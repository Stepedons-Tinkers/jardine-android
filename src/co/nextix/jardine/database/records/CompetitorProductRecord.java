package co.nextix.jardine.database.records;

public class CompetitorProductRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// competitor (competitor table)
	// product_brand
	// product_description
	// product_size
	// is_active (checkbox)
	// created_time
	// modified_time
	// user (assigned to / user table)

	private long id;
	private String no;
	private long competitor;
	private String product_brand;
	private String product_description;
	private String product_size;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public CompetitorProductRecord(long id, String no, long competitor,
			String productBrand, String productDescription, String productSize,
			int isActive, String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.competitor = competitor;
		this.product_brand = productBrand;
		this.product_description = productDescription;
		this.product_size = productSize;
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

	public void setCompetitor(long competitor) {
		this.competitor = competitor;
	}

	public long getCompetitor() {
		return this.competitor;
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
