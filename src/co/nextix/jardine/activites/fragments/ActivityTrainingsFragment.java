package co.nextix.jardine.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.nextix.jardine.R;

public class ActivityTrainingsFragment extends Fragment {
	private static volatile ActivityTrainingsFragment instance = null;
	private boolean flag = false;

	public static ActivityTrainingsFragment getInstance() {
		if (instance == null) {
			synchronized (ActivityTrainingsFragment.class) {
				// Double check
				if (instance == null) {
					instance = new ActivityTrainingsFragment();
				}
			}
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.view_activity_trainings, container, false);


		return rootView;
	}
}
