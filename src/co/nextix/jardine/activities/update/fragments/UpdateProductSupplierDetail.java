package co.nextix.jardine.activities.update.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;

import com.dd.CircularProgressButton;

public class UpdateProductSupplierDetail extends Fragment {

	private View view;

	private boolean flag = false;

	private TextView tvCreatedBy;
	private TextView tvCrmNo;
	private TextView tvActivity;

	private Spinner sProductBrand;
	private Spinner sSupplier;

	private EditText etOtherRemarks;

	private CircularProgressButton saveBtn;

	private String other_remarks = null;
	private long created_by = 0;
	private long product_brand = 0;
	private long supplier = 0;
	private long activity = 0;

	private ArrayAdapter<ProductRecord> productAdapter = null;
	private ArrayAdapter<CustomerRecord> productSupplierAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Bundle bundle = getArguments();

		if (bundle != null) {
			activity = bundle.getLong("activity");
			created_by = bundle.getLong("created_by");
			product_brand = bundle.getLong("product_brand");
			supplier = bundle.getLong("supplier");
			other_remarks = bundle.getString("other_remarks");
		}

		view = inflater.inflate(R.layout.update_product_supplier_detail,
				container, false);

		List<ProductRecord> productList = JardineApp.DB.getProduct()
				.getAllRecords();
		List<CustomerRecord> productSupplierList = JardineApp.DB.getCustomer()
				.getAllRecords();
		List<UserRecord> userList = JardineApp.DB.getUser().getAllRecords();
		List<ActivityRecord> activityList = JardineApp.DB.getActivity()
				.getAllRecords();

		productAdapter = new ArrayAdapter<ProductRecord>(JardineApp.context,
				R.layout.add_activity_textview, productList);
		productSupplierAdapter = new ArrayAdapter<CustomerRecord>(
				JardineApp.context, R.layout.add_activity_textview,
				productSupplierList);

		tvCreatedBy = (TextView) view.findViewById(R.id.update_created_by);
		tvCrmNo = (TextView) view.findViewById(R.id.update_crm_no);
		tvActivity = (TextView) view.findViewById(R.id.update_activity);

		sProductBrand = (Spinner) view.findViewById(R.id.update_product_brand);
		sSupplier = (Spinner) view.findViewById(R.id.update_supplier);

		sProductBrand.setAdapter(productAdapter);
		sSupplier.setAdapter(productSupplierAdapter);

		etOtherRemarks = (EditText) view
				.findViewById(R.id.update_other_remarks);

		if (UpdateConstants.RECORD != null) {
			tvCrmNo.setText(UpdateConstants.CRM_NO);
			etOtherRemarks.setText(other_remarks);

			for (int x = 0; x < productList.size(); x++) {
				if (productList.get(x).getId() == product_brand) {
					sProductBrand.setSelection(x);
				}
			}

			for (int y = 0; y < productSupplierList.size(); y++) {
				if (productSupplierList.get(y).getId() == supplier) {
					sSupplier.setSelection(y);
				}
			}

			for (int z = 0; z < userList.size(); z++) {
				if (userList.get(z).getId() == created_by) {
					tvCreatedBy.setText(userList.get(z).toString());
				}
			}

			for (int a = 0; a < activityList.size(); a++) {
				if (activityList.get(a).getId() == activity) {
					tvActivity.setText(activityList.get(a).toString());
				}
			}
		}

		saveBtn = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
		saveBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (saveBtn.getProgress() == 0) {
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
									saveBtn.setProgress(value);

									if (!flag) {
										saveBtn.setProgress(-1);
									}
								}
							});

					widthAnimation.start();

					long productBrand = ((ProductRecord) sProductBrand
							.getSelectedItem()).getId();
					long suppLier = ((CustomerRecord) sSupplier
							.getSelectedItem()).getId();
					String otherRemarks = etOtherRemarks.getText().toString();

					if (productBrand != 0 && suppLier != 0) {
						flag = true;
						
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								saveBtn.setClickable(true);
								saveBtn.setEnabled(true);
								
								// save it to constants then update the DATABASE then notify the listview
								
								getActivity().getSupportFragmentManager();
								getActivity().getSupportFragmentManager().popBackStack(
										"to_add_supplier",
										FragmentManager.POP_BACK_STACK_INCLUSIVE);
							}
						}, 1500);
					} else {
						flag = false;
						Toast.makeText(getActivity(),
								"Please fill up required fields",
								Toast.LENGTH_SHORT).show();
						saveBtn.setClickable(true);
						saveBtn.setEnabled(true);
					}
				} else {
					saveBtn.setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);
					Log.e("outside-else", "zup");
				}
			}
		});

		return view;
	}
}
