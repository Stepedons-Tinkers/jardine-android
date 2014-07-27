package co.nextix.jardine.activities.add.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.PicklistRecord;

import com.dd.CircularProgressButton;

public class AddActivityEndUser extends Fragment {

	private boolean trapping = false;
	private ArrayAdapter<PicklistRecord> endUserAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<PicklistRecord> endUser = JardineApp.DB.getActEndUserActivityType().getAllRecords();
		this.endUserAdapter = new ArrayAdapter<PicklistRecord>(getActivity().getApplicationContext(),
				R.layout.add_activity_multi_select_listview, endUser);

		final View view = inflater.inflate(R.layout.add_activity_end_user, container, false);

		((Spinner) view.findViewById(R.id.end_user_activity_types)).setAdapter(this.endUserAdapter);
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

							if (!trapping) {
								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					String endUserActivityTypes = ((Spinner) view.findViewById(R.id.end_user_activity_types)).getSelectedItem().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (endUserActivityTypes != null && !endUserActivityTypes.isEmpty()) {

						trapping = true;
						ListView tags = (ListView) view.findViewById(R.id.list);
						Tag tag = null;

						SparseBooleanArray checked = tags.getCheckedItemPositions();
						for (int i = 0; i < checked.size(); i++) {
							if (checked.valueAt(i) == true) {
								tag = (Tag) tags.getItemAtPosition(checked.keyAt(i));
								Log.i(JardineApp.TAG, i + " " + tag);
							}
						}

						Editor editor = pref.edit();
						editor.putString("end_user_activity_types", tag.toString());
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

		return view;
	}
}
