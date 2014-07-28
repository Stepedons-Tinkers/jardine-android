package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class JDImerchandisingCheckModel {

	// "createdtime": "2014-06-26 08:41:50",
	// "modifiedtime": "2014-06-26 08:43:02",
	// "smownerid": "18",
	// "z_jmc_activity": "439",
	// "z_jmc_crmno": "JDIMC0000004",
	// "z_jmc_product": "409",
	// "z_jmc_status": "A - Newly Merchandised",
	// "xjdimerchcheckid": "501",
	// "deleted": "0"

	@SerializedName("z_jmc_crmno")
	private String crm_no;

	@SerializedName("z_jmc_activity")
	private String activity;

	@SerializedName("z_jmc_product")
	private String product;

	@SerializedName("z_jmc_status")
	private String status;

	@SerializedName("xjdimerchcheckid")
	private String record_id;

	@SerializedName("record_module")
	private String record_module;

	@SerializedName("createdtime")
	private String created_time;

	@SerializedName("modifiedtime")
	private String modified_time;

	@SerializedName("smownerid")
	private String user_id;

	public String getCrmNo() {
		return this.crm_no;
	}

	public void setCrmNo(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecordId() {
		return this.record_id;
	}

	public void setRecordId(String recordId) {
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
