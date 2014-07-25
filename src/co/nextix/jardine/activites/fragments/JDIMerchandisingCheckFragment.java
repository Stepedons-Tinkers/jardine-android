package co.nextix.jardine.activites.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.JDIMerchandisingCheckCustomAdapter;
import co.nextix.jardine.activites.fragments.detailedact.JDIProductStockCheckInfoFragment;
import co.nextix.jardine.activities.add.fragments.AddJDIMerchandisingStockFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.JDImerchandisingCheckTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class JDIMerchandisingCheckFragment extends Fragment {
	
	private JDIMerchandisingCheckCustomAdapter adapter = null;
	private ArrayList<JDImerchandisingCheckRecord> realRecord = null;
	private ArrayList<JDImerchandisingCheckRecord> tempRecord = null;
	private ArrayList<JDImerchandisingCheckRecord> itemSearch = null;
	private Context CustomListView = null;
	private View myFragmentView = null;
	private ListView list = null;

	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;
	
	public JDIMerchandisingCheckFragment() {
		this.itemSearch = new ArrayList<JDImerchandisingCheckRecord>();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/

		this.myFragmentView = inflater.inflate(R.layout.fragment_activity_jdi_merchandising_check, container, false);
		setListData();
		
		((Button) this.myFragmentView.findViewById(R.id.add_btn_jdi_merchandising_check)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));

				android.support.v4.app.Fragment newFragment = new AddJDIMerchandisingStockFragment();

				// Create new transaction
				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Replace whatever is in the fragment_container view with this
				// fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.activity_fragment, newFragment);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
			}
		});
		
//		((EditText) this.rootView.findViewById(R.id.search_activities)).setOnEditorActionListener(new OnEditorActionListener() {
//
//			@Override
//			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//				if (actionId == EditorInfo.IME_ACTION_DONE) {
//					ActivityTable table = JardineApp.DB.getActivity();
//					List<ActivityRecord> records = table.getAllRecords();
//
//					itemSearch.clear();
//					// Getting the position of the spinner
//					for (int i = 0; i < records.size(); i++) {
//						if (searchItem.equals(getActivity().getResources().getString(R.string.crm_no))
//								&& v.getText().toString().equals(records.get(i).getCrm())) {
//
//							itemSearch.add(records.get(i));
////						} else if (searchItem.equals(getActivity().getResources().getString(R.string.workplan_info_workplan))
////								&& v.getText().toString().equals(String.valueOf(records.get(i).getWorkplan()))) {
////
////							itemSearch.add(records.get(i));
//						} else if (searchItem.equals(getActivity().getResources().getString(R.string.activity_type))
//								&& v.getText().toString().equals(String.valueOf(records.get(i).getActivityType()))) {
//
//							itemSearch.add(records.get(i));
//						} else if (searchItem.equals(getActivity().getResources().getString(R.string.assigned_to))
//								&& v.getText().toString().equals(String.valueOf(records.get(i).getCustomer()))) {
//
//							itemSearch.add(records.get(i));
//						}
//					}
//
//					CustomListView = getActivity().getApplicationContext();
//					list = (ListView) rootView.findViewById(R.id.list);
//					adapter = new StartActivityCustomAdapter(CustomListView, getActivity(), list, itemSearch, StartActivityFragment.this);
//					list.setAdapter(adapter);
//					ListViewUtility.setListViewHeightBasedOnChildren(list);
//				}
//
//				return false;
//			}
//		});

		((ImageButton) this.myFragmentView.findViewById(R.id.left_arrow)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (currentPage > 0) {
					currentPage--;
					addItem(currentPage);
				}
			}
		});

		((ImageButton) this.myFragmentView.findViewById(R.id.right_arrow)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (currentPage < totalPage - 1) {
					currentPage++;
					addItem(currentPage);
				}
			}
		});

		return this.myFragmentView;
	}

	/****** Function to set data in ArrayList *************/
	public void setListData() {
		this.realRecord = new ArrayList<JDImerchandisingCheckRecord>();
		this.tempRecord = new ArrayList<JDImerchandisingCheckRecord>();

		JDImerchandisingCheckTable table = JardineApp.DB.getJDImerchandisingCheck();
		List<JDImerchandisingCheckRecord> records = table.getAllRecords();
		this.realRecord.addAll(records);

		Log.d("Jardine", "ActivityRecord" + String.valueOf(records.size()));

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					JDImerchandisingCheckRecord rec = new JDImerchandisingCheckRecord();
					realRecord.add(rec);
				}
			}

			this.totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		} else {

			this.setView();
			this.isListHasNoData();
			((TextView) this.myFragmentView.findViewById(R.id.status_list_view)).setText("The database is still empty. Wanna sync first?");
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) this.myFragmentView.findViewById(R.id.status_count_text)).setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		this.list = (ListView) this.myFragmentView.findViewById(R.id.list);
		this.adapter = new JDIMerchandisingCheckCustomAdapter(this.CustomListView, getActivity(), list, this.tempRecord, this);
		this.list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	// Event item listener
	public void onItemClick(int mPosition) {
		JDImerchandisingCheckRecord tempValues = (JDImerchandisingCheckRecord) this.tempRecord.get(mPosition);

//		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
//		Editor editor = pref.edit();
//		editor.putLong("activity_id", tempValues.getId());
//		editor.putString("crm_no", tempValues.getCrm());
//		editor.putString("latitude", String.valueOf(tempValues.getLatitude()));
//		editor.putString("longitude", String.valueOf(tempValues.getLongitude()));
//		editor.putString("notes", tempValues.getNotes());
//		editor.putString("competitor_activities", "getCompetitorActivities()");
//		editor.putString("highlights", tempValues.getHighlights());
//		editor.putString("nextSteps", tempValues.getNextSteps());
//		editor.putString("follow_up_commitment_date", tempValues.getFollowUpCommitmentDate());
//		editor.putString("activity_type", String.valueOf(tempValues.getActivityType()));
//		editor.putString("others", "getOthers()");
//		editor.putString("business_unit", "getBusinessUnit()");
//		editor.putString("workplan_entry", String.valueOf(tempValues.getWorkplanEntry()));
//		editor.putString("customer", String.valueOf(tempValues.getCustomer()));
//		editor.putString("area", "getArea()");
//		editor.putString("province", "getProvince");
//		editor.putString("city_town", "getCityTown()");
//		editor.putString("first_time_visit", String.valueOf(tempValues.getFirstTimeVisit()));
//		editor.putString("planned_visit", String.valueOf(tempValues.getPlannedVisit()));
//		editor.putString("reason_remarks", "getReasonRemarks()");
//		editor.putString("details_admin_works", "getDetailsAdminWorks()");
//		editor.putString("source", "getSource()");
//		editor.putString("created_time", tempValues.getCreatedTime());
//		editor.putString(
//				"assigned_to",
//				String.valueOf(JardineApp.DB.getUser().getCurrentUser().getLastname() + ", "
//						+ JardineApp.DB.getUser().getCurrentUser().getFirstNameName()));
//
//		editor.commit(); 

		android.support.v4.app.Fragment fragment = new JDIProductStockCheckInfoFragment();
		android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
				.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
	}

	//TODO Protected before but was changed to public due to some issues
	public void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) this.myFragmentView.findViewById(R.id.view_stub)).setVisibility(View.GONE);
		((TextView) this.myFragmentView.findViewById(R.id.status_list_view)).setVisibility(View.VISIBLE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) this.myFragmentView.findViewById(R.id.view_stub)).setVisibility(View.VISIBLE);
		((TextView) this.myFragmentView.findViewById(R.id.status_list_view)).setVisibility(View.INVISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}


}