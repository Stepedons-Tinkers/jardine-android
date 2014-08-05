package co.nextix.jardine.activities.update.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.UserRecord;

import com.dd.CircularProgressButton;

public class UpdateWithCoSMRFragment extends Fragment {

	private View view;

	private CircularProgressButton saveBtn;

	private Spinner sSMR;

	private List<UserRecord> userList;
	private ArrayAdapter<UserRecord> smrAdapter;
	
	private boolean trapping = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_with_co_smr, container, false);

		userList = JardineApp.DB.getUser().getAllRecords();
		smrAdapter = new ArrayAdapter<UserRecord>(JardineApp.context,
				R.layout.add_activity_textview, userList);

		sSMR = (Spinner) view.findViewById(R.id.co_smr);
		sSMR.setAdapter(smrAdapter);
		
		if(UpdateConstants.RECORD != null){
			sSMR.setSelection(Integer.parseInt(String.valueOf(UpdateConstants.CO_SMR)));
		}

		saveBtn = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
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
					
					String smrString = ((UserRecord) sSMR.getSelectedItem()).toString();

					if (smrString != null && !smrString.isEmpty()) {
						trapping = true;
						
						UpdateConstants.ACTIVITY_RECORD.setSmr(((UserRecord) sSMR.getSelectedItem()).getId());

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
