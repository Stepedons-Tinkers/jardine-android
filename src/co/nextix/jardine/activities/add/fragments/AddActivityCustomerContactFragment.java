package co.nextix.jardine.activities.add.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import co.nextix.jardine.R;

import com.dd.CircularProgressButton;

public class AddActivityCustomerContactFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.customer_contact_add_new, container, false);
		
		((Button) view.findViewById(R.id.bCustomerContactAddCreate)).setVisibility(View.GONE);
		((Button) view.findViewById(R.id.bCustomerContactAddCancel)).setVisibility(View.GONE);
		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setVisibility(View.VISIBLE);
		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		
		return view;
	}
}
