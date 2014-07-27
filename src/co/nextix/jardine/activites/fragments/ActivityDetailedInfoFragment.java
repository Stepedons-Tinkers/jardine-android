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

public class ActivityDetailedInfoFragment extends Fragment {
	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;
	
	private int frag_layout_id;
	private Bundle bundle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_detailed_information, container, false);
		this.pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(pref.getLong("activity_id", 0000));
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}
		
		((TextView) myFragmentView.findViewById(R.id.notes)).setText(this.activityRecord.getNotes());
		((TextView) myFragmentView.findViewById(R.id.highlights)).setText(this.activityRecord.getHighlights());
		((TextView) myFragmentView.findViewById(R.id.nextSteps)).setText(this.activityRecord.getNextSteps());
		((TextView) myFragmentView.findViewById(R.id.follow_up_commitment_date)).setText(String.valueOf(this.activityRecord
				.getFollowUpCommitmentDate()));
		((TextView) myFragmentView.findViewById(R.id.workplan_entry)).setText(String.valueOf(this.activityRecord.getWorkplanEntry()));
		((TextView) myFragmentView.findViewById(R.id.customer)).setText(String.valueOf(this.activityRecord.getCustomer()));
		((TextView) myFragmentView.findViewById(R.id.area)).setText(String.valueOf(this.activityRecord.getArea()));
		((TextView) myFragmentView.findViewById(R.id.province)).setText(String.valueOf(this.activityRecord.getProvince()));
		((TextView) myFragmentView.findViewById(R.id.first_time_visit)).setText(String.valueOf(this.activityRecord.getFirstTimeVisit()));
		((TextView) myFragmentView.findViewById(R.id.planned_visit)).setText(String.valueOf(this.activityRecord.getPlannedVisit()));

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
