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
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.CustomerTable;

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
		
		
		TextView notes = (TextView)myFragmentView.findViewById(R.id.notes);
		TextView highlights = (TextView)myFragmentView.findViewById(R.id.highlights);
		TextView nextsteps = (TextView)myFragmentView.findViewById(R.id.nextSteps);
		TextView commitment_date = (TextView)myFragmentView.findViewById(R.id.follow_up_commitment_date);
		TextView workplan_entry = (TextView)myFragmentView.findViewById(R.id.workplan_entry);
		TextView customer = (TextView)myFragmentView.findViewById(R.id.customer);
		TextView area = (TextView)myFragmentView.findViewById(R.id.area);
		TextView province = (TextView)myFragmentView.findViewById(R.id.province);
		TextView first_time_visit = (TextView) myFragmentView.findViewById(R.id.first_time_visit);
		TextView planned_visit = (TextView) myFragmentView.findViewById(R.id.planned_visit);
		
		
		notes.setText(this.activityRecord.getNotes());
		highlights.setText(this.activityRecord.getHighlights());
		nextsteps.setText(this.activityRecord.getNextSteps());
		commitment_date.setText(String.valueOf(this.activityRecord.getFollowUpCommitmentDate()));
		first_time_visit.setText(String.valueOf(this.activityRecord.getFirstTimeVisit()));
		planned_visit.setText(String.valueOf(this.activityRecord.getPlannedVisit()));

		
		
		ActivityTypeTable type =  JardineApp.DB.getActivityType();
		if(type != null){
			ActivityTypeRecord record = type.getById(this.activityRecord.getActivityType());
			customer.setText(String.valueOf(""));
			if(record != null){
				workplan_entry.setText(String.valueOf(this.activityRecord.getWorkplanEntry()));
			}
			
		}
		
		ActivityTypeTable type1 =  JardineApp.DB.getActivityType();
		if(type != null){
			ActivityTypeRecord record = type.getById(this.activityRecord.getActivityType());
			customer.setText(String.valueOf(""));
			if(record != null){
				customer.setText(String.valueOf(this.activityRecord.getCustomer()));
			}
			
		}
		
		ActivityTypeTable type2 =  JardineApp.DB.getActivityType();
		if(type != null){
			ActivityTypeRecord record = type.getById(this.activityRecord.getActivityType());
			area.setText(String.valueOf(""));
			if(record != null){
				area.setText(String.valueOf(this.activityRecord.getArea()));
			}
			
		}
		province.setText(String.valueOf(this.activityRecord.getProvince()));
		ActivityTypeTable type3 =  JardineApp.DB.getActivityType();
		if(type != null){
			ActivityTypeRecord record = type.getById(this.activityRecord.getActivityType());
			((TextView) myFragmentView.findViewById(R.id.activity_type)).setText("");
			if(record != null){
				((TextView) myFragmentView.findViewById(R.id.activity_type)).setText(record.toString());
			}
			
		}

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
