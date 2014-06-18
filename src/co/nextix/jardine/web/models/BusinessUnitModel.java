package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class BusinessUnitModel {

	// CreatedTime: "2014-06-14 14:10:58"
	// ModifiedTime: "2014-06-14 14:12:52"
	// assigned_user_id: "8"
	// z_bu_crmno: "BU0000005"
	// z_bu_buname: "Business A"
	// z_bu_isactive: "1"
	// z_bu_businessunitcode: "12345"
	// record_id: 402
	// record_module: "XBusinessUnit"

	@SerializedName("z_bu_crmno")
	private String crm_no;

	@SerializedName("z_bu_buname")
	private String name;

	@SerializedName("z_bu_isactive")
	private String isactive;

	@SerializedName("z_bu_businessunitcode")
	private String code;

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

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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
