package co.nextix.jardine.database.tables;

import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_DESCRIPTION;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_EVENTTYPE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_ISACTIVE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_LASTUPDATE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_TAGS;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_EVENTPROTOCOL_USER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.EventProtocolRecord;

public class EventProtocolTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	// private EventProtocolCollection eventProtocolCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public EventProtocolTable(SQLiteDatabase database, String tableName) {
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

	public List<EventProtocolRecord> getAllRecords() {
		Cursor c = null;
		List<EventProtocolRecord> list = new ArrayList<EventProtocolRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_CRMNO));
					String description = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_TAGS));
					long eventType = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_EVENTTYPE));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_EVENTPROTOCOL_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_USER));

					list.add(new EventProtocolRecord(id, no, crmNo,
							description, lastUpdate, tags, eventType, isActive,
							createdTime, modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<EventProtocolRecord> getAllRecordsByUser(long userId) {
		Cursor c = null;
		List<EventProtocolRecord> list = new ArrayList<EventProtocolRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_EVENTPROTOCOL_USER + " = " + userId;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_CRMNO));
					String description = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_TAGS));
					long eventType = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_EVENTTYPE));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_EVENTPROTOCOL_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_USER));

					list.add(new EventProtocolRecord(id, no, crmNo,
							description, lastUpdate, tags, eventType, isActive,
							createdTime, modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<EventProtocolRecord> getAllRecordsBySearch(long userId,
			String hint, int column) {
		Cursor c = null;
		List<EventProtocolRecord> list = new ArrayList<EventProtocolRecord>();
		String MY_QUERY = "";

		switch (column) {
		case 0:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_EVENTPROTOCOL_CRMNO + " LIKE '%" + hint + "%'";
			break;
		case 1:

			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_EVENTPROTOCOL_DESCRIPTION + " LIKE '%" + hint + "%'";
			break;
		case 2:
			// SELECT Event_Protocol.* FROM Event_Protocol inner join Event_Type
			// on Event_Protocol.event_type = Event_Type._id Where
			// Event_Protocol.user = 1 AND Event_Type.name LIKE '%-%'
			MY_QUERY = "SELECT " + mDatabaseTable + ".* FROM " + mDatabaseTable
					+ " INNER JOIN Event_Type ON " + mDatabaseTable + "."
					+ KEY_EVENTPROTOCOL_EVENTTYPE + " = Event_Type._id WHERE "
					+ "Event_Type.name LIKE '%" + hint + "%'";

			break;
		default:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_EVENTPROTOCOL_USER + " = " + userId;
			break;
		}
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_ROWID));
					String no = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_CRMNO));
					String description = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_DESCRIPTION));
					String lastUpdate = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_LASTUPDATE));
					String tags = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_TAGS));
					long eventType = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_EVENTTYPE));
					int isActive = c.getInt(c
							.getColumnIndex(KEY_EVENTPROTOCOL_ISACTIVE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_MODIFIEDTIME));
					long user = c.getLong(c
							.getColumnIndex(KEY_EVENTPROTOCOL_USER));

					list.add(new EventProtocolRecord(id, no, crmNo,
							description, lastUpdate, tags, eventType, isActive,
							createdTime, modifiedTime, user));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<String> getNos() {
		Cursor c = null;
		List<String> list = new ArrayList<String>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					String no = c.getString(c
							.getColumnIndex(KEY_EVENTPROTOCOL_NO));

					list.add(no);
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
				+ KEY_EVENTPROTOCOL_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_EVENTPROTOCOL_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_EVENTPROTOCOL_NO
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
		String MY_QUERY = "SELECT " + KEY_EVENTPROTOCOL_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_EVENTPROTOCOL_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_EVENTPROTOCOL_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public EventProtocolRecord getById(long ID) {
		EventProtocolRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_EVENTPROTOCOL_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_EVENTPROTOCOL_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_EVENTPROTOCOL_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_CRMNO));
				String description = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_DESCRIPTION));
				String lastUpdate = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_LASTUPDATE));
				String tags = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_TAGS));
				long eventType = c.getLong(c
						.getColumnIndex(KEY_EVENTPROTOCOL_EVENTTYPE));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_EVENTPROTOCOL_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_EVENTPROTOCOL_USER));

				record = new EventProtocolRecord(id, no, crmNo, description,
						lastUpdate, tags, eventType, isActive, createdTime,
						modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public EventProtocolRecord getByWebId(String ID) {
		EventProtocolRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_EVENTPROTOCOL_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_EVENTPROTOCOL_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_EVENTPROTOCOL_NO));
				String crmNo = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_CRMNO));
				String description = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_DESCRIPTION));
				String lastUpdate = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_LASTUPDATE));
				String tags = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_TAGS));
				long eventType = c.getLong(c
						.getColumnIndex(KEY_EVENTPROTOCOL_EVENTTYPE));
				int isActive = c.getInt(c
						.getColumnIndex(KEY_EVENTPROTOCOL_ISACTIVE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_EVENTPROTOCOL_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_EVENTPROTOCOL_USER));

				record = new EventProtocolRecord(id, no, crmNo, description,
						lastUpdate, tags, eventType, isActive, createdTime,
						modifiedTime, user);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String description,
			String lastUpdate, String tags, long eventType, int isActive,
			String createdTime, String modifiedTime, long user) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// EventProtocolCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_EVENTPROTOCOL_NO, no);
		initialValues.put(KEY_EVENTPROTOCOL_CRMNO, crmNo);
		initialValues.put(KEY_EVENTPROTOCOL_DESCRIPTION, description);
		initialValues.put(KEY_EVENTPROTOCOL_LASTUPDATE, lastUpdate);
		initialValues.put(KEY_EVENTPROTOCOL_TAGS, tags);
		initialValues.put(KEY_EVENTPROTOCOL_EVENTTYPE, eventType);
		initialValues.put(KEY_EVENTPROTOCOL_ISACTIVE, isActive);
		initialValues.put(KEY_EVENTPROTOCOL_CREATEDTIME, createdTime);
		initialValues.put(KEY_EVENTPROTOCOL_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_EVENTPROTOCOL_USER, user);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			// collection.add(ids, no, description, lastUpdate, tags, eventType,
			// isActive, createdTime, modifiedTime, user);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_EVENTPROTOCOL_ROWID + "=" + rowId,
				null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(long id, String no, String crmNo, String description,
			String lastUpdate, String tags, long eventType, int isActive,
			String createdTime, String modifiedTime, long user) {
		ContentValues args = new ContentValues();
		args.put(KEY_EVENTPROTOCOL_NO, no);
		args.put(KEY_EVENTPROTOCOL_CRMNO, crmNo);
		args.put(KEY_EVENTPROTOCOL_DESCRIPTION, description);
		args.put(KEY_EVENTPROTOCOL_LASTUPDATE, lastUpdate);
		args.put(KEY_EVENTPROTOCOL_TAGS, tags);
		args.put(KEY_EVENTPROTOCOL_EVENTTYPE, eventType);
		args.put(KEY_EVENTPROTOCOL_ISACTIVE, isActive);
		args.put(KEY_EVENTPROTOCOL_CREATEDTIME, createdTime);
		args.put(KEY_EVENTPROTOCOL_MODIFIEDTIME, modifiedTime);
		args.put(KEY_EVENTPROTOCOL_USER, user);
		if (mDb.update(mDatabaseTable, args,
				KEY_EVENTPROTOCOL_ROWID + "=" + id, null) > 0) {
			// getRecords().update(id, no, description, lastUpdate, tags,
			// eventType, isActive, createdTime, modifiedTime, user);
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

	// public EventProtocolCollection getRecords() {
	// if (eventProtocolCollection == null) {
	// eventProtocolCollection = new EventProtocolCollection();
	// eventProtocolCollection.list = getAllRecords();
	// }
	// return eventProtocolCollection;
	// }
	//
	// public final class EventProtocolCollection implements
	// Iterable<EventProtocolRecord> {
	//
	// private List<EventProtocolRecord> list;
	//
	// private EventProtocolCollection() {
	// }
	//
	// public int size() {
	// return list.size();
	// }
	//
	// public EventProtocolRecord get(int i) {
	// return list.get(i);
	// }
	//
	// public EventProtocolRecord getById(long id) {
	// for (EventProtocolRecord record : list) {
	// if (record.getId() == id) {
	// return record;
	// }
	// }
	// return null;
	// }
	//
	// private void add(long id, String no, String description,
	// String lastUpdate, String tags, long eventType, int isActive,
	// String createdTime, String modifiedTime, long user) {
	// list.add(new EventProtocolRecord(id, no, description, lastUpdate,
	// tags, eventType, isActive, createdTime, modifiedTime, user));
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
	// private void update(long id, String no, String description,
	// String lastUpdate, String tags, long eventType, int isActive,
	// String createdTime, String modifiedTime, long user) {
	// EventProtocolRecord record = getById(id);
	// record.setNo(no);
	// record.setDescription(description);
	// record.setLastUpdate(lastUpdate);
	// record.setTags(tags);
	// record.setEventType(eventType);
	// record.setIsActive(isActive);
	// record.setCreatedTime(createdTime);
	// record.setModifiedTime(modifiedTime);
	// record.setUser(user);
	// }
	//
	// @Override
	// public Iterator<EventProtocolRecord> iterator() {
	// Iterator<EventProtocolRecord> iter = new Iterator<EventProtocolRecord>()
	// {
	// private int current = 0;
	//
	// @Override
	// public void remove() {
	// if (list.size() > 0) {
	// delete(list.get(current).getId());
	// deleteById(list.get(current).getId());
	// list.remove(current);
	// }
	// }
	//
	// @Override
	// public EventProtocolRecord next() {
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
