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
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.activites.fragments.AdapterActivityList;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class StartActivityFragment extends Fragment implements OnClickListener {

	private StartActivityCustomAdapter adapter = null;
	private ArrayList<ActivityRecord> realRecord = null;
	private ArrayList<ActivityRecord> tempRecord = null;
	private ArrayList<ActivityRecord> itemSearch = null;
	private Context CustomListView = null;
	private View view, header;
	private ListView list = null;
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
	private int rowSize = 8;
	private int totalPage = 0;
	private int currentPage = 0;

	// For header data
	private TableRow hRow;
	private TextView col1, col2, col3, col4, col5, col6;

	public StartActivityFragment() {
		this.c = Calendar.getInstance();
		this.df = new SimpleDateFormat("MM/dd/yyyy");
		this.day = this.c.get(Calendar.DAY_OF_MONTH);
		this.month = this.c.get(Calendar.MONTH);
		this.year = this.c.get(Calendar.YEAR);
		this.itemSearch = new ArrayList<ActivityRecord>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		header = inflater.inflate(R.layout.activity_6_columns, null, false);
		view = inflater.inflate(R.layout.fragment_activites, container, false);

		initLayout();

		return this.view;
	}

	private void initLayout() {

		// InitializeHeaderData;

		hRow = (TableRow) header.findViewById(R.id.trActivityRow);
		col1 = (TextView) header.findViewById(R.id.tvActivityCol1);
		col2 = (TextView) header.findViewById(R.id.tvActivityCol2);
		col3 = (TextView) header.findViewById(R.id.tvActivityCol3);
		col4 = (TextView) header.findViewById(R.id.tvActivityCol4);
		col5 = (TextView) header.findViewById(R.id.tvActivityCol5);
		col6 = (TextView) header.findViewById(R.id.tvActivityCol6);

		hRow.setGravity(Gravity.CENTER);
		col1.setGravity(Gravity.CENTER);
		col2.setGravity(Gravity.CENTER);
		col3.setGravity(Gravity.CENTER);
		col4.setGravity(Gravity.CENTER);
		col5.setGravity(Gravity.CENTER);
		col6.setGravity(Gravity.CENTER);

		col1.setTypeface(null, Typeface.BOLD);
		col2.setTypeface(null, Typeface.BOLD);
		col3.setTypeface(null, Typeface.BOLD);
		col4.setTypeface(null, Typeface.BOLD);
		col5.setTypeface(null, Typeface.BOLD);
		col6.setTypeface(null, Typeface.BOLD);

		col1.setText(getResources().getString(R.string.crm_no));
		col2.setText(getResources().getString(R.string.workplan_info_workplan));
		col3.setText(getResources().getString(R.string.activity_type));
		col4.setText(getResources().getString(R.string.start_time));
		col5.setText(getResources().getString(R.string.end_time));
		col6.setText(getResources().getString(R.string.created_by));

		hRow.setBackgroundResource(R.color.tab_pressed);
		header.setClickable(false);
		header.setFocusable(false);
		header.setOnClickListener(null);
		header.setFocusableInTouchMode(false);

		list = (ListView) view.findViewById(R.id.lvActivityMainList);

		list.addHeaderView(header);

		this.view.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

		this.formattedDate = this.df.format(this.c.getTime());
		// this.editMonth = (EditText) this.view.findViewById(R.id.editMonth);
		// this.editMonth.setText(this.formattedDate);

		txtFrom = (TextView) view.findViewById(R.id.tvActivitiesFromCalendar);
		txtTo = (TextView) view.findViewById(R.id.tvActivitiesToCalendar);

		txtFrom.setOnClickListener(this);
		txtTo.setOnClickListener(this);

		imgFrom = (ImageButton) view
				.findViewById(R.id.ibActivitiesFromCalendar);
		imgTo = (ImageButton) view.findViewById(R.id.ibActivitiesToCalendar);

		imgFrom.setOnClickListener(this);
		imgTo.setOnClickListener(this);
		txtFrom.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				setupActivityEntry();

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		txtTo.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				setupActivityEntry();

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();

		((Button) this.view.findViewById(R.id.add_activity_button))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						android.support.v4.app.Fragment fragment = new AddActivityFragment();
						android.support.v4.app.FragmentManager fragmentManager = getActivity()
								.getSupportFragmentManager();
						fragmentManager
								.beginTransaction()
								.setCustomAnimations(R.anim.slide_in_left,
										R.anim.slide_out_left)
								.replace(R.id.frame_container, fragment,
										"general_info")
								.addToBackStack("activities").commit();
					}
				});

		((ImageButton) this.view.findViewById(R.id.left_arrow))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (currentPage > 0) {
							currentPage--;
							addItem(currentPage);
						}
					}
				});

		((ImageButton) this.view.findViewById(R.id.right_arrow))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (currentPage < totalPage - 1) {
							currentPage++;
							addItem(currentPage);
						}
					}
				});

	}

	private void setupActivityEntry() {

		String dateFrom = txtFrom.getText().toString();
		String dateTo = txtTo.getText().toString();

		dateFrom = MyDateUtils.convertDateStringToDash(dateFrom);
		dateTo = MyDateUtils.convertDateStringToDash(dateTo);

		Log.d("Tugs", dateFrom);
		Log.d("Tugs", dateTo);

		realRecord.clear();
		realRecord.addAll(JardineApp.DB.getActivity().getActivityRecordsByDate(
				dateFrom, dateTo));
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
			// ((TextView)
			// this.view.findViewById(R.id.status_list_view)).setText("The database is still empty. Wanna sync first?");
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) this.view.findViewById(R.id.status_count_text))
				.setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}
		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		// this.adapter = new StartActivityCustomAdapter(this.CustomListView,
		// getActivity(), list, this.tempRecord, this);

		AdapterActivityList aAdapter = new AdapterActivityList(getActivity(),
				R.layout.activity_6_columns, tempRecord);
		list.setAdapter(aAdapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				
				
				ActivityRecord tempValues = (ActivityRecord) parent
						.getAdapter().getItem(position);

				UserRecord userRecord = JardineApp.DB.getUser().getById(
						tempValues.getCreatedBy());
				
				if(userRecord != null){

				// Saving acquired activity details
				SharedPreferences pref = getActivity().getApplicationContext()
						.getSharedPreferences("ActivityInfo", 0);
				Editor editor = pref.edit();
				editor.putLong("activity_id", tempValues.getId());
				editor.putString("crm_no", tempValues.getCrm());
				// editor.putString("workplan",
				// String.valueOf(tempValues.getWorkplan()));
				// editor.putString("start_time", tempValues.getStartTime());
				// editor.putString("end_time", tempValues.getEndTime());
				editor.putString("latitude",
						String.valueOf(tempValues.getLatitude()));
				editor.putString("longitude",
						String.valueOf(tempValues.getLongitude()));
				// editor.putString("objective", tempValues.getObjectives());
				editor.putString("notes", tempValues.getNotes());
				editor.putString("competitor_activities",
						"getCompetitorActivities()");
				editor.putString("highlights", tempValues.getHighlights());
				editor.putString("nextSteps", tempValues.getNextSteps());
				editor.putString("follow_up_commitment_date",
						tempValues.getFollowUpCommitmentDate());
				editor.putString("activity_type",
						String.valueOf(tempValues.getActivityType()));
				editor.putString("others", "getOthers()");
				editor.putString("business_unit", "getBusinessUnit()");
				editor.putString("workplan_entry",
						String.valueOf(tempValues.getWorkplanEntry()));
				editor.putString("customer",
						String.valueOf(tempValues.getCustomer()));
				editor.putString("area", "getArea()");
				editor.putString("province", "getProvince");
				editor.putString("city_town", "getCityTown()");
				editor.putString("first_time_visit",
						String.valueOf(tempValues.getFirstTimeVisit()));
				editor.putString("planned_visit",
						String.valueOf(tempValues.getPlannedVisit()));
				editor.putString("reason_remarks", "getReasonRemarks()");
				editor.putString("details_admin_works",
						"getDetailsAdminWorks()");
				editor.putString("source", "getSource()");
				editor.putString("created_time", tempValues.getCreatedTime());
				editor.putString(
						"assigned_to",
						String.valueOf(userRecord.getLastname() + ", "
								+ userRecord.getFirstNameName()));

				editor.commit(); // commit changes

				// final Bundle bundle = new Bundle();
				// bundle.putString("crm_no", tempValues.getCrm());
				// fragment.setArguments(bundle);

				android.support.v4.app.Fragment fragment = new ActivityInfoFragment();
				android.support.v4.app.FragmentManager fragmentManager = getActivity()
						.getSupportFragmentManager();
				fragmentManager
						.beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left,
								R.anim.slide_out_left)
						.replace(R.id.frame_container, fragment)
						.addToBackStack(null).commit();
				}
			}
		});

	}

	// Event item listener
	public void onItemClick(int mPosition) {
		ActivityRecord tempValues = (ActivityRecord) this.tempRecord
				.get(mPosition);

		if (tempValues.getCrm() != null) {

			UserRecord userRecord = JardineApp.DB.getUser().getById(
					tempValues.getCreatedBy());

			// Saving acquired activity details
			SharedPreferences pref = getActivity().getApplicationContext()
					.getSharedPreferences("ActivityInfo", 0);
			Editor editor = pref.edit();
			editor.putLong("activity_id", tempValues.getId());
			editor.putString("crm_no", tempValues.getCrm());
			// editor.putString("workplan",
			// String.valueOf(tempValues.getWorkplan()));
			// editor.putString("start_time", tempValues.getStartTime());
			// editor.putString("end_time", tempValues.getEndTime());
			editor.putString("latitude",
					String.valueOf(tempValues.getLatitude()));
			editor.putString("longitude",
					String.valueOf(tempValues.getLongitude()));
			// editor.putString("objective", tempValues.getObjectives());
			editor.putString("notes", tempValues.getNotes());
			editor.putString("competitor_activities",
					"getCompetitorActivities()");
			editor.putString("highlights", tempValues.getHighlights());
			editor.putString("nextSteps", tempValues.getNextSteps());
			editor.putString("follow_up_commitment_date",
					tempValues.getFollowUpCommitmentDate());
			editor.putString("activity_type",
					String.valueOf(tempValues.getActivityType()));
			editor.putString("others", "getOthers()");
			editor.putString("business_unit", "getBusinessUnit()");
			editor.putString("workplan_entry",
					String.valueOf(tempValues.getWorkplanEntry()));
			editor.putString("customer",
					String.valueOf(tempValues.getCustomer()));
			editor.putString("area", "getArea()");
			editor.putString("province", "getProvince");
			editor.putString("city_town", "getCityTown()");
			editor.putString("first_time_visit",
					String.valueOf(tempValues.getFirstTimeVisit()));
			editor.putString("planned_visit",
					String.valueOf(tempValues.getPlannedVisit()));
			editor.putString("reason_remarks", "getReasonRemarks()");
			editor.putString("details_admin_works", "getDetailsAdminWorks()");
			editor.putString("source", "getSource()");
			editor.putString("created_time", tempValues.getCreatedTime());
			editor.putString(
					"assigned_to",
					String.valueOf(userRecord.getLastname() + ", "
							+ userRecord.getFirstNameName()));

			editor.commit(); // commit changes

			// final Bundle bundle = new Bundle();
			// bundle.putString("crm_no", tempValues.getCrm());
			// fragment.setArguments(bundle);

			android.support.v4.app.Fragment fragment = new ActivityInfoFragment();
			android.support.v4.app.FragmentManager fragmentManager = getActivity()
					.getSupportFragmentManager();
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.slide_in_left,
							R.anim.slide_out_left)
					.replace(R.id.frame_container, fragment)
					.addToBackStack(null).commit();
		}
	}

	protected void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) this.view.findViewById(R.id.view_stub))
				.setVisibility(View.GONE);
		// ((TextView)
		// this.view.findViewById(R.id.status_list_view)).setVisibility(View.VISIBLE);
	}

	protected void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) this.view.findViewById(R.id.view_stub))
				.setVisibility(View.VISIBLE);
		// ((TextView)
		// this.view.findViewById(R.id.status_list_view)).setVisibility(View.INVISIBLE);
	}

	protected void refreshListView() {
		this.setListData();
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			StartActivityFragment.this.year = selectedYear;
			StartActivityFragment.this.month = selectedMonth;
			StartActivityFragment.this.day = selectedDay;
			StartActivityFragment.this.formattedDate = StartActivityFragment.this
					.FormatDateAndDay((StartActivityFragment.this.month + 1))
					+ "/"
					+ StartActivityFragment.this
							.FormatDateAndDay(StartActivityFragment.this.day)
					+ "/" + StartActivityFragment.this.year;

			StartActivityFragment.this.editMonth
					.setText(StartActivityFragment.this.formattedDate);
		}
	};

	private String FormatDateAndDay(int digit) {
		String formattedStringDigit = digit < 10 ? "0" + String.valueOf(digit)
				: String.valueOf(digit);
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
			DatePickerDialog pickDialog1 = new DatePickerDialog(getActivity(),
					android.R.style.Theme_Holo_Panel, datePickerListenerFrom,
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
			DatePickerDialog pickDialog2 = new DatePickerDialog(getActivity(),
					android.R.style.Theme_Holo_Panel, datePickerListenerTo,
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
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

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
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

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
