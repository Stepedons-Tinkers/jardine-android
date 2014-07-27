package co.nextix.jardine.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.nextix.jardine.R;

public class ActivityTravelOrWaitingFragment extends Fragment {
	private static volatile ActivityTravelOrWaitingFragment instance = null;
	private boolean flag = false;

	public static ActivityTravelOrWaitingFragment getInstance() {
		if (instance == null) {
			synchronized (ActivityTravelOrWaitingFragment.class) {
				// Double check
				if (instance == null) {
					instance = new ActivityTravelOrWaitingFragment();
				}
			}
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.view_activity_travel_waiting, container, false);


		return rootView;
	}
}
