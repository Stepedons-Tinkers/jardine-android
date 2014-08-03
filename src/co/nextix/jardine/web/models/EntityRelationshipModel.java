package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class EntityRelationshipModel {
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

	@SerializedName("module")
	private String module;

	@SerializedName("relcrmid")
	private String relation_crmid;

	@SerializedName("relmodule")
	private String relation_module;

	public String getCrmId() {
		return this.crmid;
	}

	public void setCrmId(String crmid) {
		this.crmid = crmid;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getRelatedCrmId() {
		return relation_crmid;
	}

	public void setRelatedCrmId(String relationCrmId) {
		this.relation_crmid = relationCrmId;
	}

	public String getRelatedModule() {
		return relation_module;
	}

	public void setRelatedModule(String relationModule) {
		this.relation_module = relationModule;
	}
}
