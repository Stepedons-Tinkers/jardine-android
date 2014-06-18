package co.nextix.jardine.database.records;

public class SupplierRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// supplier_name
	// supplier_landline
	// supplier_address
	// credit_line
	// credit_term
	// contact_person
	// is_active (checkbox)
	// created_time
	// modified_time
	// user (assigned to / user table)

	private long id;
	private String no;
	private String supplier_name;
	private String supplier_landline;
	private String supplier_address;
	private String credit_line;
	private String credit_term;
	private String contact_person;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public SupplierRecord(long id, String no, String supplierName,
			String supplierLandline, String supplierAddress, String creditLine,
			String creditTerm, String contactPerson, int isActive,
			String createdTime, String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.supplier_name = supplierName;
		this.supplier_landline = supplierLandline;
		this.supplier_address = supplierAddress;
		this.credit_line = creditLine;
		this.credit_term = creditTerm;
		this.contact_person = contactPerson;
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

	public void setSupplierName(String supplierName) {
		this.supplier_name = supplierName;
	}

	public String getSupplierName() {
		return this.supplier_name;
	}

	public void setSupplierLandline(String supplierLandline) {
		this.supplier_landline = supplierLandline;
	}

	public String getSupplierLandline() {
		return this.supplier_landline;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplier_address = supplierAddress;
	}

	public String getSupplierAddress() {
		return this.supplier_address;
	}

	public void setCreditLine(String creditLine) {
		this.credit_line = creditLine;
	}

	public String getCreditLine() {
		return this.credit_line;
	}

	public void setCreditTerm(String creditTerm) {
		this.credit_term = creditTerm;
	}

	public String getCreditTerm() {
		return this.credit_term;
	}

	public void setContactPerson(String contactPerson) {
		this.contact_person = contactPerson;
	}

	public String getContactPerson() {
		return this.contact_person;
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
