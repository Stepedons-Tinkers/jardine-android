package co.nextix.jardine.activities.add.fragments;

import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.SMRRecord;

import com.dd.CircularProgressButton;

public class AddActivityWithCoSMRsFragment extends Fragment {
	private static volatile AddActivityWithCoSMRsFragment instance = null;
	private boolean flag = false;

	public static AddActivityWithCoSMRsFragment getInstance() {
		if (instance == null) {
			synchronized (AddActivityWithCoSMRsFragment.class) {
				// Double check
				if (instance == null) {
					instance = new AddActivityWithCoSMRsFragment();
				}
			}
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.add_activity_with_co_smrs, container, false);
		List<SMRRecord> smrList = JardineApp.DB.getSMR().getAllRecords();
		ArrayAdapter<SMRRecord> smrAdapter = new ArrayAdapter<SMRRecord>(JardineApp.context, R.layout.add_activity_textview, smrList);

		((Spinner) rootView.findViewById(R.id.smr)).setAdapter(smrAdapter);
		((Spinner) rootView.findViewById(R.id.smr)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View rootView, int position, long id) {
				SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
				Editor editor = pref.edit();
				editor.putLong("smr", ((SMRRecord) parent.getSelectedItem()).getId());
				editor.commit(); // commit changes
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				if (parent.getSelectedItemPosition() == 0) {
					Toast.makeText(getActivity(), "This field is required", Toast.LENGTH_SHORT).show();
				}
			}
		});

		((CircularProgressButton) rootView.findViewById(R.id.btnWithText1)).setOnClickListener(new View.OnClickListener() {

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

					long smr = ((SMRRecord) ((Spinner) rootView.findViewById(R.id.smr)).getSelectedItem()).getId();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (smr != 0) {

						flag = true;
						Editor editor = pref.edit();
						editor.putLong("smr", smr);
						editor.commit(); // commit changes

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								getFragmentManager().popBackStackImmediate();
							}

						}, 2700);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);

							}
						}, 1500);
					}

				} else {

					// Code here
					((CircularProgressButton) v).setProgress(0);
				}
			}
		});

		return rootView;
	}
}
