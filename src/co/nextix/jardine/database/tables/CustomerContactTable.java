package co.nextix.jardine.database.tables;

import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_BIRTHDAY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_CREATEDBY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_CUSTOMER;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_EMAIL;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_FIRSTNAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_ISACTIVE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_LASTNAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_MOBILENO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_POSITION;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_CUSTOMERCONTACT_ROWID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.CustomerContactRecord;

public class CustomerContactTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	// private CustomerContactCollection customerContactRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public CustomerContactTable(SQLiteDatabase database, String tableName) {
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

	public List<CustomerContactRecord> getAllRecords() {
		Cursor c = null;
		List<CustomerContactRecord> list = new ArrayList<CustomerContactRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CRMNO));
					String firstName = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_FIRSTNAME));
					String lastName = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_LASTNAME));
					long position = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_POSITION));
					String mobileNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_MOBILENO));
					String birthday = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_BIRTHDAY));
					String emailAddress = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_EMAIL));
					long customer = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CUSTOMER));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDBY));

					list.add(new CustomerContactRecord(id, no, crmNo,
							firstName, lastName, position, mobileNo, birthday,
							emailAddress, customer, isActive, createdTime,
							modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<CustomerContactRecord> getAllRecordsByCustomerId(long custId) {
		Cursor c = null;
		List<CustomerContactRecord> list = new ArrayList<CustomerContactRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMERCONTACT_CUSTOMER + " = " + custId;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CRMNO));
					String firstName = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_FIRSTNAME));
					String lastName = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_LASTNAME));
					long position = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_POSITION));
					String mobileNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_MOBILENO));
					String birthday = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_BIRTHDAY));
					String emailAddress = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_EMAIL));
					long customer = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CUSTOMER));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDBY));

					list.add(new CustomerContactRecord(id, no, crmNo,
							firstName, lastName, position, mobileNo, birthday,
							emailAddress, customer, isActive, createdTime,
							modifiedTime, user));
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

	public List<CustomerContactRecord> getUnsyncedRecords() {
		List<CustomerContactRecord> list = new ArrayList<CustomerContactRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMERCONTACT_NO + " ISNULL";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CRMNO));
					String firstName = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_FIRSTNAME));
					String lastName = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_LASTNAME));
					long position = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_POSITION));
					String mobileNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_MOBILENO));
					String birthday = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_BIRTHDAY));
					String emailAddress = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_EMAIL));
					long customer = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CUSTOMER));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDBY));

					list.add(new CustomerContactRecord(id, no, crmNo,
							firstName, lastName, position, mobileNo, birthday,
							emailAddress, customer, isActive, createdTime,
							modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return list;
	}

	public boolean update(long id, String no, String modifiedTime, String crmNo) {
		ContentValues args = new ContentValues();
		args.put(KEY_CUSTOMERCONTACT_NO, no);
		args.put(KEY_CUSTOMERCONTACT_MODIFIEDTIME, modifiedTime);
		args.put(KEY_CUSTOMERCONTACT_CRMNO, crmNo);
		if (mDb.update(mDatabaseTable, args, KEY_CUSTOMERCONTACT_ROWID + "="
				+ id, null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateModifiedTime(long id, String modifiedTime) {
		ContentValues args = new ContentValues();
		args.put(KEY_CUSTOMERCONTACT_MODIFIEDTIME, modifiedTime);
		if (mDb.update(mDatabaseTable, args, KEY_CUSTOMERCONTACT_ROWID + "="
				+ id, null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isExisting(String webID) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMERCONTACT_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_CUSTOMERCONTACT_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_CUSTOMERCONTACT_NO
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
		String MY_QUERY = "SELECT " + KEY_CUSTOMERCONTACT_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_CUSTOMERCONTACT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_CUSTOMERCONTACT_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public CustomerContactRecord getById(long ID) {
		CustomerContactRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMERCONTACT_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c
						.getLong(c.getColumnIndex(KEY_CUSTOMERCONTACT_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_CRMNO));
				String firstName = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_FIRSTNAME));
				String lastName = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_LASTNAME));
				long position = c.getLong(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_POSITION));
				String mobileNo = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_MOBILENO));
				String birthday = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_BIRTHDAY));
				String emailAddress = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_EMAIL));
				long customer = c.getLong(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_CUSTOMER));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDBY));

				record = new CustomerContactRecord(id, no, crmNo, firstName,
						lastName, position, mobileNo, birthday, emailAddress,
						customer, isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public CustomerContactRecord getByWebId(String ID) {
		CustomerContactRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMERCONTACT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c
						.getLong(c.getColumnIndex(KEY_CUSTOMERCONTACT_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_CRMNO));
				String firstName = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_FIRSTNAME));
				String lastName = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_LASTNAME));
				long position = c.getLong(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_POSITION));
				String mobileNo = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_MOBILENO));
				String birthday = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_BIRTHDAY));
				String emailAddress = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_EMAIL));
				long customer = c.getLong(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_CUSTOMER));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_CUSTOMERCONTACT_CREATEDBY));

				record = new CustomerContactRecord(id, no, crmNo, firstName,
						lastName, position, mobileNo, birthday, emailAddress,
						customer, isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String firstName,
			String lastName, long position, String mobileNo, String birthday,
			String emailAddress, long customer, int isActive,
			String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// CustomerContactCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_CUSTOMERCONTACT_NO, no);
		initialValues.put(KEY_CUSTOMERCONTACT_CRMNO, crmNo);
		initialValues.put(KEY_CUSTOMERCONTACT_FIRSTNAME, firstName);
		initialValues.put(KEY_CUSTOMERCONTACT_LASTNAME, lastName);
		initialValues.put(KEY_CUSTOMERCONTACT_POSITION, position);
		initialValues.put(KEY_CUSTOMERCONTACT_MOBILENO, mobileNo);
		initialValues.put(KEY_CUSTOMERCONTACT_BIRTHDAY, birthday);
		initialValues.put(KEY_CUSTOMERCONTACT_EMAIL, emailAddress);
		initialValues.put(KEY_CUSTOMERCONTACT_CUSTOMER, customer);
		initialValues.put(KEY_CUSTOMERCONTACT_ISACTIVE, isActive);
		initialValues.put(KEY_CUSTOMERCONTACT_CREATEDTIME, createdTime);
		initialValues.put(KEY_CUSTOMERCONTACT_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_CUSTOMERCONTACT_CREATEDBY, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, firstName, lastName, position, mobileNo,
			// birthday, emailAddress, customer, isActive, createdTime,
			// modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_CUSTOMERCONTACT_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, String firstName,
			String lastName, long position, String mobileNo, String birthday,
			String emailAddress, long customer, int isActive,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_CUSTOMERCONTACT_NO, no);
		args.put(KEY_CUSTOMERCONTACT_CRMNO, crmNo);
		args.put(KEY_CUSTOMERCONTACT_FIRSTNAME, firstName);
		args.put(KEY_CUSTOMERCONTACT_LASTNAME, lastName);
		args.put(KEY_CUSTOMERCONTACT_POSITION, position);
		args.put(KEY_CUSTOMERCONTACT_MOBILENO, mobileNo);
		args.put(KEY_CUSTOMERCONTACT_BIRTHDAY, birthday);
		args.put(KEY_CUSTOMERCONTACT_EMAIL, emailAddress);
		args.put(KEY_CUSTOMERCONTACT_CUSTOMER, customer);
		args.put(KEY_CUSTOMERCONTACT_ISACTIVE, isActive);
		args.put(KEY_CUSTOMERCONTACT_CREATEDTIME, createdTime);
		args.put(KEY_CUSTOMERCONTACT_MODIFIEDTIME, modifiedTime);
		args.put(KEY_CUSTOMERCONTACT_CREATEDBY, user);
		if (mDb.update(mDatabaseTable, args, KEY_CUSTOMERCONTACT_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, firstName, lastName, position,
			// mobileNo, birthday, emailAddress, customer, isActive,
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
