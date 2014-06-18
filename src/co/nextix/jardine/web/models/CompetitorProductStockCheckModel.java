package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class CompetitorProductStockCheckModel {

	// CreatedTime: "2014-06-18 00:15:53"
	// ModifiedTime: "2014-06-18 00:15:53"
	// assigned_user_id: "18"
	// z_cps_crmno: "CMPSTCKCH000002"
	// z_cps_activity: "423"
	// z_cps_competitorprod: "404"
	// z_cps_stockstatus: "A - On Stock"
	// z_cps_loadedonshelves: "0"
	// record_id: 428
	// record_module: "XCompProdStockCheck"

	@SerializedName("z_cps_crmno")
	private String crm_no;

	@SerializedName("z_cps_activity")
	private String activity;

	@SerializedName("z_cps_competitorprod")
	private String competitorprod;

	@SerializedName("z_cps_stockstatus")
	private String stockstatus;

	@SerializedName("z_cps_loadedonshelves")
	private String loadedonshelves;

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

	public String getCompetitorProduct() {
		return this.competitorprod;
	}

	public void setCompetitorProduct(String competitorProduct) {
		this.competitorprod = competitorProduct;
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
