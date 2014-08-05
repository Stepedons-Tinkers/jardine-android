package co.nextix.jardine.activities.update.fragments;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;
import co.nextix.jardine.R;

import com.dd.CircularProgressButton;

public class UpdateTravelWaitingFragment extends Fragment {

	private View view;

	private EditText reasonRemarks;

	private CircularProgressButton saveBtn;

	private boolean trapping = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_travel_waiting, container,
				false);

		reasonRemarks = (EditText) view.findViewById(R.id.update_reason_remarks);
		saveBtn = (CircularProgressButton) view.findViewById(R.id.btnWithText1);

		if (UpdateConstants.RECORD != null) {
			reasonRemarks.setText(UpdateConstants.REASON_REMARKS);
		}

		saveBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (saveBtn.getProgress() == 0) {

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
									saveBtn.setProgress(value);

									if (!trapping) {
										saveBtn.setProgress(-1);
									}
								}
							});

					widthAnimation.start();

					String remarks = reasonRemarks.getText().toString();

					if (!remarks.isEmpty() && remarks != null) {
						trapping = true;

						UpdateConstants.ACTIVITY_RECORD
								.setReasonRemarks(remarks);

						// Set the button click to true
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								v.setClickable(true);
								v.setEnabled(true);
								
								getActivity().getSupportFragmentManager();
								getActivity().getSupportFragmentManager().popBackStack();
							}

						}, 1500);

					} else {

						trapping = false;
						Toast.makeText(getActivity(),
								"Please fill up required (RED COLOR) fields",
								Toast.LENGTH_LONG).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								saveBtn.setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {					
					saveBtn.setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);
				}
			}
		});

		return view;
	}
}
