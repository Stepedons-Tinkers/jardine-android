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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	private ArrayList<ActivityRecord> realRecord = null;
	private ArrayList<ActivityRecord> tempRecord = null;
	private ArrayList<ActivityRecord> itemSearch = null;
	private Context CustomListView = null;
	private View rootView = null;
	private ListView list = null;
	private Spinner addActivitySpinner = null;
	private EditText editMonth = null;

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
		this.formattedDate = this.df.format(this.c.getTime());
		this.editMonth = (EditText) this.rootView.findViewById(R.id.editMonth);
		this.editMonth.setText(this.formattedDate);
		this.addActivitySpinner = (Spinner) this.rootView.findViewById(R.id.add_activity_spinner);
		this.addActivitySpinner.setAdapter(sAdapter);

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
						} else if (parent.getSelectedItem().toString()
								.equals(getActivity().getResources().getString(R.string.workplan_info_workplan))
								&& searchItem.equals(String.valueOf(records.get(i).getWorkplan()))) {

							itemSearch.add(records.get(i));
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
					adapter = new StartActivityCustomAdapter(CustomListView, itemSearch, StartActivityFragment.this);
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
						} else if (searchItem.equals(getActivity().getResources().getString(R.string.workplan_info_workplan))
								&& v.getText().toString().equals(String.valueOf(records.get(i).getWorkplan()))) {

							itemSearch.add(records.get(i));
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
					adapter = new StartActivityCustomAdapter(CustomListView, itemSearch, StartActivityFragment.this);
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
						.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
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

	/****** Function to set data in ArrayList *************/
	public void setListData() {
		this.realRecord = new ArrayList<ActivityRecord>();
		this.tempRecord = new ArrayList<ActivityRecord>();

		ActivityTable table = JardineApp.DB.getActivity();
		List<ActivityRecord> records = table.getAllRecords();
		this.realRecord.addAll(records);

		Log.d("Jardine", String.valueOf(records.size()));

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
		this.adapter = new StartActivityCustomAdapter(this.CustomListView, this.tempRecord, this);
		this.list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	// Event item listener
	public void onItemClick(int mPosition) {
		ActivityRecord tempValues = (ActivityRecord) tempRecord.get(mPosition);
		Toast.makeText(
				getActivity(),
				"CRM No." + tempValues.getCrm() + " \n Workplan:" + tempValues.getWorkplan() + " \n Activity Type:"
						+ tempValues.getActivityType(), Toast.LENGTH_SHORT).show();

		android.support.v4.app.Fragment fragment = new ActivityInfoFragment();
		final Bundle bundle = new Bundle();
		bundle.putString("crm_no", tempValues.getCrm());
		fragment.setArguments(bundle);

		android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
				.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
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
