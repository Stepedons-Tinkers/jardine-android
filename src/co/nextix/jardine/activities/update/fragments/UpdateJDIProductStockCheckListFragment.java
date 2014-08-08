package co.nextix.jardine.activities.update.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import co.nextix.jardine.activites.fragments.adapters.JDIProductStockCheckCustomAdapter;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class UpdateJDIProductStockCheckListFragment extends Fragment {
	
	private View view;
	
	private boolean flag = false;
	
	private Button bDone;
	private Button bAddJdiProductStockCheck;
	
	private Context CustomListView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;
	
	private List<JDIproductStockCheckRecord> realRecord = null;
	private ArrayList<JDIproductStockCheckRecord> tempRecord = null;
	
	private JDIProductStockCheckCustomAdapter adapter = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_jdi_product_stock_list, container, false);
		
		bDone = (Button) view.findViewById(R.id.jdiprodadd_button_next);
		bDone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				UpdateFragment.pager.setCurrentItem(4);
			}
		});
		
		bAddJdiProductStockCheck = (Button) view.findViewById(R.id.update_add_jdi_product_stock_check);
		bAddJdiProductStockCheck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment fragment = new UpdateAddJDIProductStockCheckFragment();
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.update_add_jdi_product_stock_check_fake_layout, fragment);
				ft.addToBackStack("to_add_jdi_product");
				ft.commit();
			}
		});
		
		setListData();
		
		return view;
	}
	
	public void setListData() {
		realRecord = JardineApp.DB.getJDIproductStockCheck().getAllRecordsByActivityId(UpdateConstants.ACTIVITY_ID);
		tempRecord = new ArrayList<JDIproductStockCheckRecord>();
		
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
		adapter = new JDIProductStockCheckCustomAdapter(
				this.CustomListView, getActivity(), tempRecord, this);
		list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	// Event item listener
	public void onItemClick(int mPosition) {
		JDIproductStockCheckRecord tempValues = (JDIproductStockCheckRecord) tempRecord
				.get(mPosition);
		
		Bundle bundle = new Bundle();
		bundle.putString("crmno", tempValues.getCrm());
		bundle.putString("type_remarks", tempValues.getOtherRemarks());
		bundle.putLong("activity", tempValues.getActivity());
		bundle.putLong("product", tempValues.getId());
		bundle.putLong("status", tempValues.getStockStatus());
		bundle.putLong("supplier", tempValues.getSupplier());
		bundle.putLong("created_by", tempValues.getCreatedBy());
		bundle.putInt("loaded", tempValues.getLoadedOnShelves());
		
		Fragment fragment = new UpdateJDIProductStockCheckDetailFragment();	
		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
				.replace(R.id.update_add_jdi_product_stock_check_fake_layout, fragment).addToBackStack("to_add_jdi_product").commit();

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
}
