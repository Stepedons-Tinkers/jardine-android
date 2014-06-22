package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class PicklistModel {
	@SerializedName("fieldid")
	private String fieldid;

	@SerializedName("fieldname")
	private String fieldname;

	@SerializedName("tablename")
	private String tablename;

	public String getFieldid() {
		return this.fieldid;
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

	public String getFieldname() {
		return this.fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}
