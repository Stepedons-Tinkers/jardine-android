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
import co.nextix.jardine.database.records.ProjectRequirementRecord;

public class ProjectRequirementTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_PROJECTREQUIREMENTS_ROWID = "_id";
	private final String KEY_PROJECTREQUIREMENTS_NO = "no";
	private final String KEY_PROJECTREQUIREMENTS_TYPE = "project_requirement_type";
	private final String KEY_PROJECTREQUIREMENTS_DATENEEDED = "date_needed";
	private final String KEY_PROJECTREQUIREMENTS_SQUAREMETERS = "square_meters";
	private final String KEY_PROJECTREQUIREMENTS_PRODUCTSUSED = "products_used";
	private final String KEY_PROJECTREQUIREMENTS_OTHERDETAILS = "other_details";
	private final String KEY_PROJECTREQUIREMENTS_CREATEDTIME = "created_time";
	private final String KEY_PROJECTREQUIREMENTS_MODIFIEDTIME = "modified_time";
	private final String KEY_PROJECTREQUIREMENTS_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private ProjectRequirementCollection projectRequirementCollection;
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

	private DatabaseAdapter getDBAdapter() {
		if (mDBAdapter == null)
			mDBAdapter = DatabaseAdapter.getInstance();

		return mDBAdapter;
	}

	// ===========================================================
	// Private methods
	// ===========================================================

	private List<ProjectRequirementRecord> getAllRecords() {
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
					long projectRequirementType = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_TYPE));
					String dateNeeded = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_DATENEEDED));
					String squareMeters = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_SQUAREMETERS));
					String productsUsed = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED));
					String otherDetails = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_OTHERDETAILS));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_PROJECTREQUIREMENTS_USER));

					list.add(new ProjectRequirementRecord(id, no,
							projectRequirementType, dateNeeded, squareMeters,
							productsUsed, otherDetails, createdTime,
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

	public ProjectRequirementRecord getById(int ID) {
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
				long projectRequirementType = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_TYPE));
				String dateNeeded = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_DATENEEDED));
				String squareMeters = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_SQUAREMETERS));
				String productsUsed = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED));
				String otherDetails = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_OTHERDETAILS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_USER));

				record = new ProjectRequirementRecord(id, no,
						projectRequirementType, dateNeeded, squareMeters,
						productsUsed, otherDetails, createdTime, modifiedTime,
						user);
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
				long projectRequirementType = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_TYPE));
				String dateNeeded = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_DATENEEDED));
				String squareMeters = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_SQUAREMETERS));
				String productsUsed = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED));
				String otherDetails = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_OTHERDETAILS));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_PROJECTREQUIREMENTS_USER));

				record = new ProjectRequirementRecord(id, no,
						projectRequirementType, dateNeeded, squareMeters,
						productsUsed, otherDetails, createdTime, modifiedTime,
						user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, long projectRequirementType,
			String dateNeeded, String squareMeters, String productsUsed,
			String otherDetails, String createdTime, String modifiedTime,
			long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		ProjectRequirementCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_PROJECTREQUIREMENTS_NO, no);
		initialValues.put(KEY_PROJECTREQUIREMENTS_TYPE, projectRequirementType);
		initialValues.put(KEY_PROJECTREQUIREMENTS_DATENEEDED, dateNeeded);
		initialValues.put(KEY_PROJECTREQUIREMENTS_SQUAREMETERS, squareMeters);
		initialValues.put(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED, productsUsed);
		initialValues.put(KEY_PROJECTREQUIREMENTS_OTHERDETAILS, otherDetails);
		initialValues.put(KEY_PROJECTREQUIREMENTS_CREATEDTIME, createdTime);
		initialValues.put(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_PROJECTREQUIREMENTS_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, projectRequirementType, dateNeeded,
					squareMeters, productsUsed, otherDetails, createdTime,
					modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_PROJECTREQUIREMENTS_ROWID + "="
				+ rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, long projectRequirementType,
			String dateNeeded, String squareMeters, String productsUsed,
			String otherDetails, String createdTime, String modifiedTime,
			long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_PROJECTREQUIREMENTS_NO, no);
		args.put(KEY_PROJECTREQUIREMENTS_TYPE, projectRequirementType);
		args.put(KEY_PROJECTREQUIREMENTS_DATENEEDED, dateNeeded);
		args.put(KEY_PROJECTREQUIREMENTS_SQUAREMETERS, squareMeters);
		args.put(KEY_PROJECTREQUIREMENTS_PRODUCTSUSED, productsUsed);
		args.put(KEY_PROJECTREQUIREMENTS_OTHERDETAILS, otherDetails);
		args.put(KEY_PROJECTREQUIREMENTS_CREATEDTIME, createdTime);
		args.put(KEY_PROJECTREQUIREMENTS_MODIFIEDTIME, modifiedTime);
		args.put(KEY_PROJECTREQUIREMENTS_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_PROJECTREQUIREMENTS_ROWID
				+ "=" + id, null) > 0) {
			getRecords().update(id, no, projectRequirementType, dateNeeded,
					squareMeters, productsUsed, otherDetails, createdTime,
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

	public ProjectRequirementCollection getRecords() {
		if (projectRequirementCollection == null) {
			projectRequirementCollection = new ProjectRequirementCollection();
			projectRequirementCollection.list = getAllRecords();
		}
		return projectRequirementCollection;
	}

	public final class ProjectRequirementCollection implements
			Iterable<ProjectRequirementRecord> {

		private List<ProjectRequirementRecord> list;

		private ProjectRequirementCollection() {
		}

		public int size() {
			return list.size();
		}

		public ProjectRequirementRecord get(int i) {
			return list.get(i);
		}

		public ProjectRequirementRecord getById(long id) {
			for (ProjectRequirementRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, long projectRequirementType,
				String dateNeeded, String squareMeters, String productsUsed,
				String otherDetails, String createdTime, String modifiedTime,
				long user) {
			list.add(new ProjectRequirementRecord(id, no,
					projectRequirementType, dateNeeded, squareMeters,
					productsUsed, otherDetails, createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, long projectRequirementType,
				String dateNeeded, String squareMeters, String productsUsed,
				String otherDetails, String createdTime, String modifiedTime,
				long user) {
			ProjectRequirementRecord record = getById(id);
			record.setNo(no);
			record.setProjectRequirementType(projectRequirementType);
			record.setDateNeeded(dateNeeded);
			record.setSquareMeters(squareMeters);
			record.setProductsUsed(productsUsed);
			record.setOtherDetails(otherDetails);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<ProjectRequirementRecord> iterator() {
			Iterator<ProjectRequirementRecord> iter = new Iterator<ProjectRequirementRecord>() {
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
				public ProjectRequirementRecord next() {
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
