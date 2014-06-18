package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.ActivityTypeRecord;

public class ActivityTypeTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_ACTIVITYTYPE_ROWID = "_id";
	private final String KEY_ACTIVITYTYPE_NO = "no";
	private final String KEY_ACTIVITYTYPE_TYPE = "activity_type";
	private final String KEY_ACTIVITYTYPE_CATEGORY = "activity_type_categorization";
	private final String KEY_ACTIVITYTYPE_ISACTIVE = "is_active";
	private final String KEY_ACTIVITYTYPE_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private ActivityTypeCollection activityTypeRecords;
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

	private List<ActivityTypeRecord> getAllRecords() {
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
					long type = c.getLong(c
							.getColumnIndex(KEY_ACTIVITYTYPE_TYPE));
					long category = c.getLong(c
							.getColumnIndex(KEY_ACTIVITYTYPE_CATEGORY));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_ACTIVITYTYPE_ISACTIVE));
					long user = c.getLong(c
							.getColumnIndex(KEY_ACTIVITYTYPE_USER));

					list.add(new ActivityTypeRecord(id, no, type, category,
							isActive, user));
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

	public ActivityTypeRecord getById(int ID) {
		ActivityTypeRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITYTYPE_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_ACTIVITYTYPE_NO));
				long type = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_TYPE));
				long category = c.getLong(c
						.getColumnIndex(KEY_ACTIVITYTYPE_CATEGORY));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_ACTIVITYTYPE_ISACTIVE));
				long user = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_USER));

				record = new ActivityTypeRecord(id, no, type, category,
						isActive, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
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
				long type = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_TYPE));
				long category = c.getLong(c
						.getColumnIndex(KEY_ACTIVITYTYPE_CATEGORY));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_ACTIVITYTYPE_ISACTIVE));
				long user = c.getLong(c.getColumnIndex(KEY_ACTIVITYTYPE_USER));

				record = new ActivityTypeRecord(id, no, type, category,
						isActive, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, long type, long category, int isActive,
			long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		ActivityTypeCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_ACTIVITYTYPE_NO, no);
		initialValues.put(KEY_ACTIVITYTYPE_TYPE, type);
		initialValues.put(KEY_ACTIVITYTYPE_CATEGORY, category);
		initialValues.put(KEY_ACTIVITYTYPE_ISACTIVE, isActive);
		initialValues.put(KEY_ACTIVITYTYPE_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, type, category, isActive, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_ACTIVITYTYPE_ROWID + "=" + rowId,
				null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, long type, long category,
			int isActive, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_ACTIVITYTYPE_NO, no);
		args.put(KEY_ACTIVITYTYPE_TYPE, type);
		args.put(KEY_ACTIVITYTYPE_CATEGORY, category);
		args.put(KEY_ACTIVITYTYPE_ISACTIVE, isActive);
		args.put(KEY_ACTIVITYTYPE_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_ACTIVITYTYPE_ROWID + "=" + id,
				null) > 0) {
			getRecords().update(id, no, type, category, isActive, user);
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		String MY_QUERY = "DELETE FROM " + mDatabaseTable;
		try {
			mDb.execSQL(MY_QUERY);
			getRecords().clear();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ===========================================================
	// Public Foreign Key Methods
	// ===========================================================

	// ===========================================================
	// Collection
	// ===========================================================

	public ActivityTypeCollection getRecords() {
		if (activityTypeRecords == null) {
			activityTypeRecords = new ActivityTypeCollection();
			activityTypeRecords.list = getAllRecords();
		}
		return activityTypeRecords;
	}

	public final class ActivityTypeCollection implements
			Iterable<ActivityTypeRecord> {

		private List<ActivityTypeRecord> list;

		private ActivityTypeCollection() {
		}

		public int size() {
			return list.size();
		}

		public ActivityTypeRecord get(int i) {
			return list.get(i);
		}

		public ActivityTypeRecord getById(long id) {
			for (ActivityTypeRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, long type, long category,
				int isActive, long user) {
			list.add(new ActivityTypeRecord(id, no, type, category, isActive,
					user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, long type, long category,
				int isActive, long user) {
			ActivityTypeRecord record = getById(id);
			record.setNo(no);
			record.setActivityType(type);
			record.setActivityTypeCategorization(category);
			record.setIsActive(isActive);
			record.setUser(user);
		}

		@Override
		public Iterator<ActivityTypeRecord> iterator() {
			Iterator<ActivityTypeRecord> iter = new Iterator<ActivityTypeRecord>() {
				private int current = 0;

				@Override
				public void remove() {
					if (list.size() > 0) {
						deleteUser(list.get(current).getId());
						deleteById(list.get(current).getId());
						list.remove(current);
					}
				}

				@Override
				public ActivityTypeRecord next() {
					if (list.size() > 0) {
						return list.get(current++);
					}
					return null;
				}

				@Override
				public boolean hasNext() {
					return list.size() > 0 && current < list.size();
				}
			};
			return iter;
		}
	}
}
