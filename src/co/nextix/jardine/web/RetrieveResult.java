package co.nextix.jardine.web;

import com.google.gson.annotations.SerializedName;

public class RetrieveResult<T> {
	@SerializedName("id")
	private int id;

	@SerializedName("details")
	private T details;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public T getDetails() {
		return this.details;
	}

	public void setDetails(T details) {
		this.details = details;
	}
}
