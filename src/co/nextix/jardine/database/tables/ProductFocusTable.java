package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.ProductFocusRecord;

public class ProductFocusTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_PRODUCTFOCUS_ROWID = "_id";
	private final String KEY_PRODUCTFOCUS_PRODUCT = "product";
	private final String KEY_PRODUCTFOCUS_ACTIVITY = "activity";

	// ===========================================================
	// Private fields
	// ===========================================================

	// private WorkplanCollection workplanCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public ProductFocusTable(SQLiteDatabase database, String tableName) {
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

	// ===========================================================
	// Public methods
	// ===========================================================

	public List<ProductFocusRecord> getAllRecords() {
		Cursor c = null;
		List<ProductFocusRecord> list = new ArrayList<ProductFocusRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_PRODUCTFOCUS_ROWID));
					long product = c.getLong(c
							.getColumnIndex(KEY_PRODUCTFOCUS_PRODUCT));
					long activity = c.getLong(c
							.getColumnIndex(KEY_PRODUCTFOCUS_ACTIVITY));

					list.add(new ProductFocusRecord(id, product, activity));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	// public boolean isExisting(String webID) {
	// boolean exists = false;
	// String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
	// + KEY_PRODUCTFOCUS_PRODUCT + "='" + webID + "'";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, null);
	//
	// if ((c != null) && c.moveToFirst()) {
	// exists = true;
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	// return exists;
	// }

	public int deleteById(long[] rowIds) {

		String ids = Arrays.toString(rowIds);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "");

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_PRODUCTFOCUS_ROWID
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	// public int deleteByCrmNo(String[] no) {
	//
	// String ids = Arrays.toString(no);
	//
	// if (ids == null) {
	// return 0;
	// }
	//
	// // Remove the surrounding bracket([]) created by the method
	// // Arrays.toString()
	// ids = ids.replace("[", "").replace("]", "");
	//
	// int rowsDeleted = mDb.delete(mDatabaseTable, KEY_PRODUCTFOCUS_PRODUCT
	// + " IN (" + ids + ")", null);
	//
	// // if (rowsDeleted > 0) {
	// //
	// // // Delete the calls that are referring to the deleted work plan
	// // getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
	// // }
	//
	// return rowsDeleted;
	// }

	// public long getIdByNo(String no) {
	// long result = 0;
	// String MY_QUERY = "SELECT " + KEY_PRODUCTFOCUS_ROWID + " FROM "
	// + mDatabaseTable + " WHERE " + KEY_PRODUCTFOCUS_PRODUCT + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// result = c.getLong(c.getColumnIndex(KEY_PRODUCTFOCUS_ROWID));
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	//
	// return result;
	// }

	public ProductFocusRecord getById(long ID) {
		ProductFocusRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PRODUCTFOCUS_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_PRODUCTFOCUS_ROWID));
				long product = c.getLong(c
						.getColumnIndex(KEY_PRODUCTFOCUS_PRODUCT));
				long activity = c.getLong(c
						.getColumnIndex(KEY_PRODUCTFOCUS_ACTIVITY));

				record = new ProductFocusRecord(id, product, activity);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	// public String getNoById(long ID) {
	// String result = null;
	// String MY_QUERY = "SELECT " + KEY_PRODUCTFOCUS_PRODUCT + " FROM "
	// + mDatabaseTable + " WHERE " + KEY_PRODUCTFOCUS_ROWID + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// result = c
	// .getString(c.getColumnIndex(KEY_PRODUCTFOCUS_PRODUCT));
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	//
	// return result;
	// }
	//
	// public ProductFocusRecord getByWebId(String ID) {
	// ProductFocusRecord record = null;
	// String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
	// + KEY_PRODUCTFOCUS_PRODUCT + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// long id = c.getLong(c.getColumnIndex(KEY_PRODUCTFOCUS_ROWID));
	// String no = c.getString(c
	// .getColumnIndex(KEY_PRODUCTFOCUS_PRODUCT));
	// String crmNo = c.getString(c
	// .getColumnIndex(KEY_PRODUCTFOCUS_ACTIVITY));
	// String startDate = c.getString(c
	// .getColumnIndex(KEY_PRODUCTFOCUS_FROMDATE));
	// String endDate = c.getString(c
	// .getColumnIndex(KEY_PRODUCTFOCUS_TODATE));
	// // int status =
	// // c.getInt(c.getColumnIndex(KEY_PRODUCTFOCUS_STATUS));
	// String createdTime = c.getString(c
	// .getColumnIndex(KEY_PRODUCTFOCUS_CREATEDTIME));
	// String modifiedTime = c.getString(c
	// .getColumnIndex(KEY_PRODUCTFOCUS_MODIFIEDTIME));
	// long user = c.getLong(c
	// .getColumnIndex(KEY_PRODUCTFOCUS_CREATEDBY));
	//
	// // record = new ProductFocusRecord(id, no, startDate, endDate,
	// // status,
	// // createdTime, modifiedTime, user);
	// record = new ProductFocusRecord(id, no, crmNo, startDate, endDate,
	// createdTime, modifiedTime, user);
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	//
	// return record;
	// }

	public long insert(long product, long activity) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// WorkplanCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_PRODUCTFOCUS_PRODUCT, product);
		initialValues.put(KEY_PRODUCTFOCUS_ACTIVITY, activity);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, startDate, endDate, status, createdTime,
			// modifiedTime, user);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_PRODUCTFOCUS_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, long product, long activity) {
		ContentValues args = new ContentValues();
		args.put(KEY_PRODUCTFOCUS_PRODUCT, product);
		args.put(KEY_PRODUCTFOCUS_ACTIVITY, activity);
		if (mDb.update(mDatabaseTable, args, KEY_PRODUCTFOCUS_ROWID + "=" + id,
				null) > 0) {
			// getRecords().update(id, no, startDate, endDate, status,
			// createdTime, modifiedTime, user);
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
