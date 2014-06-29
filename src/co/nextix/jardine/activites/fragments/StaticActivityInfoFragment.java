package co.nextix.jardine.activites.fragments;

import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddActivityDIYorSupermarketFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityProjectVisitFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityRetailVisitFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityTrainingsFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityWithCoSMRsFragment;
import co.nextix.jardine.activities.update.fragments.EditActivityInfoFragment;

public class StaticActivityInfoFragment extends Fragment {

	private View rootView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.static_activity_info_fragment, container, false);
		android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();

		// Add a fucking fragment
		EditActivityInfoFragment myFragment = new EditActivityInfoFragment();
		final Bundle data = new Bundle();
		final Bundle args = getArguments();
		data.putString("crm_no", args.getString("crm_no"));
		myFragment.setArguments(data);
		
		fragmentTransaction.replace(R.id.activity_fragment, myFragment);
		fragmentTransaction.commit();

		((Button) this.rootView.findViewById(R.id.general_info_button)).getBackground().setColorFilter(
				new LightingColorFilter(0x0033FF, 0x0066FF));

		this.clearColorFilter(this.rootView.findViewById(R.id.with_co_smrs_button));
		this.clearColorFilter(this.rootView.findViewById(R.id.diy_or_supermarket_button));
		this.clearColorFilter(this.rootView.findViewById(R.id.retail_visit_button));
		this.clearColorFilter(this.rootView.findViewById(R.id.project_visit_button));
		this.clearColorFilter(this.rootView.findViewById(R.id.trainings_button));

		((Button) rootView.findViewById(R.id.general_info_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v.findViewById(R.id.general_info_button)).getBackground().setColorFilter(
						new LightingColorFilter(0x0033FF, 0x0066FF));

				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.with_co_smrs_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.diy_or_supermarket_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.retail_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.project_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.trainings_button));

				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragment
				EditActivityInfoFragment myFragment = new EditActivityInfoFragment();
				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});

		((Button) rootView.findViewById(R.id.with_co_smrs_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v.findViewById(R.id.with_co_smrs_button)).getBackground().setColorFilter(
						new LightingColorFilter(0x0033FF, 0x0066FF));

				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.general_info_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.diy_or_supermarket_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.retail_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.project_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.trainings_button));

				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragment
				AddActivityWithCoSMRsFragment myFragment = new AddActivityWithCoSMRsFragment();
				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});

		((Button) rootView.findViewById(R.id.diy_or_supermarket_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v.findViewById(R.id.diy_or_supermarket_button)).getBackground().setColorFilter(
						new LightingColorFilter(0x0033FF, 0x0066FF));

				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.with_co_smrs_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.general_info_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.retail_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.project_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.trainings_button));

				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragment
				AddActivityDIYorSupermarketFragment myFragment = new AddActivityDIYorSupermarketFragment();
				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});

		((Button) rootView.findViewById(R.id.retail_visit_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v.findViewById(R.id.retail_visit_button)).getBackground().setColorFilter(
						new LightingColorFilter(0x0033FF, 0x0066FF));

				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.with_co_smrs_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.diy_or_supermarket_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.general_info_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.project_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.trainings_button));

				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragment
				AddActivityRetailVisitFragment myFragment = new AddActivityRetailVisitFragment();
				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});

		((Button) rootView.findViewById(R.id.project_visit_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v.findViewById(R.id.project_visit_button)).getBackground().setColorFilter(
						new LightingColorFilter(0x0033FF, 0x0066FF));

				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.with_co_smrs_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.diy_or_supermarket_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.retail_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.general_info_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.trainings_button));

				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragment
				AddActivityProjectVisitFragment myFragment = new AddActivityProjectVisitFragment();
				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});

		((Button) rootView.findViewById(R.id.trainings_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v.findViewById(R.id.trainings_button)).getBackground()
						.setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));

				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.with_co_smrs_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.diy_or_supermarket_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.retail_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.project_visit_button));
				StaticActivityInfoFragment.this.clearColorFilter(rootView.findViewById(R.id.general_info_button));

				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Add a fucking fragment
				AddActivityTrainingsFragment myFragment = new AddActivityTrainingsFragment();
				fragmentTransaction.replace(R.id.activity_fragment, myFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});

		return this.rootView;
	}

	protected void clearColorFilter(View view) {
		Drawable d = view.getBackground();
		view.invalidateDrawable(d);
		d.clearColorFilter();
	}
}
