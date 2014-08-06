package co.nextix.jardine.activities.update.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.MarketingIntelCustomAdapter;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class UpdateMarketingIntelListFragment extends Fragment {
	
	private View view;
	
	private MarketingIntelCustomAdapter adapter = null;
	private List<MarketingIntelRecord> realRecord = null;
	private ArrayList<MarketingIntelRecord> tempRecord = null;
	
	private Context CustomListView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.update_marketing_intel_list, container, false);
		
		setListData();
		
		return view;
	}
	
	public void setListData() {
		realRecord = JardineApp.DB.getMarketingIntel().getAllRecordsByActivityId(UpdateConstants.ACTIVITY_ID);
		tempRecord = new ArrayList<MarketingIntelRecord>();
		
		if (realRecord.size() > 0) {
			totalPage = realRecord.size() / rowSize;
			addItem(currentPage);
		} else {
			setView();
			isListHasNoData();
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) view.findViewById(R.id.status_count_text)).setText(temp + " of " + totalPage);

		int rows = rowSize;
		if (realRecord.size() < rows)
			rows = realRecord.size();
		for (int j = 0; j < rows; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		CustomListView = getActivity().getApplicationContext();
		list = (ListView) view.findViewById(R.id.list);
		adapter = new MarketingIntelCustomAdapter(this.CustomListView, getActivity(), list, this.tempRecord, this);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	public void onItemClick(int mPosition) {
		MarketingIntelRecord tempValues = (MarketingIntelRecord) tempRecord.get(mPosition);

//		Bundle bundle = new Bundle();
//		bundle.putLong("competitor_product", tempValues.getCompetitorProduct());
//		bundle.putLong("stock_status", tempValues.getStockStatus());
//		bundle.putLong("activity", tempValues.getActivity());
//		bundle.putLong("created_by", tempValues.getCreatedBy());
//		bundle.putInt("loaded", tempValues.getLoadedOnShelves());
//		bundle.putString("other_remarks", tempValues.getOtherRemarks());
//		bundle.putString("crm_no", tempValues.getCrm());
//		
		Fragment fragment = new UpdateMarketingIntelDetailFragment();
//		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
				.replace(R.id.update_mark_fake_layout, fragment).addToBackStack("to_add_mark").commit();
	}

	public void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) view.findViewById(R.id.view_stub)).setVisibility(View.GONE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) view.findViewById(R.id.view_stub)).setVisibility(View.VISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}
}
