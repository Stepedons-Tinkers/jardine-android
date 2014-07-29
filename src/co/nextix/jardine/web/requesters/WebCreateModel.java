package co.nextix.jardine.web.requesters;

import com.google.gson.annotations.SerializedName;

public class WebCreateModel {
	// id: 432
	// mobile_id: null
	// modifiedtime: "2014-06-18 22:16:34"

	@SerializedName("id")
	private long no;

	@SerializedName("mobile_id")
	private long mobile_id;

	@SerializedName("modifiedtime")
	private String modifiedtime;

	@SerializedName("crmid")
	private String crmid;

	public long getNo() {
		return this.no;
	}

	public void setNo(long no) {
		this.no = no;
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

	public String getCrmId() {
		return this.crmid;
	}

	public void setCrmId(String crmid) {
		this.crmid = crmid;
	}
}
