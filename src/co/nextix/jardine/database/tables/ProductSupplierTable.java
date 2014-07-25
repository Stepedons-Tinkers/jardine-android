package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.ProductSupplierRecord;

public class ProductSupplierTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_PRODUCTSUPPLIER_ROWID = "_id";
	private final String KEY_PRODUCTSUPPLIER_NO = "no";
	private final String KEY_PRODUCTSUPPLIER_CRMNO = "crm_no";
	private final String KEY_PRODUCTSUPPLIER_PRODUCTBRAND = "product_brand";
	private final String KEY_PRODUCTSUPPLIER_SUPPLIER = "supplier";
	private final String KEY_PRODUCTSUPPLIER_OTHERREMARKS = "others_remarks";
	private final String KEY_PRODUCTSUPPLIER_ACTIVITY = "activity";
	private final String KEY_PRODUCTSUPPLIER_CREATEDBY = "created_by";
	private final String KEY_PRODUCTSUPPLIER_CREATEDTIME = "created_time";
	private final String KEY_PRODUCTSUPPLIER_MODIFIEDTIME = "modified_time";

	// ===========================================================
	// Private fields
	// ===========================================================

	// private ActivityTypeCollection activityTypeRecords;zg
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public ProductSupplierTable(SQLiteDatabase database, String tableName) {
		mDb = database;
		mDatabaseTable = tableName;

	}

	private DatabaseAdapter getDBAdapter() {
		if (mDBAdapter == null)
			mDBAdapter = DatabaseAdapter.getInstance();

		return mDBAdapter;
	}

	// ===========================================================
	// Private methods
	// ===========================================================

	public List<ProductSupplierRecord> getAllRecords() {
		Cursor c = null;
		List<ProductSupplierRecord> list = new ArrayList<ProductSupplierRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_CRMNO));
					String productBrand = c.getString(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_PRODUCTBRAND));
					long supplier = c.getLong(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_SUPPLIER));
					String othersRemarks = c.getString(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_OTHERREMARKS));
					long activity = c.getLong(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_ACTIVITY));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_CREATEDBY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_PRODUCTSUPPLIER_MODIFIEDTIME));

					list.add(new ProductSupplierRecord(id, no, crmNo,
							productBrand, supplier, othersRemarks, activity,
							createdBy, createdTime, modifiedTime));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public boolean isExisting(String webID) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PRODUCTSUPPLIER_NO + "='" + webID + "'";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if ((c != null) && c.moveToFirst()) {
				exists = true;
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return exists;
	}

	public int deleteById(long[] rowIds) {

		String ids = Arrays.toString(rowIds);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "");

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_PRODUCTSUPPLIER_ROWID
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public int deleteByCrmNo(String[] no) {

		String ids = Arrays.toString(no);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "");

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_PRODUCTSUPPLIER_NO
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public long getIdByNo(String no) {
		long result = 0;
		String MY_QUERY = "SELECT " + KEY_PRODUCTSUPPLIER_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_PRODUCTSUPPLIER_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_PRODUCTSUPPLIER_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public ProductSupplierRecord getById(long ID) {
		ProductSupplierRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PRODUCTSUPPLIER_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c
						.getLong(c.getColumnIndex(KEY_PRODUCTSUPPLIER_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_CRMNO));
				String productBrand = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_PRODUCTBRAND));
				long supplier = c.getLong(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_SUPPLIER));
				String othersRemarks = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_OTHERREMARKS));
				long activity = c.getLong(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_ACTIVITY));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_CREATEDBY));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_MODIFIEDTIME));

				record = new ProductSupplierRecord(id, no, crmNo, productBrand,
						supplier, othersRemarks, activity, createdBy,
						createdTime, modifiedTime);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public String getNoById(long ID) {
		String result = null;
		String MY_QUERY = "SELECT " + KEY_PRODUCTSUPPLIER_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_PRODUCTSUPPLIER_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_PRODUCTSUPPLIER_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public ProductSupplierRecord getByWebId(String ID) {
		ProductSupplierRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PRODUCTSUPPLIER_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c
						.getLong(c.getColumnIndex(KEY_PRODUCTSUPPLIER_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_CRMNO));
				String productBrand = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_PRODUCTBRAND));
				long supplier = c.getLong(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_SUPPLIER));
				String othersRemarks = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_OTHERREMARKS));
				long activity = c.getLong(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_ACTIVITY));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_CREATEDBY));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_PRODUCTSUPPLIER_MODIFIEDTIME));

				record = new ProductSupplierRecord(id, no, crmNo, productBrand,
						supplier, othersRemarks, activity, createdBy,
						createdTime, modifiedTime);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String productBrand,
			long supplier, String othersRemarks, long activity, long createdBy,
			String createdTime, String modifiedTime) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// ActivityTypeCollection collection = getRecords();

		ContentValues args = new ContentValues();

		args.put(KEY_PRODUCTSUPPLIER_NO, no);
		args.put(KEY_PRODUCTSUPPLIER_CRMNO, crmNo);
		args.put(KEY_PRODUCTSUPPLIER_PRODUCTBRAND, productBrand);
		args.put(KEY_PRODUCTSUPPLIER_SUPPLIER, supplier);
		args.put(KEY_PRODUCTSUPPLIER_OTHERREMARKS, othersRemarks);
		args.put(KEY_PRODUCTSUPPLIER_ACTIVITY, activity);
		args.put(KEY_PRODUCTSUPPLIER_CREATEDBY, createdBy);
		args.put(KEY_PRODUCTSUPPLIER_CREATEDTIME, createdTime);
		args.put(KEY_PRODUCTSUPPLIER_MODIFIEDTIME, modifiedTime);

		long ids = mDb.insert(mDatabaseTable, null, args);
		if (ids >= 0) {
			// collection.add(ids, no, type, category, isActive, user);
			// Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_PRODUCTSUPPLIER_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo,
			String productBrand, long supplier, String othersRemarks,
			long activity, long createdBy, String createdTime,
			String modifiedTime) {
		ContentValues args = new ContentValues();
		args.put(KEY_PRODUCTSUPPLIER_NO, no);
		args.put(KEY_PRODUCTSUPPLIER_CRMNO, crmNo);
		args.put(KEY_PRODUCTSUPPLIER_PRODUCTBRAND, productBrand);
		args.put(KEY_PRODUCTSUPPLIER_SUPPLIER, supplier);
		args.put(KEY_PRODUCTSUPPLIER_OTHERREMARKS, othersRemarks);
		args.put(KEY_PRODUCTSUPPLIER_ACTIVITY, activity);
		args.put(KEY_PRODUCTSUPPLIER_CREATEDBY, createdBy);
		args.put(KEY_PRODUCTSUPPLIER_CREATEDTIME, createdTime);
		args.put(KEY_PRODUCTSUPPLIER_MODIFIEDTIME, modifiedTime);
		if (mDb.update(mDatabaseTable, args, KEY_PRODUCTSUPPLIER_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, type, category, isActive, user);
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		String MY_QUERY = "DELETE FROM " + mDatabaseTable;
		try {
			mDb.execSQL(MY_QUERY);
			// getRecords().clear();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}