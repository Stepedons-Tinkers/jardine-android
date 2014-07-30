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
import android.widget.Spinner;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;

import com.dd.CircularProgressButton;

public class AddJDIMerchandisingStockFragment extends Fragment {

	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<ProductRecord> productList = JardineApp.DB.getProduct().getAllRecords();
		List<CustomerRecord> customerList = JardineApp.DB.getCustomer().getAllRecords();
		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

		final View view = inflater.inflate(R.layout.fragment_activity_add_jdi_merchandising_check, container, false);
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

					long product = ((ProductRecord) ((Spinner) view.findViewById(R.id.product)).getSelectedItem()).getId();
					long status = ((PicklistRecord) ((Spinner) view.findViewById(R.id.status)).getSelectedItem()).getId();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

					flag = true;
					Editor editor = pref.edit();
					editor.putLong("product", product);
					editor.putLong("status", status);
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
