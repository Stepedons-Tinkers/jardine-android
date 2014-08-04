package co.nextix.jardine.activities.add.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.keys.Constant;

import com.dd.CircularProgressButton;

public class AddActivityDetailsAndNotesFragment extends Fragment {

	private View rootView = null;
	private ArrayAdapter<CustomerRecord> customerAdapter = null;
	private ArrayAdapter<PicklistRecord> areaAdapter = null;
	private ArrayAdapter<ProvinceRecord> provinceAdapter = null;
	private ArrayAdapter<CityTownRecord> cityTownAdapter = null;
	private ArrayAdapter<WorkplanEntryRecord> workplanEntryAdapter = null;

	private boolean trapping = false;

	private Calendar calendar = null;

	private String formattedDate = null;
	private int day = 0;
	private int month = 0;
	private int year = 0;

	private Spinner sCustomer;
	private Spinner sArea;
	private Spinner sWorkplanEntry;
	private Spinner sProvince;
	private Spinner sCityTown;
	
	private ImageButton ibFollowUpCalendar;
	
	private CircularProgressButton cpbSave;
	
	private EditText etObjective;
	private EditText etHighlights;
	private EditText etNotes;
	private EditText etNextSteps;
	
	private TextView tvFollowUp;
	
	private CheckBox cbFirstTimeVisit;
	private CheckBox cbPlannedTimeVisit;
	
	private long provinceNo;
	private long cityNo;

