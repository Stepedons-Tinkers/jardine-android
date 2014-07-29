package co.nextix.jardine.activites.fragments.detail;

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
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProjectRequirementRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.ProjectRequirementTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PProjReqTypeTable;
import co.nextix.jardine.utils.MyDateUtils;

public class ProjectRequirementsDetailFragment extends Fragment {

	private Bundle bundle;
	private long project_id;
	private ProjectRequirementRecord record;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_project_requirements, container, false);
	
		bundle = getArguments();
		if(bundle != null){
			project_id = bundle.getLong("project_id", 0);
		}
		ProjectRequirementTable project = JardineApp.DB.getProjectRequirement();
		record = project.getById(project_id);
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.record.getCrm());
		
		ActivityTable act = JardineApp.DB.getActivity();
		if(act != null){
			ActivityRecord actRecord = act.getById(this.record.getActivity());
			((TextView) myFragmentView.findViewById(R.id.activity)).setText("");
			if(actRecord != null){
				((TextView) myFragmentView.findViewById(R.id.activity)).setText(actRecord.toString());
			}
		}
		PProjReqTypeTable project_requirements = JardineApp.DB.getProjectRequirementType();
		if(project_requirements != null){
			((TextView) myFragmentView.findViewById(R.id.project_requirement_type)).setText("");
			PicklistRecord project_type = project_requirements.getById((int)this.record.getProjectRequirementType());
			if(project_type != null){
				((TextView) myFragmentView.findViewById(R.id.project_requirement_type)).setText(project_type.toString());
			}
		}
		
		((TextView) myFragmentView.findViewById(R.id.date_needed)).setText(MyDateUtils.convertDate(this.record.getDateNeeded()));
		((TextView) myFragmentView.findViewById(R.id.square_meters)).setText(this.record.getSquareMeters());
		((TextView) myFragmentView.findViewById(R.id.product_brand)).setText(this.record.getProductsBrand());
		((TextView) myFragmentView.findViewById(R.id.created_time)).setText(MyDateUtils.convertDateTime(this.record.getCreatedTime()));
		((TextView) myFragmentView.findViewById(R.id.modified_time)).setText(MyDateUtils.convertDateTime(this.record.getModifiedTime()));
		
		UserTable user = JardineApp.DB.getUser();
		if(user != null){
			((TextView) myFragmentView.findViewById(R.id.created_by)).setText("");
			UserRecord userRecord = user.getById(this.record.getCreatedBy());
			if(userRecord != null){
				((TextView) myFragmentView.findViewById(R.id.created_by)).setText(userRecord.toString());
			}
		}
		
		((TextView) myFragmentView.findViewById(R.id.other_remarks)).setText(this.record.getOtherDetails());
//		

//		((Button) myFragmentView.findViewById(R.id.back_button)).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				getActivity().onBackPressed();
//				
//			}
//		});

		
		((Button) myFragmentView.findViewById(R.id.edit_activity)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				ActivitiesConstant.ACTIVITY_RECORD = activityRecord;
//				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
//				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();
//				fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//
//				// Add a fucking fragment
//				SaveActivityInfoFragment myFragment = new SaveActivityInfoFragment();
//				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
//				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}

}
