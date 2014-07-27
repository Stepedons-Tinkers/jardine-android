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

public class JDIProductStockCheckDetailFragment extends Fragment {

	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;
	
	private Bundle bundle;
	private int frag_layout_id;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_activity_detail_jdi_product_stock_check, container, false);
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}
		
		this.pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(pref.getLong("activity_id", 0000));
		
		((TextView) myFragmentView.findViewById(R.id.crm_no)).setText(this.activityRecord.getCrm());
		((TextView) myFragmentView.findViewById(R.id.activity)).setText(this.activityRecord.getCheckIn());
		((TextView) myFragmentView.findViewById(R.id.product)).setText(this.activityRecord.getCheckOut());
		((TextView) myFragmentView.findViewById(R.id.status_stock)).setText(String.valueOf(this.activityRecord.getLatitude()));
		((TextView) myFragmentView.findViewById(R.id.loaded_on_shelves)).setText(String.valueOf(this.activityRecord.getLongitude()));
		((TextView) myFragmentView.findViewById(R.id.supplier)).setText(this.activityRecord.getNotes());
		((TextView) myFragmentView.findViewById(R.id.created_time)).setText("getCompetitorActivities()");
		((TextView) myFragmentView.findViewById(R.id.modified_time)).setText("getCompetitorActivities()");
		((TextView) myFragmentView.findViewById(R.id.created_by)).setText("getCompetitorActivities()");
		((TextView) myFragmentView.findViewById(R.id.other_remarks)).setText("getCompetitorActivities()");
		((Button) myFragmentView.findViewById(R.id.back_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
				
			}
		});
		
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
