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
import co.nextix.jardine.database.records.CompetitorProductRecord;

public class CompetitorProductTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_COMPETITORPRODUCT_ROWID = "_id";
	private final String KEY_COMPETITORPRODUCT_NO = "no";
	private final String KEY_COMPETITORPRODUCT_COMPETITOR = "competitor";
	private final String KEY_COMPETITORPRODUCT_PRODUCTBRAND = "product_brand";
	private final String KEY_COMPETITORPRODUCT_PRODUCTDESCRIPTION = "product_description";
	private final String KEY_COMPETITORPRODUCT_PRODUCTSIZE = "product_size";
	private final String KEY_COMPETITORPRODUCT_ISACTIVE = "is_active";
	private final String KEY_COMPETITORPRODUCT_CREATEDTIME = "created_time";
	private final String KEY_COMPETITORPRODUCT_MODIFIEDTIME = "modified_time";
	private final String KEY_COMPETITORPRODUCT_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private CompetitorProductCollection competitorProductRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public CompetitorProductTable(SQLiteDatabase database, String tableName) {
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

	private List<CompetitorProductRecord> getAllRecords() {
		Cursor c = null;
		List<CompetitorProductRecord> list = new ArrayList<CompetitorProductRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_COMPETITORPRODUCT_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_COMPETITORPRODUCT_NO));
					long competitor = c.getLong(c
							.getColumnIndex(KEY_COMPETITORPRODUCT_COMPETITOR));
					String productBrand = c
							.getString(c
									.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTBRAND));
					String productDescription = c
							.getString(c
									.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTDESCRIPTION));
					String productSize = c.getString(c
							.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTSIZE));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_COMPETITORPRODUCT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_COMPETITORPRODUCT_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_COMPETITORPRODUCT_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_COMPETITORPRODUCT_USER));

					list.add(new CompetitorProductRecord(id, no, competitor,
							productBrand, productDescription, productSize,
							isActive, createdTime, modifiedTime, user));
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
				+ KEY_COMPETITORPRODUCT_NO + "='" + webID + "'";
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
				KEY_COMPETITORPRODUCT_ROWID + " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public CompetitorProductRecord getById(int ID) {
		CompetitorProductRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_COMPETITORPRODUCT_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_NO));
				long competitor = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_COMPETITOR));
				String productBrand = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTBRAND));
				String productDescription = c
						.getString(c
								.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTDESCRIPTION));
				String productSize = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTSIZE));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_USER));

				record = new CompetitorProductRecord(id, no, competitor,
						productBrand, productDescription, productSize,
						isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public CompetitorProductRecord getByWebId(String ID) {
		CompetitorProductRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_COMPETITORPRODUCT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_NO));
				long competitor = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_COMPETITOR));
				String productBrand = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTBRAND));
				String productDescription = c
						.getString(c
								.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTDESCRIPTION));
				String productSize = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_PRODUCTSIZE));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_COMPETITORPRODUCT_USER));

				record = new CompetitorProductRecord(id, no, competitor,
						productBrand, productDescription, productSize,
						isActive, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, int competitor, String productBrand,
			String productDescription, String productSize, int isActive,
			String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		CompetitorProductCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_COMPETITORPRODUCT_NO, no);
		initialValues.put(KEY_COMPETITORPRODUCT_COMPETITOR, competitor);
		initialValues.put(KEY_COMPETITORPRODUCT_PRODUCTBRAND, productBrand);
		initialValues.put(KEY_COMPETITORPRODUCT_PRODUCTDESCRIPTION,
				productDescription);
		initialValues.put(KEY_COMPETITORPRODUCT_PRODUCTSIZE, productSize);
		initialValues.put(KEY_COMPETITORPRODUCT_ISACTIVE, isActive);
		initialValues.put(KEY_COMPETITORPRODUCT_CREATEDTIME, createdTime);
		initialValues.put(KEY_COMPETITORPRODUCT_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_COMPETITORPRODUCT_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, competitor, productBrand,
					productDescription, productSize, isActive, createdTime,
					modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_COMPETITORPRODUCT_ROWID + "="
				+ rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, int competitor,
			String productBrand, String productDescription, String productSize,
			int isActive, String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_COMPETITORPRODUCT_NO, no);
		args.put(KEY_COMPETITORPRODUCT_COMPETITOR, competitor);
		args.put(KEY_COMPETITORPRODUCT_PRODUCTBRAND, productBrand);
		args.put(KEY_COMPETITORPRODUCT_PRODUCTDESCRIPTION, productDescription);
		args.put(KEY_COMPETITORPRODUCT_PRODUCTSIZE, productSize);
		args.put(KEY_COMPETITORPRODUCT_ISACTIVE, isActive);
		args.put(KEY_COMPETITORPRODUCT_CREATEDTIME, createdTime);
		args.put(KEY_COMPETITORPRODUCT_MODIFIEDTIME, modifiedTime);
		args.put(KEY_COMPETITORPRODUCT_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_COMPETITORPRODUCT_ROWID + "="
				+ id, null) > 0) {
			getRecords().update(id, no, competitor, productBrand,
					productDescription, productSize, isActive, createdTime,
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

	public CompetitorProductCollection getRecords() {
		if (competitorProductRecords == null) {
			competitorProductRecords = new CompetitorProductCollection();
			competitorProductRecords.list = getAllRecords();
		}
		return competitorProductRecords;
	}

	public final class CompetitorProductCollection implements
			Iterable<CompetitorProductRecord> {

		private List<CompetitorProductRecord> list;

		private CompetitorProductCollection() {
		}

		public int size() {
			return list.size();
		}

		public CompetitorProductRecord get(int i) {
			return list.get(i);
		}

		public CompetitorProductRecord getById(long id) {
			for (CompetitorProductRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, long competitor,
				String productBrand, String productDescription,
				String productSize, int isActive, String createdTime,
				String modifiedTime, long user) {
			list.add(new CompetitorProductRecord(id, no, competitor,
					productBrand, productDescription, productSize, isActive,
					createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, long competitor,
				String productBrand, String productDescription,
				String productSize, int isActive, String createdTime,
				String modifiedTime, long user) {
			CompetitorProductRecord record = getById(id);
			record.setNo(no);
			record.setCompetitor(competitor);
			record.setProductBrand(productBrand);
			record.setProductDescription(productDescription);
			record.setProductSize(productSize);
			record.setIsActive(isActive);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<CompetitorProductRecord> iterator() {
			Iterator<CompetitorProductRecord> iter = new Iterator<CompetitorProductRecord>() {
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
				public CompetitorProductRecord next() {
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
