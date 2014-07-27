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
import co.nextix.jardine.database.records.SMRRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
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

		((Spinner) this.rootView.findViewById(R.id.area)).setSelection((int) this.activityRecord.getArea() - 1);
		// ((Spinner)
		// this.rootView.findViewById(R.id.source)).setSelection((int)
		// this.activityRecord.getSource() - 1);
		// ((Spinner)
		// this.rootView.findViewById(R.id.workplan)).setSelection((int)
		// this.activityRecord.getWorkplan() - 1);
		((Spinner) this.rootView.findViewById(R.id.activity_type)).setSelection((int) this.activityRecord.getActivityType() - 1);
		((Spinner) this.rootView.findViewById(R.id.workplan_entry)).setSelection((int) this.activityRecord.getWorkplanEntry() - 1);
		((Spinner) this.rootView.findViewById(R.id.customer)).setSelection((int) this.activityRecord.getCustomer() - 1);
		((Spinner) this.rootView.findViewById(R.id.province)).setSelection((int) this.activityRecord.getProvince() - 1);

		((TextView) this.rootView.findViewById(R.id.crm_no)).setText(this.activityRecord.getCrm());
		
		// ((TextView)
		// this.rootView.findViewById(R.id.start_time)).setText(this.activityRecord.getStartTime());
		// ((TextView)
		// this.rootView.findViewById(R.id.end_time)).setText(this.activityRecord.getEndTime());
		
		((TextView) this.rootView.findViewById(R.id.latitude)).setText(String.valueOf(this.activityRecord.getLatitude()));
		((TextView) this.rootView.findViewById(R.id.longitude)).setText(String.valueOf(this.activityRecord.getLongitude()));
		((TextView) this.rootView.findViewById(R.id.follow_up_commitment_date)).setText(this.activityRecord.getFollowUpCommitmentDate());

		((EditText) this.rootView.findViewById(R.id.reason_remarks)).setText("getRemarks()");
		((EditText) this.rootView.findViewById(R.id.details_admin_works)).setText("getDetailedWorks()");
		// ((EditText)
		// this.rootView.findViewById(R.id.objective)).setText(this.activityRecord.getObjectives());
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

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (workplan != null && !workplan.isEmpty() && startTime != null && !startTime.isEmpty() && endTime != null
							&& !endTime.isEmpty() && objective != null && !objective.isEmpty() && notes != null && !notes.isEmpty()
							&& nextSteps != null && !nextSteps.isEmpty() && activityType != null && !activityType.isEmpty() && pref != null
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

						new UpdateTask(userId, "0", ((TextView) rootView.findViewById(R.id.crm_no)).getText().toString(),
								((ActivityTypeRecord) ((Spinner) rootView.findViewById(R.id.activity_type)).getSelectedItem()).getId(),
								"checkIn", "checkOut", businessUnit.getId(), Long.parseLong(StoreAccount.restore(JardineApp.context)
										.getString(Account.ROWID)), 123.894882, 10.310235, startTime.concat(df.format(calendar.getTime())),
								endTime.concat(df.format(calendar.getTime())), ((EditText) rootView.findViewById(R.id.reason_remarks))
										.getText().toString(), ((SMRRecord) ((Spinner) rootView.findViewById(R.id.smr)).getSelectedItem())
										.getId(), "adminDetails", ((CustomerRecord) ((Spinner) rootView.findViewById(R.id.customer))
										.getSelectedItem()).getId(), ((PicklistRecord) ((Spinner) rootView.findViewById(R.id.area))
										.getSelectedItem()).getId(), ((ProvinceRecord) ((Spinner) rootView.findViewById(R.id.province))
										.getSelectedItem()).getId(), ((CityTownRecord) ((Spinner) rootView.findViewById(R.id.city_town))
										.getSelectedItem()).getId(), ((WorkplanEntryRecord) ((Spinner) rootView
										.findViewById(R.id.workplan_entry)).getSelectedItem()).getId(), ((EditText) rootView
										.findViewById(R.id.objective)).getText().toString(), ((CheckBox) rootView
										.findViewById(R.id.first_time_visit_checkbox)).isChecked() ? 1 : 0, ((CheckBox) rootView
										.findViewById(R.id.planned_visit_checkbox)).isChecked() ? 1 : 0, ((EditText) rootView
										.findViewById(R.id.notes)).getText().toString(),
								((EditText) rootView.findViewById(R.id.highlights)).getText().toString(), nextSteps, ((TextView) rootView
										.findViewById(R.id.follow_up_commitment_date)).getText().toString(), "projectName", 0,
								0, "venue", 0, "endUserActivityTypes").execute();

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

	protected class UpdateTask extends AsyncTask<Void, Void, Boolean> {

		private ValueAnimator widthAnimation = null;

		// String to be populated
		private long id = 0000;
		private String no = null;
		private String crmNo = null;

		private long activityType = 0000;

		private String checkIn = null; 
		private String checkOut = null;

		private long businessUnit = 0000;
		private long createdBy = 000;

		private double longitude = 0.00;
		private double latitude = 0.00;

		private String createdTime = null;
		private String modifiedTime = null;
		private String reasonsRemarks = null;

		private long smr = 0000;
		private String adminDetails = null;

		private long customer = 0000;
		private long area = 0000;
		private long province = 0000;
		private long city = 0000;
		private long workplanEntry = 0000;

		private String objective = null;

		private int firstTimeVisit = 0;
		private int plannedVisit = 0;

		private String notes = null;
		private String highlights = null;
		private String nextSteps = null;
		private String followUpCommitmentDate = null;
		private String projectName = null;
		private long projectStage = 0;
		private long projectCategory = 0;
		private String venue = null;

		private int numberOfAttendees = 0;
		private String endUserActivityTypes = null;

		private boolean flag;

		private UpdateTask(long id, String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit,
				long createdBy, double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks,
				long smr, String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
				int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
				String projectName, long projectStage, long projectCategory, String venue, int numberOfAttendees,
				String endUserActivityTypes) {

			this.id = id;
			this.no = no;
			this.crmNo = crmNo;
			this.activityType = activityType;
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.businessUnit = businessUnit;
			this.createdBy = createdBy;
			this.longitude = longitude;
			this.latitude = latitude;
			this.createdTime = createdTime;
			this.modifiedTime = modifiedTime;
			this.reasonsRemarks = reasonsRemarks;
			this.smr = smr;
			this.adminDetails = adminDetails;
			this.customer = customer;
			this.area = area;
			this.province = province;
			this.city = city;
			this.workplanEntry = workplanEntry;
			this.objective = objective;
			this.firstTimeVisit = firstTimeVisit;
			this.plannedVisit = plannedVisit;
			this.notes = notes;
			this.highlights = highlights;
			this.nextSteps = nextSteps;
			this.followUpCommitmentDate = followUpCommitmentDate;
			this.projectName = projectName;
			this.projectStage = projectStage;
			this.projectCategory = projectCategory;
			this.venue = venue;
			this.numberOfAttendees = numberOfAttendees;
			this.endUserActivityTypes = endUserActivityTypes;
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

				saveActivity(this.id, this.no, this.crmNo, this.activityType, this.checkIn, this.checkOut, this.businessUnit,
						this.createdBy, this.longitude, this.latitude, this.createdTime, this.modifiedTime, this.reasonsRemarks, this.smr,
						this.adminDetails, this.customer, this.area, this.province, this.city, this.workplanEntry, this.objective,
						this.firstTimeVisit, this.plannedVisit, this.notes, this.highlights, this.nextSteps, this.followUpCommitmentDate,
						this.projectName, this.projectStage, this.projectCategory, this.venue, this.numberOfAttendees,
						this.endUserActivityTypes);

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

	protected void saveActivity(long id, String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit,
			long createdBy, double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
			String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
			int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
			String projectName, long projectStage, long projectCategory, String venue, int numberOfAttendees,
			String endUserActivityTypes) {

		// Insert to the database
		JardineApp.DB.getActivity().update(this.userId, no, crmNo, activityType, checkIn, checkOut, businessUnit, createdBy, longitude,
				latitude, createdTime, modifiedTime, reasonsRemarks, smr, adminDetails, customer, area, province, city, workplanEntry,
				objective, firstTimeVisit, plannedVisit, notes, highlights, nextSteps, followUpCommitmentDate, projectName, projectStage,
				projectCategory, venue, numberOfAttendees, endUserActivityTypes);
	}
}
