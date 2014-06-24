package co.nextix.jardine.contactperson.fragments;

import co.nextix.jardine.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddContactPersonFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_add_customer_contact_person, 
				container, false);
		return rootView;
	}
}
