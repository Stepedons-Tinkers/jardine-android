package co.nextix.jardine.activites.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.CustomerContactPersonCustomAdapter;
import co.nextix.jardine.activites.fragments.detail.CustomerContactPersonDetailFragment;

import co.nextix.jardine.activities.add.fragments.AddCompetitorStockCheckFragment;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.tables.CustomerContactTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class CustomerContactPersonFragment extends Fragment {

	private CustomerContactPersonCustomAdapter adapter = null;
	private ArrayList<CustomerContactRecord> realRecord = null;
	private ArrayList<CustomerContactRecord> tempRecord = null;
	private ArrayList<CustomerContactRecord> itemSearch = null;
	private Context CustomListView = null;
	private View myFragmentView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;

	private Bundle bundle;
	private int frag_layout_id;
	
	public CustomerContactPersonFragment() {
		this.itemSearch = new ArrayList<CustomerContactRecord>();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		myFragmentView = inflater.inflate(R.layout.fragment_activity_customer_contact_person, container, false);
		setListData();
		
//		bundle = getArguments();
		
//		if(bundle != null){
//			frag_layout_id = bundle.getInt("layoutID");
//		}
		
		bundle = new Bundle();
		
		frag_layout_id = ActivityInfoFragment.fragmentLayout_2id;
		
		// ONCLICK sa mga buttons sa fragment
		((Button) myFragmentView.findViewById(R.id.add_customer_contact_person)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));

				android.support.v4.app.Fragment newFragment = new AddCompetitorStockCheckFragment(CustomerContactPersonFragment.this);

				// Create new transaction
				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
				
				// Replace whatever is in the fragment_container view with this
				// fragment,
				// and add the transaction to the back stack
				transaction.replace(frag_layout_id, newFragment);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
			}
		});

		 ((ImageButton)
		 myFragmentView.findViewById(R.id.imageButton1)).setOnClickListener(new
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
		 myFragmentView.findViewById(R.id.imageButton3)).setOnClickListener(new
		 OnClickListener() {
		
		 @Override
		 public void onClick(View v) {
				if (currentPage < totalPage - 1) {
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
		this.realRecord = new ArrayList<CustomerContactRecord>();
		this.tempRecord = new ArrayList<CustomerContactRecord>();

		CustomerContactTable table = JardineApp.DB.getCustomerContact();
		List<CustomerContactRecord> records = table.getAllRecords();
		this.realRecord.addAll(records);

		Log.d("Jardine", "CustomerContactPerson" + String.valueOf(records.size()));

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					CustomerContactRecord rec = new CustomerContactRecord();
					realRecord.add(rec);
				}
			}

			this.totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		} else {

			this.setView();
			this.isListHasNoData();
//			((TextView) this.myFragmentView.findViewById(R.id.status_list_view)).setText("The database is still empty. Wanna sync first?");
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
//		Toast.makeText(getActivity(), count+" "+realRecord.size()+" ", Toast.LENGTH_LONG).show();
		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		this.list = (ListView) this.myFragmentView.findViewById(R.id.list);
		this.adapter = new CustomerContactPersonCustomAdapter(CustomListView,getActivity(),list,this.tempRecord,this);
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
		CustomerContactRecord tempValues = (CustomerContactRecord) this.tempRecord.get(mPosition);

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

		Fragment fragment = new CustomerContactPersonDetailFragment();
		bundle.putLong("customer_id", tempValues.getId());
		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
				.replace(frag_layout_id, fragment).addToBackStack(null).commit();

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
