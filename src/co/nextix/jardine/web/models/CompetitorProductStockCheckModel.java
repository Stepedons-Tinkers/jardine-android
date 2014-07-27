package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class CompetitorProductStockCheckModel {

//	"createdtime": "2014-06-26 08:45:59",
//    "modifiedtime": "2014-06-30 11:28:09",
//    "smownerid": "18",
//    "z_cps_activity": "439",
//    "z_cps_competitorprod": "404",
//    "z_cps_crmno": "CMPSTCKCH000003",
//    "z_cps_loadedonshelves": "0",
//    "z_cps_othertyprmrks": "TEST",
//    "z_cps_stockstatus": "- Select -",
//    "xcompprodstockcheckid": "502",
//    "deleted": "0"

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
	
	@SerializedName("z_cps_othertyprmrks")
	private String otherremarks;

	@SerializedName("xcompprodstockcheckid")
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
	
	public String getOtherRemarks() {
		return otherremarks;
	}
	
	public void setOtherRemarks(String otherremarks) {
		this.otherremarks = otherremarks;
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
