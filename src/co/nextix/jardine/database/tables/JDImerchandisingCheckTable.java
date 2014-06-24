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
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;

public class JDImerchandisingCheckTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_JDIMERCHANDISING_ROWID = "_id";
	private final String KEY_JDIMERCHANDISING_NO = "no";
	private final String KEY_JDIMERCHANDISING_ACTIVITY = "activity";
	private final String KEY_JDIMERCHANDISING_PRODUCT = "product";
	private final String KEY_JDIMERCHANDISING_ISACTIVE = "is_active";
	private final String KEY_JDIMERCHANDISING_CREATEDTIME = "created_time";
	private final String KEY_JDIMERCHANDISING_MODIFIEDTIME = "modified_time";
	private final String KEY_JDIMERCHANDISING_USER = "user";

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

	private DatabaseAdapter getDBAdapter() {
		if (mDBAdapter == null)
			mDBAdapter = DatabaseAdapter.getInstance();

		return mDBAdapter;
	}

	// ===========================================================
	// Private methods
	// ===========================================================

	private List<JDImerchandisingCheckRecord> getAllRecords() {
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
					long activity = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_ACTIVITY));
					long product = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_PRODUCT));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_JDIMERCHANDISING_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_JDIMERCHANDISING_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_JDIMERCHANDISING_USER));

					list.add(new JDImerchandisingCheckRecord(id, no, activity,
							product, isActive, createdTime, modifiedTime, user));
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

	public JDImerchandisingCheckRecord getById(int ID) {
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
				long activity = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_ACTIVITY));
				long product = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_PRODUCT));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_JDIMERCHANDISING_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_USER));

				record = new JDImerchandisingCheckRecord(id, no, activity,
						product, isActive, createdTime, modifiedTime, user);
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
				long activity = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_ACTIVITY));
				long product = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_PRODUCT));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_JDIMERCHANDISING_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_JDIMERCHANDISING_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_JDIMERCHANDISING_USER));

				record = new JDImerchandisingCheckRecord(id, no, activity,
						product, isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, long activity, long product,
			int isActive, String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// JDIMERCHANDISINGCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_JDIMERCHANDISING_NO, no);
		initialValues.put(KEY_JDIMERCHANDISING_ACTIVITY, activity);
		initialValues.put(KEY_JDIMERCHANDISING_PRODUCT, product);
		initialValues.put(KEY_JDIMERCHANDISING_ISACTIVE, isActive);
		initialValues.put(KEY_JDIMERCHANDISING_CREATEDTIME, createdTime);
		initialValues.put(KEY_JDIMERCHANDISING_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_JDIMERCHANDISING_USER, user);

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

	public boolean update(long id, String no, long activity, long product,
			int isActive, String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_JDIMERCHANDISING_NO, no);
		args.put(KEY_JDIMERCHANDISING_ACTIVITY, activity);
		args.put(KEY_JDIMERCHANDISING_PRODUCT, product);
		args.put(KEY_JDIMERCHANDISING_ISACTIVE, isActive);
		args.put(KEY_JDIMERCHANDISING_CREATEDTIME, createdTime);
		args.put(KEY_JDIMERCHANDISING_MODIFIEDTIME, modifiedTime);
		args.put(KEY_JDIMERCHANDISING_USER, user);
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
