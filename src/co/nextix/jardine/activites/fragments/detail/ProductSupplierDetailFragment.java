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
import co.nextix.jardine.activities.update.fragments.UpdateFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.JDIproductStockCheckTable;
import co.nextix.jardine.database.tables.ProductSupplierTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;
import co.nextix.jardine.utils.MyDateUtils;

public class ProductSupplierDetailFragment extends Fragment {

	
	private Bundle bundle;
	private int frag_layout_id;
	private long product_id;
	private ProductSupplierRecord record;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_product_supplier, container, false);
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
			product_id = bundle.getLong("product_id", 0);
		}
		
		ProductSupplierTable productTable = JardineApp.DB.getProductSupplier();
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
		
		CustomerTable supplier = JardineApp.DB.getCustomer();
		if(supplier != null){
			CustomerRecord rec = supplier.getById(this.record.getSupplier());
			((TextView) myFragmentView.findViewById(R.id.supplier)).setText("");
			if(rec != null){
				((TextView) myFragmentView.findViewById(R.id.supplier)).setText(rec.toString());
			}
		}

		((TextView) myFragmentView.findViewById(R.id.created_time)).setText(MyDateUtils.convertDateTime(this.record.getCreatedTime()));
		((TextView) myFragmentView.findViewById(R.id.modified_time)).setText(MyDateUtils.convertDateTime(this.record.getModifiedTime()));

		UserTable user = JardineApp.DB.getUser();
		if(user != null){
			((TextView) myFragmentView.findViewById(R.id.created_by)).setText("");
			UserRecord userRecord = user.getById(this.record.getCreatedBy());
			if(userRecord != null){
				((TextView) myFragmentView.findViewById(R.id.created_by)).setText(userRecord.toString());
			}
		}
		
//		
//		((TextView) myFragmentView.findViewById(R.id.other_remarks)).setText(this.record.getOtherRemarks());
//		((Button) myFragmentView.findViewById(R.id.back_button)).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				getActivity().onBackPressed();
//				
//			}
//		});
//		
//		((Button) myFragmentView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
//				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
//				fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//
//				// Add a fucking fragment
//				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
//				fragmentTransaction.replace(frag_layout_id, myFragment);
//				fragmentTransaction.commit();
//			}
//		});

		return myFragmentView;
	}
}
