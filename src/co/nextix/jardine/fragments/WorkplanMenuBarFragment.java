package co.nextix.jardine.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.DatePickerDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.collaterals.AdapterCollateralsEventProtocols;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.database.tables.WorkplanEntryTable;
import co.nextix.jardine.database.tables.WorkplanTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
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

	static final int DATE_DIALOG_ID = 999;

	private ImageButton arrowLeft, arrowRight, btnCalendar;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtCust, txtDate, txtIsActType, txtActualDate;
	private TableRow trow;
	private EditText search;
	private Spinner spinner;

	private List<WorkplanEntryRecord> realRecord;
	private List<WorkplanEntryRecord> tempRecord;

	public static EditText editMonth;

	public static Date today = null;
	public static SimpleDateFormat df = null;
	public static String formattedDate = null;

	public int day, month, year;

	private List<String> workplans = new ArrayList<String>();
	private ArrayAdapter<String> adap;

	private WorkplanTable table;
	private WorkplanEntryTable entry;

	public WorkplanMenuBarFragment() {

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
		header = inflater
				.inflate(R.layout.collaterals_event_protocol_row, null);

		initLayout();
		return view;
	}

	private void initLayout() {

		txtActualDate = (TextView) view.findViewById(R.id.tvWorkPlanCalendar);
		txtActualDate.setText(formattedDate);
		spinner = (Spinner) view.findViewById(R.id.spiWorkPlanList);

		// Header Data
		trow = (TableRow) header
				.findViewById(R.id.trCollateralsEventerProtocolRow);
		txtCrm = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolCrmNo);
		txtCust = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolDescription);
		txtDate = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolEventType);
		txtIsActType = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolIsActive);

		txtCrm.setText(getResources().getString(R.string.collaterals_ep_crm_no));
		txtCust.setText(getResources().getString(
				R.string.collaterals_ep_description));
		txtDate.setText(getResources().getString(
				R.string.collaterals_ep_event_type));
		txtIsActType.setText(getResources().getString(
				R.string.collaterals_ep_is_active));
		trow.setBackgroundResource(R.color.tab_pressed);
		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);
		//

		list = (ListView) view.findViewById(R.id.lvWorkPlan);

		list.addHeaderView(header);

		txtPage = (TextView) view.findViewById(R.id.ibWorkPlanPage);

		search = (EditText) view.findViewById(R.id.tvWorkPlanSearch);

		arrowLeft = (ImageButton) view.findViewById(R.id.ibWorkPlanLeft);
		arrowRight = (ImageButton) view.findViewById(R.id.ibWorkPlanRight);

		btnCalendar = (ImageButton) view.findViewById(R.id.ibWorkPlanCalendar);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);
		btnCalendar.setOnClickListener(this);

		realRecord = new ArrayList<WorkplanEntryRecord>();
		tempRecord = new ArrayList<WorkplanEntryRecord>();

		int j = 0;

		table = JardineApp.DB.getWorkplan();
		entry = JardineApp.DB.getWorkplanEntry();

		String id = StoreAccount.restore(getActivity())
				.getString(Account.ROWID);
		long userId = Long.parseLong(id);

		try {
			workplans.addAll(table.getAllWorkplan(userId));
		} catch (Exception e) {

		}

		adap = new ArrayAdapter<String>(getActivity(),
				R.layout.workplan_spinner_row, workplans);
		spinner.setAdapter(adap);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				String str = (String) parent.getAdapter().getItem(position);
				setupWorkplanEntry(str);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void setupWorkplanEntry(String str) {
		long wId = JardineApp.DB.getWorkplan().getIdByNo(str);
		int remainder = realRecord.size() % rowSize;
		if (remainder > 0) {
			realRecord.clear();
			realRecord.addAll(JardineApp.DB.getWorkplanEntry().getAllRecords());

		}

		if (realRecord.size() > 0) {

			totalPage = realRecord.size() / rowSize;
			addItem(currentPage);
		}

		Toast.makeText(getActivity(), wId + " " + realRecord.size(),
				Toast.LENGTH_SHORT).show();
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
				R.layout.collaterals_event_protocol_row, tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				WorkplanEntryRecord epr = (WorkplanEntryRecord) parent
						.getAdapter().getItem(position);

				if (epr.getNo() != null) {

					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.frame_container,
									WorkPlanFragmentDetails.newInstance(epr
											.getId()), JardineApp.TAG)
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

		case R.id.ibWorkPlanCalendar:
			DatePickerDialog pickDialog = new DatePickerDialog(getActivity(),
					android.R.style.Theme_Holo_Panel, datePickerListener, year,
					month, day);
			pickDialog.show();

			break;
		}

	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			// txtActualDate.setText(new StringBuilder().append(month + 1)
			// .append("/").append(day).append("/").append(year)
			// .append(" "));
			formattedDate = (month + 1) + "/" + day + "/" + year;
			txtActualDate.setText(formattedDate);

		}

	};

}
