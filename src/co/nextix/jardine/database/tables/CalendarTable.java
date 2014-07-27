package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.CalendarRecord;

public class CalendarTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_CALENDAR_ROWID = "_id";
	private final String KEY_CALENDAR_ACTIVITYTYPE = "activitytype";
	private final String KEY_CALENDAR_DATESTART = "date_start";
	private final String KEY_CALENDAR_DUEDATE = "due_date";
	private final String KEY_CALENDAR_DESCRIPTION = "description";
	private final String KEY_CALENDAR_SUBJECT = "subject";
	private final String KEY_CALENDAR_TIMESTART = "time_start";
	private final String KEY_CALENDAR_TIMEEND = "time_end";
	private final String KEY_CALENDAR_ACTIVITY = "activity";
	private final String KEY_CALENDAR_CREATEDTIME = "created_time";
	private final String KEY_CALENDAR_MODIFIEDTIME = "modified_time";
	private final String KEY_CALENDAR_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public CalendarTable(SQLiteDatabase database, String tableName) {
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

	public List<CalendarRecord> getAllRecords() {
		Cursor c = null;
		List<CalendarRecord> list = new ArrayList<CalendarRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_CALENDAR_ROWID));
					String activityType = c.getString(c
							.getColumnIndex(KEY_CALENDAR_ACTIVITYTYPE));
					String dateStart = c.getString(c
							.getColumnIndex(KEY_CALENDAR_DATESTART));
					String dueDate = c.getString(c
							.getColumnIndex(KEY_CALENDAR_DUEDATE));
					String description = c.getString(c
							.getColumnIndex(KEY_CALENDAR_DESCRIPTION));
					String subject = c.getString(c
							.getColumnIndex(KEY_CALENDAR_SUBJECT));
					String timeStart = c.getString(c
							.getColumnIndex(KEY_CALENDAR_TIMESTART));
					String timeEnd = c.getString(c
							.getColumnIndex(KEY_CALENDAR_TIMEEND));
					int activity = c.getInt(c
							.getColumnIndex(KEY_CALENDAR_ACTIVITY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_CALENDAR_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_CALENDAR_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_CALENDAR_USER));

					list.add(new CalendarRecord(id, activityType, dateStart,
							dueDate, description, subject, timeStart, timeEnd,
							activity, createdTime, modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<CalendarRecord> getAllRecordsByUserId(long userId, String date) {
		Cursor c = null;
		List<CalendarRecord> list = new ArrayList<CalendarRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CALENDAR_USER + " = " + userId + " AND "
				+ KEY_CALENDAR_DUEDATE + " >= DATE('" + date + "')";
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_CALENDAR_ROWID));
					String activityType = c.getString(c
							.getColumnIndex(KEY_CALENDAR_ACTIVITYTYPE));
					String dateStart = c.getString(c
							.getColumnIndex(KEY_CALENDAR_DATESTART));
					String dueDate = c.getString(c
							.getColumnIndex(KEY_CALENDAR_DUEDATE));
					String description = c.getString(c
							.getColumnIndex(KEY_CALENDAR_DESCRIPTION));
					String subject = c.getString(c
							.getColumnIndex(KEY_CALENDAR_SUBJECT));
					String timeStart = c.getString(c
							.getColumnIndex(KEY_CALENDAR_TIMESTART));
					String timeEnd = c.getString(c
							.getColumnIndex(KEY_CALENDAR_TIMEEND));
					int activity = c.getInt(c
							.getColumnIndex(KEY_CALENDAR_ACTIVITY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_CALENDAR_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_CALENDAR_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_CALENDAR_USER));

					list.add(new CalendarRecord(id, activityType, dateStart,
							dueDate, description, subject, timeStart, timeEnd,
							activity, createdTime, modifiedTime, user));
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

	public boolean isExisting(long activity, String activityType, String subject,
			String dateStart, String dueDate) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CALENDAR_ACTIVITY + "=" + activity + " AND "
				+ KEY_CALENDAR_ACTIVITYTYPE + "='" + activityType + "' AND "
				+ KEY_CALENDAR_SUBJECT + "='" + subject + "' AND "
				+ KEY_CALENDAR_DATESTART + "='" + dateStart + "' AND "
				+ KEY_CALENDAR_DUEDATE + "='" + dueDate + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_CALENDAR_ROWID
				+ " IN (" + ids + ")", null);

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
	// int rowsDeleted = mDb.delete(mDatabaseTable, KEY_CALENDAR_NO + " IN ("
	// + ids + ")", null);
	//
	// // if (rowsDeleted > 0) {
	// //
	// // // Delete the calls that are referring to the deleted work plan
	// // getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
	// // }
	//
	// return rowsDeleted;
	// }

	public CalendarRecord getById(long ID) {
		CalendarRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CALENDAR_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_CALENDAR_ROWID));
				String activityType = c.getString(c
						.getColumnIndex(KEY_CALENDAR_ACTIVITYTYPE));
				String dateStart = c.getString(c
						.getColumnIndex(KEY_CALENDAR_DATESTART));
				String dueDate = c.getString(c
						.getColumnIndex(KEY_CALENDAR_DUEDATE));
				String description = c.getString(c
						.getColumnIndex(KEY_CALENDAR_DESCRIPTION));
				String subject = c.getString(c
						.getColumnIndex(KEY_CALENDAR_SUBJECT));
				String timeStart = c.getString(c
						.getColumnIndex(KEY_CALENDAR_TIMESTART));
				String timeEnd = c.getString(c
						.getColumnIndex(KEY_CALENDAR_TIMEEND));
				int activity = c
						.getInt(c.getColumnIndex(KEY_CALENDAR_ACTIVITY));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_CALENDAR_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_CALENDAR_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_CALENDAR_USER));

				record = new CalendarRecord(id, activityType, dateStart,
						dueDate, description, subject, timeStart, timeEnd,
						activity, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	// public CalendarRecord getByWebId(String ID) {
	// CalendarRecord record = null;
	// String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
	// + KEY_CALENDAR_NO + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// long id = c.getLong(c.getColumnIndex(KEY_CALENDAR_ROWID));
	// String no = c.getString(c.getColumnIndex(KEY_CALENDAR_NO));
	// String crmNo = c
	// .getString(c.getColumnIndex(KEY_CALENDAR_CRMNO));
	// String name = c.getString(c.getColumnIndex(KEY_CALENDAR_NAME));
	// String code = c.getString(c.getColumnIndex(KEY_CALENDAR_CODE));
	// int isActive = c
	// .getInt(c.getColumnIndex(KEY_CALENDAR_ISACTIVE));
	// String createdTime = c.getString(c
	// .getColumnIndex(KEY_CALENDAR_CREATEDTIME));
	// String modifiedTime = c.getString(c
	// .getColumnIndex(KEY_CALENDAR_MODIFIEDTIME));
	// long user = c.getLong(c.getColumnIndex(KEY_CALENDAR_USER));
	//
	// record = new CalendarRecord(id, no, crmNo, name, code,
	// isActive, createdTime, modifiedTime, user);
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	//
	// return record;
	// }
	//
	// public long getIdByNo(String no) {
	// long result = 0;
	// String MY_QUERY = "SELECT " + KEY_CALENDAR_ROWID + " FROM "
	// + mDatabaseTable + " WHERE " + KEY_CALENDAR_NO + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// result = c.getLong(c.getColumnIndex(KEY_CALENDAR_ROWID));
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
	// public String getNoById(long ID) {
	// String result = null;
	// String MY_QUERY = "SELECT " + KEY_CALENDAR_NO + " FROM "
	// + mDatabaseTable + " WHERE " + KEY_CALENDAR_ROWID + "=?";
	// Cursor c = null;
	// try {
	// c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });
	//
	// if ((c != null) && c.moveToFirst()) {
	// result = c.getString(c.getColumnIndex(KEY_CALENDAR_ROWID));
	// }
	// } finally {
	// if (c != null) {
	// c.close();
	// }
	// }
	//
	// return result;
	// }

	public long insert(String activityType, String dateStart, String dueDate,
			String description, String subject, String timeStart,
			String timeEnd, long activity, String createdTime,
			String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// BusinessUnitCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_CALENDAR_ACTIVITYTYPE, activityType);
		initialValues.put(KEY_CALENDAR_DATESTART, dateStart);
		initialValues.put(KEY_CALENDAR_DUEDATE, dueDate);
		initialValues.put(KEY_CALENDAR_DESCRIPTION, description);
		initialValues.put(KEY_CALENDAR_SUBJECT, subject);
		initialValues.put(KEY_CALENDAR_TIMESTART, timeStart);
		initialValues.put(KEY_CALENDAR_TIMEEND, timeEnd);
		initialValues.put(KEY_CALENDAR_ACTIVITY, activity);
		initialValues.put(KEY_CALENDAR_CREATEDTIME, createdTime);
		initialValues.put(KEY_CALENDAR_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_CALENDAR_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, businessUnitName, businessUnitCode,
			// isActive, createdTime, modifiedTime, user);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_CALENDAR_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String activityType, String dateStart,
			String dueDate, String description, String subject,
			String timeStart, String timeEnd, long activity,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_CALENDAR_ACTIVITYTYPE, activityType);
		args.put(KEY_CALENDAR_DATESTART, dateStart);
		args.put(KEY_CALENDAR_DUEDATE, dueDate);
		args.put(KEY_CALENDAR_DESCRIPTION, description);
		args.put(KEY_CALENDAR_SUBJECT, subject);
		args.put(KEY_CALENDAR_TIMESTART, timeStart);
		args.put(KEY_CALENDAR_TIMEEND, timeEnd);
		args.put(KEY_CALENDAR_ACTIVITY, activity);
		args.put(KEY_CALENDAR_CREATEDTIME, createdTime);
		args.put(KEY_CALENDAR_MODIFIEDTIME, modifiedTime);
		args.put(KEY_CALENDAR_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_CALENDAR_ROWID + "=" + id,
				null) > 0) {
			// getRecords().update(id, no, businessUnitName, businessUnitCode,
			// isActive, createdTime, modifiedTime, user);
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
