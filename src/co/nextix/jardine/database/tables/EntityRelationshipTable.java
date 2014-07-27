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
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_MODULENAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_RELATEDNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ENTITYRELATIONSHIP_RELATEDMODULENAME;

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
					String crmNo = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_CRMNO));
					String moduleName = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULENAME));
					String relatedNo = c.getString(c
							.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDNO));
					String relatedModuleName = c
							.getString(c
									.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDMODULENAME));

					list.add(new EntityRelationshipRecord(id, crmNo,
							moduleName, relatedNo, relatedModuleName));
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

	public boolean isExisting(String crmNo, String module, String relCrmNo,
			String relModule) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ENTITYRELATIONSHIP_CRMNO + "='" + crmNo + "' AND "
				+ KEY_ENTITYRELATIONSHIP_CRMNO + "='" + relCrmNo + "' AND "
				+ KEY_ENTITYRELATIONSHIP_MODULENAME + "='" + module + "' AND "
				+ KEY_ENTITYRELATIONSHIP_RELATEDMODULENAME + "='" + relModule
				+ "'";
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
				String crmNo = c.getString(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_CRMNO));
				String moduleName = c.getString(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_MODULENAME));
				String relatedNo = c.getString(c
						.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDNO));
				String relatedModuleName = c
						.getString(c
								.getColumnIndex(KEY_ENTITYRELATIONSHIP_RELATEDMODULENAME));

				record = new EntityRelationshipRecord(id, crmNo, moduleName,
						relatedNo, relatedModuleName);
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

	public long insert(String crmNo, String moduleName, String relatedNo,
			String relatedModuleName) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// WorkplanCollection collection = getRecords();

		ContentValues args = new ContentValues();

		args.put(KEY_ENTITYRELATIONSHIP_CRMNO, crmNo);
		args.put(KEY_ENTITYRELATIONSHIP_MODULENAME, moduleName);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDNO, relatedNo);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDMODULENAME, relatedModuleName);

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

	public boolean update(long id, String crmNo, String moduleName,
			String relatedNo, String relatedModuleName) {
		ContentValues args = new ContentValues();
		args.put(KEY_ENTITYRELATIONSHIP_CRMNO, crmNo);
		args.put(KEY_ENTITYRELATIONSHIP_MODULENAME, moduleName);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDNO, relatedNo);
		args.put(KEY_ENTITYRELATIONSHIP_RELATEDMODULENAME, relatedModuleName);
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
