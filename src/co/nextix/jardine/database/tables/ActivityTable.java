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
import co.nextix.jardine.database.records.ActivityRecord;

public class ActivityTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_ACTIVITY_ROWID = "_id";
	private final String KEY_ACTIVITY_NO = "no";
	private final String KEY_ACTIVITY_CRMNO = "crm_no";
	private final String KEY_ACTIVITY_SMRUSERID = "smr_user_id";
	private final String KEY_ACTIVITY_ACTIVITYTYPE = "activity_type";
	private final String KEY_ACTIVITY_CHECKIN = "check_in";
	private final String KEY_ACTIVITY_CHECKOUT = "check_out";
	private final String KEY_ACTIVITY_LONGITUDE = "longitude";
	private final String KEY_ACTIVITY_LATITUDE = "latitude";
	private final String KEY_ACTIVITY_CREATEDBY = "created_by";
	private final String KEY_ACTIVITY_CREATEDTIME = "created_time";
	private final String KEY_ACTIVITY_MODIFIEDTIME = "modified_time";
	private final String KEY_ACTIVITY_REASONREMARKS = "reason_remarks";
	private final String KEY_ACTIVITY_WORKWITH = "work_with";
	private final String KEY_ACTIVITY_ADMINWORKDETAILS = "admin_work_details";
	private final String KEY_ACTIVITY_CUSTOMER = "customer";
	private final String KEY_ACTIVITY_AREA = "area";
	private final String KEY_ACTIVITY_PROVINCE = "province";
	private final String KEY_ACTIVITY_CITY = "city";
	private final String KEY_ACTIVITY_WORKPLANENTRY = "workplan_entry";
	private final String KEY_ACTIVITY_OBJECTIVEOFACTIVITY = "objective_of_activity";
	private final String KEY_ACTIVITY_FIRSTTIMEVISIT = "first_time_visit";
	private final String KEY_ACTIVITY_PLANNEDVISIT = "planned_visit";
	private final String KEY_ACTIVITY_NOTES = "notes";
	private final String KEY_ACTIVITY_HIGHLIGHTS = "highlights";
	private final String KEY_ACTIVITY_NEXTSTEPS = "next_steps";
	private final String KEY_ACTIVITY_FOLLOWUP = "follow_up_commitment_date";
	private final String KEY_ACTIVITY_CONTACTPERSON = "contact_person";
	private final String KEY_ACTIVITY_JDIPRODUCTSTOCKCHECK = "jdi_product_stock_check";
	private final String KEY_ACTIVITY_PRODUCTSUPPLIER = "product_supplier";
	private final String KEY_ACTIVITY_JDIMERCHCHECK = "jdi_merchandising_check";
	private final String KEY_ACTIVITY_JDICOMPETITORPRODSTOCKCHECK = "jdi_competitor_product_stock_check";
	private final String KEY_ACTIVITY_MARKETINGINTEL = "marketing_intel";
	private final String KEY_ACTIVITY_PROJECTVISITDETAILS = "project_visit_details";
	private final String KEY_ACTIVITY_PROJECTREQUIREMENTS = "project_requirements";
	private final String KEY_ACTIVITY_TRAININGS = "trainings";
	private final String KEY_ACTIVITY_IDENTIFYPRODUCTFOCUS = "identify_product_focus";
	private final String KEY_ACTIVITY_FULLBRANDACTIVATION = "full_brand_activation";
	private final String KEY_ACTIVITY_PHOTOSATTACHMENT = "activity_photos_and_attachments";

	// private final String KEY_ACTIVITY_BUSINESSUNIT = "business_unit";
	// private final String KEY_ACTIVITY_SOURCE = "source";
	// private final String KEY_ACTIVITY_ISSUES = "issues_identified";
	// private final String KEY_ACTIVITY_FEEDBACK = "feedback_from_customer";
	// private final String KEY_ACTIVITY_ONGOINGCAMPAIGNS = "ongoing_campaigns";
	// private final String KEY_ACTIVITY_LASTDELIVERY = "last_delivery";
	// private final String KEY_ACTIVITY_PROMOSTUBS = "promo_stubs_details";
	// private final String KEY_ACTIVITY_PROJECTNAME = "project_name";
	// private final String KEY_ACTIVITY_PROJECTCATEGORY = "project_category";
	// private final String KEY_ACTIVITY_PROJECTSTAGE = "project_stage";
	// private final String KEY_ACTIVITY_DATE = "date";
	// private final String KEY_ACTIVITY_TIME = "time";
	// private final String KEY_ACTIVITY_VENUE = "venue";
	// private final String KEY_ACTIVITY_NOOFATTENDEES = "no_of_attendees";

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
					String smrUserId = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_SMRUSERID));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					String checkIn = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKIN));
					String checkOut = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					String reasonRemarks = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
					String workWith = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_WORKWITH));
					String adminWorkDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PROVINCE));
					long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					String objectives = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVEOFACTIVITY));
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

					long contactPerson = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CONTACTPERSON));
					long jdiProductStockCheck = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_JDIPRODUCTSTOCKCHECK));
					long productSupplier = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PRODUCTSUPPLIER));
					long jdiMerchandisingCheck = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_JDIMERCHCHECK));
					long jdiCompetitorProductStockCheck = c
							.getLong(c
									.getColumnIndex(KEY_ACTIVITY_JDICOMPETITORPRODSTOCKCHECK));
					long marketingIntel = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_MARKETINGINTEL));
					String projectVisitDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTVISITDETAILS));
					String projectRequirements = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTREQUIREMENTS));
					String training = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_TRAININGS));
					String identifyProductFocus = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_IDENTIFYPRODUCTFOCUS));
					String fullBrandActivation = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FULLBRANDACTIVATION));
					String activityPhotosAttachment = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PHOTOSATTACHMENT));

					list.add(new ActivityRecord(id, no, crmNo, smrUserId,
							activityType, checkIn, checkOut, longitude,
							latitude, createdBy, createdTime, modifiedTime,
							reasonRemarks, workWith, adminWorkDetails,
							customer, area, province, city, workplanEntry,
							objectives, firstTimeVisit, plannedVisit, notes,
							highlights, nextSteps, followUpCommitmentDate,
							contactPerson, jdiProductStockCheck,
							productSupplier, jdiMerchandisingCheck,
							jdiCompetitorProductStockCheck, marketingIntel,
							projectVisitDetails, projectRequirements, training,
							identifyProductFocus, fullBrandActivation,
							activityPhotosAttachment));
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
					String smrUserId = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_SMRUSERID));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					String checkIn = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKIN));
					String checkOut = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					String reasonRemarks = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
					String workWith = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_WORKWITH));
					String adminWorkDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PROVINCE));
					long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					String objectives = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVEOFACTIVITY));
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

					long contactPerson = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CONTACTPERSON));
					long jdiProductStockCheck = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_JDIPRODUCTSTOCKCHECK));
					long productSupplier = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PRODUCTSUPPLIER));
					long jdiMerchandisingCheck = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_JDIMERCHCHECK));
					long jdiCompetitorProductStockCheck = c
							.getLong(c
									.getColumnIndex(KEY_ACTIVITY_JDICOMPETITORPRODSTOCKCHECK));
					long marketingIntel = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_MARKETINGINTEL));
					String projectVisitDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTVISITDETAILS));
					String projectRequirements = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTREQUIREMENTS));
					String training = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_TRAININGS));
					String identifyProductFocus = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_IDENTIFYPRODUCTFOCUS));
					String fullBrandActivation = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FULLBRANDACTIVATION));
					String activityPhotosAttachment = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PHOTOSATTACHMENT));

					list.add(new ActivityRecord(id, no, crmNo, smrUserId,
							activityType, checkIn, checkOut, longitude,
							latitude, createdBy, createdTime, modifiedTime,
							reasonRemarks, workWith, adminWorkDetails,
							customer, area, province, city, workplanEntry,
							objectives, firstTimeVisit, plannedVisit, notes,
							highlights, nextSteps, followUpCommitmentDate,
							contactPerson, jdiProductStockCheck,
							productSupplier, jdiMerchandisingCheck,
							jdiCompetitorProductStockCheck, marketingIntel,
							projectVisitDetails, projectRequirements, training,
							identifyProductFocus, fullBrandActivation,
							activityPhotosAttachment));
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
					String smrUserId = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_SMRUSERID));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					String checkIn = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKIN));
					String checkOut = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					long createdBy = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					String reasonRemarks = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
					String workWith = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_WORKWITH));
					String adminWorkDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
					long province = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PROVINCE));
					long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					String objectives = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVEOFACTIVITY));
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

					long contactPerson = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CONTACTPERSON));
					long jdiProductStockCheck = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_JDIPRODUCTSTOCKCHECK));
					long productSupplier = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_PRODUCTSUPPLIER));
					long jdiMerchandisingCheck = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_JDIMERCHCHECK));
					long jdiCompetitorProductStockCheck = c
							.getLong(c
									.getColumnIndex(KEY_ACTIVITY_JDICOMPETITORPRODSTOCKCHECK));
					long marketingIntel = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_MARKETINGINTEL));
					String projectVisitDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTVISITDETAILS));
					String projectRequirements = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTREQUIREMENTS));
					String training = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_TRAININGS));
					String identifyProductFocus = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_IDENTIFYPRODUCTFOCUS));
					String fullBrandActivation = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FULLBRANDACTIVATION));
					String activityPhotosAttachment = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PHOTOSATTACHMENT));

					list.add(new ActivityRecord(id, no, crmNo, smrUserId,
							activityType, checkIn, checkOut, longitude,
							latitude, createdBy, createdTime, modifiedTime,
							reasonRemarks, workWith, adminWorkDetails,
							customer, area, province, city, workplanEntry,
							objectives, firstTimeVisit, plannedVisit, notes,
							highlights, nextSteps, followUpCommitmentDate,
							contactPerson, jdiProductStockCheck,
							productSupplier, jdiMerchandisingCheck,
							jdiCompetitorProductStockCheck, marketingIntel,
							projectVisitDetails, projectRequirements, training,
							identifyProductFocus, fullBrandActivation,
							activityPhotosAttachment));
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
				String smrUserId = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_SMRUSERID));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
				String checkIn = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CHECKIN));
				String checkOut = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
				double latitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LATITUDE));
				double longitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
				String reasonRemarks = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
				String workWith = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_WORKWITH));
				String adminWorkDetails = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
				long customer = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
				long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_PROVINCE));
				long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
				long workplanEntry = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
				String objectives = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_OBJECTIVEOFACTIVITY));
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

				long contactPerson = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CONTACTPERSON));
				long jdiProductStockCheck = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_JDIPRODUCTSTOCKCHECK));
				long productSupplier = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_PRODUCTSUPPLIER));
				long jdiMerchandisingCheck = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_JDIMERCHCHECK));
				long jdiCompetitorProductStockCheck = c
						.getLong(c
								.getColumnIndex(KEY_ACTIVITY_JDICOMPETITORPRODSTOCKCHECK));
				long marketingIntel = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_MARKETINGINTEL));
				String projectVisitDetails = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTVISITDETAILS));
				String projectRequirements = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTREQUIREMENTS));
				String training = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_TRAININGS));
				String identifyProductFocus = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_IDENTIFYPRODUCTFOCUS));
				String fullBrandActivation = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_FULLBRANDACTIVATION));
				String activityPhotosAttachment = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PHOTOSATTACHMENT));

				record = new ActivityRecord(id, no, crmNo, smrUserId,
						activityType, checkIn, checkOut, longitude, latitude,
						createdBy, createdTime, modifiedTime, reasonRemarks,
						workWith, adminWorkDetails, customer, area, province,
						city, workplanEntry, objectives, firstTimeVisit,
						plannedVisit, notes, highlights, nextSteps,
						followUpCommitmentDate, contactPerson,
						jdiProductStockCheck, productSupplier,
						jdiMerchandisingCheck, jdiCompetitorProductStockCheck,
						marketingIntel, projectVisitDetails,
						projectRequirements, training, identifyProductFocus,
						fullBrandActivation, activityPhotosAttachment);
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
				String smrUserId = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_SMRUSERID));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
				String checkIn = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CHECKIN));
				String checkOut = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CHECKOUT));
				double latitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LATITUDE));
				double longitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
				long createdBy = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDBY));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
				String reasonRemarks = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_REASONREMARKS));
				String workWith = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_WORKWITH));
				String adminWorkDetails = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ADMINWORKDETAILS));
				long customer = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
				long area = c.getLong(c.getColumnIndex(KEY_ACTIVITY_AREA));
				long province = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_PROVINCE));
				long city = c.getLong(c.getColumnIndex(KEY_ACTIVITY_CITY));
				long workplanEntry = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
				String objectives = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_OBJECTIVEOFACTIVITY));
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

				long contactPerson = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CONTACTPERSON));
				long jdiProductStockCheck = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_JDIPRODUCTSTOCKCHECK));
				long productSupplier = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_PRODUCTSUPPLIER));
				long jdiMerchandisingCheck = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_JDIMERCHCHECK));
				long jdiCompetitorProductStockCheck = c
						.getLong(c
								.getColumnIndex(KEY_ACTIVITY_JDICOMPETITORPRODSTOCKCHECK));
				long marketingIntel = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_MARKETINGINTEL));
				String projectVisitDetails = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTVISITDETAILS));
				String projectRequirements = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTREQUIREMENTS));
				String training = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_TRAININGS));
				String identifyProductFocus = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_IDENTIFYPRODUCTFOCUS));
				String fullBrandActivation = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_FULLBRANDACTIVATION));
				String activityPhotosAttachment = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PHOTOSATTACHMENT));

				record = new ActivityRecord(id, no, crmNo, smrUserId,
						activityType, checkIn, checkOut, longitude, latitude,
						createdBy, createdTime, modifiedTime, reasonRemarks,
						workWith, adminWorkDetails, customer, area, province,
						city, workplanEntry, objectives, firstTimeVisit,
						plannedVisit, notes, highlights, nextSteps,
						followUpCommitmentDate, contactPerson,
						jdiProductStockCheck, productSupplier,
						jdiMerchandisingCheck, jdiCompetitorProductStockCheck,
						marketingIntel, projectVisitDetails,
						projectRequirements, training, identifyProductFocus,
						fullBrandActivation, activityPhotosAttachment);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insert(String no, String crmNo, String smrUserId,
			long activityType, String checkIn, String checkOut,
			double longitude, double latitude, long createdBy,
			String createdTime, String modifiedTime, String reasonRemarks,
			String workWith, String adminWorkDetails, long customer, long area,
			long province, long city, long workplanEntry,
			String objectiveOfActivity, int firstTimeVisit, int plannedVisit,
			String notes, String highlights, String nextSteps,
			String followUpCommitmentDate, long contactPerson,
			long jdiProductStockCheck, long productSupplier,
			long jdiMerchandising, long jdiCompetitorProductStockCheck,
			long marketingIntel, String projectVisitDetails,
			String projectRequirements, String trainings,
			String identifyProductFocus, String fullBrandActivation,
			String activityPhotosAttachment) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		// ActivityCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_ACTIVITY_NO, no);
		initialValues.put(KEY_ACTIVITY_CRMNO, crmNo);
		initialValues.put(KEY_ACTIVITY_SMRUSERID, smrUserId);
		initialValues.put(KEY_ACTIVITY_ACTIVITYTYPE, activityType);
		initialValues.put(KEY_ACTIVITY_CHECKIN, checkIn);
		initialValues.put(KEY_ACTIVITY_CHECKOUT, checkOut);
		initialValues.put(KEY_ACTIVITY_LONGITUDE, longitude);
		initialValues.put(KEY_ACTIVITY_LATITUDE, latitude);
		initialValues.put(KEY_ACTIVITY_CREATEDBY, createdBy);
		initialValues.put(KEY_ACTIVITY_CREATEDTIME, createdTime);
		initialValues.put(KEY_ACTIVITY_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_ACTIVITY_REASONREMARKS, reasonRemarks);
		initialValues.put(KEY_ACTIVITY_WORKWITH, workWith);
		initialValues.put(KEY_ACTIVITY_ADMINWORKDETAILS, adminWorkDetails);
		initialValues.put(KEY_ACTIVITY_CUSTOMER, customer);
		initialValues.put(KEY_ACTIVITY_AREA, area);
		initialValues.put(KEY_ACTIVITY_PROVINCE, province);
		initialValues.put(KEY_ACTIVITY_CITY, city);
		initialValues.put(KEY_ACTIVITY_WORKPLANENTRY, workplanEntry);
		initialValues
				.put(KEY_ACTIVITY_OBJECTIVEOFACTIVITY, objectiveOfActivity);
		initialValues.put(KEY_ACTIVITY_FIRSTTIMEVISIT, firstTimeVisit);
		initialValues.put(KEY_ACTIVITY_PLANNEDVISIT, plannedVisit);
		initialValues.put(KEY_ACTIVITY_NOTES, notes);
		initialValues.put(KEY_ACTIVITY_HIGHLIGHTS, highlights);
		initialValues.put(KEY_ACTIVITY_NEXTSTEPS, nextSteps);
		initialValues.put(KEY_ACTIVITY_FOLLOWUP, followUpCommitmentDate);
		initialValues.put(KEY_ACTIVITY_CONTACTPERSON, contactPerson);
		initialValues.put(KEY_ACTIVITY_JDIPRODUCTSTOCKCHECK,
				jdiProductStockCheck);
		initialValues.put(KEY_ACTIVITY_PRODUCTSUPPLIER, productSupplier);
		initialValues.put(KEY_ACTIVITY_JDIMERCHCHECK, jdiMerchandising);
		initialValues.put(KEY_ACTIVITY_JDICOMPETITORPRODSTOCKCHECK,
				jdiCompetitorProductStockCheck);
		initialValues.put(KEY_ACTIVITY_MARKETINGINTEL, marketingIntel);
		initialValues
				.put(KEY_ACTIVITY_PROJECTVISITDETAILS, projectVisitDetails);
		initialValues
				.put(KEY_ACTIVITY_PROJECTREQUIREMENTS, projectRequirements);
		initialValues.put(KEY_ACTIVITY_TRAININGS, trainings);
		initialValues.put(KEY_ACTIVITY_IDENTIFYPRODUCTFOCUS,
				identifyProductFocus);
		initialValues
				.put(KEY_ACTIVITY_FULLBRANDACTIVATION, fullBrandActivation);
		initialValues.put(KEY_ACTIVITY_PHOTOSATTACHMENT,
				activityPhotosAttachment);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
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

	public boolean update(long id, String no, String crmNo, String smrUserId,
			long activityType, String checkIn, String checkOut,
			double longitude, double latitude, long createdBy,
			String createdTime, String modifiedTime, String reasonRemarks,
			String workWith, String adminWorkDetails, long customer, long area,
			long province, long city, long workplanEntry,
			String objectiveOfActivity, int firstTimeVisit, int plannedVisit,
			String notes, String highlights, String nextSteps,
			String followUpCommitmentDate, long contactPerson,
			long jdiProductStockCheck, long productSupplier,
			long jdiMerchandising, long jdiCompetitorProductStockCheck,
			long marketingIntel, String projectVisitDetails,
			String projectRequirements, String trainings,
			String identifyProductFocus, String fullBrandActivation,
			String activityPhotosAttachment) {
		ContentValues args = new ContentValues();
		args.put(KEY_ACTIVITY_NO, no);
		args.put(KEY_ACTIVITY_CRMNO, crmNo);
		args.put(KEY_ACTIVITY_SMRUSERID, smrUserId);
		args.put(KEY_ACTIVITY_ACTIVITYTYPE, activityType);
		args.put(KEY_ACTIVITY_CHECKIN, checkIn);
		args.put(KEY_ACTIVITY_CHECKOUT, checkOut);
		args.put(KEY_ACTIVITY_LONGITUDE, longitude);
		args.put(KEY_ACTIVITY_LATITUDE, latitude);
		args.put(KEY_ACTIVITY_CREATEDBY, createdBy);
		args.put(KEY_ACTIVITY_CREATEDTIME, createdTime);
		args.put(KEY_ACTIVITY_MODIFIEDTIME, modifiedTime);
		args.put(KEY_ACTIVITY_REASONREMARKS, reasonRemarks);
		args.put(KEY_ACTIVITY_WORKWITH, workWith);
		args.put(KEY_ACTIVITY_ADMINWORKDETAILS, adminWorkDetails);
		args.put(KEY_ACTIVITY_CUSTOMER, customer);
		args.put(KEY_ACTIVITY_AREA, area);
		args.put(KEY_ACTIVITY_PROVINCE, province);
		args.put(KEY_ACTIVITY_CITY, city);
		args.put(KEY_ACTIVITY_WORKPLANENTRY, workplanEntry);
		args.put(KEY_ACTIVITY_OBJECTIVEOFACTIVITY, objectiveOfActivity);
		args.put(KEY_ACTIVITY_FIRSTTIMEVISIT, firstTimeVisit);
		args.put(KEY_ACTIVITY_PLANNEDVISIT, plannedVisit);
		args.put(KEY_ACTIVITY_NOTES, notes);
		args.put(KEY_ACTIVITY_HIGHLIGHTS, highlights);
		args.put(KEY_ACTIVITY_NEXTSTEPS, nextSteps);
		args.put(KEY_ACTIVITY_FOLLOWUP, followUpCommitmentDate);
		args.put(KEY_ACTIVITY_CONTACTPERSON, contactPerson);
		args.put(KEY_ACTIVITY_JDIPRODUCTSTOCKCHECK, jdiProductStockCheck);
		args.put(KEY_ACTIVITY_PRODUCTSUPPLIER, productSupplier);
		args.put(KEY_ACTIVITY_JDIMERCHCHECK, jdiMerchandising);
		args.put(KEY_ACTIVITY_JDICOMPETITORPRODSTOCKCHECK,
				jdiCompetitorProductStockCheck);
		args.put(KEY_ACTIVITY_MARKETINGINTEL, marketingIntel);
		args.put(KEY_ACTIVITY_PROJECTVISITDETAILS, projectVisitDetails);
		args.put(KEY_ACTIVITY_PROJECTREQUIREMENTS, projectRequirements);
		args.put(KEY_ACTIVITY_TRAININGS, trainings);
		args.put(KEY_ACTIVITY_IDENTIFYPRODUCTFOCUS, identifyProductFocus);
		args.put(KEY_ACTIVITY_FULLBRANDACTIVATION, fullBrandActivation);
		args.put(KEY_ACTIVITY_PHOTOSATTACHMENT, activityPhotosAttachment);

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
