package co.nextix.jardine.activities.add.fragments;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;

import com.dd.CircularProgressButton;

public class AddActivityDetailsAndNotesFragment extends Fragment {
	
	private FragmentTransaction ft;
	
	private Fragment fragmentForTransition;
	
	private boolean trapping = false;
	
	private Bundle bundle;
	
	private int frag_layout_id;
	
	private Fragment fragment = null;

	private AddActivityFragment addActFrag;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.add_activity_activity_details_and_notes, container, false);

		bundle = getArguments();
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}
		
		addActFrag = (AddActivityFragment) fragment;
		addActFrag.pager.setCurrentItem(4);
		
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

					long customer = ((CustomerRecord) ((Spinner) view.findViewById(R.id.customer)).getSelectedItem()).getId();
					long area = ((PicklistRecord) ((Spinner) view.findViewById(R.id.area)).getSelectedItem()).getId();
					long province = ((ProvinceRecord) ((Spinner) view.findViewById(R.id.province)).getSelectedItem()).getId();
					long cityTown = ((CityTownRecord) ((Spinner) view.findViewById(R.id.city_town)).getSelectedItem()).getId();
					String objective = ((EditText) view.findViewById(R.id.objective)).getText().toString();
					long workplanEntry = ((WorkplanEntryRecord) ((Spinner) view.findViewById(R.id.workplan_entry)).getSelectedItem())
							.getId();
					int firstTimeVisit = ((CheckBox) view.findViewById(R.id.first_time_visit_checkbox)).isChecked() ? 1 : 0;
					int plannedTimeVisit = ((CheckBox) view.findViewById(R.id.planned_visit_checkbox)).isChecked() ? 1 : 0;

					String highlights = ((EditText) view.findViewById(R.id.highlights)).getText().toString();
					String notes = ((EditText) view.findViewById(R.id.notes)).getText().toString();
					String nextSteps = ((EditText) view.findViewById(R.id.next_steps)).getText().toString();
					String followUpCommittmentDate = ((TextView) view.findViewById(R.id.follow_up_commitment_date)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (objective != null && !objective.isEmpty() && notes != null && !notes.isEmpty() && highlights != null
							&& !highlights.isEmpty() && nextSteps != null && !nextSteps.isEmpty()) {

						fragmentForTransition = new AddActivityCustomerContactFragment();
						fragmentForTransition.setArguments(bundle);
						
						trapping = true;
						Editor editor = pref.edit();
						editor.putLong("customer", customer);
						editor.putLong("area", area);
						editor.putLong("province", province);
						editor.putLong("city_town", cityTown);
						editor.putLong("workplan_entry", workplanEntry);
						editor.putInt("first_time_visit", firstTimeVisit);
						editor.putInt("planned_time_visit", plannedTimeVisit);
						editor.putString("objective", objective);
						editor.putString("highlights", highlights);
						editor.putString("notes", notes);
						editor.putString("next_steps", nextSteps);
						editor.putString("follow_up_committment_date", followUpCommittmentDate);
						
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
					
					if(AddActivityGeneralInformationFragment.ActivityType == 1){
						ft = getActivity().getSupportFragmentManager().beginTransaction();
						ft.replace(frag_layout_id, fragmentForTransition);
						ft.addToBackStack(null);
						ft.commit();
					}
					/*addActFrag.pager.setCurrentItem(tabID);

					ft = getActivity().getSupportFragmentManager().beginTransaction();
					ft.replace(frag_layout_id, fragmentForTransition);
					ft.addToBackStack(null);
					ft.commit();*/
				}
			}
		});

		return view;
	}
}
