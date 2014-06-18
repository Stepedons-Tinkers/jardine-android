package co.nextix.jardine.database.records;

public class CityTownRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// name
	// province (Province table)

	private long id;
	private String name;
	private long province;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public CityTownRecord(long id, String name, long province) {

		this.id = id;
		this.name = name;
		this.province = province;
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

	public void setProvince(long province) {
		this.province = province;
	}

	public long getProvince() {
		return this.province;
	}
}
