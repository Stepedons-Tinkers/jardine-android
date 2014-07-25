package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
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
	private final String KEY_WORKPLAN_CRMNO = "crm_no";
	private final String KEY_WORKPLAN_FROMDATE = "from_date";
	private final String KEY_WORKPLAN_TODATE = "to_date";
	// private final String KEY_WORKPLAN_STATUS = "status"; removed
	private final String KEY_WORKPLAN_CREATEDTIME = "created_time";
	private final String KEY_WORKPLAN_MODIFIEDTIME = "modified_time";
	private final String KEY_WORKPLAN_CREATEDBY = "created_by";

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
					String crmNo = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_CRMNO));
					String startDate = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_FROMDATE));
					String endDate = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_TODATE));
					// int status = c
					// .getInt(c.getColumnIndex(KEY_WORKPLAN_STATUS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_WORKPLAN_CREATEDBY));

					// list.add(new WorkplanRecord(id, no, startDate, endDate,
					// status, createdTime, modifiedTime, user));
					list.add(new WorkplanRecord(id, no, crmNo, startDate,
							endDate, createdTime, modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<WorkplanRecord> getAllRecords(long userId) {
		Cursor c = null;
		List<WorkplanRecord> list = new ArrayList<WorkplanRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLAN_CREATEDBY + " = " + userId;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_WORKPLAN_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_WORKPLAN_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_CRMNO));
					String startDate = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_FROMDATE));
					String endDate = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_TODATE));
					// int status = c
					// .getInt(c.getColumnIndex(KEY_WORKPLAN_STATUS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_WORKPLAN_CREATEDBY));

					// list.add(new WorkplanRecord(id, no, startDate, endDate,
					// status, createdTime, modifiedTime, user));
					list.add(new WorkplanRecord(id, no, crmNo, startDate,
							endDate, createdTime, modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<WorkplanRecord> getAllRecords(long userId, String workPlan,
			String date) {
		Cursor c = null;
		List<WorkplanRecord> list = new ArrayList<WorkplanRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLAN_CREATEDBY + " = " + userId + " AND "
				+ KEY_WORKPLAN_NO + " = '" + workPlan + "' AND "
				+ KEY_WORKPLAN_CREATEDTIME + " = '" + date + "'";
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_WORKPLAN_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_WORKPLAN_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_CRMNO));
					String startDate = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_FROMDATE));
					String endDate = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_TODATE));
					// int status = c
					// .getInt(c.getColumnIndex(KEY_WORKPLAN_STATUS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_WORKPLAN_CREATEDBY));

					list.add(new WorkplanRecord(id, no, crmNo, startDate,
							endDate, createdTime, modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<String> getAllWorkplan(long userId) {
		Cursor c = null;
		List<String> list = new ArrayList<String>();
		String MY_QUERY = "SELECT " + KEY_WORKPLAN_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_WORKPLAN_CREATEDBY + " = "
				+ userId;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if ((c != null) && c.moveToFirst()) {
				do {
					String no = c.getString(c
							.getColumnIndex(KEY_WORKPLAN_CRMNO));
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

	public int deleteByCrmNo(String[] no) {

		String ids = Arrays.toString(no);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "");

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_WORKPLAN_NO + " IN ("
				+ ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public long getIdByNo(String no) {
		long result = 0;
		String MY_QUERY = "SELECT " + KEY_WORKPLAN_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_WORKPLAN_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_WORKPLAN_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}
	

	public WorkplanRecord getById(long ID) {
		WorkplanRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLAN_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_WORKPLAN_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_WORKPLAN_NO));
				String crmNo = c
						.getString(c.getColumnIndex(KEY_WORKPLAN_CRMNO));
				String startDate = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_FROMDATE));
				String endDate = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_TODATE));
				// int status = c.getInt(c.getColumnIndex(KEY_WORKPLAN_STATUS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_WORKPLAN_CREATEDBY));

				// record = new WorkplanRecord(id, no, startDate, endDate,
				// status,
				// createdTime, modifiedTime, user);
				record = new WorkplanRecord(id, no, crmNo, startDate, endDate,
						createdTime, modifiedTime, user);
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
		String MY_QUERY = "SELECT " + KEY_WORKPLAN_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_WORKPLAN_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_WORKPLAN_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
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
				String crmNo = c
						.getString(c.getColumnIndex(KEY_WORKPLAN_CRMNO));
				String startDate = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_FROMDATE));
				String endDate = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_TODATE));
				// int status = c.getInt(c.getColumnIndex(KEY_WORKPLAN_STATUS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_WORKPLAN_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_WORKPLAN_CREATEDBY));

				// record = new WorkplanRecord(id, no, startDate, endDate,
				// status,
				// createdTime, modifiedTime, user);
				record = new WorkplanRecord(id, no, crmNo, startDate, endDate,
						createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String startDate,
			String endDate, String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// WorkplanCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_WORKPLAN_NO, no);
		initialValues.put(KEY_WORKPLAN_CRMNO, crmNo);
		initialValues.put(KEY_WORKPLAN_FROMDATE, startDate);
		initialValues.put(KEY_WORKPLAN_TODATE, endDate);
		// initialValues.put(KEY_WORKPLAN_STATUS, status);
		initialValues.put(KEY_WORKPLAN_CREATEDTIME, createdTime);
		initialValues.put(KEY_WORKPLAN_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_WORKPLAN_CREATEDBY, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, startDate, endDate, status, createdTime,
			// modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_WORKPLAN_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, String startDate,
			String endDate, String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_WORKPLAN_NO, no);
		args.put(KEY_WORKPLAN_CRMNO, crmNo);
		args.put(KEY_WORKPLAN_FROMDATE, startDate);
		args.put(KEY_WORKPLAN_TODATE, endDate);
		// args.put(KEY_WORKPLAN_STATUS, status);
		args.put(KEY_WORKPLAN_CREATEDTIME, createdTime);
		args.put(KEY_WORKPLAN_MODIFIEDTIME, modifiedTime);
		args.put(KEY_WORKPLAN_CREATEDBY, user);
		if (mDb.update(mDatabaseTable, args, KEY_WORKPLAN_ROWID + "=" + id,
				null) > 0) {
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
