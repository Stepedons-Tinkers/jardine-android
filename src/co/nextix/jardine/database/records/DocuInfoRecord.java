package co.nextix.jardine.database.records;

public class DocuInfoRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	private String crmno;
	private String moduletype;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public DocuInfoRecord(String no, String moduleType) {

		this.crmno = no;
		this.moduletype = moduleType;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public void setNo(String no) {
		this.crmno = no;
	}

	public String getNo() {
		return this.crmno;
	}

	public void setModuleType(String moduleType) {
		this.moduletype = moduleType;
	}

	public String getModuleType() {
		return this.moduletype;
	}
}
