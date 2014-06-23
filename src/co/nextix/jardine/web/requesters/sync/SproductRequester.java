package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.ProductModel;

import com.google.gson.annotations.SerializedName;

public class SproductRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private ProdResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ProdResult getResult() {
		return this.result;
	}

	public void setResult(ProdResult result) {
		this.result = result;
	}

	public class ProdResult {

		@SerializedName("updated")
		private List<ProductModel> updated;

		@SerializedName("deleted")
		private List<ProductModel> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<ProductModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<ProductModel> updated) {
			this.updated = updated;
		}

		public List<ProductModel> getDeleted() {
			return this.deleted;
		}

		public void setDeleted(List<ProductModel> deleted) {
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
