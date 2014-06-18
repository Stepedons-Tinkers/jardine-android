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
import co.nextix.jardine.database.records.WorkplanEntryRecord;

public class WorkplanEntryTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_WORKPLANENTRY_ROWID = "_id";
	private final String KEY_WORKPLANENTRY_NO = "no";
	private final String KEY_WORKPLANENTRY_CUSTOMER = "customer";
	private final String KEY_WORKPLANENTRY_DATE = "date";
	private final String KEY_WORKPLANENTRY_STATUS = "status";
	private final String KEY_WORKPLANENTRY_AREA = "area";
	private final String KEY_WORKPLANENTRY_PROVINCE = "province";
	private final String KEY_WORKPLANENTRY_CITYTOWN = "city_town";
	private final String KEY_WORKPLANENTRY_REMARKS = "remarks";
	private final String KEY_WORKPLANENTRY_ACTIVITYTYPE = "activity_type";
	private final String KEY_WORKPLANENTRY_WORKPLAN = "workplan";
	private final String KEY_WORKPLANENTRY_CREATEDTIME = "created_time";
	private final String KEY_WORKPLANENTRY_MODIFIEDTIME = "modified_time";
	private final String KEY_WORKPLANENTRY_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private WorkplanEntryCollection workplanEntryCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public WorkplanEntryTable(SQLiteDatabase database, String tableName) {
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

	private List<WorkplanEntryRecord> getAllRecords() {
		Cursor c = null;
		List<WorkplanEntryRecord> list = new ArrayList<WorkplanEntryRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_NO));
					long customer = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_CUSTOMER));
					String date = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_DATE));
					long status = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_STATUS));
					long area = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_PROVINCE));
					long cityTown = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_CITYTOWN));
					String remarks = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_REMARKS));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
					long workplan = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_WORKPLANENTRY_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_WORKPLANENTRY_USER));

					list.add(new WorkplanEntryRecord(id, no, customer, date,
							status, area, province, cityTown, remarks,
							activityType, workplan, createdTime, modifiedTime,
							user));
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
				+ KEY_WORKPLANENTRY_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_WORKPLANENTRY_ROWID
				+ " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public WorkplanEntryRecord getById(int ID) {
		WorkplanEntryRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLANENTRY_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_WORKPLANENTRY_NO));
				long customer = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_CUSTOMER));
				String date = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_DATE));
				long status = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_STATUS));
				long area = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_PROVINCE));
				long cityTown = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_CITYTOWN));
				String remarks = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_REMARKS));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
				long workplan = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_USER));

				record = new WorkplanEntryRecord(id, no, customer, date,
						status, area, province, cityTown, remarks,
						activityType, workplan, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public WorkplanEntryRecord getByWebId(String ID) {
		WorkplanEntryRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_WORKPLANENTRY_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_WORKPLANENTRY_NO));
				long customer = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_CUSTOMER));
				String date = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_DATE));
				long status = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_STATUS));
				long area = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_PROVINCE));
				long cityTown = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_CITYTOWN));
				String remarks = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_REMARKS));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
				long workplan = c.getLong(c
						.getColumnIndex(KEY_WORKPLANENTRY_ACTIVITYTYPE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_WORKPLANENTRY_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_WORKPLANENTRY_USER));

				record = new WorkplanEntryRecord(id, no, customer, date,
						status, area, province, cityTown, remarks,
						activityType, workplan, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, long customer, String date, long status,
			long area, long province, long cityTown, String remarks,
			long activityType, long workplan, String createdTime,
			String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		WorkplanEntryCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_WORKPLANENTRY_NO, no);
		initialValues.put(KEY_WORKPLANENTRY_CUSTOMER, customer);
		initialValues.put(KEY_WORKPLANENTRY_DATE, date);
		initialValues.put(KEY_WORKPLANENTRY_STATUS, status);
		initialValues.put(KEY_WORKPLANENTRY_AREA, area);
		initialValues.put(KEY_WORKPLANENTRY_PROVINCE, province);
		initialValues.put(KEY_WORKPLANENTRY_CITYTOWN, cityTown);
		initialValues.put(KEY_WORKPLANENTRY_REMARKS, remarks);
		initialValues.put(KEY_WORKPLANENTRY_ACTIVITYTYPE, activityType);
		initialValues.put(KEY_WORKPLANENTRY_WORKPLAN, workplan);
		initialValues.put(KEY_WORKPLANENTRY_CREATEDTIME, createdTime);
		initialValues.put(KEY_WORKPLANENTRY_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_WORKPLANENTRY_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, customer, date, status, area, province,
					cityTown, remarks, activityType, workplan, createdTime,
					modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_WORKPLANENTRY_ROWID + "=" + rowId,
				null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, long customer, String date,
			long status, long area, long province, long cityTown,
			String remarks, long activityType, long workplan,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_WORKPLANENTRY_NO, no);
		args.put(KEY_WORKPLANENTRY_CUSTOMER, customer);
		args.put(KEY_WORKPLANENTRY_DATE, date);
		args.put(KEY_WORKPLANENTRY_STATUS, status);
		args.put(KEY_WORKPLANENTRY_AREA, area);
		args.put(KEY_WORKPLANENTRY_PROVINCE, province);
		args.put(KEY_WORKPLANENTRY_CITYTOWN, cityTown);
		args.put(KEY_WORKPLANENTRY_REMARKS, remarks);
		args.put(KEY_WORKPLANENTRY_ACTIVITYTYPE, activityType);
		args.put(KEY_WORKPLANENTRY_WORKPLAN, workplan);
		args.put(KEY_WORKPLANENTRY_CREATEDTIME, createdTime);
		args.put(KEY_WORKPLANENTRY_MODIFIEDTIME, modifiedTime);
		args.put(KEY_WORKPLANENTRY_USER, user);
		if (mDb.update(mDatabaseTable, args,
				KEY_WORKPLANENTRY_ROWID + "=" + id, null) > 0) {
			getRecords().update(id, no, customer, date, status, area, province,
					cityTown, remarks, activityType, workplan, createdTime,
					modifiedTime, user);
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

	public WorkplanEntryCollection getRecords() {
		if (workplanEntryCollection == null) {
			workplanEntryCollection = new WorkplanEntryCollection();
			workplanEntryCollection.list = getAllRecords();
		}
		return workplanEntryCollection;
	}

	public final class WorkplanEntryCollection implements
			Iterable<WorkplanEntryRecord> {

		private List<WorkplanEntryRecord> list;

		private WorkplanEntryCollection() {
		}

		public int size() {
			return list.size();
		}

		public WorkplanEntryRecord get(int i) {
			return list.get(i);
		}

		public WorkplanEntryRecord getById(long id) {
			for (WorkplanEntryRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, long customer, String date,
				long status, long area, long province, long cityTown,
				String remarks, long activityType, long workplan,
				String createdTime, String modifiedTime, long user) {
			list.add(new WorkplanEntryRecord(id, no, customer, date, status,
					area, province, cityTown, remarks, activityType, workplan,
					createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, long customer, String date,
				long status, long area, long province, long cityTown,
				String remarks, long activityType, long workplan,
				String createdTime, String modifiedTime, long user) {
			WorkplanEntryRecord record = getById(id);
			record.setNo(no);
			record.setCustomer(customer);
			record.setDate(date);
			record.setStatus(status);
			record.setArea(area);
			record.setProvince(province);
			record.setCityTown(cityTown);
			record.setRemarks(remarks);
			record.setActivityType(activityType);
			record.setWorkplan(workplan);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<WorkplanEntryRecord> iterator() {
			Iterator<WorkplanEntryRecord> iter = new Iterator<WorkplanEntryRecord>() {
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
				public WorkplanEntryRecord next() {
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
