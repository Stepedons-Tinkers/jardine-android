package co.nextix.jardine.database.records;

public class CustomerContactRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// first_name
	// last_name
	// position (position table / picklist)
	// mobile_no
	// birthday
	// email_address
	// customer (customer table)
	// created_time
	// modified_time
	// user(assigned_to / user table)

	private long id;
	private String no;
	private String first_name;
	private String last_name;
	private long position;
	private String mobile_no;
	private String birthday;
	private String email_address;
	private long customer;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public CustomerContactRecord(long id, String no, String firstName,
			String lastName, long position, String mobileNo, String birthday,
			String emailAddress, long customer, int isActive, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.first_name = firstName;
		this.last_name = lastName;
		this.position = position;
		this.mobile_no = mobileNo;
		this.birthday = birthday;
		this.email_address = emailAddress;
		this.customer = customer;
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

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getFirstName() {
		return this.first_name;
	}
	
	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public String getLastName() {
		return this.last_name;
	}
	
	public void setPosition(long position) {
		this.position = position;
	}

	public long getPosition() {
		return this.position;
	}
	
	public void setMobileNo(String mobileNo) {
		this.mobile_no = mobileNo;
	}

	public String getMobileNo() {
		return this.mobile_no;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setEmailAddress(String emailAddress) {
		this.email_address = emailAddress;
	}

	public String getEmailAddress() {
		return this.email_address;
	}
	
	public void setCustomer(long customer) {
		this.customer = customer;
	}

	public long getCustomer() {
		return this.customer;
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
