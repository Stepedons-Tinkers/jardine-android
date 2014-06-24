package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.BusinessUnitModel;

import com.google.gson.annotations.SerializedName;

public class SbuRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private BuResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public BuResult getResult() {
		return this.result;
	}

	public void setResult(BuResult result) {
		this.result = result;
	}

	public class BuResult {

		@SerializedName("updated")
		private List<BusinessUnitModel> updated;

		@SerializedName("deleted")
		private List<String> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<BusinessUnitModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<BusinessUnitModel> updated) {
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
