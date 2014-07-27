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
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.UserTable;

public class ProductFocusDetailFragment extends Fragment {

	private long product_id;
	private Bundle bundle;
	private ProductRecord record;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_product_focus, container, false);
		
		bundle = getArguments();
		if(bundle != null){
			product_id = bundle.getLong("product_id", 0);
		}
		
		ProductTable product = JardineApp.DB.getProduct();
		 record = product.getById(product_id);
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.record.getCrm());
		((TextView) myFragmentView.findViewById(R.id.product_number)).setText(this.record.getProductNumber());
		((TextView) myFragmentView.findViewById(R.id.product_brand)).setText(this.record.getProductBrand());
		((TextView) myFragmentView.findViewById(R.id.product_description)).setText(this.record.getProductDescription());
		((TextView) myFragmentView.findViewById(R.id.is_active)).setText(String.valueOf(this.record.getIsActive()));
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
		
		((Button) myFragmentView.findViewById(R.id.back_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
				
			}
		});

		
		((Button) myFragmentView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				ActivitiesConstant.ACTIVITY_RECORD = activityRecord;
//				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
//				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
//				fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//
//				// Add a fucking fragment
//				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
//				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
//				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}

}
