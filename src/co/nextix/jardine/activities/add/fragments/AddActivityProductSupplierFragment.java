package co.nextix.jardine.activities.add.fragments;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.JDIMerchandisingCheckFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.ProductRecord;

import com.dd.CircularProgressButton;

public class AddActivityProductSupplierFragment extends Fragment {
	
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.add_activity_product_supplier, container, false);
		
		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(1500);
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

					long activity = ((ActivityRecord) ((Spinner) view.findViewById(R.id.activity)).getSelectedItem()).getId();
					long productBrand = ((ProductRecord) ((Spinner) view.findViewById(R.id.product_brand)).getSelectedItem()).getId();
					long supplier = ((CustomerRecord) ((Spinner) view.findViewById(R.id.supplier)).getSelectedItem()).getId();
					String otherRemarks = ((EditText) view.findViewById(R.id.other_remarks)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);		

					flag = true;
					Editor editor = pref.edit();
					editor.putLong("activity", activity);
					editor.putLong("product_brand", productBrand);
					editor.putLong("supplier", supplier);
					editor.putString("product_supplier_other_remarks", otherRemarks);
					editor.commit(); // commit changes

					Handler handler = new Handler();
					handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							getFragmentManager().popBackStackImmediate();
						}

					}, 2700);

				} else {
					((CircularProgressButton) v).setProgress(0);
					
					if(AddActivityGeneralInformationFragment.ActivityType == 4){ // retails
						DashBoardActivity.tabIndex.add(5, 8);
						AddActivityFragment.pager.setCurrentItem(8);
					}
				}
			}
		});

		return view;
	}
}
