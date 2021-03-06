package co.nextix.jardine.database.tables;

import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_ACTIVITY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_CREATEDBY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_PRODUCTBRAND;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_JDIMERCHANDISING_STATUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;

public class JDImerchandisingCheckTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public JDImerchandisingCheckTable(SQLiteDatabase database, String tableName) {
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

	public List<JDImerchandisingCheckRecord> getAllRecords() {
		Cursor c = null;
		List<JDImerchandisingCheckRecord> list = new ArrayList<JDImerchandisingCheckRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CRMNO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_ACTIVITY));
					long productBrand = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_PRODUCTBRAND));
					int status = c.getInt(c
							.getColumnIndex(KEY_JDIMERCHANDISING_STATUS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDBY));

					list.add(new JDImerchandisingCheckRecord(id, no, crmNo,
							activity, productBrand, status, createdTime,
							modifiedTime, createdBy));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}
	
	public List<JDImerchandisingCheckRecord> getAllRecordsByActivity(long activityID) {
		Cursor c = null;
		List<JDImerchandisingCheckRecord> list = new ArrayList<JDImerchandisingCheckRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE " + KEY_JDIMERCHANDISING_ACTIVITY + "=" + activityID;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CRMNO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_ACTIVITY));
					long productBrand = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_PRODUCTBRAND));
					int status = c.getInt(c
							.getColumnIndex(KEY_JDIMERCHANDISING_STATUS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDBY));

					list.add(new JDImerchandisingCheckRecord(id, no, crmNo,
							activity, productBrand, status, createdTime,
							modifiedTime, createdBy));
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

	public List<JDImerchandisingCheckRecord> getUnsyncedRecords() {
		List<JDImerchandisingCheckRecord> list = new ArrayList<JDImerchandisingCheckRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_JDIMERCHANDISING_NO + "=''";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CRMNO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_ACTIVITY));
					long productBrand = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_PRODUCTBRAND));
					int status = c.getInt(c
							.getColumnIndex(KEY_JDIMERCHANDISING_STATUS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDBY));

					list.add(new JDImerchandisingCheckRecord(id, no, crmNo,
							activity, productBrand, status, createdTime,
							modifiedTime, createdBy));
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
		args.put(KEY_JDIMERCHANDISING_NO, no);
		args.put(KEY_JDIMERCHANDISING_MODIFIEDTIME, modifiedTime);
		args.put(KEY_JDIMERCHANDISING_CRMNO, crmNo);
		if (mDb.update(mDatabaseTable, args, KEY_JDIMERCHANDISING_ROWID + "="
				+ id, null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateModifiedTime(long id, String modifiedTime) {
		ContentValues args = new ContentValues();
		args.put(KEY_JDIMERCHANDISING_MODIFIEDTIME, modifiedTime);
		if (mDb.update(mDatabaseTable, args, KEY_JDIMERCHANDISING_ROWID + "="
				+ id, null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isExisting(String webID) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_JDIMERCHANDISING_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_JDIMERCHANDISING_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_JDIMERCHANDISING_NO
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
		String MY_QUERY = "SELECT " + KEY_JDIMERCHANDISING_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_JDIMERCHANDISING_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c
						.getLong(c.getColumnIndex(KEY_JDIMERCHANDISING_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public JDImerchandisingCheckRecord getById(long ID) {
		JDImerchandisingCheckRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_JDIMERCHANDISING_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_CRMNO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_ACTIVITY));
				long productBrand = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_PRODUCTBRAND));
				int status = c.getInt(c
						.getColumnIndex(KEY_JDIMERCHANDISING_STATUS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDBY));

				record = new JDImerchandisingCheckRecord(id, no, crmNo,
						activity, productBrand, status, createdTime,
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
		String MY_QUERY = "SELECT " + KEY_JDIMERCHANDISING_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_JDIMERCHANDISING_ROWID
				+ "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_JDIMERCHANDISING_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public JDImerchandisingCheckRecord getByWebId(String ID) {
		JDImerchandisingCheckRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_JDIMERCHANDISING_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_CRMNO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_ACTIVITY));
				long productBrand = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_PRODUCTBRAND));
				int status = c.getInt(c
						.getColumnIndex(KEY_JDIMERCHANDISING_STATUS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDBY));

				record = new JDImerchandisingCheckRecord(id, no, crmNo,
						activity, productBrand, status, createdTime,
						modifiedTime, createdBy);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, long activity,
			long productBrand, long status, String createdTime,
			String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// JDIMERCHANDISINGCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_JDIMERCHANDISING_NO, no);
		initialValues.put(KEY_JDIMERCHANDISING_CRMNO, crmNo);
		initialValues.put(KEY_JDIMERCHANDISING_ACTIVITY, activity);
		initialValues.put(KEY_JDIMERCHANDISING_PRODUCTBRAND, productBrand);
		initialValues.put(KEY_JDIMERCHANDISING_STATUS, status);
		initialValues.put(KEY_JDIMERCHANDISING_CREATEDTIME, createdTime);
		initialValues.put(KEY_JDIMERCHANDISING_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_JDIMERCHANDISING_CREATEDBY, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, type, category, isActive, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable,
				KEY_JDIMERCHANDISING_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, long activity,
			long productBrand, long status, String createdTime,
			String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_JDIMERCHANDISING_NO, no);
		args.put(KEY_JDIMERCHANDISING_CRMNO, crmNo);
		args.put(KEY_JDIMERCHANDISING_ACTIVITY, activity);
		args.put(KEY_JDIMERCHANDISING_PRODUCTBRAND, productBrand);
		args.put(KEY_JDIMERCHANDISING_STATUS, status);
		args.put(KEY_JDIMERCHANDISING_CREATEDTIME, createdTime);
		args.put(KEY_JDIMERCHANDISING_MODIFIEDTIME, modifiedTime);
		args.put(KEY_JDIMERCHANDISING_CREATEDBY, user);
		if (mDb.update(mDatabaseTable, args, KEY_JDIMERCHANDISING_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, type, category, isActive, user);
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
}
