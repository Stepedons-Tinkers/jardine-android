package co.nextix.jardine.activities.add.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.utils.MultiSpinner;
import co.nextix.jardine.utils.MultiSpinner.MultiSpinnerListener;

import com.dd.CircularProgressButton;

public class AddActivityFullBrandActivationFragment extends Fragment implements MultiSpinnerListener {
	private View view = null;
	private boolean flag = false;
	public static int POSITION_END_USER_ACTIVITY_TYPE = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.view = inflater.inflate(R.layout.add_activity_full_brand, container, false);
		final List<ActivityTypeRecord> activityTypeList = JardineApp.DB.getActivityType().getAllRecords();
		MultiSpinner multiSpinner = (MultiSpinner) this.view.findViewById(R.id.multi_spinner);

		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		long activity_id = pref.getLong("activity_id_edit", 0);

		ActivityRecord activityRecord = JardineApp.DB.getActivity().getById(activity_id);
		if (activityRecord != null) {

			// Check if it has check
			multiSpinner.setItems(activityTypeList, " ", this);

		} else {
			multiSpinner.setItems(activityTypeList, "- Uncheked to select ( max 5; min 1 ) -", this);

		}

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

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					flag = true;

					String endUserActvityTypes = ((Spinner) view.findViewById(R.id.multi_spinner)).getSelectedItem().toString();
					Toast.makeText(getActivity().getApplicationContext(), endUserActvityTypes, Toast.LENGTH_LONG).show();

					Editor editor = pref.edit();
					editor.putString("end_user_activity_types", endUserActvityTypes);
					editor.putInt("end_user_activity_types_position", POSITION_END_USER_ACTIVITY_TYPE);
					editor.commit(); // commit changes

				} else {

					((CircularProgressButton) v).setProgress(0);
					if (AddActivityGeneralInformationFragment.ActivityType == 41) { // full
																					// brand
						DashBoardActivity.tabIndex.add(4, 16);
						AddActivityFragment.pager.setCurrentItem(16);
					}
				}
			}
		});

		return this.view;
	}

	@Override
	public void onItemsSelected(boolean[] selected) {
		// Code here
	}
}
