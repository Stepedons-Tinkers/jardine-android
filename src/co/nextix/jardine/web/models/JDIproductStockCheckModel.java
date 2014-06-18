package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class JDIproductStockCheckModel {

	// CreatedTime: "2014-06-18 00:12:39"
	// ModifiedTime: "2014-06-18 00:12:39"
	// assigned_user_id: "18"
	// z_jps_crmno: "JDISTCKCH000002"
	// z_jps_activity: "423"
	// z_jps_product: "409"
	// z_jps_stockstatus: "A - On Stock"
	// z_jps_loadedonshelves: "1"
	// z_jps_supplier: "411"
	// record_id: 427
	// record_module: "XJDIProductStockCheck"

	@SerializedName("z_jps_crmno")
	private String crm_no;

	@SerializedName("z_jps_activity")
	private String activity;

	@SerializedName("z_jps_product")
	private String product;

	@SerializedName("z_jps_stockstatus")
	private String stockstatus;

	@SerializedName("z_jps_loadedonshelves")
	private String loadedonshelves;

	@SerializedName("z_jps_supplier")
	private String supplier;

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

	public String getStockstatus() {
		return this.stockstatus;
	}

	public void setStockstatus(String stockstatus) {
		this.stockstatus = stockstatus;
	}

	public String getLoadedonshelves() {
		return this.loadedonshelves;
	}

	public void setLoadedonshelves(String loadedonshelves) {
		this.loadedonshelves = loadedonshelves;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
