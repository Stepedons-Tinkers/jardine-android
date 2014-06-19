package co.nextix.jardine.web.requesters;

import com.google.gson.annotations.SerializedName;

public class WebCreateModel {
	// id: 432
	// mobile_id: null
	// modifiedtime: "2014-06-18 22:16:34"

	@SerializedName("id")
	private long crm_no;

	@SerializedName("mobile_id")
	private long mobile_id;

	@SerializedName("modifiedtime")
	private String modifiedtime;

	public long getCrmNo() {
		return this.crm_no;
	}

	public void setCrmNo(long crmNo) {
		this.crm_no = crmNo;
	}

	public long getMobileId() {
		return this.mobile_id;
	}

	public void setMobileId(long mobileId) {
		this.mobile_id = mobileId;
	}

	public String getModifiedTime() {
		return this.modifiedtime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedtime = modifiedTime;
	}
}
