package co.nextix.jardine.database.records;

public class CustomerRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// customer_name
	// street_address
	// chain_name
	// landline
	// customer_size
	// customer_record_status (customer record stat table/picklist)
	// customer_type (customer type table/picklist)
	// business_unit (Business unit table)
	// province (province table/picklist)
	// city_town (city town table /picklist)
	// is_active
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private String customer_name;
	private String chain_name;
	private String landline;
	private String fax;
	private long customer_size;
	private String street_address;
	// private long customer_record_status; removed
	private long customer_type;
	private long business_unit;
	private long area;
	private long province;
	private long city_town;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public CustomerRecord() {

	}

	public CustomerRecord(long id, String no, String customerName,
			String chainName, String landline, String fax, long customerSize,
			String streetAddress, long customerType, long businessUnit,
			long area, long province, long cityTown, int isActive,
			String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.customer_name = customerName;
		this.chain_name = chainName;
		this.landline = landline;
		this.fax = fax;
		this.customer_size = customerSize;
		this.street_address = streetAddress;
//		this.customer_record_status = customerRecordStatus;
		this.customer_type = customerType;
		this.business_unit = businessUnit;
		this.area = area;
		this.province = province;
		this.city_town = cityTown;
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

	public void setCustomerName(String customerName) {
		this.customer_name = customerName;
	}

	public String getCustomerName() {
		return this.customer_name;
	}

	public void setChainName(String chainName) {
		this.chain_name = chainName;
	}

	public String getChainName() {
		return this.chain_name;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return this.fax;
	}

	public void setCustomerSize(long customerSize) {
		this.customer_size = customerSize;
	}

	public long getCustomerSize() {
		return this.customer_size;
	}

	public void setStreetAddress(String streetAddress) {
		this.street_address = streetAddress;
	}

	public String getStreetAddress() {
		return this.street_address;
	}

//	public void setCustomerRecordStatus(long customerRecordStatus) {
//		this.customer_record_status = customerRecordStatus;
//	}
//
//	public long getCustomerRecordStatus() {
//		return this.customer_record_status;
//	}

	public void setCustomerType(long customerType) {
		this.customer_type = customerType;
	}

	public long getCustomerType() {
		return this.customer_type;
	}

	public void setBusinessUnit(long businessUnit) {
		this.business_unit = businessUnit;
	}

	public long getBusinessUnit() {
		return this.business_unit;
	}

	public void setArea(long area) {
		this.area = area;
	}

	public long getArea() {
		return this.area;
	}

	public void setProvince(long province) {
		this.province = province;
	}

	public long getProvince() {
		return this.province;
	}

	public void setCityTown(long cityTown) {
		this.city_town = cityTown;
	}

	public long getCityTown() {
		return this.city_town;
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
