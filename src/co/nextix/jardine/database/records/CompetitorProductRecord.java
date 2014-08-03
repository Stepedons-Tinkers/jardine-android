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
	private String crm_no;
	private String competitor;
	private String product_brand;
	private String product_description;
	private String product_size;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long created_by; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public CompetitorProductRecord(long id, String no, String crmNo,
			String competitor, String productBrand, String productDescription,
			String productSize, int isActive, String createdTime,
			String modifiedTime, long created_by) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.competitor = competitor;
		this.product_brand = productBrand;
		this.product_description = productDescription;
		this.product_size = productSize;
		this.is_active = isActive;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.created_by = created_by;
	}
	
	public CompetitorProductRecord(){
		
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

	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}

	public String getCompetitor() {
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

	public void setCreatedBy(long created_by) {
		this.created_by = created_by;
	}

	public long getCreatedBy() {
		return this.created_by;
	}
	
	@Override
	public String toString() {
		return this.product_brand;
	}
}
