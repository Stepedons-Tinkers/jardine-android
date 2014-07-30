package co.nextix.jardine.activites.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.tables.ActivityTypeTable;

import com.astuetz.PagerSlidingTabStrip;

public class ActivityInfoFragment extends Fragment {

	private View myFragmentView = null;

	private PagerSlidingTabStrip tabs;
	private PagerSlidingTabStrip tabers;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	private int firstPos;

	private FragmentTransaction ft;

	public static FrameLayout fragmentLayout_1;
	public static FrameLayout fragmentLayout_2;

	private Bundle bundle;

	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;

	private int titles;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.myFragmentView = inflater.inflate(
				R.layout.fragment_activity_static_fields, container, false);

		ft = getFragmentManager().beginTransaction();
		fragmentLayout_1 = (FrameLayout) this.myFragmentView
				.findViewById(R.id.layoutForAddingFrag);
		fragmentLayout_2 = (FrameLayout) this.myFragmentView
				.findViewById(R.id.layoutForEditFrag);

		bundle = new Bundle();
		bundle.putInt("layoutID", fragmentLayout_1.getId());

		tabs = (PagerSlidingTabStrip) this.myFragmentView
				.findViewById(R.id.tabs);

		tabers = (PagerSlidingTabStrip) tabs;
		pager = (ViewPager) this.myFragmentView.findViewById(R.id.pager);
		pager.setCurrentItem(0);

		ArrayList<Integer> disableTabs = new ArrayList<Integer>();
		this.pref = getActivity().getApplicationContext().getSharedPreferences(
				"ActivityInfo", 0);
		this.activityRecord = JardineApp.DB.getActivity().getById(
				pref.getLong("activity_id", 0));

		bundle = getArguments();

		ActivityTypeTable type = JardineApp.DB.getActivityType();
		List<Fragment> fragment = null;
		if (type != null) {
			ActivityTypeRecord record = type.getById(this.activityRecord
					.getActivityType());
			if (record != null) {

				if (record.toString().equals("Travel")
						|| record.toString().equals("Waiting")) { // done
					fragment = getFragments(0);
					titles = 0;
				} else if (record.toString().equals(
						"Company Work-with Co-SMR/ Supervisor")) { // done
					titles = 1;
					fragment = getFragments(1);
				} else if (record.toString().equals("Admin Work")) { // done
					fragment = getFragments(2);
					titles = 2;
				} else if (record.toString().equals(
						"Retail Visits (Traditional Hardware)")
						|| record.toString().equals("Retail Visits (Merienda)")) { // done
					fragment = getFragments(3);
					titles = 3;
				} else if (record.toString().equals("KI Visits - On-site")) { // done
					fragment = getFragments(4);
					titles = 4;
				} else if (record.toString().contains("Major Training")) { // done
					fragment = getFragments(5);
					titles = 5;
				} else if (record.toString().contains("End User Activity")) { // done
					fragment = getFragments(6);
					titles = 6;
				} else if (record.toString().equals("Full Brand Activation")) { // done
					fragment = getFragments(7);
					titles = 7;
				} else {
					fragment = getFragments(8);
					titles = 8;
				}
			}
		}

		adapter = new MyPagerAdapter(getFragmentManager(), fragment);

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		pager.setCurrentItem(0);

		tabs.setViewPager(pager, true);

