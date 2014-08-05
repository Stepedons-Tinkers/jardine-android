package co.nextix.jardine.activities.update.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.ProductSupplierCustomAdapter;
import co.nextix.jardine.activites.fragments.detail.ProductSupplierDetailFragment;
import co.nextix.jardine.activities.add.fragments.AddJDIProductStockFragment;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class UpdateProductSupplierList extends Fragment {
	
	private View view;
	
	private List<ProductSupplierRecord> realRecord = null;
	private ArrayList<ProductSupplierRecord> tempRecord = null;
	private ProductSupplierCustomAdapter adapter = null;
	
	private Context CustomListView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;
	
	private Button bAddProductSupplier;
	private Button bDone;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.update_product_supplier_list, container, false);
		
		bAddProductSupplier = (Button) view.findViewById(R.id.add_btn_jdi_product_stock_check);
		bAddProductSupplier.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment fragment = new UpdateProductSupplierDetail();
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.update_fake_layout, fragment);
				ft.addToBackStack("to_add_supplier");
				ft.commit();
			}
		});
		
		bDone = (Button) view.findViewById(R.id.btnWithText1);
		bDone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UpdateFragment.pager.setCurrentItem(5);
			}
		});
		
		setListData();
		
		return view;
	}
	
	public void setListData(){
		realRecord = JardineApp.DB.getProductSupplier().getAllRecordsByActivity(UpdateConstants.ACTIVITY_ID);
		tempRecord = new ArrayList<ProductSupplierRecord>();
		
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
		adapter = new ProductSupplierCustomAdapter(
				CustomListView, getActivity(), tempRecord, this);
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
		ProductSupplierRecord tempValues = (ProductSupplierRecord) this.tempRecord.get(mPosition);
		
		Bundle bundle = new Bundle();
		bundle.putLong("activity", tempValues.getActivity());
		bundle.putLong("product_brand", tempValues.getProductBrand());
		bundle.putLong("supplier", tempValues.getSupplier());
		bundle.putString("other_remarks", tempValues.getOthersRemarks());
		bundle.putLong("created_by", tempValues.getCreatedBy());
		
		Fragment fragment = new UpdateProductSupplierDetail();
		fragment.setArguments(bundle);
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
		ft.replace(R.id.update_fake_layout, fragment);
		ft.addToBackStack("to_add_supplier");
		ft.commit();

	}
}