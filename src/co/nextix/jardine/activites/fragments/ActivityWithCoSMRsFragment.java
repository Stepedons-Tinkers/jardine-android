package co.nextix.jardine.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.nextix.jardine.R;

public class ActivityWithCoSMRsFragment extends Fragment {
	private static volatile ActivityWithCoSMRsFragment instance = null;
	private boolean flag = false;

	public static ActivityWithCoSMRsFragment getInstance() {
		if (instance == null) {
			synchronized (ActivityWithCoSMRsFragment.class) {
				// Double check
				if (instance == null) {
					instance = new ActivityWithCoSMRsFragment();
				}
			}
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.view_activity_with_co_smrs, container, false);


		return rootView;
	}
}
