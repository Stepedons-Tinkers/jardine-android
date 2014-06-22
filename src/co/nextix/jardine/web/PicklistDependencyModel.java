package co.nextix.jardine.web;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PicklistDependencyModel {
	// "id": "5",
	// "tabid": "58",
	// "sourcefield": "z_area",
	// "targetfield": "z_province",
	// "sourcevalue": "- Select -",
	// "targetvalues": "[&quot;- Select -&quot;]",
	// "criteria": null

	@SerializedName("id")
	private String id;

	@SerializedName("tabid")
	private String tabid;

	@SerializedName("sourcefield")
	private String sourcefield;

	@SerializedName("targetfield")
	private String targetfield;

	@SerializedName("sourcevalue")
	private String sourcevalue;

	@SerializedName("targetvalues")
	private String targetvalues;

//	@SerializedName("criteria")
//	private String criteria;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTabId() {
		return this.tabid;
	}

	public void setTabId(String tabid) {
		this.tabid = tabid;
	}

	public String getSourceField() {
		return this.sourcefield;
	}

	public void setSourceField(String sourcefield) {
		this.sourcefield = sourcefield;
	}

	public String getTargetField() {
		return this.targetfield;
	}

	public void setTargetField(String targetfield) {
		this.targetfield = targetfield;
	}

	public String getSourceValue() {
		return this.sourcevalue;
	}

	public void setSourceValue(String sourcevalue) {
		this.sourcevalue = sourcevalue;
	}

	public String getTargetValues() {
		return this.targetvalues;
	}

	public void setTargetValues(String targetvalues) {
		this.targetvalues = targetvalues;
	}

//	public String getCriteria() {
//		return this.criteria;
//	}
//
//	public void setCriteria(String criteria) {
//		this.criteria = criteria;
//	}

}
