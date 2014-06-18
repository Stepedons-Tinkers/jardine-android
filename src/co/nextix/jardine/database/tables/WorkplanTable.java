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
import co.nextix.jardine.database.records.WorkplanRecord;

public class WorkplanTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_WORKPLAN_ROWID = "_id";
	private final String KEY_WORKPLAN_NO = "no";
	private final String KEY_WORKPLAN_STARTDATE = "customer";
	private final String KEY_WORKPLAN_ENDDATE = "date";
	private final String KEY_WORKPLAN_STATUS = "status";
	private final String KEY_WORKPLAN_CREATEDTIME = "created_time";
	private final String KEY_WORKPLAN_MODIFIEDTIME = "modified_time";
	private final String KEY_WORKPLAN_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private WorkplanCollection workplanCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public WorkplanTable(SQLiteDatabase database, String tableName) {
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

	private List<WorkplanRecord> getAllRecords() {
		Cursor c = null;
		List<WorkplanRecord> list = new ArrayList<WorkplanRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_WORKPLAN_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_WORKPLAN_NO));
					String startDate = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_STARTDATE));
					String endDate = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_ENDDATE));
					int status = c
							.getInt(c.getColumnIndex(KEY_WORKPLAN_STATUS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_WORKPLAN_USER));

					list.add(new WorkplanRecord(id, no, startDate, endDate,
							status, createdTime, modifiedTime, user));
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
				+ KEY_WORKPLAN_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_WORKPLAN_ROWID
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public WorkplanRecord getById(int ID) {
		WorkplanRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLAN_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_WORKPLAN_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_WORKPLAN_NO));
				String startDate = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_STARTDATE));
				String endDate = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_ENDDATE));
				int status = c.getInt(c.getColumnIndex(KEY_WORKPLAN_STATUS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_WORKPLAN_USER));

				record = new WorkplanRecord(id, no, startDate, endDate, status,
						createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public WorkplanRecord getByWebId(String ID) {
		WorkplanRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLAN_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_WORKPLAN_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_WORKPLAN_NO));
				String startDate = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_STARTDATE));
				String endDate = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_ENDDATE));
				int status = c.getInt(c.getColumnIndex(KEY_WORKPLAN_STATUS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_WORKPLAN_USER));

				record = new WorkplanRecord(id, no, startDate, endDate, status,
						createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, String startDate, String endDate,
			int status, String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		WorkplanCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_WORKPLAN_NO, no);
		initialValues.put(KEY_WORKPLAN_STARTDATE, startDate);
		initialValues.put(KEY_WORKPLAN_ENDDATE, endDate);
		initialValues.put(KEY_WORKPLAN_STATUS, status);
		initialValues.put(KEY_WORKPLAN_CREATEDTIME, createdTime);
		initialValues.put(KEY_WORKPLAN_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_WORKPLAN_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, startDate, endDate, status, createdTime,
					modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_WORKPLAN_ROWID + "=" + rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, String startDate,
			String endDate, int status, String createdTime,
			String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_WORKPLAN_NO, no);
		args.put(KEY_WORKPLAN_STARTDATE, startDate);
		args.put(KEY_WORKPLAN_ENDDATE, endDate);
		args.put(KEY_WORKPLAN_STATUS, status);
		args.put(KEY_WORKPLAN_CREATEDTIME, createdTime);
		args.put(KEY_WORKPLAN_MODIFIEDTIME, modifiedTime);
		args.put(KEY_WORKPLAN_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_WORKPLAN_ROWID + "=" + id,
				null) > 0) {
			getRecords().update(id, no, startDate, endDate, status,
					createdTime, modifiedTime, user);
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

	public WorkplanCollection getRecords() {
		if (workplanCollection == null) {
			workplanCollection = new WorkplanCollection();
			workplanCollection.list = getAllRecords();
		}
		return workplanCollection;
	}

	public final class WorkplanCollection implements Iterable<WorkplanRecord> {

		private List<WorkplanRecord> list;

		private WorkplanCollection() {
		}

		public int size() {
			return list.size();
		}

		public WorkplanRecord get(int i) {
			return list.get(i);
		}

		public WorkplanRecord getById(long id) {
			for (WorkplanRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, String startDate, String endDate,
				int status, String createdTime, String modifiedTime, long user) {
			list.add(new WorkplanRecord(id, no, startDate, endDate, status,
					createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, String startDate,
				String endDate, int status, String createdTime,
				String modifiedTime, long user) {
			WorkplanRecord record = getById(id);
			record.setNo(no);
			record.setStartDate(startDate);
			record.setEndDate(endDate);
			record.setStatus(status);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<WorkplanRecord> iterator() {
			Iterator<WorkplanRecord> iter = new Iterator<WorkplanRecord>() {
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
				public WorkplanRecord next() {
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
