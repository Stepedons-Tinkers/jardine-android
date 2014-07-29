package co.nextix.jardine.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.R;
import co.nextix.jardine.profile.ProfileInformationFragment;
import co.nextix.jardine.profile.ProfileNoticationsFragment;
import co.nextix.jardine.view.group.utils.TabFactory;

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
		
		DashBoardActivity.tabIndex.clear();
		DashBoardActivity.fromAddActivities = false;

		tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		tabHost.setup();
		setupTabHost();

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

		myProfileContent = new ProfileInformationFragment();
		getFragmentManager().beginTransaction()
				.replace(R.id.profile_fl, myProfileContent).commit();
		tabHost.setCurrentTab(0);

		return rootView;
	}

	private void setupTabHost() {
		String tab1 = getResources().getString(R.string.profile_information);
		String tab2 = getResources().getString(R.string.profile_notification);
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec(tab1).setIndicator(tab1)
				.setContent(new TabFactory()));
		tabHost.addTab(tabHost.newTabSpec(tab2).setIndicator(tab2)
				.setContent(new TabFactory()));

		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			View v = tabHost.getTabWidget().getChildAt(i);
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.title);
			tv.setTextColor(getResources().getColor(R.color.tab_txt_color));
			v.setBackgroundResource(R.drawable.xml_tab_indicator);
		}

		tabHost.setCurrentTab(0);

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
