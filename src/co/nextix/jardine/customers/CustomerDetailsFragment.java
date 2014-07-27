package co.nextix.jardine.customers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import co.nextix.jardine.R;
import co.nextix.jardine.view.group.utils.TabFactory;

public class CustomerDetailsFragment extends Fragment implements
		OnTabChangeListener {

	private View view;
	private TabHost tabHost;
	private String tab1, tab2;
	private long customerId = 0;
	private String customerName;

	public CustomerDetailsFragment() {

	}

	public static CustomerDetailsFragment newInstance(long custId) {

		CustomerDetailsFragment fragment = new CustomerDetailsFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(CustomerConstants.KEY_CUSTOMER_LONG_ID, custId);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		customerId = getArguments().getLong(
				CustomerConstants.KEY_CUSTOMER_LONG_ID);

		customerName = getArguments().getString(
				CustomerConstants.KEY_CUSTOMER_USERNAME);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		view = inflater.inflate(R.layout.collaterals, container, false);
		initLayout();
		return view;
	}

	private void initLayout() {

		tabHost = (TabHost) view.findViewById(R.id.thCollaterals1);
		tab1 = getResources().getString(R.string.capslock_customer_details);
		tab2 = getResources().getString(
				R.string.capslock_customer_contact_person);
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

		Fragment fragment = new ViewAllCustomersFragment();
		switch (tabHost.getCurrentTab()) {
		case 0:
			fragment = CustomerGeneralInformation.newInstance(customerId);
			break;
		case 1:
			fragment = CustomerContactList.newInstance(customerId);
			break;
		}

		getChildFragmentManager().beginTransaction()
				.replace(R.id.flCollateraslTabData, fragment).commit();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getTargetFragment().setMenuVisibility(true);
	}

	@Override
	public void onResume() {
		super.onResume();
		getTargetFragment().setMenuVisibility(false);
	}

}
