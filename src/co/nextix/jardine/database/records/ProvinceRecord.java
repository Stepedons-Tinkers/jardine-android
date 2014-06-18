package co.nextix.jardine.database.records;

public class ProvinceRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// name
	// area (Area table)

	private long id;
	private String name;
	private long area;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ProvinceRecord(long id, String name, long area) {

		this.id = id;
		this.name = name;
		this.area = area;
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

	public void setArea(long area) {
		this.area = area;
	}

	public long getArea() {
		return this.area;
	}
}
