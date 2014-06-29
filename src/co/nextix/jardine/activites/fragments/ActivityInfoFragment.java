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
import android.widget.Toast;
import co.nextix.jardine.R;

public class ActivityInfoFragment extends Fragment {

	private View myFragmentView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.myFragmentView = inflater.inflate(R.layout.fragment_activity_static_fields, container, false);
		android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();

		// Add a fucking fragment
		StaticActivityInfoFragment myFragmentAddActivity = new StaticActivityInfoFragment();
		final Bundle data = new Bundle();
		final Bundle args = getArguments();
		data.putString("crm_no", args.getString("crm_no"));
		myFragmentAddActivity.setArguments(data);
		
		fragmentTransaction.replace(R.id.second_header_tab, myFragmentAddActivity);
		fragmentTransaction.commit();

		((Button) this.myFragmentView.findViewById(R.id.activity_info_button)).getBackground().setColorFilter(
				new LightingColorFilter(0x0033FF, 0x0066FF));

		this.clearColorFilter(this.myFragmentView.findViewById(R.id.more_info_button));

		((Button) this.myFragmentView.findViewById(R.id.more_info_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v.findViewById(R.id.more_info_button)).getBackground()
						.setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));

				ActivityInfoFragment.this.clearColorFilter(ActivityInfoFragment.this.myFragmentView.findViewById(R.id.activity_info_button));
				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();

				// Add a fucking fragment
				MoreActivityInformationFragment myFragment = new MoreActivityInformationFragment();
				fragmentTransaction.replace(R.id.second_header_tab, myFragment);
				fragmentTransaction.commit();
			}
		});

		((Button) this.myFragmentView.findViewById(R.id.activity_info_button)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v.findViewById(R.id.activity_info_button)).getBackground().setColorFilter(
						new LightingColorFilter(0x0033FF, 0x0066FF));

				ActivityInfoFragment.this.clearColorFilter(ActivityInfoFragment.this.myFragmentView.findViewById(R.id.more_info_button));
				android.support.v4.app.FragmentManager fragmentActivityDetailManager = getActivity().getSupportFragmentManager();
				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentActivityDetailManager.beginTransaction();

				// Add a fucking fragment
				StaticActivityInfoFragment myFragment = new StaticActivityInfoFragment();
				fragmentTransaction.replace(R.id.second_header_tab, myFragment);
				fragmentTransaction.commit();
			}
		});

		return myFragmentView;
	}

	protected void clearColorFilter(View view) {
		Drawable d = view.getBackground();
		view.invalidateDrawable(d);
		d.clearColorFilter();
	}
}
