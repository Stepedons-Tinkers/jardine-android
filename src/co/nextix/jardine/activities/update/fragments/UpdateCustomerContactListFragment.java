package co.nextix.jardine.activities.update.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import co.nextix.jardine.R;

public class UpdateCustomerContactListFragment extends Fragment {
	
	private View view;
	
	private boolean flag = false;
	
	private Button bDone;
	private Button bAddCustomerContact;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_customer_contact_list, container, false);
		
		bAddCustomerContact = (Button) view.findViewById(R.id.update_add_customer_contact);
		bAddCustomerContact.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		bDone = (Button) view.findViewById(R.id.update_cust_done);
		bDone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//UpdateFragment.pager.setCurrentItem(3);
			}
		});
		
		return view;
	}
}
