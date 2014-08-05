package co.nextix.jardine.activites.fragments.edit;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.update.fragments.UpdateGeneralInformationFragment;

public class ActivityEditInfoDetailsFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		View rootView = inflater.inflate(R.layout.fragment_edit_activity, container, false);

		((Button) rootView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				android.support.v4.app.Fragment newFragment = new EditActivityInfoFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}
		});
		return rootView;
	}
}
