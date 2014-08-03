package co.nextix.jardine.database.records;

public class EntityRelationshipRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	private long id;
	private long module_id;
	private String module_no;
	private String module_name;
	private long related_id;
	private String related_no;
	private String related_name;
	private int is_synced;

	// ===========================================================
	// Public constructors
	// ===========================================================

//	public EntityRelationshipRecord(long moduleId, String moduleNo,
//			String moduleName, long relatedId, String relatedNo,
//			String relatedName, int isSynced) {
//
//		this.module_id = moduleId;
//		this.module_no = moduleNo;
//		this.module_name = moduleName;
//		this.related_id = relatedId;
//		this.related_no = relatedNo;
//		this.related_name = relatedName;
//		this.is_synced = isSynced;
//	}

	public EntityRelationshipRecord(long id, long moduleId, String moduleNo,
			String moduleName, long relatedId, String relatedNo,
			String relatedName, int isSynced) {

		this.id = id;
		this.module_id = moduleId;
		this.module_no = moduleNo;
		this.module_name = moduleName;
		this.related_id = relatedId;
		this.related_no = relatedNo;
		this.related_name = relatedName;
		this.is_synced = isSynced;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public long getId() {
		return id;
	}

	public void setModuleId(long moduleId) {
		this.module_id = moduleId;
	}

	public long getModuleId() {
		return module_id;
	}

	public void setModuleNo(String moduleNo) {
		this.module_no = moduleNo;
	}

	public String getModuleNo() {
		return module_no;
	}

	public void setModuleName(String moduleName) {
		this.module_name = moduleName;
	}

	public String getModuleName() {
		return this.module_name;
	}

	public void setRelatedId(long relatedId) {
		this.related_id = relatedId;
	}

	public long getRelatedId() {
		return related_id;
	}

	public void setRelatedNo(String relatedNo) {
		this.related_no = relatedNo;
	}

	public String getRelatedNo() {
		return related_no;
	}

	public void setRelatedName(String relatedName) {
		this.related_name = relatedName;
	}

	public String getRelatedName() {
		return related_name;
	}
	
	public void setIsSynced(int isSynced) {
		this.is_synced = isSynced;
	}
	
	public int getIsSynced() {
		return is_synced;
	}
}
