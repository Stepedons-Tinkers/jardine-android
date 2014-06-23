package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.SMRModel;

import com.google.gson.annotations.SerializedName;

public class SsmrRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private SmrResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public SmrResult getResult() {
		return this.result;
	}

	public void setResult(SmrResult result) {
		this.result = result;
	}

	public class SmrResult {

		@SerializedName("updated")
		private List<SMRModel> updated;

		@SerializedName("deleted")
		private List<SMRModel> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<SMRModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<SMRModel> updated) {
			this.updated = updated;
		}

		public List<SMRModel> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<SMRModel> deleted) {
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
