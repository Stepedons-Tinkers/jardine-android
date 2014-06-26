package co.nextix.jardine.web.requesters;

import java.util.List;

import co.nextix.jardine.web.models.DocumentModel;

import com.google.gson.annotations.SerializedName;

public class DocumentRequester {

	// "success": true,
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

	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private Result result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Result getResult() {
		return this.result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public class Result {

		@SerializedName("details")
		private List<DocumentModel> details;

		@SerializedName("id")
		private String id;

		public List<DocumentModel> getDetails() {
			return this.details;
		}

		public void setDetails(List<DocumentModel> details) {
			this.details = details;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
