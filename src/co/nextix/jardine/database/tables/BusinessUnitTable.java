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
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_NAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_CODE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_ISACTIVE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_BUSINESSUNIT_CREATED_BY;
import co.nextix.jardine.database.records.BusinessUnitRecord;

public class BusinessUnitTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	// private BusinessUnitCollection businessUnitRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public BusinessUnitTable(SQLiteDatabase database, String tableName) {
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

	public List<BusinessUnitRecord> getAllRecords() {
		Cursor c = null;
		List<BusinessUnitRecord> list = new ArrayList<BusinessUnitRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_BUSINESSUNIT_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_BUSINESSUNIT_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_BUSINESSUNIT_CRMNO));
					String name = c.getString(c
							.getColumnIndex(KEY_BUSINESSUNIT_NAME));
					String code = c.getString(c
							.getColumnIndex(KEY_BUSINESSUNIT_CODE));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_BUSINESSUNIT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_BUSINESSUNIT_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_BUSINESSUNIT_MODIFIEDTIME));
					long created_by = c.getLong(c
							.getColumnIndex(KEY_BUSINESSUNIT_CREATED_BY));

					list.add(new BusinessUnitRecord(id, no, crmNo, name, code,
							isActive, createdTime, modifiedTime, created_by));
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
				+ KEY_BUSINESSUNIT_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_BUSINESSUNIT_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_BUSINESSUNIT_NO
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public BusinessUnitRecord getById(long ID) {
		BusinessUnitRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_BUSINESSUNIT_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_BUSINESSUNIT_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_BUSINESSUNIT_NO));
				String name = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_NAME));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_CRMNO));
				String code = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_CODE));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_BUSINESSUNIT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_MODIFIEDTIME));
				long created_by = c.getLong(c.getColumnIndex(KEY_BUSINESSUNIT_CREATED_BY));

				record = new BusinessUnitRecord(id, no, crmNo, name, code,
						isActive, createdTime, modifiedTime, created_by);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public BusinessUnitRecord getByWebId(String ID) {
		BusinessUnitRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_BUSINESSUNIT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_BUSINESSUNIT_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_BUSINESSUNIT_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_CRMNO));
				String name = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_NAME));
				String code = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_CODE));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_BUSINESSUNIT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_BUSINESSUNIT_MODIFIEDTIME));
				long created_by = c.getLong(c.getColumnIndex(KEY_BUSINESSUNIT_CREATED_BY));

				record = new BusinessUnitRecord(id, no, crmNo, name, code,
						isActive, createdTime, modifiedTime, created_by);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long getIdByNo(String no) {
		long result = 0;
		String MY_QUERY = "SELECT " + KEY_BUSINESSUNIT_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_BUSINESSUNIT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_BUSINESSUNIT_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public String getNoById(long ID) {
		String result = null;
		String MY_QUERY = "SELECT " + KEY_BUSINESSUNIT_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_BUSINESSUNIT_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_BUSINESSUNIT_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public long insert(String no, String crmNo, String businessUnitName,
			String businessUnitCode, int isActive, String createdTime,
			String modifiedTime, long created_by) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// BusinessUnitCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_BUSINESSUNIT_NO, no);
		initialValues.put(KEY_BUSINESSUNIT_CRMNO, crmNo);
		initialValues.put(KEY_BUSINESSUNIT_NAME, businessUnitName);
		initialValues.put(KEY_BUSINESSUNIT_CODE, businessUnitCode);
		initialValues.put(KEY_BUSINESSUNIT_ISACTIVE, isActive);
		initialValues.put(KEY_BUSINESSUNIT_CREATEDTIME, createdTime);
		initialValues.put(KEY_BUSINESSUNIT_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_BUSINESSUNIT_CREATED_BY, created_by);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, businessUnitName, businessUnitCode,
			// isActive, createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_BUSINESSUNIT_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo,
			String businessUnitName, String businessUnitCode, int isActive,
			String createdTime, String modifiedTime, long created_by) {
		ContentValues args = new ContentValues();
		args.put(KEY_BUSINESSUNIT_NO, no);
		args.put(KEY_BUSINESSUNIT_CRMNO, crmNo);
		args.put(KEY_BUSINESSUNIT_NAME, businessUnitName);
		args.put(KEY_BUSINESSUNIT_CODE, businessUnitCode);
		args.put(KEY_BUSINESSUNIT_ISACTIVE, isActive);
		args.put(KEY_BUSINESSUNIT_CREATEDTIME, createdTime);
		args.put(KEY_BUSINESSUNIT_MODIFIEDTIME, modifiedTime);
		args.put(KEY_BUSINESSUNIT_CREATED_BY, created_by);
		if (mDb.update(mDatabaseTable, args, KEY_BUSINESSUNIT_ROWID + "=" + id,
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

	// ===========================================================
	// Public Foreign Key Methods
	// ===========================================================

	// ===========================================================
	// Collection
	// ===========================================================

	// public BusinessUnitCollection getRecords() {
	// if (businessUnitRecords == null) {
	// businessUnitRecords = new BusinessUnitCollection();
	// businessUnitRecords.list = getAllRecords();
	// }
	// return businessUnitRecords;
	// }
	//
	// public final class BusinessUnitCollection implements
	// Iterable<BusinessUnitRecord> {
	//
	// private List<BusinessUnitRecord> list;
	//
	// private BusinessUnitCollection() {
	// }
	//
	// public int size() {
	// return list.size();
	// }
	//
	// public BusinessUnitRecord get(int i) {
	// return list.get(i);
	// }
	//
	// public BusinessUnitRecord getById(long id) {
	// for (BusinessUnitRecord record : list) {
	// if (record.getId() == id) {
	// return record;
	// }
	// }
	// return null;
	// }
	//
	// private void add(long id, String no, String businessUnitName,
	// String businessUnitCode, int isActive, String createdTime,
	// String modifiedTime, long user) {
	// list.add(new BusinessUnitRecord(id, no, businessUnitName,
	// businessUnitCode, isActive, createdTime, modifiedTime, user));
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
	// private void update(long id, String no, String businessUnitName,
	// String businessUnitCode, int isActive, String createdTime,
	// String modifiedTime, long user) {
	// BusinessUnitRecord record = getById(id);
	// record.setNo(no);
	// record.setBusinessUnitName(businessUnitName);
	// record.setBusinessUnitCode(businessUnitCode);
	// record.setIsActive(isActive);
	// record.setCreatedTime(createdTime);
	// record.setModifiedTime(modifiedTime);
	// record.setUser(user);
	// }
	//
	// @Override
	// public Iterator<BusinessUnitRecord> iterator() {
	// Iterator<BusinessUnitRecord> iter = new Iterator<BusinessUnitRecord>() {
	// private int current = 0;
	//
	// @Override
	// public void remove() {
	// if (list.size() > 0) {
	// delete(list.get(current).getId());
	// deleteById(list.get(current).getId());
	// list.remove(current);
	// }
	// }
	//
	// @Override
	// public BusinessUnitRecord next() {
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
