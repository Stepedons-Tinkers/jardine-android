package co.nextix.jardine.database.records;

public class ProductFocusRecord {
	// ===========================================================
	// Private fields
	// ===========================================================

	// General Details

	// _id
	// no
	// activity_type_categorization ( activity type category table / picklist)
	// is_active (checkbox)
	// user(assigned_to / user table)

	private long id;
	private long product;
	// private long activity_type;
	private long activity;

	// ===========================================================
	// Public constructors
	// ===========================================================

	public ProductFocusRecord(long id, long product, long activity) {

		this.id = id;
		this.product = product;
		this.activity = activity;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public long getId() {
		return this.id;
	}

	public void setProduct(long product) {
		this.product = product;
	}

	public long getProduct() {
		return product;
	}

	public void setActivity(long activity) {
		this.activity = activity;
	}

	public long getActivity() {
		return activity;
	}

}
