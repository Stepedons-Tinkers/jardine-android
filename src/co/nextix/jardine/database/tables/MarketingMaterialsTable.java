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
import co.nextix.jardine.database.records.MarketingMaterialsRecord;

public class MarketingMaterialsTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_MARKETINGMATERIALS_ROWID = "_id";
	private final String KEY_MARKETINGMATERIALS_NO = "no";
	private final String KEY_MARKETINGMATERIALS_DESCRIPTION = "description";
	private final String KEY_MARKETINGMATERIALS_LASTUPDATE = "last_update";
	private final String KEY_MARKETINGMATERIALS_TAGS = "tags";
	private final String KEY_MARKETINGMATERIALS_CREATEDTIME = "created_time";
	private final String KEY_MARKETINGMATERIALS_MODIFIEDTIME = "modified_time";
	private final String KEY_MARKETINGMATERIALS_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private MarketingMaterialsCollection marketingMaterialsCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public MarketingMaterialsTable(SQLiteDatabase database, String tableName) {
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

	private List<MarketingMaterialsRecord> getAllRecords() {
		Cursor c = null;
		List<MarketingMaterialsRecord> list = new ArrayList<MarketingMaterialsRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_NO));
					String description = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_TAGS));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_USER));

					list.add(new MarketingMaterialsRecord(id, no, description,
							lastUpdate, tags, createdTime, modifiedTime, user));
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
				+ KEY_MARKETINGMATERIALS_NO + "='" + webID + "'";
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
				KEY_MARKETINGMATERIALS_ROWID + " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public MarketingMaterialsRecord getById(int ID) {
		MarketingMaterialsRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGMATERIALS_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_NO));
				String description = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_DESCRIPTION));
				String lastUpdate = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_LASTUPDATE));
				String tags = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_TAGS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_USER));

				record = new MarketingMaterialsRecord(id, no, description,
						lastUpdate, tags, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public MarketingMaterialsRecord getByWebId(String ID) {
		MarketingMaterialsRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGMATERIALS_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_NO));
				String description = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_DESCRIPTION));
				String lastUpdate = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_LASTUPDATE));
				String tags = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_TAGS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_USER));

				record = new MarketingMaterialsRecord(id, no, description,
						lastUpdate, tags, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, String description, String lastUpdate,
			String tags, String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		MarketingMaterialsCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_MARKETINGMATERIALS_NO, no);
		initialValues.put(KEY_MARKETINGMATERIALS_DESCRIPTION, description);
		initialValues.put(KEY_MARKETINGMATERIALS_LASTUPDATE, lastUpdate);
		initialValues.put(KEY_MARKETINGMATERIALS_TAGS, tags);
		initialValues.put(KEY_MARKETINGMATERIALS_CREATEDTIME, createdTime);
		initialValues.put(KEY_MARKETINGMATERIALS_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_MARKETINGMATERIALS_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, description, lastUpdate, tags, createdTime,
					modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_MARKETINGMATERIALS_ROWID + "="
				+ rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, String description,
			String lastUpdate, String tags, String createdTime,
			String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_MARKETINGMATERIALS_NO, no);
		args.put(KEY_MARKETINGMATERIALS_DESCRIPTION, description);
		args.put(KEY_MARKETINGMATERIALS_LASTUPDATE, lastUpdate);
		args.put(KEY_MARKETINGMATERIALS_TAGS, tags);
		args.put(KEY_MARKETINGMATERIALS_CREATEDTIME, createdTime);
		args.put(KEY_MARKETINGMATERIALS_MODIFIEDTIME, modifiedTime);
		args.put(KEY_MARKETINGMATERIALS_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_MARKETINGMATERIALS_ROWID + "="
				+ id, null) > 0) {
			getRecords().update(id, no, description, lastUpdate, tags,
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

	public MarketingMaterialsCollection getRecords() {
		if (marketingMaterialsCollection == null) {
			marketingMaterialsCollection = new MarketingMaterialsCollection();
			marketingMaterialsCollection.list = getAllRecords();
		}
		return marketingMaterialsCollection;
	}

	public final class MarketingMaterialsCollection implements
			Iterable<MarketingMaterialsRecord> {

		private List<MarketingMaterialsRecord> list;

		private MarketingMaterialsCollection() {
		}

		public int size() {
			return list.size();
		}

		public MarketingMaterialsRecord get(int i) {
			return list.get(i);
		}

		public MarketingMaterialsRecord getById(long id) {
			for (MarketingMaterialsRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, String description,
				String lastUpdate, String tags, String createdTime,
				String modifiedTime, long user) {
			list.add(new MarketingMaterialsRecord(id, no, description,
					lastUpdate, tags, createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, String description,
				String lastUpdate, String tags, String createdTime,
				String modifiedTime, long user) {
			MarketingMaterialsRecord record = getById(id);
			record.setNo(no);
			record.setDescription(description);
			record.setLastUpdate(lastUpdate);
			record.setTags(tags);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<MarketingMaterialsRecord> iterator() {
			Iterator<MarketingMaterialsRecord> iter = new Iterator<MarketingMaterialsRecord>() {
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
				public MarketingMaterialsRecord next() {
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
