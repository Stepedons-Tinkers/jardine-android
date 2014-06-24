package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.ActivityTypeModel;

import com.google.gson.annotations.SerializedName;

public class SacttypeRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private ActTypeResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ActTypeResult getResult() {
		return this.result;
	}

	public void setResult(ActTypeResult result) {
		this.result = result;
	}

	public class ActTypeResult {

		@SerializedName("updated")
		private List<ActivityTypeModel> updated;

		@SerializedName("deleted")
		private List<String> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<ActivityTypeModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<ActivityTypeModel> updated) {
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
