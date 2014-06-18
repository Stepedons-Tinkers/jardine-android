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
import co.nextix.jardine.database.records.MarketingIntelRecord;

public class MarketingIntelTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_MARKETINGINTEL_ROWID = "_id";
	private final String KEY_MARKETINGINTEL_NO = "no";
	private final String KEY_MARKETINGINTEL_ACTIVITY = "activity";
	private final String KEY_MARKETINGINTEL_COMPETITOR = "competitor";
	private final String KEY_MARKETINGINTEL_DETAILS = "details";
	private final String KEY_MARKETINGINTEL_REMARKS = "remarks";
	private final String KEY_MARKETINGINTEL_CREATEDTIME = "created_time";
	private final String KEY_MARKETINGINTEL_MODIFIEDTIME = "modified_time";
	private final String KEY_MARKETINGINTEL_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private MarketingIntelCollection marketingIntelCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public MarketingIntelTable(SQLiteDatabase database, String tableName) {
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

	private List<MarketingIntelRecord> getAllRecords() {
		Cursor c = null;
		List<MarketingIntelRecord> list = new ArrayList<MarketingIntelRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_NO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_ACTIVITY));
					long competitor = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_COMPETITOR));
					String details = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_DETAILS));
					String remarks = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_REMARKS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_USER));

					list.add(new MarketingIntelRecord(id, no, activity,
							competitor, details, remarks, createdTime,
							modifiedTime, user));
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
				+ KEY_MARKETINGINTEL_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_MARKETINGINTEL_ROWID
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public MarketingIntelRecord getById(int ID) {
		MarketingIntelRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGINTEL_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_MARKETINGINTEL_ROWID));
				String no = c
						.getString(c.getColumnIndex(KEY_MARKETINGINTEL_NO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_ACTIVITY));
				long competitor = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_COMPETITOR));
				String details = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_DETAILS));
				String remarks = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_REMARKS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_MODIFIEDTIME));
				long user = c
						.getLong(c.getColumnIndex(KEY_MARKETINGINTEL_USER));

				record = new MarketingIntelRecord(id, no, activity, competitor,
						details, remarks, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public MarketingIntelRecord getByWebId(String ID) {
		MarketingIntelRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGINTEL_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_MARKETINGINTEL_ROWID));
				String no = c
						.getString(c.getColumnIndex(KEY_MARKETINGINTEL_NO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_ACTIVITY));
				long competitor = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_COMPETITOR));
				String details = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_DETAILS));
				String remarks = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_REMARKS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_MODIFIEDTIME));
				long user = c
						.getLong(c.getColumnIndex(KEY_MARKETINGINTEL_USER));

				record = new MarketingIntelRecord(id, no, activity, competitor,
						details, remarks, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, long activity, long competitor,
			String details, String remarks, String createdTime,
			String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		MarketingIntelCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_MARKETINGINTEL_NO, no);
		initialValues.put(KEY_MARKETINGINTEL_ACTIVITY, activity);
		initialValues.put(KEY_MARKETINGINTEL_COMPETITOR, competitor);
		initialValues.put(KEY_MARKETINGINTEL_DETAILS, details);
		initialValues.put(KEY_MARKETINGINTEL_REMARKS, remarks);
		initialValues.put(KEY_MARKETINGINTEL_CREATEDTIME, createdTime);
		initialValues.put(KEY_MARKETINGINTEL_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_MARKETINGINTEL_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, activity, competitor, details, remarks,
					createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_MARKETINGINTEL_ROWID + "=" + rowId,
				null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, long activity,
			long competitor, String details, String remarks,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_MARKETINGINTEL_NO, no);
		args.put(KEY_MARKETINGINTEL_ACTIVITY, activity);
		args.put(KEY_MARKETINGINTEL_COMPETITOR, competitor);
		args.put(KEY_MARKETINGINTEL_DETAILS, details);
		args.put(KEY_MARKETINGINTEL_REMARKS, remarks);
		args.put(KEY_MARKETINGINTEL_CREATEDTIME, createdTime);
		args.put(KEY_MARKETINGINTEL_MODIFIEDTIME, modifiedTime);
		args.put(KEY_MARKETINGINTEL_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_MARKETINGINTEL_ROWID + "="
				+ id, null) > 0) {
			getRecords().update(id, no, activity, competitor, details, remarks,
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

	public MarketingIntelCollection getRecords() {
		if (marketingIntelCollection == null) {
			marketingIntelCollection = new MarketingIntelCollection();
			marketingIntelCollection.list = getAllRecords();
		}
		return marketingIntelCollection;
	}

	public final class MarketingIntelCollection implements
			Iterable<MarketingIntelRecord> {

		private List<MarketingIntelRecord> list;

		private MarketingIntelCollection() {
		}

		public int size() {
			return list.size();
		}

		public MarketingIntelRecord get(int i) {
			return list.get(i);
		}

		public MarketingIntelRecord getById(long id) {
			for (MarketingIntelRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, long activity, long competitor,
				String details, String remarks, String createdTime,
				String modifiedTime, long user) {
			list.add(new MarketingIntelRecord(id, no, activity, competitor,
					details, remarks, createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, long activity, long competitor,
				String details, String remarks, String createdTime,
				String modifiedTime, long user) {
			MarketingIntelRecord record = getById(id);
			record.setNo(no);
			record.setActivity(activity);
			record.setCompetitor(competitor);
			record.setDetails(details);
			record.setRemarks(remarks);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<MarketingIntelRecord> iterator() {
			Iterator<MarketingIntelRecord> iter = new Iterator<MarketingIntelRecord>() {
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
				public MarketingIntelRecord next() {
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
