package co.nextix.jardine.activites.fragments.detail;

import android.content.SharedPreferences;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
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
import co.nextix.jardine.activites.fragments.backup.MoreActivityInformationFragment;
import co.nextix.jardine.activites.fragments.backup.StaticActivityInfoFragment;
import co.nextix.jardine.activities.add.fragments.ActivitiesConstant;
import co.nextix.jardine.activities.update.fragments.SaveActivityInfoFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.CompetitorProductStockCheckTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PComptProdStockStatusTable;

public class CompetitorProductStockCheckDetailFragment extends Fragment {

	private long product_id;
	private Bundle bundle;
	private CompetitorProductStockCheckRecord record;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_competitor_stock_check, container, false);

		bundle = getArguments();
		
		if(bundle != null){
			product_id = bundle.getLong("product_id", 0);
		}
		
		CompetitorProductStockCheckTable product = JardineApp.DB.getCompetitorProductStockCheck();
		record = product.getById(product_id);
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.record.getCrm());
		
		ActivityTable act = JardineApp.DB.getActivity();
		if(act != null){
			ActivityRecord actRecord =  act.getById(this.record.getActivity());
			((TextView) myFragmentView.findViewById(R.id.activity)).setText("");
			if(actRecord != null){
				((TextView) myFragmentView.findViewById(R.id.activity)).setText(actRecord.toString());
			}
			
		}
		
		CompetitorProductTable competitor = JardineApp.DB.getCompetitorProduct();
		if(competitor != null){
			CompetitorProductRecord compRecord = competitor.getById(this.record.getCompetitorProduct());
			((TextView) myFragmentView.findViewById(R.id.competitor_product)).setText("");
			if(compRecord != null){
				((TextView) myFragmentView.findViewById(R.id.competitor_product)).setText(compRecord.toString());
			}
		}
		
		 PComptProdStockStatusTable competitor_status = JardineApp.DB.getCompetitorProductStockStatus();
			if(competitor_status != null){
				PicklistRecord statusRecord = competitor_status.getById((int)this.record.getStockStatus());
				((TextView) myFragmentView.findViewById(R.id.stock_status)).setText("");
				if(statusRecord != null){
					((TextView) myFragmentView.findViewById(R.id.stock_status)).setText(statusRecord.toString());
				}
			}
		
		((TextView) myFragmentView.findViewById(R.id.loaded_on_shelves)).setText(String.valueOf(this.record.getLoadedOnShelves()));
		((TextView) myFragmentView.findViewById(R.id.created_time)).setText(this.record.getCreatedTime());
		((TextView) myFragmentView.findViewById(R.id.modified_time)).setText(this.record.getModifiedTime());
		
		UserTable user = JardineApp.DB.getUser();
		if(user != null){
			UserRecord userRecord = user.getById(this.record.getCreatedBy());
			((TextView) myFragmentView.findViewById(R.id.created_by)).setText("");
			if(userRecord != null){
				((TextView) myFragmentView.findViewById(R.id.created_by)).setText(userRecord.toString());
			}
		}
		
		((TextView) myFragmentView.findViewById(R.id.other_remarks)).setText(this.record.getOtherRemarks());
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
