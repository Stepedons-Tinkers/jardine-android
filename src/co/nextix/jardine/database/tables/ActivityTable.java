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
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_ROWID;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_NO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_CRMNO;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_ACTIVITYTYPE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_CHECKIN;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_CHECKOUT;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_BUSINESSUNIT;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_CREATEDBY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_LONGITUDE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_LATITUDE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_CREATEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_MODIFIEDTIME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_REASONREMARKS;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_SMR;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_ADMINWORKDETAILS;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_CUSTOMER;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_AREA;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_PROVINCE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_CITY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_WORKPLANENTRY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_OBJECTIVES;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_FIRSTTIMEVISIT;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_PLANNEDVISIT;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_NOTES;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_HIGHLIGHTS;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_NEXTSTEPS;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_FOLLOWUP;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_PROJECTNAME;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_PROJECTSTAGE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_PROJECTCATEGORY;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_VENUE;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_NOOFATTENDEES;
import static co.nextix.jardine.database.DatabaseAdapter.KEY_ACTIVITY_ENDUSERACTIVITYTYPES;
import co.nextix.jardine.database.records.ActivityRecord;

public class ActivityTable {

	// ===========================================================
	// Private fields
	// ===========================================================

	// private ActivityCollection activityCollection;
	private SQLiteDatabase mDb;
	private String mDatabaseTable;
	private DatabaseAdapter mDBAdapter;

	// ===========================================================
	// Public constructor
	// ===========================================================

