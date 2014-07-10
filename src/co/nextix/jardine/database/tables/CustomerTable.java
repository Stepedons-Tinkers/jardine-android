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
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.SMRtimeCardRecord;

public class CustomerTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_CUSTOMER_ROWID = "_id";
	private final String KEY_CUSTOMER_NO = "no";
	private final String KEY_CUSTOMER_NAME = "customer_name";
	private final String KEY_CUSTOMER_CHAINNAME = "chain_name";
	private final String KEY_CUSTOMER_LANDLINE = "landline";
	private final String KEY_CUSTOMER_FAX = "fax";
	private final String KEY_CUSTOMER_SIZE = "customer_size";
	private final String KEY_CUSTOMER_STREETADDRESS = "street_address";
	// private final String KEY_CUSTOMER_RECORDSTATUS =
	// "customer_record_status";
	private final String KEY_CUSTOMER_TYPE = "customer_type";
	private final String KEY_CUSTOMER_BUSINESSUNIT = "business_unit";
	private final String KEY_CUSTOMER_AREA = "area";
	private final String KEY_CUSTOMER_PROVINCE = "province";
	private final String KEY_CUSTOMER_CITYTOWN = "city_town";
	private final String KEY_CUSTOMER_ISACTIVE = "is_active";
	private final String KEY_CUSTOMER_CREATEDTIME = "created_time";
	private final String KEY_CUSTOMER_MODIFIEDTIME = "modified_time";
	private final String KEY_CUSTOMER_USER = "user";

	private final String KEY_CUSTOMER_CRMNO = "crm_no";

	// ===========================================================
	// Private fields
	// ===========================================================

	// private CustomerCollection customerRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public CustomerTable(SQLiteDatabase database, String tableName) {
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

	public List<CustomerRecord> getAllRecords() {
		Cursor c = null;
		List<CustomerRecord> list = new ArrayList<CustomerRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_CUSTOMER_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_CUSTOMER_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CRMNO));
					String customerName = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_NAME));
					String chainName = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CHAINNAME));
					String landline = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_LANDLINE));
					String fax = c
							.getString(c.getColumnIndex(KEY_CUSTOMER_FAX));
					long customerSize = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_SIZE));
					String streetAddress = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_STREETADDRESS));
					// long customerRecordStatus = c.getLong(c
					// .getColumnIndex(KEY_CUSTOMER_RECORDSTATUS));
					long customerType = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_TYPE));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_BUSINESSUNIT));
					long area = c.getLong(c.getColumnIndex(KEY_CUSTOMER_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_PROVINCE));
					long cityTown = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_CITYTOWN));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_CUSTOMER_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_CUSTOMER_USER));

					// list.add(new CustomerRecord(id, no, customerName,
					// chainName, landline, fax, customerSize,
					// streetAddress, customerRecordStatus, customerType,
					// businessUnit,
					// area, province, cityTown, isActive, createdTime,
					// modifiedTime, user));
					list.add(new CustomerRecord(id, no, crmNo, customerName,
							chainName, landline, fax, customerSize,
							streetAddress, customerType, businessUnit, area,
							province, cityTown, isActive, createdTime,
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

	public List<CustomerRecord> getAllRecordsBySearch(String hint, int column) {
		Cursor c = null;
		List<CustomerRecord> list = new ArrayList<CustomerRecord>();
		String MY_QUERY = "";

		switch (column) {
		case 0:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_CUSTOMER_CRMNO + " LIKE " + "'%" + hint + "%'";
			break;
		case 1:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_CUSTOMER_NAME + " LIKE " + "'%" + hint + "%'";
			break;
		case 2:

			break;
		case 3:
			// SELECT Customer.* FROM Customer inner join Area on Customer.area
			// = Area._id Where Area.name LIKE 'north%'
			MY_QUERY = "SELECT " + mDatabaseTable + ".* FROM " + mDatabaseTable
					+ " INNER JOIN Area ON " + mDatabaseTable + "."
					+ KEY_CUSTOMER_AREA + " = Area._id WHERE Area.name LIKE '"
					+ hint + "%'";
			break;
		case 4:
			// SELECT Customer.* FROM Customer inner join Province on
			// Customer.province = Province._id Where Province.name LIKE
			// '%bata%'
			MY_QUERY = "SELECT " + mDatabaseTable + ".* FROM " + mDatabaseTable
					+ " INNER JOIN Province ON " + mDatabaseTable + "."
					+ KEY_CUSTOMER_PROVINCE
					+ " = Province._id WHERE Province.name LIKE '%" + hint
					+ "%'";

			break;
		case 5:
			// SELECT Customer.* FROM Customer inner join City_Town on
			// Customer.city_town = City_Town._id Where City_Town.name LIKE
			// '%veri%'
			MY_QUERY = "SELECT " + mDatabaseTable + ".* FROM " + mDatabaseTable
					+ " INNER JOIN City_Town ON " + mDatabaseTable + "."
					+ KEY_CUSTOMER_CITYTOWN
					+ " = City_Town._id WHERE City_Town.name LIKE '%" + hint
					+ "%'";

			break;
		case 6:
			break;
		default:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_CUSTOMER_NAME + " LIKE " + "'%" + hint + "%'";
			break;

		}

		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_CUSTOMER_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_CUSTOMER_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CRMNO));
					String customerName = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_NAME));
					String chainName = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CHAINNAME));
					String landline = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_LANDLINE));
					String fax = c
							.getString(c.getColumnIndex(KEY_CUSTOMER_FAX));
					long customerSize = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_SIZE));
					String streetAddress = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_STREETADDRESS));
					// long customerRecordStatus = c.getLong(c
					// .getColumnIndex(KEY_CUSTOMER_RECORDSTATUS));
					long customerType = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_TYPE));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_BUSINESSUNIT));
					long area = c.getLong(c.getColumnIndex(KEY_CUSTOMER_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_PROVINCE));
					long cityTown = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_CITYTOWN));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_CUSTOMER_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_CUSTOMER_USER));

					// list.add(new CustomerRecord(id, no, customerName,
					// chainName, landline, fax, customerSize,
					// streetAddress, customerRecordStatus, customerType,
					// businessUnit,
					// area, province, cityTown, isActive, createdTime,
					// modifiedTime, user));
					list.add(new CustomerRecord(id, no, crmNo, customerName,
							chainName, landline, fax, customerSize,
							streetAddress, customerType, businessUnit, area,
							province, cityTown, isActive, createdTime,
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

	public List<CustomerRecord> getUnsyncedRecords() {
		List<CustomerRecord> list = new ArrayList<CustomerRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMER_NO + " ISNULL";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_CUSTOMER_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_CUSTOMER_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CRMNO));
					String customerName = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_NAME));
					String chainName = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CHAINNAME));
					String landline = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_LANDLINE));
					String fax = c
							.getString(c.getColumnIndex(KEY_CUSTOMER_FAX));
					long customerSize = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_SIZE));
					String streetAddress = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_STREETADDRESS));
					// long customerRecordStatus = c.getLong(c
					// .getColumnIndex(KEY_CUSTOMER_RECORDSTATUS));
					long customerType = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_TYPE));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_BUSINESSUNIT));
					long area = c.getLong(c.getColumnIndex(KEY_CUSTOMER_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_PROVINCE));
					long cityTown = c.getLong(c
							.getColumnIndex(KEY_CUSTOMER_CITYTOWN));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_CUSTOMER_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_CUSTOMER_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_CUSTOMER_USER));

					// list.add(new CustomerRecord(id, no, customerName,
					// chainName, landline, fax, customerSize,
					// streetAddress, customerRecordStatus, customerType,
					// businessUnit,
					// area, province, cityTown, isActive, createdTime,
					// modifiedTime, user));
					list.add(new CustomerRecord(id, no, crmNo, customerName,
							chainName, landline, fax, customerSize,
							streetAddress, customerType, businessUnit, area,
							province, cityTown, isActive, createdTime,
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

	public boolean updateNo(long id, String no) {
		ContentValues args = new ContentValues();
		args.put(KEY_CUSTOMER_NO, no);
		if (mDb.update(mDatabaseTable, args, KEY_CUSTOMER_ROWID + "=" + id,
				null) > 0) {
			// getRecords().update(id, no, competitor, productBrand,
			// productDescription, productSize, isActive, createdTime,
			// modifiedTime, user);
			return true;
		} else {
			return false;
		}
	}

	public boolean isExisting(String webID) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMER_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_CUSTOMER_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_CUSTOMER_NO + " IN ("
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
		String MY_QUERY = "SELECT " + KEY_CUSTOMER_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_CUSTOMER_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_CUSTOMER_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public CustomerRecord getById(long ID) {
		CustomerRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMER_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_CUSTOMER_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_CUSTOMER_NO));
				String crmNo = c
						.getString(c.getColumnIndex(KEY_CUSTOMER_CRMNO));
				String customerName = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_NAME));
				String chainName = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_CHAINNAME));
				String landline = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_LANDLINE));
				String fax = c.getString(c.getColumnIndex(KEY_CUSTOMER_FAX));
				long customerSize = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_SIZE));
				String streetAddress = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_STREETADDRESS));
				// long customerRecordStatus = c.getLong(c
				// .getColumnIndex(KEY_CUSTOMER_RECORDSTATUS));
				long customerType = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_TYPE));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_BUSINESSUNIT));
				long area = c.getLong(c.getColumnIndex(KEY_CUSTOMER_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_PROVINCE));
				long cityTown = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_CITYTOWN));
				int isActive = c
						.getInt(c.getColumnIndex(KEY_CUSTOMER_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_CUSTOMER_USER));

				// record = new CustomerRecord(id, no, customerName, chainName,
				// landline, fax, customerSize, streetAddress,
				// customerRecordStatus, customerType, businessUnit,
				// area, province, cityTown, isActive, createdTime,
				// modifiedTime, user);

				record = new CustomerRecord(id, no, crmNo, customerName,
						chainName, landline, fax, customerSize, streetAddress,
						customerType, businessUnit, area, province, cityTown,
						isActive, createdTime, modifiedTime, user);
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
		String MY_QUERY = "SELECT " + KEY_CUSTOMER_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_CUSTOMER_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_CUSTOMER_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public CustomerRecord getByWebId(String ID) {
		CustomerRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_CUSTOMER_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_CUSTOMER_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_CUSTOMER_NO));
				String crmNo = c
						.getString(c.getColumnIndex(KEY_CUSTOMER_CRMNO));
				String customerName = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_NAME));
				String chainName = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_CHAINNAME));
				String landline = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_LANDLINE));
				String fax = c.getString(c.getColumnIndex(KEY_CUSTOMER_FAX));
				long customerSize = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_SIZE));
				String streetAddress = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_STREETADDRESS));
				// long customerRecordStatus = c.getLong(c
				// .getColumnIndex(KEY_CUSTOMER_RECORDSTATUS));
				long customerType = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_TYPE));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_BUSINESSUNIT));
				long area = c.getLong(c.getColumnIndex(KEY_CUSTOMER_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_PROVINCE));
				long cityTown = c.getLong(c
						.getColumnIndex(KEY_CUSTOMER_CITYTOWN));
				int isActive = c
						.getInt(c.getColumnIndex(KEY_CUSTOMER_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_CUSTOMER_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_CUSTOMER_USER));

				// record = new CustomerRecord(id, no, customerName, chainName,
				// landline, fax, customerSize, streetAddress,
				// customerRecordStatus, customerType, businessUnit, area,
				// province, cityTown, isActive, createdTime,
				// modifiedTime, user);

				record = new CustomerRecord(id, no, crmNo, customerName,
						chainName, landline, fax, customerSize, streetAddress,
						customerType, businessUnit, area, province, cityTown,
						isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String customerName,
			String chainName, String landline, String fax, long customerSize,
			String streetAddress, long customerType, long businessUnit,
			long area, long province, long cityTown, int isActive,
			String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// CustomerCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_CUSTOMER_NO, no);
		initialValues.put(KEY_CUSTOMER_CRMNO, crmNo);
		initialValues.put(KEY_CUSTOMER_NAME, customerName);
		initialValues.put(KEY_CUSTOMER_CHAINNAME, chainName);
		initialValues.put(KEY_CUSTOMER_LANDLINE, landline);
		initialValues.put(KEY_CUSTOMER_FAX, fax);
		initialValues.put(KEY_CUSTOMER_SIZE, customerSize);
		initialValues.put(KEY_CUSTOMER_STREETADDRESS, streetAddress);
		// initialValues.put(KEY_CUSTOMER_RECORDSTATUS, customerRecordStatus);
		initialValues.put(KEY_CUSTOMER_TYPE, customerType);
		initialValues.put(KEY_CUSTOMER_BUSINESSUNIT, businessUnit);
		initialValues.put(KEY_CUSTOMER_AREA, area);
		initialValues.put(KEY_CUSTOMER_PROVINCE, province);
		initialValues.put(KEY_CUSTOMER_CITYTOWN, cityTown);
		initialValues.put(KEY_CUSTOMER_ISACTIVE, isActive);
		initialValues.put(KEY_CUSTOMER_CREATEDTIME, createdTime);
		initialValues.put(KEY_CUSTOMER_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_CUSTOMER_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, customerName, chainName, landline, fax,
			// customerSize, streetAddress, customerRecordStatus,
			// customerType, businessUnit, area, province, cityTown,
			// isActive, createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_CUSTOMER_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo,
			String customerName, String chainName, String landline, String fax,
			long customerSize, String streetAddress, long customerType,
			long businessUnit, long area, long province, long cityTown,
			int isActive, String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_CUSTOMER_NO, no);
		args.put(KEY_CUSTOMER_CRMNO, crmNo);
		args.put(KEY_CUSTOMER_NAME, customerName);
		args.put(KEY_CUSTOMER_CHAINNAME, chainName);
		args.put(KEY_CUSTOMER_LANDLINE, landline);
		args.put(KEY_CUSTOMER_FAX, fax);
		args.put(KEY_CUSTOMER_SIZE, customerSize);
		args.put(KEY_CUSTOMER_STREETADDRESS, streetAddress);
		// args.put(KEY_CUSTOMER_RECORDSTATUS, customerRecordStatus);
		args.put(KEY_CUSTOMER_TYPE, customerType);
		args.put(KEY_CUSTOMER_BUSINESSUNIT, businessUnit);
		args.put(KEY_CUSTOMER_AREA, area);
		args.put(KEY_CUSTOMER_PROVINCE, province);
		args.put(KEY_CUSTOMER_CITYTOWN, cityTown);
		args.put(KEY_CUSTOMER_ISACTIVE, isActive);
		args.put(KEY_CUSTOMER_CREATEDTIME, createdTime);
		args.put(KEY_CUSTOMER_MODIFIEDTIME, modifiedTime);
		args.put(KEY_CUSTOMER_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_CUSTOMER_ROWID + "=" + id,
				null) > 0) {
			// getRecords().update(id, no, customerName, chainName, landline,
			// customerSize, customerRecordStatus, customerType,
			// businessUnit, province, cityTown, isActive, createdTime,
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

	// ===========================================================
	// Public Foreign Key Methods
	// ===========================================================

	// ===========================================================
	// Collection
	// ===========================================================

	// public CustomerCollection getRecords() {
	// if (customerRecords == null) {
	// customerRecords = new CustomerCollection();
	// customerRecords.list = getAllRecords();
	// }
	// return customerRecords;
	// }
	//
	// public final class CustomerCollection implements Iterable<CustomerRecord>
	// {
	//
	// private List<CustomerRecord> list;
	//
	// private CustomerCollection() {
	// }
	//
	// public int size() {
	// return list.size();
	// }
	//
	// public CustomerRecord get(int i) {
	// return list.get(i);
	// }
	//
	// public CustomerRecord getById(long id) {
	// for (CustomerRecord record : list) {
	// if (record.getId() == id) {
	// return record;
	// }
	// }
	// return null;
	// }
	//
	// private void add(long id, String no, String customerName,
	// String chainName, String landline, String fax, String customerSize,
	// String streetAddress, long customerRecordStatus, long customerType,
	// long businessUnit, long area, long province, long cityTown,
	// int isActive, String createdTime, String modifiedTime, long user) {
	// list.add(new CustomerRecord(id, no, customerName, chainName,
	// landline, fax, customerSize, streetAddress, customerRecordStatus,
	// customerType, businessUnit, area, province, cityTown, isActive,
	// createdTime, modifiedTime, user));
	// }
	//
	// private void clear() {
	// list.clear();
	// }
	//
	// private void deleteById(long id) {
	// list.remove(getById(id));
	// }
	//
	// private void update(long id, String no, String customerName,
	// String chainName, String landline, String customerSize,
	// long customerRecordStatus, long customerType,
	// long businessUnit, long province, long cityTown, int isActive,
	// String createdTime, String modifiedTime, long user) {
	// CustomerRecord record = getById(id);
	// record.setNo(no);
	// record.setCustomerName(customerName);
	// record.setChainName(chainName);
	// record.setLandline(landline);
	// record.setCustomerSize(customerSize);
	// record.setCustomerRecordStatus(customerRecordStatus);
	// record.setCustomerType(customerType);
	// record.setBusinessUnit(businessUnit);
	// record.setProvince(province);
	// record.setCityTown(cityTown);
	// record.setIsActive(isActive);
	// record.setCreatedTime(createdTime);
	// record.setModifiedTime(modifiedTime);
	// record.setUser(user);
	// }
	//
	// @Override
	// public Iterator<CustomerRecord> iterator() {
	// Iterator<CustomerRecord> iter = new Iterator<CustomerRecord>() {
	// private int current = 0;
	//
	// @Override
	// public void remove() {
	// if (list.size() > 0) {
	// deleteUser(list.get(current).getId());
	// deleteById(list.get(current).getId());
	// list.remove(current);
	// }
	// }
	//
	// @Override
	// public CustomerRecord next() {
	// if (list.size() > 0) {
	// return list.get(current++);
	// }
	// return null;
	// }
	//
	// @Override
	// public boolean hasNext() {
	// return list.size() > 0 && current < list.size();
	// }
	// };
	// return iter;
	// }
	// }
}
