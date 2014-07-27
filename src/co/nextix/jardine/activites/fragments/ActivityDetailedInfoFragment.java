package co.nextix.jardine.activites.fragments;

import android.content.SharedPreferences;
import android.hardware.Camera.Area;
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
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.WorkplanTable;
import co.nextix.jardine.database.tables.picklists.PAreaTable;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;

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
		TextView city = (TextView) myFragmentView.findViewById(R.id.city_town);
		
		TextView objective = (TextView) myFragmentView.findViewById(R.id.objective);
		
		TextView first_time_visit = (TextView) myFragmentView.findViewById(R.id.first_time_visit);
		TextView planned_visit = (TextView) myFragmentView.findViewById(R.id.planned_visit);
		
		
		notes.setText(this.activityRecord.getNotes());
		highlights.setText(this.activityRecord.getHighlights());
		nextsteps.setText(this.activityRecord.getNextSteps());
		commitment_date.setText(String.valueOf(this.activityRecord.getFollowUpCommitmentDate()));
		first_time_visit.setText(String.valueOf(this.activityRecord.getFirstTimeVisit()));
		planned_visit.setText(String.valueOf(this.activityRecord.getPlannedVisit()));
		objective.setText(String.valueOf(this.activityRecord.getObjective()));
		
		WorkplanTable work =  JardineApp.DB.getWorkplan();
		if(work != null){
			WorkplanRecord record = work.getById(this.activityRecord.getWorkplanEntry());
			workplan_entry.setText("");
			if(record != null){
				workplan_entry.setText(record.toString());
			}
			
		}
		
		CustomerTable cust =  JardineApp.DB.getCustomer();
		if(cust != null){
			CustomerRecord record = cust.getById(this.activityRecord.getCustomer());
			customer.setText("");
			if(record != null){
				customer.setText(record.toString());
			}
			
		}
		
		PAreaTable areaa =  JardineApp.DB.getArea();
		if(areaa != null){
			PicklistRecord record = areaa.getById(this.activityRecord.getArea());
			area.setText("");
			if(record != null){
				area.setText(record.toString());
			}
			
		}
		
		PProvinceTable prov =  JardineApp.DB.getProvince();
		if(prov != null){
			ProvinceRecord record = prov.getById(this.activityRecord.getProvince());
			province.setText("");
			if(record != null){
				province.setText(record.toString());
			}
			
		}
		
		PCityTownTable cit =  JardineApp.DB.getCityTown();
		if(cit != null){
			CityTownRecord record = cit.getById(this.activityRecord.getCity());
			city.setText("");
			if(record != null){
				city.setText(record.toString());
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
