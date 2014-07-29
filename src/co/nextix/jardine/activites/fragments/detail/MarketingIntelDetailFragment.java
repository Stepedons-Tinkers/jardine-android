package co.nextix.jardine.activites.fragments.detail;

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
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.MarketingIntelTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.utils.MyDateUtils;

public class MarketingIntelDetailFragment extends Fragment {

	private Bundle bundle;
	private long marketing_intel_id;
	private MarketingIntelRecord record;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_marketing_intel, container, false);
		
		bundle = getArguments();
		if(bundle != null){
			marketing_intel_id = bundle.getLong("marketing_intel_id",0);
		}
		MarketingIntelTable marketing = JardineApp.DB.getMarketingIntel();
		record = marketing.getById(marketing_intel_id);
		
	
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.record.getCrm());
		ActivityTable act = JardineApp.DB.getActivity();
		if(act != null){
			ActivityRecord actRecord = act.getById(this.record.getActivity());
			((TextView) myFragmentView.findViewById(R.id.activity)).setText("");
			if(actRecord != null){
				((TextView) myFragmentView.findViewById(R.id.activity)).setText(actRecord.toString());
			}
		}
		
		CompetitorProductTable product = JardineApp.DB.getCompetitorProduct();
		if(product != null){
			CompetitorProductRecord productRecord = product.getById(this.record.getCompetitorProduct());
			((TextView) myFragmentView.findViewById(R.id.competitor_product)).setText("");
			if(productRecord != null){
				((TextView) myFragmentView.findViewById(R.id.competitor_product)).setText(productRecord.toString());
			}
		}
		
		((TextView) myFragmentView.findViewById(R.id.details)).setText(this.record.getDetails());		
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
		
		((Button) myFragmentView.findViewById(R.id.back_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
				
			}
		});

		
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
