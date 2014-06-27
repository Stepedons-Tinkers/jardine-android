package co.nextix.jardine.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class StartActivityFragment extends Fragment {

	private StartActivityCustomAdapter adapter = null;
	public Context CustomListView = null;
	public ArrayList<StartActivityListModel> CustomListViewValuesArr = null;
	private View rootView = null;
	private ListView list = null;
	private EditText editMonth = null;
	private Calendar c = null;
	private Date today = null;
	private SimpleDateFormat df = null;
	private String formattedDate = null;
	public int day = 0;
	public int month = 0;
	public int year = 0;
	private Spinner addActivitySpinner = null;

	public StartActivityFragment() {
		this.c = Calendar.getInstance();
		this.df = new SimpleDateFormat("MM/dd/yyyy");
		this.today = new Date();
		this.day = this.c.get(Calendar.DAY_OF_MONTH);
		this.month = this.c.get(Calendar.MONTH);
		this.year = this.c.get(Calendar.YEAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(getActivity(), R.layout.workplan_spinner_row, getResources()
				.getStringArray(R.array.activity_spinner_items));

		this.CustomListViewValuesArr = new ArrayList<StartActivityListModel>();
		this.rootView = inflater.inflate(R.layout.fragment_activites, container, false);
		this.editMonth = (EditText) this.rootView.findViewById(R.id.editMonth);
		this.addActivitySpinner = (Spinner) this.rootView.findViewById(R.id.add_activity_spinner);
		this.formattedDate = this.df.format(this.c.getTime());
		this.addActivitySpinner.setAdapter(sAdapter);

		CustomListView = getActivity().getApplicationContext();

		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();

		this.list = (ListView) this.rootView.findViewById(R.id.list);

		/**************** Create Custom Adapter *********/
		this.adapter = new StartActivityCustomAdapter(this.CustomListView, this.CustomListViewValuesArr, this);
		this.list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);

		// Now formattedDate have current date/time
		//Toast.makeText(getActivity(), "" + this.today, Toast.LENGTH_SHORT).show();
		this.editMonth.setText(this.formattedDate);

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
						.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
			}
		});

		((ImageButton) this.rootView.findViewById(R.id.imageButton1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "<==== ni sud here", Toast.LENGTH_SHORT).show();
			}
		});

		((ImageButton) this.rootView.findViewById(R.id.imageButton3)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "ni sud here ====>", Toast.LENGTH_SHORT).show();
			}
		});

		/*
		 * ((Button)
		 * rootView.findViewById(R.id.activity_info)).getBackground().setColorFilter
		 * ( new LightingColorFilter(0x0033FF, 0x0066FF));
		 */

		return this.rootView;
	}

	private void addTableRow() {
		final TableLayout table = (TableLayout) this.rootView.findViewById(R.id.activity_table);
		final TableRow tr = (TableRow) getLayoutInflater(null).inflate(R.layout.table_row_item, null);

		TextView tv;
		// Fill out our cells
		// tv = (TextView) tr.findViewById(R.id.cell_1);
		// tv.setText(...);

		// tv = (TextView) tr.findViewById(R.id.cell_N);
		// tv.setText(...);
		// table.addView(tr);

		// Draw separator
		// tv = new TextView(this);
		// tv.setBackgroundColor(Color.parseColor("#80808080"));
		// tv.setHeight(4/*height of separaor line. 2dip will be enough*/);
		// table.addView(tv);

		// If you use context menu it should be registered for each table row
		registerForContextMenu(tr);
	}

	/****** Function to set data in ArrayList *************/
	public void setListData() {

		for (int i = 1; i <= 5; i++) {

			final StartActivityListModel sched = new StartActivityListModel();

			ActivityTable table = JardineApp.DB.getActivity();
			List<ActivityRecord> records = table.getAllRecords();

			for (ActivityRecord rec : records) {
				if (rec.equals("") || rec.equals(null)) {
					((TextView) this.rootView.findViewById(R.id.status_list_view)).setVisibility(View.VISIBLE);
					((ListView) this.rootView.findViewById(R.id.list)).setVisibility(View.INVISIBLE);
					((View) this.rootView.findViewById(R.id.view_stub)).setVisibility(View.INVISIBLE);
					return;
				}

				/******* Firstly take data in model object ******/
				sched.setCrmNo(String.valueOf(rec.getCrm()));
				sched.setWorkplan(String.valueOf(rec.getWorkplan()));
				sched.setActivityType(String.valueOf(rec.getActivityType()));
				sched.setStartTime(String.valueOf(rec.getStartTime()));
				sched.setEndTime(String.valueOf(rec.getEndTime()));
				sched.setAssignedTo(String.valueOf(rec.getCustomer()));
			}

			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(sched);
		}
	}

	public void onItemClick(int mPosition) {

		StartActivityListModel tempValues = (StartActivityListModel) CustomListViewValuesArr.get(mPosition);
		Toast.makeText(getActivity(),
				"" + tempValues.getCrmNo() + " \nImage:" + tempValues.getWorkplan() + " \nUrl:" + tempValues.getActivityType(),
				Toast.LENGTH_SHORT).show();

		android.support.v4.app.Fragment fragment = new ActivityInfoFragment();
		android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
				.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
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
}
