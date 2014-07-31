package co.nextix.jardine.activities.add.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;

import com.dd.CircularProgressButton;

public class AddMarketingIntelFragment extends Fragment {

	private ArrayAdapter<CompetitorProductRecord> competitorProductAdapter = null;
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();
		List<CompetitorProductRecord> competitorProductLsit = JardineApp.DB.getCompetitorProduct().getAllRecords();

		this.competitorProductAdapter = new ArrayAdapter<CompetitorProductRecord>(JardineApp.context, R.layout.add_activity_textview,
				competitorProductLsit);

		final View view = inflater.inflate(R.layout.fragment_activity_add_marketing_intel, container, false);
		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

		long id = pref.getLong("activity_id_edit", 0);
		MarketingIntelRecord marketingIntel = JardineApp.DB.getMarketingIntel().getById(id);

		if (marketingIntel != null) {
			String marketingIntelCreatedBy = JardineApp.DB.getUser().getById(marketingIntel.getCreatedBy()).toString();
			String marketingIntelCrmNo = marketingIntel.getCrm();
			String marketingActivity = String.valueOf(marketingIntel.getActivity());
			long marketingCompetitorProduct = marketingIntel.getCompetitorProduct();
			String marketingDetails = marketingIntel.getDetails();

			if (marketingIntelCreatedBy != null && !marketingIntelCreatedBy.isEmpty() && marketingIntelCrmNo != null
					&& !marketingIntelCrmNo.isEmpty() && marketingActivity != null && !marketingActivity.isEmpty()
					&& marketingCompetitorProduct != 0 && marketingDetails != null && !marketingDetails.isEmpty()) {

				((TextView) view.findViewById(R.id.created_by)).setText(marketingIntelCrmNo);
				((TextView) view.findViewById(R.id.crm_no)).setText(marketingIntelCrmNo);
				((EditText) view.findViewById(R.id.activity)).setText(marketingActivity);

				for (int i = 0; i < competitorProductLsit.size(); i++) {
					if (marketingIntel.getCompetitorProduct() == competitorProductLsit.get(i).getId()) {
						((Spinner) view.findViewById(R.id.competitor_product)).setSelection(i);
						break;
					}
				}

				((EditText) view.findViewById(R.id.details)).setText(marketingDetails);

			}
		} else {

			((Spinner) view.findViewById(R.id.customer)).setAdapter(this.competitorProductAdapter);
			((TextView) view.findViewById(R.id.activity)).setText("AUTO_GEN_ON_SAVE");
			((TextView) view.findViewById(R.id.activity)).setEnabled(false);
			((TextView) view.findViewById(R.id.activity)).setClickable(false);

			((TextView) view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);
			((TextView) view.findViewById(R.id.created_by)).setEnabled(false);
			((TextView) view.findViewById(R.id.created_by)).setClickable(false);
		}

		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {

							Integer value = (Integer) animation.getAnimatedValue();
							((CircularProgressButton) v).setProgress(value);

							if (!flag) {

								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					String details = ((EditText) view.findViewById(R.id.details)).getText().toString();
					long competitorProduct = ((CompetitorProductRecord) ((Spinner) view.findViewById(R.id.competitor_product))
							.getSelectedItem()).getId();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

					if (details != null && !details.isEmpty() && competitorProduct != 0) {
						flag = true;
						Editor editor = pref.edit();
						editor.putString("details", details);
						editor.putLong("competitor_product_marketing_intel", competitorProduct);
						editor.commit(); // commit changes

						v.setClickable(true);
						v.setEnabled(true);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);

					if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retail
						DashBoardActivity.tabIndex.add(8, 16);
						AddActivityFragment.pager.setCurrentItem(16);
					} else if (AddActivityGeneralInformationFragment.ActivityType == 9) { // ki
																							// visits
						DashBoardActivity.tabIndex.add(4, 11);
						AddActivityFragment.pager.setCurrentItem(11);
					}
				}
			}
		});

		return view;
	}
}