	public ActivityTable(SQLiteDatabase database, String tableName) {
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

	public List<ActivityRecord> getAllRecords() {
		Cursor c = null;
		List<ActivityRecord> list = new ArrayList<ActivityRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CRMNO));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					String checkIn = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKIN));
					String checkOut = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_BUSINESSUNIT));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					String reasonsRemarks = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
					long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
					String adminDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PROVINCE));
					long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					String objective = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVES));
					int firstTimeVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
					int plannedVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
					String notes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NOTES));
					String highlights = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
					String nextSteps = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
					String followUpCommitmentDate = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
					String projectName = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
					String projectStage = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));
					String projectCategory = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
					String venue = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_VENUE));
					int numberOfAttendees = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_NOOFATTENDEES));
					String endUserActivityTypes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ENDUSERACTIVITYTYPES));

					list.add(new ActivityRecord(id, no, crmNo, activityType,
							checkIn, checkOut, businessUnit, createdBy,
							longitude, latitude, createdTime, modifiedTime,
							reasonsRemarks, smr, adminDetails, customer, area,
							province, city, workplanEntry, objective,
							firstTimeVisit, plannedVisit, notes, highlights,
							nextSteps, followUpCommitmentDate, projectName,
							projectStage, projectCategory, venue,
							numberOfAttendees, endUserActivityTypes));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<ActivityRecord> getAllRecordsByWorkEntry(long wId) {
		Cursor c = null;
		List<ActivityRecord> list = new ArrayList<ActivityRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITY_WORKPLANENTRY + " = " + wId;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CRMNO));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					String checkIn = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKIN));
					String checkOut = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_BUSINESSUNIT));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					String reasonsRemarks = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
					long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
					String adminDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PROVINCE));
					long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					String objective = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVES));
					int firstTimeVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
					int plannedVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
					String notes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NOTES));
					String highlights = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
					String nextSteps = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
					String followUpCommitmentDate = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
					String projectName = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
					String projectStage = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));
					String projectCategory = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
					String venue = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_VENUE));
					int numberOfAttendees = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_NOOFATTENDEES));
					String endUserActivityTypes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ENDUSERACTIVITYTYPES));

					list.add(new ActivityRecord(id, no, crmNo, activityType,
							checkIn, checkOut, businessUnit, createdBy,
							longitude, latitude, createdTime, modifiedTime,
							reasonsRemarks, smr, adminDetails, customer, area,
							province, city, workplanEntry, objective,
							firstTimeVisit, plannedVisit, notes, highlights,
							nextSteps, followUpCommitmentDate, projectName,
							projectStage, projectCategory, venue,
							numberOfAttendees, endUserActivityTypes));
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

	public List<ActivityRecord> getUnsyncedRecords() {
		List<ActivityRecord> list = new ArrayList<ActivityRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITY_NO + " ISNULL";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, null);

			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CRMNO));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					String checkIn = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKIN));
					String checkOut = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_BUSINESSUNIT));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					String reasonsRemarks = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
					long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
					String adminDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PROVINCE));
					long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					String objective = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVES));
					int firstTimeVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
					int plannedVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
					String notes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NOTES));
					String highlights = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
					String nextSteps = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
					String followUpCommitmentDate = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
					String projectName = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
					String projectStage = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));
					String projectCategory = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
					String venue = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_VENUE));
					int numberOfAttendees = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_NOOFATTENDEES));
					String endUserActivityTypes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ENDUSERACTIVITYTYPES));

					list.add(new ActivityRecord(id, no, crmNo, activityType,
							checkIn, checkOut, businessUnit, createdBy,
							longitude, latitude, createdTime, modifiedTime,
							reasonsRemarks, smr, adminDetails, customer, area,
							province, city, workplanEntry, objective,
							firstTimeVisit, plannedVisit, notes, highlights,
							nextSteps, followUpCommitmentDate, projectName,
							projectStage, projectCategory, venue,
							numberOfAttendees, endUserActivityTypes));
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
					String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));

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

	public boolean updateNo(long id, String no) {
		ContentValues args = new ContentValues();
		args.put(KEY_ACTIVITY_NO, no);
		if (mDb.update(mDatabaseTable, args, KEY_ACTIVITY_ROWID + "=" + id,
				null) > 0) {
			// getRecords().update(id, no, competitor, productBrand,
			// productDescription, productSize, isActive, createdTime,
			// modifiedTime, user);
			return true;
		} else {
			return false;
		}
	}

	public boolean isExisting(String webID) {
		boolean exists = false;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITY_NO + "='" + webID + "'";
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_ACTIVITY_ROWID
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

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_ACTIVITY_NO + " IN ("
				+ ids + ")", null);

		// if (rowsDeleted > 0) {
		//
		// // Delete the calls that are referring to the deleted work plan
		// getDBAdapter().getCalls().deleteRecordsWithoutUserParent();
		// }

		return rowsDeleted;
	}

	public long getIdByNo(String no) {
		long result = 0;
		String MY_QUERY = "SELECT " + KEY_ACTIVITY_ROWID + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_ACTIVITY_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(no) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public ActivityRecord getById(long ID) {
		ActivityRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITY_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
				String crmNo = c
						.getString(c.getColumnIndex(KEY_ACTIVITY_CRMNO));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
				String checkIn = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CHECKIN));
				String checkOut = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_BUSINESSUNIT));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
				double longitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
				double latitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LATITUDE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
				String reasonsRemarks = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
				long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
				String adminDetails = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
				long customer = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
				long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_PROVINCE));
				long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
				long workplanEntry = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
				String objective = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_OBJECTIVES));
				int firstTimeVisit = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
				int plannedVisit = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
				String notes = c
						.getString(c.getColumnIndex(KEY_ACTIVITY_NOTES));
				String highlights = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
				String nextSteps = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
				String followUpCommitmentDate = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
				String projectName = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
				String projectStage = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));
				String projectCategory = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
				String venue = c
						.getString(c.getColumnIndex(KEY_ACTIVITY_VENUE));
				int numberOfAttendees = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_NOOFATTENDEES));
				String endUserActivityTypes = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ENDUSERACTIVITYTYPES));

				record = new ActivityRecord(id, no, crmNo, activityType,
						checkIn, checkOut, businessUnit, createdBy, longitude,
						latitude, createdTime, modifiedTime, reasonsRemarks,
						smr, adminDetails, customer, area, province, city,
						workplanEntry, objective, firstTimeVisit, plannedVisit,
						notes, highlights, nextSteps, followUpCommitmentDate,
						projectName, projectStage, projectCategory, venue,
						numberOfAttendees, endUserActivityTypes);
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
		String MY_QUERY = "SELECT " + KEY_ACTIVITY_NO + " FROM "
				+ mDatabaseTable + " WHERE " + KEY_ACTIVITY_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				result = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	public ActivityRecord getByWebId(String ID) {
		ActivityRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITY_NO + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
				String crmNo = c
						.getString(c.getColumnIndex(KEY_ACTIVITY_CRMNO));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
				String checkIn = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CHECKIN));
				String checkOut = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
				long businessUnit = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_BUSINESSUNIT));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
				double longitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
				double latitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LATITUDE));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
				String reasonsRemarks = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
				long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
				String adminDetails = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
				long customer = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
				long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_PROVINCE));
				long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
				long workplanEntry = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
				String objective = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_OBJECTIVES));
				int firstTimeVisit = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
				int plannedVisit = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
				String notes = c
						.getString(c.getColumnIndex(KEY_ACTIVITY_NOTES));
				String highlights = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
				String nextSteps = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
				String followUpCommitmentDate = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
				String projectName = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
				String projectStage = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));
				String projectCategory = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
				String venue = c
						.getString(c.getColumnIndex(KEY_ACTIVITY_VENUE));
				int numberOfAttendees = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_NOOFATTENDEES));
				String endUserActivityTypes = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ENDUSERACTIVITYTYPES));

				record = new ActivityRecord(id, no, crmNo, activityType,
						checkIn, checkOut, businessUnit, createdBy, longitude,
						latitude, createdTime, modifiedTime, reasonsRemarks,
						smr, adminDetails, customer, area, province, city,
						workplanEntry, objective, firstTimeVisit, plannedVisit,
						notes, highlights, nextSteps, followUpCommitmentDate,
						projectName, projectStage, projectCategory, venue,
						numberOfAttendees, endUserActivityTypes);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, long activityType,
			String checkIn, String checkOut, long businessUnit, long createdBy,
			double longitude, double latitude, String createdTime,
			String modifiedTime, String reasonsRemarks, long smr,
			String adminDetails, long customer, long area, long province,
			long city, long workplanEntry, String objective,
			int firstTimeVisit, int plannedVisit, String notes,
			String highlights, String nextSteps, String followUpCommitmentDate,
			String projectName, String projectStage, String projectCategory,
			String venue, int numberOfAttendees, String endUserActivityTypes) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// ActivityCollection collection = getRecords();

		ContentValues args = new ContentValues();

		args.put(KEY_ACTIVITY_NO, no);
		args.put(KEY_ACTIVITY_CRMNO, crmNo);
		args.put(KEY_ACTIVITY_ACTIVITYTYPE, activityType);
		args.put(KEY_ACTIVITY_CHECKIN, checkIn);
		args.put(KEY_ACTIVITY_CHECKOUT, checkOut);
		args.put(KEY_ACTIVITY_BUSINESSUNIT, businessUnit);
		args.put(KEY_ACTIVITY_CREATEDBY, createdBy);
		args.put(KEY_ACTIVITY_LONGITUDE, longitude);
		args.put(KEY_ACTIVITY_LATITUDE, latitude);
		args.put(KEY_ACTIVITY_CREATEDTIME, createdTime);
		args.put(KEY_ACTIVITY_MODIFIEDTIME, modifiedTime);
		args.put(KEY_ACTIVITY_REASONREMARKS, reasonsRemarks);
		args.put(KEY_ACTIVITY_SMR, smr);
		args.put(KEY_ACTIVITY_ADMINWORKDETAILS, adminDetails);
		args.put(KEY_ACTIVITY_CUSTOMER, customer);
		args.put(KEY_ACTIVITY_AREA, area);
		args.put(KEY_ACTIVITY_PROVINCE, province);
		args.put(KEY_ACTIVITY_CITY, city);
		args.put(KEY_ACTIVITY_WORKPLANENTRY, workplanEntry);
		args.put(KEY_ACTIVITY_OBJECTIVES, objective);
		args.put(KEY_ACTIVITY_FIRSTTIMEVISIT, firstTimeVisit);
		args.put(KEY_ACTIVITY_PLANNEDVISIT, plannedVisit);
		args.put(KEY_ACTIVITY_NOTES, notes);
		args.put(KEY_ACTIVITY_HIGHLIGHTS, highlights);
		args.put(KEY_ACTIVITY_NEXTSTEPS, nextSteps);
		args.put(KEY_ACTIVITY_FOLLOWUP, followUpCommitmentDate);
		args.put(KEY_ACTIVITY_PROJECTNAME, projectName);
		args.put(KEY_ACTIVITY_PROJECTSTAGE, projectStage);
		args.put(KEY_ACTIVITY_PROJECTCATEGORY, projectCategory);
		args.put(KEY_ACTIVITY_VENUE, venue);
		args.put(KEY_ACTIVITY_NOOFATTENDEES, numberOfAttendees);
		args.put(KEY_ACTIVITY_ENDUSERACTIVITYTYPES, endUserActivityTypes);

		long ids = mDb.insert(mDatabaseTable, null, args);
		if (ids >= 0) {
			// collection.add(ids, no, workplan, startTime, endTime, longitude,
			// latitude, objectives, notes, highlights, nextSteps,
			// followUpCommitmentDate, activityType, workplanEntry,
			// customer, firstTimeVisit, plannedVisit, createdTime,
			// modifiedTime, user, smr, issuesIdentified,
			// feedBackFromCustomer, ongoingCampaigns, lastDelivery,
			// promoStubsDetails, projectName, projectCategory,
			// projectStage, date, time, venue, noOfAttendees);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean delete(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_ACTIVITY_ROWID + "=" + rowId, null) > 0) {
			// getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public int deleteByWebID(String[] webID) {

		String ids = Arrays.toString(webID);

		if (ids == null) {
			return 0;
		}

		// Remove the surrounding bracket([]) created by the method
		// Arrays.toString()
		ids = ids.replace("[", "").replace("]", "").replace(", ", "','");

		int rowsDeleted = mDb.delete(mDatabaseTable, KEY_ACTIVITY_NO + " IN ('"
				+ ids + "')", null);

		return rowsDeleted;
	}

	public boolean update(long id, String no, String crmNo, long activityType,
			String checkIn, String checkOut, long businessUnit, long createdBy,
			double longitude, double latitude, String createdTime,
			String modifiedTime, String reasonsRemarks, long smr,
			String adminDetails, long customer, long area, long province,
			long city, long workplanEntry, String objective,
			int firstTimeVisit, int plannedVisit, String notes,
			String highlights, String nextSteps, String followUpCommitmentDate,
			String projectName, String projectStage, String projectCategory,
			String venue, int numberOfAttendees, String endUserActivityTypes) {
		ContentValues args = new ContentValues();
		args.put(KEY_ACTIVITY_NO, no);
		args.put(KEY_ACTIVITY_CRMNO, crmNo);
		args.put(KEY_ACTIVITY_ACTIVITYTYPE, activityType);
		args.put(KEY_ACTIVITY_CHECKIN, checkIn);
		args.put(KEY_ACTIVITY_CHECKOUT, checkOut);
		args.put(KEY_ACTIVITY_BUSINESSUNIT, businessUnit);
		args.put(KEY_ACTIVITY_CREATEDBY, createdBy);
		args.put(KEY_ACTIVITY_LONGITUDE, longitude);
		args.put(KEY_ACTIVITY_LATITUDE, latitude);
		args.put(KEY_ACTIVITY_CREATEDTIME, createdTime);
		args.put(KEY_ACTIVITY_MODIFIEDTIME, modifiedTime);
		args.put(KEY_ACTIVITY_REASONREMARKS, reasonsRemarks);
		args.put(KEY_ACTIVITY_SMR, smr);
		args.put(KEY_ACTIVITY_ADMINWORKDETAILS, adminDetails);
		args.put(KEY_ACTIVITY_CUSTOMER, customer);
		args.put(KEY_ACTIVITY_AREA, area);
		args.put(KEY_ACTIVITY_PROVINCE, province);
		args.put(KEY_ACTIVITY_CITY, city);
		args.put(KEY_ACTIVITY_WORKPLANENTRY, workplanEntry);
		args.put(KEY_ACTIVITY_OBJECTIVES, objective);
		args.put(KEY_ACTIVITY_FIRSTTIMEVISIT, firstTimeVisit);
		args.put(KEY_ACTIVITY_PLANNEDVISIT, plannedVisit);
		args.put(KEY_ACTIVITY_NOTES, notes);
		args.put(KEY_ACTIVITY_HIGHLIGHTS, highlights);
		args.put(KEY_ACTIVITY_NEXTSTEPS, nextSteps);
		args.put(KEY_ACTIVITY_FOLLOWUP, followUpCommitmentDate);
		args.put(KEY_ACTIVITY_PROJECTNAME, projectName);
		args.put(KEY_ACTIVITY_PROJECTSTAGE, projectStage);
		args.put(KEY_ACTIVITY_PROJECTCATEGORY, projectCategory);
		args.put(KEY_ACTIVITY_VENUE, venue);
		args.put(KEY_ACTIVITY_NOOFATTENDEES, numberOfAttendees);
		args.put(KEY_ACTIVITY_ENDUSERACTIVITYTYPES, endUserActivityTypes);

		if (mDb.update(mDatabaseTable, args, KEY_ACTIVITY_ROWID + "=" + id,
				null) > 0) {
			// getRecords().update(id, no, workplan, startTime, endTime,
			// longitude, latitude, objectives, notes, highlights,
			// nextSteps, followUpCommitmentDate, activityType,
			// workplanEntry, customer, firstTimeVisit, plannedVisit,
			// createdTime, modifiedTime, user, smr, issuesIdentified,
			// feedBackFromCustomer, ongoingCampaigns, lastDelivery,
			// promoStubsDetails, projectName, projectCategory,
			// projectStage, date, time, venue, noOfAttendees);
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

	public List<ActivityRecord> getAllRecordsByDate(String hint) {
		Cursor c = null;
		List<ActivityRecord> list = new ArrayList<ActivityRecord>();
		String MY_QUERY = "";

		MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITY_CHECKIN + " LIKE " + "'%" + hint + "%'";

		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CRMNO));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					String checkIn = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKIN));
					String checkOut = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_BUSINESSUNIT));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					String reasonsRemarks = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
					long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
					String adminDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PROVINCE));
					long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					String objective = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVES));
					int firstTimeVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
					int plannedVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
					String notes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NOTES));
					String highlights = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
					String nextSteps = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
					String followUpCommitmentDate = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
					String projectName = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
					String projectStage = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));
					String projectCategory = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
					String venue = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_VENUE));
					int numberOfAttendees = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_NOOFATTENDEES));
					String endUserActivityTypes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ENDUSERACTIVITYTYPES));

					list.add(new ActivityRecord(id, no, crmNo, activityType,
							checkIn, checkOut, businessUnit, createdBy,
							longitude, latitude, createdTime, modifiedTime,
							reasonsRemarks, smr, adminDetails, customer, area,
							province, city, workplanEntry, objective,
							firstTimeVisit, plannedVisit, notes, highlights,
							nextSteps, followUpCommitmentDate, projectName,
							projectStage, projectCategory, venue,
							numberOfAttendees, endUserActivityTypes));
					// list.add(new CustomerRecord(id, no, crmNo, customerName,
					// chainName, landline, fax, customerSize,
					// streetAddress, customerType, businessUnit, area,
					// province, cityTown, isActive, createdTime,
					// modifiedTime, created_by));
				} while (c.moveToNext());
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return list;
	}

	public List<ActivityRecord> getAllRecordsBySearch(String hint, int column) {
		Cursor c = null;
		List<ActivityRecord> list = new ArrayList<ActivityRecord>();
		String MY_QUERY = "";

		switch (column) {
		case 0:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_ACTIVITY_CRMNO + " LIKE " + "'%" + hint + "%'";
			break;
		case 1:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_ACTIVITY_WORKPLANENTRY + " LIKE " + "'%" + hint
					+ "%'";
			break;
		case 2:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_ACTIVITY_ACTIVITYTYPE + " LIKE " + "'%" + hint + "%'";
			break;
		case 3:
			MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
					+ KEY_ACTIVITY_ACTIVITYTYPE + " LIKE " + "'%" + hint + "%'";
			break;
		}

		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
					String crmNo = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CRMNO));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					String checkIn = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKIN));
					String checkOut = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
					long businessUnit = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_BUSINESSUNIT));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					String reasonsRemarks = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
					long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
					String adminDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PROVINCE));
					long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					String objective = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVES));
					int firstTimeVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
					int plannedVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
					String notes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NOTES));
					String highlights = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
					String nextSteps = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
					String followUpCommitmentDate = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
					String projectName = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
					String projectStage = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));
					String projectCategory = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
					String venue = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_VENUE));
					int numberOfAttendees = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_NOOFATTENDEES));
					String endUserActivityTypes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ENDUSERACTIVITYTYPES));

					list.add(new ActivityRecord(id, no, crmNo, activityType,
							checkIn, checkOut, businessUnit, createdBy,
							longitude, latitude, createdTime, modifiedTime,
							reasonsRemarks, smr, adminDetails, customer, area,
							province, city, workplanEntry, objective,
							firstTimeVisit, plannedVisit, notes, highlights,
							nextSteps, followUpCommitmentDate, projectName,
							projectStage, projectCategory, venue,
							numberOfAttendees, endUserActivityTypes));
					// list.add(new CustomerRecord(id, no, crmNo, customerName,
					// chainName, landline, fax, customerSize,
					// streetAddress, customerType, businessUnit, area,
					// province, cityTown, isActive, createdTime,
					// modifiedTime, created_by));
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
	// Public Foreign Key Methods
	// ===========================================================

	// ===========================================================
	// Collection
	// ===========================================================

	// public ActivityCollection getRecords() {
	// if (activityCollection == null) {
	// activityCollection = new ActivityCollection();
	// activityCollection.list = getAllRecords();
	// }
	// return activityCollection;
	// }
	//
	// public final class ActivityCollection implements Iterable<ActivityRecord>
	// {
	//
	// private List<ActivityRecord> list;
	//
	// private ActivityCollection() {
	// }
	//
	// public int size() {
	// return list.size();
	// }
	//
	// public ActivityRecord get(int i) {
	// return list.get(i);
	// }
	//
	// public ActivityRecord getById(long id) {
	// for (ActivityRecord record : list) {
	// if (record.getId() == id) {
	// return record;
	// }
	// }
	// return null;
	// }
	//
	// private void add(long id, String no, long workplan, String startTime,
	// String endTime, double longitude, double latitude,
	// String objectives, String notes, String highlights,
	// String nextSteps, String followUpCommitmentDate,
	// long activityType, long workplanEntry, long customer,
	// int firstTimeVisit, int plannedVisit, String createdTime,
	// String modifiedTime, long user, long smr,
	// String issuesIdentified, String feedBackFromCustomer,
	// String ongoingCampaigns, String lastDelivery,
	// String promoStubsDetails, String projectName,
	// String projectCategory, String projectStage, String date,
	// String time, String venue, String noOfAttendees) {
	// list.add(new ActivityRecord(id, no, workplan, startTime, endTime,
	// longitude, latitude, objectives, notes, highlights,
	// nextSteps, followUpCommitmentDate, activityType,
	// workplanEntry, customer, firstTimeVisit, plannedVisit,
	// createdTime, modifiedTime, user, smr, issuesIdentified,
	// feedBackFromCustomer, ongoingCampaigns, lastDelivery,
	// promoStubsDetails, projectName, projectCategory,
	// projectStage, date, time, venue, noOfAttendees));
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
	// private void update(long id, String no, long workplan,
	// String startTime, String endTime, double longitude,
	// double latitude, String objectives, String notes,
	// String highlights, String nextSteps,
	// String followUpCommitmentDate, long activityType,
	// long workplanEntry, long customer, int firstTimeVisit,
	// int plannedVisit, String createdTime, String modifiedTime,
	// long user, long smr, String issuesIdentified,
	// String feedBackFromCustomer, String ongoingCampaigns,
	// String lastDelivery, String promoStubsDetails,
	// String projectName, String projectCategory,
	// String projectStage, String date, String time, String venue,
	// String noOfAttendees) {
	// ActivityRecord record = getById(id);
	// record.setNo(no);
	// record.setStartTime(startTime);
	// record.setEndTime(endTime);
	// record.setLongitude(longitude);
	// record.setLatitude(latitude);
	// record.setObjectives(objectives);
	// record.setNotes(notes);
	// record.setHighlights(highlights);
	// record.setNextSteps(nextSteps);
	// record.setFollowUpCommitmentDate(followUpCommitmentDate);
	// record.setActivityType(activityType);
	// record.setWorkplanEntry(workplanEntry);
	// record.setCustomer(customer);
	// record.setFirstTimeVisit(firstTimeVisit);
	// record.setPlannedVisit(plannedVisit);
	// record.setCreatedTime(createdTime);
	// record.setModifiedTime(modifiedTime);
	// record.setUser(user);
	// record.setSMR(smr);
	// record.setIssuesIdentified(issuesIdentified);
	// record.setFeedbackFromCustomer(feedBackFromCustomer);
	// record.setOngoingCampaigns(ongoingCampaigns);
	// record.setLastDelivery(lastDelivery);
	// record.setPromoStubsDetails(promoStubsDetails);
	// record.setProjectName(projectName);
	// record.setProjectCategory(projectCategory);
	// record.setProjectStage(projectStage);
	// record.setDate(date);
	// record.setTime(time);
	// record.setVenue(venue);
	// record.setNoOfAttendees(noOfAttendees);
	// }
	//
	// @Override
	// public Iterator<ActivityRecord> iterator() {
	// Iterator<ActivityRecord> iter = new Iterator<ActivityRecord>() {
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
	// public ActivityRecord next() {
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
