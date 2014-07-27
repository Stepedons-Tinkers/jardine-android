package co.nextix.jardine.activites.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.ActivitiesConstant;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.BusinessUnitTable;
import co.nextix.jardine.database.tables.UserTable;

public class ActivityTravelOrWaitingFragment extends Fragment {
	private static volatile ActivityTravelOrWaitingFragment instance = null;
	private boolean flag = false;
	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;
	
	private int frag_layout_id;
	private Bundle bundle;

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

		this.pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(pref.getLong("activity_id", 0000));
		
		((TextView) rootView.findViewById(R.id.textView1)).setText("");
		if(activityRecord != null){
			((TextView) rootView.findViewById(R.id.textView1)).setText(this.activityRecord.getReasonRemarks());
		}
		
		
		

//		((Button) rootView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				ActivitiesConstant.ACTIVITY_RECORD = activityRecord;
//				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
//				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
//				fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//
//				// Add a fucking fragmentTODO
////				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
////				fragmentTransaction.replace(frag_layout_id, myFragment);
////				fragmentTransaction.commit();
//			}
//		});
		
		
		return rootView;
	}
}
