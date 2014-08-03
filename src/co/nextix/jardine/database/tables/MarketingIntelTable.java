package co.nextix.jardine.database.tables;

import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_ACTIVITY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_COMPETITORPRODUCT;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_CREATEDBY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_DETAILS;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_MARKETINGINTEL_ROWID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.MarketingIntelRecord;

public class MarketingIntelTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	// private MarketingIntelCollection marketingIntelCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public MarketingIntelTable(SQLiteDatabase database, String tableName) {
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

	public List<MarketingIntelRecord> getAllRecords() {
		Cursor c = null;
		List<MarketingIntelRecord> list = new ArrayList<MarketingIntelRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_CRMNO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_ACTIVITY));
					// long competitor = c.getLong(c
					// .getColumnIndex(KEY_MARKETINGINTEL_COMPETITOR));
					long competitorProduct = c
							.getLong(c
									.getColumnIndex(KEY_MARKETINGINTEL_COMPETITORPRODUCT));
					String details = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_DETAILS));
					// String remarks = c.getString(c
					// .getColumnIndex(KEY_MARKETINGINTEL_REMARKS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_CREATEDBY));

					list.add(new MarketingIntelRecord(id, no, crmNo, activity,
							competitorProduct, details, createdTime,
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

	public List<MarketingIntelRecord> getUnsyncedRecords() {
		List<MarketingIntelRecord> list = new ArrayList<MarketingIntelRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGINTEL_NO + "=''";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_CRMNO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_ACTIVITY));
					// long competitor = c.getLong(c
					// .getColumnIndex(KEY_MARKETINGINTEL_COMPETITOR));
					long competitorProduct = c
							.getLong(c
									.getColumnIndex(KEY_MARKETINGINTEL_COMPETITORPRODUCT));
					String details = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_DETAILS));
					// String remarks = c.getString(c
					// .getColumnIndex(KEY_MARKETINGINTEL_REMARKS));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_MARKETINGINTEL_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_MARKETINGINTEL_CREATEDBY));

					list.add(new MarketingIntelRecord(id, no, crmNo, activity,
							competitorProduct, details, createdTime,
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
		args.put(KEY_MARKETINGINTEL_NO, no);
		args.put(KEY_MARKETINGINTEL_MODIFIEDTIME, modifiedTime);
		args.put(KEY_MARKETINGINTEL_CRMNO, crmNo);
		if (mDb.update(mDatabaseTable, args, KEY_MARKETINGINTEL_ROWID + "="
				+ id, null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateModifiedTime(long id, String modifiedTime) {
		ContentValues args = new ContentValues();
		args.put(KEY_MARKETINGINTEL_MODIFIEDTIME, modifiedTime);
		if (mDb.update(mDatabaseTable, args, KEY_MARKETINGINTEL_ROWID + "="
				+ id, null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isExisting(String webID) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGINTEL_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_MARKETINGINTEL_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_MARKETINGINTEL_NO
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
		String MY_QUERY = "SELECT " + KEY_MARKETINGINTEL_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_MARKETINGINTEL_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_MARKETINGINTEL_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public MarketingIntelRecord getById(long ID) {
		MarketingIntelRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGINTEL_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_MARKETINGINTEL_ROWID));
				String no = c
						.getString(c.getColumnIndex(KEY_MARKETINGINTEL_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_CRMNO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_ACTIVITY));
				// long competitor = c.getLong(c
				// .getColumnIndex(KEY_MARKETINGINTEL_COMPETITOR));
				long competitorProduct = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_COMPETITORPRODUCT));
				String details = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_DETAILS));
				// String remarks = c.getString(c
				// .getColumnIndex(KEY_MARKETINGINTEL_REMARKS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_CREATEDBY));

				record = new MarketingIntelRecord(id, no, crmNo, activity,
						competitorProduct, details, createdTime, modifiedTime,
						createdBy);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public MarketingIntelRecord getByWebId(String ID) {
		MarketingIntelRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_MARKETINGINTEL_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_MARKETINGINTEL_ROWID));
				String no = c
						.getString(c.getColumnIndex(KEY_MARKETINGINTEL_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_CRMNO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_ACTIVITY));
				// long competitor = c.getLong(c
				// .getColumnIndex(KEY_MARKETINGINTEL_COMPETITOR));
				long competitorProduct = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_COMPETITORPRODUCT));
				String details = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_DETAILS));
				// String remarks = c.getString(c
				// .getColumnIndex(KEY_MARKETINGINTEL_REMARKS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_MARKETINGINTEL_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_MARKETINGINTEL_CREATEDBY));

				record = new MarketingIntelRecord(id, no, crmNo, activity,
						competitorProduct, details, createdTime, modifiedTime,
						createdBy);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, long activity,
			long competitorProduct, String details, String createdTime,
			String modifiedTime, long createdBy) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// MarketingIntelCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_MARKETINGINTEL_NO, no);
		initialValues.put(KEY_MARKETINGINTEL_CRMNO, crmNo);
		initialValues.put(KEY_MARKETINGINTEL_ACTIVITY, activity);
		// initialValues.put(KEY_MARKETINGINTEL_COMPETITOR, competitor);
		initialValues.put(KEY_MARKETINGINTEL_COMPETITORPRODUCT,
				competitorProduct);
		initialValues.put(KEY_MARKETINGINTEL_DETAILS, details);
		// initialValues.put(KEY_MARKETINGINTEL_REMARKS, remarks);
		initialValues.put(KEY_MARKETINGINTEL_CREATEDTIME, createdTime);
		initialValues.put(KEY_MARKETINGINTEL_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_MARKETINGINTEL_CREATEDBY, createdBy);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, activity, competitor, details, remarks,
			// createdTime, modifiedTime, createdBy);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_MARKETINGINTEL_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, long activity,
			long competitorProduct, String details, String createdTime,
			String modifiedTime, long createdBy) {
		ContentValues args = new ContentValues();
		args.put(KEY_MARKETINGINTEL_NO, no);
		args.put(KEY_MARKETINGINTEL_CRMNO, crmNo);
		args.put(KEY_MARKETINGINTEL_ACTIVITY, activity);
		// args.put(KEY_MARKETINGINTEL_COMPETITOR, competitor);
		args.put(KEY_MARKETINGINTEL_COMPETITORPRODUCT, competitorProduct);
		args.put(KEY_MARKETINGINTEL_DETAILS, details);
		// args.put(KEY_MARKETINGINTEL_REMARKS, remarks);
		args.put(KEY_MARKETINGINTEL_CREATEDTIME, createdTime);
		args.put(KEY_MARKETINGINTEL_MODIFIEDTIME, modifiedTime);
		args.put(KEY_MARKETINGINTEL_CREATEDBY, createdBy);
		if (mDb.update(mDatabaseTable, args, KEY_MARKETINGINTEL_ROWID + "="
				+ id, null) > 0) {
			// getRecords().update(id, no, activity, competitor, details,
			// remarks,
			// createdTime, modifiedTime, createdBy);
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
