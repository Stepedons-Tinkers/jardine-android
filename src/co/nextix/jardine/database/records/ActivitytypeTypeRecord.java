package co.nextix.jardine.database.records;

public class ActivitytypeTypeRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// name
	// activity_category (activity category table)

	private long id;
	private String name;
	private long activity_category;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ActivitytypeTypeRecord(long id, String name, long activityCategory) {

		this.id = id;
		this.name = name;
		this.activity_category = activityCategory;
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

	public void setActivityCategory(long activityCategory) {
		this.activity_category = activityCategory;
	}

	public long getActivityCategory() {
		return this.activity_category;
	}
}
