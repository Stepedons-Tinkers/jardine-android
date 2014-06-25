package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class DocuRelModel {

	// "result": [
	// {
	// "crmid": "408",
	// "notesid": "487"
	// },
	// {
	// "crmid": "408",
	// "notesid": "491"
	// }
	// ]

	@SerializedName("crmid")
	private String crmid;

	@SerializedName("notesid")
	private String notesid;

	public String getCrmId() {
		return this.crmid;
	}

	public void setCrmId(String crmid) {
		this.crmid = crmid;
	}

	public String getNotesid() {
		return this.notesid;
	}

	public void setNotesid(String notesid) {
		this.notesid = notesid;
	}
}
