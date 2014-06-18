package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class JDImerchandisingCheckModel {

	// CreatedTime: "2014-06-18 00:22:21"
	// ModifiedTime: "2014-06-18 00:22:21"
	// assigned_user_id: "18"
	// z_jmc_crmno: "JDIMC0000002"
	// z_jmc_activity: "423"
	// z_jmc_product: "409"
	// z_jmc_status: "A - Newly Merchandised"
	// record_id: 429
	// record_module: "XJDIMerchCheck"

	@SerializedName("z_jmc_crmno")
	private String crm_no;

	@SerializedName("z_jmc_activity")
	private String activity;

	@SerializedName("z_jmc_product")
	private String product;

	@SerializedName("z_jmc_status")
	private String status;

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
