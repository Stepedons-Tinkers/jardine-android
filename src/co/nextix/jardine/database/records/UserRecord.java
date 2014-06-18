package co.nextix.jardine.database.records;

public class UserRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	private long id;
	private String no;
	private String username;
	private String password;
	private String email_address;
	private String last_name;
	private String middle_name;
	private String first_name;
	private int logged_in;
	private int status;
	private String last_sync;
	private String created_time;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public UserRecord(long id, String no, String username, String password,
			String emailAdd, String lastName, String midName, String firstName,
			int loggedIn, int status, String lastSync, String createdTime) {

		this.id = id;
		this.no = no;
		this.username = username;
		this.password = password;
		this.email_address = emailAdd;
		this.last_name = lastName;
		this.middle_name = midName;
		this.first_name = firstName;
		this.logged_in = loggedIn;
		this.status = status;
		this.last_sync = lastSync;
		this.created_time = createdTime;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setEmail(String emailAdd) {
		this.email_address = emailAdd;
	}

	public String getEmail() {
		return this.email_address;
	}

	public void setLastname(String lastName) {
		this.last_name = lastName;
	}

	public String getLastname() {
		return this.last_name;
	}

	public void setMiddleName(String middleName) {
		this.middle_name = middleName;
	}

	public String getMiddleName() {
		return this.middle_name;
	}

	public void setFirstNameName(String firstName) {
		this.first_name = firstName;
	}

	public String getFirstNameName() {
		return this.first_name;
	}

	public void setLoggedIn(int loggedIn) {
		this.logged_in = loggedIn;
	}

	public int getLoggedIn() {
		return this.logged_in;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return this.status;
	}

	public void setLastSync(String lastSync) {
		this.last_sync = lastSync;
	}

	public String getLastSync() {
		return this.last_sync;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return this.created_time;
	}
}
