package co.nextix.jardine.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.DatePickerDialog;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.collaterals.CustomSpinnerAdapter;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.database.tables.WorkplanEntryTable;
import co.nextix.jardine.database.tables.WorkplanTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.view.group.utils.ListViewUtility;
import co.nextix.jardine.workplan.AdapterWorkplanEntry;
import co.nextix.jardine.workplan.WorkPlanFragmentDetails;

public class WorkplanMenuBarFragment extends Fragment implements
		OnClickListener {

	private View view;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;
	private String searchPhrase = "";

	static final int DATE_DIALOG_ID = 999;

	private ImageButton arrowLeft, arrowRight, btnCalendar;
	private TextView txtPage;
	private View header;
	private TextView txtCol1, txtCol2, txtCol3;
	private TableRow trow;
	private TextView txtFrom, txtTo;
	private Spinner spinner, searchSpinner;
	private SearchView searchView;
	private ImageButton imgFrom, imgTo;

	private List<WorkplanEntryRecord> realRecord;
	private List<WorkplanEntryRecord> tempRecord;
	private List<WorkplanEntryRecord> searchRecord;

	public static EditText editMonth;

	public static Date today = null;
	public static SimpleDateFormat df = null;
	public static String formattedDate = null;

	private WorkplanCustomerAdapter adap;

	public int day, month, year;
	private long userId;
	private List<WorkplanRecord> workplans = new ArrayList<WorkplanRecord>();
	// private ArrayAdapter<String> adap;

	private WorkplanTable table;
	private WorkplanEntryTable entry;

	public WorkplanMenuBarFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		String id = StoreAccount.restore(getActivity())
//				.getString(Account.ROWID);
		userId = StoreAccount.restore(getActivity()).getLong(Account.ROWID);
		this.setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		final Calendar c = Calendar.getInstance();
		df = new SimpleDateFormat("MM/dd/yyyy");
		today = new Date();

		day = c.get(Calendar.DAY_OF_MONTH);
		month = c.get(Calendar.MONTH);
		year = c.get(Calendar.YEAR);

		// day = today.getDay();
		// month = today.getMonth();
		// year = today.getYear();
		// formattedDate = df.format(c.getTime());
		formattedDate = (month + 1) + "/" + day + "/" + year;

		view = inflater.inflate(R.layout.workplan, container, false);
		header = inflater.inflate(R.layout.workplan_3_columns, null);

		DashBoardActivity.tabIndex.clear();
		DashBoardActivity.fromAddActivities = false;

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initLayout();
	}

	private void initLayout() {

		// Header Data
		getActivity().invalidateOptionsMenu();
		trow = (TableRow) header.findViewById(R.id.trWorkplanRowH);
		txtCol1 = (TextView) header.findViewById(R.id.tvWorkplanCol1);
		txtCol2 = (TextView) header.findViewById(R.id.tvWorkplanCol2);
		txtCol3 = (TextView) header.findViewById(R.id.tvWorkplanCol3);

		trow.setGravity(Gravity.CENTER);
		txtCol1.setGravity(Gravity.CENTER);
		txtCol2.setGravity(Gravity.CENTER);
		txtCol3.setGravity(Gravity.CENTER);

		txtCol1.setTypeface(null, Typeface.BOLD);
		txtCol2.setTypeface(null, Typeface.BOLD);
		txtCol3.setTypeface(null, Typeface.BOLD);

		txtCol1.setText(getResources()
				.getString(R.string.collaterals_ep_crm_no));
		txtCol2.setText(getResources().getString(R.string.workplan_date));
		txtCol3.setText(getResources().getString(
				R.string.workplan_activity_type));

		trow.setBackgroundResource(R.color.tab_pressed);

		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);

		list = (ListView) view.findViewById(R.id.lvWorkPlan);
		list.addHeaderView(header);

		searchSpinner = (Spinner) view.findViewById(R.id.spiWorkPlanSpinner);

		txtPage = (TextView) view.findViewById(R.id.ibWorkPlanPage);
		txtFrom = (TextView) view.findViewById(R.id.tvWorkPlanFromCalendar);
		txtTo = (TextView) view.findViewById(R.id.tvWorkPlanToCalendar);

		imgFrom = (ImageButton) view.findViewById(R.id.ibWorkPlanFromCalendar);
		imgTo = (ImageButton) view.findViewById(R.id.ibWorkPlanToCalendar);

		txtFrom.setText(formattedDate);
		txtTo.setText(formattedDate);

		txtFrom.setOnClickListener(this);
		txtTo.setOnClickListener(this);

		imgFrom.setOnClickListener(this);
		imgTo.setOnClickListener(this);

		// search = (EditText) view.findViewById(R.id.tvWorkPlanSearch);

		arrowLeft = (ImageButton) view.findViewById(R.id.ibWorkPlanLeft);
		arrowRight = (ImageButton) view.findViewById(R.id.ibWorkPlanRight);

		// btnCalendar = (ImageButton)
		// view.findViewById(R.id.ibWorkPlanCalendar);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		realRecord = new ArrayList<WorkplanEntryRecord>();
		tempRecord = new ArrayList<WorkplanEntryRecord>();

		int j = 0;

		table = JardineApp.DB.getWorkplan();
		entry = JardineApp.DB.getWorkplanEntry();

		try {
			workplans.addAll(table.getAllRecords(userId));
		} catch (Exception e) {

		}

		adap = new WorkplanCustomerAdapter(getActivity(),
				R.layout.workplan_3_columns, workplans);

		searchSpinner.setAdapter(adap);

		searchSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				setupWorkplanEntry();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		txtFrom.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				setupWorkplanEntry();

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
				setupWorkplanEntry();

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		setupWorkplanEntry();
	}

	private void setupWorkplanEntry() {

		String dateFrom = txtFrom.getText().toString();
		String dateTo = txtTo.getText().toString();
		long wId = ((WorkplanRecord) searchSpinner.getSelectedItem()).getId();

		dateFrom = MyDateUtils.convertDateStringToDash(dateFrom);
		dateTo = MyDateUtils.convertDateStringToDash(dateTo);

		Log.d("Tugs", dateFrom);
		Log.d("Tugs", dateTo);
		Log.d("Tugs", wId + "");
		Log.d("Tugs", searchPhrase + "");

		realRecord.clear();

		if (spinner == null) {
			realRecord.addAll(JardineApp.DB.getWorkplanEntry()
					.getRecordsByWorkplanIdDate(wId, dateFrom, dateTo,
							searchPhrase, 0));
		} else {
			realRecord.addAll(JardineApp.DB.getWorkplanEntry()
					.getRecordsByWorkplanIdDate(wId, dateFrom, dateTo,
							searchPhrase, spinner.getSelectedItemPosition()));

		}

		int remainder = realRecord.size() % rowSize;
		if (remainder > 0) {
			for (int i = 0; i < rowSize - remainder; i++) {
				WorkplanEntryRecord rec = new WorkplanEntryRecord();
				realRecord.add(rec);
			}

		} else {
			AdapterWorkplanEntry adapter = new AdapterWorkplanEntry(
					getActivity(), R.layout.workplan_3_columns, realRecord);
			list.setAdapter(adapter);
		}

		if (realRecord.size() > 0) {

			totalPage = realRecord.size() / rowSize;
			addItem(currentPage);
		}

	}

	private void addItemFromSearch(int count) {

		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		txtPage.setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, searchRecord.get(count));
			count = count + 1;
		}

		setView();
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		txtPage.setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		setView();
	}

	private void setView() {

		AdapterWorkplanEntry adapter = new AdapterWorkplanEntry(getActivity(),
				R.layout.workplan_3_columns, tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				WorkplanEntryRecord epr = (WorkplanEntryRecord) parent
						.getAdapter().getItem(position);

				if (epr.getNo() != null) {

					WorkPlanFragmentDetails frag = WorkPlanFragmentDetails
							.newInstance(epr.getId());

					frag.setTargetFragment(WorkplanMenuBarFragment.this, 15);

					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager().beginTransaction()
							.add(R.id.frame_container, frag, JardineApp.TAG)
							.addToBackStack(JardineApp.TAG).commit();
				}

			}
		});

		ListViewUtility.setListViewHeightBasedOnChildren(list);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ibWorkPlanLeft:
			if (currentPage > 0) {
				currentPage--;
				addItem(currentPage);
			}
			break;
		case R.id.ibWorkPlanRight:
			if (currentPage < totalPage - 1) {
				currentPage++;
				addItem(currentPage);
			}
			break;

		case R.id.ibWorkPlanFromCalendar:
			DatePickerDialog pickDialog1 = new DatePickerDialog(getActivity(),
					android.R.style.Theme_Holo_Panel, datePickerListenerFrom,
					year, month, day);
			pickDialog1.show();

			break;

		case R.id.ibWorkPlanToCalendar:
			DatePickerDialog pickDialog2 = new DatePickerDialog(getActivity(),
					android.R.style.Theme_Holo_Panel, datePickerListenerTo,
					year, month, day);
			pickDialog2.show();

			break;

		case R.id.tvWorkPlanFromCalendar:
			txtFrom.setText(null);
			break;
		case R.id.tvWorkPlanToCalendar:
			txtTo.setText(null);
			break;
		}

	}

	private DatePickerDialog.OnDateSetListener datePickerListenerFrom = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			formattedDate = (month + 1) + "/" + day + "/" + year;
			txtFrom.setText(formattedDate);

		}

	};

	private DatePickerDialog.OnDateSetListener datePickerListenerTo = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			formattedDate = (month + 1) + "/" + day + "/" + year;
			txtTo.setText(formattedDate);

		}

	};

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.collaterals_menu, menu);
		searchView = (SearchView) menu.findItem(R.id.itemCollateralSearch)
				.getActionView();
		spinner = (Spinner) menu.findItem(R.id.itemCollateralSpinner)
				.getActionView();

		List<String> strSearcher = new ArrayList<String>();

		strSearcher.add(getResources()
				.getString(R.string.collaterals_ep_crm_no));
		strSearcher.add(getResources().getString(
				R.string.workplan_activity_type));

		CustomSpinnerAdapter cus = new CustomSpinnerAdapter(getActivity(),
				R.layout.workplan_spinner_row, strSearcher);
		spinner.setAdapter(cus);

		searchView.setOnCloseListener(new OnCloseListener() {

			@Override
			public boolean onClose() {
				searchView.clearFocus();
				currentPage = 0;
				addItem(currentPage);
				searchView.onActionViewCollapsed();
				searchPhrase = "";
				setupWorkplanEntry();

				return true;
			}
		});
		searchView.setOnSearchClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tempRecord.clear();

				searchView.clearFocus();
			}
		});
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextChange(String arg0) {

				return true;
			}

			@Override
			public boolean onQueryTextSubmit(String arg0) {

				currentPage = 0;
				try {

					searchPhrase = arg0;
					setupWorkplanEntry();

				} catch (SQLiteException e) {

					Log.e("Tugs", e.toString());
				}

				searchView.clearFocus();
				return true;
			}

		});

	}
}
