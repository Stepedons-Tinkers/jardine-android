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
import co.nextix.jardine.database.records.ActivityRecord;

public class ActivityTable {
	// ===========================================================
	// Private static fields
	// ===========================================================

	private final String KEY_ACTIVITY_ROWID = "_id";
	private final String KEY_ACTIVITY_NO = "no";
	private final String KEY_ACTIVITY_WORKPLAN = "workplan";
	private final String KEY_ACTIVITY_STARTTIME = "start_time";
	private final String KEY_ACTIVITY_ENDTIME = "end_time";
	private final String KEY_ACTIVITY_LATITUDE = "latitude";
	private final String KEY_ACTIVITY_LONGITUDE = "longitude";
	private final String KEY_ACTIVITY_OBJECTIVE = "objective";
	private final String KEY_ACTIVITY_NOTES = "notes";
	private final String KEY_ACTIVITY_HIGHLIGHTS = "highlights";
	private final String KEY_ACTIVITY_NEXTSTEPS = "next_steps";
	private final String KEY_ACTIVITY_FOLLOWUP = "follow_up_commitment_date";
	private final String KEY_ACTIVITY_ACTIVITYTYPE = "activity_type";
	private final String KEY_ACTIVITY_WORKPLANENTRY = "workplan_entry";
	private final String KEY_ACTIVITY_CUSTOMER = "customer";
	private final String KEY_ACTIVITY_FIRSTTIMEVISIT = "first_time_visit";
	private final String KEY_ACTIVITY_PLANNEDVISIT = "planned_visit";
	private final String KEY_ACTIVITY_CREATEDTIME = "created_time";
	private final String KEY_ACTIVITY_MODIFIEDTIME = "modified_time";
	private final String KEY_ACTIVITY_USER = "user";
	private final String KEY_ACTIVITY_SMR = "smr";
	private final String KEY_ACTIVITY_ISSUES = "issues_identified";
	private final String KEY_ACTIVITY_FEEDBACK = "feedback_from_customer";
	private final String KEY_ACTIVITY_ONGOINGCAMPAIGNS = "ongoing_campaigns";
	private final String KEY_ACTIVITY_LASTDELIVERY = "last_delivery";
	private final String KEY_ACTIVITY_PROMOSTUBS = "promo_stubs_details";
	private final String KEY_ACTIVITY_PROJECTNAME = "project_name";
	private final String KEY_ACTIVITY_PROJECTCATEGORY = "project_category";
	private final String KEY_ACTIVITY_PROJECTSTAGE = "project_stage";

	// ===========================================================
	// Private fields
	// ===========================================================

	private ActivityCollection activityCollection;
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

