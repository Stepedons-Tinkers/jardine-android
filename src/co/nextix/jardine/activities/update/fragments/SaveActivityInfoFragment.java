package co.nextix.jardine.activities.update.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.ActivitiesConstant;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.tables.picklists.PAreaTable;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class SaveActivityInfoFragment extends Fragment {
	private ActivityRecord activityRecord = null;
	private View rootView = null;
	private ArrayAdapter<String> workplanAdapter = null;
	private ArrayAdapter<PicklistRecord> areaAdapter = null;
	private ArrayAdapter<CustomerRecord> customerAdapter = null;
	private ArrayAdapter<ProvinceRecord> provinceAdapter = null;
	private ArrayAdapter<CityTownRecord> cityTownAdapter = null;
	private ArrayAdapter<PicklistRecord> sourceAdapter = null;
	private ArrayAdapter<ActivityTypeRecord> activityTypeAdapter = null;
	private ArrayAdapter<WorkplanEntryRecord> workplanEntryRecordAdapter = null;
	private CircularProgressButton saveBtn = null;
	private String userName = null;
	private long userId = 0;
	private Calendar calendar = null;
	private SimpleDateFormat df = null;

	public SaveActivityInfoFragment() {
		String id = StoreAccount.restore(JardineApp.context).getString(Account.ROWID);
		this.calendar = Calendar.getInstance();
		this.df = new SimpleDateFormat("HH:mm:ss");
		this.userName = StoreAccount.restore(JardineApp.context).getString(Account.USERNAME);
		this.userId = Long.parseLong(id);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_activity_edit, container, false);
		this.activityRecord = ActivitiesConstant.ACTIVITY_RECORD;

		// List to be populated in spinner adapter
		List<PicklistRecord> areaList = JardineApp.DB.getArea().getAllRecords();
		List<PicklistRecord> sourceList = JardineApp.DB.getArea().getAllRecords();
		List<String> workplanList = JardineApp.DB.getWorkplan().getAllWorkplan(JardineApp.DB.getUser().getCurrentUser().getId());

		// Karon clear na
		List<ActivityTypeRecord> activityTypeList = JardineApp.DB.getActivityType().getAllRecords();
		List<WorkplanEntryRecord> workplanEntryList = JardineApp.DB.getWorkplanEntry().getAllRecords();
		List<CustomerRecord> customerList = JardineApp.DB.getCustomer().getAllRecords();

		// Auto populate fields
		BusinessUnitRecord activity = JardineApp.DB.getBusinessUnit().getById(activityRecord.getId());
		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

		// ArrayAdapter for spinners
		this.areaAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, areaList);
		this.sourceAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, sourceList);
		this.workplanAdapter = new ArrayAdapter<String>(JardineApp.context, R.layout.add_activity_textview, workplanList);
		this.activityTypeAdapter = new ArrayAdapter<ActivityTypeRecord>(JardineApp.context, R.layout.add_activity_textview,
				activityTypeList);
		this.workplanEntryRecordAdapter = new ArrayAdapter<WorkplanEntryRecord>(JardineApp.context, R.layout.add_activity_textview,
				workplanEntryList);
		this.customerAdapter = new ArrayAdapter<CustomerRecord>(JardineApp.context, R.layout.add_activity_textview, customerList);

		// Setting to text field auto populate data
		((EditText) this.rootView.findViewById(R.id.business_unit)).setText(activity != null ? activity.toString() : "");
		((EditText) this.rootView.findViewById(R.id.assigned_to)).setText(assignedToLname + "," + assignedToFname);

		((Spinner) this.rootView.findViewById(R.id.area)).setAdapter(this.areaAdapter);
		((Spinner) this.rootView.findViewById(R.id.source)).setAdapter(this.sourceAdapter);
		((Spinner) this.rootView.findViewById(R.id.workplan)).setAdapter(workplanAdapter);
		((Spinner) this.rootView.findViewById(R.id.activity_type)).setAdapter(activityTypeAdapter);
		((Spinner) this.rootView.findViewById(R.id.workplan_entry)).setAdapter(workplanEntryRecordAdapter);
		((Spinner) this.rootView.findViewById(R.id.customer)).setAdapter(customerAdapter);

		((Spinner) this.rootView.findViewById(R.id.area)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				PProvinceTable provinceTable = JardineApp.DB.getProvince();
				provinceAdapter = new ArrayAdapter<ProvinceRecord>(JardineApp.context, R.layout.add_activity_textview, provinceTable
						.getRecordsByAreaId(id + 1));
				((Spinner) SaveActivityInfoFragment.this.rootView.findViewById(R.id.province)).setAdapter(provinceAdapter);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getActivity().getApplicationContext(), "Must be filled!", Toast.LENGTH_SHORT).show();
			}
		});

		((Spinner) this.rootView.findViewById(R.id.province)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				PCityTownTable cityTable = JardineApp.DB.getCityTown();
				cityTownAdapter = new ArrayAdapter<CityTownRecord>(JardineApp.context, R.layout.add_activity_textview, cityTable
						.getRecordsByProvinceId(id + 1));
				((Spinner) SaveActivityInfoFragment.this.rootView.findViewById(R.id.city_town)).setAdapter(cityTownAdapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getActivity().getApplicationContext(), "Must be filled!", Toast.LENGTH_SHORT).show();
			}
		});

		((Spinner) this.rootView.findViewById(R.id.area)).setSelection((int) this.activityRecord.getArea());
		((Spinner) this.rootView.findViewById(R.id.source)).setSelection((int) this.activityRecord.getSource() - 1);
		((Spinner) this.rootView.findViewById(R.id.workplan)).setSelection((int) this.activityRecord.getWorkplan() - 1);
		((Spinner) this.rootView.findViewById(R.id.activity_type)).setSelection((int) this.activityRecord.getActivityType() - 1);
		((Spinner) this.rootView.findViewById(R.id.workplan_entry)).setSelection((int) this.activityRecord.getWorkplanEntry());
		((Spinner) this.rootView.findViewById(R.id.customer)).setSelection((int) this.activityRecord.getCustomer());
		((Spinner) this.rootView.findViewById(R.id.province)).setSelection((int) this.activityRecord.getProvince());

		((TextView) this.rootView.findViewById(R.id.crm_no)).setText(this.activityRecord.getCrm());
		((TextView) this.rootView.findViewById(R.id.start_time)).setText(this.activityRecord.getStartTime());
		((TextView) this.rootView.findViewById(R.id.end_time)).setText(this.activityRecord.getEndTime());
		((TextView) this.rootView.findViewById(R.id.latitude)).setText(String.valueOf(this.activityRecord.getLatitude()));
		((TextView) this.rootView.findViewById(R.id.longitude)).setText(String.valueOf(this.activityRecord.getLongitude()));
		((TextView) this.rootView.findViewById(R.id.follow_up_commitment_date)).setText(this.activityRecord.getFollowUpCommitmentDate());

		((EditText) this.rootView.findViewById(R.id.reason_remarks)).setText("getRemarks()");
		((EditText) this.rootView.findViewById(R.id.details_admin_works)).setText("getDetailedWorks()");
		((EditText) this.rootView.findViewById(R.id.objective)).setText(this.activityRecord.getObjectives());
		((EditText) this.rootView.findViewById(R.id.notes)).setText(this.activityRecord.getNotes());
		((EditText) this.rootView.findViewById(R.id.competitors_activities)).setText("getCompetitorActivities()");
		((EditText) this.rootView.findViewById(R.id.highlights)).setText(this.activityRecord.getHighlights());
		((EditText) this.rootView.findViewById(R.id.next_steps)).setText(this.activityRecord.getNextSteps());
		((EditText) this.rootView.findViewById(R.id.others_activity_type_remarks)).setText("getOthers()");

		((CheckBox) this.rootView.findViewById(R.id.first_time_visit_checkbox))
				.setChecked(this.activityRecord.getFirstTimeVisit() == 1 ? true : false);
		((CheckBox) this.rootView.findViewById(R.id.planned_visit_checkbox)).setChecked(this.activityRecord.getPlannedVisit() == 1 ? true
				: false);

		this.saveBtn = (CircularProgressButton) this.rootView.findViewById(R.id.btnWithText1);
		this.saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (saveBtn.getProgress() == 0) {
					String workplan = String.valueOf(((Spinner) rootView.findViewById(R.id.workplan)).getSelectedItem());
					String startTime = ((TextView) rootView.findViewById(R.id.start_time)).getText().toString();
					String endTime = ((TextView) rootView.findViewById(R.id.end_time)).getText().toString();
					String objective = ((EditText) rootView.findViewById(R.id.objective)).getText().toString();
					String notes = ((EditText) rootView.findViewById(R.id.notes)).getText().toString();
					String nextSteps = ((EditText) rootView.findViewById(R.id.next_steps)).getText().toString();
					String activityType = String.valueOf(((Spinner) rootView.findViewById(R.id.activity_type)).getSelectedItem());

					BusinessUnitRecord businessUnit = JardineApp.DB.getBusinessUnit().getById(
							JardineApp.DB.getUser().getCurrentUser().getId());

					long area = ((PicklistRecord) ((Spinner) rootView.findViewById(R.id.area)).getSelectedItem()).getId();
					long province = ((ProvinceRecord) ((Spinner) rootView.findViewById(R.id.province)).getSelectedItem()).getId();
					long cityTown = ((CityTownRecord) ((Spinner) rootView.findViewById(R.id.city_town)).getSelectedItem()).getId();
					long source = ((PicklistRecord) ((Spinner) rootView.findViewById(R.id.source)).getSelectedItem()).getId();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (workplan != null && !workplan.isEmpty() && startTime != null && !startTime.isEmpty() && endTime != null
							&& !endTime.isEmpty() && objective != null && !objective.isEmpty() && notes != null && !notes.isEmpty()
							&& nextSteps != null && !nextSteps.isEmpty() && activityType != null && !activityType.isEmpty() && source != 0
							&& pref != null
					/*
					 * && pref.getString("smr", null) != null &&
					 * pref.getString("issues_identified", null) != null &&
					 * pref.getString("feedback_from_customer", null) != null &&
					 * pref.getString("ongoing_campaigns", null) != null &&
					 * pref.getString("last_delivery", null) != null &&
					 * pref.getString("promo_stubs_details", null) != null &&
					 * pref.getString("project_name", null) != null &&
					 * pref.getString("project_stage", null) != null &&
					 * pref.getString("project_category", null) != null &&
					 * pref.getString("date", null) != null &&
					 * pref.getString("time", null) != null &&
					 * pref.getString("venue", null) != null &&
					 * pref.getString("no_attendees", null) != null
					 */) {

						long smr = (long) 0.0004;// Long.parseLong(pref.getString("smr",
													// null));
						String issuesIdentified = pref.getString("issues_identified", null);
						String feedBackFromCustomer = pref.getString("feedback_from_customer", null);
						String ongoingCampaigns = pref.getString("ongoing_campaigns", null);
						String lastDelivery = pref.getString("last_delivery", null);
						String promoStubsDetails = pref.getString("promo_stubs_details", null);
						String projectName = pref.getString("project_name", null);
						String projectCategory = pref.getString("project_category", null);
						String projectStage = pref.getString("project_stage", null);
						String date = pref.getString("date", null);
						String time = pref.getString("time", null);
						String venue = pref.getString("venue", null);
						String noOfAttendees = pref.getString("no_attendees", null);

						new InsertTask("0", ((TextView) rootView.findViewById(R.id.crm_no)).getText().toString(), Long.parseLong(workplan),
								startTime.concat(df.format(calendar.getTime())), endTime.concat(df.format(calendar.getTime())), 123.894882,
								10.310235, ((EditText) rootView.findViewById(R.id.objective)).getText().toString(), notes,
								((EditText) rootView.findViewById(R.id.highlights)).getText().toString(), nextSteps, ((TextView) rootView
										.findViewById(R.id.follow_up_commitment_date)).getText().toString(), Long.parseLong(StoreAccount
										.restore(JardineApp.context).getString(Account.ROWID)), Long.parseLong(StoreAccount.restore(
										JardineApp.context).getString(Account.ROWID)), Long.parseLong(StoreAccount.restore(
										JardineApp.context).getString(Account.ROWID)), ((CheckBox) rootView
										.findViewById(R.id.first_time_visit_checkbox)).isChecked() ? 1 : 0, ((CheckBox) rootView
										.findViewById(R.id.planned_visit_checkbox)).isChecked() ? 1 : 0, calendar.getTime().toString(),
								calendar.getTime().toString(), Long.parseLong(StoreAccount.restore(JardineApp.context).getString(
										Account.ROWID)), smr, issuesIdentified, feedBackFromCustomer, ongoingCampaigns, lastDelivery,
								promoStubsDetails, projectName, projectCategory, projectStage, date, time, venue, noOfAttendees,
								businessUnit.getId(), area, province, cityTown, source).execute();

					} else {
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_LONG).show();
					}

				} else {
					saveBtn.setProgress(0);
				}
			}
		});

		return rootView;
	}

	protected class InsertTask extends AsyncTask<Void, Void, Boolean> {

		private ValueAnimator widthAnimation = null;

		// String to be populated
		private String no = null;
		private String crmNo = null;
		private String highlights = null;
		private String followUpCommitmentDate = null;
		private String startTime = null;
		private String endTime = null;
		private String objective = null;
		private String notes = null;
		private String nextSteps = null;
		private String createdTime = null;
		private String modifiedTime = null;

		private int firstTimeVisit = 0;
		private int plannedVisit = 0;
		private double longitude = 0000;
		private double latitude = 0000;
		private long activityType = 0000;
		private long workplan = 0000;
		private long workplanEntry = 0000;
		private long customer = 0000;
		private long user = 0000;
		private long smr = 0000;
		private long businessUnit = 0000;
		private long area = 0000;
		private long province = 0000;
		private long cityTown = 0000;
		private long source = 0000;

		private String issuesIdentified = null;
		private String feedBackFromCustomer = null;
		private String ongoingCampaigns = null;
		private String lastDelivery = null;
		private String promoStubsDetails = null;
		private String projectName = null;
		private String projectCategory = null;
		private String projectStage = null;
		private String date = null;
		private String time = null;
		private String venue = null;
		private String noOfAttendees = null;

		private boolean flag;

		private InsertTask(String no, String crmNo, long workplan, String startTime, String endTime, double longitude, double latitude,
				String objectives, String notes, String highlights, String nextSteps, String followUpCommitmentDate, long activityType,
				long workplanEntry, long customer, int firstTimeVisit, int plannedVisit, String createdTime, String modifiedTime,
				long user, long smr, String issuesIdentified, String feedBackFromCustomer, String ongoingCampaigns, String lastDelivery,
				String promoStubsDetails, String projectName, String projectCategory, String projectStage, String date, String time,
				String venue, String noOfAttendees, long businessUnit, long area, long province, long cityTown, long source) {

			this.no = no;
			this.crmNo = crmNo;
			this.workplan = workplan;
			this.startTime = startTime;
			this.endTime = endTime;
			this.longitude = longitude;
			this.latitude = latitude;
			this.objective = objectives;
			this.notes = notes;
			this.highlights = highlights;
			this.nextSteps = nextSteps;
			this.followUpCommitmentDate = followUpCommitmentDate;
			this.activityType = activityType;
			this.workplanEntry = workplanEntry;
			this.customer = customer;
			this.firstTimeVisit = firstTimeVisit;
			this.plannedVisit = plannedVisit;
			this.createdTime = createdTime;
			this.modifiedTime = modifiedTime;
			this.user = user;
			this.promoStubsDetails = promoStubsDetails;
			this.projectName = projectName;
			this.projectCategory = projectCategory;
			this.projectStage = projectStage;
			this.date = date;
			this.time = time;
			this.venue = venue;
			this.noOfAttendees = noOfAttendees;
			this.businessUnit = businessUnit;
			this.area = area;
			this.province = province;
			this.cityTown = cityTown;
			this.source = source;
		}

		@Override
		protected void onPreExecute() {
			// Animate Button
			this.widthAnimation = ValueAnimator.ofInt(1, 100);
			this.widthAnimation.setDuration(1500);
			this.widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
			this.widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					Integer value = (Integer) animation.getAnimatedValue();
					saveBtn.setProgress(value);

					if (!flag) {
						saveBtn.setProgress(-1);
					}
				}
			});

			this.widthAnimation.start();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			this.flag = false;
			try {

				saveActivity(this.no, this.crmNo, this.workplan, this.startTime, this.endTime, this.longitude, this.latitude,
						this.objective, this.notes, this.highlights, this.nextSteps, this.followUpCommitmentDate, this.activityType,
						this.workplanEntry, this.customer, this.firstTimeVisit, this.plannedVisit, this.createdTime, this.modifiedTime,
						this.user, this.smr, this.issuesIdentified, this.feedBackFromCustomer, this.ongoingCampaigns, this.lastDelivery,
						this.promoStubsDetails, this.projectName, this.projectCategory, this.projectStage, this.date, this.time,
						this.venue, this.noOfAttendees, this.businessUnit, this.area, this.province, this.cityTown, this.source);

				this.flag = true;
			} catch (Exception e) {
				this.flag = false;
				Log.e("Jardine", e.toString());
			}

			return this.flag;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				saveBtn.setProgress(100);
			} else {
				this.flag = false;
			}
		}
	}

	protected void saveActivity(String no, String crmNo, long workplan, String startTime, String endTime, double longitude,
			double latitude, String objectives, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
			long activityType, long workplanEntry, long customer, int firstTimeVisit, int plannedVisit, String createdTime,
			String modifiedTime, long user, long smr, String issuesIdentified, String feedBackFromCustomer, String ongoingCampaigns,
			String lastDelivery, String promoStubsDetails, String projectName, String projectCategory, String projectStage, String date,
			String time, String venue, String noOfAttendees, long businessUnit, long area, long province, long cityTown, long source) {

		// Insert to the database
		JardineApp.DB.getActivity().update(this.userId, no, crmNo, workplan, startTime, endTime, longitude, latitude, objectives, notes,
				highlights, nextSteps, followUpCommitmentDate, activityType, workplanEntry, customer, firstTimeVisit, plannedVisit,
				createdTime, modifiedTime, user, smr, issuesIdentified, feedBackFromCustomer, ongoingCampaigns, lastDelivery,
				promoStubsDetails, projectName, projectCategory, projectStage, date, time, venue, noOfAttendees, businessUnit, area,
				province, cityTown, source);
	}
}
