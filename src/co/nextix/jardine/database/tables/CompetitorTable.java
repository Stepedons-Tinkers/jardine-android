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
import co.nextix.jardine.database.records.CompetitorRecord;

public class CompetitorTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_COMPETITOR_ROWID = "_id";
	private final String KEY_COMPETITOR_NO = "no";
	private final String KEY_COMPETITOR_NAME = "competitor_name";
	private final String KEY_COMPETITOR_ISACTIVE = "is_active";
	private final String KEY_COMPETITOR_CREATEDTIME = "created_time";
	private final String KEY_COMPETITOR_MODIFIEDTIME = "modified_time";
	private final String KEY_COMPETITOR_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private CompetitorCollection competitorRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public CompetitorTable(SQLiteDatabase database, String tableName) {
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

	private List<CompetitorRecord> getAllRecords() {
		Cursor c = null;
		List<CompetitorRecord> list = new ArrayList<CompetitorRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_COMPETITOR_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_COMPETITOR_NO));
					String competitorName = c.getString(c
							.getColumnIndex(KEY_COMPETITOR_NAME));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_COMPETITOR_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_COMPETITOR_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_COMPETITOR_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_COMPETITOR_USER));

					list.add(new CompetitorRecord(id, no, competitorName,
							isActive, createdTime, modifiedTime, user));
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
				+ KEY_COMPETITOR_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_COMPETITOR_ROWID
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public CompetitorRecord getById(int ID) {
		CompetitorRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_COMPETITOR_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c
						.getLong(c.getColumnIndex(KEY_COMPETITOR_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_COMPETITOR_NO));
				String competitorName = c.getString(c
						.getColumnIndex(KEY_COMPETITOR_NAME));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_COMPETITOR_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_COMPETITOR_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_COMPETITOR_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_COMPETITOR_USER));

				record = new CompetitorRecord(id, no, competitorName, isActive,
						createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public CompetitorRecord getByWebId(String ID) {
		CompetitorRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_COMPETITOR_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c
						.getLong(c.getColumnIndex(KEY_COMPETITOR_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_COMPETITOR_NO));
				String competitorName = c.getString(c
						.getColumnIndex(KEY_COMPETITOR_NAME));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_COMPETITOR_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_COMPETITOR_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_COMPETITOR_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_COMPETITOR_USER));

				record = new CompetitorRecord(id, no, competitorName, isActive,
						createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, String competitorName, int isActive,
			String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		CompetitorCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_COMPETITOR_NO, no);
		initialValues.put(KEY_COMPETITOR_NAME, competitorName);
		initialValues.put(KEY_COMPETITOR_ISACTIVE, isActive);
		initialValues.put(KEY_COMPETITOR_CREATEDTIME, createdTime);
		initialValues.put(KEY_COMPETITOR_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_COMPETITOR_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, competitorName, isActive, createdTime,
					modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_COMPETITOR_ROWID + "=" + rowId,
				null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, String competitorName,
			int isActive, String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_COMPETITOR_NO, no);
		args.put(KEY_COMPETITOR_NAME, competitorName);
		args.put(KEY_COMPETITOR_ISACTIVE, isActive);
		args.put(KEY_COMPETITOR_CREATEDTIME, createdTime);
		args.put(KEY_COMPETITOR_MODIFIEDTIME, modifiedTime);
		args.put(KEY_COMPETITOR_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_COMPETITOR_ROWID + "="
				+ id, null) > 0) {
			getRecords().update(id, no, competitorName, isActive, createdTime,
					modifiedTime, user);
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

	public CompetitorCollection getRecords() {
		if (competitorRecords == null) {
			competitorRecords = new CompetitorCollection();
			competitorRecords.list = getAllRecords();
		}
		return competitorRecords;
	}

	public final class CompetitorCollection implements
			Iterable<CompetitorRecord> {

		private List<CompetitorRecord> list;

		private CompetitorCollection() {
		}

		public int size() {
			return list.size();
		}

		public CompetitorRecord get(int i) {
			return list.get(i);
		}

		public CompetitorRecord getById(long id) {
			for (CompetitorRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, String competitorName,
				int isActive, String createdTime, String modifiedTime, long user) {
			list.add(new CompetitorRecord(id, no, competitorName, isActive,
					createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, String competitorName,
				int isActive, String createdTime, String modifiedTime, long user) {
			CompetitorRecord record = getById(id);
			record.setNo(no);
			record.setCompetitorName(competitorName);
			record.setIsActive(isActive);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<CompetitorRecord> iterator() {
			Iterator<CompetitorRecord> iter = new Iterator<CompetitorRecord>() {
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
				public CompetitorRecord next() {
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
