package co.nextix.jardine.activities.add.fragments;

import java.util.List;

import com.dd.CircularProgressButton;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.PicklistRecord;

public class AddActivityProjectVisitFragment extends Fragment {
	
	private ArrayAdapter<PicklistRecord> projectStage = null;
	private ArrayAdapter<PicklistRecord> projectCategory = null;
	
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<PicklistRecord> projectStage = JardineApp.DB.getActivityProjectStage().getAllRecords();
		List<PicklistRecord> projectCategory = JardineApp.DB.getActivityProjectCategory().getAllRecords();
		
		this.projectStage = new ArrayAdapter<PicklistRecord>(getActivity().getApplicationContext(), R.layout.add_activity_textview, projectStage);
		this.projectCategory = new ArrayAdapter<PicklistRecord>(getActivity().getApplicationContext(), R.layout.add_activity_textview, projectCategory);
		
		final View rootView = inflater.inflate(R.layout.add_activity_project_visit, container, false);
		((TextView) rootView.findViewById(R.id.project_name)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("project_name", v.getText().toString());
					editor.putString("project_stage", String.valueOf(((Spinner) rootView.findViewById(R.id.project_stage)).getSelectedItem()));
					editor.putString("project_category", String.valueOf(((Spinner) rootView.findViewById(R.id.project_category)).getSelectedItem()));
					editor.commit(); // commit changes
				}

				return false;
			}
		});
		
		((Spinner) rootView.findViewById(R.id.project_stage)).setAdapter(this.projectStage);
		((Spinner) rootView.findViewById(R.id.project_category)).setAdapter(this.projectCategory);
		
//		((Spinner) rootView.findViewById(R.id.project_stage)).setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//				SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
//				Editor editor = pref.edit();
//				editor.putString("project_name", ((EditText) rootView.findViewById(R.id.project_name)).getText().toString());
//				editor.putString("project_stage", String.valueOf(parent.getSelectedItem()));
//				editor.putString("project_category", String.valueOf(((Spinner) rootView.findViewById(R.id.project_category)).getSelectedItem()));
//				editor.commit(); // commit changes
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				if (parent.getSelectedItemPosition() == 0) {
//					Toast.makeText(getActivity(), "This field requires data", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
//
//		((Spinner) rootView.findViewById(R.id.project_category)).setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//				SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
//				Editor editor = pref.edit();
//				editor.putString("project_name", ((EditText) rootView.findViewById(R.id.project_name)).getText().toString());
//				editor.putString("project_category", String.valueOf(parent.getSelectedItem()));
//				editor.putString("project_stage", String.valueOf(((Spinner) rootView.findViewById(R.id.project_stage)).getSelectedItem()));
//				editor.commit(); // commit changes
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				if (parent.getSelectedItemPosition() == 0) {
//					Toast.makeText(getActivity(), "This field requires data", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
		
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

							if (!flag) {

								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();
					
//					String details = ((EditText) rootView.findViewById(R.id.details)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

					if(true){
						flag = true;
						Editor editor = pref.edit();
//						editor.putString("details", details );
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
					((CircularProgressButton) v).setProgress(0);
					
					if (AddActivityGeneralInformationFragment.ActivityType == 9) { // ki visits
						DashBoardActivity.tabIndex.add(5, 12);
						AddActivityFragment.pager.setCurrentItem(12);
					}
				}
			}
		});
		
		return rootView;
	}
}
