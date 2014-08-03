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
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class AddActivityProductSupplierFragment extends Fragment {

	private boolean flag = false;
	private ArrayAdapter<ProductRecord> productAdapter = null;
	private ArrayAdapter<CustomerRecord> productSupplierAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<ProductRecord> productList = JardineApp.DB.getProduct().getAllRecords();
		List<CustomerRecord> productSupplierList = JardineApp.DB.getCustomer().getAllRecords();

		String assignedToFname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getFirstNameName();

		String assignedToLname = JardineApp.DB.getUser().getById(StoreAccount.restore(JardineApp.context).getLong(Account.ROWID))
				.getLastname();

		final View view = inflater.inflate(R.layout.add_activity_product_supplier, container, false);
//		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
//
//		long id = pref.getLong("activity_id_edit", 0);
//
//		ProductSupplierRecord productRecord = JardineApp.DB.getProductSupplier().getById(id);
//		CustomerRecord productSupplier = JardineApp.DB.getCustomer().getById(id);
//
//		if (productSupplier != null) {
//			String productSupplierCreatedBy = null;
//			String productCrmNo = null;
//			
//			long productActivity = 0;
//			long productProductBrand = 0;
//			
//			String productSuppliers = null;
//			String productOtherRemarks = null;
//
//			try {
//				productSupplierCreatedBy = JardineApp.DB.getUser().getById(productRecord.getCreatedBy()).toString();
//				productCrmNo = productRecord.getCrm();
//				productActivity = productRecord.getId(); //getActivity();
//				productProductBrand = productRecord.getProductBrand();
//				productSuppliers = productSupplier.getCustomerName();
//				productOtherRemarks = productRecord.getOthersRemarks();
//
//			} catch (Exception e) {
//
//			}
//
//			if (productSupplierCreatedBy != null || productCrmNo != null || productActivity != 0 || productProductBrand != 0
//					|| productSuppliers != null || productOtherRemarks != null) {
//
//				((TextView) view.findViewById(R.id.created_by)).setText(productSupplierCreatedBy);
//				((TextView) view.findViewById(R.id.crm_no)).setText(productCrmNo);
//				((TextView) view.findViewById(R.id.activity)).setText(String.valueOf(productActivity));
//
//				((EditText) view.findViewById(R.id.other_remarks)).setText(productOtherRemarks);
//
//				for (int i = 0; i < productList.size(); i++) {
//					if (productRecord.getProductBrand() == productList.get(i).getId()) {
//						((Spinner) view.findViewById(R.id.product_brand)).setSelection(i);
//						break;
//					}
//				}
//
//				for (int i = 0; i < productSupplierList.size(); i++) {
//					if (productSupplier.getId() == productSupplierList.get(i).getId()) {
//						((Spinner) view.findViewById(R.id.supplier)).setSelection(i);
//						break;
//					}
//				}
//
//			}
//
//		} else {

			this.productAdapter = new ArrayAdapter<ProductRecord>(JardineApp.context, R.layout.add_activity_textview, productList);
			this.productSupplierAdapter = new ArrayAdapter<CustomerRecord>(JardineApp.context, R.layout.add_activity_textview,
					productSupplierList);

			((Spinner) view.findViewById(R.id.product_brand)).setAdapter(this.productAdapter);
			((Spinner) view.findViewById(R.id.supplier)).setAdapter(this.productSupplierAdapter);

			((TextView) view.findViewById(R.id.activity)).setText("AUTO_GEN_ON_SAVE");
			((TextView) view.findViewById(R.id.activity)).setEnabled(false);
			((TextView) view.findViewById(R.id.activity)).setClickable(false);

			((TextView) view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);
			((TextView) view.findViewById(R.id.created_by)).setEnabled(false);
			((TextView) view.findViewById(R.id.created_by)).setClickable(false);
//		}

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

					long productBrand = ((ProductRecord) ((Spinner) view.findViewById(R.id.product_brand)).getSelectedItem()).getId();
					long supplier = ((CustomerRecord) ((Spinner) view.findViewById(R.id.supplier)).getSelectedItem()).getId();
					String otherRemarks = ((EditText) view.findViewById(R.id.other_remarks)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (productBrand != 0 && supplier != 0) {
						flag = true;
						Editor editor = pref.edit();
						editor.putLong("product_brand", productBrand);
						editor.putLong("supplier", supplier);
						editor.putString("product_supplier_other_remarks", otherRemarks);
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
						DashBoardActivity.tabIndex.add(5, 8);
						AddActivityFragment.pager.setCurrentItem(8);
					}
				}
			}
		});

		return view;
	}
}
