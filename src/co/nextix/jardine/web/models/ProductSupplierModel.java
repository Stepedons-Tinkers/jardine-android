package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class ProductSupplierModel {
	// "createdtime": "2014-07-21 18:38:14",
	// "modifiedtime": "2014-07-25 19:11:55",
	// "smownerid": "1",
	// "z_ps_activity": "423",
	// "z_ps_crmno": "PS00000",
	// "z_ps_othersremarks": "",
	// "z_ps_productbrand": "409",
	// "z_ps_supplier": "473",
	// "xproductsupplierid": "520",
	// "deleted": "0"

	@SerializedName("xproductsupplierid")
	private String record_id;

	@SerializedName("z_ps_crmno")
	private String crm_no;

	@SerializedName("z_ps_activity")
	private String activity;

	@SerializedName("z_ps_othersremarks")
	private String othersremarks;

	@SerializedName("z_ps_productbrand")
	private String productbrand;

	@SerializedName("z_ps_supplier")
	private String supplier;

	@SerializedName("createdtime")
	private String created_time;

	@SerializedName("modifiedtime")
	private String modified_time;

	@SerializedName("smownerid")
	private String user_id;

	public void setRecordId(String recordId) {
		this.record_id = recordId;
	}

	public String getRecordId() {
		return record_id;
	}

	public void setCrmNo(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getCrmNo() {
		return crm_no;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getActivity() {
		return activity;
	}

	public void setOthersRemarks(String othersRemarks) {
		this.othersremarks = othersRemarks;
	}

	public String getOthersRemarks() {
		return othersremarks;
	}

	public void setProductbrand(String productbrand) {
		this.productbrand = productbrand;
	}

	public String getProductbrand() {
		return productbrand;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setUserId(String userId) {
		this.user_id = userId;
	}

	public String getUserId() {
		return user_id;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getCreatedTime() {
		return created_time;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modified_time = modifiedTime;
	}

	public String getModifiedTime() {
		return modified_time;
	}
}
