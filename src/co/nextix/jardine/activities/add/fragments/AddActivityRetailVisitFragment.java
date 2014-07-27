package co.nextix.jardine.activities.add.fragments;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import co.nextix.jardine.R;

import com.dd.CircularProgressButton;

public class AddActivityRetailVisitFragment extends Fragment {

	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.add_activity_retail_visit, container, false);
		((EditText) rootView.findViewById(R.id.promo_stubs_details)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("last_delivery", ((EditText) rootView.findViewById(R.id.last_delivery)).getText().toString());
					editor.putString("promo_stubs_details", v.getText().toString());
					editor.commit(); // commit changes
				}

				return false;
			}
		});

		((EditText) rootView.findViewById(R.id.last_delivery)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("last_delivery", v.getText().toString());
					editor.putString("prom_stubs_details", ((EditText) rootView.findViewById(R.id.promo_stubs_details)).getText()
							.toString());
					editor.commit();
				}

				return false;
			}
		});

		((CircularProgressButton) rootView.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

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

					String lastDelivery = ((EditText) rootView.findViewById(R.id.last_delivery)).getText().toString();
					String promoStubsDetails = ((EditText) rootView.findViewById(R.id.promo_stubs_details)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("last_delivery", lastDelivery);
					editor.putString("promo_stubs_details", promoStubsDetails);
					editor.commit(); // commit changes
					
					flag = true;

					Handler handler = new Handler();
					handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							getFragmentManager().popBackStackImmediate();
						}

					}, 2700);

					flag = false;
					Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

//					Handler handler = new Handler();
//					handler.postDelayed(new Runnable() {
//
//						@Override
//						public void run() {
//							saveBtn.setProgress(0);
//
//						}
//					}, 1500);
				}

				else {

					((CircularProgressButton) v).setProgress(0);
				}

			}
		});

		return rootView;
	}

}
