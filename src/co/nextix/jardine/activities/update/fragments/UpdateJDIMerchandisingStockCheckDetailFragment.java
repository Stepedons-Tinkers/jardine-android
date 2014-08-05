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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;

import com.dd.CircularProgressButton;

public class UpdateJDIMerchandisingStockCheckDetailFragment extends Fragment {

	private View view;
	
	private boolean flag = false;

	private CircularProgressButton cpbSave;
	private CircularProgressButton cpbCancel;

	private String crm_no = null;
	private long activity = 0;
	private long product = 0;
	private long status = 0;
	private long created_by = 0;

	private TextView tvCrmNo;
	private TextView tvActivity;
	private TextView tvCreatedBy;

	private Spinner sProduct;
	private Spinner sStatus;

	private List<ProductRecord> productList = null;
	private List<UserRecord> userList = null;
	private List<PicklistRecord> statusList = null;

	private ArrayAdapter<ProductRecord> productAdapter = null;
	private ArrayAdapter<PicklistRecord> statusAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(
				R.layout.update_jdi_merchandising_stock_check_detail,
				container, false);

		Bundle bundle = getArguments();

		if (bundle != null) {
			crm_no = bundle.getString("crm_no");
			activity = bundle.getLong("activity");
			product = bundle.getLong("product");
			status = bundle.getLong("status");
			created_by = bundle.getLong("created_by");
		}

		cpbCancel = (CircularProgressButton) view
				.findViewById(R.id.btnWithText2);
		cpbSave = (CircularProgressButton) view.findViewById(R.id.btnWithText1);

		tvCrmNo = (TextView) view.findViewById(R.id.update_jdi_crm_no);
		tvActivity = (TextView) view.findViewById(R.id.update_jdi_activity);
		tvCreatedBy = (TextView) view.findViewById(R.id.update_jdi_created_by);

		sProduct = (Spinner) view.findViewById(R.id.update_jdi_product);
		sStatus = (Spinner) view.findViewById(R.id.update_jdi_status);

		statusList = JardineApp.DB.getJDIproductStatus().getAllRecords();
		productList = JardineApp.DB.getProduct().getAllRecords();
		userList = JardineApp.DB.getUser().getAllRecords();

		productAdapter = new ArrayAdapter<ProductRecord>(JardineApp.context,
				R.layout.add_activity_textview, productList);
		statusAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context,
				R.layout.add_activity_textview, statusList);

		sProduct.setAdapter(productAdapter);
		sStatus.setAdapter(statusAdapter);

		if (UpdateConstants.RECORD != null) {
			tvCrmNo.setText(crm_no);
			tvActivity.setText(JardineApp.DB.getActivity().getById(activity)
					.toString());

			for (int x = 0; x < productList.size(); x++) {
				if (productList.get(x).getId() == product) {
					sProduct.setSelection(x);
					break;
				}
			}

			for (int y = 0; y < userList.size(); y++) {
				if (userList.get(y).getId() == created_by) {
					tvCreatedBy.setText(userList.get(y).toString());
					break;
				}
			}
			
			for(int z = 0; z < statusList.size(); z++){
				if(statusList.get(z).getId() == status){
					sStatus.setSelection(z);
					break;
				}
			}

		}

		cpbCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager().popBackStack(
						"to_add_jdi_merchandising",
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});
		
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
					
					long product_modify = ((ProductRecord) sProduct.getSelectedItem()).getId();
					long status_modify = ((PicklistRecord) sStatus.getSelectedItem()).getId();
					
					if (product_modify != 0 && status_modify != 0) {
						flag = true;
						
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								cpbSave.setClickable(true);
								cpbSave.setEnabled(true);
								
								// save it to constants then update the DATABASE then notify the listview
								
								getActivity().getSupportFragmentManager();
								getActivity().getSupportFragmentManager().popBackStack(
										"to_add_jdi_merchandising",
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
