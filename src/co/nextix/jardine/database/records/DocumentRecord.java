package co.nextix.jardine.database.records;

public class DocumentRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// id
	// no (crm no)
	// title
	// folder_name
	// file_name
	// file_type
	// active
	// user (assignedTo)

	private long id;
	private String no;
	private String title;
	private String folder_name;
	private String file_name;
	private String file_type;
	private int is_active;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	// ===========================================================
	// Public constructors
	// ===========================================================

	public DocumentRecord() {
	}

	public DocumentRecord(long id, String no, String title, String folderName,
			String fileName, String fileType, int isActive, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.title = title;
		this.folder_name = folderName;
		this.file_name = fileName;
		this.file_type = fileType;
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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setFolderName(String folderName) {
		this.folder_name = folderName;
	}

	public String getFolderName() {
		return this.folder_name;
	}

	public void setFileName(String fileName) {
		this.file_name = fileName;
	}

	public String getFileName() {
		return this.file_name;
	}

	public void setFileType(String fileType) {
		this.file_type = fileType;
	}

	public String getFileType() {
		return this.file_type;
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
