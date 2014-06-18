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
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;

public class CompetitorProductStockCheckTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID = "_id";
	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_NO = "no";
	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_ACTIVITY = "activity";
	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_COMPETITORPRODUCT = "competitor_product";
	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_STOCKSTATUS = "stock_status";
	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_LOADEDONSHELVES = "loaded_on_shelves";
	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDTIME = "created_time";
	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_MODIFIEDTIME = "modified_time";
	private final String KEY_COMPETITORPRODUCTSTOCKCHECK_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private CompetitorProductStockCheckCollection competitorProductStockCheckRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public CompetitorProductStockCheckTable(SQLiteDatabase database,
			String tableName) {
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

	private List<CompetitorProductStockCheckRecord> getAllRecords() {
		Cursor c = null;
		List<CompetitorProductStockCheckRecord> list = new ArrayList<CompetitorProductStockCheckRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c
							.getLong(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID));
					String no = c
							.getString(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_NO));
					long activity = c
							.getLong(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_ACTIVITY));
					long competitorProduct = c
							.getLong(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_COMPETITORPRODUCT));
					int stockStatus = c
							.getInt(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_STOCKSTATUS));
					int loadedOnShelves = c
							.getInt(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_LOADEDONSHELVES));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_MODIFIEDTIME));
					long user = c
							.getLong(c
									.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_USER));

					list.add(new CompetitorProductStockCheckRecord(id, no,
							activity, competitorProduct, stockStatus,
							loadedOnShelves, createdTime, modifiedTime, user));
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
				+ KEY_COMPETITORPRODUCTSTOCKCHECK_NO + "='" + webID + "'";
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
				KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID + " IN (" + ids + ")",
				null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public CompetitorProductStockCheckRecord getById(int ID) {
		CompetitorProductStockCheckRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_NO));
				long activity = c
						.getLong(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_ACTIVITY));
				long competitorProduct = c
						.getLong(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_COMPETITORPRODUCT));
				int stockStatus = c
						.getInt(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_STOCKSTATUS));
				int loadedOnShelves = c
						.getInt(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_LOADEDONSHELVES));
				String createdTime = c
						.getString(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDTIME));
				String modifiedTime = c
						.getString(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_USER));

				record = new CompetitorProductStockCheckRecord(id, no,
						activity, competitorProduct, stockStatus,
						loadedOnShelves, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public CompetitorProductStockCheckRecord getByWebId(String ID) {
		CompetitorProductStockCheckRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_COMPETITORPRODUCTSTOCKCHECK_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_NO));
				long activity = c
						.getLong(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_ACTIVITY));
				long competitorProduct = c
						.getLong(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_COMPETITORPRODUCT));
				int stockStatus = c
						.getInt(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_STOCKSTATUS));
				int loadedOnShelves = c
						.getInt(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_LOADEDONSHELVES));
				String createdTime = c
						.getString(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDTIME));
				String modifiedTime = c
						.getString(c
								.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCTSTOCKCHECK_USER));

				record = new CompetitorProductStockCheckRecord(id, no,
						activity, competitorProduct, stockStatus,
						loadedOnShelves, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, long activity, long competitorProduct,
			long stockStatus, int loadedOnShelves, String createdTime,
			String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		CompetitorProductStockCheckCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_COMPETITORPRODUCTSTOCKCHECK_NO, no);
		initialValues.put(KEY_COMPETITORPRODUCTSTOCKCHECK_ACTIVITY, activity);
		initialValues.put(KEY_COMPETITORPRODUCTSTOCKCHECK_COMPETITORPRODUCT,
				competitorProduct);
		initialValues.put(KEY_COMPETITORPRODUCTSTOCKCHECK_STOCKSTATUS,
				stockStatus);
		initialValues.put(KEY_COMPETITORPRODUCTSTOCKCHECK_LOADEDONSHELVES,
				loadedOnShelves);
		initialValues.put(KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDTIME,
				createdTime);
		initialValues.put(KEY_COMPETITORPRODUCTSTOCKCHECK_MODIFIEDTIME,
				modifiedTime);
		initialValues.put(KEY_COMPETITORPRODUCTSTOCKCHECK_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, activity, competitorProduct, stockStatus,
					loadedOnShelves, createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID
				+ "=" + rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, long activity,
			long competitorProduct, long stockStatus, int loadedOnShelves,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_COMPETITORPRODUCTSTOCKCHECK_NO, no);
		args.put(KEY_COMPETITORPRODUCTSTOCKCHECK_ACTIVITY, activity);
		args.put(KEY_COMPETITORPRODUCTSTOCKCHECK_COMPETITORPRODUCT,
				competitorProduct);
		args.put(KEY_COMPETITORPRODUCTSTOCKCHECK_STOCKSTATUS, stockStatus);
		args.put(KEY_COMPETITORPRODUCTSTOCKCHECK_LOADEDONSHELVES,
				loadedOnShelves);
		args.put(KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDTIME, createdTime);
		args.put(KEY_COMPETITORPRODUCTSTOCKCHECK_MODIFIEDTIME, modifiedTime);
		args.put(KEY_COMPETITORPRODUCTSTOCKCHECK_USER, user);
		if (mDb.update(mDatabaseTable, args,
				KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID + "=" + id, null) > 0) {
			getRecords().update(id, no, activity, competitorProduct,
					stockStatus, loadedOnShelves, createdTime, modifiedTime,
					user);
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

	public CompetitorProductStockCheckCollection getRecords() {
		if (competitorProductStockCheckRecords == null) {
			competitorProductStockCheckRecords = new CompetitorProductStockCheckCollection();
			competitorProductStockCheckRecords.list = getAllRecords();
		}
		return competitorProductStockCheckRecords;
	}

	public final class CompetitorProductStockCheckCollection implements
			Iterable<CompetitorProductStockCheckRecord> {

		private List<CompetitorProductStockCheckRecord> list;

		private CompetitorProductStockCheckCollection() {
		}

		public int size() {
			return list.size();
		}

		public CompetitorProductStockCheckRecord get(int i) {
			return list.get(i);
		}

		public CompetitorProductStockCheckRecord getById(long id) {
			for (CompetitorProductStockCheckRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, long activity,
				long competitorProduct, long stockStatus, int loadedOnShelves,
				String createdTime, String modifiedTime, long user) {
			list.add(new CompetitorProductStockCheckRecord(id, no, activity,
					competitorProduct, stockStatus, loadedOnShelves,
					createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, long activity,
				long competitorProduct, long stockStatus, int loadedOnShelves,
				String createdTime, String modifiedTime, long user) {
			CompetitorProductStockCheckRecord record = getById(id);
			record.setNo(no);
			record.setActivity(activity);
			record.setCompetitorProduct(competitorProduct);
			record.setStockStatus(stockStatus);
			record.setLoadedOnShelves(loadedOnShelves);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<CompetitorProductStockCheckRecord> iterator() {
			Iterator<CompetitorProductStockCheckRecord> iter = new Iterator<CompetitorProductStockCheckRecord>() {
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
				public CompetitorProductStockCheckRecord next() {
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
