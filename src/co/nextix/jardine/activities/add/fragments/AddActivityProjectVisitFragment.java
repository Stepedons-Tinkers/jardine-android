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
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.keys.Constant;

import com.dd.CircularProgressButton;

public class AddActivityProjectVisitFragment extends Fragment {

	private ArrayAdapter<PicklistRecord> projectStage = null;
	private ArrayAdapter<PicklistRecord> projectCategory = null;

	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		List<PicklistRecord> projectStage = JardineApp.DB
				.getActivityProjectStage().getAllRecords();
		List<PicklistRecord> projectCategory = JardineApp.DB
				.getActivityProjectCategory().getAllRecords();

		this.projectStage = new ArrayAdapter<PicklistRecord>(getActivity()
				.getApplicationContext(), R.layout.add_activity_textview,
				projectStage);
		this.projectCategory = new ArrayAdapter<PicklistRecord>(getActivity()
				.getApplicationContext(), R.layout.add_activity_textview,
				projectCategory);

		final View rootView = inflater.inflate(
				R.layout.add_activity_project_visit, container, false);
		// SharedPreferences pref =
		// getActivity().getApplicationContext().getSharedPreferences("ActivityInfo",
		// 0);
		// long id = pref.getLong("activity_id_edit", 0);
		//
		// ActivityRecord record = JardineApp.DB.getActivity().getById(id);
		//
		// if (record != null) {
		// String projectName = null;
		// long projectStageRecord = 0;
		// long projectCategoryRecord = 0;
		//
		// try {
		// projectName = record.getProjectName();
		// projectStageRecord = record.getProjectStage();
		// projectCategoryRecord = record.getProjectCategory();
		//
		// } catch (Exception e) {
		//
		// }
		//
		// if (projectName != null || projectStageRecord != 0 ||
		// projectCategoryRecord != 0) {
		// ((TextView)
		// rootView.findViewById(R.id.project_name)).setText(projectName);
		//
		// for (int i = 0; i < projectStage.size(); i++) {
		// if (projectStage.get(i).getId() == projectStageRecord) {
		// ((Spinner)
		// rootView.findViewById(R.id.project_stage)).setSelection(i);
		// break;
		// }
		// }
		//
		// for (int i = 0; i < projectCategory.size(); i++) {
		// if (projectCategory.get(i).getId() == projectCategoryRecord) {
		// ((Spinner)
		// rootView.findViewById(R.id.project_category)).setSelection(i);
		// break;
		// }
		// }
		// }
		//
		// }

		((Spinner) rootView.findViewById(R.id.project_stage))
				.setAdapter(this.projectStage);
		((Spinner) rootView.findViewById(R.id.project_category))
				.setAdapter(this.projectCategory);

		((CircularProgressButton) rootView.findViewById(R.id.btnWithText1))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(final View v) {
						v.setClickable(false);
						v.setEnabled(false);

						if (((CircularProgressButton) v).getProgress() == 0) {

							ValueAnimator widthAnimation = ValueAnimator.ofInt(
									1, 100);
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
											((CircularProgressButton) v)
													.setProgress(value);

											if (!flag) {

												((CircularProgressButton) v)
														.setProgress(-1);
											}
										}
									});

							widthAnimation.start();

							EditText editProjName = (EditText) rootView
									.findViewById(R.id.project_name);
							Spinner spinProjectStage = (Spinner) rootView
									.findViewById(R.id.project_stage);
							Spinner spinProjectCategory = (Spinner) rootView
									.findViewById(R.id.project_category);
							String projectName = editProjName.getText()
									.toString();
							String projectStageString = spinProjectStage
									.getSelectedItem().toString();
							String projectCategoryString = spinProjectCategory
									.getSelectedItem().toString();
							long projectStageId = ((PicklistRecord) spinProjectStage
									.getSelectedItem()).getId();
							long projectCategoryId = ((PicklistRecord) spinProjectCategory
									.getSelectedItem()).getId();
							
							Constant.activityGeneralInfo.setProjectName(projectName);
							Constant.activityGeneralInfo.setProjectStage(projectStageId);
							Constant.activityGeneralInfo.setProjectCategory(projectCategoryId);

							/** Checking of required fields **/
							SharedPreferences pref = getActivity()
									.getApplicationContext()
									.getSharedPreferences("ActivityInfo", 0);

							if (projectName != null && !projectName.isEmpty()
									&& projectStageString != null
									&& !projectStageString.isEmpty()
									&& projectCategoryString != null
									&& !projectCategoryString.isEmpty()) {
								flag = true;

								
//								long projectStage = ((PicklistRecord) ((Spinner) rootView
//										.findViewById(R.id.project_stage))
//										.getSelectedItem()).getId();
//								long projectCategory = ((PicklistRecord) ((Spinner) rootView
//										.findViewById(R.id.project_category))
//										.getSelectedItem()).getId();
//								Editor editor = pref.edit();
//								editor.putString("project_name", projectName);
//								editor.putLong("project_stage", projectStage);
//								editor.putLong("project_category",
//										projectCategory);
//								editor.commit(); // commit changes
								
								

								v.setClickable(true);
								v.setEnabled(true);

							} else {
								editProjName.setError("Please Add Project Name");
								editProjName.setOnFocusChangeListener(new OnFocusChangeListener() {
									
									@Override
									public void onFocusChange(View v, boolean hasFocus) {
										// TODO Auto-generated method stub
										((TextView) v).setError(null);
									}
								});
								flag = false;
								Toast.makeText(
										getActivity(),
										"Please fill up required (RED COLOR) fields",
										Toast.LENGTH_SHORT).show();

								Handler handler = new Handler();
								handler.postDelayed(new Runnable() {

									@Override
									public void run() {
										((CircularProgressButton) v)
												.setProgress(0);
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
