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
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class AddJDIProductStockFragment extends Fragment {

	private boolean flag = false;

	private ArrayAdapter<ProductRecord> productAdapter = null;
	private ArrayAdapter<PicklistRecord> stockStatusAdapter = null;
	private ArrayAdapter<CustomerRecord> supplierAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		List<ProductRecord> productList = JardineApp.DB.getProduct().getAllRecords();
		List<PicklistRecord> stockStatusList = JardineApp.DB.getJDIproductStatus().getAllRecords();
		List<CustomerRecord> supplierList = JardineApp.DB.getCustomer().getAllRecords();

		String assignedToFname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getLastname();

		final View view = inflater.inflate(R.layout.fragment_activity_add_jdi_product_stock_check, container, false);
		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

		long id = pref.getLong("activity_id_edit", 0);
		JDIproductStockCheckRecord jdiProductStock = JardineApp.DB.getJDIproductStockCheck().getById(id);

		if (jdiProductStock != null) {

			String jdiProductStockCrmNo = null;
			String jdiActivity = null;
			int jdiProduct = 0;
			int jdiStockStatus = 0;
			int jdiLoadedOnShelves = 0;
			int jdiSupplier = 0;
			String jdiCreatedBy = null;
			String jdiOtherRemarks = null;

			try {
				jdiProductStockCrmNo = jdiProductStock.getCrm();
				jdiActivity = String.valueOf(jdiProductStock.getActivity());
				jdiProduct = Integer.parseInt(String.valueOf(jdiProductStock.getProductBrand()));
				jdiStockStatus = Integer.parseInt(String.valueOf(jdiProductStock.getStockStatus()));
				jdiLoadedOnShelves = jdiProductStock.getLoadedOnShelves();
				jdiSupplier = Integer.parseInt(String.valueOf(jdiProductStock.getSupplier()));
				jdiCreatedBy = JardineApp.DB.getUser().getById(jdiProductStock.getCreatedBy()).toString();
				jdiOtherRemarks = jdiProductStock.getOtherRemarks();

			} catch (Exception e) {

			}

			if (jdiProductStockCrmNo != null || !jdiProductStockCrmNo.isEmpty() || jdiActivity != null || !jdiActivity.isEmpty()
					|| jdiProduct != 0 || jdiStockStatus != 0 || jdiLoadedOnShelves != -1 || jdiSupplier != 0 || jdiCreatedBy != null
					|| !jdiCreatedBy.isEmpty() || jdiOtherRemarks != null || !jdiOtherRemarks.isEmpty()) {

				((TextView) view.findViewById(R.id.crm_no)).setText(jdiProductStockCrmNo);
				((TextView) view.findViewById(R.id.activity)).setText(jdiActivity);
				((CheckBox) view.findViewById(R.id.loaded_on_shelves)).setChecked(true);
				((TextView) view.findViewById(R.id.created_by)).setText(jdiCreatedBy);
				((EditText) view.findViewById(R.id.other_type_remarks)).setText(jdiOtherRemarks);

				for (int i = 0; i < productList.size(); i++) {
					if (jdiProductStock.getProductBrand() == productList.get(i).getId()) {
						((Spinner) view.findViewById(R.id.product)).setSelection(i);
						break;
					}
				}

				for (int i = 0; i < stockStatusList.size(); i++) {
					if (jdiProductStock.getStockStatus() == stockStatusList.get(i).getId()) {
						((Spinner) view.findViewById(R.id.stock_status)).setSelection(i);
						break;
					}
				}

				for (int i = 0; i < supplierList.size(); i++) {
					if (jdiProductStock.getSupplier() == supplierList.get(i).getId()) {
						((Spinner) view.findViewById(R.id.supplier)).setSelection(i);
						break;
					}
				}

			}

		} else {

			this.productAdapter = new ArrayAdapter<ProductRecord>(JardineApp.context, R.layout.add_activity_textview, productList);
			this.stockStatusAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, stockStatusList);
			this.supplierAdapter = new ArrayAdapter<CustomerRecord>(JardineApp.context, R.layout.add_activity_textview, supplierList);

			((TextView) view.findViewById(R.id.activity)).setText("AUTO_GEN_ON_SAVE");
			((Spinner) view.findViewById(R.id.product)).setAdapter(this.productAdapter);
			((Spinner) view.findViewById(R.id.stock_status)).setAdapter(this.stockStatusAdapter);
			((Spinner) view.findViewById(R.id.supplier)).setAdapter(this.supplierAdapter);
			((TextView) view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);

			// Disable fields
			((TextView) view.findViewById(R.id.activity)).setEnabled(false);
			((TextView) view.findViewById(R.id.activity)).setClickable(false);
			((TextView) view.findViewById(R.id.created_by)).setClickable(false);
			((TextView) view.findViewById(R.id.created_by)).setEnabled(false);
		}

		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				if (((CircularProgressButton) v).getProgress() == 0) {
					v.setClickable(false);
					v.setEnabled(false);

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

					long product = ((ProductRecord) ((Spinner) view.findViewById(R.id.product)).getSelectedItem()).getId();
					long stockStatus = ((PicklistRecord) ((Spinner) view.findViewById(R.id.stock_status)).getSelectedItem()).getId();

					long loadedOnShelves = ((CheckBox) view.findViewById(R.id.loaded_on_shelves)).isChecked() ? 1 : 0;
					long supplier = ((CustomerRecord) ((Spinner) view.findViewById(R.id.supplier)).getSelectedItem()).getId();

					String otherTypeRemarks = ((EditText) view.findViewById(R.id.other_type_remarks)).getText().toString();
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

					if (product != 0 && stockStatus != 0) {

						flag = true;

						Editor editor = pref.edit();
						editor.putString("other_type_remarks", otherTypeRemarks);
						editor.putLong("stock_status", stockStatus);
						editor.putLong("loaded_on_shelves", loadedOnShelves);
						editor.putLong("product", product);
						editor.putLong("supplier", supplier);
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
								((CircularProgressButton) v).setProgress(-1);
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

						DashBoardActivity.tabIndex.add(4, 7);
						AddActivityFragment.pager.setCurrentItem(7);
					}
				}
			}
		});

		return view;
	}

	public void onCancel(View view) {
		getActivity().getSupportFragmentManager().popBackStackImmediate();
	}

	public void createJDIProductStockCheck(View view) {

	}
}
