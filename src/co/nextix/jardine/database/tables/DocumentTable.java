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
import co.nextix.jardine.database.records.DocuInfoRecord;
import co.nextix.jardine.database.records.DocumentRecord;

import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_TITLE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_MODULENAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_MODULEID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_FILENAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_FILETYPE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_FILEPATH;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_ISACTIVE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_USER;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_DOCUMENT_CRMNO;

public class DocumentTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	// private BusinessUnitCollection businessUnitRecords;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public DocumentTable(SQLiteDatabase database, String tableName) {
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

	public List<DocumentRecord> getAllRecords() {
		Cursor c = null;
		List<DocumentRecord> list = new ArrayList<DocumentRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_DOCUMENT_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_DOCUMENT_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_CRMNO));
					String title = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_TITLE));
					String moduleName = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODULENAME));
					String moduleId = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODULEID));
					String fileName = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILENAME));
					String fileType = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILETYPE));
					String filePath = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILEPATH));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_DOCUMENT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_DOCUMENT_USER));

					list.add(new DocumentRecord(id, no, crmNo, title,
							moduleName, moduleId, fileName, fileType, filePath,
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

	public List<DocumentRecord> getAllByCrmNo(String crm) {
		Cursor c = null;
		List<DocumentRecord> list = new ArrayList<DocumentRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_DOCUMENT_MODULEID + " = '" + crm + "'";
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_DOCUMENT_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_DOCUMENT_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_CRMNO));
					String title = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_TITLE));
					String moduleName = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODULENAME));
					String moduleId = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODULEID));
					String fileName = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILENAME));
					String fileType = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILETYPE));
					String filePath = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILEPATH));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_DOCUMENT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_DOCUMENT_USER));

					list.add(new DocumentRecord(id, no, crmNo, title,
							moduleName, moduleId, fileName, fileType, filePath,
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

	public List<DocuInfoRecord> getDocuNos() {
		Cursor c = null;
		List<DocuInfoRecord> list = new ArrayList<DocuInfoRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					String moduleName = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODULENAME));
					String moduleId = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODULEID));

					list.add(new DocuInfoRecord(moduleId, moduleName));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<DocumentRecord> getUnsyncedRecords() {
		List<DocumentRecord> list = new ArrayList<DocumentRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_DOCUMENT_NO + " ISNULL";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_DOCUMENT_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_DOCUMENT_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_CRMNO));
					String title = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_TITLE));
					String moduleName = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODULENAME));
					String moduleId = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODULEID));
					String fileName = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILENAME));
					String fileType = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILETYPE));
					String filePath = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_FILEPATH));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_DOCUMENT_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_DOCUMENT_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_DOCUMENT_USER));

					list.add(new DocumentRecord(id, no, crmNo, title,
							moduleName, moduleId, fileName, fileType, filePath,
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
				+ KEY_DOCUMENT_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_DOCUMENT_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_DOCUMENT_NO + " IN ("
				+ ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public DocumentRecord getById(int ID) {
		DocumentRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_DOCUMENT_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_DOCUMENT_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_DOCUMENT_NO));
				String crmNo = c
						.getString(c.getColumnIndex(KEY_DOCUMENT_CRMNO));
				String title = c
						.getString(c.getColumnIndex(KEY_DOCUMENT_TITLE));
				String moduleName = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_MODULENAME));
				String moduleId = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_MODULEID));
				String fileName = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_FILENAME));
				String fileType = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_FILETYPE));
				String filePath = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_FILEPATH));
				int isActive = c
						.getInt(c.getColumnIndex(KEY_DOCUMENT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_DOCUMENT_USER));

				record = new DocumentRecord(id, no, crmNo, title, moduleName,
						moduleId, fileName, fileType, filePath, isActive,
						createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public DocumentRecord getByWebId(String ID) {
		DocumentRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_DOCUMENT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_DOCUMENT_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_DOCUMENT_NO));
				String crmNo = c
						.getString(c.getColumnIndex(KEY_DOCUMENT_CRMNO));
				String title = c
						.getString(c.getColumnIndex(KEY_DOCUMENT_TITLE));
				String moduleName = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_MODULENAME));
				String moduleId = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_MODULEID));
				String fileName = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_FILENAME));
				String fileType = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_FILETYPE));
				String filePath = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_FILEPATH));
				int isActive = c
						.getInt(c.getColumnIndex(KEY_DOCUMENT_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_DOCUMENT_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_DOCUMENT_USER));

				record = new DocumentRecord(id, no, crmNo, title, moduleName,
						moduleId, fileName, fileType, filePath, isActive,
						createdTime, modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long getIdByNo(String no) {
		long result = 0;
		String MY_QUERY = "SELECT " + KEY_DOCUMENT_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_DOCUMENT_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_DOCUMENT_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public String getNoById(long ID) {
		String result = null;
		String MY_QUERY = "SELECT " + KEY_DOCUMENT_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_DOCUMENT_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_DOCUMENT_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public long insert(String no, String crmNo, String title,
			String moduleName, String moduleId, String fileName,
			String fileType, String filePath, int isActive, String createdTime,
			String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// BusinessUnitCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_DOCUMENT_NO, no);
		initialValues.put(KEY_DOCUMENT_CRMNO, crmNo);
		initialValues.put(KEY_DOCUMENT_TITLE, title);
		initialValues.put(KEY_DOCUMENT_MODULENAME, moduleName);
		initialValues.put(KEY_DOCUMENT_MODULEID, moduleId);
		initialValues.put(KEY_DOCUMENT_FILENAME, fileName);
		initialValues.put(KEY_DOCUMENT_FILETYPE, fileType);
		initialValues.put(KEY_DOCUMENT_FILEPATH, filePath);
		initialValues.put(KEY_DOCUMENT_ISACTIVE, isActive);
		initialValues.put(KEY_DOCUMENT_CREATEDTIME, createdTime);
		initialValues.put(KEY_DOCUMENT_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_DOCUMENT_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, businessUnitName, businessUnitCode,
			// isActive, createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_DOCUMENT_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateNo(long id, String no) {
		ContentValues args = new ContentValues();
		args.put(KEY_DOCUMENT_NO, no);
		if (mDb.update(mDatabaseTable, args, KEY_DOCUMENT_ROWID + "=" + id,
				null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateCrmNo(long id, String crmNo) {
		ContentValues args = new ContentValues();
		args.put(KEY_DOCUMENT_CRMNO, crmNo);
		if (mDb.update(mDatabaseTable, args, KEY_DOCUMENT_ROWID + "=" + id,
				null) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, String title,
			String moduleName, String moduleId, String fileName,
			String fileType, String filePath, int isActive, String createdTime,
			String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_DOCUMENT_NO, no);
		args.put(KEY_DOCUMENT_CRMNO, crmNo);
		args.put(KEY_DOCUMENT_TITLE, title);
		args.put(KEY_DOCUMENT_MODULENAME, moduleName);
		args.put(KEY_DOCUMENT_MODULEID, moduleId);
		args.put(KEY_DOCUMENT_FILENAME, fileName);
		args.put(KEY_DOCUMENT_FILETYPE, fileType);
		args.put(KEY_DOCUMENT_FILEPATH, filePath);
		args.put(KEY_DOCUMENT_ISACTIVE, isActive);
		args.put(KEY_DOCUMENT_CREATEDTIME, createdTime);
		args.put(KEY_DOCUMENT_MODIFIEDTIME, modifiedTime);
		args.put(KEY_DOCUMENT_USER, user);
		if (mDb.update(mDatabaseTable, args, KEY_DOCUMENT_ROWID + "=" + id,
				null) > 0) {
			// getRecords().update(id, no, businessUnitName, businessUnitCode,
			// isActive, createdTime, modifiedTime, user);
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