	public AddActivityDetailsAndNotesFragment() {
		this.calendar = Calendar.getInstance();
		this.day = this.calendar.get(Calendar.DAY_OF_MONTH);
		this.month = this.calendar.get(Calendar.MONTH);
		this.year = this.calendar.get(Calendar.YEAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		rootView = inflater.inflate(
				R.layout.add_activity_activity_details_and_notes, container,
				false);
		
		List<CustomerRecord> customer = JardineApp.DB.getCustomer()
				.getAllRecords();
		List<PicklistRecord> area = JardineApp.DB.getArea().getAllRecords();
		List<WorkplanEntryRecord> workplanEntry = JardineApp.DB
				.getWorkplanEntry().getAllRecords();

		customerAdapter = new ArrayAdapter<CustomerRecord>(getActivity()
				.getApplicationContext(), R.layout.add_activity_textview,
				customer);
		areaAdapter = new ArrayAdapter<PicklistRecord>(getActivity()
				.getApplicationContext(), R.layout.add_activity_textview, area);
		workplanEntryAdapter = new ArrayAdapter<WorkplanEntryRecord>(
				getActivity().getApplicationContext(),
				R.layout.add_activity_textview, workplanEntry);
		
		cbFirstTimeVisit = (CheckBox) rootView.findViewById(R.id.first_time_visit_checkbox);
		cbPlannedTimeVisit = (CheckBox) rootView.findViewById(R.id.planned_visit_checkbox);
		
		etObjective = (EditText) rootView.findViewById(R.id.objective);
		etHighlights = (EditText) rootView.findViewById(R.id.highlights);
		etNotes = (EditText) rootView.findViewById(R.id.notes);
		etNextSteps = (EditText) rootView.findViewById(R.id.next_steps);
		
		tvFollowUp = (TextView) rootView.findViewById(R.id.follow_up_commitment_date);
		
		sCustomer = (Spinner) rootView.findViewById(R.id.customer);
		sCustomer.setAdapter(this.customerAdapter);
		
		sWorkplanEntry = (Spinner) rootView.findViewById(R.id.workplan_entry);
		sWorkplanEntry.setAdapter(workplanEntryAdapter);
		
		sArea = (Spinner) rootView.findViewById(R.id.area);		
		sArea.setAdapter(areaAdapter);
		
		sProvince = (Spinner) rootView.findViewById(R.id.province);
		
		ibFollowUpCalendar = (ImageButton)rootView.findViewById(R.id.ibFollowUpCommitmentCalendar);
		
		cpbSave = (CircularProgressButton) rootView.findViewById(R.id.btnWithText1);
		
		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		if (AddActivityFragment.activityID != 0) {	
			long customerNo = pref.getLong("activity_id_customer", 0);
			long areaNo = pref.getLong("activitiy_id_area", 0);
			provinceNo = pref.getLong("activity_id_province", 0);
			cityNo = pref.getLong("activity_id_city", 0);
			String objective = pref.getString("activity_id_objective", null);
			String notes = pref.getString("activity_id_notes", null);
			String highlights = pref.getString("activity_id_highlights", null);
			String nextSteps = pref.getString("activity_id_next_steps", null);
			String followUp = pref.getString("activity_id_followup_committment_date", null);
			int firstTime = pref.getInt("activity_id_first_time_visit", 0);
			int planned = pref.getInt("activity_id_planned_visit", 0);
			
			sCustomer.setSelection(Integer.parseInt(String.valueOf(customerNo-1))-1);
			sArea.setSelection(Integer.parseInt(String.valueOf(areaNo))-1);
			
			etObjective.setText(objective);
			etNotes.setText(notes);
			etHighlights.setText(highlights);
			etNextSteps.setText(nextSteps);
			tvFollowUp.setText(followUp);
			
			if(firstTime == 0){
				cbFirstTimeVisit.setChecked(true);
			}
			
			if(planned == 0){
				cbPlannedTimeVisit.setChecked(true);
			}
		}

		sCustomer.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				CustomerRecord cRecord = (CustomerRecord) parent.getAdapter()
						.getItem(position);
				ActivitiesConstant.ACTIVITY_CUSTOMER_RECORD = cRecord;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		sArea.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						PProvinceTable provinceTable = JardineApp.DB
								.getProvince();
						provinceAdapter = new ArrayAdapter<ProvinceRecord>(
								JardineApp.context,
								R.layout.add_activity_textview, provinceTable
										.getRecordsByAreaId(id + 1));

						sProvince = (Spinner) rootView.findViewById(R.id.province);
						sProvince.setAdapter(provinceAdapter);
						sProvince.setSelection(Integer.parseInt(String.valueOf(provinceNo))-1);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						Toast.makeText(getActivity().getApplicationContext(),
								"Must be filled!", Toast.LENGTH_SHORT).show();
					}
				});

		sProvince.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						PCityTownTable cityTable = JardineApp.DB.getCityTown();
						cityTownAdapter = new ArrayAdapter<CityTownRecord>(
								JardineApp.context,
								R.layout.add_activity_textview, cityTable
										.getRecordsByProvinceId(id + 1));

						sCityTown = (Spinner) rootView.findViewById(R.id.city_town);
						sCityTown.setAdapter(cityTownAdapter);
						sCityTown.setSelection(Integer.parseInt(String.valueOf(cityNo))-1);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						Toast.makeText(getActivity().getApplicationContext(),
								"Must be filled!", Toast.LENGTH_SHORT).show();
					}
				});

		ibFollowUpCalendar.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						DatePickerDialog pickDialog = new DatePickerDialog(
								getActivity(),
								android.R.style.Theme_Holo_Panel,
								datePickerListener, year, month, day);
						pickDialog.show();

					}
				});

		cpbSave.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(final View v) {
						v.setClickable(false);
						v.setClickable(false);

						if (cpbSave.getProgress() == 0) {

							ValueAnimator widthAnimation = ValueAnimator.ofInt(
									1, 100);
							widthAnimation.setDuration(500);
							widthAnimation
									.setInterpolator(new AccelerateDecelerateInterpolator());
							widthAnimation
									.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
										@Override
										public void onAnimationUpdate(
												ValueAnimator animation) {
											Integer value = (Integer) animation
													.getAnimatedValue();
											cpbSave.setProgress(value);

											if (!trapping) {
												cpbSave.setProgress(-1);
											}
										}
									});

							widthAnimation.start();
							long customer = 0;
							long customerType = 0;
							long area = 0;
							long province = 0;
							long cityTown = 0;
							String objective = null;
							long workplanEntry = 0;
							int firstTimeVisit = 0;
							int plannedTimeVisit = 0;

							String highlights = null;
							String notes = null;
							String nextSteps = null;
							String followUpCommittmentDate = null;

							try {

								customer = ((CustomerRecord) sCustomer.getSelectedItem()).getId();
								customerType = ((CustomerRecord) sCustomer.getSelectedItem()).getCustomerType();
								area = ((PicklistRecord) sArea.getSelectedItem()).getId();
								province = ((ProvinceRecord) sProvince.getSelectedItem()).getId();
								cityTown = ((CityTownRecord) sCityTown.getSelectedItem()).getId();
								objective = etObjective.getText().toString();
								workplanEntry = ((WorkplanEntryRecord) sWorkplanEntry.getSelectedItem()).getId();
								firstTimeVisit = cbFirstTimeVisit.isChecked() ? 1 : 0;
								plannedTimeVisit = cbPlannedTimeVisit.isChecked() ? 1 : 0;
								highlights = etHighlights.getText().toString();
								notes = etNotes.getText().toString();
								nextSteps = etNextSteps.getText().toString();
								followUpCommittmentDate = tvFollowUp.getText().toString();

								Constant.activityGeneralInfo
										.setCustomer(customer);
								Constant.activityGeneralInfo.setArea(area);
								Constant.activityGeneralInfo
										.setProvince(province);
								Constant.activityGeneralInfo.setCity(cityTown);
								Constant.activityGeneralInfo
										.setObjective(objective);
								Constant.activityGeneralInfo
										.setWorkplanEntry(workplanEntry);
								Constant.activityGeneralInfo
										.setFirstTimeVisit(firstTimeVisit);
								Constant.activityGeneralInfo
										.setPlannedVisit(plannedTimeVisit);
								Constant.activityGeneralInfo
										.setHighlights(highlights);
								Constant.activityGeneralInfo.setNotes(notes);
								Constant.activityGeneralInfo
										.setNextSteps(nextSteps);
								Constant.activityGeneralInfo
										.setFollowUpCommitmentDate(followUpCommittmentDate);

							} catch (Exception e) {

							}

							/** Checking of required fields **/
							SharedPreferences pref = getActivity()
									.getApplicationContext()
									.getSharedPreferences("ActivityInfo", 0);
							if (objective != null && !objective.isEmpty()
									&& notes != null && !notes.isEmpty()
									&& highlights != null
									&& !highlights.isEmpty()
									&& nextSteps != null
									&& !nextSteps.isEmpty()
									&& !((PicklistRecord) sArea.getSelectedItem()).toString().equals("- Select -")
									&& !((ProvinceRecord) sProvince.getSelectedItem()).toString().equals("- Select -")
									&& !((CityTownRecord) sCityTown.getSelectedItem()).toString().equals("- Select -")){
								
									etObjective.setError(null);
									etHighlights.setError(null);
									etNotes.setError(null);
									etNextSteps.setError(null);

								trapping = true;
								Editor editor = pref.edit();
								editor.putLong("customerlong", customer);
								editor.putLong("customer_type", customerType);
								editor.putLong("area", area);
								editor.putLong("province", province);
								editor.putLong("city_town", cityTown);
								editor.putLong("workplan_entry", workplanEntry);
								editor.putInt("first_time_visit",
										firstTimeVisit);
								editor.putInt("planned_time_visit",
										plannedTimeVisit);
								editor.putString("objective", objective);
								editor.putString("highlights", highlights);
								editor.putString("notes", notes);
								editor.putString("next_steps", nextSteps);
								editor.putString("follow_up_committment_date",
										followUpCommittmentDate);

								editor.commit(); // commit changes

								Handler handler = new Handler();
								handler.postDelayed(new Runnable() {

									@Override
									public void run() {
										v.setClickable(true);
										v.setEnabled(true);
									}

								}, 1500);

							} else {
								if(objective == null || objective.isEmpty())
									etObjective.setError("Please provide an objective.");
								if( highlights == null || highlights.isEmpty())
									etHighlights.setError("Please provide some highlights.");
								if(notes == null || notes.isEmpty())
									etNotes.setError("Please provide some notes.");
								if(nextSteps == null || nextSteps.isEmpty())
									etNextSteps.setError("Please provide next step.");
								
								trapping = false;
								Toast.makeText(
										getActivity(),
										"Please fill up required fields",
										Toast.LENGTH_LONG).show();

								Handler handler = new Handler();
								handler.postDelayed(new Runnable() {

									@Override
									public void run() {
										cpbSave.setProgress(0);
										v.setClickable(true);
										v.setClickable(true);
									}
								}, 1500);
							}

						} else {
							cpbSave.setProgress(0);

							if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retails
								AddActivityFragment.fromOther = true;
								DashBoardActivity.tabIndex.add(2, 5);
								AddActivityFragment.pager.setCurrentItem(5);
							} else if (AddActivityGeneralInformationFragment.ActivityType == 9) { // kivisits
								AddActivityFragment.fromOther = true;
								DashBoardActivity.tabIndex.add(2, 5);
								AddActivityFragment.pager.setCurrentItem(5);
							} else if (AddActivityGeneralInformationFragment.ActivityType == 101) { // major
																									// //
																									// training
								AddActivityFragment.fromOther = true;
								DashBoardActivity.tabIndex.add(2, 5);
								AddActivityFragment.pager.setCurrentItem(5);
							} else if (AddActivityGeneralInformationFragment.ActivityType == 102) { // end
																									// user
								AddActivityFragment.fromOther = true;
								DashBoardActivity.tabIndex.add(2, 5);
								AddActivityFragment.pager.setCurrentItem(5);
							} else if (AddActivityGeneralInformationFragment.ActivityType == 41) { // full
																									// brand
								AddActivityFragment.fromOther = true;
								DashBoardActivity.tabIndex.add(2, 5);
								AddActivityFragment.pager.setCurrentItem(5);
							} else if (AddActivityGeneralInformationFragment.ActivityType == 100) { // others
								AddActivityFragment.fromOther = true;
								DashBoardActivity.tabIndex.add(2, 5);
								AddActivityFragment.pager.setCurrentItem(5);

							}
						}
					}
				});

		return rootView;
	}

	protected DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			formattedDate = year + "-" + FormatDateAndDay((month + 1)) + "-"
					+ FormatDateAndDay(day);
			((TextView) rootView.findViewById(R.id.follow_up_commitment_date))
					.setText(formattedDate);
		}
	};

	protected String FormatDateAndDay(int digit) {
		String formattedStringDigit = digit < 10 ? "0" + String.valueOf(digit)
				: String.valueOf(digit);
		return String.valueOf(formattedStringDigit);
	}

	protected String displayFollowUpCommittmentDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return " " + df.format(calendar.getTime());
	}
}
