package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.CustomerModel;

import com.google.gson.annotations.SerializedName;

public class ScustRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private CustResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public CustResult getResult() {
		return this.result;
	}

	public void setResult(CustResult result) {
		this.result = result;
	}

	public class CustResult {

		@SerializedName("updated")
		private List<CustomerModel> updated;

		@SerializedName("deleted")
		private List<String> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<CustomerModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<CustomerModel> updated) {
			this.updated = updated;
		}

		public List<String> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<String> deleted) {
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