		return myFragmentView;
	}

	public class MyPagerAdapter extends FragmentStatePagerAdapter {

		private ArrayList<String> TITLES = new ArrayList<String>();
		private List<Fragment> fragments;

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
			switch(titles){
			
			case 0:
				TITLES.add("General Information");
				TITLES.add("Travel or Waiting");
				break;			
			case 1:
				TITLES.add("General Information");
				TITLES.add("With CoSMRs");
				break;
			case 2:
				TITLES.add("General Information");
				TITLES.add("Admin Works");
				break;
			case 3:
				TITLES.add("General Information");
				TITLES.add("Activity Details and Notes");
				TITLES.add("Customer Contact Person");
				TITLES.add("JDI Product Stock Check");
				TITLES.add("Product Supplier");
				TITLES.add("JDI Merchandising Check");
				TITLES.add("JDI Competitor Product Stock Check");
				TITLES.add("Marketing Intel");
				TITLES.add("Activity Photos and Attachments");
				break;
			case 4:
				TITLES.add("General Information");
				TITLES.add("Activity Details and Notes");
				TITLES.add("Customer Contact Person");
				TITLES.add("Marketing Intel");
				TITLES.add("Project Visit");
				TITLES.add("Project Requirements");
				TITLES.add("Activity Photos and Attachments");
				break;
			case 5:
				TITLES.add("General Information");
				TITLES.add("Activity Details and Notes");
				TITLES.add("Customer Contact Person");
				TITLES.add("JDI Product Stock Check");
				TITLES.add("Trainings");
				TITLES.add("Activity Photos and Attachments");
				break;
			case 6:
				TITLES.add("General Information");
				TITLES.add("Activity Details and Notes");
				TITLES.add("Customer Contact Person");
				TITLES.add("Activity Photos and Attachments");
				break;
			case 7:
				TITLES.add("General Information");
				TITLES.add("Activity Details and Notes");
				TITLES.add("Customer Contact Person");
				TITLES.add("Full Brand Activation");
				TITLES.add("Activity Photos and Attachments");
				break;
			default:
				TITLES.add("General Information");
				TITLES.add("Activity Details and Notes");
				TITLES.add("Customer Contact Person");
				TITLES.add("Activity Photos and Attachments");
				break;
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES.get(position);
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

	private List<Fragment> getFragments(int position) {
		List<Fragment> flist = new ArrayList<Fragment>();

		switch (position) {
		case 0:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityTravelOrWaitingFragment.instantiate(
					getActivity(),
					ActivityTravelOrWaitingFragment.class.getName()));
			break;
		case 1:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityWithCoSMRsFragment.instantiate(getActivity(),
					ActivityWithCoSMRsFragment.class.getName()));
			break;
		case 2:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityAdminWorksFragment.instantiate(getActivity(),
					ActivityAdminWorksFragment.class.getName()));
			break;
		case 3:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityDetailedInfoFragment.instantiate(getActivity(),
					ActivityDetailedInfoFragment.class.getName()));
			flist.add(CustomerContactPersonFragment.instantiate(getActivity(),
					CustomerContactPersonFragment.class.getName()));
			flist.add(JDIProductStockFragment.instantiate(getActivity(),
					JDIProductStockFragment.class.getName()));
			flist.add(ProductSupplierFragment.instantiate(getActivity(),
					ProductSupplierFragment.class.getName()));
			flist.add(JDIMerchandisingCheckFragment.instantiate(getActivity(),
					JDIMerchandisingCheckFragment.class.getName()));
			flist.add(CompetitorStockCheckFragment.instantiate(getActivity(),
					CompetitorStockCheckFragment.class.getName()));
			flist.add(MarketingIntelFragment.instantiate(getActivity(),
					MarketingIntelFragment.class.getName()));
			flist.add(PhotosAndAttachmentsFragment.instantiate(getActivity(),
					PhotosAndAttachmentsFragment.class.getName()));
			break;
		case 4:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityDetailedInfoFragment.instantiate(getActivity(),
					ActivityDetailedInfoFragment.class.getName()));
			flist.add(CustomerContactPersonFragment.instantiate(getActivity(),
					CustomerContactPersonFragment.class.getName()));
			flist.add(MarketingIntelFragment.instantiate(getActivity(),
					MarketingIntelFragment.class.getName()));
			flist.add(ActivityProjectVisitFragment.instantiate(getActivity(),
					ActivityProjectVisitFragment.class.getName()));
			flist.add(ProjectRequirementsFragment.instantiate(getActivity(),
					ProjectRequirementsFragment.class.getName()));
			flist.add(PhotosAndAttachmentsFragment.instantiate(getActivity(),
					PhotosAndAttachmentsFragment.class.getName()));
			break;
		case 5:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityDetailedInfoFragment.instantiate(getActivity(),
					ActivityDetailedInfoFragment.class.getName()));
			flist.add(CustomerContactPersonFragment.instantiate(getActivity(),
					CustomerContactPersonFragment.class.getName()));
			flist.add(JDIProductStockFragment.instantiate(getActivity(),
					JDIProductStockFragment.class.getName()));
			flist.add(ActivityTrainingsFragment.instantiate(getActivity(),
					ActivityTrainingsFragment.class.getName()));
			flist.add(PhotosAndAttachmentsFragment.instantiate(getActivity(),
					PhotosAndAttachmentsFragment.class.getName()));
			break;
		case 6:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityDetailedInfoFragment.instantiate(getActivity(),
					ActivityDetailedInfoFragment.class.getName()));
			flist.add(CustomerContactPersonFragment.instantiate(getActivity(),
					CustomerContactPersonFragment.class.getName()));
			flist.add(PhotosAndAttachmentsFragment.instantiate(getActivity(),
					PhotosAndAttachmentsFragment.class.getName())); // identify
																	// product
																	// focus
			flist.add(PhotosAndAttachmentsFragment.instantiate(getActivity(),
					PhotosAndAttachmentsFragment.class.getName()));
			break;
		case 7:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityDetailedInfoFragment.instantiate(getActivity(),
					ActivityDetailedInfoFragment.class.getName()));
			flist.add(CustomerContactPersonFragment.instantiate(getActivity(),
					CustomerContactPersonFragment.class.getName()));
			flist.add(ActivityBrandActivationFragment.instantiate(
					getActivity(),
					ActivityBrandActivationFragment.class.getName()));
			flist.add(PhotosAndAttachmentsFragment.instantiate(getActivity(),
					PhotosAndAttachmentsFragment.class.getName()));
			break;

		default:
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),
					ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityDetailedInfoFragment.instantiate(getActivity(),
					ActivityDetailedInfoFragment.class.getName()));
			flist.add(CustomerContactPersonFragment.instantiate(getActivity(),
					CustomerContactPersonFragment.class.getName()));
			flist.add(PhotosAndAttachmentsFragment.instantiate(getActivity(),
					PhotosAndAttachmentsFragment.class.getName()));

		}
		return flist;
	}
}
