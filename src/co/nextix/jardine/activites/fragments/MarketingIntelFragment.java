package co.nextix.jardine.activites.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.MarketingIntelCustomAdapter;
import co.nextix.jardine.activites.fragments.detail.JDIMerchandisingCheckDetailFragment;
import co.nextix.jardine.activites.fragments.detail.MarketingIntelDetailFragment;
import co.nextix.jardine.activities.add.fragments.AddCompetitorStockCheckFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.database.records.ProjectRequirementRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.MarketingIntelTable;
import co.nextix.jardine.database.tables.ProjectRequirementTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class MarketingIntelFragment extends Fragment {

	private MarketingIntelCustomAdapter adapter = null;
	private ArrayList<MarketingIntelRecord> realRecord = null;
	private ArrayList<MarketingIntelRecord> tempRecord = null;
	private ArrayList<MarketingIntelRecord> itemSearch = null;
	private Context CustomListView = null;
	private View myFragmentView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;

	private Bundle bundle;
	private int frag_layout_id;

	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;
	
	public MarketingIntelFragment() {
		this.itemSearch = new ArrayList<MarketingIntelRecord>();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		myFragmentView = inflater.inflate(R.layout.fragment_activity_marketing_intel, container, false);
		setListData();
		
//		bundle = getArguments();
		
//		if(bundle != null){
//			frag_layout_id = bundle.getInt("layoutID");
//		}
		
		bundle = new Bundle();
		
		frag_layout_id = ActivityInfoFragment.fragmentLayout_2id;
		
		// ONCLICK sa mga buttons sa fragment
//		((Button) myFragmentView.findViewById(R.id.add_marketing_intel)).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
//
//				android.support.v4.app.Fragment newFragment = new AddCompetitorStockCheckFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
//						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(frag_layout_id, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
//			}
//		});

		 ((ImageButton)
		 myFragmentView.findViewById(R.id.left_arrow)).setOnClickListener(new
		 OnClickListener() {
		
		 @Override
		 public void onClick(View v) {
				if (currentPage > 0) {
					currentPage--;
					addItem(currentPage);
				}
//		 Toast.makeText(getActivity(), "<==== ni sud here",
//		 Toast.LENGTH_SHORT).show();
		 }
		 });
		
		 ((ImageButton)
		 myFragmentView.findViewById(R.id.right_arrow)).setOnClickListener(new
		 OnClickListener() {
		
		 @Override
		 public void onClick(View v) {
				if (currentPage <  totalPage - 1) {
					currentPage++;
					addItem(currentPage);
				}
//		 Toast.makeText(getActivity(), "ni sud here ====>",
//		 Toast.LENGTH_SHORT).show();
		 }
		 });

		return myFragmentView;
	}
	
	
	public void setListData() {
		this.realRecord = new ArrayList<MarketingIntelRecord>();
		this.tempRecord = new ArrayList<MarketingIntelRecord>();
		
		this.pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(pref.getLong("activity_id", 0));
		
		
		ActivityTable act = JardineApp.DB.getActivity();
		MarketingIntelTable table = JardineApp.DB.getMarketingIntel();
		List<MarketingIntelRecord> records = table.getAllRecords();
		
		for (MarketingIntelRecord rec : records){
			ActivityRecord reca = act.getById(rec.getActivity());
			if(reca != null)
			if (reca.toString().equals(this.activityRecord.getCrm()))
				this.realRecord.add(rec);
		}

//		MarketingIntelTable table = JardineApp.DB.getMarketingIntel();
//		List<MarketingIntelRecord> records = table.getAllRecords();
//		this.realRecord.addAll(records);

		Log.d("Jardine", "ActivityRecord" + String.valueOf(records.size()));

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					MarketingIntelRecord rec = new MarketingIntelRecord();
					realRecord.add(rec);
				}
			}

			this.totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		} else {

			this.setView();
			this.isListHasNoData();
//			((TextView) this.myFragmentView.findViewById(R.id.status_list_view)).setText("");
		}
	}
	
	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) this.myFragmentView.findViewById(R.id.status_count_text))
				.setText(temp + " of " + totalPage);

		int rows = rowSize;
		if (realRecord.size() < rows)
			rows = realRecord.size();
		for (int j = 0; j < rows; j++) {
//		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		this.list = (ListView) this.myFragmentView.findViewById(R.id.list);
		this.adapter = new MarketingIntelCustomAdapter(CustomListView,getActivity(),list,this.tempRecord,this);
//		this.adapter = new MarketingIntelCustomAdapter(this.CustomListView, getActivity(), list, this.tempRecord, this);
		this.list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}
	
	
	public void onItemClick(int mPosition) {
		MarketingIntelRecord tempValues = (MarketingIntelRecord) this.tempRecord.get(mPosition);

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
				
		Fragment fragment = new MarketingIntelDetailFragment();
		bundle.putLong("marketing_intel_id", tempValues.getId());
		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
			.replace(frag_layout_id, fragment).addToBackStack(null).commit();
//		android.support.v4.app.Fragment fragment = new ActivityInfoFragment();
//		android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
//				.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
	}
	
	
	public void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) this.myFragmentView.findViewById(R.id.view_stub)).setVisibility(View.GONE);
//		((TextView) this.myFragmentView.findViewById(R.id.status_list_view)).setVisibility(View.VISIBLE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) this.myFragmentView.findViewById(R.id.view_stub)).setVisibility(View.VISIBLE);
//		((TextView) this.myFragmentView.findViewById(R.id.status_list_view)).setVisibility(View.INVISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}
	
}
