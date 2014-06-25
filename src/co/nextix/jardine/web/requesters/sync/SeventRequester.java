package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.EventProtocolModel;

import com.google.gson.annotations.SerializedName;

public class SeventRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private EventResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public EventResult getResult() {
		return this.result;
	}

	public void setResult(EventResult result) {
		this.result = result;
	}

	public class EventResult {

		@SerializedName("updated")
		private List<EventProtocolModel> updated;

		@SerializedName("deleted")
		private List<String> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<EventProtocolModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<EventProtocolModel> updated) {
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
