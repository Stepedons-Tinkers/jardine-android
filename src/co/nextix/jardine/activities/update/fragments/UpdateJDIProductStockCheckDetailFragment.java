package co.nextix.jardine.activities.update.fragments;

import java.util.List;

import com.dd.CircularProgressButton;

import android.animation.ValueAnimator;
import android.location.Criteria;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;

public class UpdateJDIProductStockCheckDetailFragment extends Fragment {

	private View view;

	private boolean flag = false;

	private CircularProgressButton cpbSave;
	private CircularProgressButton cpbCancel;

	private TextView tvCrmNo;
	private TextView tvActivity;
	private TextView tvCreatedBy;

	private Spinner sProduct;
	private Spinner sStockStatus;
	private Spinner sSupplier;

	private EditText etOtherRemarks;

	private CheckBox cbLoaded;

	private String crm_no = null;
	private String remarks = null;
	private long created_by = 0;
	private long activity = 0;
	private long product = 0;
	private long status = 0;
	private long supplier = 0;
	private int loaded = 0;

	private List<ProductRecord> productList = null;
	private List<UserRecord> userList = null;
	private List<PicklistRecord> statusList = null;

	private ArrayAdapter<ProductRecord> productAdapter = null;
	private ArrayAdapter<PicklistRecord> statusAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Bundle bundle = getArguments();
		if (bundle != null) {
			crm_no = bundle.getString("crmno");
			remarks = bundle.getString("type_remarks");
			created_by = bundle.getLong("created_by");
			activity = bundle.getLong("activity");
			product = bundle.getLong("product");
			status = bundle.getLong("status");
			supplier = bundle.getLong("supplier");
			loaded = bundle.getInt("loaded");
		}

		view = inflater.inflate(R.layout.update_jdi_product_stock_check_detail,
				container, false);

		tvCrmNo = (TextView) view.findViewById(R.id.update_jdi_product_crm_no);
		tvActivity = (TextView) view
				.findViewById(R.id.update_jdi_product_activity);
		tvCreatedBy = (TextView) view
				.findViewById(R.id.update_jdi_product_created_by);

		sProduct = (Spinner) view.findViewById(R.id.update_jdi_product_product);
		sStockStatus = (Spinner) view
				.findViewById(R.id.update_jdi_product_stock_status);
		sSupplier = (Spinner) view
				.findViewById(R.id.update_jdi_product_supplier);

		etOtherRemarks = (EditText) view
				.findViewById(R.id.update_jdi_product_other_type_remarks);

		cbLoaded = (CheckBox) view
				.findViewById(R.id.update_jdi_product_loaded_on_shelves);

		statusList = JardineApp.DB.getJDIproductStatus().getAllRecords();
		productList = JardineApp.DB.getProduct().getAllRecords();
		userList = JardineApp.DB.getUser().getAllRecords();

		productAdapter = new ArrayAdapter<ProductRecord>(JardineApp.context,
				R.layout.add_activity_textview, productList);
		statusAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context,
				R.layout.add_activity_textview, statusList);

		sProduct.setAdapter(productAdapter);
		sStockStatus.setAdapter(statusAdapter);

		tvCrmNo.setText(crm_no);
		etOtherRemarks.setText(remarks);
		tvActivity.setText(JardineApp.DB.getActivity().getById(activity)
				.toString());

		if (loaded != 0) {
			cbLoaded.setChecked(true);
		}

		for (int x = 0; x < statusList.size(); x++) {
			if (statusList.get(x).getId() == status) {
				sStockStatus.setSelection(x);
			}
		}

		for (int y = 0; y < productList.size(); y++) {
			if (productList.get(y).getId() == product) {
				sProduct.setSelection(y);
			}
		}

		for (int z = 0; z < userList.size(); z++) {
			if (userList.get(z).getId() == created_by) {
				tvCreatedBy.setText(userList.get(z).toString());
			}
		}

		cpbCancel = (CircularProgressButton) view
				.findViewById(R.id.btnWithText2);
		cpbCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager().popBackStack(
						"to_add_jdi_product",
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

					long product_modify = ((ProductRecord) sProduct
							.getSelectedItem()).getId();
					long status_modify = ((PicklistRecord) sStockStatus
							.getSelectedItem()).getId();

					if (product_modify != 0 && status_modify != 0) {
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
												"to_add_jdi_product",
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
