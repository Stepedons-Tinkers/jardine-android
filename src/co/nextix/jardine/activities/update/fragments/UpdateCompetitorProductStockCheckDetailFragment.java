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
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;

import com.dd.CircularProgressButton;

public class UpdateCompetitorProductStockCheckDetailFragment extends Fragment {

	private View view;

	private boolean flag = false;

	private CircularProgressButton cpbSave;
	private CircularProgressButton cpbCancel;

	private TextView tvCrmNo;
	private TextView tvActivity;
	private TextView tvCreatedBy;

	private EditText etOtherTypeRemarks;

	private Spinner sCompProduct;
	private Spinner sStockStaus;

	private CheckBox cbLoaded;

	private String crm_no = null;
	private String other_remarks = null;
	private long competitor_product = 0;
	private long stock_status = 0;
	private long activity = 0;
	private long created_by = 0;
	private int loaded = 0;

	private List<UserRecord> userList = null;
	private List<CompetitorProductRecord> compProductList = null;
	private List<PicklistRecord> statusList = null;
	private List<ActivityRecord> activityList = null;

	private ArrayAdapter<CompetitorProductRecord> compProductAdapter = null;
	private ArrayAdapter<PicklistRecord> statusAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_competitor_stock_check_detail,
				container, false);

		sCompProduct = (Spinner) view
				.findViewById(R.id.update_comp_competitor_product);
		sStockStaus = (Spinner) view
				.findViewById(R.id.update_comp_stock_status);

		tvCrmNo = (TextView) view.findViewById(R.id.update_comp_crm_no);
		tvActivity = (TextView) view.findViewById(R.id.update_comp_activity);
		tvCreatedBy = (TextView) view.findViewById(R.id.update_comp_created_by);

		etOtherTypeRemarks = (EditText) view
				.findViewById(R.id.update_comp_other_remarks);

		cbLoaded = (CheckBox) view
				.findViewById(R.id.update_comp_loaded_on_shelves);

		userList = JardineApp.DB.getUser().getAllRecords();
		compProductList = JardineApp.DB.getCompetitorProduct().getAllRecords();
		statusList = JardineApp.DB.getCompetitorProductStockStatus()
				.getAllRecords();
		activityList = JardineApp.DB.getActivity().getAllRecords();

		compProductAdapter = new ArrayAdapter<CompetitorProductRecord>(
				JardineApp.context, R.layout.add_activity_textview,
				compProductList);
		statusAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context,
				R.layout.add_activity_textview, statusList);

		sCompProduct.setAdapter(compProductAdapter);
		sStockStaus.setAdapter(statusAdapter);

		Bundle bundle = getArguments();
		if (bundle != null) {
			crm_no = bundle.getString("crm_no");
			other_remarks = bundle.getString("other_remarks");
			competitor_product = bundle.getLong("competitor_product");
			stock_status = bundle.getLong("stock_status");
			loaded = bundle.getInt("loaded");
			created_by = bundle.getLong("created_by");
			activity = bundle.getLong("activity");
		}

		if (UpdateConstants.RECORD != null) {
			tvCrmNo.setText(crm_no);
			etOtherTypeRemarks.setText(other_remarks);

			for (int x = 0; x < userList.size(); x++) {
				if (userList.get(x).getId() == created_by) {
					tvCreatedBy.setText(userList.get(x).toString());
				}
			}

			for (int y = 0; y < compProductList.size(); y++) {
				if (compProductList.get(y).getId() == competitor_product) {
					sCompProduct.setSelection(y);
				}
			}

			for (int z = 0; z < statusList.size(); z++) {
				if (statusList.get(z).getId() == stock_status) {
					sStockStaus.setSelection(z);
				}
			}

			for (int a = 0; a < activityList.size(); a++) {
				if (activityList.get(a).getId() == activity) {
					tvActivity.setText(activityList.get(a).toString());
				}
			}

			if (loaded != 0) {
				cbLoaded.setChecked(true);
			}
		}

		cpbCancel = (CircularProgressButton) view
				.findViewById(R.id.btnWithText2);
		cpbCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager()
						.popBackStack("to_add_comp",
								FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

					long status = ((PicklistRecord) sStockStaus
							.getSelectedItem()).getId();
					long product = ((CompetitorProductRecord) sCompProduct
							.getSelectedItem()).getId();

					if (status != 0 && product != 0) {
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
												"to_add_comp",
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