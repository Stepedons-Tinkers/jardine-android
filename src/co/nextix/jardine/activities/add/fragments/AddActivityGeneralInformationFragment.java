package co.nextix.jardine.activities.add.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

@SuppressLint("ValidFragment")
public class AddActivityGeneralInformationFragment extends Fragment {
	private View rootView = null;
	private ArrayAdapter<ActivityTypeRecord> activityTypeAdapter = null;
	private CircularProgressButton saveBtn = null;

	private Calendar calendar = null;
	private SimpleDateFormat df = null;
	private String formattedDate = null;
	private int day = 0;
	private int month = 0;
	private int year = 0;
	private int flag;

	private Fragment fragment = null;

	private AddActivityFragment addActFrag;

	public AddActivityGeneralInformationFragment(Fragment frag) {
		this.calendar = Calendar.getInstance();
		this.df = new SimpleDateFormat("HH:mm:ss");
		this.day = this.calendar.get(Calendar.DAY_OF_MONTH);
		this.month = this.calendar.get(Calendar.MONTH);
		this.year = this.calendar.get(Calendar.YEAR);
		this.flag = 0;
		this.fragment = frag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.add_activity_gen_info, container, false);

		addActFrag = (AddActivityFragment) fragment;

		List<ActivityTypeRecord> activityTypeList = JardineApp.DB.getActivityType().getAllRecords();

		// Auto populate fields
		BusinessUnitRecord activity = JardineApp.DB.getBusinessUnit().getById(JardineApp.DB.getUser().getCurrentUser().getId());
		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

		// ArrayAdapter for spinners
		this.activityTypeAdapter = new ArrayAdapter<ActivityTypeRecord>(JardineApp.context, R.layout.add_activity_textview,
				activityTypeList);

		// Setting to text field auto populate data
		((EditText) this.rootView.findViewById(R.id.business_unit)).setText(activity != null ? activity.toString() : "");
		((EditText) this.rootView.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);

