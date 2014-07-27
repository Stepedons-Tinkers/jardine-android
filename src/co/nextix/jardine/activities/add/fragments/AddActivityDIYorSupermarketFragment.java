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

public class AddActivityDIYorSupermarketFragment extends Fragment {

	private boolean trapping = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.add_activity_diy_or_supermarket, container, false);
		((EditText) rootView.findViewById(R.id.ongoing_campaigns)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("issues_identified", ((EditText) rootView.findViewById(R.id.issues_identified)).getText().toString());
					editor.putString("feedback_from_customer", ((EditText) rootView.findViewById(R.id.feedback_from_customer)).getText()
							.toString());
					
					editor.putString("ongoing_campaigns", v.getText().toString());
					editor.commit(); // commit changes
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

							if (!trapping) {
								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					String ongoingCampaigns = ((EditText) rootView.findViewById(R.id.ongoing_campaigns)).getText().toString();
					String issuesIdentified = ((EditText) rootView.findViewById(R.id.issues_identified)).getText().toString();
					String feedbackFromCustomer = ((EditText) rootView.findViewById(R.id.feedback_from_customer)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (issuesIdentified != null && !issuesIdentified.isEmpty() && feedbackFromCustomer != null
							&& !feedbackFromCustomer.isEmpty()) {

						trapping = true;
						Editor editor = pref.edit();
						editor.putString("ongoing_campaigns", ongoingCampaigns);
						editor.putString("issues_identified", issuesIdentified);
						editor.putString("feedback_from_customer", feedbackFromCustomer);
						editor.commit(); // commit changes

					} else {

						trapping = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_LONG).show();

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
					// addActFrag.pager.setCurrentItem(tabID);
					//
					// ft =
					// getActivity().getSupportFragmentManager().beginTransaction();
					// ft.replace(frag_layout_id, fragmentForTransition);
					// ft.addToBackStack(null);
					// ft.commit();
				}

			}
		});

		return rootView;
	}

}
