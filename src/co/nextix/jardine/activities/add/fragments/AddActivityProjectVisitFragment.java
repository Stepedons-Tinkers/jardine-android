package co.nextix.jardine.activities.add.fragments;

import java.util.List;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.PicklistRecord;

import com.dd.CircularProgressButton;

public class AddActivityProjectVisitFragment extends Fragment {

	private ArrayAdapter<PicklistRecord> projectStage = null;
	private ArrayAdapter<PicklistRecord> projectCategory = null;

	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<PicklistRecord> projectStage = JardineApp.DB.getActivityProjectStage().getAllRecords();
		List<PicklistRecord> projectCategory = JardineApp.DB.getActivityProjectCategory().getAllRecords();

		this.projectStage = new ArrayAdapter<PicklistRecord>(getActivity().getApplicationContext(), R.layout.add_activity_textview,
				projectStage);
		this.projectCategory = new ArrayAdapter<PicklistRecord>(getActivity().getApplicationContext(), R.layout.add_activity_textview,
				projectCategory);

		final View rootView = inflater.inflate(R.layout.add_activity_project_visit, container, false);
		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
		long id = pref.getLong("activity_id_edit", 0);

		ActivityRecord record = JardineApp.DB.getActivity().getById(id);

		if (record != null) {
			String projectName = record.getProjectName();
			long projectStageRecord = record.getProjectStage();
			long projectCategoryRecord = record.getProjectCategory();

			if (projectName != null && !projectName.isEmpty() && projectStageRecord != 0 && projectCategoryRecord != 0) {
				((TextView) rootView.findViewById(R.id.project_name)).setText(projectName);

				for (int i = 0; i < projectStage.size(); i++) {
					if (projectStage.get(i).getId() == projectStageRecord) {
						((Spinner) rootView.findViewById(R.id.project_stage)).setSelection(i);
						break;
					}
				}

				for (int i = 0; i < projectCategory.size(); i++) {
					if (projectCategory.get(i).getId() == projectCategoryRecord) {
						((Spinner) rootView.findViewById(R.id.project_category)).setSelection(i);
						break;
					}
				}
			}

		}

		((TextView) rootView.findViewById(R.id.project_name)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("project_name", v.getText().toString());
					editor.putString("project_stage",
							String.valueOf(((Spinner) rootView.findViewById(R.id.project_stage)).getSelectedItem()));
					editor.putString("project_category",
							String.valueOf(((Spinner) rootView.findViewById(R.id.project_category)).getSelectedItem()));
					editor.commit(); // commit changes
				}

				return false;
			}
		});

		((Spinner) rootView.findViewById(R.id.project_stage)).setAdapter(this.projectStage);
		((Spinner) rootView.findViewById(R.id.project_category)).setAdapter(this.projectCategory);

		// ((Spinner)
		// rootView.findViewById(R.id.project_stage)).setOnItemSelectedListener(new
		// OnItemSelectedListener() {
		//
		// @Override
		// public void onItemSelected(AdapterView<?> parent, View view, int
		// position, long id) {
		// SharedPreferences pref =
		// getActivity().getApplicationContext().getSharedPreferences("ActivityInfo",
		// 0);
		// Editor editor = pref.edit();
		// editor.putString("project_name", ((EditText)
		// rootView.findViewById(R.id.project_name)).getText().toString());
		// editor.putString("project_stage",
		// String.valueOf(parent.getSelectedItem()));
		// editor.putString("project_category", String.valueOf(((Spinner)
		// rootView.findViewById(R.id.project_category)).getSelectedItem()));
		// editor.commit(); // commit changes
		// }
		//
		// @Override
		// public void onNothingSelected(AdapterView<?> parent) {
		// if (parent.getSelectedItemPosition() == 0) {
		// Toast.makeText(getActivity(), "This field requires data",
		// Toast.LENGTH_SHORT).show();
		// }
		// }
		// });
		//
		// ((Spinner)
		// rootView.findViewById(R.id.project_category)).setOnItemSelectedListener(new
		// OnItemSelectedListener() {
		//
		// @Override
		// public void onItemSelected(AdapterView<?> parent, View view, int
		// position, long id) {
		// SharedPreferences pref =
		// getActivity().getApplicationContext().getSharedPreferences("ActivityInfo",
		// 0);
		// Editor editor = pref.edit();
		// editor.putString("project_name", ((EditText)
		// rootView.findViewById(R.id.project_name)).getText().toString());
		// editor.putString("project_category",
		// String.valueOf(parent.getSelectedItem()));
		// editor.putString("project_stage", String.valueOf(((Spinner)
		// rootView.findViewById(R.id.project_stage)).getSelectedItem()));
		// editor.commit(); // commit changes
		// }
		//
		// @Override
		// public void onNothingSelected(AdapterView<?> parent) {
		// if (parent.getSelectedItemPosition() == 0) {
		// Toast.makeText(getActivity(), "This field requires data",
		// Toast.LENGTH_SHORT).show();
		// }
		// }
		// });

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

							if (!flag) {

								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					String projectName = ((EditText) rootView.findViewById(R.id.project_name)).getText().toString();
					long projectStage = ((PicklistRecord) ((Spinner) rootView.findViewById(R.id.project_stage)).getSelectedItem()).getId();
					long projectCategory = ((PicklistRecord) ((Spinner) rootView.findViewById(R.id.project_category)).getSelectedItem())
							.getId();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

					if (projectName != null && !projectName.isEmpty()) {
						flag = true;
						Editor editor = pref.edit();
						editor.putString("project_name", projectName);
						editor.putLong("project_stage", projectStage);
						editor.putLong("project_category", projectCategory);
						editor.commit(); // commit changes

						v.setClickable(true);
						v.setEnabled(true);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

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

					if (AddActivityGeneralInformationFragment.ActivityType == 9) { // ki
																					// visits
						DashBoardActivity.tabIndex.add(5, 12);
						AddActivityFragment.pager.setCurrentItem(12);
					}
				}
			}
		});

		return rootView;
	}
}
