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
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;

public class JDIproductStockCheckTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_JDIPRODUCTSTOCKCHECK_ROWID = "_id";
	private final String KEY_JDIPRODUCTSTOCKCHECK_NO = "no";
	private final String KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY = "customer_name";
	private final String KEY_JDIPRODUCTSTOCKCHECK_PRODUCT = "chain_name";
	private final String KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS = "landline";
	private final String KEY_JDIPRODUCTSTOCKCHECK_QUANTITY = "customer_size";
	private final String KEY_JDIPRODUCTSTOCKCHECK_LOADEDONSHELVES = "customer_record_status";
	private final String KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER = "customer_type";
	private final String KEY_JDIPRODUCTSTOCKCHECK_CREATEDTIME = "created_time";
	private final String KEY_JDIPRODUCTSTOCKCHECK_MODIFIEDTIME = "modified_time";
	private final String KEY_JDIPRODUCTSTOCKCHECK_USER = "user";

	// ===========================================================
	// Private fields
	// ===========================================================

	private JDIProductStockCheckCollection JDIproductStockCheckRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public JDIproductStockCheckTable(SQLiteDatabase database, String tableName) {
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

	private List<JDIproductStockCheckRecord> getAllRecords() {
		Cursor c = null;
		List<JDIproductStockCheckRecord> list = new ArrayList<JDIproductStockCheckRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_NO));
					long activity = c.getLong(c
							.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY));
					long product = c.getLong(c
							.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_PRODUCT));
					long stockStatus = c
							.getLong(c
									.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS));
					int quantity = c.getInt(c
							.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_QUANTITY));
					int loadedOnShelves = c
							.getInt(c
									.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_LOADEDONSHELVES));
					long supplier = c.getLong(c
							.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER));
					String createdTime = c
							.getString(c
									.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_CREATEDTIME));
					String modifiedTime = c
							.getString(c
									.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_USER));

					list.add(new JDIproductStockCheckRecord(id, no, activity,
							product, stockStatus, quantity, loadedOnShelves,
							supplier, createdTime, modifiedTime, user));
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
				+ KEY_JDIPRODUCTSTOCKCHECK_NO + "='" + webID + "'";
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
				KEY_JDIPRODUCTSTOCKCHECK_ROWID + " IN (" + ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public JDIproductStockCheckRecord getById(int ID) {
		JDIproductStockCheckRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_JDIPRODUCTSTOCKCHECK_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_NO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY));
				long product = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_PRODUCT));
				long stockStatus = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS));
				int quantity = c.getInt(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_QUANTITY));
				int loadedOnShelves = c
						.getInt(c
								.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_LOADEDONSHELVES));
				long supplier = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_USER));

				record = new JDIproductStockCheckRecord(id, no, activity,
						product, stockStatus, quantity, loadedOnShelves,
						supplier, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public JDIproductStockCheckRecord getByWebId(String ID) {
		JDIproductStockCheckRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_JDIPRODUCTSTOCKCHECK_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_ROWID));
				String no = c.getString(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_NO));
				long activity = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY));
				long product = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_PRODUCT));
				long stockStatus = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS));
				int quantity = c.getInt(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_QUANTITY));
				int loadedOnShelves = c
						.getInt(c
								.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_LOADEDONSHELVES));
				long supplier = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_MODIFIEDTIME));
				long user = c.getLong(c
						.getColumnIndex(KEY_JDIPRODUCTSTOCKCHECK_USER));

				record = new JDIproductStockCheckRecord(id, no, activity,
						product, stockStatus, quantity, loadedOnShelves,
						supplier, createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, long activity, long product,
			long stockStatus, int quantity, int loadedOnShelves, long supplier,
			String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		JDIProductStockCheckCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_NO, no);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY, activity);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_PRODUCT, product);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS, stockStatus);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_QUANTITY, quantity);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_LOADEDONSHELVES,
				loadedOnShelves);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER, supplier);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_CREATEDTIME, createdTime);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_JDIPRODUCTSTOCKCHECK_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, activity, product, stockStatus, quantity,
					loadedOnShelves, supplier, createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_JDIPRODUCTSTOCKCHECK_ROWID + "="
				+ rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, long activity, long product,
			long stockStatus, int quantity, int loadedOnShelves, long supplier,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_JDIPRODUCTSTOCKCHECK_NO, no);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY, activity);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_PRODUCT, product);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS, stockStatus);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_QUANTITY, quantity);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_LOADEDONSHELVES, loadedOnShelves);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER, supplier);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_CREATEDTIME, createdTime);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_MODIFIEDTIME, modifiedTime);
		args.put(KEY_JDIPRODUCTSTOCKCHECK_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_JDIPRODUCTSTOCKCHECK_ROWID
				+ "=" + id, null) > 0) {
			getRecords().update(id, no, activity, product, stockStatus,
					quantity, loadedOnShelves, supplier, createdTime,
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

	public JDIProductStockCheckCollection getRecords() {
		if (JDIproductStockCheckRecords == null) {
			JDIproductStockCheckRecords = new JDIProductStockCheckCollection();
			JDIproductStockCheckRecords.list = getAllRecords();
		}
		return JDIproductStockCheckRecords;
	}

	public final class JDIProductStockCheckCollection implements
			Iterable<JDIproductStockCheckRecord> {

		private List<JDIproductStockCheckRecord> list;

		private JDIProductStockCheckCollection() {
		}

		public int size() {
			return list.size();
		}

		public JDIproductStockCheckRecord get(int i) {
			return list.get(i);
		}

		public JDIproductStockCheckRecord getById(long id) {
			for (JDIproductStockCheckRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, long activity, long product,
				long stockStatus, int quantity, int loadedOnShelves,
				long supplier, String createdTime, String modifiedTime,
				long user) {
			list.add(new JDIproductStockCheckRecord(id, no, activity, product,
					stockStatus, quantity, loadedOnShelves, supplier,
					createdTime, modifiedTime, user));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, long activity, long product,
				long stockStatus, int quantity, int loadedOnShelves,
				long supplier, String createdTime, String modifiedTime,
				long user) {
			JDIproductStockCheckRecord record = getById(id);
			record.setNo(no);
			record.setActivity(activity);
			record.setProduct(product);
			record.setStockStatus(stockStatus);
			record.setQuantity(quantity);
			record.setLoadedOnShelves(loadedOnShelves);
			record.setSupplier(supplier);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
		}

		@Override
		public Iterator<JDIproductStockCheckRecord> iterator() {
			Iterator<JDIproductStockCheckRecord> iter = new Iterator<JDIproductStockCheckRecord>() {
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
				public JDIproductStockCheckRecord next() {
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
