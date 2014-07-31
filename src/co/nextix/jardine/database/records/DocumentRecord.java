package co.nextix.jardine.database.records;

public class DocumentRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// id
	// no (web no)
	// title
	// folder_name
	// file_name
	// file_type
	// active
	// user (assignedTo)

	private long id;
	private String no;
	private String title;
	private String module_name;
	private String module_id;
	private String file_name;
	private String file_type;
	private String file_path;
	private int is_active;
	private long module_rowid;
	private String created_time;
	private String modified_time;
	private long user; // User Table

	private String crm_no;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public DocumentRecord() {
	}

	public DocumentRecord(long id, String no, String crmNo, String title,
			String moduleName, String moduleId, String fileName,
			String fileType, String filePath, int isActive, long moduleRowId, String createdTime,
			String modifiedTime, long user) {

		this.id = id;
		this.no = no;
		this.crm_no = crmNo;
		this.title = title;
		this.module_name = moduleName;
		this.module_id = moduleId;
		this.file_name = fileName;
		this.file_type = fileType;
		this.file_path = filePath;
		this.is_active = isActive;
		this.module_rowid = moduleRowId;
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
	
	public void setCrmNo(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getCrmNo() {
		return this.crm_no;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setModuleName(String moduleName) {
		this.module_name = moduleName;
	}

	public String getModuleName() {
		return this.module_name;
	}

	public void setModuleId(String moduleId) {
		this.module_id = moduleId;
	}

	public String getModuleId() {
		return this.module_id;
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

	public void setFilePath(String filePath) {
		this.file_path = filePath;
	}

	public String getFilePath() {
		return this.file_path;
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
	
	public void setModuleRowid(long moduleRowid) {
		this.module_rowid = moduleRowid;
	}
	
	public long getModuleRowid() {
		return module_rowid;
	}
	
	@Override
	public String toString() {
		return this.file_name;
	}
}
