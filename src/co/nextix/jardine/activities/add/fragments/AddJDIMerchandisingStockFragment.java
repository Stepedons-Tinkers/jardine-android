package co.nextix.jardine.activities.add.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.JDIMerchandisingCheckFragment;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class AddJDIMerchandisingStockFragment extends Fragment {

	private ArrayAdapter<ProductRecord> productAdapter = null;
	private ArrayAdapter<PicklistRecord> statusAdapter = null;
	private boolean flag = false;

	private Fragment frag = null;

	public AddJDIMerchandisingStockFragment(Fragment frag) {
		this.frag = frag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<ProductRecord> productList = JardineApp.DB.getProduct().getAllRecords();
		List<PicklistRecord> statusList = JardineApp.DB.getJDImerchCheckStatus().getAllRecords();

		String assignedToFname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getLastname();

		this.productAdapter = new ArrayAdapter<ProductRecord>(JardineApp.context, R.layout.add_activity_textview, productList);
		this.statusAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, statusList);

		final View view = inflater.inflate(R.layout.fragment_activity_add_jdi_merchandising_check, container, false);
		// SharedPreferences pref =
		// getActivity().getApplicationContext().getSharedPreferences("ActivityInfo",
		// 0);
		//
		// long id = pref.getLong("activity_id_edit", 0);
		// JDImerchandisingCheckRecord jdiMerchandisingCheck =
		// JardineApp.DB.getJDImerchandisingCheck().getById(id);
		//
		// if (jdiMerchandisingCheck != null) {
		//
		// String jdiMerchandisingCrmNo = null;
		// String jdiMerchandisingActivity = null;
		// int jdiMerchandisingProduct = 0;
		// int jdiMerchandisingStatus = 0;
		// String jdiMerchandisingCreatedBy = null;
		//
		// try {
		//
		// jdiMerchandisingCrmNo = jdiMerchandisingCheck.getCrm();
		// jdiMerchandisingActivity =
		// String.valueOf(jdiMerchandisingCheck.getActivity());
		// jdiMerchandisingProduct =
		// Integer.parseInt(String.valueOf(jdiMerchandisingCheck.getProductBrand()));
		// jdiMerchandisingStatus =
		// Integer.parseInt(String.valueOf(jdiMerchandisingCheck.getStatus()));
		// jdiMerchandisingCreatedBy =
		// JardineApp.DB.getUser().getById(jdiMerchandisingCheck.getCreatedBy()).toString();
		//
		// } catch (Exception e) {
		//
		// }
		//
		// if (jdiMerchandisingCrmNo != null || jdiMerchandisingActivity != null
		// || jdiMerchandisingProduct != 0
		// || jdiMerchandisingStatus != 0 || jdiMerchandisingCreatedBy != null)
		// {
		//
		// ((TextView)
		// view.findViewById(R.id.crm_no)).setText(jdiMerchandisingCrmNo);
		// ((TextView)
		// view.findViewById(R.id.activity)).setText(jdiMerchandisingActivity);
		// ((TextView)
		// view.findViewById(R.id.created_by)).setText(jdiMerchandisingCreatedBy);
		//
		// for (int i = 0; i < productList.size(); i++) {
		// if (jdiMerchandisingCheck.getProductBrand() ==
		// productList.get(i).getId()) {
		// ((Spinner) view.findViewById(R.id.product)).setSelection(i);
		// break;
		// }
		// }
		//
		// for (int i = 0; i < statusList.size(); i++) {
		// if (jdiMerchandisingCheck.getStatus() == statusList.get(i).getId()) {
		// ((Spinner) view.findViewById(R.id.status)).setSelection(i);
		// break;
		// }
		// }
		//
		// }
		//
		// } else {

		((Spinner) view.findViewById(R.id.product)).setAdapter(this.productAdapter);
		((Spinner) view.findViewById(R.id.status)).setAdapter(this.statusAdapter);

		((TextView) view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);
		((TextView) view.findViewById(R.id.created_by)).setEnabled(false);
		((TextView) view.findViewById(R.id.created_by)).setClickable(false);

		((TextView) view.findViewById(R.id.activity)).setText("AUTO_GEN_ON_SAVE");
		((TextView) view.findViewById(R.id.activity)).setEnabled(false);
		((TextView) view.findViewById(R.id.activity)).setClickable(false);
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

					String product = ((Spinner) view.findViewById(R.id.product)).getSelectedItem().toString();
					String status = ((Spinner) view.findViewById(R.id.product)).getSelectedItem().toString();

					/** Checking of required fields **/
					if (product != null && status != null && !product.isEmpty() && !status.isEmpty()) {

						long productLong = ((ProductRecord) ((Spinner) view.findViewById(R.id.product)).getSelectedItem()).getId();
						long statusLong = ((PicklistRecord) ((Spinner) view.findViewById(R.id.status)).getSelectedItem()).getId();

						flag = true;

						JDImerchandisingCheckRecord jdiMerchant = new JDImerchandisingCheckRecord();
						jdiMerchant.setCrm(((TextView) view.findViewById(R.id.crm_no)).getText().toString());
						jdiMerchant.setProductBrand(productLong);
						jdiMerchant.setStatus(statusLong);
						jdiMerchant.setCreatedBy(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID));
						Constant.addJDImerchandisingCheckRecords.add(jdiMerchant);

						JDIMerchandisingCheckFragment jdiMerchantFrag = (JDIMerchandisingCheckFragment) frag;
						jdiMerchantFrag.setListData();

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
						DashBoardActivity.tabIndex.add(6, 9);
						AddActivityFragment.pager.setCurrentItem(9);
					}
				}
			}
		});

		return view;
	}

	public void createJDIProductStockCheck(View view) {

	}

}
