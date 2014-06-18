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
import co.nextix.jardine.database.records.SupplierRecord;

public class SupplierTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_SUPPLIER_ROWID = "_id";
	private final String KEY_SUPPLIER_NO = "no";
	private final String KEY_SUPPLIER_NAME = "supplier_name";
	private final String KEY_SUPPLIER_LANDLINE = "supplier_landline";
	private final String KEY_SUPPLIER_ADDRESS = "supplier_address";
	private final String KEY_SUPPLIER_CREDITLINE = "credit_line";
	private final String KEY_SUPPLIER_CREDITTERM = "credit_term";
	private final String KEY_SUPPLIER_CONTACTPERSON = "contact_person";
	private final String KEY_SUPPLIER_ISACTIVE = "is_active";
	private final String KEY_SUPPLIER_CREATEDTIME = "created_time";
	private final String KEY_SUPPLIER_MODIFIEDTIME = "modified_time";
	private final String KEY_SUPPLIER_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private SupplierCollection supplierCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public SupplierTable(SQLiteDatabase database, String tableName) {
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

	private List<SupplierRecord> getAllRecords() {
		Cursor c = null;
		List<SupplierRecord> list = new ArrayList<SupplierRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_SUPPLIER_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_SUPPLIER_NO));
					String supplierName = c.getString(c
							.getColumnIndex(KEY_SUPPLIER_NAME));
					String supplierLandline = c.getString(c
							.getColumnIndex(KEY_SUPPLIER_LANDLINE));
					String supplierAddress = c.getString(c
							.getColumnIndex(KEY_SUPPLIER_ADDRESS));
					String creditLine = c.getString(c
							.getColumnIndex(KEY_SUPPLIER_CREDITLINE));
					String creditTerm = c.getString(c
							.getColumnIndex(KEY_SUPPLIER_CREDITTERM));
					String contactPerson = c.getString(c
							.getColumnIndex(KEY_SUPPLIER_CONTACTPERSON));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_SUPPLIER_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_SUPPLIER_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_SUPPLIER_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_SUPPLIER_USER));

					list.add(new SupplierRecord(id, no, supplierName,
							supplierLandline, supplierAddress, creditLine,
							creditTerm, contactPerson, isActive, createdTime,
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
				+ KEY_SUPPLIER_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_SUPPLIER_ROWID
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public SupplierRecord getById(int ID) {
		SupplierRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SUPPLIER_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_SUPPLIER_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_SUPPLIER_NO));
				String supplierName = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_NAME));
				String supplierLandline = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_LANDLINE));
				String supplierAddress = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_ADDRESS));
				String creditLine = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_CREDITLINE));
				String creditTerm = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_CREDITTERM));
				String contactPerson = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_CONTACTPERSON));
				int isActive = c
						.getInt(c.getColumnIndex(KEY_SUPPLIER_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_SUPPLIER_USER));

				record = new SupplierRecord(id, no, supplierName,
						supplierLandline, supplierAddress, creditLine,
						creditTerm, contactPerson, isActive, createdTime,
						modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public SupplierRecord getByWebId(String ID) {
		SupplierRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_SUPPLIER_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_SUPPLIER_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_SUPPLIER_NO));
				String supplierName = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_NAME));
				String supplierLandline = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_LANDLINE));
				String supplierAddress = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_ADDRESS));
				String creditLine = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_CREDITLINE));
				String creditTerm = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_CREDITTERM));
				String contactPerson = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_CONTACTPERSON));
				int isActive = c
						.getInt(c.getColumnIndex(KEY_SUPPLIER_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_SUPPLIER_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_SUPPLIER_USER));

				record = new SupplierRecord(id, no, supplierName,
						supplierLandline, supplierAddress, creditLine,
						creditTerm, contactPerson, isActive, createdTime,
						modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, String supplierName,
			String supplierLandline, String supplierAddress, String creditLine,
			String creditTerm, String contactPerson, int isActive,
			String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		SupplierCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_SUPPLIER_NO, no);
		initialValues.put(KEY_SUPPLIER_NAME, supplierName);
		initialValues.put(KEY_SUPPLIER_LANDLINE, supplierLandline);
		initialValues.put(KEY_SUPPLIER_ADDRESS, supplierAddress);
		initialValues.put(KEY_SUPPLIER_CREDITLINE, creditLine);
		initialValues.put(KEY_SUPPLIER_CREDITTERM, creditTerm);
		initialValues.put(KEY_SUPPLIER_CONTACTPERSON, contactPerson);
		initialValues.put(KEY_SUPPLIER_ISACTIVE, isActive);
		initialValues.put(KEY_SUPPLIER_CREATEDTIME, createdTime);
		initialValues.put(KEY_SUPPLIER_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_SUPPLIER_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, supplierName, supplierLandline,
					supplierAddress, creditLine, creditTerm, contactPerson,
					isActive, createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_SUPPLIER_ROWID + "=" + rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, String supplierName,
			String supplierLandline, String supplierAddress, String creditLine,
			String creditTerm, String contactPerson, int isActive,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_SUPPLIER_NO, no);
		args.put(KEY_SUPPLIER_NAME, supplierName);
		args.put(KEY_SUPPLIER_LANDLINE, supplierLandline);
		args.put(KEY_SUPPLIER_ADDRESS, supplierAddress);
		args.put(KEY_SUPPLIER_CREDITLINE, creditLine);
		args.put(KEY_SUPPLIER_CREDITTERM, creditTerm);
		args.put(KEY_SUPPLIER_CONTACTPERSON, contactPerson);
		args.put(KEY_SUPPLIER_ISACTIVE, isActive);
		args.put(KEY_SUPPLIER_CREATEDTIME, createdTime);
		args.put(KEY_SUPPLIER_MODIFIEDTIME, modifiedTime);
		args.put(KEY_SUPPLIER_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_SUPPLIER_ROWID + "=" + id,
				null) > 0) {
			getRecords().update(id, no, supplierName, supplierLandline,
					supplierAddress, creditLine, creditTerm, contactPerson,
					isActive, createdTime, modifiedTime, user);
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

	public SupplierCollection getRecords() {
		if (supplierCollection == null) {
			supplierCollection = new SupplierCollection();
			supplierCollection.list = getAllRecords();
		}
		return supplierCollection;
	}

	public final class SupplierCollection implements Iterable<SupplierRecord> {

		private List<SupplierRecord> list;

		private SupplierCollection() {
		}

		public int size() {
			return list.size();
		}

		public SupplierRecord get(int i) {
			return list.get(i);
		}

		public SupplierRecord getById(long id) {
			for (SupplierRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, String supplierName,
				String supplierLandline, String supplierAddress,
				String creditLine, String creditTerm, String contactPerson,
				int isActive, String createdTime, String modifiedTime, long user) {
			list.add(new SupplierRecord(id, no, supplierName, supplierLandline,
					supplierAddress, creditLine, creditTerm, contactPerson,
					isActive, createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, String supplierName,
				String supplierLandline, String supplierAddress,
				String creditLine, String creditTerm, String contactPerson,
				int isActive, String createdTime, String modifiedTime, long user) {
			SupplierRecord record = getById(id);
			record.setNo(no);
			record.setSupplierName(supplierName);
			record.setSupplierLandline(supplierLandline);
			record.setSupplierAddress(supplierAddress);
			record.setCreditLine(creditLine);
			record.setCreditTerm(creditTerm);
			record.setContactPerson(contactPerson);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<SupplierRecord> iterator() {
			Iterator<SupplierRecord> iter = new Iterator<SupplierRecord>() {
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
				public SupplierRecord next() {
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
