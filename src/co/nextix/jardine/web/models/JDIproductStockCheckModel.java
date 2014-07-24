package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class JDIproductStockCheckModel {

	// "createdtime": "2014-06-26 08:28:28",
	// "modifiedtime": "2014-06-26 08:30:14",
	// "smownerid": "18",
	// "z_jps_activity": "439",
	// "z_jps_crmno": "JDISTCKCH000005",
	// "z_jps_loadedonshelves": "1",
	// "z_jps_othertyprmrks": "TEST",
	// "z_jps_product": "409",
	// "z_jps_stockstatus": "- Select -",
	// "z_jps_supplier": "411",
	// "xjdiproductstockcheckid": "500",
	// "deleted": "0"

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

	@SerializedName("z_jps_othertyprmrks")
	private String othertyprmrks;

	@SerializedName("z_jps_supplier")
	private String supplier;

	@SerializedName("xjdiproductstockcheckid")
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
	
	public String getOtherRemarks() {
		return othertyprmrks;
	}
	
	public void setOtherRemarks(String remarks) {
		this.othertyprmrks = remarks;
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
