package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.JDIproductStockCheckModel;

import com.google.gson.annotations.SerializedName;

public class SjdiprodRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private JdiProdResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public JdiProdResult getResult() {
		return this.result;
	}

	public void setResult(JdiProdResult result) {
		this.result = result;
	}

	public class JdiProdResult {

		@SerializedName("updated")
		private List<JDIproductStockCheckModel> updated;

		@SerializedName("deleted")
		private List<JDIproductStockCheckModel> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<JDIproductStockCheckModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<JDIproductStockCheckModel> updated) {
			this.updated = updated;
		}

		public List<JDIproductStockCheckModel> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<JDIproductStockCheckModel> deleted) {
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
