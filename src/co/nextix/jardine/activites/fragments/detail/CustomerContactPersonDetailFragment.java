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
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.CustomerContactTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PCustConPositionTable;

public class CustomerContactPersonDetailFragment extends Fragment {

	private Bundle bundle;
	private long customer_id;
	private CustomerContactRecord record;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_customer_contact_person, container, false);
	
		bundle = getArguments();
		if(bundle != null){
			customer_id = bundle.getLong("customer_id", 0);
		}		
		CustomerContactTable contact = JardineApp.DB.getCustomerContact();
		record = contact.getById(customer_id);
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.record.getCrm());
		((TextView) myFragmentView.findViewById(R.id.first_name)).setText(this.record.getFirstName());
		((TextView) myFragmentView.findViewById(R.id.last_name)).setText(this.record.getLastName());
		
		PCustConPositionTable customer = JardineApp.DB.getCustomerContactPosition();
		if(customer != null){
			PicklistRecord customerRecord = customer.getById(this.record.getPosition());
			((TextView) myFragmentView.findViewById(R.id.position)).setText("");
			if(customerRecord != null){
				((TextView) myFragmentView.findViewById(R.id.position)).setText(customerRecord.toString());
			}
		}
		
		((TextView) myFragmentView.findViewById(R.id.mobile_no)).setText(String.valueOf(this.record.getMobileNo()));

		UserTable user = JardineApp.DB.getUser();
		if(user != null){
			UserRecord userRecord = user.getById(this.record.getCreatedBy());
			((TextView) myFragmentView.findViewById(R.id.created_by)).setText("");
			if(userRecord != null){
				((TextView) myFragmentView.findViewById(R.id.created_by)).setText(userRecord.toString());
			}
		}
		
//		((TextView) myFragmentView.findViewById(R.id.product_brand)).setText(this.activityRecord.getNotes());
//		((TextView) myFragmentView.findViewById(R.id.created_time)).setText(this.activityRecord.getModifiedTime());
//		((TextView) myFragmentView.findViewById(R.id.modified_time)).setText(this.activityRecord.getModifiedTime());
//		((TextView) myFragmentView.findViewById(R.id.other_remarks)).setText("getCompetitorActivities()");
//		((Button) myFragmentView.findViewById(R.id.back_button)).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				getActivity().onBackPressed();
//				
//			}
//		});

		
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
