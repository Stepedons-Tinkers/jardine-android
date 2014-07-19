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
import co.nextix.jardine.database.records.ProductRecord;

public class ProductTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_PRODUCT_ROWID = "_id";
	private final String KEY_PRODUCT_NO = "no";
	private final String KEY_PRODUCT_NUMBER = "product_number";
	private final String KEY_PRODUCT_BRAND = "product_brand";
	private final String KEY_PRODUCT_DESCRIPTION = "product_description";
	private final String KEY_PRODUCT_PACKSIZE = "pack_size";
	private final String KEY_PRODUCT_BUSINESSUNIT = "business_unit";
	private final String KEY_PRODUCT_ISACTIVE = "is_active";
	private final String KEY_PRODUCT_CREATEDTIME = "created_time";
	private final String KEY_PRODUCT_MODIFIEDTIME = "modified_time";
	private final String KEY_PRODUCT_CREATEDBY = "created_by";
	private final String KEY_PRODUCT_CRMNO = "crm_no";

	// ===========================================================
	// Private fields
	// ===========================================================

	// private ProductCollection productCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public ProductTable(SQLiteDatabase database, String tableName) {
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

	private List<ProductRecord> getAllRecords() {
		Cursor c = null;
		List<ProductRecord> list = new ArrayList<ProductRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_PRODUCT_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_PRODUCT_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_PRODUCT_CRMNO));
					String productNumber = c.getString(c
							.getColumnIndex(KEY_PRODUCT_NUMBER));
					String productBrand = c.getString(c
							.getColumnIndex(KEY_PRODUCT_BRAND));
					String productDescription = c.getString(c
							.getColumnIndex(KEY_PRODUCT_DESCRIPTION));
					String packSize = c.getString(c
							.getColumnIndex(KEY_PRODUCT_PACKSIZE));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_PRODUCT_BUSINESSUNIT));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_PRODUCT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_PRODUCT_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_PRODUCT_MODIFIEDTIME));
					long createdBy = c.getLong(c.getColumnIndex(KEY_PRODUCT_CREATEDBY));

					list.add(new ProductRecord(id, no, crmNo, productNumber,
							productBrand, productDescription, packSize,
							businessUnit, isActive, createdTime, modifiedTime,
							createdBy));
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
				+ KEY_PRODUCT_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_PRODUCT_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_PRODUCT_NO + " IN ("
				+ ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public ProductRecord getById(long ID) {
		ProductRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PRODUCT_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_PRODUCT_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_PRODUCT_NO));
				String crmNo = c.getString(c.getColumnIndex(KEY_PRODUCT_CRMNO));
				String productNumber = c.getString(c
						.getColumnIndex(KEY_PRODUCT_NUMBER));
				String productBrand = c.getString(c
						.getColumnIndex(KEY_PRODUCT_BRAND));
				String productDescription = c.getString(c
						.getColumnIndex(KEY_PRODUCT_DESCRIPTION));
				String packSize = c.getString(c
						.getColumnIndex(KEY_PRODUCT_PACKSIZE));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_PRODUCT_BUSINESSUNIT));
				int isActive = c.getInt(c.getColumnIndex(KEY_PRODUCT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_PRODUCT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_PRODUCT_MODIFIEDTIME));
				long createdBy = c.getLong(c.getColumnIndex(KEY_PRODUCT_CREATEDBY));

				record = new ProductRecord(id, no, crmNo, productNumber,
						productBrand, productDescription, packSize,
						businessUnit, isActive, createdTime, modifiedTime, createdBy);
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
		String MY_QUERY = "SELECT " + KEY_PRODUCT_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_PRODUCT_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_PRODUCT_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public long getIdByNo(String no) {
		long result = 0;
		String MY_QUERY = "SELECT " + KEY_PRODUCT_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_PRODUCT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_PRODUCT_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public ProductRecord getByWebId(String ID) {
		ProductRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_PRODUCT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_PRODUCT_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_PRODUCT_NO));
				String crmNo = c.getString(c.getColumnIndex(KEY_PRODUCT_CRMNO));
				String productNumber = c.getString(c
						.getColumnIndex(KEY_PRODUCT_NUMBER));
				String productBrand = c.getString(c
						.getColumnIndex(KEY_PRODUCT_BRAND));
				String productDescription = c.getString(c
						.getColumnIndex(KEY_PRODUCT_DESCRIPTION));
				String packSize = c.getString(c
						.getColumnIndex(KEY_PRODUCT_PACKSIZE));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_PRODUCT_BUSINESSUNIT));
				int isActive = c.getInt(c.getColumnIndex(KEY_PRODUCT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_PRODUCT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_PRODUCT_MODIFIEDTIME));
				long createdBy = c.getLong(c.getColumnIndex(KEY_PRODUCT_CREATEDBY));

				record = new ProductRecord(id, no, crmNo, productNumber,
						productBrand, productDescription, packSize,
						businessUnit, isActive, createdTime, modifiedTime, createdBy);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String productNumber,
			String productBrand, String productDescription, String packSize,
			long businessUnit, int isActive, String createdTime,
			String modifiedTime, long createdBy) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// ProductCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_PRODUCT_NO, no);
		initialValues.put(KEY_PRODUCT_CRMNO, crmNo);
		initialValues.put(KEY_PRODUCT_NUMBER, productNumber);
		initialValues.put(KEY_PRODUCT_BRAND, productBrand);
		initialValues.put(KEY_PRODUCT_DESCRIPTION, productDescription);
		initialValues.put(KEY_PRODUCT_PACKSIZE, packSize);
		initialValues.put(KEY_PRODUCT_BUSINESSUNIT, businessUnit);
		initialValues.put(KEY_PRODUCT_ISACTIVE, isActive);
		initialValues.put(KEY_PRODUCT_CREATEDTIME, createdTime);
		initialValues.put(KEY_PRODUCT_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_PRODUCT_CREATEDBY, createdBy);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, productNumber, productBrand,
			// productDescription, productSize, businessUnit, isActive,
			// createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_PRODUCT_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo,
			String productNumber, String productBrand,
			String productDescription, String packSize, long businessUnit,
			int isActive, String createdTime, String modifiedTime, long createdBy) {
		ContentValues args = new ContentValues();
		args.put(KEY_PRODUCT_NO, no);
		args.put(KEY_PRODUCT_CRMNO, crmNo);
		args.put(KEY_PRODUCT_NUMBER, productNumber);
		args.put(KEY_PRODUCT_BRAND, productBrand);
		args.put(KEY_PRODUCT_DESCRIPTION, productDescription);
		args.put(KEY_PRODUCT_PACKSIZE, packSize);
		args.put(KEY_PRODUCT_BUSINESSUNIT, businessUnit);
		args.put(KEY_PRODUCT_ISACTIVE, isActive);
		args.put(KEY_PRODUCT_CREATEDTIME, createdTime);
		args.put(KEY_PRODUCT_MODIFIEDTIME, modifiedTime);
		args.put(KEY_PRODUCT_CREATEDBY, createdBy);
		if (mDb.update(mDatabaseTable, args, KEY_PRODUCT_ROWID + "=" + id, null) > 0) {
			// getRecords().update(id, no, productNumber, productBrand,
			// productDescription, productSize, businessUnit, isActive,
			// createdTime, modifiedTime, user);
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

	// public ProductCollection getRecords() {
	// if (productCollection == null) {
	// productCollection = new ProductCollection();
	// productCollection.list = getAllRecords();
	// }
	// return productCollection;
	// }
	//
	// public final class ProductCollection implements Iterable<ProductRecord> {
	//
	// private List<ProductRecord> list;
	//
	// private ProductCollection() {
	// }
	//
	// public int size() {
	// return list.size();
	// }
	//
	// public ProductRecord get(int i) {
	// return list.get(i);
	// }
	//
	// public ProductRecord getById(long id) {
	// for (ProductRecord record : list) {
	// if (record.getId() == id) {
	// return record;
	// }
	// }
	// return null;
	// }
	//
	// private void add(long id, String no, String productNumber,
	// String productBrand, String productDescription,
	// String productSize, long businessUnit, int isActive,
	// String createdTime, String modifiedTime, long user) {
	// list.add(new ProductRecord(id, no, productNumber, productBrand,
	// productDescription, productSize, businessUnit, isActive,
	// createdTime, modifiedTime, user));
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
	// private void update(long id, String no, String productNumber,
	// String productBrand, String productDescription,
	// String productSize, long businessUnit, int isActive,
	// String createdTime, String modifiedTime, long user) {
	// ProductRecord record = getById(id);
	// record.setNo(no);
	// record.setProductNumber(productNumber);
	// record.setProductBrand(productBrand);
	// record.setProductDescription(productDescription);
	// record.setProductSize(productSize);
	// record.setBusinessUnit(businessUnit);
	// record.setIsActive(isActive);
	// record.setCreatedTime(createdTime);
	// record.setModifiedTime(modifiedTime);
	// record.setUser(user);
	// }
	//
	// @Override
	// public Iterator<ProductRecord> iterator() {
	// Iterator<ProductRecord> iter = new Iterator<ProductRecord>() {
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
	// public ProductRecord next() {
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
