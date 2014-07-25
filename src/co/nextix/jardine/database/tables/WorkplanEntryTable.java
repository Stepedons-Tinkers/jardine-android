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
import co.nextix.jardine.database.records.WorkplanEntryRecord;

public class WorkplanEntryTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_WORKPLANENTRY_ROWID = "_id";
	private final String KEY_WORKPLANENTRY_NO = "no";
	private final String KEY_WORKPLANENTRY_CRMNO = "crm_no";
	// private final String KEY_WORKPLANENTRY_CUSTOMER = "customer";
	private final String KEY_WORKPLANENTRY_DATE = "date";
	private final String KEY_WORKPLANENTRY_STATUS = "status";
	private final String KEY_WORKPLANENTRY_AREA = "area";
	private final String KEY_WORKPLANENTRY_PROVINCE = "province";
	private final String KEY_WORKPLANENTRY_CITY = "city";
	private final String KEY_WORKPLANENTRY_ACTIVITYTYPE = "activity_type";
	private final String KEY_WORKPLANENTRY_REMARKS = "remarks";
	private final String KEY_WORKPLANENTRY_WORKPLAN = "workplan";
	private final String KEY_WORKPLANENTRY_ACTIVITYQUANTITY = "activity_quantity";
	private final String KEY_WORKPLANENTRY_BUSINESSUNIT = "business_unit";
	private final String KEY_WORKPLANENTRY_CREATEDTIME = "created_time";
	private final String KEY_WORKPLANENTRY_MODIFIEDTIME = "modified_time";
	private final String KEY_WORKPLANENTRY_CREATEDBY = "created_by";

	// ===========================================================
	// Private fields
	// ===========================================================

	// private WorkplanEntryCollection workplanEntryCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public WorkplanEntryTable(SQLiteDatabase database, String tableName) {
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

	public List<WorkplanEntryRecord> getAllRecords() {
		Cursor c = null;
		List<WorkplanEntryRecord> list = new ArrayList<WorkplanEntryRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_CRMNO));
					String date = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_DATE));
					long status = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_STATUS));
					long area = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_PROVINCE));
					long city = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_CITY));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
					String remarks = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_REMARKS));
					int activityQuantity = c
							.getInt(c
									.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYQUANTITY));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_BUSINESSUNIT));
					long workplan = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_WORKPLAN));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_CREATEDBY));

					list.add(new WorkplanEntryRecord(id, no, crmNo, date,
							status, area, province, city, activityType,
							remarks, workplan, activityQuantity, businessUnit,
							createdTime, modifiedTime, createdBy));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<WorkplanEntryRecord> getRecordsByWorkplanId(long wId) {
		Cursor c = null;
		List<WorkplanEntryRecord> list = new ArrayList<WorkplanEntryRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLANENTRY_WORKPLAN + " = " + wId;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_CRMNO));
					String date = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_DATE));
					long status = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_STATUS));
					long area = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_PROVINCE));
					long city = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_CITY));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
					String remarks = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_REMARKS));
					int activityQuantity = c
							.getInt(c
									.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYQUANTITY));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_BUSINESSUNIT));
					long workplan = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_WORKPLAN));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_CREATEDBY));

					list.add(new WorkplanEntryRecord(id, no, crmNo, date,
							status, area, province, city, activityType,
							remarks, workplan, activityQuantity, businessUnit,
							createdTime, modifiedTime, createdBy));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<WorkplanEntryRecord> getRecordsByWorkplanIdDate(long wId,
			String dateFrom, String dateTo, String phrase, int position) {
		Cursor c = null;

		List<WorkplanEntryRecord> list = new ArrayList<WorkplanEntryRecord>();
		String MY_QUERY = null;

		// SELECT * FROM Workplan_Entry WHERE date >= DATE('2014-07-08') AND
		// date <= DATE('2014-07-10') AND workplan = 0
		if (wId == 0) {
			return list;
		} else if (dateFrom.contentEquals("") && dateTo.contentEquals("")
				&& phrase.contentEquals("")) {
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_WORKPLANENTRY_WORKPLAN + " = " + wId;
		} else if (dateFrom.contentEquals("") && !dateTo.contentEquals("")
				&& phrase.contentEquals("")) {
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_WORKPLANENTRY_WORKPLAN + " = " + wId + " AND "
					+ KEY_WORKPLANENTRY_DATE + " <= DATE('" + dateTo + "')";
		} else if (!dateFrom.contentEquals("") && dateTo.contentEquals("")
				&& phrase.contentEquals("")) {
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_WORKPLANENTRY_WORKPLAN + " = " + wId + " AND "
					+ KEY_WORKPLANENTRY_DATE + " >= DATE('" + dateFrom + "')";
		} else if (!dateFrom.contentEquals("") && !dateTo.contentEquals("")
				&& phrase.contentEquals("")) {
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_WORKPLANENTRY_WORKPLAN + " = " + wId + " AND "
					+ KEY_WORKPLANENTRY_DATE + " >= DATE('" + dateFrom
					+ "') AND " + KEY_WORKPLANENTRY_DATE + " <= DATE('"
					+ dateTo + "')";
		} else if (dateFrom.contentEquals("") && dateTo.contentEquals("")
				&& !phrase.contentEquals("")) {

			// SELECT Workplan_Entry.* FROM Workplan_Entry INNER JOIN
			// Activity_Type ON Workplan_Entry.activity_type = Activity_Type._id
			// WHERE Workplan_Entry.workplan = 5 AND Activity_Type.name LIKE
			// '%retail%'
			switch (position) {
			case 0:
				MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
						+ KEY_WORKPLANENTRY_WORKPLAN + " = " + wId + " AND "
						+ KEY_WORKPLANENTRY_CRMNO + " LIKE '%" + phrase + "%'";
				break;
			case 1:
				MY_QUERY = "SELECT " + mDatabaseTable + ".* FROM" + mDatabaseTable + " INNER JOIN "
				+ DatabaseAdapter.ACTIVITY_TYPE_TABLE + " ON " + mDatabaseTable + "." + KEY_WORKPLANENTRY_ACTIVITYTYPE
				+ " = " + DatabaseAdapter.ACTIVITY_TYPE_TABLE + ".";
				break;
			}

		}

		Log.d("Tugs", MY_QUERY);
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_CRMNO));
					String date = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_DATE));
					long status = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_STATUS));
					long area = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_PROVINCE));
					long city = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_CITY));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
					String remarks = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_REMARKS));
					int activityQuantity = c
							.getInt(c
									.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYQUANTITY));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_BUSINESSUNIT));
					long workplan = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_WORKPLAN));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_CREATEDBY));

					list.add(new WorkplanEntryRecord(id, no, crmNo, date,
							status, area, province, city, activityType,
							remarks, workplan, activityQuantity, businessUnit,
							createdTime, modifiedTime, createdBy));
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
				+ KEY_WORKPLANENTRY_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_WORKPLANENTRY_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_WORKPLANENTRY_NO
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
		String MY_QUERY = "SELECT " + KEY_WORKPLANENTRY_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_WORKPLANENTRY_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public WorkplanEntryRecord getById(long ID) {
		WorkplanEntryRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLANENTRY_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_WORKPLANENTRY_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_CRMNO));
				String date = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_DATE));
				long status = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_STATUS));
				long area = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_PROVINCE));
				long city = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_CITY));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
				String remarks = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_REMARKS));
				int activityQuantity = c.getInt(c
						.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYQUANTITY));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_BUSINESSUNIT));
				long workplan = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_WORKPLAN));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_CREATEDBY));

				record = new WorkplanEntryRecord(id, no, crmNo, date, status,
						area, province, city, activityType, remarks, workplan,
						activityQuantity, businessUnit, createdTime,
						modifiedTime, createdBy);
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
		String MY_QUERY = "SELECT " + KEY_WORKPLANENTRY_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_WORKPLANENTRY_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_WORKPLANENTRY_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public WorkplanEntryRecord getByWebId(String ID) {
		WorkplanEntryRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLANENTRY_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_WORKPLANENTRY_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_CRMNO));
				String date = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_DATE));
				long status = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_STATUS));
				long area = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_PROVINCE));
				long city = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_CITY));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
				String remarks = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_REMARKS));
				int activityQuantity = c.getInt(c
						.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYQUANTITY));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_BUSINESSUNIT));
				long workplan = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_WORKPLAN));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_CREATEDBY));

				record = new WorkplanEntryRecord(id, no, crmNo, date, status,
						area, province, city, activityType, remarks, workplan,
						activityQuantity, businessUnit, createdTime,
						modifiedTime, createdBy);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String date, long status,
			long area, long province, long city, long activityType,
			String remarks, long workplan, int activityQuantity,
			long businessUnit, String createdTime, String modifiedTime,
			long createdBy) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// WorkplanEntryCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_WORKPLANENTRY_NO, no);
		initialValues.put(KEY_WORKPLANENTRY_CRMNO, crmNo);
		// initialValues.put(KEY_WORKPLANENTRY_CUSTOMER, customer);
		initialValues.put(KEY_WORKPLANENTRY_DATE, date);
		initialValues.put(KEY_WORKPLANENTRY_STATUS, status);
		initialValues.put(KEY_WORKPLANENTRY_AREA, area);
		initialValues.put(KEY_WORKPLANENTRY_PROVINCE, province);
		initialValues.put(KEY_WORKPLANENTRY_CITY, city);
		initialValues.put(KEY_WORKPLANENTRY_ACTIVITYTYPE, activityType);
		initialValues.put(KEY_WORKPLANENTRY_REMARKS, remarks);
		initialValues.put(KEY_WORKPLANENTRY_WORKPLAN, workplan);
		initialValues.put(KEY_WORKPLANENTRY_ACTIVITYQUANTITY, activityQuantity);
		initialValues.put(KEY_WORKPLANENTRY_BUSINESSUNIT, businessUnit);
		initialValues.put(KEY_WORKPLANENTRY_CREATEDTIME, createdTime);
		initialValues.put(KEY_WORKPLANENTRY_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_WORKPLANENTRY_CREATEDBY, createdBy);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, customer, date, status, area, province,
			// cityTown, remarks, activityType, workplan, createdTime,
			// modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_WORKPLANENTRY_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, String date,
			long status, long area, long province, long city,
			long activityType, String remarks, long workplan,
			int activityQuantity, long businessUnit, String createdTime,
			String modifiedTime, long createdBy) {
		ContentValues args = new ContentValues();
		args.put(KEY_WORKPLANENTRY_NO, no);
		args.put(KEY_WORKPLANENTRY_CRMNO, crmNo);
		// args.put(KEY_WORKPLANENTRY_CUSTOMER, customer);
		args.put(KEY_WORKPLANENTRY_DATE, date);
		args.put(KEY_WORKPLANENTRY_STATUS, status);
		args.put(KEY_WORKPLANENTRY_AREA, area);
		args.put(KEY_WORKPLANENTRY_PROVINCE, province);
		args.put(KEY_WORKPLANENTRY_CITY, city);
		args.put(KEY_WORKPLANENTRY_ACTIVITYTYPE, activityType);
		args.put(KEY_WORKPLANENTRY_REMARKS, remarks);
		args.put(KEY_WORKPLANENTRY_WORKPLAN, workplan);
		args.put(KEY_WORKPLANENTRY_ACTIVITYQUANTITY, activityQuantity);
		args.put(KEY_WORKPLANENTRY_BUSINESSUNIT, businessUnit);
		args.put(KEY_WORKPLANENTRY_CREATEDTIME, createdTime);
		args.put(KEY_WORKPLANENTRY_MODIFIEDTIME, modifiedTime);
		args.put(KEY_WORKPLANENTRY_CREATEDBY, createdBy);
		if (mDb.update(mDatabaseTable, args,
				KEY_WORKPLANENTRY_ROWID + "=" + id, null) > 0) {
			// getRecords().update(id, no, customer, date, status, area,
			// province,
			// cityTown, remarks, activityType, workplan, createdTime,
			// modifiedTime, user);
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
