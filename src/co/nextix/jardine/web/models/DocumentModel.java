package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class DocumentModel {
	// "result": [
	// {
	// "id": 487,
	// "details": {
	// "notes_title": "SampleFile",
	// "createdtime": "2014-06-25 17:04:35",
	// "modifiedtime": "2014-06-25 17:04:35",
	// "filename": "01.jpg",
	// "assigned_user_id": "18",
	// "notecontent": "",
	// "filetype": "image/jpeg",
	// "filesize": "72265",
	// "filelocationtype": "I",
	// "fileversion": "",
	// "filestatus": "1",
	// "filedownloadcount": null,
	// "folderid": "1",
	// "note_no": "DOC6",
	// "modifiedby": "18",
	// "record_id": 487,
	// "record_module": "Documents",
	// "file_path":
	// "http://124.105.240.108:3000/downloader/storage(^_^)2014(^_^)June(^_^)week4(^_^)488_01.jpg"
	// }
	// }
	// ]

	@SerializedName("note_no")
	private String note_no;

	@SerializedName("notes_title")
	private String notes_title;

	@SerializedName("filename")
	private String filename;

	@SerializedName("filetype")
	private String filetype;

	@SerializedName("filesize")
	private String filesize;

	@SerializedName("folderid")
	private String folderid;

	@SerializedName("file_path")
	private String file_path;

	@SerializedName("createdtime")
	private String created_time;

	@SerializedName("modifiedtime")
	private String modified_time;

	@SerializedName("assigned_user_id")
	private String user_id;

	public String getNoteNo() {
		return this.note_no;
	}

	public void setNoteNo(String noteNo) {
		this.note_no = noteNo;
	}

	public String getNotesTitle() {
		return this.notes_title;
	}

	public void setNotesTitle(String notesTitle) {
		this.notes_title = notesTitle;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileType() {
		return this.filetype;
	}

	public void setFileType(String fileType) {
		this.filetype = fileType;
	}

	public String getFileSize() {
		return this.filesize;
	}

	public void setFileSize(String fileSize) {
		this.filesize = fileSize;
	}

	public String getFolderId() {
		return this.folderid;
	}

	public void setFolderId(String folderId) {
		this.folderid = folderId;
	}

	public String getFilePath() {
		return this.file_path;
	}

	public void setFilePath(String filePath) {
		this.file_path = filePath;
	}

	public String getCreatedTime() {
		return this.created_time;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getModifiedTime() {
		return this.modified_time;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modified_time = modifiedTime;
	}

	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String userId) {
		this.user_id = userId;
	}
}
