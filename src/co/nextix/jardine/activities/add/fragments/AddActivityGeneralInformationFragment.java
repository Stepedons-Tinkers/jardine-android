package co.nextix.jardine.activities.add.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

@SuppressLint("ValidFragment")
public class AddActivityGeneralInformationFragment extends Fragment {
	private View rootView = null;
	private ArrayAdapter<ActivityTypeRecord> activityTypeAdapter = null;
	private ArrayAdapter<BusinessUnitRecord> activityBusinessAdapter = null;
	private CircularProgressButton saveBtn = null;

	private Calendar calendar = null;
	private SimpleDateFormat df = null;
	private String formattedDate = null;
	private int day = 0;
	private int month = 0;
	private int year = 0;
	private int flag = 0;

	private boolean trapping = false;

	public static int ActivityType = 0;

	public AddActivityGeneralInformationFragment() {
		this.calendar = Calendar.getInstance();
		this.df = new SimpleDateFormat("HH:mm:ss");
		this.day = this.calendar.get(Calendar.DAY_OF_MONTH);
		this.month = this.calendar.get(Calendar.MONTH);
		this.year = this.calendar.get(Calendar.YEAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.add_activity_gen_info, container, false);
		List<ActivityTypeRecord> activityTypeList = JardineApp.DB.getActivityType().getAllRecords();
		List<BusinessUnitRecord> businessUnitList = JardineApp.DB.getBusinessUnit().getAllRecords();

		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		long id = pref.getLong("activity_id_edit", 0);

		DashBoardActivity.fromAddActivities = true;
		if (DashBoardActivity.tabIndex.size() == 0) {
			DashBoardActivity.tabIndex.add(0, 0);
		}

		if (JardineApp.DB.getActivity().getById(id) != null) {

			String crmNo = JardineApp.DB.getActivity().getById(id).getCrm();
			String checkIn = JardineApp.DB.getActivity().getById(id).getCheckIn();
			String checkOut = JardineApp.DB.getActivity().getById(id).getCheckOut();
			long businessUnit = JardineApp.DB.getActivity().getById(id).getBusinessUnit();
			long activityType = JardineApp.DB.getActivity().getById(id).getActivityType();
			long createdBy = JardineApp.DB.getActivity().getById(id).getCreatedBy();

			if (crmNo != null && checkIn != null && businessUnit != 0 && activityType != 0 && checkOut != null && createdBy != 0) {

				Log.e("condition", "true");
				String lastName = JardineApp.DB.getUser().getById(createdBy).getLastname();
				String firstName = JardineApp.DB.getUser().getById(createdBy).getFirstNameName();

				((TextView) this.rootView.findViewById(R.id.crm_no)).setText(crmNo);
				((TextView) this.rootView.findViewById(R.id.check_in)).setText(checkIn);
				((TextView) this.rootView.findViewById(R.id.check_out)).setText(checkOut);
				((EditText) this.rootView.findViewById(R.id.created_by)).setText("" + lastName + ", " + firstName);

				for (int i = 0; i < activityTypeList.size(); i++) {
					if (JardineApp.DB.getActivity().getById(id).getActivityType() == activityTypeList.get(i).getId()) {
						((Spinner) this.rootView.findViewById(R.id.activity_type)).setSelection(i);
						break;
					}
				}

				for (int i = 0; i < businessUnitList.size(); i++) {
					if (JardineApp.DB.getActivity().getById(id).getBusinessUnit() == businessUnitList.get(i).getId()) {
						((Spinner) this.rootView.findViewById(R.id.business_unit)).setSelection(i);
						break;
					}
				}
			}

		} else {

			Log.e("condition", "false");

			// Auto populate fields

			String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
			String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

			// ArrayAdapter for spinners
			this.activityTypeAdapter = new ArrayAdapter<ActivityTypeRecord>(JardineApp.context, R.layout.add_activity_textview,
					activityTypeList);

			this.activityBusinessAdapter = new ArrayAdapter<BusinessUnitRecord>(JardineApp.context, R.layout.add_activity_textview,
					businessUnitList);

			// Setting to text field auto populate data
			((Spinner) this.rootView.findViewById(R.id.business_unit)).setAdapter(this.activityBusinessAdapter);
			((Spinner) this.rootView.findViewById(R.id.activity_type)).setAdapter(activityTypeAdapter);
			((EditText) this.rootView.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);
		}

		((Spinner) this.rootView.findViewById(R.id.activity_type)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String activityTypeName = ((ActivityTypeRecord) parent.getSelectedItem()).getName();
				ArrayList<Integer> indexes = new ArrayList<Integer>();

				if (activityTypeName.equals("Travel") || activityTypeName.equals("Waiting")) { // done
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
					indexes.add(13);
					indexes.add(14);
					indexes.add(15);
					indexes.add(16);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 1;

				} else if (activityTypeName.equals("Company Work-with Co-SMR/ Supervisor")) { // done
					indexes.add(1);
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
					indexes.add(13);
					indexes.add(14);
					indexes.add(15);
					indexes.add(16);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 43;

				} else if (activityTypeName.equals("Admin Work")) { // done
					indexes.add(1);
					indexes.add(2);
					indexes.add(4);
					indexes.add(5);
					indexes.add(6);
					indexes.add(7);
					indexes.add(8);
					indexes.add(9);
					indexes.add(10);
					indexes.add(11);
					indexes.add(12);
					indexes.add(13);
					indexes.add(14);
					indexes.add(15);
					indexes.add(16);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 47;

				} else if (activityTypeName.equals("Retail Visits (Traditional Hardware)")
						|| activityTypeName.equals("Retail Visits (Merienda)")) { // done
					indexes.add(1);
					indexes.add(2);
					indexes.add(3);
					indexes.add(11);
					indexes.add(12);
					indexes.add(13);
					indexes.add(14);
					indexes.add(15);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 4;

				} else if (activityTypeName.equals("KI Visits - On-site")) { // done
					indexes.add(1);
					indexes.add(2);
					indexes.add(3);
					indexes.add(6);
					indexes.add(7);
					indexes.add(8);
					indexes.add(9);
					indexes.add(13);
					indexes.add(14);
					indexes.add(15);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 9;

				} else if (activityTypeName.contains("Major Training")) { // done
					indexes.add(1);
					indexes.add(2);
					indexes.add(3);
					indexes.add(6);
					indexes.add(7);
					indexes.add(8);
					indexes.add(9);
					indexes.add(10);
					indexes.add(11);
					indexes.add(12);
					indexes.add(14);
					indexes.add(15);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 101;

				} else if (activityTypeName.contains("End User Activity")) { // done
					indexes.add(1);
					indexes.add(2);
					indexes.add(3);
					indexes.add(6);
					indexes.add(7);
					indexes.add(8);
					indexes.add(9);
					indexes.add(10);
					indexes.add(11);
					indexes.add(12);
					indexes.add(13);
					indexes.add(15);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 102;

				} else if (activityTypeName.equals("Full Brand Activation")) { // done
					indexes.add(1);
					indexes.add(2);
					indexes.add(3);
					indexes.add(6);
					indexes.add(7);
					indexes.add(8);
					indexes.add(9);
					indexes.add(10);
					indexes.add(11);
					indexes.add(12);
					indexes.add(13);
					indexes.add(14);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 41;

				} else { // done
					indexes.add(1);
					indexes.add(2);
					indexes.add(3);
					indexes.add(6);
					indexes.add(7);
					indexes.add(8);
					indexes.add(9);
					indexes.add(10);
					indexes.add(11);
					indexes.add(12);
					indexes.add(13);
					indexes.add(14);
					indexes.add(15);
					AddActivityFragment.tabs.setViewPagerForDisable(AddActivityFragment.pager, false, indexes);
					ActivityType = 100;

				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		((TextView) this.rootView.findViewById(R.id.check_in)).setText(this.displayCheckIn());
		((TextView) this.rootView.findViewById(R.id.check_in)).setClickable(false);
		((TextView) this.rootView.findViewById(R.id.check_in)).setFocusable(false);
		((ImageButton) this.rootView.findViewById(R.id.ibChechOutCalendar)).setOnClickListener(new OnClickListener() {

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
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (saveBtn.getProgress() == 0) {
					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							Integer value = (Integer) animation.getAnimatedValue();
							saveBtn.setProgress(value);

							if (!trapping) {
								saveBtn.setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					String crmno = ((TextView) rootView.findViewById(R.id.crm_no)).getText().toString();
					String checkin = ((TextView) rootView.findViewById(R.id.check_in)).getText().toString();
					String checkout = ((TextView) rootView.findViewById(R.id.check_out)).getText().toString();
					long activityType = ((ActivityTypeRecord) ((Spinner) rootView.findViewById(R.id.activity_type)).getSelectedItem())
							.getId();
					long createdBy = Long.parseLong(StoreAccount.restore(getActivity()).getString(Account.ROWID));

					BusinessUnitRecord businessUnit = JardineApp.DB.getBusinessUnit().getById(
							JardineApp.DB.getUser().getCurrentUser().getId());

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (activityType != 0 && checkin != null && !checkin.isEmpty() && checkout != null && !checkout.isEmpty()) {

						trapping = true;
						Editor editor = pref.edit();
						editor.putString("crm_no", crmno);
						editor.putString("check_in", checkin);
						editor.putString("check_out", checkout);
						editor.putLong("activity_type", activityType);
						editor.putLong("createdBy", createdBy);
						editor.putLong("business_unit", businessUnit.getId());
						editor.commit(); // commit changes

						// Set the button click to true
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								v.setClickable(true);
								v.setEnabled(true);
							}

						}, 700);

					} else {

						trapping = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_LONG).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								saveBtn.setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);

					if (ActivityType == 1) { // travel
						DashBoardActivity.tabIndex.add(1, 1);
						AddActivityFragment.pager.setCurrentItem(1);
					} else if (ActivityType == 43) { // coSMRs
						DashBoardActivity.tabIndex.add(1, 2);
						AddActivityFragment.pager.setCurrentItem(2);
					} else if (ActivityType == 47) { // admin works
						DashBoardActivity.tabIndex.add(1, 3);
						AddActivityFragment.pager.setCurrentItem(3);
					} else if (ActivityType == 4) { // retails
						DashBoardActivity.tabIndex.add(1, 4);
						AddActivityFragment.pager.setCurrentItem(4);
					} else if (ActivityType == 9) { // ki visits
						DashBoardActivity.tabIndex.add(1, 4);
						AddActivityFragment.pager.setCurrentItem(4);
					} else if (ActivityType == 101) { // major training
						DashBoardActivity.tabIndex.add(1, 4);
						AddActivityFragment.pager.setCurrentItem(4);
					} else if (ActivityType == 102) { // end user
						DashBoardActivity.tabIndex.add(1, 4);
						AddActivityFragment.pager.setCurrentItem(4);
					} else if (ActivityType == 41) { // full brand
						DashBoardActivity.tabIndex.add(1, 4);
						AddActivityFragment.pager.setCurrentItem(4);
					} else if (ActivityType == 100) { // others
						DashBoardActivity.tabIndex.add(1, 4);
						AddActivityFragment.pager.setCurrentItem(4);
					}
				}
			}
		});

		((CircularProgressButton) this.rootView.findViewById(R.id.btnWithText2)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStackImmediate("general_information",
						FragmentManager.POP_BACK_STACK_INCLUSIVE);

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
		private long projectStage = 0;
		private long projectCategory = 0;
		private String venue = null;

		private int numberOfAttendees = 0;
		private String endUserActivityTypes = null;

		private boolean flag;

		private InsertTask(String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit, long createdBy,
				double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
				String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
				int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
				String projectName, long projectStage, long projectCategory, String venue, int numberOfAttendees,
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

			if (flag == 4) {
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

	protected String displayCheckIn() {
		AddActivityGeneralInformationFragment.this.formattedDate = AddActivityGeneralInformationFragment.this.year + "-"
				+ AddActivityGeneralInformationFragment.this.FormatDateAndDay((AddActivityGeneralInformationFragment.this.month + 1)) + "-"
				+ AddActivityGeneralInformationFragment.this.FormatDateAndDay(AddActivityGeneralInformationFragment.this.day);

		return this.formattedDate.concat(" " + df.format(calendar.getTime()));
	}

	protected void saveActivity(String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit,
			long createdBy, double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
			String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
			int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
			String projectName, long projectStage, long projectCategory, String venue, int numberOfAttendees, String endUserActivityTypes) {

		// Insert to the database
		JardineApp.DB.getActivity().insert(no, crmNo, activityType, checkIn, checkOut, businessUnit, createdBy, longitude, latitude,
				createdTime, modifiedTime, reasonsRemarks, smr, adminDetails, customer, area, province, city, workplanEntry, objective,
				firstTimeVisit, plannedVisit, notes, highlights, nextSteps, followUpCommitmentDate, projectName, projectStage,
				projectCategory, venue, numberOfAttendees, endUserActivityTypes);
	}
}
