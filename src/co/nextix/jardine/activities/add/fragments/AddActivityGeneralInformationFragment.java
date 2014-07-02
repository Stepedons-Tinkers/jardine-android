package co.nextix.jardine.activities.add.fragments;

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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class AddActivityGeneralInformationFragment extends Fragment {
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

	private Calendar calendar = null;
	private SimpleDateFormat df = null;

	public AddActivityGeneralInformationFragment() {
		this.calendar = Calendar.getInstance();
		this.df = new SimpleDateFormat("HH:mm:ss");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.add_activity_gen_info, container, false);

		// List to be populated in spinner adapter
		List<PicklistRecord> areaList = JardineApp.DB.getArea().getAllRecords();
		List<ProvinceRecord> provinceList = JardineApp.DB.getProvince().getAllRecords();
		List<CityTownRecord> city_townList = JardineApp.DB.getCityTown().getAllRecords();
		List<PicklistRecord> sourceList = JardineApp.DB.getArea().getAllRecords();
		List<String> workplanList = JardineApp.DB.getWorkplan().getAllWorkplan(JardineApp.DB.getUser().getCurrentUser().getId());

		// Wala pa ni clear
		List<ActivityTypeRecord> activityTypeList = JardineApp.DB.getActivityType().getAllRecords();
		List<WorkplanEntryRecord> workplanEntryList = JardineApp.DB.getWorkplanEntry().getAllRecords();
		List<CustomerRecord> customerList = JardineApp.DB.getCustomer().getAllRecords();

		// Auto populate fields
		BusinessUnitRecord activity = JardineApp.DB.getBusinessUnit().getById(JardineApp.DB.getUser().getCurrentUser().getId());
		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

		// ArrayAdapter for spinners
		this.areaAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, areaList);
		this.provinceAdapter = new ArrayAdapter<ProvinceRecord>(JardineApp.context, R.layout.add_activity_textview, provinceList);
		this.cityTownAdapter = new ArrayAdapter<CityTownRecord>(JardineApp.context, R.layout.add_activity_textview, city_townList);
		this.sourceAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, sourceList);
		this.workplanAdapter = new ArrayAdapter<String>(JardineApp.context, R.layout.add_activity_textview, workplanList);
		this.activityTypeAdapter = new ArrayAdapter<ActivityTypeRecord>(JardineApp.context, R.layout.add_activity_textview,
				activityTypeList);
		this.workplanEntryRecordAdapter = new ArrayAdapter<WorkplanEntryRecord>(JardineApp.context, R.layout.add_activity_textview,
				workplanEntryList);
		this.customerAdapter = new ArrayAdapter<CustomerRecord>(JardineApp.context, R.layout.add_activity_textview, customerList);

		// Setting to text field auto populate data
		((EditText) this.rootView.findViewById(R.id.business_unit)).setText(activity.toString());
		((EditText) this.rootView.findViewById(R.id.assigned_to)).setText(assignedToLname + "," + assignedToFname);

		((Spinner) this.rootView.findViewById(R.id.area)).setAdapter(this.areaAdapter);
		((Spinner) this.rootView.findViewById(R.id.province)).setAdapter(this.provinceAdapter);
		((Spinner) this.rootView.findViewById(R.id.city_town)).setAdapter(this.cityTownAdapter);
		((Spinner) this.rootView.findViewById(R.id.source)).setAdapter(this.sourceAdapter);
		((Spinner) this.rootView.findViewById(R.id.workplan)).setAdapter(workplanAdapter);
		((Spinner) this.rootView.findViewById(R.id.activity_type)).setAdapter(activityTypeAdapter);
		((Spinner) this.rootView.findViewById(R.id.workplan_entry)).setAdapter(workplanEntryRecordAdapter);
		((Spinner) this.rootView.findViewById(R.id.customer)).setAdapter(customerAdapter);

		saveBtn = (CircularProgressButton) rootView.findViewById(R.id.btnWithText1);
		saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (saveBtn.getProgress() == 0) {
					String workplan = ((EditText) rootView.findViewById(R.id.workplan)).getText().toString();
					String startTime = ((TextView) rootView.findViewById(R.id.start_time)).getText().toString();
					String endTime = ((TextView) rootView.findViewById(R.id.end_time)).getText().toString();
					String objective = ((EditText) rootView.findViewById(R.id.objective)).getText().toString();
					String notes = ((EditText) rootView.findViewById(R.id.notes)).getText().toString();
					String nextSteps = ((EditText) rootView.findViewById(R.id.next_steps)).getText().toString();
					String activityType = ((EditText) rootView.findViewById(R.id.activity_type)).getText().toString();
					String businessUnit = ((EditText) rootView.findViewById(R.id.business_unit)).getText().toString();
					String source = String.valueOf(((Spinner) rootView.findViewById(R.id.source)).getSelectedItem());

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (workplan != null && !workplan.isEmpty() && startTime != null && !startTime.isEmpty() && endTime != null
							&& !endTime.isEmpty() && objective != null && !objective.isEmpty() && notes != null && !notes.isEmpty()
							&& nextSteps != null && !nextSteps.isEmpty() && activityType != null && !activityType.isEmpty()
							&& businessUnit != null && !businessUnit.isEmpty() && source != null && !source.isEmpty() && pref != null
							&& pref.getString("smr", null) != null && pref.getString("issues_identified", null) != null
							&& pref.getString("feedback_from_customer", null) != null && pref.getString("ongoing_campaigns", null) != null
							&& pref.getString("last_delivery", null) != null && pref.getString("promo_stubs_details", null) != null
							&& pref.getString("project_name", null) != null && pref.getString("project_stage", null) != null
							&& pref.getString("project_category", null) != null && pref.getString("date", null) != null
							&& pref.getString("time", null) != null && pref.getString("venue", null) != null
							&& pref.getString("no_attendees", null) != null) {

						long smr = Long.parseLong(pref.getString("smr", null));
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

						new InsertTask("0", ((EditText) rootView.findViewById(R.id.crm_no)).getText().toString(), Long.parseLong(workplan),
								startTime.concat(df.format(calendar.getTime())), endTime.concat(df.format(calendar.getTime())), 123.894882,
								10.310235, ((EditText) rootView.findViewById(R.id.objective)).getText().toString(), notes,
								((EditText) rootView.findViewById(R.id.highlights)).getText().toString(), nextSteps, ((EditText) rootView
										.findViewById(R.id.follow_up_commitment_date)).getText().toString(), Long.parseLong(activityType),
								Long.parseLong(((EditText) rootView.findViewById(R.id.workplan_entry)).getText().toString()), Long
										.parseLong(((EditText) rootView.findViewById(R.id.customer)).getText().toString()),
								((CheckBox) rootView.findViewById(R.id.first_time_visit_checkbox)).isChecked() ? 1 : 0,
								((CheckBox) rootView.findViewById(R.id.planned_visit_checkbox)).isChecked() ? 1 : 0, calendar.getTime()
										.toString(), calendar.getTime().toString(), Long.parseLong(StoreAccount.restore(JardineApp.context)
										.getString(Account.ROWID)), smr, issuesIdentified, feedBackFromCustomer, ongoingCampaigns,
								lastDelivery, promoStubsDetails, projectName, projectCategory, projectStage, date, time, venue,
								noOfAttendees).execute();

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

	private class InsertTask extends AsyncTask<Void, Void, Boolean> {

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
		private String businessUnit = null;
		private String createdTime = null;
		private String modifiedTime = null;
		private String source = null;

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
				String venue, String noOfAttendees) {

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
						this.venue, this.noOfAttendees);

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

	private void saveActivity(String no, String crmNo, long workplan, String startTime, String endTime, double longitude, double latitude,
			String objectives, String notes, String highlights, String nextSteps, String followUpCommitmentDate, long activityType,
			long workplanEntry, long customer, int firstTimeVisit, int plannedVisit, String createdTime, String modifiedTime, long user,
			long smr, String issuesIdentified, String feedBackFromCustomer, String ongoingCampaigns, String lastDelivery,
			String promoStubsDetails, String projectName, String projectCategory, String projectStage, String date, String time,
			String venue, String noOfAttendees) {

		// Insert to the database
		JardineApp.DB.getActivity().insert(no, crmNo, workplan, startTime, endTime, longitude, latitude, objectives, notes, highlights,
				nextSteps, followUpCommitmentDate, activityType, workplanEntry, customer, firstTimeVisit, plannedVisit, createdTime,
				modifiedTime, user, smr, issuesIdentified, feedBackFromCustomer, ongoingCampaigns, lastDelivery, promoStubsDetails,
				projectName, projectCategory, projectStage, date, time, venue, noOfAttendees);
	}

}