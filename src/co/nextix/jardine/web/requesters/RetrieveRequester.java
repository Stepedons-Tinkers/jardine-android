package co.nextix.jardine.web.requesters;

import com.google.gson.annotations.SerializedName;

public class RetrieveRequester<T> {
	// success: true
	// -result: {
	// -details: {
	// -412: {
	// CreatedTime: "2014-06-14 17:42:40"
	// ModifiedTime: "2014-06-14 18:13:56"
	// assigned_user_id: "18"
	// z_min_crmno: "MIN0000002"
	// z_min_activity: ""
	// z_min_competitor: "403"
	// z_min_details: "test"
	// record_id: 412
	// record_module: "XMarketingIntel"
	// }
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

		@SerializedName("details")
		private T details;

		public T getDetails() {
			return this.details;
		}

		public void setDetails(T details) {
			this.details = details;
		}
	}
}
