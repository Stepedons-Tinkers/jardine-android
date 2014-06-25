package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.JDImerchandisingCheckModel;

import com.google.gson.annotations.SerializedName;

public class SjdimerchRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private JdiMerchResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public JdiMerchResult getResult() {
		return this.result;
	}

	public void setResult(JdiMerchResult result) {
		this.result = result;
	}

	public class JdiMerchResult {

		@SerializedName("updated")
		private List<JDImerchandisingCheckModel> updated;

		@SerializedName("deleted")
		private List<String> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<JDImerchandisingCheckModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<JDImerchandisingCheckModel> updated) {
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
