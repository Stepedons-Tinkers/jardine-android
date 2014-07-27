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
import co.nextix.jardine.database.records.SMRRecord;
import co.nextix.jardine.database.tables.SMRTable;

public class ActivityWithCoSMRsFragment extends Fragment {
	private static volatile ActivityWithCoSMRsFragment instance = null;
	private boolean flag = false;
	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(
				R.layout.view_activity_with_co_smrs, container, false);
		this.pref = getActivity().getApplicationContext().getSharedPreferences(
				"ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(
				pref.getLong("activity_id", 0000));

		
		SMRTable smr = JardineApp.DB.getSMR();
		if (smr != null) {
			SMRRecord rec = smr.getById(this.activityRecord.getSmr());
			((TextView) rootView.findViewById(R.id.smr))
			.setText("");
			if(rec != null){
				((TextView) rootView.findViewById(R.id.smr))
				.setText(rec.getFirstname() + " "+ rec.getLastname());
			}
		}

		return rootView;
	}
}