		((Spinner) this.rootView.findViewById(R.id.activity_type)).setAdapter(activityTypeAdapter);
		((Spinner) this.rootView.findViewById(R.id.activity_type)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String activityTypeName = ((ActivityTypeRecord) parent.getSelectedItem()).getName();

				ArrayList<Integer> indexes = new ArrayList<Integer>();

				switch (activityTypeName) {
				case "Travel":
					indexes.add(2);
					indexes.add(3);
					indexes.add(4);
					indexes.add(5);
					indexes.add(6);
					indexes.add(7);
					indexes.add(8);
					indexes.add(9);
					indexes.add(10);
					indexes.add(11);
					indexes.add(12);
					addActFrag.tabs.setViewPagerForDisable(addActFrag.pager, false, indexes);
					break;

				case "Waiting":
					indexes.add(2);
					indexes.add(3);
					indexes.add(4);
					indexes.add(5);
					indexes.add(6);
					indexes.add(7);
					indexes.add(8);
					indexes.add(9);
					indexes.add(10);
					indexes.add(11);
					indexes.add(12);
					addActFrag.tabs.setViewPagerForDisable(addActFrag.pager, false, indexes);
					break;

				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		((TextView) this.rootView.findViewById(R.id.check_in)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				flag = 0;
				DatePickerDialog pickDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Panel, datePickerListener,
						AddActivityGeneralInformationFragment.this.year, AddActivityGeneralInformationFragment.this.month,
						AddActivityGeneralInformationFragment.this.day);
				pickDialog.show();
			}
		});

		((TextView) this.rootView.findViewById(R.id.check_out)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				flag = 4;
				DatePickerDialog pickDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Panel, datePickerListener,
						AddActivityGeneralInformationFragment.this.year, AddActivityGeneralInformationFragment.this.month,
						AddActivityGeneralInformationFragment.this.day);
				pickDialog.show();
			}
		});

		saveBtn = (CircularProgressButton) rootView.findViewById(R.id.btnWithText1);
		saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (saveBtn.getProgress() == 0) {
					String checkin = ((TextView) rootView.findViewById(R.id.check_in)).getText().toString();
					String checkout = ((TextView) rootView.findViewById(R.id.check_out)).getText().toString();
					String activityType = String.valueOf(((Spinner) rootView.findViewById(R.id.activity_type)).getSelectedItem());
					long createdBy = Long.parseLong(StoreAccount.restore(getActivity()).getString(Account.ROWID));

					BusinessUnitRecord businessUnit = JardineApp.DB.getBusinessUnit().getById(
							JardineApp.DB.getUser().getCurrentUser().getId());

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

					if (activityType != null && !activityType.isEmpty() && checkin != null && !checkin.isEmpty() && checkout != null
							&& !checkout.isEmpty() && pref.getString("reasons_remarks", null) != null
							&& pref.getString("smr", null) != null && pref.getString("details_admin_works", null) != null
							&& pref.getString("objective", null) != null && pref.getString("notes", null) != null
							&& pref.getString("highlights", null) != null && pref.getString("next_steps", null) != null
							&& pref.getString("project_name", null) != null && pref.getString("project_stage", null) != null
							&& pref.getString("project_category", null) != null && pref.getString("venue", null) != null
							&& pref.getString("no_attendees", null) != null && pref.getString("end_user_activity_types", null) != null
							&& pref != null) {

						String reasonsRemarks = pref.getString("reasons_remarks", null);
						long smr = pref.getLong("smr", 0);
						String adminDetails = pref.getString("details_admin_works", null);
						long customer = pref.getLong("customer", 0);
						long area = pref.getLong("area", 0);
						long province = pref.getLong("province", 0);
						long city = pref.getLong("city", 0);
						long workplanEntry = pref.getLong("workplanEntry", 0);
						String objective = pref.getString("objective", null);
						int firstTimeVisit = pref.getInt("first_time_visit", 0);
						int plannedVisit = pref.getInt("plannedVisit", 0);
						String notes = pref.getString("notes", null);
						String highlights = pref.getString("highlights", null);
						String nextSteps = pref.getString("next_steps", null);
						String followUpCommittmentDate = pref.getString("follow_up_commitment_date", null);
						String projectName = pref.getString("projectName", null);
						String projectStage = pref.getString("project_stage", null);
						String projectCategory = pref.getString("project_category", null);
						String venue = pref.getString("venue", null);
						int numberOfAttendees = pref.getInt("numberOfAttendees", 0);
						String endUserActivityTypes = pref.getString("end_user_activity_types", null);

						new InsertTask("0", ((TextView) rootView.findViewById(R.id.crm_no)).getText().toString(),
								((ActivityTypeRecord) ((Spinner) rootView.findViewById(R.id.activity_type)).getSelectedItem()).getId(),
								checkin.concat(df.format(calendar.getTime())), checkout.concat(df.format(calendar.getTime())), businessUnit
										.getId(), createdBy, 123.894882, 10.310235, checkin.concat(df.format(calendar.getTime())), checkout
										.concat(df.format(calendar.getTime())), reasonsRemarks, smr, adminDetails, customer, area,
								province, city, workplanEntry, objective, firstTimeVisit, plannedVisit, notes, highlights, nextSteps,
								followUpCommittmentDate, projectName, projectStage, projectCategory, venue, numberOfAttendees,
								endUserActivityTypes).execute();

					} else {
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_LONG).show();
					}
				} else {
					saveBtn.setProgress(0);
				}
			}
		});

		((CircularProgressButton) this.rootView.findViewById(R.id.btnWithText2)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStackImmediate();
			}
		});

		return rootView;
	}

	protected class InsertTask extends AsyncTask<Void, Void, Boolean> {

		private ValueAnimator widthAnimation = null;

		// String to be populated
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
		private String projectStage = null;
		private String projectCategory = null;
		private String venue = null;

		private int numberOfAttendees = 0;
		private String endUserActivityTypes = null;

		private boolean flag;

		private InsertTask(String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit, long createdBy,
				double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
				String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
				int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
				String projectName, String projectStage, String projectCategory, String venue, int numberOfAttendees,
				String endUserActivityTypes) {

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

				saveActivity(this.no, this.crmNo, this.activityType, this.checkIn, this.checkOut, this.businessUnit, this.createdBy,
						this.longitude, this.latitude, this.createdTime, this.modifiedTime, this.reasonsRemarks, this.smr,
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

	protected DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			AddActivityGeneralInformationFragment.this.year = selectedYear;
			AddActivityGeneralInformationFragment.this.month = selectedMonth;
			AddActivityGeneralInformationFragment.this.day = selectedDay;
			AddActivityGeneralInformationFragment.this.formattedDate = AddActivityGeneralInformationFragment.this.year + "-"
					+ AddActivityGeneralInformationFragment.this.FormatDateAndDay((AddActivityGeneralInformationFragment.this.month + 1))
					+ "-" + AddActivityGeneralInformationFragment.this.FormatDateAndDay(AddActivityGeneralInformationFragment.this.day);

			if (flag == 0) {
				((TextView) rootView.findViewById(R.id.check_in)).setText(AddActivityGeneralInformationFragment.this.formattedDate);
			} else if (flag == 4) {
				((TextView) rootView.findViewById(R.id.check_out)).setText(AddActivityGeneralInformationFragment.this.formattedDate);
			} else {
				((TextView) rootView.findViewById(R.id.follow_up_commitment_date))
						.setText(AddActivityGeneralInformationFragment.this.formattedDate);
			}
		}
	};

	protected String FormatDateAndDay(int digit) {
		String formattedStringDigit = digit < 10 ? "0" + String.valueOf(digit) : String.valueOf(digit);
		return String.valueOf(formattedStringDigit);
	}

	protected void saveActivity(String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit,
			long createdBy, double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
			String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
			int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
			String projectName, String projectStage, String projectCategory, String venue, int numberOfAttendees,
			String endUserActivityTypes) {

		// Insert to the database
		JardineApp.DB.getActivity().insert(no, crmNo, activityType, checkIn, checkOut, businessUnit, createdBy, longitude, latitude,
				createdTime, modifiedTime, reasonsRemarks, smr, adminDetails, customer, area, province, city, workplanEntry, objective,
				firstTimeVisit, plannedVisit, notes, highlights, nextSteps, followUpCommitmentDate, projectName, projectStage,
				projectCategory, venue, numberOfAttendees, endUserActivityTypes);
	}
}
