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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.PicklistRecord;

import com.dd.CircularProgressButton;

public class AddCompetitorStockCheckFragment extends Fragment {
	
	private boolean flag = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(
				R.layout.fragment_activity_add_competitor_product_stock_check,
				container, false);
		
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

					long competitorProduct = ((CompetitorProductRecord) ((Spinner) view.findViewById(R.id.competitor_product)).getSelectedItem()).getId();
					long stockStatus = ((PicklistRecord) ((Spinner) view.findViewById(R.id.stock_status)).getSelectedItem()).getId();
					int loadedOnShelves = ((CheckBox) view.findViewById(R.id.loaded_on_shelves)).isChecked() ? 1 : 0;
					String otherRemarks = ((EditText) view.findViewById(R.id.other_remarks)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);				
					
					flag = true;
					Editor editor = pref.edit();
					editor.putLong("competitor_product", competitorProduct);
					editor.putLong("stock_status", stockStatus);
					editor.putInt("loaded_on_shelves", loadedOnShelves);
					editor.putString("competitor_other_remarks", otherRemarks);
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
						DashBoardActivity.tabIndex.add(7, 10);
						AddActivityFragment.pager.setCurrentItem(10);
					}
				}
			}
		});

		return view;
	}

}
