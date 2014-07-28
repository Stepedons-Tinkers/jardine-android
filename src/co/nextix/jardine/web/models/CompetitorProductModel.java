package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class CompetitorProductModel {
	// "cf_1143": "fsdf",
	// "createdtime": "2014-06-14 14:49:14",
	// "modifiedtime": "2014-07-23 20:08:35",
	// "smownerid": "11",
	// "z_cmp_crmno": "CPR0000002",
	// "z_cmp_isactive": "1",
	// "z_cmp_prbrnd": "Product Brand1",
	// "z_cmp_prdesc": "Product Desc1",
	// "z_cmp_prsize": "Product Size1",
	// "xcompetitorprodid": "404",
	// "deleted": "0"

	@SerializedName("z_cmp_crmno")
	private String crm_no;

	@SerializedName("z_cmp_comp")
	private String competitor;

	@SerializedName("z_cmp_prbrnd")
	private String prod_brand;

	@SerializedName("z_cmp_prdesc")
	private String prod_desc;

	@SerializedName("z_cmp_prsize")
	private String prod_size;

	@SerializedName("z_cmp_isactive")
	private String isactive;

	@SerializedName("xcompetitorprodid")
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

	public String getCompetitor() {
		return this.competitor;
	}

	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}

	public String getProdBrand() {
		return this.prod_brand;
	}

	public void setProdBrand(String prodBrand) {
		this.prod_brand = prodBrand;
	}

	public String getProdDesc() {
		return this.prod_desc;
	}

	public void setProdDesc(String prodDesc) {
		this.prod_desc = prodDesc;
	}

	public String getProdSize() {
		return this.prod_size;
	}

	public void setProdSize(String prodSize) {
		this.prod_size = prodSize;
	}

	public String getIsActive() {
		return this.isactive;
	}

	public void setIsActive(String isActive) {
		this.isactive = isActive;
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
