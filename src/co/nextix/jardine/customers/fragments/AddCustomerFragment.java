package co.nextix.jardine.customers.fragments;

import co.nextix.jardine.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AddCustomerFragment extends Fragment 
implements OnClickListener {
	
	private View view;
	private Button save, cancel;
	
	public AddCustomerFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_add_customer, 
				container, false);
		initLayout();
		return view;
	}
	
	private void initLayout() {
		save = (Button) view.findViewById(R.id.btn_save_add_customer);
		cancel = (Button) view.findViewById(R.id.btn_cancel_add_customer);
	}

	@Override
	public void onClick(View v) {
		switch(view.getId()) {
		case R.id.btn_save_add_customer:
			// save new customer data
			break;
		case R.id.btn_cancel_add_customer:
			// cancel adding new customer
			break;
		}
		
	}
}
