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
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.JDImerchandisingCheckTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PJDImerchCheckStatusTable;
import co.nextix.jardine.utils.MyDateUtils;

public class JDIMerchandisingCheckDetailFragment extends Fragment {

	private long merchandising_id;
	private Bundle bundle;
	private JDImerchandisingCheckRecord record;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_jdi_merchandising_check, container, false);
		bundle = getArguments();
		if(bundle != null){
			merchandising_id = bundle.getLong("merchandising_id", 0);
		}
		
		JDImerchandisingCheckTable merchandising = JardineApp.DB.getJDImerchandisingCheck();
		record = merchandising.getById(merchandising_id);		
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.record.getCrm());
		
		ActivityTable activity = JardineApp.DB.getActivity();
		if(activity != null){
			((TextView) myFragmentView.findViewById(R.id.customer)).setText("");
			ActivityRecord actRecord = activity.getById(this.record.getActivity());
			if(actRecord != null){
				((TextView) myFragmentView.findViewById(R.id.customer)).setText(actRecord.toString());
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
		
		PJDImerchCheckStatusTable status = JardineApp.DB.getJDImerchCheckStatus();
		if(status != null){
			PicklistRecord statusStock = status.getById((int)this.record.getStatus());
			((TextView) myFragmentView.findViewById(R.id.status_stock)).setText("");
			if(statusStock != null){
				((TextView) myFragmentView.findViewById(R.id.status_stock)).setText(statusStock.toString());
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
