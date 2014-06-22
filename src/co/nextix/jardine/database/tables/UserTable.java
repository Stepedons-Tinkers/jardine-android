package co.nextix.jardine.database.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.UserRecord;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

	private List<UserRecord> getAllRecords() {
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
					String createdTime = c.getString(c
							.getColumnIndex(KEY_USER_CREATEDTIME));

					list.add(new UserRecord(id, no, username, password, email,
							lastname, middlename, firstname, loggedin, status,
							lastSync, createdTime));
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
				String createdTime = c.getString(c
						.getColumnIndex(KEY_USER_CREATEDTIME));

				record = new UserRecord(id, no, username, password, email,
						lastname, middlename, firstname, loggedin, status,
						lastSync, createdTime);
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
				String createdTime = c.getString(c
						.getColumnIndex(KEY_USER_CREATEDTIME));

				user = new UserRecord(id, no, username, password, email,
						lastname, middlename, firstname, loggedin, status,
						lastSync, createdTime);
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
				String createdTime = c.getString(c
						.getColumnIndex(KEY_USER_CREATEDTIME));

				user = new UserRecord(id, no, username, password, email,
						lastname, middlename, firstname, loggedin, status,
						lastSync, createdTime);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return user;
	}

	public long insertUser(String no, String username, String password,
			String email, String lastname, String middlename, String firstname,
			int loggedin, int status, String lastSync, String createdTime) {
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
			String firstname, int loggedin, String lastSync) {
		ContentValues args = new ContentValues();
		args.put(KEY_USER_NO, no);
		args.put(KEY_USER_USERNAME, username);
		args.put(KEY_USER_PASSWORD, password);
		args.put(KEY_USER_EMAILADDRESS, email);
		args.put(KEY_USER_LASTNAME, lastname);
		args.put(KEY_USER_MIDDLENAME, middlename);
		args.put(KEY_USER_FIRSTNAME, firstname);
		args.put(KEY_USER_LOGGEDIN, loggedin);
		args.put(KEY_USER_LASTSYNC, lastSync);
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
	//
	// public UserCollection getRecords() {
	// if (userRecords == null) {
	// userRecords = new UserCollection();
	// userRecords.users = getAllRecords();
	// }
	// return userRecords;
	// }
	//
	// public final class UserCollection implements Iterable<UserRecord> {
	//
	// private List<UserRecord> users;
	//
	// private UserCollection() {
	// }
	//
	// public int size() {
	// return users.size();
	// }
	//
	// public UserRecord get(int i) {
	// return users.get(i);
	// }
	//
	// public UserRecord getById(long id) {
	// for (UserRecord record : users) {
	// if (record.getId() == id) {
	// return record;
	// }
	// }
	// return null;
	// }
	//
	// private void add(long id, String no, String username, String password,
	// String email, String lastname, String middlename,
	// String firstname, int loggedin, int status, String createdTime) {
	// users.add(new UserRecord(id, no, username, password, email,
	// lastname, middlename, firstname, loggedin, status,
	// createdTime));
	// }
	//
	// private void clear() {
	// users.clear();
	// }
	//
	// private void deleteById(long id) {
	// users.remove(getById(id));
	// }
	//
	// private void update(long id, String no, String username,
	// String password, String email, String lastname,
	// String middlename, String firstname, int loggedin) {
	// UserRecord record = getById(id);
	// record.setNo(no);
	// record.setUsername(username);
	// record.setPassword(password);
	// record.setEmail(email);
	// record.setLastname(lastname);
	// record.setMiddleName(middlename);
	// record.setFirstNameName(firstname);
	// record.setLoggedIn(loggedin);
	// }
	//
	// private void updateLogStat(long id, int loggedin) {
	// UserRecord record = getById(id);
	// record.setLoggedIn(loggedin);
	// }
	//
	// @Override
	// public Iterator<UserRecord> iterator() {
	// Iterator<UserRecord> iter = new Iterator<UserRecord>() {
	// private int current = 0;
	//
	// @Override
	// public void remove() {
	// if (users.size() > 0) {
	// deleteUser(users.get(current).getId());
	// deleteById(users.get(current).getId());
	// users.remove(current);
	// }
	// }
	//
	// @Override
	// public UserRecord next() {
	// if (users.size() > 0) {
	// return users.get(current++);
	// }
	// return null;
	// }
	//
	// @Override
	// public boolean hasNext() {
	// return users.size() > 0 && current < users.size();
	// }
	// };
	// return iter;
	// }
	// }
}
