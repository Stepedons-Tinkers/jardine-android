package co.nextix.jardine.activities.add.fragments;

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
import android.widget.EditText;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.utils.Tools;

import com.dd.CircularProgressButton;

public class AddActivityTrainingsFragment extends Fragment {

	private boolean trapping = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.add_activity_trainings, container, false);
//		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
//		long activity_id = pref.getLong("activity_id_edit", 0);
//
//		ActivityRecord activityRecord = JardineApp.DB.getActivity().getById(activity_id);
//		if (activityRecord != null) {
//
//			String venue = null;
//			int numberOfAttendees = 0;
//
//			try {
//
//				venue = activityRecord.getVenue();
//				numberOfAttendees = activityRecord.getNumberOfAttendees();
//
//			} catch (Exception e) {
//
//			}
//
//			((EditText) rootView.findViewById(R.id.venue)).setText(venue);
//			((EditText) rootView.findViewById(R.id.no_of_attendees)).setText(String.valueOf(numberOfAttendees));
//
//		}

		((CircularProgressButton) rootView.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

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

							if (!trapping) {
								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					String venue = ((EditText) rootView.findViewById(R.id.venue)).getText().toString();
					String noOfAttendess = ((EditText) rootView.findViewById(R.id.no_of_attendees)).getText().toString();
					
					Constant.activityGeneralInfo.setVenue(venue);
					Constant.activityGeneralInfo.setNumberOfAttendees(Tools.parseIntWithDefault(noOfAttendess, 0));

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (venue != null && !venue.isEmpty() && noOfAttendess != null && !noOfAttendess.isEmpty()) {

						((EditText) rootView.findViewById(R.id.venue)).setError(null);
						((EditText) rootView.findViewById(R.id.no_of_attendees)).setError(null);

						trapping = true;
						int noOfAttendees = Integer.parseInt((((EditText) rootView.findViewById(R.id.no_of_attendees)).getText().toString()));

						Editor editor = pref.edit();
						editor.putString("trainings_venue", venue);
						editor.putInt("trainings_no", noOfAttendees);
						editor.commit(); // commit changes

						// Set the button click to true
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								v.setClickable(true);
								v.setEnabled(true);
							}

						}, 700);

					} else {
						if(venue == null || venue.isEmpty() )
							((EditText) rootView.findViewById(R.id.venue)).setError("Please provide a venue.");
						if(noOfAttendess == null || noOfAttendess.isEmpty())
							((EditText) rootView.findViewById(R.id.no_of_attendees)).setError("Please provide no. of attendees.");

						trapping = false;
						Toast.makeText(getActivity(), "Please fill up required fields", Toast.LENGTH_LONG).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);

					if (AddActivityGeneralInformationFragment.ActivityType == 101) { // major
																						// training
						DashBoardActivity.tabIndex.add(4, 16);
						AddActivityFragment.pager.setCurrentItem(16);
					}

				}

			}
		});

		return rootView;
	}
}