	private List<ActivityRecord> getAllRecords() {
		Cursor c = null;
		List<ActivityRecord> list = new ArrayList<ActivityRecord>();
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable;
		try {
			c = mDb.rawQuery(MY_QUERY, null);
			if (c.moveToFirst()) {
				do {
					long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
					String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
					long workplan = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLAN));
					String startTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_STARTTIME));
					String endTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ENDTIME));
					double longitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
					double latitude = c.getDouble(c
							.getColumnIndex(KEY_ACTIVITY_LATITUDE));
					String objectives = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_OBJECTIVE));
					String notes = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NOTES));
					String highlights = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
					String nextSteps = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
					String followUpCommitmentDate = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
					long activityType = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
					long workplanEntry = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
					long customer = c.getLong(c
							.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
					int firstTimeVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
					int plannedVisit = c.getInt(c
							.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
					String createdTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
					String modifiedTime = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
					long user = c.getLong(c.getColumnIndex(KEY_ACTIVITY_USER));
					long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
					String issuesIdentified = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ISSUES));
					String feedBackFromCustomer = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_FEEDBACK));
					String ongoingCampaigns = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_ONGOINGCAMPAIGNS));
					String lastDelivery = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_LASTDELIVERY));
					String promoStubsDetails = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROMOSTUBS));
					String projectName = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
					String projectCategory = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
					String projectStage = c.getString(c
							.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));

					list.add(new ActivityRecord(id, no, workplan, startTime,
							endTime, longitude, latitude, objectives, notes,
							highlights, nextSteps, followUpCommitmentDate,
							activityType, workplanEntry, customer,
							firstTimeVisit, plannedVisit, createdTime,
							modifiedTime, user, smr, issuesIdentified,
							feedBackFromCustomer, ongoingCampaigns,
							lastDelivery, promoStubsDetails, projectName,
							projectCategory, projectStage));
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

	public ActivityRecord getById(int ID) {
		ActivityRecord record = null;
		String MY_QUERY = "SELECT * FROM " + mDatabaseTable + " WHERE "
				+ KEY_ACTIVITY_ROWID + "=?";
		Cursor c = null;
		try {
			c = mDb.rawQuery(MY_QUERY, new String[] { String.valueOf(ID) });

			if ((c != null) && c.moveToFirst()) {
				long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ROWID));
				String no = c.getString(c.getColumnIndex(KEY_ACTIVITY_NO));
				long workplan = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_WORKPLAN));
				String startTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_STARTTIME));
				String endTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ENDTIME));
				double longitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
				double latitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LATITUDE));
				String objectives = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_OBJECTIVE));
				String notes = c
						.getString(c.getColumnIndex(KEY_ACTIVITY_NOTES));
				String highlights = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
				String nextSteps = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
				String followUpCommitmentDate = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
				long workplanEntry = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
				long customer = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
				int firstTimeVisit = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
				int plannedVisit = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_ACTIVITY_USER));
				long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
				String issuesIdentified = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ISSUES));
				String feedBackFromCustomer = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_FEEDBACK));
				String ongoingCampaigns = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ONGOINGCAMPAIGNS));
				String lastDelivery = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_LASTDELIVERY));
				String promoStubsDetails = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROMOSTUBS));
				String projectName = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
				String projectCategory = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
				String projectStage = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));

				record = new ActivityRecord(id, no, workplan, startTime,
						endTime, longitude, latitude, objectives, notes,
						highlights, nextSteps, followUpCommitmentDate,
						activityType, workplanEntry, customer, firstTimeVisit,
						plannedVisit, createdTime, modifiedTime, user, smr,
						issuesIdentified, feedBackFromCustomer,
						ongoingCampaigns, lastDelivery, promoStubsDetails,
						projectName, projectCategory, projectStage);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
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
				long workplan = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_WORKPLAN));
				String startTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_STARTTIME));
				String endTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ENDTIME));
				double longitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LONGITUDE));
				double latitude = c.getDouble(c
						.getColumnIndex(KEY_ACTIVITY_LATITUDE));
				String objectives = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_OBJECTIVE));
				String notes = c
						.getString(c.getColumnIndex(KEY_ACTIVITY_NOTES));
				String highlights = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_HIGHLIGHTS));
				String nextSteps = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_NEXTSTEPS));
				String followUpCommitmentDate = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_FOLLOWUP));
				long activityType = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_ACTIVITYTYPE));
				long workplanEntry = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_WORKPLANENTRY));
				long customer = c.getLong(c
						.getColumnIndex(KEY_ACTIVITY_CUSTOMER));
				int firstTimeVisit = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_FIRSTTIMEVISIT));
				int plannedVisit = c.getInt(c
						.getColumnIndex(KEY_ACTIVITY_PLANNEDVISIT));
				String createdTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_CREATEDTIME));
				String modifiedTime = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_MODIFIEDTIME));
				long user = c.getLong(c.getColumnIndex(KEY_ACTIVITY_USER));
				long smr = c.getLong(c.getColumnIndex(KEY_ACTIVITY_SMR));
				String issuesIdentified = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ISSUES));
				String feedBackFromCustomer = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_FEEDBACK));
				String ongoingCampaigns = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_ONGOINGCAMPAIGNS));
				String lastDelivery = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_LASTDELIVERY));
				String promoStubsDetails = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROMOSTUBS));
				String projectName = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTNAME));
				String projectCategory = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTCATEGORY));
				String projectStage = c.getString(c
						.getColumnIndex(KEY_ACTIVITY_PROJECTSTAGE));

				record = new ActivityRecord(id, no, workplan, startTime,
						endTime, longitude, latitude, objectives, notes,
						highlights, nextSteps, followUpCommitmentDate,
						activityType, workplanEntry, customer, firstTimeVisit,
						plannedVisit, createdTime, modifiedTime, user, smr,
						issuesIdentified, feedBackFromCustomer,
						ongoingCampaigns, lastDelivery, promoStubsDetails,
						projectName, projectCategory, projectStage);
			}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return record;
	}

	public long insertUser(String no, long workplan, String startTime,
			String endTime, double longitude, double latitude,
			String objectives, String notes, String highlights,
			String nextSteps, String followUpCommitmentDate, long activityType,
			long workplanEntry, long customer, int firstTimeVisit,
			int plannedVisit, String createdTime, String modifiedTime,
			long user, long smr, String issuesIdentified,
			String feedBackFromCustomer, String ongoingCampaigns,
			String lastDelivery, String promoStubsDetails, String projectName,
			String projectCategory, String projectStage) {
		// if (name == null) {
		// throw new NullPointerException("name");
		// }
		ActivityCollection collection = getRecords();

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_ACTIVITY_NO, no);
		initialValues.put(KEY_ACTIVITY_STARTTIME, startTime);
		initialValues.put(KEY_ACTIVITY_ENDTIME, endTime);
		initialValues.put(KEY_ACTIVITY_LONGITUDE, longitude);
		initialValues.put(KEY_ACTIVITY_LATITUDE, latitude);
		initialValues.put(KEY_ACTIVITY_OBJECTIVE, objectives);
		initialValues.put(KEY_ACTIVITY_NOTES, notes);
		initialValues.put(KEY_ACTIVITY_HIGHLIGHTS, highlights);
		initialValues.put(KEY_ACTIVITY_NEXTSTEPS, nextSteps);
		initialValues.put(KEY_ACTIVITY_FOLLOWUP, followUpCommitmentDate);
		initialValues.put(KEY_ACTIVITY_ACTIVITYTYPE, activityType);
		initialValues.put(KEY_ACTIVITY_WORKPLANENTRY, workplanEntry);
		initialValues.put(KEY_ACTIVITY_CUSTOMER, customer);
		initialValues.put(KEY_ACTIVITY_FIRSTTIMEVISIT, firstTimeVisit);
		initialValues.put(KEY_ACTIVITY_PLANNEDVISIT, plannedVisit);
		initialValues.put(KEY_ACTIVITY_CREATEDTIME, createdTime);
		initialValues.put(KEY_ACTIVITY_MODIFIEDTIME, modifiedTime);
		initialValues.put(KEY_ACTIVITY_USER, user);
		initialValues.put(KEY_ACTIVITY_SMR, smr);
		initialValues.put(KEY_ACTIVITY_ISSUES, issuesIdentified);
		initialValues.put(KEY_ACTIVITY_FEEDBACK, feedBackFromCustomer);
		initialValues.put(KEY_ACTIVITY_ONGOINGCAMPAIGNS, ongoingCampaigns);
		initialValues.put(KEY_ACTIVITY_LASTDELIVERY, lastDelivery);
		initialValues.put(KEY_ACTIVITY_PROMOSTUBS, promoStubsDetails);
		initialValues.put(KEY_ACTIVITY_PROJECTNAME, projectName);
		initialValues.put(KEY_ACTIVITY_PROJECTCATEGORY, projectCategory);
		initialValues.put(KEY_ACTIVITY_PROJECTSTAGE, projectStage);

		long ids = mDb.insert(mDatabaseTable, null, initialValues);
		if (ids >= 0) {
			collection.add(ids, no, workplan, startTime, endTime, longitude,
					latitude, objectives, notes, highlights, nextSteps,
					followUpCommitmentDate, activityType, workplanEntry,
					customer, firstTimeVisit, plannedVisit, createdTime,
					modifiedTime, user, smr, issuesIdentified,
					feedBackFromCustomer, ongoingCampaigns, lastDelivery,
					promoStubsDetails, projectName, projectCategory,
					projectStage);
			Log.i("WEB", "DB insert " + no);
		} else {
			throw new SQLException("insert failed");
		}
		return ids;
	}

	public boolean deleteUser(long rowId) {
		if (mDb.delete(mDatabaseTable, KEY_ACTIVITY_ROWID + "=" + rowId, null) > 0) {
			getRecords().deleteById(rowId);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(long id, String no, long workplan,
			String startTime, String endTime, double longitude,
			double latitude, String objectives, String notes,
			String highlights, String nextSteps, String followUpCommitmentDate,
			long activityType, long workplanEntry, long customer,
			int firstTimeVisit, int plannedVisit, String createdTime,
			String modifiedTime, long user, long smr, String issuesIdentified,
			String feedBackFromCustomer, String ongoingCampaigns,
			String lastDelivery, String promoStubsDetails, String projectName,
			String projectCategory, String projectStage) {
		ContentValues args = new ContentValues();
		args.put(KEY_ACTIVITY_NO, no);
		args.put(KEY_ACTIVITY_WORKPLAN, workplan);
		args.put(KEY_ACTIVITY_STARTTIME, startTime);
		args.put(KEY_ACTIVITY_ENDTIME, endTime);
		args.put(KEY_ACTIVITY_LONGITUDE, longitude);
		args.put(KEY_ACTIVITY_LATITUDE, latitude);
		args.put(KEY_ACTIVITY_OBJECTIVE, objectives);
		args.put(KEY_ACTIVITY_NOTES, notes);
		args.put(KEY_ACTIVITY_HIGHLIGHTS, highlights);
		args.put(KEY_ACTIVITY_NEXTSTEPS, nextSteps);
		args.put(KEY_ACTIVITY_FOLLOWUP, followUpCommitmentDate);
		args.put(KEY_ACTIVITY_ACTIVITYTYPE, activityType);
		args.put(KEY_ACTIVITY_WORKPLANENTRY, workplanEntry);
		args.put(KEY_ACTIVITY_CUSTOMER, customer);
		args.put(KEY_ACTIVITY_FIRSTTIMEVISIT, firstTimeVisit);
		args.put(KEY_ACTIVITY_PLANNEDVISIT, plannedVisit);
		args.put(KEY_ACTIVITY_CREATEDTIME, createdTime);
		args.put(KEY_ACTIVITY_MODIFIEDTIME, modifiedTime);
		args.put(KEY_ACTIVITY_USER, user);
		args.put(KEY_ACTIVITY_SMR, smr);
		args.put(KEY_ACTIVITY_ISSUES, issuesIdentified);
		args.put(KEY_ACTIVITY_FEEDBACK, feedBackFromCustomer);
		args.put(KEY_ACTIVITY_ONGOINGCAMPAIGNS, ongoingCampaigns);
		args.put(KEY_ACTIVITY_LASTDELIVERY, lastDelivery);
		args.put(KEY_ACTIVITY_PROMOSTUBS, promoStubsDetails);
		args.put(KEY_ACTIVITY_PROJECTNAME, projectName);
		args.put(KEY_ACTIVITY_PROJECTCATEGORY, projectCategory);
		args.put(KEY_ACTIVITY_PROJECTSTAGE, projectStage);
		if (mDb.update(mDatabaseTable, args, KEY_ACTIVITY_ROWID + "=" + id,
				null) > 0) {
			getRecords().update(id, no, workplan, startTime, endTime,
					longitude, latitude, objectives, notes, highlights,
					nextSteps, followUpCommitmentDate, activityType,
					workplanEntry, customer, firstTimeVisit, plannedVisit,
					createdTime, modifiedTime, user, smr, issuesIdentified,
					feedBackFromCustomer, ongoingCampaigns, lastDelivery,
					promoStubsDetails, projectName, projectCategory,
					projectStage);
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

	public ActivityCollection getRecords() {
		if (activityCollection == null) {
			activityCollection = new ActivityCollection();
			activityCollection.list = getAllRecords();
		}
		return activityCollection;
	}

	public final class ActivityCollection implements Iterable<ActivityRecord> {

		private List<ActivityRecord> list;

		private ActivityCollection() {
		}

		public int size() {
			return list.size();
		}

		public ActivityRecord get(int i) {
			return list.get(i);
		}

		public ActivityRecord getById(long id) {
			for (ActivityRecord record : list) {
				if (record.getId() == id) {
					return record;
				}
			}
			return null;
		}

		private void add(long id, String no, long workplan, String startTime,
				String endTime, double longitude, double latitude,
				String objectives, String notes, String highlights,
				String nextSteps, String followUpCommitmentDate,
				long activityType, long workplanEntry, long customer,
				int firstTimeVisit, int plannedVisit, String createdTime,
				String modifiedTime, long user, long smr,
				String issuesIdentified, String feedBackFromCustomer,
				String ongoingCampaigns, String lastDelivery,
				String promoStubsDetails, String projectName,
				String projectCategory, String projectStage) {
			list.add(new ActivityRecord(id, no, workplan, startTime, endTime,
					longitude, latitude, objectives, notes, highlights,
					nextSteps, followUpCommitmentDate, activityType,
					workplanEntry, customer, firstTimeVisit, plannedVisit,
					createdTime, modifiedTime, user, smr, issuesIdentified,
					feedBackFromCustomer, ongoingCampaigns, lastDelivery,
					promoStubsDetails, projectName, projectCategory,
					projectStage));
		}

		private void clear() {
			list.clear();
		}

		private void deleteById(long id) {
			list.remove(getById(id));
		}

		private void update(long id, String no, long workplan,
				String startTime, String endTime, double longitude,
				double latitude, String objectives, String notes,
				String highlights, String nextSteps,
				String followUpCommitmentDate, long activityType,
				long workplanEntry, long customer, int firstTimeVisit,
				int plannedVisit, String createdTime, String modifiedTime,
				long user, long smr, String issuesIdentified,
				String feedBackFromCustomer, String ongoingCampaigns,
				String lastDelivery, String promoStubsDetails,
				String projectName, String projectCategory, String projectStage) {
			ActivityRecord record = getById(id);
			record.setNo(no);
			record.setStartTime(startTime);
			record.setEndTime(endTime);
			record.setLongitude(longitude);
			record.setLatitude(latitude);
			record.setObjectives(objectives);
			record.setNotes(notes);
			record.setHighlights(highlights);
			record.setNextSteps(nextSteps);
			record.setFollowUpCommitmentDate(followUpCommitmentDate);
			record.setActivityType(activityType);
			record.setWorkplanEntry(workplanEntry);
			record.setCustomer(customer);
			record.setFirstTimeVisit(firstTimeVisit);
			record.setPlannedVisit(plannedVisit);
			record.setCreatedTime(createdTime);
			record.setModifiedTime(modifiedTime);
			record.setUser(user);
			record.setSMR(smr);
			record.setIssuesIdentified(issuesIdentified);
			record.setFeedbackFromCustomer(feedBackFromCustomer);
			record.setOngoingCampaigns(ongoingCampaigns);
			record.setLastDelivery(lastDelivery);
			record.setPromoStubsDetails(promoStubsDetails);
			record.setProjectName(projectName);
			record.setProjectCategory(projectCategory);
			record.setProjectStage(projectStage);
		}

		@Override
		public Iterator<ActivityRecord> iterator() {
			Iterator<ActivityRecord> iter = new Iterator<ActivityRecord>() {
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
				public ActivityRecord next() {
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
