package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.SalesProtocolRecord;

public class SalesProtocolTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_SALESPROTOCOL_ROWID = "_id";
	private final String KEY_SALESPROTOCOL_NO = "no";
	private final String KEY_SALESPROTOCOL_CRMNO = "crm_no";
	private final String KEY_SALESPROTOCOL_BUSINESSUNIT = "business_unit";
	private final String KEY_SALESPROTOCOL_DESCRIPTION = "description";
	private final String KEY_SALESPROTOCOL_LASTUPDATE = "last_update";
	private final String KEY_SALESPROTOCOL_TAGS = "tags";
	private final String KEY_SALESPROTOCOL_PROTOCOLTYPE = "protocol_type";
	private final String KEY_SALESPROTOCOL_ISACTIVE = "is_active";
	private final String KEY_SALESPROTOCOL_CREATEDBY = "created_by";
	private final String KEY_SALESPROTOCOL_CREATEDTIME = "created_time";
	private final String KEY_SALESPROTOCOL_MODIFIEDTIME = "modified_time";

	// ===========================================================
	// Private fields
	// ===========================================================

	// private ActivityTypeCollection activityTypeRecords;zg
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public SalesProtocolTable(SQLiteDatabase database, String tableName) {
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

	public List<SalesProtocolRecord> getAllRecords() {
		Cursor c = null;
		List<SalesProtocolRecord> list = new ArrayList<SalesProtocolRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_CRMNO));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_BUSINESSUNIT));
					String description = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_TAGS));
					long protocolType = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_PROTOCOLTYPE));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_SALESPROTOCOL_ISACTIVE));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_CREATEDBY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_MODIFIEDTIME));

					list.add(new SalesProtocolRecord(id, no, crmNo,
							businessUnit, description, lastUpdate, tags,
							protocolType, isActive, createdBy, createdTime,
							modifiedTime));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<SalesProtocolRecord> getAllRecordsByUserId(long userId) {
		Cursor c = null;
		List<SalesProtocolRecord> list = new ArrayList<SalesProtocolRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SALESPROTOCOL_CREATEDBY + " = " + userId;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_CRMNO));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_BUSINESSUNIT));
					String description = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_TAGS));
					long protocolType = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_PROTOCOLTYPE));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_SALESPROTOCOL_ISACTIVE));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_CREATEDBY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_MODIFIEDTIME));

					list.add(new SalesProtocolRecord(id, no, crmNo,
							businessUnit, description, lastUpdate, tags,
							protocolType, isActive, createdBy, createdTime,
							modifiedTime));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<SalesProtocolRecord> getAllRecordsBySearch(long userId,
			String searchPhrase, int position) {
		Cursor c = null;
		List<SalesProtocolRecord> list = new ArrayList<SalesProtocolRecord>();
		String MY_QUERY = "";

		switch (position) {
		case 0:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_SALESPROTOCOL_CREATEDBY + " = " + userId;
			break;
		case 1:
			break;
		case 2:
			break;
		}

		Log.d(JardineApp.TAG, MY_QUERY);

		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_CRMNO));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_BUSINESSUNIT));
					String description = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_TAGS));
					long protocolType = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_PROTOCOLTYPE));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_SALESPROTOCOL_ISACTIVE));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_SALESPROTOCOL_CREATEDBY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_SALESPROTOCOL_MODIFIEDTIME));

					list.add(new SalesProtocolRecord(id, no, crmNo,
							businessUnit, description, lastUpdate, tags,
							protocolType, isActive, createdBy, createdTime,
							modifiedTime));
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
							.getColumnIndex(KEY_SALESPROTOCOL_NO));

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
				+ KEY_SALESPROTOCOL_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_SALESPROTOCOL_ROWID
				+ " IN (" + ids + ")", null);

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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_SALESPROTOCOL_NO
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
		String MY_QUERY = "SELECT " + KEY_SALESPROTOCOL_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_SALESPROTOCOL_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_SALESPROTOCOL_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public SalesProtocolRecord getById(long ID) {
		SalesProtocolRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SALESPROTOCOL_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_SALESPROTOCOL_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_SALESPROTOCOL_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_CRMNO));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_SALESPROTOCOL_BUSINESSUNIT));
				String description = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_DESCRIPTION));
				String lastUpdate = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_LASTUPDATE));
				String tags = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_TAGS));
				long protocolType = c.getLong(c
						.getColumnIndex(KEY_SALESPROTOCOL_PROTOCOLTYPE));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_SALESPROTOCOL_ISACTIVE));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_SALESPROTOCOL_CREATEDBY));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_MODIFIEDTIME));

				record = new SalesProtocolRecord(id, no, crmNo, businessUnit,
						description, lastUpdate, tags, protocolType, isActive,
						createdBy, createdTime, modifiedTime);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public String getNoById(long ID) {
		String result = null;
		String MY_QUERY = "SELECT " + KEY_SALESPROTOCOL_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_SALESPROTOCOL_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_SALESPROTOCOL_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public SalesProtocolRecord getByWebId(String ID) {
		SalesProtocolRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SALESPROTOCOL_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_SALESPROTOCOL_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_SALESPROTOCOL_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_CRMNO));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_SALESPROTOCOL_BUSINESSUNIT));
				String description = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_DESCRIPTION));
				String lastUpdate = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_LASTUPDATE));
				String tags = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_TAGS));
				long protocolType = c.getLong(c
						.getColumnIndex(KEY_SALESPROTOCOL_PROTOCOLTYPE));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_SALESPROTOCOL_ISACTIVE));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_SALESPROTOCOL_CREATEDBY));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_SALESPROTOCOL_MODIFIEDTIME));

				record = new SalesProtocolRecord(id, no, crmNo, businessUnit,
						description, lastUpdate, tags, protocolType, isActive,
						createdBy, createdTime, modifiedTime);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, long businessUnit,
			String description, String lastUpdate, String tags,
			long protocolType, int isActive, long createdBy,
			String createdTime, String modifiedTime) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// ActivityTypeCollection collection = getRecords();

		ContentValues args = new ContentValues();

		args.put(KEY_SALESPROTOCOL_NO, no);
		args.put(KEY_SALESPROTOCOL_CRMNO, crmNo);
		args.put(KEY_SALESPROTOCOL_BUSINESSUNIT, businessUnit);
		args.put(KEY_SALESPROTOCOL_DESCRIPTION, description);
		args.put(KEY_SALESPROTOCOL_LASTUPDATE, lastUpdate);
		args.put(KEY_SALESPROTOCOL_TAGS, tags);
		args.put(KEY_SALESPROTOCOL_PROTOCOLTYPE, protocolType);
		args.put(KEY_SALESPROTOCOL_ISACTIVE, isActive);
		args.put(KEY_SALESPROTOCOL_CREATEDBY, createdBy);
		args.put(KEY_SALESPROTOCOL_CREATEDTIME, createdTime);
		args.put(KEY_SALESPROTOCOL_MODIFIEDTIME, modifiedTime);

		long ids = mDb.insert(mDatabaseTable, null, args);
		if (ids >= 0) {
			// collection.add(ids, no, type, category, isActive, user);
			// Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_SALESPROTOCOL_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, long businessUnit,
			String description, String lastUpdate, String tags,
			long protocolType, int isActive, long createdBy,
			String createdTime, String modifiedTime) {
		ContentValues args = new ContentValues();
		args.put(KEY_SALESPROTOCOL_NO, no);
		args.put(KEY_SALESPROTOCOL_CRMNO, crmNo);
		args.put(KEY_SALESPROTOCOL_BUSINESSUNIT, businessUnit);
		args.put(KEY_SALESPROTOCOL_DESCRIPTION, description);
		args.put(KEY_SALESPROTOCOL_LASTUPDATE, lastUpdate);
		args.put(KEY_SALESPROTOCOL_TAGS, tags);
		args.put(KEY_SALESPROTOCOL_PROTOCOLTYPE, protocolType);
		args.put(KEY_SALESPROTOCOL_ISACTIVE, isActive);
		args.put(KEY_SALESPROTOCOL_CREATEDBY, createdBy);
		args.put(KEY_SALESPROTOCOL_CREATEDTIME, createdTime);
		args.put(KEY_SALESPROTOCOL_MODIFIEDTIME, modifiedTime);
		if (mDb.update(mDatabaseTable, args,
				KEY_SALESPROTOCOL_ROWID + "=" + id, null) > 0) {
			// getRecords().update(id, no, type, category, isActive, user);
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
