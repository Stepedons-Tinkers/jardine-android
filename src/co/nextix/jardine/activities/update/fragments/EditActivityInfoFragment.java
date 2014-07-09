package co.nextix.jardine.activities.update.fragments;

import java.util.List;

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
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.tables.ActivityTable;

public class EditActivityInfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(R.layout.fragment_start_activity, container, false);
		Bundle args = getArguments();
		if (args != null && args.containsKey("crm_no")) {
			ActivityTable table = JardineApp.DB.getActivity();
			List<ActivityRecord> records = table.getAllRecords();
			
			for (ActivityRecord rec : records) {
				if(rec.getCrm().equals(args.getString("crm_no"))){
					((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(rec.getCrm());
					((TextView) myFragmentView.findViewById(R.id.activity)).setText(String.valueOf(rec.getUser()));
					((TextView) myFragmentView.findViewById(R.id.start_time)).setText(rec.getStartTime());
					((TextView) myFragmentView.findViewById(R.id.end_time)).setText(rec.getEndTime());
					((TextView) myFragmentView.findViewById(R.id.latitude)).setText(String.valueOf(rec.getLatitude()));
					((TextView) myFragmentView.findViewById(R.id.longitude)).setText(String.valueOf(rec.getLongitude()));
					((TextView) myFragmentView.findViewById(R.id.objective)).setText(rec.getObjectives());
					((TextView) myFragmentView.findViewById(R.id.notes)).setText(rec.getNotes());
					((TextView) myFragmentView.findViewById(R.id.highlights)).setText(rec.getHighlights());
					((TextView) myFragmentView.findViewById(R.id.nextSteps)).setText(rec.getNextSteps());
					((TextView) myFragmentView.findViewById(R.id.follow_up_date)).setText(rec.getFollowUpCommitmentDate());
					((TextView) myFragmentView.findViewById(R.id.activity_type)).setText(String.valueOf(rec.getActivityType()));
					((TextView) myFragmentView.findViewById(R.id.workplan_entry)).setText(String.valueOf(rec.getWorkplanEntry()));
					((TextView) myFragmentView.findViewById(R.id.customer)).setText(String.valueOf(rec.getCustomer()));
					((TextView) myFragmentView.findViewById(R.id.first_time)).setText(String.valueOf(rec.getFirstTimeVisit()));
					((TextView) myFragmentView.findViewById(R.id.planned_visit)).setText(String.valueOf(rec.getPlannedVisit()));
					((TextView) myFragmentView.findViewById(R.id.created_time)).setText(rec.getCreatedTime());
					((TextView) myFragmentView.findViewById(R.id.modified_time)).setText(rec.getModifiedTime());
					((TextView) myFragmentView.findViewById(R.id.assigned_to)).setText(String.valueOf(rec.getUser()));
				}
			}
		}

		((Button) myFragmentView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
				fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragment
				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}
}
