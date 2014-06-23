package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.MarketingIntelModel;

import com.google.gson.annotations.SerializedName;

public class SmarkintRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private MarketIntResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public MarketIntResult getResult() {
		return this.result;
	}

	public void setResult(MarketIntResult result) {
		this.result = result;
	}

	public class MarketIntResult {

		@SerializedName("updated")
		private List<MarketingIntelModel> updated;

		@SerializedName("deleted")
		private List<MarketingIntelModel> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<MarketingIntelModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<MarketingIntelModel> updated) {
			this.updated = updated;
		}

		public List<MarketingIntelModel> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<MarketingIntelModel> deleted) {
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
