package co.nextix.jardine.database.tables;

import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_BUSINESSUNIT;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_CREATEDBY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_DESCRIPTION;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_ISACTIVE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_ISNEW;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_LASTUPDATE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGMATERIALS_TAGS;

import java.util.ArrayList;
import java.util.Arrays;
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
	// Private fields
	// ===========================================================

	// private MarketingMaterialsCollection marketingMaterialsCollection;
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

	@SuppressWarnings("unused")
	private DatabaseAdapter getDBAdapter() {
		if (mDBAdapter == null)
			mDBAdapter = DatabaseAdapter.getInstance();

		return mDBAdapter;
	}

	// ===========================================================
	// Private methods
	// ===========================================================

	public List<MarketingMaterialsRecord> getAllRecords() {
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
					String crmNo = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_CRMNO));
					String description = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_TAGS));
					long businessUnit = c
							.getLong(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_BUSINESSUNIT));
					int isNew = c.getInt(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ISNEW));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ISACTIVE));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDBY));

					list.add(new MarketingMaterialsRecord(id, no, crmNo,
							description, lastUpdate, tags, businessUnit, isNew,
							isActive, createdTime, modifiedTime, createdBy));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<MarketingMaterialsRecord> getAllRecordsByUser(long userId) {
		Cursor c = null;
		List<MarketingMaterialsRecord> list = new ArrayList<MarketingMaterialsRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGMATERIALS_CREATEDBY + " = " + userId;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_CRMNO));
					String description = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_TAGS));
					long businessUnit = c
							.getLong(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_BUSINESSUNIT));
					int isNew = c.getInt(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ISNEW));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ISACTIVE));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDBY));

					list.add(new MarketingMaterialsRecord(id, no, crmNo,
							description, lastUpdate, tags, businessUnit, isNew,
							isActive, createdTime, modifiedTime, createdBy));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<MarketingMaterialsRecord> getAllRecordsBySearch(long userId,
			String hint, int column) {
		Cursor c = null;
		List<MarketingMaterialsRecord> list = new ArrayList<MarketingMaterialsRecord>();
		String MY_QUERY = "";

		switch (column) {
		case 0:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_MARKETINGMATERIALS_CREATEDBY + " = " + userId
					+ " AND " + KEY_MARKETINGMATERIALS_CRMNO + " LIKE '%"
					+ hint + "%'";
			break;
		case 1:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_MARKETINGMATERIALS_CREATEDBY + " = " + userId
					+ " AND " + KEY_MARKETINGMATERIALS_DESCRIPTION + " LIKE '%"
					+ hint + "%'";
			break;
		case 2:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_MARKETINGMATERIALS_CREATEDBY + " = " + userId
					+ " AND " + KEY_MARKETINGMATERIALS_TAGS + " LIKE '%" + hint
					+ "%'";
			break;
		default:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_MARKETINGMATERIALS_CREATEDBY + " = " + userId;
			break;

		}
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_CRMNO));
					String description = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_TAGS));
					long businessUnit = c
							.getLong(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_BUSINESSUNIT));
					int isNew = c.getInt(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ISNEW));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_ISACTIVE));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_MARKETINGMATERIALS_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDBY));

					list.add(new MarketingMaterialsRecord(id, no, crmNo,
							description, lastUpdate, tags, businessUnit, isNew,
							isActive, createdTime, modifiedTime, createdBy));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<String> getNos() {
		Cursor c = null;
		List<String> list = new ArrayList<String>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					String no = c.getString(c
							.getColumnIndex(KEY_MARKETINGMATERIALS_NO));

					list.add(no);
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

	public int deleteByCrmNo(String[] no) {

		String ids = Arrays.toString(no);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "");

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_MARKETINGMATERIALS_NO
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
		String MY_QUERY = "SELECT " + KEY_MARKETINGMATERIALS_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_MARKETINGMATERIALS_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public MarketingMaterialsRecord getById(long ID) {
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
				String crmNo = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_CRMNO));
				String description = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_DESCRIPTION));
				String lastUpdate = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_LASTUPDATE));
				String tags = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_TAGS));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_BUSINESSUNIT));
				int isNew = c.getInt(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_ISNEW));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDBY));

				record = new MarketingMaterialsRecord(id, no, crmNo,
						description, lastUpdate, tags, businessUnit, isNew,
						isActive, createdTime, modifiedTime, createdBy);
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
				String crmNo = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_CRMNO));
				String description = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_DESCRIPTION));
				String lastUpdate = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_LASTUPDATE));
				String tags = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_TAGS));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_BUSINESSUNIT));
				int isNew = c.getInt(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_ISNEW));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_MARKETINGMATERIALS_CREATEDBY));

				record = new MarketingMaterialsRecord(id, no, crmNo,
						description, lastUpdate, tags, businessUnit, isNew,
						isActive, createdTime, modifiedTime, createdBy);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String description,
			String lastUpdate, String tags, long businessUnit, int isNew,
			int isActive, String createdTime, String modifiedTime,
			long createdBy) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// MarketingMaterialsCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_MARKETINGMATERIALS_NO, no);
		initialValues.put(KEY_MARKETINGMATERIALS_CRMNO, crmNo);
		initialValues.put(KEY_MARKETINGMATERIALS_DESCRIPTION, description);
		initialValues.put(KEY_MARKETINGMATERIALS_LASTUPDATE, lastUpdate);
		initialValues.put(KEY_MARKETINGMATERIALS_TAGS, tags);
		initialValues.put(KEY_MARKETINGMATERIALS_BUSINESSUNIT, businessUnit);
		initialValues.put(KEY_MARKETINGMATERIALS_ISNEW, isNew);
		initialValues.put(KEY_MARKETINGMATERIALS_ISACTIVE, isActive);
		initialValues.put(KEY_MARKETINGMATERIALS_CREATEDTIME, createdTime);
		initialValues.put(KEY_MARKETINGMATERIALS_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_MARKETINGMATERIALS_CREATEDBY, createdBy);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, description, lastUpdate, tags,
			// createdTime,
			// modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_MARKETINGMATERIALS_ROWID + "="
				+ rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, String description,
			String lastUpdate, String tags, long businessUnit, int isNew,
			int isActive, String createdTime, String modifiedTime,
			long createdBy) {
		ContentValues args = new ContentValues();
		args.put(KEY_MARKETINGMATERIALS_NO, no);
		args.put(KEY_MARKETINGMATERIALS_CRMNO, crmNo);
		args.put(KEY_MARKETINGMATERIALS_DESCRIPTION, description);
		args.put(KEY_MARKETINGMATERIALS_LASTUPDATE, lastUpdate);
		args.put(KEY_MARKETINGMATERIALS_TAGS, tags);
		args.put(KEY_MARKETINGMATERIALS_BUSINESSUNIT, businessUnit);
		args.put(KEY_MARKETINGMATERIALS_ISNEW, isNew);
		args.put(KEY_MARKETINGMATERIALS_ISACTIVE, isActive);
		args.put(KEY_MARKETINGMATERIALS_CREATEDTIME, createdTime);
		args.put(KEY_MARKETINGMATERIALS_MODIFIEDTIME, modifiedTime);
		args.put(KEY_MARKETINGMATERIALS_CREATEDBY, createdBy);
		if (mDb.update(mDatabaseTable, args, KEY_MARKETINGMATERIALS_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, description, lastUpdate, tags,
			// createdTime, modifiedTime, user);
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

	// ===========================================================
	// Public Foreign Key Methods
	// ===========================================================

	// ===========================================================
	// Collection
	// ===========================================================

	// public MarketingMaterialsCollection getRecords() {
	// if (marketingMaterialsCollection == null) {
	// marketingMaterialsCollection = new MarketingMaterialsCollection();
	// marketingMaterialsCollection.list = getAllRecords();
	// }
	// return marketingMaterialsCollection;
	// }
	//
	// public final class MarketingMaterialsCollection implements
	// Iterable<MarketingMaterialsRecord> {
	//
	// private List<MarketingMaterialsRecord> list;
	//
	// private MarketingMaterialsCollection() {
	// }
	//
	// public int size() {
	// return list.size();
	// }
	//
	// public MarketingMaterialsRecord get(int i) {
	// return list.get(i);
	// }
	//
	// public MarketingMaterialsRecord getById(long id) {
	// for (MarketingMaterialsRecord record : list) {
	// if (record.getId() == id) {
	// return record;
	// }
	// }
	// return null;
	// }
	//
	// private void add(long id, String no, String description,
	// String lastUpdate, String tags, String createdTime,
	// String modifiedTime, long user) {
	// list.add(new MarketingMaterialsRecord(id, no, description,
	// lastUpdate, tags, createdTime, modifiedTime, user));
	// }
	//
	// private void clear() {
	// list.clear();
	// }
	//
	// private void deleteById(long id) {
	// list.remove(getById(id));
	// }
	//
	// private void update(long id, String no, String description,
	// String lastUpdate, String tags, String createdTime,
	// String modifiedTime, long user) {
	// MarketingMaterialsRecord record = getById(id);
	// record.setNo(no);
	// record.setDescription(description);
	// record.setLastUpdate(lastUpdate);
	// record.setTags(tags);
	// record.setCreatedTime(createdTime);
	// record.setModifiedTime(modifiedTime);
	// record.setUser(user);
	// }
	//
	// @Override
	// public Iterator<MarketingMaterialsRecord> iterator() {
	// Iterator<MarketingMaterialsRecord> iter = new
	// Iterator<MarketingMaterialsRecord>() {
	// private int current = 0;
	//
	// @Override
	// public void remove() {
	// if (list.size() > 0) {
	// delete(list.get(current).getId());
	// deleteById(list.get(current).getId());
	// list.remove(current);
	// }
	// }
	//
	// @Override
	// public MarketingMaterialsRecord next() {
	// if (list.size() > 0) {
	// return list.get(current++);
	// }
	// return null;
	// }
	//
	// @Override
	// public boolean hasNext() {
	// return list.size() > 0 && current < list.size();
	// }
	// };
	// return iter;
	// }
	// }
}
