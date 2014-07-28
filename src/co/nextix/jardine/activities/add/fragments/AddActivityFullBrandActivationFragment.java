package co.nextix.jardine.activities.add.fragments;

import java.util.List;

import com.dd.CircularProgressButton;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.customers.AddCustomerContactsFragment;
import co.nextix.jardine.database.records.ActivityTypeRecord;

public class AddActivityFullBrandActivationFragment extends Fragment {

	private ArrayAdapter<ActivityTypeRecord> endUserActivityTypesAdapter = null;
	private View view = null;
	
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.add_activity_full_brand, container,
				false);

		List<ActivityTypeRecord> activityTypeList = JardineApp.DB
				.getActivityType().getAllRecords();

		// ArrayAdapter for spinners
		this.endUserActivityTypesAdapter = new ArrayAdapter<ActivityTypeRecord>(
				JardineApp.context,
				R.layout.add_activity_multi_select_listview, activityTypeList);

		((Spinner) view.findViewById(R.id.end_user_activity_types))
				.setAdapter(this.endUserActivityTypesAdapter);

		((CircularProgressButton) view.findViewById(R.id.btnWithText1))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(final View v) {
						if (((CircularProgressButton) v).getProgress() == 0) {

							ValueAnimator widthAnimation = ValueAnimator.ofInt(
									1, 100);
							widthAnimation.setDuration(1500);
							widthAnimation
									.setInterpolator(new AccelerateDecelerateInterpolator());
							widthAnimation
									.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
										@Override
										public void onAnimationUpdate(
												ValueAnimator animation) {
											Integer value = (Integer) animation
													.getAnimatedValue();
											((CircularProgressButton) v)
													.setProgress(value);

											if (!flag) {
												((CircularProgressButton) v)
														.setProgress(-1);
											}
										}
									});

							widthAnimation.start();

							/** Checking of required fields **/
							SharedPreferences pref = getActivity()
									.getApplicationContext()
									.getSharedPreferences("ActivityInfo", 0);

							flag = true;

							String endUserActvityTypes = ((Spinner) view
									.findViewById(R.id.end_user_activity_types))
									.getSelectedItem().toString();

							Editor editor = pref.edit();
							editor.putString("end_user_activity_types",
									endUserActvityTypes);
							editor.commit(); // commit changes

						} else {

							((CircularProgressButton) v).setProgress(0);
							
							if (AddActivityGeneralInformationFragment.ActivityType == 41) { // full brand
								AddActivityFragment.pager.setCurrentItem(16);
							}
						}
					}
				});

		return view;
	}
}
