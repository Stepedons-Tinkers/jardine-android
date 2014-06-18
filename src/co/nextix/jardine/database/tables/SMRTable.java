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
import co.nextix.jardine.database.records.SMRRecord;

public class SMRTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_SMR_ROWID = "_id";
	private final String KEY_SMR_NO = "no";
	private final String KEY_SMR_FIRSTNAME = "firstname";
	private final String KEY_SMR_LASTNAME = "lastname";
	private final String KEY_SMR_REGION = "region";
	private final String KEY_SMR_AREA = "area";
	private final String KEY_SMR_ISACTIVE = "is_active";
	private final String KEY_SMR_CREATEDTIME = "created_time";
	private final String KEY_SMR_MODIFIEDTIME = "modified_time";
	private final String KEY_SMR_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private SMRCollection smrCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public SMRTable(SQLiteDatabase database, String tableName) {
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

	private List<SMRRecord> getAllRecords() {
		Cursor c = null;
		List<SMRRecord> list = new ArrayList<SMRRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_SMR_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_SMR_NO));
					String firstname = c.getString(c
							.getColumnIndex(KEY_SMR_FIRSTNAME));
					String lastname = c.getString(c
							.getColumnIndex(KEY_SMR_LASTNAME));
					long region = c.getLong(c.getColumnIndex(KEY_SMR_REGION));
					long area = c.getLong(c.getColumnIndex(KEY_SMR_AREA));
					int isActive = c.getInt(c.getColumnIndex(KEY_SMR_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_SMR_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_SMR_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_SMR_USER));

					list.add(new SMRRecord(id, no, firstname, lastname, region,
							area, isActive, createdTime, modifiedTime, user));
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
				+ KEY_SMR_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_SMR_ROWID + " IN ("
				+ ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public SMRRecord getById(int ID) {
		SMRRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SMR_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_SMR_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_SMR_NO));
				String firstname = c.getString(c
						.getColumnIndex(KEY_SMR_FIRSTNAME));
				String lastname = c.getString(c
						.getColumnIndex(KEY_SMR_LASTNAME));
				long region = c.getLong(c.getColumnIndex(KEY_SMR_REGION));
				long area = c.getLong(c.getColumnIndex(KEY_SMR_AREA));
				int isActive = c.getInt(c.getColumnIndex(KEY_SMR_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_SMR_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_SMR_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_SMR_USER));

				record = new SMRRecord(id, no, firstname, lastname, region,
						area, isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public SMRRecord getByWebId(String ID) {
		SMRRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SMR_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_SMR_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_SMR_NO));
				String firstname = c.getString(c
						.getColumnIndex(KEY_SMR_FIRSTNAME));
				String lastname = c.getString(c
						.getColumnIndex(KEY_SMR_LASTNAME));
				long region = c.getLong(c.getColumnIndex(KEY_SMR_REGION));
				long area = c.getLong(c.getColumnIndex(KEY_SMR_AREA));
				int isActive = c.getInt(c.getColumnIndex(KEY_SMR_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_SMR_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_SMR_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_SMR_USER));

				record = new SMRRecord(id, no, firstname, lastname, region,
						area, isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, String firstname, String lastname,
			long region, long area, int isActive, String createdTime,
			String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		SMRCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_SMR_NO, no);
		initialValues.put(KEY_SMR_FIRSTNAME, firstname);
		initialValues.put(KEY_SMR_LASTNAME, lastname);
		initialValues.put(KEY_SMR_REGION, region);
		initialValues.put(KEY_SMR_AREA, area);
		initialValues.put(KEY_SMR_ISACTIVE, isActive);
		initialValues.put(KEY_SMR_CREATEDTIME, createdTime);
		initialValues.put(KEY_SMR_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_SMR_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, firstname, lastname, region, area,
					isActive, createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_SMR_ROWID + "=" + rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, String firstname,
			String lastname, long region, long area, int isActive,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_SMR_NO, no);
		args.put(KEY_SMR_FIRSTNAME, firstname);
		args.put(KEY_SMR_LASTNAME, lastname);
		args.put(KEY_SMR_REGION, region);
		args.put(KEY_SMR_AREA, area);
		args.put(KEY_SMR_ISACTIVE, isActive);
		args.put(KEY_SMR_CREATEDTIME, createdTime);
		args.put(KEY_SMR_MODIFIEDTIME, modifiedTime);
		args.put(KEY_SMR_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_SMR_ROWID + "=" + id, null) > 0) {
			getRecords().update(id, no, firstname, lastname, region, area,
					isActive, createdTime, modifiedTime, user);
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

	public SMRCollection getRecords() {
		if (smrCollection == null) {
			smrCollection = new SMRCollection();
			smrCollection.list = getAllRecords();
		}
		return smrCollection;
	}

	public final class SMRCollection implements Iterable<SMRRecord> {

		private List<SMRRecord> list;

		private SMRCollection() {
		}

		public int size() {
			return list.size();
		}

		public SMRRecord get(int i) {
			return list.get(i);
		}

		public SMRRecord getById(long id) {
			for (SMRRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, String firstname, String lastname,
				long region, long area, int isActive, String createdTime,
				String modifiedTime, long user) {
			list.add(new SMRRecord(id, no, firstname, lastname, region, area,
					isActive, createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, String firstname,
				String lastname, long region, long area, int isActive,
				String createdTime, String modifiedTime, long user) {
			SMRRecord record = getById(id);
			record.setNo(no);
			record.setFirstname(firstname);
			record.setLastname(lastname);
			record.setRegion(region);
			record.setArea(area);
			record.setIsActive(isActive);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<SMRRecord> iterator() {
			Iterator<SMRRecord> iter = new Iterator<SMRRecord>() {
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
				public SMRRecord next() {
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
