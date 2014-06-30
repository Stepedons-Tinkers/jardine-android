package co.nextix.jardine.activities.add.fragments;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import co.nextix.jardine.R;

import com.dd.CircularProgressButton;

public class AddActivityGeneralInformationFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.add_activity_gen_info,
				container, false);
		
		final CircularProgressButton btnWithText1 = (CircularProgressButton) rootView
				.findViewById(R.id.btnWithText1);

		btnWithText1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (btnWithText1.getProgress() == 0) {
					simulateSuccessProgress(btnWithText1);
				} else {
					btnWithText1.setProgress(0);
				}
			}
		});

		return rootView;
	}

	private void simulateSuccessProgress(final CircularProgressButton button) {
		ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
		widthAnimation.setDuration(1500);
		widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		widthAnimation
				.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						Integer value = (Integer) animation.getAnimatedValue();
						button.setProgress(value);
					}
				});
		widthAnimation.start();
	}

	private void simulateErrorProgress(final CircularProgressButton button) {
		ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
		widthAnimation.setDuration(1500);
		widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		widthAnimation
				.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						Integer value = (Integer) animation.getAnimatedValue();
						button.setProgress(value);
						if (value == 99) {
							button.setProgress(-1);
						}
					}
				});
		widthAnimation.start();
	}
}