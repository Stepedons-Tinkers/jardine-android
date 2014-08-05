package co.nextix.jardine.activities.update.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
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

public class UpdateDetailsAndNotesFragment extends Fragment {

	private View view;
	
	private PProvinceTable provinceTable = null;
	private PCityTownTable cityTable = null;

	private ArrayAdapter<CustomerRecord> customerAdapter = null;
	private ArrayAdapter<PicklistRecord> areaAdapter = null;
	private ArrayAdapter<ProvinceRecord> provinceAdapter = null;
	private ArrayAdapter<CityTownRecord> cityTownAdapter = null;
	private ArrayAdapter<WorkplanEntryRecord> workplanEntryAdapter = null;

	private List<CustomerRecord> customer;
	private List<PicklistRecord> area;
	private List<WorkplanEntryRecord> workplanEntry;

	private Calendar calendar = null;
	private String formattedDate = null;
	private int day = 0;
	private int month = 0;
	private int year = 0;

	private CircularProgressButton saveBtn;

	private boolean trapping = false;

	private Spinner sCustomer;
	private Spinner sArea;
	private Spinner sProvince;
	private Spinner sCityTown;
	private Spinner sWorkplanEntry;

	private EditText etObjective;
	private EditText etHighlights;
	private EditText etNotes;
	private EditText etNextSteps;

	private TextView tvFollowUp;

	private CheckBox cbFirstTime;
	private CheckBox cbPlanned;

	private ImageButton ibCalendar;

