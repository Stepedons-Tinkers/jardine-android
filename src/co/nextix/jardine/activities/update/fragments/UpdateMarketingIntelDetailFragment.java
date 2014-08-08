package co.nextix.jardine.activities.update.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;

import com.dd.CircularProgressButton;

public class UpdateMarketingIntelDetailFragment extends Fragment {
	
	private View view;
	
	private boolean flag = false;
	
	private CircularProgressButton cpbSave;
	private CircularProgressButton cpbCancel;
	
	private String crm_no = null;
	private String details = null;
	
	private long comp_product = 0;
	private long activity = 0;
	private long created_by = 0;

	private TextView tvCreatedBy;
	private TextView tvCrmNo;
	private TextView tvActivty;
	
	private EditText etDetails;
	
	private Spinner sCompProduct;
	
	private List<UserRecord> userList = null;
	private List<CompetitorProductRecord> compProductList = null;
	private List<ActivityRecord> activityList = null;
	
	private ArrayAdapter<CompetitorProductRecord> compProductAdapter = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Bundle bundle = getArguments();
		if(bundle != null){
			crm_no = bundle.getString("crm_no");
			details = bundle.getString("details");
			comp_product = bundle.getLong("competitor_product");
			activity = bundle.getLong("activity");
			created_by = bundle.getLong("created_by");
		}
		
		view = inflater.inflate(R.layout.update_marketing_intel_detail, container, false);
		
		tvCreatedBy = (TextView) view.findViewById(R.id.update_mark_created_by);
		tvCrmNo = (TextView) view.findViewById(R.id.update_mark_crm_no);
		tvActivty = (TextView) view.findViewById(R.id.update_mark_activity);
		
		etDetails = (EditText) view.findViewById(R.id.update_mark_details);
		
		sCompProduct = (Spinner) view.findViewById(R.id.update_mark_competitor_product);
		
		userList = JardineApp.DB.getUser().getAllRecords();
		compProductList = JardineApp.DB.getCompetitorProduct().getAllRecords();
		activityList = JardineApp.DB.getActivity().getAllRecords();
		
		compProductAdapter = new ArrayAdapter<CompetitorProductRecord>(
				JardineApp.context, R.layout.add_activity_textview,
				compProductList);

	// -------------------------------------------------------------------------------------------------------------------------
		sCompProduct.setAdapter(compProductAdapter);
		tvCrmNo.setText(crm_no);
		etDetails.setText(details);
		
		for(int x = 0; x < compProductList.size();x++){
			if(compProductList.get(x).getId() == comp_product){
				sCompProduct.setSelection(x);
			}
		}
		
		for(int y = 0; y < userList.size(); y++){
			if(userList.get(y).getId() == created_by){
				tvCreatedBy.setText(userList.get(y).toString());
			}
		}
		
		for(int z = 0; z < activityList.size(); z++){
			if(activityList.get(z).getId() == activity){
				tvActivty.setText(activityList.get(z).toString());
			}
		}

	// -------------------------------------------------------------------------------------------------------------------------
		cpbCancel = (CircularProgressButton) view.findViewById(R.id.btnWithText2);
		cpbCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager().popBackStack("to_add_mark", FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});
		
		cpbSave = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
		cpbSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (cpbSave.getProgress() == 0) {
					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation
							.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation
							.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

								@Override
								public void onAnimationUpdate(
										ValueAnimator animation) {

									Integer value = (Integer) animation
											.getAnimatedValue();
									cpbSave.setProgress(value);

									if (!flag) {
										cpbSave.setProgress(-1);
									}
								}
							});

					widthAnimation.start();

					String product = sCompProduct.getSelectedItem().toString();
					String detail = etDetails.getText().toString();

					if (detail != null && !detail.isEmpty() && !product.equalsIgnoreCase("- Select -")) {
						flag = true;

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								cpbSave.setClickable(true);
								cpbSave.setEnabled(true);

								// save it to constants then update the DATABASE
								// then notify the listview

								getActivity().getSupportFragmentManager();
								getActivity()
										.getSupportFragmentManager()
										.popBackStack(
												"to_add_mark",
												FragmentManager.POP_BACK_STACK_INCLUSIVE);
							}
						}, 1500);
					} else {
						flag = false;
						Toast.makeText(getActivity(),
								"Please fill up required fields",
								Toast.LENGTH_SHORT).show();
						cpbSave.setClickable(true);
						cpbSave.setEnabled(true);
					}
				} else {
					cpbSave.setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);
				}
			}
		});
		
		return view;
	}
}
