package co.nextix.jardine.web.requesters.sync;

import java.util.List;

import co.nextix.jardine.web.models.ProductSupplierModel;

import com.google.gson.annotations.SerializedName;

public class SproductSupplierRequester {
	@SerializedName("success")
	private String success;

	@SerializedName("result")
	private ProductSupplierResult result;

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ProductSupplierResult getResult() {
		return this.result;
	}

	public void setResult(ProductSupplierResult result) {
		this.result = result;
	}

	public class ProductSupplierResult {

		@SerializedName("updated")
		private List<ProductSupplierModel> updated;

		@SerializedName("deleted")
		private List<String> deleted;

		@SerializedName("more")
		private boolean more;

		@SerializedName("lastModifiedTime")
		private String lastModifiedTime;

		public List<ProductSupplierModel> getUpdated() {
			return this.updated;
		}

		public void setUpdated(List<ProductSupplierModel> updated) {
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
