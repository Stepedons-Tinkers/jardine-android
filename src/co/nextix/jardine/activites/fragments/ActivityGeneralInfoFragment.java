package co.nextix.jardine.activites.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.ActivitiesConstant;
import co.nextix.jardine.database.records.ActivityRecord;

public class ActivityGeneralInfoFragment extends Fragment {
	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;
	
	private int frag_layout_id;
	private Bundle bundle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_general_information, container, false);
		this.pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(pref.getLong("activity_id", 0000));
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.activityRecord.getCrm());
		((TextView) myFragmentView.findViewById(R.id.start_time)).setText(this.activityRecord.getCheckIn());
		((TextView) myFragmentView.findViewById(R.id.end_time)).setText(this.activityRecord.getCheckOut());

		((TextView) myFragmentView.findViewById(R.id.activity_type)).setText(String.valueOf(this.activityRecord.getActivityType()));

//		((TextView) myFragmentView.findViewById(R.id.created_time)).setText(this.activityRecord.getCreatedTime());
		((TextView) myFragmentView.findViewById(R.id.assigned_to)).setText(String.valueOf(JardineApp.DB.getUser().getCurrentUser()
				.getLastname()
				+ ", " + JardineApp.DB.getUser().getCurrentUser().getFirstNameName()));

		((Button) myFragmentView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivitiesConstant.ACTIVITY_RECORD = activityRecord;
				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
				fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragmentTODO
//				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
//				fragmentTransaction.replace(frag_layout_id, myFragment);
//				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}
}
