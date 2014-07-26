package co.nextix.jardine.activities.add.fragments;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;
import co.nextix.jardine.R;

import com.dd.CircularProgressButton;

public class AddActivityTravelWaitingFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.add_activity_travel_or_waiting, container, false);
		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				if (((CircularProgressButton) v).getProgress() == 0) {

					String reasons = ((EditText) view.findViewById(R.id.reason_remarks)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (reasons != null && !reasons.isEmpty()) {

						ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
						widthAnimation.setDuration(1500);
						widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
						widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
							@Override
							public void onAnimationUpdate(ValueAnimator animation) {

								Integer value = (Integer) animation.getAnimatedValue();
								((CircularProgressButton) v).setProgress(value);
							}
						});

						widthAnimation.start();

						Editor editor = pref.edit();
						editor.putString("reasons_remarks", reasons);
						editor.commit(); // commit changes

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								getFragmentManager().popBackStackImmediate();
							}

						}, 2700);

					} else {
//						ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
//						widthAnimation.setDuration(1500);
//						widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
//						widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//							@Override
//							public void onAnimationUpdate(ValueAnimator animation) {
//								Integer value = (Integer) animation.getAnimatedValue();
//								((CircularProgressButton) v).setProgress(value);
//								if (value == 99) {
//									((CircularProgressButton) v).setProgress(-1);
//								}
//							}
//						});
//
//						widthAnimation.start();
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();
					}

				} else {
					// Code here
				}
			}
		});

		return view;
	}
}
