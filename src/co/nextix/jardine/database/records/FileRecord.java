package co.nextix.jardine.database.records;

public class FileRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	private String file_name;
	private String file_path;
	private String file_size;
	private String module_name;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public FileRecord(String fileName, String filePath, String fileSize,
			String moduleName) {

		this.file_name = fileName;
		this.file_path = filePath;
		this.file_size = fileSize;
		this.module_name = moduleName;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public void setFileName(String fileName) {
		this.file_name = fileName;
	}

	public String getFileName() {
		return this.file_name;
	}

	public void setFilePath(String filePath) {
		this.file_path = filePath;
	}

	public String getFilePath() {
		return this.file_path;
	}

	public void setFileSize(String fileSize) {
		this.file_size = fileSize;
	}

	public String getFileSize() {
		return this.file_size;
	}

	public void setModuleName(String moduleName) {
		this.module_name = moduleName;
	}

	public String getModuleName() {
		return this.module_name;
	}
}
