package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.CompetitorModel;

import com.google.gson.annotations.SerializedName;

public class ScompetitorRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private ComptResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ComptResult getResult() {
		return this.result;
	}

	public void setResult(ComptResult result) {
		this.result = result;
	}

	public class ComptResult {

		@SerializedName("updated")
		private List<CompetitorModel> updated;

		@SerializedName("deleted")
		private List<CompetitorModel> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<CompetitorModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<CompetitorModel> updated) {
			this.updated = updated;
		}

		public List<CompetitorModel> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<CompetitorModel> deleted) {
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
