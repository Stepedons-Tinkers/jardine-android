package co.nextix.jardine.database.tables;

import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_ACTIVITY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_CREATEDBY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_DATENEEDED;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_OTHERDETAILS;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_SQUAREMETERS;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_PROJECTREQUIREMENTS_TYPE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.ProjectRequirementRecord;

public class ProjectRequirementTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	// private ProjectRequirementCollection projectRequirementCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public ProjectRequirementTable(SQLiteDatabase database, String tableName) {
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

	public List<ProjectRequirementRecord> getAllRecords() {
		Cursor c = null;
		List<ProjectRequirementRecord> list = new ArrayList<ProjectRequirementRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_CRMNO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_ACTIVITY));
					long projectRequirementType = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_TYPE));
					String dateNeeded = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_DATENEEDED));
					String squareMeters = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_SQUAREMETERS));
					// String productsUsed = c
					// .getString(c
					// .getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED));
					String productsBrand = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND));
					String otherDetails = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_OTHERDETAILS));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDBY));

					list.add(new ProjectRequirementRecord(id, no, crmNo,
							activity, projectRequirementType, dateNeeded,
							squareMeters, productsBrand, otherDetails,
							createdTime, modifiedTime, createdBy));
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

	public List<ProjectRequirementRecord> getUnsyncedRecords() {
		List<ProjectRequirementRecord> list = new ArrayList<ProjectRequirementRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PROJECTREQUIREMENTS_NO + " ISNULL";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_CRMNO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_ACTIVITY));
					long projectRequirementType = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_TYPE));
					String dateNeeded = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_DATENEEDED));
					String squareMeters = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_SQUAREMETERS));
					// String productsUsed = c
					// .getString(c
					// .getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED));
					String productsBrand = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND));
					String otherDetails = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_OTHERDETAILS));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDBY));

					list.add(new ProjectRequirementRecord(id, no, crmNo,
							activity, projectRequirementType, dateNeeded,
							squareMeters, productsBrand, otherDetails,
							createdTime, modifiedTime, createdBy));
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
		args.put(KEY_PROJECTREQUIREMENTS_NO, no);
		args.put(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME, modifiedTime);
		args.put(KEY_PROJECTREQUIREMENTS_CRMNO, crmNo);
		if (mDb.update(mDatabaseTable, args, KEY_PROJECTREQUIREMENTS_ROWID
				+ "=" + id, null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isExisting(String webID) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PROJECTREQUIREMENTS_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable,
				KEY_PROJECTREQUIREMENTS_ROWID + " IN (" + ids + ")", null);

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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_PROJECTREQUIREMENTS_NO
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
		String MY_QUERY = "SELECT " + KEY_PROJECTREQUIREMENTS_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_PROJECTREQUIREMENTS_NO
				+ "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public ProjectRequirementRecord getById(long ID) {
		ProjectRequirementRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PROJECTREQUIREMENTS_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_CRMNO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_ACTIVITY));
				long projectRequirementType = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_TYPE));
				String dateNeeded = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_DATENEEDED));
				String squareMeters = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_SQUAREMETERS));
				// String productsUsed = c.getString(c
				// .getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED));
				String productsBrand = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND));
				String otherDetails = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_OTHERDETAILS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDBY));

				record = new ProjectRequirementRecord(id, no, crmNo, activity,
						projectRequirementType, dateNeeded, squareMeters,
						productsBrand, otherDetails, createdTime, modifiedTime,
						createdBy);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public ProjectRequirementRecord getByWebId(String ID) {
		ProjectRequirementRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PROJECTREQUIREMENTS_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_CRMNO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_ACTIVITY));
				long projectRequirementType = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_TYPE));
				String dateNeeded = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_DATENEEDED));
				String squareMeters = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_SQUAREMETERS));
				// String productsUsed = c.getString(c
				// .getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED));
				String productsBrand = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND));
				String otherDetails = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_OTHERDETAILS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDBY));

				record = new ProjectRequirementRecord(id, no, crmNo, activity,
						projectRequirementType, dateNeeded, squareMeters,
						productsBrand, otherDetails, createdTime, modifiedTime,
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
			long projectRequirementType, String dateNeeded,
			String squareMeters, String productsBrand, String otherDetails,
			String createdTime, String modifiedTime, long createdBy) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// ProjectRequirementCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_PROJECTREQUIREMENTS_NO, no);
		initialValues.put(KEY_PROJECTREQUIREMENTS_CRMNO, crmNo);
		initialValues.put(KEY_PROJECTREQUIREMENTS_ACTIVITY, activity);
		initialValues.put(KEY_PROJECTREQUIREMENTS_TYPE, projectRequirementType);
		initialValues.put(KEY_PROJECTREQUIREMENTS_DATENEEDED, dateNeeded);
		initialValues.put(KEY_PROJECTREQUIREMENTS_SQUAREMETERS, squareMeters);
		// initialValues.put(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED,
		// productsUsed);
		initialValues.put(KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND, productsBrand);
		initialValues.put(KEY_PROJECTREQUIREMENTS_OTHERDETAILS, otherDetails);
		initialValues.put(KEY_PROJECTREQUIREMENTS_CREATEDTIME, createdTime);
		initialValues.put(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_PROJECTREQUIREMENTS_CREATEDBY, createdBy);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, projectRequirementType, dateNeeded,
			// squareMeters, productsUsed, otherDetails, createdTime,
			// modifiedTime, createdBy);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_PROJECTREQUIREMENTS_ROWID + "="
				+ rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, long activity,
			long projectRequirementType, String dateNeeded,
			String squareMeters, String productsBrand, String otherDetails,
			String createdTime, String modifiedTime, long createdBy) {
		ContentValues args = new ContentValues();
		args.put(KEY_PROJECTREQUIREMENTS_NO, no);
		args.put(KEY_PROJECTREQUIREMENTS_CRMNO, crmNo);
		args.put(KEY_PROJECTREQUIREMENTS_ACTIVITY, activity);
		args.put(KEY_PROJECTREQUIREMENTS_TYPE, projectRequirementType);
		args.put(KEY_PROJECTREQUIREMENTS_DATENEEDED, dateNeeded);
		args.put(KEY_PROJECTREQUIREMENTS_SQUAREMETERS, squareMeters);
		// args.put(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED, productsUsed);
		args.put(KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND, productsBrand);
		args.put(KEY_PROJECTREQUIREMENTS_OTHERDETAILS, otherDetails);
		args.put(KEY_PROJECTREQUIREMENTS_CREATEDTIME, createdTime);
		args.put(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME, modifiedTime);
		args.put(KEY_PROJECTREQUIREMENTS_CREATEDBY, createdBy);
		if (mDb.update(mDatabaseTable, args, KEY_PROJECTREQUIREMENTS_ROWID
				+ "=" + id, null) > 0) {
			// getRecords().update(id, no, projectRequirementType, dateNeeded,
			// squareMeters, productsUsed, otherDetails, createdTime,
			// modifiedTime, createdBy);
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

	// public ProjectRequirementCollection getRecords() {
	// if (projectRequirementCollection == null) {
	// projectRequirementCollection = new ProjectRequirementCollection();
	// projectRequirementCollection.list = getAllRecords();
	// }
	// return projectRequirementCollection;
	// }
	//
	// public final class ProjectRequirementCollection implements
	// Iterable<ProjectRequirementRecord> {
	//
	// private List<ProjectRequirementRecord> list;
	//
	// private ProjectRequirementCollection() {
	// }
	//
	// public int size() {
	// return list.size();
	// }
	//
	// public ProjectRequirementRecord get(int i) {
	// return list.get(i);
	// }
	//
	// public ProjectRequirementRecord getById(long id) {
	// for (ProjectRequirementRecord record : list) {
	// if (record.getId() == id) {
	// return record;
	// }
	// }
	// return null;
	// }
	//
	// private void add(long id, String no, long projectRequirementType,
	// String dateNeeded, String squareMeters, String productsUsed,
	// String otherDetails, String createdTime, String modifiedTime,
	// long user) {
	// list.add(new ProjectRequirementRecord(id, no,
	// projectRequirementType, dateNeeded, squareMeters,
	// productsUsed, otherDetails, createdTime, modifiedTime, user));
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
	// private void update(long id, String no, long projectRequirementType,
	// String dateNeeded, String squareMeters, String productsUsed,
	// String otherDetails, String createdTime, String modifiedTime,
	// long user) {
	// ProjectRequirementRecord record = getById(id);
	// record.setNo(no);
	// record.setProjectRequirementType(projectRequirementType);
	// record.setDateNeeded(dateNeeded);
	// record.setSquareMeters(squareMeters);
	// record.setProductsUsed(productsUsed);
	// record.setOtherDetails(otherDetails);
	// record.setCreatedTime(createdTime);
	// record.setModifiedTime(modifiedTime);
	// record.setUser(user);
	// }
	//
	// @Override
	// public Iterator<ProjectRequirementRecord> iterator() {
	// Iterator<ProjectRequirementRecord> iter = new
	// Iterator<ProjectRequirementRecord>() {
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
	// public ProjectRequirementRecord next() {
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
