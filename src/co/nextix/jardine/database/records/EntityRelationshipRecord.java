package co.nextix.jardine.database.records;

public class EntityRelationshipRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	private long id;
	private String crm_no;
	private String module_name;
	private String related_no;
	private String related_module_name;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public EntityRelationshipRecord(String crmNo, String moduleName,
			String relatedNo, String relatedModuleName) {

		this.crm_no = crmNo;
		this.module_name = moduleName;
		this.related_no = relatedNo;
		this.related_module_name = relatedModuleName;
	}

	public EntityRelationshipRecord(long id, String crmNo, String moduleName,
			String relatedNo, String relatedModuleName) {

		this.id = id;
		this.crm_no = crmNo;
		this.module_name = moduleName;
		this.related_no = relatedNo;
		this.related_module_name = relatedModuleName;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public long getId() {
		return id;
	}

	public void setCrmNo(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getCrmNo() {
		return crm_no;
	}

	public void setModuleName(String moduleName) {
		this.module_name = moduleName;
	}

	public String getModuleName() {
		return this.module_name;
	}

	public void setRelatedNo(String relatedNo) {
		this.related_no = relatedNo;
	}

	public String getRelatedNo() {
		return related_no;
	}

	public void setRelatedModuleName(String relatedModuleName) {
		this.related_module_name = relatedModuleName;
	}

	public String getRelatedModuleName() {
		return related_module_name;
	}
}
