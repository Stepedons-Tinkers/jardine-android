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

public class PAreaTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_AREA_ROWID = "_id";
	private final String KEY_AREA_NAME = "name";

	// ===========================================================
	// Private fields
	// ===========================================================

	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public PAreaTable(SQLiteDatabase database, String tableName) {
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
					long id = c.getLong(c.getColumnIndex(KEY_AREA_ROWID));
					String name = c.getString(c.getColumnIndex(KEY_AREA_NAME));

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

	public List<PicklistRecord> getRecords() {
		Cursor c = null;
		List<PicklistRecord> list = new ArrayList<PicklistRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_AREA_ROWID));
					String name = c.getString(c.getColumnIndex(KEY_AREA_NAME));

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

	public int deleteById(long[] rowIds) {

		String ids = Arrays.toString(rowIds);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "");

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_AREA_ROWID + " IN ("
				+ ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public boolean isExisting(String name) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_AREA_NAME + "='" + name + "'";
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

	public String getNameById(long ID) {
		String result = null;
		String MY_QUERY = "SELECT " + KEY_AREA_NAME + " FROM " + mDatabaseTable
				+ " WHERE " + KEY_AREA_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_AREA_NAME));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public long getIdByName(String name) {
		long result = 0;
		String MY_QUERY = "SELECT " + KEY_AREA_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_AREA_NAME + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(name) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_AREA_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public PicklistRecord getById(int ID) {
		PicklistRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_AREA_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_AREA_ROWID));
				String name = c.getString(c.getColumnIndex(KEY_AREA_NAME));

				record = new PicklistRecord(id, name);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertArea(String name) {
		// ActivityTypeCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_AREA_NAME, name);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, category, isActive, user);
			Log.i("WEB", "DB insert " + name);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteArea(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_AREA_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateArea(long id, String name) {
		ContentValues args = new ContentValues();
		args.put(KEY_AREA_NAME, name);
		if (mDb.update(mDatabaseTable, args, KEY_AREA_ROWID + "=" + id, null) > 0) {
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
