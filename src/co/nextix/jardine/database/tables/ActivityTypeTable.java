package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.DatabaseAdapter;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_NAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_CATEGORY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_ISACTIVE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITYTYPE_USER;
import co.nextix.jardine.database.records.ActivityTypeRecord;

public class ActivityTypeTable {

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

	public ActivityTypeTable(SQLiteDatabase database, String tableName) {
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

	public List<ActivityTypeRecord> getAllRecords() {
		Cursor c = null;
		List<ActivityTypeRecord> list = new ArrayList<ActivityTypeRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_ACTIVITYTYPE_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_ACTIVITYTYPE_NO));
					// long type = c.getLong(c
					// .getColumnIndex(KEY_ACTIVITYTYPE_TYPE));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_ACTIVITYTYPE_CRMNO));
					String name = c.getString(c
							.getColumnIndex(KEY_ACTIVITYTYPE_NAME));
					long category = c.getLong(c
							.getColumnIndex(KEY_ACTIVITYTYPE_CATEGORY));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_ACTIVITYTYPE_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITYTYPE_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITYTYPE_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_ACTIVITYTYPE_USER));

					list.add(new ActivityTypeRecord(id, no, crmNo, name,
							category, isActive, createdTime, modifiedTime, user));
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
				+ KEY_ACTIVITYTYPE_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_ACTIVITYTYPE_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_ACTIVITYTYPE_NO
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
		String MY_QUERY = "SELECT " + KEY_ACTIVITYTYPE_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_ACTIVITYTYPE_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public ActivityTypeRecord getById(long ID) {
		ActivityTypeRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITYTYPE_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_ACTIVITYTYPE_NO));
				// long type =
				// c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_TYPE));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_CRMNO));
				String name = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_NAME));
				long category = c.getLong(c
						.getColumnIndex(KEY_ACTIVITYTYPE_CATEGORY));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_ACTIVITYTYPE_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_USER));

				record = new ActivityTypeRecord(id, no, crmNo, name, category,
						isActive, createdTime, modifiedTime, user);
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
		String MY_QUERY = "SELECT " + KEY_ACTIVITYTYPE_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_ACTIVITYTYPE_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_ACTIVITYTYPE_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public ActivityTypeRecord getByWebId(String ID) {
		ActivityTypeRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITYTYPE_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_ACTIVITYTYPE_NO));
				// long type =
				// c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_TYPE));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_CRMNO));
				String name = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_NAME));
				long category = c.getLong(c
						.getColumnIndex(KEY_ACTIVITYTYPE_CATEGORY));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_ACTIVITYTYPE_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITYTYPE_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_USER));

				record = new ActivityTypeRecord(id, no, crmNo, name, category,
						isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String name, long category,
			int isActive, String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// ActivityTypeCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_ACTIVITYTYPE_NO, no);
		initialValues.put(KEY_ACTIVITYTYPE_CRMNO, crmNo);
		// initialValues.put(KEY_ACTIVITYTYPE_TYPE, type);
		initialValues.put(KEY_ACTIVITYTYPE_NAME, name);
		Log.w(JardineApp.TAG, "name: " + name);
		initialValues.put(KEY_ACTIVITYTYPE_CATEGORY, category);
		initialValues.put(KEY_ACTIVITYTYPE_ISACTIVE, isActive);
		initialValues.put(KEY_ACTIVITYTYPE_CREATEDTIME, createdTime);
		initialValues.put(KEY_ACTIVITYTYPE_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_ACTIVITYTYPE_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, type, category, isActive, user);
//			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_ACTIVITYTYPE_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, String name,
			long category, int isActive, String createdTime,
			String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_ACTIVITYTYPE_NO, no);
		// args.put(KEY_ACTIVITYTYPE_TYPE, type);
		args.put(KEY_ACTIVITYTYPE_NAME, name);
		args.put(KEY_ACTIVITYTYPE_CRMNO, crmNo);
		args.put(KEY_ACTIVITYTYPE_CATEGORY, category);
		args.put(KEY_ACTIVITYTYPE_ISACTIVE, isActive);
		args.put(KEY_ACTIVITYTYPE_CREATEDTIME, createdTime);
		args.put(KEY_ACTIVITYTYPE_MODIFIEDTIME, modifiedTime);
		args.put(KEY_ACTIVITYTYPE_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_ACTIVITYTYPE_ROWID + "=" + id,
				null) > 0) {
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
