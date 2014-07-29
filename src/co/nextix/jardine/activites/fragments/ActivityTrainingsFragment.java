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

public class ActivityTrainingsFragment extends Fragment {
	private static volatile ActivityTrainingsFragment instance = null;
	private boolean flag = false;
	private ActivityRecord activityRecord;
	private SharedPreferences pref;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(
				R.layout.view_activity_trainings, container, false);

		this.pref = getActivity().getApplicationContext().getSharedPreferences(
				"ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(
				pref.getLong("activity_id", 0000));

		((TextView) rootView.findViewById(R.id.venue)).setText("");
		if (activityRecord != null) {
			((TextView) rootView.findViewById(R.id.venue))
					.setText(this.activityRecord.getReasonRemarks());
		}

		return rootView;
	}
}
