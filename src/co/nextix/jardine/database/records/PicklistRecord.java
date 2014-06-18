package co.nextix.jardine.database.records;

public class PicklistRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// name

	private long id;
	private String name;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public PicklistRecord(long id, String name) {

		this.id = id;
		this.name = name;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
