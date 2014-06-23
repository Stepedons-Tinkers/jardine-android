package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.CompetitorProductModel;

import com.google.gson.annotations.SerializedName;

public class ScompetrprodRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private ComptProdResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ComptProdResult getResult() {
		return this.result;
	}

	public void setResult(ComptProdResult result) {
		this.result = result;
	}

	public class ComptProdResult {

		@SerializedName("updated")
		private List<CompetitorProductModel> updated;

		@SerializedName("deleted")
		private List<CompetitorProductModel> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<CompetitorProductModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<CompetitorProductModel> updated) {
			this.updated = updated;
		}

		public List<CompetitorProductModel> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<CompetitorProductModel> deleted) {
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
