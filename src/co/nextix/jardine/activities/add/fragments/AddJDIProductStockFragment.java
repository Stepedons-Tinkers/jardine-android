package co.nextix.jardine.activities.add.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
<<<<<<< HEAD
import co.nextix.jardine.JardineApp;
=======
import co.nextix.jardine.DashBoardActivity;
>>>>>>> 89ec886edb1dd1a5741eb7aa9c115966132a8844
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;

import com.dd.CircularProgressButton;

public class AddJDIProductStockFragment extends Fragment {

	private boolean flag = false;
<<<<<<< HEAD

	private Bundle bundle;
	private int frag_layout_id = 0;
=======
>>>>>>> 89ec886edb1dd1a5741eb7aa9c115966132a8844

	private ArrayAdapter<ActivityRecord> activityAdapter = null;
	private ArrayAdapter<ProductRecord> productAdapter = null;
	private ArrayAdapter<PicklistRecord> stockStatusAdapter = null;
	private ArrayAdapter<CustomerRecord> supplierAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<ActivityRecord> activityList = JardineApp.DB.getActivity().getAllRecords();
		List<ProductRecord> productList = JardineApp.DB.getProduct().getAllRecords();
		List<PicklistRecord> stockStatusList = JardineApp.DB.getJDIproductStatus().getAllRecords();
		List<CustomerRecord> supplierList = JardineApp.DB.getCustomer().getAllRecords();

		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

		final View view = inflater.inflate(R.layout.fragment_activity_add_jdi_product_stock_check, container, false);
<<<<<<< HEAD
		this.bundle = getArguments();

		if (this.bundle != null) {
			this.frag_layout_id = this.bundle.getInt("layoutID");
		}
=======
>>>>>>> 89ec886edb1dd1a5741eb7aa9c115966132a8844

		this.activityAdapter = new ArrayAdapter<ActivityRecord>(JardineApp.context, R.layout.add_activity_textview, activityList);
		this.productAdapter = new ArrayAdapter<ProductRecord>(JardineApp.context, R.layout.add_activity_textview, productList);
		this.stockStatusAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, stockStatusList);
		this.supplierAdapter = new ArrayAdapter<CustomerRecord>(JardineApp.context, R.layout.add_activity_textview, supplierList);

		((Spinner) view.findViewById(R.id.activity)).setAdapter(this.activityAdapter);
		((Spinner) view.findViewById(R.id.product)).setAdapter(this.productAdapter);
		((Spinner) view.findViewById(R.id.stock_status)).setAdapter(this.stockStatusAdapter);
		((Spinner) view.findViewById(R.id.supplier)).setAdapter(this.supplierAdapter);
		((TextView) view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);

		// Disable fields
		((Spinner) view.findViewById(R.id.activity)).setEnabled(false);
		((Spinner) view.findViewById(R.id.activity)).setClickable(false);
		((TextView) view.findViewById(R.id.created_by)).setClickable(false);
		((TextView) view.findViewById(R.id.created_by)).setEnabled(false);

		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
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

					long product = ((ProductRecord) ((Spinner) view.findViewById(R.id.product)).getSelectedItem()).getId();
					long stockStatus = ((JDIproductStockCheckRecord) ((Spinner) view.findViewById(R.id.stock_status)).getSelectedItem())
							.getId();
					long loadedOnShelves = ((CheckBox) view.findViewById(R.id.loaded_on_shelves)).isChecked() ? 1 : 0;
					long supplier = ((CustomerRecord) ((Spinner) view.findViewById(R.id.supplier)).getSelectedItem()).getId();
					long activity = ((ActivityRecord) ((Spinner) view.findViewById(R.id.activity)).getSelectedItem()).getId();

					String otherTypeRemarks = ((EditText) view.findViewById(R.id.other_type_remarks)).getText().toString();
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

<<<<<<< HEAD
					if (product != 0 && stockStatus != 0) {

						if (AddActivityFragment.ACTIVITY_TYPE == 4) {
							fragmentForTransition = new AddActivityProductSupplierFragment();
						}

=======
					if (otherTypeRemarks != null && !otherTypeRemarks.isEmpty()) {
						
>>>>>>> 89ec886edb1dd1a5741eb7aa9c115966132a8844
						Editor editor = pref.edit();
						editor.putString("other_type_remarks", otherTypeRemarks);
						editor.putLong("stock_status", stockStatus);
						editor.putLong("loaded_on_shelves", loadedOnShelves);
						editor.putLong("product", product);
						editor.putLong("supplier", supplier);
						editor.putLong("activity", activity);

						editor.commit(); // commit changes
					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);

							}
						}, 1500);
					}
				} else {
					((CircularProgressButton) v).setProgress(0);
<<<<<<< HEAD

					fragmentForTransition.setArguments(bundle);

					ft = getActivity().getSupportFragmentManager().beginTransaction();
					ft.replace(frag_layout_id, fragmentForTransition);
					ft.addToBackStack(null);
					ft.commit();
=======
					
					if (AddActivityGeneralInformationFragment.ActivityType == 4){ // retails
						DashBoardActivity.tabIndex.add(4, 7);
						AddActivityFragment.pager.setCurrentItem(7);
					}
>>>>>>> 89ec886edb1dd1a5741eb7aa9c115966132a8844
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
