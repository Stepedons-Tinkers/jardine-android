package co.nextix.jardine.activites.fragments;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddCustomerContactPersonFragment;
import co.nextix.jardine.activities.select.fragments.SelectCustomerContactPersonFragment;

public class CustomerContactPersonFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View myFragmentView = inflater.inflate(R.layout.fragment_activity_customer_contact_person, container, false);
		((Button) myFragmentView.findViewById(R.id.add_customer_contact_person)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
//
//				android.support.v4.app.Fragment newFragment = new AddCustomerContactPersonFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
//						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}
		});

		((Button) myFragmentView.findViewById(R.id.select_customer_contact_person)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
//
//				android.support.v4.app.Fragment newFragment = new SelectCustomerContactPersonFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
//						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}
		});

		((ImageButton) myFragmentView.findViewById(R.id.imageButton1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "<==== ni sud here", Toast.LENGTH_SHORT).show();
			}
		});

		((ImageButton) myFragmentView.findViewById(R.id.imageButton3)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "ni sud here ====>", Toast.LENGTH_SHORT).show();
			}
		});

		return myFragmentView;
	}
}
