package co.nextix.jardine.workplan;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.collaterals.CollateralsEventFiles;
import co.nextix.jardine.collaterals.CollateralsEventProtocols;
import co.nextix.jardine.collaterals.CollateralsGeneralInformation;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.tables.WorkplanEntryTable;
import co.nextix.jardine.view.group.utils.TabFactory;

public class WorkPlanFragmentDetails extends Fragment implements
		OnTabChangeListener {

	private View view;
	private TabHost tabHost;
	private String tab1, tab2;
	private static String KEY_WORKPLAN = "workplan";
	private long workplanNo;

	public static WorkPlanFragmentDetails newInstance(long id) {
		WorkPlanFragmentDetails workplan = new WorkPlanFragmentDetails();
		Bundle bundle = new Bundle();
		bundle.putLong(KEY_WORKPLAN, id);
		workplan.setArguments(bundle);
		return workplan;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		view = inflater.inflate(R.layout.collaterals, container, false);
		initLayout();
		return view;
	}

	private void initLayout() {

		WorkPlanConstants.WORKPLAN_ID = getArguments().getLong(KEY_WORKPLAN);

		tabHost = (TabHost) view.findViewById(R.id.thCollaterals1);
		tab1 = getResources().getString(R.string.workplan_details);
		tab2 = getResources().getString(R.string.workplan_activities);
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

		Fragment fragment = new WorkPlanFragmentGeneralInfo();
		switch (tabHost.getCurrentTab()) {
		case 0:
			fragment = new WorkPlanFragmentGeneralInfo();
			break;
		case 1:
			fragment = new WorkPlanFragmentActivities();
			break;
		}

		getChildFragmentManager().beginTransaction()
				.replace(R.id.flCollateraslTabData, fragment).commit();
	}
}
