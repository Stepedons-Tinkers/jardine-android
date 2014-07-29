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
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.BusinessUnitTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.utils.MyDateUtils;

public class ActivityGeneralInfoFragment extends Fragment {
	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;

	private int frag_layout_id;
	private Bundle bundle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_general_information, container, false);
		this.pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(pref.getLong("activity_id", 0));
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.activityRecord.getCrm() != null ? this.activityRecord.getCrm() : "");
		((TextView) myFragmentView.findViewById(R.id.start_time)).setText(MyDateUtils.convertDateTime(this.activityRecord.getCheckIn()));
		((TextView) myFragmentView.findViewById(R.id.end_time)).setText(MyDateUtils.convertDateTime(this.activityRecord.getCheckOut()));

	
		ActivityTypeTable type =  JardineApp.DB.getActivityType();
		if(type != null){
			ActivityTypeRecord record = type.getById(this.activityRecord.getActivityType());
			((TextView) myFragmentView.findViewById(R.id.activity_type)).setText("");
			if(record != null){
				((TextView) myFragmentView.findViewById(R.id.activity_type)).setText(record.toString());
			}
			
		}
		
		BusinessUnitTable business = JardineApp.DB.getBusinessUnit();
		if(business != null){
			BusinessUnitRecord rec = business.getById(this.activityRecord.getBusinessUnit());
			((TextView) myFragmentView.findViewById(R.id.business_unit)).setText("");
			if(rec != null){
				((TextView) myFragmentView.findViewById(R.id.business_unit)).setText(rec.toString());
			}
		}
		
		UserTable user = JardineApp.DB.getUser();
		if(user != null){
			UserRecord rec = user.getById(this.activityRecord.getCreatedBy());
			((TextView) myFragmentView.findViewById(R.id.assigned_to)).setText("");
			if(rec != null){
				((TextView) myFragmentView.findViewById(R.id.assigned_to)).setText(rec.toString());
			}
		}

		((Button) myFragmentView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bun = new Bundle();
				bun.putLong("activityID", activityRecord.getId());
				ActivitiesConstant.ACTIVITY_RECORD = activityRecord;
				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
				fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragmentTODO
				AddActivityFragment myFragment = new AddActivityFragment();
				myFragment.setArguments(bun);
				fragmentTransaction.replace(frag_layout_id, myFragment);
				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}
}
