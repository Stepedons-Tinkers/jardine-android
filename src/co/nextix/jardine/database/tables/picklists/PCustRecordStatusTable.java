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

public class PCustRecordStatusTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_CUSTOMER_RECORDSTATUS_ROWID = "_id";
	private final String KEY_CUSTOMER_RECORDSTATUS_NAME = "name";

	// ===========================================================
	// Private fields
	// ===========================================================

	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public PCustRecordStatusTable(SQLiteDatabase database, String tableName) {
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

	public List<PicklistRecord> getAllRecords() {
		Cursor c = null;
		List<PicklistRecord> list = new ArrayList<PicklistRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_RECORDSTATUS_ROWID));
					String name = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_RECORDSTATUS_NAME));

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

	public boolean isExisting(String name) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMER_RECORDSTATUS_NAME + "='" + name + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable,
				KEY_CUSTOMER_RECORDSTATUS_ROWID + " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public PicklistRecord getById(long ID) {
		PicklistRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMER_RECORDSTATUS_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_RECORDSTATUS_ROWID));
				String name = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_RECORDSTATUS_NAME));

				record = new PicklistRecord(id, name);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public PicklistRecord getByName(String name) {
		PicklistRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMER_RECORDSTATUS_NAME + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(name) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_RECORDSTATUS_ROWID));
				String sname = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_RECORDSTATUS_NAME));

				record = new PicklistRecord(id, sname);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public String getNameById(long ID) {
		String result = null;
		String MY_QUERY = "SELECT " + KEY_CUSTOMER_RECORDSTATUS_NAME + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_CUSTOMER_RECORDSTATUS_ROWID
				+ "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_RECORDSTATUS_NAME));
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
		String MY_QUERY = "SELECT " + KEY_CUSTOMER_RECORDSTATUS_ROWID
				+ " FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMER_RECORDSTATUS_NAME + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(name) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_RECORDSTATUS_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public long insert(String no) {
		// ActivityTypeCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_CUSTOMER_RECORDSTATUS_NAME, no);

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
		if (mDb.delete(mDatabaseTable, KEY_CUSTOMER_RECORDSTATUS_ROWID + "="
				+ rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String name) {
		ContentValues args = new ContentValues();
		args.put(KEY_CUSTOMER_RECORDSTATUS_NAME, name);
		if (mDb.update(mDatabaseTable, args, KEY_CUSTOMER_RECORDSTATUS_ROWID
				+ "=" + id, null) > 0) {
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
