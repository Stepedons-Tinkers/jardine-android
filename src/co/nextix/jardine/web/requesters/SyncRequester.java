package co.nextix.jardine.web.requesters;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SyncRequester<T> {

	// {
	// "success": true,
	// "result": {
	// "updated": [
	// {
	// "createdtime": "2014-06-17 23:09:57",
	// "modifiedtime": "2014-06-17 23:09:57",
	// "smownerid": "18",
	// "z_area": "CENTRAL NCR AREA",
	// "z_city": "MANDALUYONG CITY",
	// "z_province": "MANDALUYONG",
	// "z_wpe_activityquantity": "2",
	// "z_wpe_activitytype": "352",
	// "z_wpe_crmno": "WPE000002",
	// "z_wpe_date": "2014-06-18",
	// "z_wpe_othersremarks": "",
	// "z_wpe_status": "Pending For Approval",
	// "z_wpe_workplan": "422",
	// "xworkplanentryid": "425",
	// "deleted": "0"
	// }
	// ],
	// "deleted": [],
	// "more": false,
	// "lastModifiedTime": 1403017797
	// }
	// }

	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private Result result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Result getResult() {
		return this.result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public class Result {

		@SerializedName("updated")
		private List<T> updated;

		@SerializedName("deleted")
		private List<T> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<T> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<T> updated) {
			this.updated = updated;
		}

		public List<T> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<T> deleted) {
			this.deleted = deleted;
		}

		public boolean getMore() {
			return this.more;
		}

		public void setMore(boolean more) {
			this.more = more;
		}

		public String getLastModifiedTime() {
			return this.lastModifiedTime;
		}

		public void setLastModifiedTime(String lastModifiedTime) {
			this.lastModifiedTime = lastModifiedTime;
		}
	}
}
