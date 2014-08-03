package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;
import java.util.List;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.MarketingIntelFragment;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class AddMarketingIntelFragment extends Fragment {

	OnHeadlineSelectedListener mCallback;

	private ArrayAdapter<CompetitorProductRecord> competitorProductAdapter = null;
	private boolean flag = false;
	private CircularProgressButton btn = null;
	private Fragment frag = null;

	public AddMarketingIntelFragment(Fragment frag) {
		this.frag = frag;
	}

	// Container Activity must implement this interface
	public interface OnHeadlineSelectedListener {
		public void onArticleSelected(ArrayList<MarketingIntelRecord> rec);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (OnHeadlineSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String assignedToFname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getLastname();

		List<CompetitorProductRecord> competitorProductLsit = JardineApp.DB.getCompetitorProduct().getAllRecords();

		this.competitorProductAdapter = new ArrayAdapter<CompetitorProductRecord>(JardineApp.context, R.layout.add_activity_textview,
				competitorProductLsit);

		final View view = inflater.inflate(R.layout.fragment_activity_add_marketing_intel, container, false);
		// SharedPreferences pref =
		// getActivity().getApplicationContext().getSharedPreferences("ActivityInfo",
		// 0);
		//
		// long id = pref.getLong("activity_id_edit", 0);
		// MarketingIntelRecord marketingIntel =
		// JardineApp.DB.getMarketingIntel().getById(id);
		//
		// if (marketingIntel != null) {
		// String marketingIntelCreatedBy = null;
		// String marketingIntelCrmNo = null;
		// String marketingActivity = null;
		// long marketingCompetitorProduct = 0;
		// String marketingDetails = null;
		//
		// try {
		// marketingIntelCreatedBy =
		// JardineApp.DB.getUser().getById(marketingIntel.getCreatedBy()).toString();
		// marketingIntelCrmNo = marketingIntel.getCrm();
		// marketingActivity = String.valueOf(marketingIntel.getActivity());
		// marketingCompetitorProduct = marketingIntel.getCompetitorProduct();
		// marketingDetails = marketingIntel.getDetails();
		//
		// } catch (Exception e) {
		//
		// }
		//
		// if (marketingIntelCreatedBy != null || marketingIntelCrmNo != null ||
		// marketingActivity != null
		// || marketingCompetitorProduct != 0 || marketingDetails != null) {
		//
		// ((TextView)
		// view.findViewById(R.id.created_by)).setText(marketingIntelCrmNo);
		// ((TextView)
		// view.findViewById(R.id.crm_no)).setText(marketingIntelCrmNo);
		// ((EditText)
		// view.findViewById(R.id.activity)).setText(marketingActivity);
		//
		// for (int i = 0; i < competitorProductLsit.size(); i++) {
		// if (marketingIntel.getCompetitorProduct() ==
		// competitorProductLsit.get(i).getId()) {
		// ((Spinner)
		// view.findViewById(R.id.competitor_product)).setSelection(i);
		// break;
		// }
		// }
		//
		// ((EditText)
		// view.findViewById(R.id.details)).setText(marketingDetails);
		//
		// }
		//
		// } else {

		((Spinner) view.findViewById(R.id.competitor_product)).setAdapter(this.competitorProductAdapter);
		((TextView) view.findViewById(R.id.activity)).setText("AUTO_GEN_ON_SAVE");
		((TextView) view.findViewById(R.id.activity)).setEnabled(false);
		((TextView) view.findViewById(R.id.activity)).setClickable(false);

		((TextView) view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);
		((TextView) view.findViewById(R.id.created_by)).setEnabled(false);
		((TextView) view.findViewById(R.id.created_by)).setClickable(false);
		// }

		this.btn = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
		this.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btn.setClickable(false);
				btn.setEnabled(false);

				if (btn.getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {

							Integer value = (Integer) animation.getAnimatedValue();
							btn.setProgress(value);

							if (!flag) {

								btn.setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					String details = ((EditText) view.findViewById(R.id.details)).getText().toString();
					String competitorProductString = ((Spinner) view.findViewById(R.id.competitor_product)).getSelectedItem().toString();

					/** Checking of required fields **/

					if (details != null && !details.isEmpty() && competitorProductString != null && !competitorProductString.isEmpty()) {

						flag = true;
						long competitorProduct = ((CompetitorProductRecord) ((Spinner) view.findViewById(R.id.competitor_product))
								.getSelectedItem()).getId();

						MarketingIntelRecord record = new MarketingIntelRecord();
						record.setCrm(((TextView) view.findViewById(R.id.crm_no)).getText().toString());
						record.setCreatedBy(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID));
						record.setDetails(details);
						record.setCompetitorProduct(competitorProduct);
						Constant.addMarketingIntelRecords.add(record);
						
						MarketingIntelFragmentAdd mark = (MarketingIntelFragmentAdd) frag;
						mark.setListData();

						// Calling the activity itself
						mCallback.onArticleSelected(Constant.addMarketingIntelRecords);

						Log.d(JardineApp.TAG, "" + Constant.addMarketingIntelRecords.size());
						btn.setProgress(0);

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								getFragmentManager().popBackStackImmediate();
								btn.setClickable(true);
								btn.setEnabled(true);
							}
						}, 1500);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						btn.setClickable(true);
						btn.setEnabled(true);

					}

				} else {

					btn.setProgress(0);
					btn.setClickable(true);
					btn.setEnabled(true);

				}
			}
		});

		((Button) view.findViewById(R.id.back)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View btn) {
				getFragmentManager().popBackStackImmediate();
			}
		});

		return view;
	}
}
