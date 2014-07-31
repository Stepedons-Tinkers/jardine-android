package co.nextix.jardine.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.view.group.utils.ListViewUtility;
import co.nextix.jardine.workplan.AdapterWorkplanEntry;

public class StartActivityFragment extends Fragment implements OnClickListener {

	private StartActivityCustomAdapter adapter = null;
	private ArrayList<ActivityRecord> realRecord = null;
	private ArrayList<ActivityRecord> tempRecord = null;
	private ArrayList<ActivityRecord> itemSearch = null;
	private Context CustomListView = null;
	private View rootView = null;
	private ListView list = null;
	private Spinner addActivitySpinner = null;
	private EditText editMonth = null;
	private ImageButton imgFrom, imgTo;
	private TextView txtFrom, txtTo;
	private Date maxDate = null;
	private Date minDate = null;

	private Calendar c = null;
	private SimpleDateFormat df = null;
	public String formattedDate = null;
	private int day = 0;
	private int month = 0;
	private int year = 0;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;

	public StartActivityFragment() {
		this.c = Calendar.getInstance();
		this.df = new SimpleDateFormat("MM/dd/yyyy");
		this.day = this.c.get(Calendar.DAY_OF_MONTH);
		this.month = this.c.get(Calendar.MONTH);
		this.year = this.c.get(Calendar.YEAR);
		this.itemSearch = new ArrayList<ActivityRecord>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(getActivity(), R.layout.workplan_spinner_row, getResources()
				.getStringArray(R.array.activity_spinner_items));

		this.rootView = inflater.inflate(R.layout.fragment_activites, container, false);
		this.rootView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

		this.formattedDate = this.df.format(this.c.getTime());
		this.editMonth = (EditText) this.rootView.findViewById(R.id.editMonth);
		this.editMonth.setText(this.formattedDate);
		this.addActivitySpinner = (Spinner) this.rootView.findViewById(R.id.add_activity_spinner);
		this.addActivitySpinner.setAdapter(sAdapter);

		txtFrom = (TextView) rootView.findViewById(R.id.tvActivitiesFromCalendar);
		txtTo = (TextView) rootView.findViewById(R.id.tvActivitiesToCalendar);

		txtFrom.setOnClickListener(this);
		txtTo.setOnClickListener(this);

		imgFrom = (ImageButton) rootView.findViewById(R.id.ibActivitiesFromCalendar);
		imgTo = (ImageButton) rootView.findViewById(R.id.ibActivitiesToCalendar);

		imgFrom.setOnClickListener(this);
		imgTo.setOnClickListener(this);
		txtFrom.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				setupActivityEntry();

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		txtTo.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				setupActivityEntry();

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();

		this.addActivitySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				ActivityTable table = JardineApp.DB.getActivity();
				List<ActivityRecord> records = table.getAllRecords();

				// Getting the position of the spinner
				String searchItem = ((EditText) rootView.findViewById(R.id.search_activities)).getText().toString();
				if (searchItem.length() > 0) {
					itemSearch.clear();
					for (int i = 0; i < records.size(); i++) {
						if (parent.getSelectedItem().toString().equals(getActivity().getResources().getString(R.string.crm_no))
								&& searchItem.equals(records.get(i).getCrm())) {

							itemSearch.add(records.get(i));
							// } else if
							// (parent.getSelectedItem().toString()
							// .equals(getActivity().getResources().getString(R.string.workplan_info_workplan))
							// &&
							// searchItem.equals(String.valueOf(records.get(i).getWorkplan())))
							// {
							//
							// itemSearch.add(records.get(i));
						} else if (parent.getSelectedItem().toString()
								.equals(getActivity().getResources().getString(R.string.activity_type))
								&& searchItem.equals(String.valueOf(records.get(i).getActivityType()))) {

							itemSearch.add(records.get(i));
						} else if (parent.getSelectedItem().toString().equals(getActivity().getResources().getString(R.string.assigned_to))
								&& searchItem.equals(String.valueOf(records.get(i).getCustomer()))) {

							itemSearch.add(records.get(i));
						}
					}

					CustomListView = getActivity().getApplicationContext();
					list = (ListView) rootView.findViewById(R.id.list);
					adapter = new StartActivityCustomAdapter(CustomListView, getActivity(), list, itemSearch, StartActivityFragment.this);
					list.setAdapter(adapter);
					ListViewUtility.setListViewHeightBasedOnChildren(list);
				} else {
					return;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		((EditText) this.rootView.findViewById(R.id.search_activities)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					ActivityTable table = JardineApp.DB.getActivity();
					List<ActivityRecord> records = table.getAllRecords();

					itemSearch.clear();

					// Getting the position of the spinner
					String searchItem = String.valueOf(StartActivityFragment.this.addActivitySpinner.getSelectedItem());
					for (int i = 0; i < records.size(); i++) {
						if (searchItem.equals(getActivity().getResources().getString(R.string.crm_no))
								&& v.getText().toString().equals(records.get(i).getCrm())) {

							itemSearch.add(records.get(i));
							// } else if
							// (searchItem.equals(getActivity().getResources().getString(R.string.workplan_info_workplan))
							// &&
							// v.getText().toString().equals(String.valueOf(records.get(i).getWorkplan())))
							// {
							//
							// itemSearch.add(records.get(i));
						} else if (searchItem.equals(getActivity().getResources().getString(R.string.activity_type))
								&& v.getText().toString().equals(String.valueOf(records.get(i).getActivityType()))) {

							itemSearch.add(records.get(i));
						} else if (searchItem.equals(getActivity().getResources().getString(R.string.assigned_to))
								&& v.getText().toString().equals(String.valueOf(records.get(i).getCustomer()))) {

							itemSearch.add(records.get(i));
						}
					}

					CustomListView = getActivity().getApplicationContext();
					list = (ListView) rootView.findViewById(R.id.list);
					adapter = new StartActivityCustomAdapter(CustomListView, getActivity(), list, itemSearch, StartActivityFragment.this);
					list.setAdapter(adapter);
					ListViewUtility.setListViewHeightBasedOnChildren(list);
				}

				return false;
			}
		});

		((ImageButton) this.rootView.findViewById(R.id.prev_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StartActivityFragment.this.c.add(Calendar.DAY_OF_MONTH, -1);
				Date yesterday = StartActivityFragment.this.c.getTime();
				StartActivityFragment.this.editMonth.setText(toddMMyy(yesterday));
			}
		});

		((ImageButton) this.rootView.findViewById(R.id.next_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// adding one day to current date
				StartActivityFragment.this.c.add(Calendar.DAY_OF_MONTH, 1);
				Date tommrrow = StartActivityFragment.this.c.getTime();
				StartActivityFragment.this.editMonth.setText(toddMMyy(tommrrow));
			}
		});

		((ImageButton) this.rootView.findViewById(R.id.activity_schedule)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog pickDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Panel, datePickerListener,
						StartActivityFragment.this.year, StartActivityFragment.this.month, StartActivityFragment.this.day);
				pickDialog.show();
			}
		});

		((Button) this.rootView.findViewById(R.id.add_activity_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				android.support.v4.app.Fragment fragment = new AddActivityFragment();
				android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
						.replace(R.id.frame_container, fragment, "general_info").addToBackStack("activities").commit();
			}
		});

		((ImageButton) this.rootView.findViewById(R.id.left_arrow)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (currentPage > 0) {
					currentPage--;
					addItem(currentPage);
				}
			}
		});

		((ImageButton) this.rootView.findViewById(R.id.right_arrow)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (currentPage < totalPage - 1) {
					currentPage++;
					addItem(currentPage);
				}
			}
		});

		return this.rootView;
	}

	private void setupActivityEntry() {

		String dateFrom = txtFrom.getText().toString();
		String dateTo = txtTo.getText().toString();

		dateFrom = MyDateUtils.convertDateStringToDash(dateFrom);
		dateTo = MyDateUtils.convertDateStringToDash(dateTo);

		Log.d("Tugs", dateFrom);
		Log.d("Tugs", dateTo);

		realRecord.clear();
		realRecord.addAll(JardineApp.DB.getActivity().getActivityRecordsByDate(dateFrom, dateTo));
		int remainder = realRecord.size() % rowSize;
		// if (remainder > 0) {
		for (int i = 0; i < rowSize - remainder; i++) {
			ActivityRecord rec = new ActivityRecord();
			realRecord.add(rec);
		}

		// } else {
		// AdapterWorkplanEntry adapter = new AdapterWorkplanEntry(
		// getActivity(),
		// R.layout.collaterals_marketing_materials_row, realRecord);
		// list.setAdapter(adapter);
		// }

		if (realRecord.size() > 0) {

			totalPage = realRecord.size() / rowSize;
			currentPage = 0;
			addItem(currentPage);
		}

	}

	/****** Function to set data in ArrayList *************/
	public void setListData() {
		this.realRecord = new ArrayList<ActivityRecord>();
		this.tempRecord = new ArrayList<ActivityRecord>();

		ActivityTable table = JardineApp.DB.getActivity();
		List<ActivityRecord> records = table.getAllRecords();
		this.realRecord.addAll(records);

		Log.d("Jardine", "ActivityRecord" + String.valueOf(records.size()));

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					ActivityRecord rec = new ActivityRecord();
					realRecord.add(rec);
				}
			}

			this.totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		} else {

			this.setView();
			this.isListHasNoData();
			((TextView) this.rootView.findViewById(R.id.status_list_view)).setText("The database is still empty. Wanna sync first?");
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) this.rootView.findViewById(R.id.status_count_text)).setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}
		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		this.list = (ListView) this.rootView.findViewById(R.id.list);
		this.adapter = new StartActivityCustomAdapter(this.CustomListView, getActivity(), list, this.tempRecord, this);
		this.list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	// Event item listener
	public void onItemClick(int mPosition) {
		ActivityRecord tempValues = (ActivityRecord) this.tempRecord.get(mPosition);

		if (tempValues.getCrm() != null) {

			UserRecord userRecord = JardineApp.DB.getUser().getById(tempValues.getCreatedBy());

			// Saving acquired activity details
			SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
			Editor editor = pref.edit();
			editor.putLong("activity_id", tempValues.getId());
			editor.putString("crm_no", tempValues.getCrm());
			// editor.putString("workplan",
			// String.valueOf(tempValues.getWorkplan()));
			// editor.putString("start_time", tempValues.getStartTime());
			// editor.putString("end_time", tempValues.getEndTime());
			editor.putString("latitude", String.valueOf(tempValues.getLatitude()));
			editor.putString("longitude", String.valueOf(tempValues.getLongitude()));
			// editor.putString("objective", tempValues.getObjectives());
			editor.putString("notes", tempValues.getNotes());
			editor.putString("competitor_activities", "getCompetitorActivities()");
			editor.putString("highlights", tempValues.getHighlights());
			editor.putString("nextSteps", tempValues.getNextSteps());
			editor.putString("follow_up_commitment_date", tempValues.getFollowUpCommitmentDate());
			editor.putString("activity_type", String.valueOf(tempValues.getActivityType()));
			editor.putString("others", "getOthers()");
			editor.putString("business_unit", "getBusinessUnit()");
			editor.putString("workplan_entry", String.valueOf(tempValues.getWorkplanEntry()));
			editor.putString("customer", String.valueOf(tempValues.getCustomer()));
			editor.putString("area", "getArea()");
			editor.putString("province", "getProvince");
			editor.putString("city_town", "getCityTown()");
			editor.putString("first_time_visit", String.valueOf(tempValues.getFirstTimeVisit()));
			editor.putString("planned_visit", String.valueOf(tempValues.getPlannedVisit()));
			editor.putString("reason_remarks", "getReasonRemarks()");
			editor.putString("details_admin_works", "getDetailsAdminWorks()");
			editor.putString("source", "getSource()");
			editor.putString("created_time", tempValues.getCreatedTime());
			editor.putString("assigned_to", String.valueOf(userRecord.getLastname() + ", " + userRecord.getFirstNameName()));

			editor.commit(); // commit changes

			// final Bundle bundle = new Bundle();
			// bundle.putString("crm_no", tempValues.getCrm());
			// fragment.setArguments(bundle);

			android.support.v4.app.Fragment fragment = new ActivityInfoFragment();
			android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
					.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
		}
	}

	protected void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) this.rootView.findViewById(R.id.view_stub)).setVisibility(View.GONE);
		((TextView) this.rootView.findViewById(R.id.status_list_view)).setVisibility(View.VISIBLE);
	}

	protected void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) this.rootView.findViewById(R.id.view_stub)).setVisibility(View.VISIBLE);
		((TextView) this.rootView.findViewById(R.id.status_list_view)).setVisibility(View.INVISIBLE);
	}

	protected void refreshListView() {
		this.setListData();
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			StartActivityFragment.this.year = selectedYear;
			StartActivityFragment.this.month = selectedMonth;
			StartActivityFragment.this.day = selectedDay;
			StartActivityFragment.this.formattedDate = StartActivityFragment.this.FormatDateAndDay((StartActivityFragment.this.month + 1))
					+ "/" + StartActivityFragment.this.FormatDateAndDay(StartActivityFragment.this.day) + "/"
					+ StartActivityFragment.this.year;

			StartActivityFragment.this.editMonth.setText(StartActivityFragment.this.formattedDate);
		}
	};

	private String FormatDateAndDay(int digit) {
		String formattedStringDigit = digit < 10 ? "0" + String.valueOf(digit) : String.valueOf(digit);
		return String.valueOf(formattedStringDigit);
	}

	private String toddMMyy(Date day) {
		String date = this.df.format(day);
		return date;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.ibActivitiesFromCalendar:
			DatePickerDialog pickDialog1 = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Panel, datePickerListenerFrom,
					year, month, day);
			if (maxDate != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(maxDate);
				Date date = calendar.getTime();
				pickDialog1.getDatePicker().setMaxDate(date.getTime());
			}
			pickDialog1.show();

			break;

		case R.id.ibActivitiesToCalendar:
			DatePickerDialog pickDialog2 = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Panel, datePickerListenerTo,
					year, month, day);
			if (minDate != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(minDate);
				Date date = calendar.getTime();
				pickDialog2.getDatePicker().setMinDate(date.getTime());
			}
			pickDialog2.show();

			break;

		case R.id.tvActivitiesFromCalendar:
			txtFrom.setText(null);
			break;
		case R.id.tvActivitiesToCalendar:
			txtTo.setText(null);
			break;
		}
		// if(v == imgFrom){
		//
		// }else if(v == imgTo){
		//
		// }else if(v == txtFrom){
		//
		// }else if(v == txtTo){
		//
		// }
		//

	}

	private DatePickerDialog.OnDateSetListener datePickerListenerFrom = new DatePickerDialog.OnDateSetListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			formattedDate = (month + 1) + "/" + day + "/" + year;
			Calendar lcalendar = Calendar.getInstance();
			lcalendar.set(selectedYear, selectedMonth, selectedDay);
			minDate = lcalendar.getTime();
			txtFrom.setText(formattedDate);

		}

	};

	private DatePickerDialog.OnDateSetListener datePickerListenerTo = new DatePickerDialog.OnDateSetListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			formattedDate = (month + 1) + "/" + day + "/" + year;
			Calendar lcalendar = Calendar.getInstance();
			lcalendar.set(selectedYear, selectedMonth, selectedDay);
			maxDate = lcalendar.getTime();
			txtTo.setText(formattedDate);

		}

	};

}
