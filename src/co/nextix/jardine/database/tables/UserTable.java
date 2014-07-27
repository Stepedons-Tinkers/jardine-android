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
import co.nextix.jardine.database.records.UserRecord;

public class UserTable {

	// ===========================================================
	// Private static fields
	// ===========================================================
	private static final String TAG = UserTable.class.getSimpleName();

	private final String KEY_USER_ROWID = "_id";
	private final String KEY_USER_NO = "no";
	private final String KEY_USER_USERNAME = "username";
	private final String KEY_USER_PASSWORD = "password";
	private final String KEY_USER_EMAILADDRESS = "email_address";
	private final String KEY_USER_LASTNAME = "last_name";
	private final String KEY_USER_MIDDLENAME = "middle_name";
	private final String KEY_USER_FIRSTNAME = "first_name";
	private final String KEY_USER_LOGGEDIN = "logged_in";
	private final String KEY_USER_STATUS = "status";
	private final String KEY_USER_LASTSYNC = "last_sync";
	private final String KEY_USER_AREA = "area";
	private final String KEY_USER_LOGGEDAREA = "logged_area";
	private final String KEY_USER_CREATEDTIME = "created_time";

	// ===========================================================
	// Private fields
	// ===========================================================

	// private UserCollection userRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public UserTable(SQLiteDatabase database, String tableName) {
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

