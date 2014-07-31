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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class AddActivityTrainingsFragment extends Fragment {

	private boolean trapping = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.add_activity_trainings, container, false);
		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		long activity_id = pref.getLong("activity_id_edit", 0);

		ActivityRecord activityRecord = JardineApp.DB.getActivity().getById(activity_id);
		if (activityRecord != null) {
			
			String venue = null;
			int numberOfAttendees = 0;

			try {
				
				venue = activityRecord.getVenue();
				numberOfAttendees = activityRecord.getNumberOfAttendees();
				
			} catch (Exception e) {

			}

			((EditText) rootView.findViewById(R.id.venue)).setText(venue);
			((EditText) rootView.findViewById(R.id.no_of_attendees)).setText(String.valueOf(numberOfAttendees));

		}

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

					String crmno = ((TextView) rootView.findViewById(R.id.crm_no)).getText().toString();
					String checkin = ((TextView) rootView.findViewById(R.id.check_in)).getText().toString();
					String checkout = ((TextView) rootView.findViewById(R.id.check_out)).getText().toString();
					long activityType = ((ActivityTypeRecord) ((Spinner) rootView.findViewById(R.id.activity_type)).getSelectedItem())
							.getId();
					long createdBy = Long.parseLong(StoreAccount.restore(getActivity()).getString(Account.ROWID));

					BusinessUnitRecord businessUnit = JardineApp.DB.getBusinessUnit().getById(
							JardineApp.DB.getUser().getCurrentUser().getId());

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (activityType != 0 && checkin != null && !checkin.isEmpty() && checkout != null && !checkout.isEmpty()) {

						trapping = true;
						Editor editor = pref.edit();
						editor.putString("crm_no", crmno);
						editor.putString("check_in", checkin);
						editor.putString("check_out", checkout);
						editor.putLong("activity_type", activityType);
						editor.putLong("createdBy", createdBy);
						editor.putLong("business_unit", businessUnit.getId());
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

						trapping = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_LONG).show();

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

		((TextView) rootView.findViewById(R.id.venue)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("venue", v.getText().toString());
					editor.putString("no_attendees", ((EditText) rootView.findViewById(R.id.number_of_attendees)).getText().toString());
					editor.commit(); // commit changes
				}

				return false;
			}
		});

		return rootView;
	}
}
