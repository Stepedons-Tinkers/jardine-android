package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.WorkplanModel;

import com.google.gson.annotations.SerializedName;

public class SworkplanRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private WorkResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public WorkResult getResult() {
		return this.result;
	}

	public void setResult(WorkResult result) {
		this.result = result;
	}

	public class WorkResult {

		@SerializedName("updated")
		private List<WorkplanModel> updated;

		@SerializedName("deleted")
		private List<WorkplanModel> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<WorkplanModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<WorkplanModel> updated) {
			this.updated = updated;
		}

		public List<WorkplanModel> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<WorkplanModel> deleted) {
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
