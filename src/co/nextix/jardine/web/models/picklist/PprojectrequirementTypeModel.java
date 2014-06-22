package co.nextix.jardine.web.models.picklist;

import com.google.gson.annotations.SerializedName;

public class PprojectrequirementTypeModel {
	@SerializedName("z_pr_prtype")
	private String z_pr_prtype;

	public String getValue() {
		return this.z_pr_prtype;
	}

	public void setValue(String z_pr_prtype) {
		this.z_pr_prtype = z_pr_prtype;
	}
}
