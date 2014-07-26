package co.nextix.jardine.activities.update.fragments;

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

public class EditActivityInfoFragment extends Fragment {
	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;
	
	private int frag_layout_id;
	private Bundle bundle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_start_activity, container, false);
		this.pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(pref.getLong("activity_id", 0000));
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.activityRecord.getCrm());
//		((TextView) myFragmentView.findViewById(R.id.workplan)).setText(String.valueOf(this.activityRecord.getWorkplan()));
		((TextView) myFragmentView.findViewById(R.id.start_time)).setText(this.activityRecord.getCheckIn());
		((TextView) myFragmentView.findViewById(R.id.end_time)).setText(this.activityRecord.getCheckOut());
		((TextView) myFragmentView.findViewById(R.id.latitude)).setText(String.valueOf(this.activityRecord.getLatitude()));
		((TextView) myFragmentView.findViewById(R.id.longitude)).setText(String.valueOf(this.activityRecord.getLongitude()));
//		((TextView) myFragmentView.findViewById(R.id.objective)).setText(this.activityRecord.getObjectives());
		((TextView) myFragmentView.findViewById(R.id.notes)).setText(this.activityRecord.getNotes());
		((TextView) myFragmentView.findViewById(R.id.competitor_activities)).setText("getCompetitorActivities()");
		((TextView) myFragmentView.findViewById(R.id.highlights)).setText(this.activityRecord.getHighlights());
		((TextView) myFragmentView.findViewById(R.id.nextSteps)).setText(this.activityRecord.getNextSteps());
		((TextView) myFragmentView.findViewById(R.id.follow_up_commitment_date)).setText(String.valueOf(this.activityRecord
				.getFollowUpCommitmentDate()));
		((TextView) myFragmentView.findViewById(R.id.activity_type)).setText(String.valueOf(this.activityRecord.getActivityType()));
		((TextView) myFragmentView.findViewById(R.id.others)).setText(String.valueOf("getOthers()"));
//		((TextView) myFragmentView.findViewById(R.id.business_unit)).setText(String.valueOf(String.valueOf(this.activityRecord
//				.getBusinessUnit())));
		((TextView) myFragmentView.findViewById(R.id.workplan_entry)).setText(String.valueOf(this.activityRecord.getWorkplanEntry()));
		((TextView) myFragmentView.findViewById(R.id.customer)).setText(String.valueOf(this.activityRecord.getCustomer()));
		((TextView) myFragmentView.findViewById(R.id.area)).setText(String.valueOf(this.activityRecord.getArea()));
		((TextView) myFragmentView.findViewById(R.id.province)).setText(String.valueOf(this.activityRecord.getProvince()));
//		((TextView) myFragmentView.findViewById(R.id.city_town)).setText(String.valueOf(this.activityRecord.getCityTown()));
		((TextView) myFragmentView.findViewById(R.id.first_time_visit)).setText(String.valueOf(this.activityRecord.getFirstTimeVisit()));
		((TextView) myFragmentView.findViewById(R.id.planned_visit)).setText(String.valueOf(this.activityRecord.getPlannedVisit()));
		((TextView) myFragmentView.findViewById(R.id.reason_remarks)).setText("getReasons()");
		((TextView) myFragmentView.findViewById(R.id.details_admin_works)).setText("getDetailsAdminWorks()");
//		((TextView) myFragmentView.findViewById(R.id.source)).setText(String.valueOf(this.activityRecord.getSource()));
		((TextView) myFragmentView.findViewById(R.id.created_time)).setText(this.activityRecord.getCreatedTime());
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

				// Add a fucking fragment
				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
				fragmentTransaction.replace(frag_layout_id, myFragment);
				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}
}