	public List<UserRecord> getAllRecords() {
		Cursor c = null;
		List<UserRecord> list = new ArrayList<UserRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_USER_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_USER_NO));
					String username = c.getString(c
							.getColumnIndex(KEY_USER_USERNAME));
					String password = c.getString(c
							.getColumnIndex(KEY_USER_PASSWORD));
					String email = c.getString(c
							.getColumnIndex(KEY_USER_EMAILADDRESS));
					String lastname = c.getString(c
							.getColumnIndex(KEY_USER_LASTNAME));
					String middlename = c.getString(c
							.getColumnIndex(KEY_USER_MIDDLENAME));
					String firstname = c.getString(c
							.getColumnIndex(KEY_USER_FIRSTNAME));
					int loggedin = c
							.getInt(c.getColumnIndex(KEY_USER_LOGGEDIN));
					int status = c.getInt(c.getColumnIndex(KEY_USER_STATUS));
					String lastSync = c.getString(c
							.getColumnIndex(KEY_USER_LASTSYNC));
					String area = c.getString(c.getColumnIndex(KEY_USER_AREA));
					String loggedArea = c.getString(c
							.getColumnIndex(KEY_USER_LOGGEDAREA));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_USER_CREATEDTIME));

					list.add(new UserRecord(id, no, username, password, email,
							lastname, middlename, firstname, loggedin, status,
							lastSync, area, loggedArea, createdTime));
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
				+ KEY_USER_NO + "='" + webID + "'";
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

	public UserRecord getCurrentUser() {
		UserRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_USER_LOGGEDIN + "=1";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_USER_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_USER_NO));
				String username = c.getString(c
						.getColumnIndex(KEY_USER_USERNAME));
				String password = c.getString(c
						.getColumnIndex(KEY_USER_PASSWORD));
				String email = c.getString(c
						.getColumnIndex(KEY_USER_EMAILADDRESS));
				String lastname = c.getString(c
						.getColumnIndex(KEY_USER_LASTNAME));
				String middlename = c.getString(c
						.getColumnIndex(KEY_USER_MIDDLENAME));
				String firstname = c.getString(c
						.getColumnIndex(KEY_USER_FIRSTNAME));
				int loggedin = c.getInt(c.getColumnIndex(KEY_USER_LOGGEDIN));
				int status = c.getInt(c.getColumnIndex(KEY_USER_STATUS));
				String lastSync = c.getString(c
						.getColumnIndex(KEY_USER_LASTSYNC));
				String area = c.getString(c.getColumnIndex(KEY_USER_AREA));
				String loggedArea = c.getString(c
						.getColumnIndex(KEY_USER_LOGGEDAREA));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_USER_CREATEDTIME));

				record = new UserRecord(id, no, username, password, email,
						lastname, middlename, firstname, loggedin, status,
						lastSync, area, loggedArea, createdTime);
				Log.i(JardineApp.TAG, "Usertable: getCurrent");
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return record;
	}

	public int deleteById(long[] rowIds) {

		String ids = Arrays.toString(rowIds);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "");

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_USER_ROWID + " IN ("
				+ ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public UserRecord getById(long ID) {
		UserRecord user = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_USER_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_USER_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_USER_NO));
				String username = c.getString(c
						.getColumnIndex(KEY_USER_USERNAME));
				String password = c.getString(c
						.getColumnIndex(KEY_USER_PASSWORD));
				String email = c.getString(c
						.getColumnIndex(KEY_USER_EMAILADDRESS));
				String lastname = c.getString(c
						.getColumnIndex(KEY_USER_LASTNAME));
				String middlename = c.getString(c
						.getColumnIndex(KEY_USER_MIDDLENAME));
				String firstname = c.getString(c
						.getColumnIndex(KEY_USER_FIRSTNAME));
				int loggedin = c.getInt(c.getColumnIndex(KEY_USER_LOGGEDIN));
				int status = c.getInt(c.getColumnIndex(KEY_USER_STATUS));
				String lastSync = c.getString(c
						.getColumnIndex(KEY_USER_LASTSYNC));
				String area = c.getString(c.getColumnIndex(KEY_USER_AREA));
				String loggedArea = c.getString(c
						.getColumnIndex(KEY_USER_LOGGEDAREA));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_USER_CREATEDTIME));

				user = new UserRecord(id, no, username, password, email,
						lastname, middlename, firstname, loggedin, status,
						lastSync, area, loggedArea, createdTime);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return user;
	}

	public String getNoById(long ID) {
		String result = null;
		String MY_QUERY = "SELECT " + KEY_USER_NO + " FROM " + mDatabaseTable
				+ " WHERE " + KEY_USER_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_USER_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public String getLastSync() {
		String result = "";
		String MY_QUERY = "SELECT " + KEY_USER_LASTSYNC + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_USER_LOGGEDIN + "=1";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_USER_LASTSYNC));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public UserRecord getByWebId(String ID) {
		UserRecord user = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_USER_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_USER_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_USER_NO));
				String username = c.getString(c
						.getColumnIndex(KEY_USER_USERNAME));
				String password = c.getString(c
						.getColumnIndex(KEY_USER_PASSWORD));
				String email = c.getString(c
						.getColumnIndex(KEY_USER_EMAILADDRESS));
				String lastname = c.getString(c
						.getColumnIndex(KEY_USER_LASTNAME));
				String middlename = c.getString(c
						.getColumnIndex(KEY_USER_MIDDLENAME));
				String firstname = c.getString(c
						.getColumnIndex(KEY_USER_FIRSTNAME));
				int loggedin = c.getInt(c.getColumnIndex(KEY_USER_LOGGEDIN));
				int status = c.getInt(c.getColumnIndex(KEY_USER_STATUS));
				String lastSync = c.getString(c
						.getColumnIndex(KEY_USER_LASTSYNC));
				String area = c.getString(c.getColumnIndex(KEY_USER_AREA));
				String loggedArea = c.getString(c
						.getColumnIndex(KEY_USER_LOGGEDAREA));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_USER_CREATEDTIME));

				user = new UserRecord(id, no, username, password, email,
						lastname, middlename, firstname, loggedin, status,
						lastSync, area, loggedArea, createdTime);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return user;
	}

	public long getIdByNo(String no) {
		long result = 0;
		String MY_QUERY = "SELECT " + KEY_USER_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_USER_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_USER_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public long insertUser(String no, String username, String password,
			String email, String lastname, String middlename, String firstname,
			int loggedin, int status, String lastSync, String area,
			String loggedInArea, String createdTime) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// UserCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_USER_NO, no);
		initialValues.put(KEY_USER_USERNAME, username);
		initialValues.put(KEY_USER_PASSWORD, password);
		initialValues.put(KEY_USER_EMAILADDRESS, email);
		initialValues.put(KEY_USER_LASTNAME, lastname);
		initialValues.put(KEY_USER_MIDDLENAME, middlename);
		initialValues.put(KEY_USER_FIRSTNAME, firstname);
		initialValues.put(KEY_USER_LOGGEDIN, loggedin);
		initialValues.put(KEY_USER_STATUS, status);
		initialValues.put(KEY_USER_LASTSYNC, lastSync);
		initialValues.put(KEY_USER_AREA, area);
		initialValues.put(KEY_USER_LOGGEDAREA, loggedInArea);
		initialValues.put(KEY_USER_CREATEDTIME, createdTime);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, username, password, email, lastname,
			// middlename, firstname, loggedin, status, createdTime);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_USER_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, String username,
			String password, String email, String lastname, String middlename,
			String firstname, String area, String loggedInArea, int loggedin) {
		ContentValues args = new ContentValues();
		args.put(KEY_USER_NO, no);
		args.put(KEY_USER_USERNAME, username);
		args.put(KEY_USER_PASSWORD, password);
		args.put(KEY_USER_EMAILADDRESS, email);
		args.put(KEY_USER_LASTNAME, lastname);
		args.put(KEY_USER_MIDDLENAME, middlename);
		args.put(KEY_USER_FIRSTNAME, firstname);
		args.put(KEY_USER_LOGGEDIN, loggedin);
		args.put(KEY_USER_AREA, area);
		args.put(KEY_USER_LOGGEDAREA, loggedInArea);
		if (mDb.update(mDatabaseTable, args, KEY_USER_ROWID + "=" + id, null) > 0) {
			// getRecords().update(id, no, username, password, email, lastname,
			// middlename, firstname, loggedin, lastSync);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateLogStatus(long id, int loggedin) {
		ContentValues args = new ContentValues();
		args.put(KEY_USER_LOGGEDIN, loggedin);
		if (mDb.update(mDatabaseTable, args, KEY_USER_ROWID + "=" + id, null) > 0) {
			// getRecords().updateLogStat(id, loggedin);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateLastsync(long id, String lastSync) {
		ContentValues args = new ContentValues();
		args.put(KEY_USER_LASTSYNC, lastSync);
		if (mDb.update(mDatabaseTable, args, KEY_USER_ROWID + "=" + id, null) > 0) {
			// getRecords().updateLogStat(id, loggedin);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateLoggedArea(long id, String loggedArea) {
		ContentValues args = new ContentValues();
		args.put(KEY_USER_LOGGEDAREA, loggedArea);
		if (mDb.update(mDatabaseTable, args, KEY_USER_ROWID + "=" + id, null) > 0) {
			// getRecords().updateLogStat(id, loggedin);
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		String MY_QUERY = "DELETE FROM " + mDatabaseTable;
		try {
			mDb.execSQL(MY_QUERY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
