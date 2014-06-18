package co.nextix.jardine.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import co.nextix.jardine.R;
import co.nextix.jardine.profile.ProfileInformationFragment;
import co.nextix.jardine.profile.ProfileNoticationsFragment;

public class ProfileFragment extends Fragment {

	TabHost tabHost;
	Fragment myProfileContent;

	public ProfileFragment() {
		// TODO Auto-generated constructor stub
	}
	
	public static Fragment newInstance() {
		ProfileFragment fragment = new ProfileFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		View rootView = inflater.inflate(R.layout.fragment_profile, container,
				false);

		tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.addTab(tabHost
				.newTabSpec(
						getResources().getString(R.string.profile_information))
				.setIndicator(
						getResources().getString(R.string.profile_information))
				.setContent(new TabFactory()));
		tabHost.addTab(tabHost
				.newTabSpec(
						getResources().getString(R.string.profile_notification))
				.setIndicator(
						getResources().getString(R.string.profile_notification))
				.setContent(new TabFactory()));
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String arg0) {
				switch (tabHost.getCurrentTab()) {
				case 0:
					myProfileContent = new ProfileInformationFragment();
					break;
				case 1:
					myProfileContent = new ProfileNoticationsFragment();
					break;
				}

				getFragmentManager().beginTransaction()
						.replace(R.id.profile_fl, myProfileContent).commit();
			}
		});

		tabHost.setCurrentTab(0);

		return rootView;
	}

	private class TabFactory implements TabContentFactory {

		@Override
		public View createTabContent(String tag) {
			View v = new View(getActivity());
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}

	}

}
