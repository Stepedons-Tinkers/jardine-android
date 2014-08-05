package co.nextix.jardine.activities.update.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.JDIMerchandisingCheckCustomAdapter;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class UpdateJDIMerchandisingStockCheckListFragment extends Fragment {
	
	private View view;
	
	private JDIMerchandisingCheckCustomAdapter adapter = null;
	private List<JDImerchandisingCheckRecord> realRecord = null;
	private ArrayList<JDImerchandisingCheckRecord> tempRecord = null;
	
	private Context CustomListView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;
	
	private Button bDone;
	private Button bAdd;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.update_jdi_merchandising_stock_check_list, container, false);
		
		bDone = (Button) view.findViewById(R.id.btnWithText1);
		bDone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UpdateFragment.pager.setCurrentItem(6);
			}
		});
		
		bAdd = (Button) view.findViewById(R.id.add_merchandising_stock_check);
		bAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		setListData();
		
		return view;
	}
	
	private void setListData(){
		realRecord = JardineApp.DB.getJDImerchandisingCheck().getAllRecordsByActivity(UpdateConstants.ACTIVITY_ID);
		tempRecord = new ArrayList<JDImerchandisingCheckRecord>();
		
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
		((TextView) view.findViewById(R.id.status_count_text))
				.setText(temp + " of " + totalPage);

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
		adapter = new JDIMerchandisingCheckCustomAdapter(CustomListView, getActivity(), list, tempRecord, this);
		list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}
	
	public void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) view.findViewById(R.id.view_stub))
				.setVisibility(View.GONE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) view.findViewById(R.id.view_stub))
				.setVisibility(View.VISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}
	
	public void onItemClick(int mPosition) {
		JDImerchandisingCheckRecord tempValues = (JDImerchandisingCheckRecord) tempRecord.get(mPosition);
		
		Bundle bundle = new Bundle();
		bundle.putString("crm_no", tempValues.getCrm());
		bundle.putLong("activity", tempValues.getActivity());
		bundle.putLong("product", tempValues.getProductBrand());
		bundle.putLong("status", tempValues.getStatus());
		bundle.putLong("created_by", tempValues.getCreatedBy());
		
		Fragment fragment = new UpdateJDIMerchandisingStockCheckDetailFragment();
		fragment.setArguments(bundle);
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
		ft.replace(R.id.update_jdi_fake_layout, fragment);
		ft.addToBackStack("to_add_jdi_merchandising");
		ft.commit();

	}
}
