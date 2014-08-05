package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;

import co.nextix.jardine.activities.update.fragments.UpdateConstants;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.DocuInfoRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.database.records.ProjectRequirementRecord;
import co.nextix.jardine.keys.Constant;

import com.astuetz.PagerSlidingTabStrip;

public class AddActivityFragment extends Fragment {

	private View rootView = null;

	public static PagerSlidingTabStrip tabs;
	public static ViewPager pager;
	protected MyPagerAdapter adapter;

	public static long WORKPLAN_ENTRY_ID = 0;
	public static long ACTIVITY_TYPE = 0;
	public static int tabPosition = -1;
	public static boolean fragmentStart = false;
	public static boolean fromOther = true;

	private Bundle bundle;
	public static long activityID = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		AddActivityFragment.fromOther = true;
		UpdateConstants.RECORD = null;

		Constant.addJDIproductStockCheckRecords = new ArrayList<JDIproductStockCheckRecord>();
		Constant.addProductSupplierRecords = new ArrayList<ProductSupplierRecord>();
		Constant.addJDImerchandisingCheckRecords = new ArrayList<JDImerchandisingCheckRecord>();

		Constant.addCompetitorProductRecords = new ArrayList<CompetitorProductStockCheckRecord>();
		Constant.addMarketingIntelRecords = new ArrayList<MarketingIntelRecord>();
		Constant.addProjectRequirmentRecords = new ArrayList<ProjectRequirementRecord>();

		Constant.addProductFocusRecords = new ArrayList<ProductRecord>();
		Constant.addCustomerContactRecords = new ArrayList<CustomerContactRecord>();
		Constant.addDocuInfoRecords = new ArrayList<DocuInfoRecord>();

		rootView = inflater.inflate(R.layout.add_activity_fragment, container, false);

		bundle = getArguments();

		if (bundle != null) {
			activityID = bundle.getLong("activityID");
		}

		List<Fragment> fragments = getFragments();

		tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
		pager = (ViewPager) rootView.findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(), fragments);

		pager.setAdapter(adapter);
		pager.setCurrentItem(0);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager, false);

		return rootView;
	}

	protected void clearColorFilter(View view) {
		Drawable d = view.getBackground();
		view.invalidateDrawable(d);
		d.clearColorFilter();
	}

	protected void setClickable(View findViewById) {
		findViewById.setClickable(true);
	}

	public class MyPagerAdapter extends FragmentStatePagerAdapter {

		private final String[] TITLES = { "General Information", "Travel or Waiting", "With CoSMRs", "Admin Works",
				"Activity Details and Notes", "Customer Contact Person", "JDI Product Stock Check", "Product Supplier",
				"JDI Merchandising Check", "JDI Competitor Product Stock Check", "Marketing Intel", "Project Visit",
				"Project Requirements", "Trainings", "Identify Product Focus", "Full Brand Activation", "Activity Photos and Attachments" };

		private List<Fragment> fragments;

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}
	}

	private List<Fragment> getFragments() {
		List<Fragment> flist = new ArrayList<Fragment>();

		/* 0 */flist.add(AddActivityGeneralInformationFragment.instantiate(getActivity(), AddActivityGeneralInformationFragment.class.getName()));
		/* 1 */flist.add(AddActivityTravelWaitingFragment.instantiate(getActivity(), AddActivityTravelWaitingFragment.class.getName()));
		/* 2 */flist.add(AddActivityWithCoSMRsFragment.instantiate(getActivity(), AddActivityWithCoSMRsFragment.class.getName()));
		/* 3 */flist.add(AddActivityAdminWorksFragment.instantiate(getActivity(), AddActivityAdminWorksFragment.class.getName()));
		/* 4 */flist.add(AddActivityDetailsAndNotesFragment.instantiate(getActivity(), AddActivityDetailsAndNotesFragment.class.getName()));
		/* 5 */flist.add(ActivitiesCustomerContactList.instantiate(getActivity(), ActivitiesCustomerContactList.class.getName()));
		/* 6 */flist.add(AddJDIProductStockListFragment.instantiate(getActivity(), AddJDIProductStockListFragment.class.getName()));
		/* 7 */flist.add(AddActivityProductSupplierListFragment.instantiate(getActivity(), AddActivityProductSupplierListFragment.class.getName()));
		/* 8 */flist.add(JDIMerchandisingCheckFragmentAdd.instantiate(getActivity(), JDIMerchandisingCheckFragmentAdd.class.getName()));
		/* 9 */flist.add(CompetitorStockCheckFragmentAdd.instantiate(getActivity(), CompetitorStockCheckFragmentAdd.class.getName()));
		/* 10 */flist.add(MarketingIntelFragmentAdd.instantiate(getActivity(), MarketingIntelFragmentAdd.class.getName()));
		/* 11 */flist.add(AddActivityProjectVisitFragment.instantiate(getActivity(), AddActivityProjectVisitFragment.class.getName()));
		/* 12 */flist.add(AddActivityProjectRequirementsListFragment.instantiate(getActivity(), AddActivityProjectRequirementsListFragment.class.getName()));
		/* 13 */flist.add(AddActivityTrainingsFragment.instantiate(getActivity(), AddActivityTrainingsFragment.class.getName()));
		/* 14 */flist.add(AddIdentifyProductFocusFragment.instantiate(getActivity(), AddIdentifyProductFocusFragment.class.getName()));
		/* 15 */flist.add(AddActivityFullBrandActivationFragment.instantiate(getActivity(), AddActivityFullBrandActivationFragment.class.getName()));
		/* 16 */flist.add(ActivitiesDocumentList.instantiate(getActivity(), ActivitiesDocumentList.class.getName()));

		return flist;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		fragmentStart = false;
	}
}
