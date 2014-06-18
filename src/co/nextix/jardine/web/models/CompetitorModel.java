package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class CompetitorModel {

	// CreatedTime: "2014-06-14 14:15:03"
	// ModifiedTime: "2014-06-14 14:43:22"
	// assigned_user_id: "12"
	// z_cm_crmno: "COM3"
	// z_cm_cmname: "Competitor 1"
	// z_cm_isactv: "1"
	// record_id: 403
	// record_module: "XCompetitor"

	@SerializedName("z_cm_crmno")
	private String crm_no;

	@SerializedName("z_cm_cmname")
	private String name;

	@SerializedName("z_cm_isactv")
	private String isactive;

	@SerializedName("record_id")
	private long record_id;

	@SerializedName("record_module")
	private String record_module;

	@SerializedName("CreatedTime")
	private String created_time;

	@SerializedName("ModifiedTime")
	private String modified_time;

	@SerializedName("assigned_user_id")
	private String user_id;

	public String getCrmNo() {
		return this.crm_no;
	}

	public void setCrmNo(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsActive() {
		return this.isactive;
	}

	public void setIsActive(String isActive) {
		this.isactive = isActive;
	}

	public long getRecordId() {
		return this.record_id;
	}

	public void setRecordId(long recordId) {
		this.record_id = recordId;
	}

	public String getRecordModule() {
		return this.record_module;
	}

	public void setRecordModule(String recordModule) {
		this.record_module = recordModule;
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
