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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class AddCompetitorStockCheckFragment extends Fragment {
	private ArrayAdapter<CompetitorProductRecord> competitorStockAdapter = null;
	private ArrayAdapter<PicklistRecord> jdiCompetitorStockCheckAdapter = null;
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<CompetitorProductRecord> competitorStockCheckList = JardineApp.DB.getCompetitorProduct().getAllRecords();
		List<PicklistRecord> jdiCompetitorStockCheckList = JardineApp.DB.getCompetitorProductStockStatus().getAllRecords();

		String assignedToFname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getLastname();

		this.competitorStockAdapter = new ArrayAdapter<CompetitorProductRecord>(JardineApp.context, R.layout.add_activity_textview,
				competitorStockCheckList);

		this.jdiCompetitorStockCheckAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview,
				jdiCompetitorStockCheckList);

		final View view = inflater.inflate(R.layout.fragment_activity_add_competitor_product_stock_check, container, false);
		// SharedPreferences pref =
		// getActivity().getApplicationContext().getSharedPreferences("ActivityInfo",
		// 0);
		//
		// long id = pref.getLong("activity_id_edit", 0);
		// CompetitorProductStockCheckRecord jdiCompetitorStockCheck =
		// JardineApp.DB.getCompetitorProductStockCheck().getById(id);
		//
		// if (jdiCompetitorStockCheck != null) {
		// String jdiCompetitorProductCrmNo = null;
		// String jdiCompetitorProductActivity = null;
		// int jdiCompetitorProduct = 0;
		// int jdiCompetitorStockStatus = 0;
		// int jdiCompetitorLoadedOnShelves = 0;
		// String jdiCompetitorCreatedBy = null;
		// String jdiCompetitorOtherTypeRemarks = null;
		//
		// try {
		// jdiCompetitorProductCrmNo = jdiCompetitorStockCheck.getCrm();
		// jdiCompetitorProductActivity =
		// String.valueOf(jdiCompetitorStockCheck.getActivity());
		// jdiCompetitorProduct =
		// Integer.parseInt(String.valueOf(jdiCompetitorStockCheck.getCompetitorProduct()));
		// jdiCompetitorStockStatus =
		// Integer.parseInt(String.valueOf(jdiCompetitorStockCheck.getStockStatus()));
		// jdiCompetitorLoadedOnShelves =
		// jdiCompetitorStockCheck.getLoadedOnShelves();
		// jdiCompetitorCreatedBy =
		// JardineApp.DB.getUser().getById(jdiCompetitorStockCheck.getCreatedBy()).toString();
		// jdiCompetitorOtherTypeRemarks =
		// jdiCompetitorStockCheck.getOtherRemarks();
		//
		// } catch (Exception e) {
		//
		// }
		//
		// if (jdiCompetitorProductCrmNo != null || jdiCompetitorProductActivity
		// != null || jdiCompetitorProduct != 0
		// || jdiCompetitorStockStatus != 0 || jdiCompetitorLoadedOnShelves !=
		// -1 || jdiCompetitorCreatedBy != null
		// || jdiCompetitorOtherTypeRemarks != null) {
		//
		// ((TextView)
		// view.findViewById(R.id.crm_no)).setText(jdiCompetitorProductCrmNo);
		// ((TextView)
		// view.findViewById(R.id.activity)).setText(jdiCompetitorProductActivity);
		// ((CheckBox)
		// view.findViewById(R.id.loaded_on_shelves)).setChecked(true);
		// ((TextView)
		// view.findViewById(R.id.created_by)).setText(jdiCompetitorCreatedBy);
		// ((EditText)
		// view.findViewById(R.id.other_remarks)).setText(jdiCompetitorOtherTypeRemarks);
		//
		// for (int i = 0; i < competitorStockCheckList.size(); i++) {
		// if (jdiCompetitorStockCheck.getCompetitorProduct() ==
		// competitorStockCheckList.get(i).getId()) {
		// ((Spinner)
		// view.findViewById(R.id.competitor_product)).setSelection(i);
		// break;
		// }
		// }
		//
		// for (int i = 0; i < jdiCompetitorStockCheckList.size(); i++) {
		// if (jdiCompetitorStockCheck.getStockStatus() ==
		// jdiCompetitorStockCheckList.get(i).getId()) {
		// ((Spinner) view.findViewById(R.id.stock_status)).setSelection(i);
		// break;
		// }
		// }
		//
		// }
		// } else {

		((Spinner) view.findViewById(R.id.competitor_product)).setAdapter(this.competitorStockAdapter);
		((Spinner) view.findViewById(R.id.stock_status)).setAdapter(this.jdiCompetitorStockCheckAdapter);

		((TextView) view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);
		((TextView) view.findViewById(R.id.activity)).setText("AUTO_GEN_ON_SAVE");

		// Disable fields
		((TextView) view.findViewById(R.id.activity)).setEnabled(false);
		((TextView) view.findViewById(R.id.activity)).setClickable(false);
		((TextView) view.findViewById(R.id.created_by)).setClickable(false);
		((TextView) view.findViewById(R.id.created_by)).setEnabled(false);
		// }

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

					String competitorProduct = ((Spinner) view.findViewById(R.id.competitor_product)).getSelectedItem().toString();

					String stockStatus = ((Spinner) view.findViewById(R.id.stock_status)).getSelectedItem().toString();
					/** Checking of required fields **/

					if (competitorProduct != null && stockStatus != null && !competitorProduct.isEmpty() && !stockStatus.isEmpty()) {

						flag = true;

						long competitorProductLong = ((CompetitorProductRecord) ((Spinner) view.findViewById(R.id.competitor_product))
								.getSelectedItem()).getId();
						long stockStatusLong = ((PicklistRecord) ((Spinner) view.findViewById(R.id.stock_status)).getSelectedItem())
								.getId();
						int loadedOnShelves = ((CheckBox) view.findViewById(R.id.loaded_on_shelves)).isChecked() ? 1 : 0;
						String otherRemarks = ((EditText) view.findViewById(R.id.other_remarks)).getText().toString();

						CompetitorProductStockCheckRecord compStockCheck = new CompetitorProductStockCheckRecord();
						compStockCheck.setCrm(((TextView) view.findViewById(R.id.crm_no)).getText().toString());
						compStockCheck.setCompetitorProduct(((CompetitorProductRecord) ((Spinner) view
								.findViewById(R.id.competitor_product)).getSelectedItem()).getId());

						compStockCheck.setStockStatus(stockStatusLong);
						compStockCheck.setLoadedOnShelves(loadedOnShelves);
						compStockCheck.setCreatedBy(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID));
						compStockCheck.setOtherRemarks(otherRemarks);
						Constant.addCompetitorProductRecords.add(compStockCheck);

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								getFragmentManager().popBackStackImmediate();
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);

					if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retails
						DashBoardActivity.tabIndex.add(7, 10);
						AddActivityFragment.pager.setCurrentItem(10);
					}
				}
			}
		});

		return view;
	}
}
