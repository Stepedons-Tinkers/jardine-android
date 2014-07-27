package co.nextix.jardine.activites.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;

public class ActivityBrandActivationFragment extends Fragment {
	private static volatile ActivityBrandActivationFragment instance = null;
	private boolean flag = false;
	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;

	public static ActivityBrandActivationFragment getInstance() {
		if (instance == null) {
			synchronized (ActivityBrandActivationFragment.class) {
				// Double check
				if (instance == null) {
					instance = new ActivityBrandActivationFragment();
				}
			}
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.view_activity_brand_activation, container, false);
		this.pref = getActivity().getApplicationContext().getSharedPreferences(
				"ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(
				pref.getLong("activity_id", 0000));
		
		((TextView) rootView.findViewById(R.id.end_user_activity_type))
		.setText(this.activityRecord.getEndUserActivityTypes());

		return rootView;
	}
}
