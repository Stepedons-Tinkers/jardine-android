package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.EntityRelationshipRecord;
import co.nextix.jardine.keys.Modules;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_MODULEID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_MODULENO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_MODULENAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_RELATEDID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_RELATEDNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_RELATEDNAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_SYNCED;

public class EntityRelationshipTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	// private WorkplanCollection workplanCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public EntityRelationshipTable(SQLiteDatabase database, String tableName) {
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

	// ===========================================================
	// Public methods
	// ===========================================================

	public List<EntityRelationshipRecord> getAllRecords() {
		Cursor c = null;
		List<EntityRelationshipRecord> list = new ArrayList<EntityRelationshipRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_ROWID));
					long moduleId = c.getLong(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULEID));
					String moduleNo = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULENO));
					String moduleName = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULENAME));
					long relatedId = c.getLong(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDID));
					String relatedNo = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDNO));
					String relatedName = c
							.getString(c
									.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDNAME));
					int isSynced = c.getInt(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_SYNCED));

					list.add(new EntityRelationshipRecord(id, moduleId,
							moduleNo, moduleName, relatedId, relatedNo,
							relatedName, isSynced));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<EntityRelationshipRecord> getUnsyncedRecords() {
		Cursor c = null;
		List<EntityRelationshipRecord> list = new ArrayList<EntityRelationshipRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ENTITYRELATIONSHIP_SYNCED + "=0";
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_ROWID));
					long moduleId = c.getLong(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULEID));
					String moduleNo = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULENO));
					String moduleName = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULENAME));
					long relatedId = c.getLong(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDID));
					String relatedNo = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDNO));
					String relatedName = c
							.getString(c
									.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDNAME));
					int isSynced = c.getInt(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_SYNCED));

					list.add(new EntityRelationshipRecord(id, moduleId,
							moduleNo, moduleName, relatedId, relatedNo,
							relatedName, isSynced));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<Long> getRecordsWithRelatedProducts(
			long activity) {
		Cursor c = null;
		List<Long> list = new ArrayList<Long>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ENTITYRELATIONSHIP_RELATEDNAME + "='" + Modules.Product
				+ "'" + " AND " + KEY_ENTITYRELATIONSHIP_MODULEID + "="
				+ activity;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long relatedId = c.getLong(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDID));

					list.add(relatedId);
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	// public boolean isExisting(String webID) {
	// boolean exists = false;
	// String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
	// + KEY_ENTITYRELATIONSHIP_PRODUCT + "='" + webID + "'";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, null);
	//
	// if ((c != null) && c.moveToFirst()) {
	// exists = true;
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	// return exists;
	// }

	public boolean isExisting(String moduleNo, String moduleName,
			String relatedNo, String relatedName) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ENTITYRELATIONSHIP_MODULENO + "='" + moduleNo + "' AND "
				+ KEY_ENTITYRELATIONSHIP_RELATEDNO + "='" + relatedNo
				+ "' AND " + KEY_ENTITYRELATIONSHIP_MODULENAME + "='"
				+ moduleName + "' AND " + KEY_ENTITYRELATIONSHIP_RELATEDNAME
				+ "='" + relatedName + "'";
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
				KEY_ENTITYRELATIONSHIP_ROWID + " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	// public int deleteByCrmNo(String[] no) {
	//
	// String ids = Arrays.toString(no);
	//
	// if (ids == null) {
	// return 0;
	// }
	//
	// // Remove the surrounding bracket([]) created by the method
	// // Arrays.toString()
	// ids = ids.replace("[", "").replace("]", "");
	//
	// int rowsDeleted = mDb.delete(mDatabaseTable,
	// KEY_ENTITYRELATIONSHIP_PRODUCT
	// + " IN (" + ids + ")", null);
	//
	// // if (rowsDeleted > 0) {
	// //
	// // // Delete the calls that are referring to the deleted work plan
	// // getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
	// // }
	//
	// return rowsDeleted;
	// }

	// public long getIdByNo(String no) {
	// long result = 0;
	// String MY_QUERY = "SELECT " + KEY_ENTITYRELATIONSHIP_ROWID + " FROM "
	// + mDatabaseTable + " WHERE " + KEY_ENTITYRELATIONSHIP_PRODUCT + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// result = c.getLong(c.getColumnIndex(KEY_ENTITYRELATIONSHIP_ROWID));
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	//
	// return result;
	// }

	public EntityRelationshipRecord getById(long ID) {
		EntityRelationshipRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ENTITYRELATIONSHIP_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_ROWID));
				long moduleId = c.getLong(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULEID));
				String moduleNo = c.getString(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULENO));
				String moduleName = c.getString(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULENAME));
				long relatedId = c.getLong(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDID));
				String relatedNo = c.getString(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDNO));
				String relatedName = c.getString(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDNAME));
				int isSynced = c.getInt(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_SYNCED));

				record = new EntityRelationshipRecord(id, moduleId, moduleNo,
						moduleName, relatedId, relatedNo, relatedName, isSynced);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	// public String getNoById(long ID) {
	// String result = null;
	// String MY_QUERY = "SELECT " + KEY_ENTITYRELATIONSHIP_PRODUCT + " FROM "
	// + mDatabaseTable + " WHERE " + KEY_ENTITYRELATIONSHIP_ROWID + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// result = c
	// .getString(c.getColumnIndex(KEY_ENTITYRELATIONSHIP_PRODUCT));
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	//
	// return result;
	// }
	//
	// public EntityRelationshipRecord getByWebId(String ID) {
	// EntityRelationshipRecord record = null;
	// String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
	// + KEY_ENTITYRELATIONSHIP_PRODUCT + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// long id = c.getLong(c.getColumnIndex(KEY_ENTITYRELATIONSHIP_ROWID));
	// String no = c.getString(c
	// .getColumnIndex(KEY_ENTITYRELATIONSHIP_PRODUCT));
	// String crmNo = c.getString(c
	// .getColumnIndex(KEY_ENTITYRELATIONSHIP_ACTIVITY));
	// String startDate = c.getString(c
	// .getColumnIndex(KEY_ENTITYRELATIONSHIP_FROMDATE));
	// String endDate = c.getString(c
	// .getColumnIndex(KEY_ENTITYRELATIONSHIP_TODATE));
	// // int status =
	// // c.getInt(c.getColumnIndex(KEY_ENTITYRELATIONSHIP_STATUS));
	// String createdTime = c.getString(c
	// .getColumnIndex(KEY_ENTITYRELATIONSHIP_CREATEDTIME));
	// String modifiedTime = c.getString(c
	// .getColumnIndex(KEY_ENTITYRELATIONSHIP_MODIFIEDTIME));
	// long user = c.getLong(c
	// .getColumnIndex(KEY_ENTITYRELATIONSHIP_CREATEDBY));
	//
	// // record = new EntityRelationshipRecord(id, no, startDate, endDate,
	// // status,
	// // createdTime, modifiedTime, user);
	// record = new EntityRelationshipRecord(id, no, crmNo, startDate, endDate,
	// createdTime, modifiedTime, user);
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	//
	// return record;
	// }

	public long insert(long moduleId, String moduleNo, String moduleName,
			long relatedId, String relatedNo, String relatedName, int isSynced) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// WorkplanCollection collection = getRecords();

		ContentValues args = new ContentValues();

		args.put(KEY_ENTITYRELATIONSHIP_MODULEID, moduleId);
		args.put(KEY_ENTITYRELATIONSHIP_MODULENO, moduleNo);
		args.put(KEY_ENTITYRELATIONSHIP_MODULENAME, moduleName);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDID, relatedId);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDNO, relatedNo);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDNAME, relatedName);
		args.put(KEY_ENTITYRELATIONSHIP_SYNCED, isSynced);

		long ids = mDb.insert(mDatabaseTable, null, args);
		if (ids >= 0) {
			// collection.add(ids, no, startDate, endDate, status, createdTime,
			// modifiedTime, user);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_ENTITYRELATIONSHIP_ROWID + "="
				+ rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateSync(long id, int isSynced) {
		ContentValues args = new ContentValues();
		args.put(KEY_ENTITYRELATIONSHIP_SYNCED, isSynced);
		if (mDb.update(mDatabaseTable, args, KEY_ENTITYRELATIONSHIP_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, startDate, endDate, status,
			// createdTime, modifiedTime, user);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateNos(long id, String moduleNo, String relatedNo) {
		ContentValues args = new ContentValues();
		args.put(KEY_ENTITYRELATIONSHIP_MODULENO, moduleNo);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDNO, relatedNo);
		if (mDb.update(mDatabaseTable, args, KEY_ENTITYRELATIONSHIP_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, startDate, endDate, status,
			// createdTime, modifiedTime, user);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, long moduleId, String moduleNo,
			String moduleName, long relatedId, String relatedNo,
			String relatedName, int isSynced) {
		ContentValues args = new ContentValues();
		args.put(KEY_ENTITYRELATIONSHIP_MODULEID, moduleId);
		args.put(KEY_ENTITYRELATIONSHIP_MODULENO, moduleNo);
		args.put(KEY_ENTITYRELATIONSHIP_MODULENAME, moduleName);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDID, relatedId);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDNO, relatedNo);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDNAME, relatedName);
		args.put(KEY_ENTITYRELATIONSHIP_SYNCED, isSynced);
		if (mDb.update(mDatabaseTable, args, KEY_ENTITYRELATIONSHIP_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, startDate, endDate, status,
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
}
