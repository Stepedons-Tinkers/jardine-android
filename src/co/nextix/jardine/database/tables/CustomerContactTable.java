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
import co.nextix.jardine.database.records.CustomerContactRecord;

public class CustomerContactTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_CUSTOMERCONTACT_ROWID = "_id";
	private final String KEY_CUSTOMERCONTACT_NO = "no";
	private final String KEY_CUSTOMERCONTACT_FIRSTNAME = "first_name";
	private final String KEY_CUSTOMERCONTACT_LASTNAME = "last_name";
	private final String KEY_CUSTOMERCONTACT_POSITION = "position";
	private final String KEY_CUSTOMERCONTACT_MOBILENO = "mobile_no";
	private final String KEY_CUSTOMERCONTACT_BIRTHDAY = "birthday";
	private final String KEY_CUSTOMERCONTACT_EMAIL = "email_address";
	private final String KEY_CUSTOMERCONTACT_CUSTOMER = "customer";
	private final String KEY_CUSTOMERCONTACT_ISACTIVE = "is_active";
	private final String KEY_CUSTOMERCONTACT_CREATEDTIME = "created_time";
	private final String KEY_CUSTOMERCONTACT_MODIFIEDTIME = "modified_time";
	private final String KEY_CUSTOMERCONTACT_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private CustomerContactCollection customerContactRecords;
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

	private DatabaseAdapter getDBAdapter() {
		if (mDBAdapter == null)
			mDBAdapter = DatabaseAdapter.getInstance();

		return mDBAdapter;
	}

	// ===========================================================
	// Private methods
	// ===========================================================

	private List<CustomerContactRecord> getAllRecords() {
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
							.getColumnIndex(KEY_CUSTOMERCONTACT_USER));

					list.add(new CustomerContactRecord(id, no, firstName,
							lastName, position, mobileNo, birthday,
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

	public CustomerContactRecord getById(int ID) {
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
						.getColumnIndex(KEY_CUSTOMERCONTACT_USER));

				record = new CustomerContactRecord(id, no, firstName, lastName,
						position, mobileNo, birthday, emailAddress, customer,
						isActive, createdTime, modifiedTime, user);
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
						.getColumnIndex(KEY_CUSTOMERCONTACT_USER));

				record = new CustomerContactRecord(id, no, firstName, lastName,
						position, mobileNo, birthday, emailAddress, customer,
						isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, String firstName, String lastName,
			long position, String mobileNo, String birthday,
			String emailAddress, long customer, int isActive,
			String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		CustomerContactCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_CUSTOMERCONTACT_NO, no);
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
		initialValues.put(KEY_CUSTOMERCONTACT_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, firstName, lastName, position, mobileNo,
					birthday, emailAddress, customer, isActive, createdTime,
					modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_CUSTOMERCONTACT_ROWID + "=" + rowId,
				null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, String firstName,
			String lastName, long position, String mobileNo, String birthday,
			String emailAddress, long customer, int isActive,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_CUSTOMERCONTACT_NO, no);
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
		args.put(KEY_CUSTOMERCONTACT_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_CUSTOMERCONTACT_ROWID + "="
				+ id, null) > 0) {
			getRecords().update(id, no, firstName, lastName, position,
					mobileNo, birthday, emailAddress, customer, isActive,
					createdTime, modifiedTime, user);
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		String MY_QUERY = "DELETE FROM " + mDatabaseTable;
		try {
			mDb.execSQL(MY_QUERY);
			getRecords().clear();
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

	public CustomerContactCollection getRecords() {
		if (customerContactRecords == null) {
			customerContactRecords = new CustomerContactCollection();
			customerContactRecords.list = getAllRecords();
		}
		return customerContactRecords;
	}

	public final class CustomerContactCollection implements
			Iterable<CustomerContactRecord> {

		private List<CustomerContactRecord> list;

		private CustomerContactCollection() {
		}

		public int size() {
			return list.size();
		}

		public CustomerContactRecord get(int i) {
			return list.get(i);
		}

		public CustomerContactRecord getById(long id) {
			for (CustomerContactRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, String firstName, String lastName,
				long position, String mobileNo, String birthday,
				String emailAddress, long customer, int isActive,
				String createdTime, String modifiedTime, long user) {
			list.add(new CustomerContactRecord(id, no, firstName, lastName,
					position, mobileNo, birthday, emailAddress, customer,
					isActive, createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, String firstName,
				String lastName, long position, String mobileNo,
				String birthday, String emailAddress, long customer,
				int isActive, String createdTime, String modifiedTime, long user) {
			CustomerContactRecord record = getById(id);
			record.setNo(no);
			record.setFirstName(firstName);
			record.setLastName(lastName);
			record.setPosition(position);
			record.setMobileNo(mobileNo);
			record.setBirthday(birthday);
			record.setEmailAddress(emailAddress);
			record.setCustomer(customer);
			record.setIsActive(isActive);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<CustomerContactRecord> iterator() {
			Iterator<CustomerContactRecord> iter = new Iterator<CustomerContactRecord>() {
				private int current = 0;

				@Override
				public void remove() {
					if (list.size() > 0) {
						deleteUser(list.get(current).getId());
						deleteById(list.get(current).getId());
						list.remove(current);
					}
				}

				@Override
				public CustomerContactRecord next() {
					if (list.size() > 0) {
						return list.get(current++);
					}
					return null;
				}

				@Override
				public boolean hasNext() {
					return list.size() > 0 && current < list.size();
				}
			};
			return iter;
		}
	}
}
