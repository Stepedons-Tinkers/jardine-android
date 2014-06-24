package co.nextix.jardine.activities.update.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import co.nextix.jardine.R;

public class EditActivityInfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(R.layout.fragment_start_activity, container, false);
		((Button) myFragmentView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
				// Add a fucking fragment
				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}

}
