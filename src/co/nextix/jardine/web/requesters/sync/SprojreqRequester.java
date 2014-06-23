package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.ProjectRequirementModel;

import com.google.gson.annotations.SerializedName;

public class SprojreqRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private ProjReqResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ProjReqResult getResult() {
		return this.result;
	}

	public void setResult(ProjReqResult result) {
		this.result = result;
	}

	public class ProjReqResult {

		@SerializedName("updated")
		private List<ProjectRequirementModel> updated;

		@SerializedName("deleted")
		private List<ProjectRequirementModel> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<ProjectRequirementModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<ProjectRequirementModel> updated) {
			this.updated = updated;
		}

		public List<ProjectRequirementModel> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<ProjectRequirementModel> deleted) {
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