	public UpdateDetailsAndNotesFragment() {
		this.calendar = Calendar.getInstance();
		this.day = this.calendar.get(Calendar.DAY_OF_MONTH);
		this.month = this.calendar.get(Calendar.MONTH);
		this.year = this.calendar.get(Calendar.YEAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_details_and_notes, container,
				false);
		
		provinceTable = JardineApp.DB.getProvince();
		cityTable = JardineApp.DB.getCityTown();
		
		saveBtn = (CircularProgressButton) view.findViewById(R.id.btnWithText1);

		customer = JardineApp.DB.getCustomer().getAllRecords();
		area = JardineApp.DB.getArea().getAllRecords();
		workplanEntry = JardineApp.DB.getWorkplanEntry().getAllRecords();

		customerAdapter = new ArrayAdapter<CustomerRecord>(getActivity()
				.getApplicationContext(), R.layout.add_activity_textview,
				customer);
		areaAdapter = new ArrayAdapter<PicklistRecord>(getActivity()
				.getApplicationContext(), R.layout.add_activity_textview, area);
		workplanEntryAdapter = new ArrayAdapter<WorkplanEntryRecord>(
				getActivity().getApplicationContext(),
				R.layout.add_activity_textview, workplanEntry);

		sCustomer = (Spinner) view.findViewById(R.id.update_customer);
		sArea = (Spinner) view.findViewById(R.id.update_area);
		sProvince = (Spinner) view.findViewById(R.id.update_province);
		sCityTown = (Spinner) view.findViewById(R.id.update_city_town);
		sWorkplanEntry = (Spinner) view
				.findViewById(R.id.update_workplan_entry);

		etObjective = (EditText) view.findViewById(R.id.update_objective);
		etHighlights = (EditText) view.findViewById(R.id.update_highlights);
		etNotes = (EditText) view.findViewById(R.id.update_notes);
		etNextSteps = (EditText) view.findViewById(R.id.update_next_steps);

		tvFollowUp = (TextView) view
				.findViewById(R.id.update_follow_up_commitment_date);

		cbFirstTime = (CheckBox) view
				.findViewById(R.id.update_first_time_visit_checkbox);
		cbPlanned = (CheckBox) view
				.findViewById(R.id.update_planned_visit_checkbox);

		ibCalendar = (ImageButton) view.findViewById(R.id.update_ibFollowUpCommitmentCalendar);
		
		sCustomer.setAdapter(customerAdapter);
		sArea.setAdapter(areaAdapter);
		sWorkplanEntry.setAdapter(workplanEntryAdapter);
		
		if(UpdateConstants.RECORD != null){
			etObjective.setText(UpdateConstants.OBJECTIVE);
			etHighlights.setText(UpdateConstants.HIGHLIGHTS);
			etNotes.setText(UpdateConstants.NOTES);
			etNextSteps.setText(UpdateConstants.NEXT_STEPS);
			tvFollowUp.setText(UpdateConstants.FOLLOW_UP_DATE);
			
			sCustomer.setSelection(Integer.parseInt(String.valueOf(UpdateConstants.CUSTOMER))-1);
			sArea.setSelection(Integer.parseInt(String.valueOf(UpdateConstants.AREA))-1);
			sWorkplanEntry.setSelection(Integer.parseInt(String.valueOf(UpdateConstants.WORKPLAN_ENTRY))-1);
			
			provinceAdapter = new ArrayAdapter<ProvinceRecord>(
					JardineApp.context,
					R.layout.add_activity_textview, provinceTable
							.getRecordsByAreaId(sArea.getSelectedItemId() + 1));
			
			
			sProvince.setAdapter(provinceAdapter);
			
			for(int x = 0; x < provinceAdapter.getCount(); x++){
				String prov = JardineApp.DB.getProvince().getById(UpdateConstants.PROVINCE).toString();
				String data = provinceAdapter.getItem(x).toString();
				
				if(prov.equalsIgnoreCase(data)){
					sProvince.setSelection(x);
					break;
				}
			}
			
			cityTownAdapter = new ArrayAdapter<CityTownRecord>(
					JardineApp.context,
					R.layout.add_activity_textview, cityTable
							.getRecordsByProvinceId(sProvince.getSelectedItemId() + 1));
			
			sCityTown.setAdapter(cityTownAdapter);
			
			for(int y = 0; y < cityTownAdapter.getCount(); y++){
				String city = JardineApp.DB.getCityTown().getById(UpdateConstants.CITY_TOWN).toString();
				String data = cityTownAdapter.getItem(y).toString();
				
				if(city.equalsIgnoreCase(data)){
					sCityTown.setSelection(y);
					break;
				}
			}
			
			if(UpdateConstants.FIRST_TIME != 0){
				cbFirstTime.setChecked(true);
			}
			
			if(UpdateConstants.PLANNED != 0){
				cbPlanned.setChecked(true);
			}
		}
		
		sCustomer.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				CustomerRecord cRecord = (CustomerRecord) parent.getAdapter().getItem(position);
				UpdateConstants.CUSTOMER_RECORD = cRecord;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
		sArea.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				if(UpdateConstants.RECORD == null){
					provinceAdapter = new ArrayAdapter<ProvinceRecord>(
							JardineApp.context,
							R.layout.add_activity_textview, provinceTable
									.getRecordsByAreaId(id + 1));
					
					sProvince.setAdapter(provinceAdapter);
				}
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
				if(UpdateConstants.RECORD == null){
					cityTownAdapter = new ArrayAdapter<CityTownRecord>(
							JardineApp.context,
							R.layout.add_activity_textview, cityTable
									.getRecordsByProvinceId(id + 1));
					
					sCityTown.setAdapter(cityTownAdapter);
				}				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getActivity().getApplicationContext(),
						"Must be filled!", Toast.LENGTH_SHORT).show();
			}
		});
		
		ibCalendar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog pickDialog = new DatePickerDialog(
						getActivity(),
						android.R.style.Theme_Holo_Panel,
						datePickerListener, year, month, day);
				pickDialog.show();

			}
		});
		
		saveBtn = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
		saveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);
				
				if (saveBtn.getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
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
									saveBtn.setProgress(value);

									if (!trapping) {
										saveBtn.setProgress(-1);
									}
								}
							});

					widthAnimation.start();
					
					String notes = etNotes.getText().toString();
					String highlights = etHighlights.getText().toString();
					String nextSteps = etNextSteps.getText().toString();
					String objective = etObjective.getText().toString();

					if (notes != null && !notes.isEmpty()
							&& highlights != null && !highlights.isEmpty()
							&& nextSteps != null && !nextSteps.isEmpty()
							&& objective != null && !objective.isEmpty()) {
						
						trapping = true;
						
						UpdateConstants.ACTIVITY_RECORD.setNo(notes);
						UpdateConstants.ACTIVITY_RECORD.setHighlights(highlights);
						UpdateConstants.ACTIVITY_RECORD.setNextSteps(nextSteps);
						UpdateConstants.ACTIVITY_RECORD.setObjective(objective);
						UpdateConstants.ACTIVITY_RECORD.setCustomer(sCustomer.getSelectedItemId());
						UpdateConstants.ACTIVITY_RECORD.setArea(sArea.getSelectedItemId());
						UpdateConstants.ACTIVITY_RECORD.setProvince(sProvince.getSelectedItemId());
						UpdateConstants.ACTIVITY_RECORD.setCity(sCityTown.getSelectedItemId());
						UpdateConstants.ACTIVITY_RECORD.setFirstTimeVisit(cbFirstTime.isChecked() ? 1 : 0);
						UpdateConstants.ACTIVITY_RECORD.setPlannedVisit(cbPlanned.isChecked() ? 1 : 0);
						UpdateConstants.ACTIVITY_RECORD.setFollowUpCommitmentDate(tvFollowUp.getText().toString());

						// Set the button click to true
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								v.setClickable(true);
								v.setEnabled(true);
								
								getActivity().getSupportFragmentManager();
								getActivity().getSupportFragmentManager().popBackStack();
							}

						}, 1500);

					} else {

						trapping = false;
						Toast.makeText(getActivity(),
								"Please fill up required (RED COLOR) fields",
								Toast.LENGTH_LONG).show();

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
					saveBtn.setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);
				}
			}
		});

		return view;
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
			tvFollowUp.setText(formattedDate);
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
