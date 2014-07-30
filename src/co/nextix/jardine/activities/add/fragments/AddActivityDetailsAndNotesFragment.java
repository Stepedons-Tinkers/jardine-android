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

	public AddActivityDetailsAndNotesFragment() {
		this.calendar = Calendar.getInstance();
		this.day = this.calendar.get(Calendar.DAY_OF_MONTH);
		this.month = this.calendar.get(Calendar.MONTH);
		this.year = this.calendar.get(Calendar.YEAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<CustomerRecord> customer = JardineApp.DB.getCustomer().getAllRecords();
		List<PicklistRecord> area = JardineApp.DB.getArea().getAllRecords();
		List<WorkplanEntryRecord> workplanEntry = JardineApp.DB.getWorkplanEntry().getAllRecords();

		this.customerAdapter = new ArrayAdapter<CustomerRecord>(getActivity().getApplicationContext(), R.layout.add_activity_textview,
				customer);
		this.areaAdapter = new ArrayAdapter<PicklistRecord>(getActivity().getApplicationContext(), R.layout.add_activity_textview, area);
		this.workplanEntryAdapter = new ArrayAdapter<WorkplanEntryRecord>(getActivity().getApplicationContext(),
				R.layout.add_activity_textview, workplanEntry);

		this.rootView = inflater.inflate(R.layout.add_activity_activity_details_and_notes, container, false);
		((Spinner) this.rootView.findViewById(R.id.area)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				PProvinceTable provinceTable = JardineApp.DB.getProvince();
				provinceAdapter = new ArrayAdapter<ProvinceRecord>(JardineApp.context, R.layout.add_activity_textview, provinceTable
						.getRecordsByAreaId(id + 1));

				((Spinner) rootView.findViewById(R.id.province)).setAdapter(provinceAdapter);

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

				((Spinner) rootView.findViewById(R.id.city_town)).setAdapter(cityTownAdapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getActivity().getApplicationContext(), "Must be filled!", Toast.LENGTH_SHORT).show();
			}
		});

		((ImageButton) this.rootView.findViewById(R.id.ibFollowUpCommitmentCalendar)).setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				DatePickerDialog pickDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Panel, datePickerListener,
						year, month, day);
				pickDialog.show();

			}
		});

		((Spinner) rootView.findViewById(R.id.customer)).setAdapter(this.customerAdapter);
		((Spinner) rootView.findViewById(R.id.area)).setAdapter(this.areaAdapter);
		((Spinner) rootView.findViewById(R.id.workplan_entry)).setAdapter(this.workplanEntryAdapter);

		((CircularProgressButton) rootView.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setClickable(false);

				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							Integer value = (Integer) animation.getAnimatedValue();
							((CircularProgressButton) v).setProgress(value);

							if (!trapping) {
								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					long customer = ((CustomerRecord) ((Spinner) rootView.findViewById(R.id.customer)).getSelectedItem()).getId();
					long area = ((PicklistRecord) ((Spinner) rootView.findViewById(R.id.area)).getSelectedItem()).getId();
					long province = ((ProvinceRecord) ((Spinner) rootView.findViewById(R.id.province)).getSelectedItem()).getId();
					long cityTown = ((CityTownRecord) ((Spinner) rootView.findViewById(R.id.city_town)).getSelectedItem()).getId();
					String objective = ((EditText) rootView.findViewById(R.id.objective)).getText().toString();
					long workplanEntry = ((WorkplanEntryRecord) ((Spinner) rootView.findViewById(R.id.workplan_entry)).getSelectedItem())
							.getId();
					int firstTimeVisit = ((CheckBox) rootView.findViewById(R.id.first_time_visit_checkbox)).isChecked() ? 1 : 0;
					int plannedTimeVisit = ((CheckBox) rootView.findViewById(R.id.planned_visit_checkbox)).isChecked() ? 1 : 0;

					String highlights = ((EditText) rootView.findViewById(R.id.highlights)).getText().toString();
					String notes = ((EditText) rootView.findViewById(R.id.notes)).getText().toString();
					String nextSteps = ((EditText) rootView.findViewById(R.id.next_steps)).getText().toString();
					String followUpCommittmentDate = ((TextView) rootView.findViewById(R.id.follow_up_commitment_date)).getText()
							.toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (objective != null && !objective.isEmpty() && notes != null && !notes.isEmpty() && highlights != null
							&& !highlights.isEmpty() && nextSteps != null && !nextSteps.isEmpty()) {

						trapping = true;
						Editor editor = pref.edit();
						editor.putLong("customer", customer);
						editor.putLong("area", area);
						editor.putLong("province", province);
						editor.putLong("city_town", cityTown);
						editor.putLong("workplan_entry", workplanEntry);
						editor.putInt("first_time_visit", firstTimeVisit);
						editor.putInt("planned_time_visit", plannedTimeVisit);
						editor.putString("objective", objective);
						editor.putString("highlights", highlights);
						editor.putString("notes", notes);
						editor.putString("next_steps", nextSteps);
						editor.putString("follow_up_committment_date", followUpCommittmentDate);

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

						trapping = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_LONG).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);
								v.setClickable(true);
								v.setClickable(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);

					if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retails
						AddActivityFragment.fromOther = true;
						DashBoardActivity.tabIndex.add(2, 5);
						AddActivityFragment.pager.setCurrentItem(5);
					} else if (AddActivityGeneralInformationFragment.ActivityType == 9) { // kivisits
						AddActivityFragment.fromOther = true;
						DashBoardActivity.tabIndex.add(2, 5);
						AddActivityFragment.pager.setCurrentItem(5);
					} else if (AddActivityGeneralInformationFragment.ActivityType == 101) { // major																			// training
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
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			formattedDate = year + "-" + FormatDateAndDay((month + 1)) + "-" + FormatDateAndDay(day);
			((TextView) rootView.findViewById(R.id.follow_up_commitment_date)).setText(formattedDate);
		}
	};

	protected String FormatDateAndDay(int digit) {
		String formattedStringDigit = digit < 10 ? "0" + String.valueOf(digit) : String.valueOf(digit);
		return String.valueOf(formattedStringDigit);
	}

	protected String displayFollowUpCommittmentDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return " " + df.format(calendar.getTime());
	}
}
