package co.nextix.jardine.activites.fragments.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.update.fragments.SaveActivityInfoFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.JDIproductStockCheckTable;
import co.nextix.jardine.database.tables.ProductSupplierTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;

public class JDIProductStockCheckDetailFragment extends Fragment {

//	private ActivityRecord activityRecord = null;
//	private SharedPreferences pref = null;
	
	private Bundle bundle;
	private int frag_layout_id;
	private long product_id;
	JDIproductStockCheckRecord record;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_jdi_product_stock_check, container, false);
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
			product_id = bundle.getLong("product_id", 0);
		}
		
//		this.pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
//		this.activityRecord = JardineApp.DB.getActivity().getById(pref.getLong("activity_id", 0000));
		JDIproductStockCheckTable productTable = JardineApp.DB.getJDIproductStockCheck();
	    record = productTable.getById(product_id);
		
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.record.getCrm());
		
		ActivityTable act = JardineApp.DB.getActivity();
		if(act != null){
			ActivityRecord actRecord = act.getById(this.record.getActivity());
			((TextView) myFragmentView.findViewById(R.id.activity)).setText("");
			if(actRecord != null){
				((TextView) myFragmentView.findViewById(R.id.activity)).setText(actRecord.toString());
			}
		}
		
		ProductTable product = JardineApp.DB.getProduct();
		if(product != null){
			ProductRecord proRecord = product.getById(this.record.getProductBrand());
			((TextView) myFragmentView.findViewById(R.id.product)).setText("");
			if(proRecord != null){
				((TextView) myFragmentView.findViewById(R.id.product)).setText(proRecord.toString());
			}
		}
		
		PJDIprodStatusTable status = JardineApp.DB.getJDIproductStatus();
		if(status != null){
			PicklistRecord statusStock = status.getById((int)this.record.getStockStatus());
			((TextView) myFragmentView.findViewById(R.id.status_stock)).setText("");
			if(statusStock != null){
				((TextView) myFragmentView.findViewById(R.id.status_stock)).setText(statusStock.toString());
			}
		}
		
		((TextView) myFragmentView.findViewById(R.id.loaded_on_shelves)).setText(String.valueOf(this.record.getLoadedOnShelves()));
		
		ProductSupplierTable supplier = JardineApp.DB.getProductSupplier();
		if(supplier != null){
			((TextView) myFragmentView.findViewById(R.id.supplier)).setText("");
			ProductSupplierRecord supplierRecord = supplier.getById(this.record.getSupplier());
			if(supplierRecord != null){
				((TextView) myFragmentView.findViewById(R.id.supplier)).setText(supplierRecord.toString());
			}
		}
		
		((TextView) myFragmentView.findViewById(R.id.created_time)).setText(this.record.getCreatedTime());
		((TextView) myFragmentView.findViewById(R.id.modified_time)).setText(this.record.getModifiedTime());
		
		UserTable user = JardineApp.DB.getUser();
		if(user != null){
			((TextView) myFragmentView.findViewById(R.id.created_by)).setText("");
			UserRecord userRecord = user.getById(this.record.getCreatedBy());
			if(userRecord != null){
				((TextView) myFragmentView.findViewById(R.id.created_by)).setText(userRecord.toString());
			}
		}
		
		((TextView) myFragmentView.findViewById(R.id.other_remarks)).setText(this.record.getOtherRemarks());
		((Button) myFragmentView.findViewById(R.id.back_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
				
			}
		});
		
		((Button) myFragmentView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
				fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragment
				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
				fragmentTransaction.replace(frag_layout_id, myFragment);
				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}
}
