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
import co.nextix.jardine.database.records.SMRtimeCardRecord;

public class SMRtimeCardTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_SMRTIMECARD_ROWID = "_id";
	private final String KEY_SMRTIMECARD_NO = "no";
	private final String KEY_SMRTIMECARD_DATE = "date";
	private final String KEY_SMRTIMECARD_TIMESTAMP = "timestamp";
	private final String KEY_SMRTIMECARD_ENTRYTYPE = "entry_type";
	private final String KEY_SMRTIMECARD_CREATEDTIME = "created_time";
	private final String KEY_SMRTIMECARD_MODIFIEDTIME = "modified_time";
	private final String KEY_SMRTIMECARD_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private SMRtimeCardCollection smrTimeCardCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public SMRtimeCardTable(SQLiteDatabase database, String tableName) {
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

	private List<SMRtimeCardRecord> getAllRecords() {
		Cursor c = null;
		List<SMRtimeCardRecord> list = new ArrayList<SMRtimeCardRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c
							.getLong(c.getColumnIndex(KEY_SMRTIMECARD_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_SMRTIMECARD_NO));
					String date = c.getString(c
							.getColumnIndex(KEY_SMRTIMECARD_DATE));
					String timestamp = c.getString(c
							.getColumnIndex(KEY_SMRTIMECARD_TIMESTAMP));
					long entryType = c.getLong(c
							.getColumnIndex(KEY_SMRTIMECARD_ENTRYTYPE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_SMRTIMECARD_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_SMRTIMECARD_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_SMRTIMECARD_USER));

					list.add(new SMRtimeCardRecord(id, no, date, timestamp,
							entryType, createdTime, modifiedTime, user));
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
				+ KEY_SMRTIMECARD_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_SMRTIMECARD_ROWID
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public SMRtimeCardRecord getById(int ID) {
		SMRtimeCardRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SMRTIMECARD_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_SMRTIMECARD_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_SMRTIMECARD_NO));
				String date = c.getString(c
						.getColumnIndex(KEY_SMRTIMECARD_DATE));
				String timestamp = c.getString(c
						.getColumnIndex(KEY_SMRTIMECARD_TIMESTAMP));
				long entryType = c.getLong(c
						.getColumnIndex(KEY_SMRTIMECARD_ENTRYTYPE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_SMRTIMECARD_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_SMRTIMECARD_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_SMRTIMECARD_USER));

				record = new SMRtimeCardRecord(id, no, date, timestamp,
						entryType, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public SMRtimeCardRecord getByWebId(String ID) {
		SMRtimeCardRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SMRTIMECARD_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_SMRTIMECARD_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_SMRTIMECARD_NO));
				String date = c.getString(c
						.getColumnIndex(KEY_SMRTIMECARD_DATE));
				String timestamp = c.getString(c
						.getColumnIndex(KEY_SMRTIMECARD_TIMESTAMP));
				long entryType = c.getLong(c
						.getColumnIndex(KEY_SMRTIMECARD_ENTRYTYPE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_SMRTIMECARD_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_SMRTIMECARD_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_SMRTIMECARD_USER));

				record = new SMRtimeCardRecord(id, no, date, timestamp,
						entryType, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, String date, String timestamp,
			long entryType, String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		SMRtimeCardCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_SMRTIMECARD_NO, no);
		initialValues.put(KEY_SMRTIMECARD_DATE, date);
		initialValues.put(KEY_SMRTIMECARD_TIMESTAMP, timestamp);
		initialValues.put(KEY_SMRTIMECARD_ENTRYTYPE, entryType);
		initialValues.put(KEY_SMRTIMECARD_CREATEDTIME, createdTime);
		initialValues.put(KEY_SMRTIMECARD_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_SMRTIMECARD_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, date, timestamp, entryType, createdTime,
					modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_SMRTIMECARD_ROWID + "=" + rowId,
				null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, String date,
			String timestamp, long entryType, String createdTime,
			String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_SMRTIMECARD_NO, no);
		args.put(KEY_SMRTIMECARD_DATE, date);
		args.put(KEY_SMRTIMECARD_TIMESTAMP, timestamp);
		args.put(KEY_SMRTIMECARD_ENTRYTYPE, entryType);
		args.put(KEY_SMRTIMECARD_CREATEDTIME, createdTime);
		args.put(KEY_SMRTIMECARD_MODIFIEDTIME, modifiedTime);
		args.put(KEY_SMRTIMECARD_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_SMRTIMECARD_ROWID + "=" + id,
				null) > 0) {
			getRecords().update(id, no, date, timestamp, entryType,
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

	public SMRtimeCardCollection getRecords() {
		if (smrTimeCardCollection == null) {
			smrTimeCardCollection = new SMRtimeCardCollection();
			smrTimeCardCollection.list = getAllRecords();
		}
		return smrTimeCardCollection;
	}

	public final class SMRtimeCardCollection implements
			Iterable<SMRtimeCardRecord> {

		private List<SMRtimeCardRecord> list;

		private SMRtimeCardCollection() {
		}

		public int size() {
			return list.size();
		}

		public SMRtimeCardRecord get(int i) {
			return list.get(i);
		}

		public SMRtimeCardRecord getById(long id) {
			for (SMRtimeCardRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, String date, String timestamp,
				long entryType, String createdTime, String modifiedTime,
				long user) {
			list.add(new SMRtimeCardRecord(id, no, date, timestamp, entryType,
					createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, String date, String timestamp,
				long entryType, String createdTime, String modifiedTime,
				long user) {
			SMRtimeCardRecord record = getById(id);
			record.setNo(no);
			record.setDate(date);
			record.setTimestamp(timestamp);
			record.setEntryType(entryType);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<SMRtimeCardRecord> iterator() {
			Iterator<SMRtimeCardRecord> iter = new Iterator<SMRtimeCardRecord>() {
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
				public SMRtimeCardRecord next() {
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
