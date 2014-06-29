package co.nextix.jardine.collaterals;

import co.nextix.jardine.R;
import co.nextix.jardine.view.group.utils.TabFactory;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

public class CollateralsDetails extends Fragment implements OnTabChangeListener {

	private View view;
	private TabHost tabHost;
	private String tab1, tab2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		CollateralsConstants.ROW_ID = getArguments().getLong(
				CollateralsConstants.KEY_ROW_ID);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		view = inflater.inflate(R.layout.collaterals, container, false);
		initLayout();
		return view;
	}

	public static CollateralsDetails newInstance(long id) {
		CollateralsDetails fragment = new CollateralsDetails();
		Bundle b = new Bundle();
		b.putLong(CollateralsConstants.KEY_ROW_ID, id);
		fragment.setArguments(b);
		return fragment;
	}

	private void initLayout() {

		tabHost = (TabHost) view.findViewById(R.id.thCollaterals1);
		if (CollateralsConstants.FROM_WHERE == 1) {
			tab1 = getResources().getString(
					R.string.collaterals_event_protocols_detail);
		} else {
			tab1 = getResources().getString(
					R.string.collaterals_marketing_material_detail);
		}

		tab2 = getResources().getString(
				R.string.collaterals_event_protocols_files);
		tabHost.setOnTabChangedListener(this);
		setupTabHost();
	}

	private void setupTabHost() {
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

	@Override
	public void onTabChanged(String tabId) {

		Fragment fragment = new CollateralsEventProtocols();
		switch (tabHost.getCurrentTab()) {
		case 0:
			if (CollateralsConstants.FROM_WHERE == 1)
				fragment = CollateralsGeneralInformation
						.newInstance(CollateralsConstants.ROW_ID);
			else
				fragment = CollateralsMMGeneralInformation
						.newInstance(CollateralsConstants.ROW_ID);
			break;
		case 1:
			fragment = CollateralsEventFiles
					.newInstance(CollateralsConstants.ROW_ID);
			break;
		}

		getChildFragmentManager().beginTransaction()
				.replace(R.id.flCollateraslTabData, fragment).commit();
	}
}
