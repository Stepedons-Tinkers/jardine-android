package co.nextix.jardine.database.tables.picklists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.PicklistRecord;

public class PActtypeTypeTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_ACTIVITYTYPE_TYPE_ROWID = "_id";
	private final String KEY_ACTIVITYTYPE_TYPE_NAME = "name";
	private final String KEY_ACTIVITYTYPE_TYPE_CATEGORY = "activity_category";

	// ===========================================================
	// Private fields
	// ===========================================================

	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public PActtypeTypeTable(SQLiteDatabase database, String tableName) {
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

	private List<PicklistRecord> getAllRecords() {
		Cursor c = null;
		List<PicklistRecord> list = new ArrayList<PicklistRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_ACTIVITYTYPE_TYPE_ROWID));
					String name = c.getString(c
							.getColumnIndex(KEY_ACTIVITYTYPE_TYPE_NAME));

					list.add(new PicklistRecord(id, name));
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

	public int deleteById(long[] rowIds) {

		String ids = Arrays.toString(rowIds);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "");

		int rowsDeleted = mDb.delete(mDatabaseTable,
				KEY_ACTIVITYTYPE_TYPE_ROWID + " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public PicklistRecord getById(int ID) {
		PicklistRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITYTYPE_TYPE_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_ACTIVITYTYPE_TYPE_ROWID));
				String name = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_TYPE_NAME));

				record = new PicklistRecord(id, name);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, long category) {
		// ActivityTypeCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_ACTIVITYTYPE_TYPE_NAME, no);
		initialValues.put(KEY_ACTIVITYTYPE_TYPE_CATEGORY, category);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, category, isActive, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_ACTIVITYTYPE_TYPE_ROWID + "="
				+ rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, long category) {
		ContentValues args = new ContentValues();
		args.put(KEY_ACTIVITYTYPE_TYPE_NAME, no);
		args.put(KEY_ACTIVITYTYPE_TYPE_CATEGORY, category);
		if (mDb.update(mDatabaseTable, args, KEY_ACTIVITYTYPE_TYPE_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, category, isActive, user);
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
