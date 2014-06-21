package co.nextix.jardine.database.records;

public class JDImerchandisingCheckRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// activity (activity table)
	// product (product table)
	// status
	// user (user table)

	private long id;
	private String no;
	private long activity;
	private long product;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public JDImerchandisingCheckRecord() {
	}

	public JDImerchandisingCheckRecord(long id, String no, long activity,
			long product, int isActive, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.activity = activity;
		this.product = product;
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

	public void setIsActive(int isActive) {
		this.is_active = isActive;
	}

	public int getIsActive() {
		return this.is_active;
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

	public void setUser(long user) {
		this.user = user;
	}

	public long getUser() {
		return this.user;
	}
}
