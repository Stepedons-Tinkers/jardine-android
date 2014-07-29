package co.nextix.jardine.activites.fragments;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.SuperAwesomeCardFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityDIYorSupermarketFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityProjectVisitFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityRetailVisitFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityTrainingsFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityWithCoSMRsFragment;
import co.nextix.jardine.activities.update.fragments.EditActivityInfoFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.utils.MyDateUtils;

import com.astuetz.PagerSlidingTabStrip;

public class ActivityInfoFragment extends Fragment {

	private View myFragmentView = null;

	private PagerSlidingTabStrip tabs;
	private PagerSlidingTabStrip tabers;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	private int firstPos;

	private FragmentTransaction ft;

	private FrameLayout fl;

	private Bundle bundle;

	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.myFragmentView = inflater.inflate(
				R.layout.fragment_activity_static_fields, container, false);

		ft = getFragmentManager().beginTransaction();
		fl = (FrameLayout) this.myFragmentView
				.findViewById(R.id.layoutForAddingFrag);

		bundle = new Bundle();
		bundle.putInt("layoutID", fl.getId());

		tabs = (PagerSlidingTabStrip) this.myFragmentView
				.findViewById(R.id.tabs);

		tabers = (PagerSlidingTabStrip) tabs;
		pager = (ViewPager) this.myFragmentView.findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		pager.setCurrentItem(0);

//		ArrayList<Integer> disableTabs = new ArrayList<Integer>();
//		this.pref = getActivity().getApplicationContext().getSharedPreferences(
//				"ActivityInfo", 0);
//		this.activityRecord = JardineApp.DB.getActivity().getById(
//				pref.getLong("activity_id", 0));
//
//		bundle = getArguments();
//
//		ActivityTypeTable type = JardineApp.DB.getActivityType();
//		if (type != null) {
//			ActivityTypeRecord record = type.getById(this.activityRecord
//					.getActivityType());
//
//			if (record != null) {
//
//				if (record.toString().equals("Travel")
//						|| record.toString().equals("Waiting")) { // done
//					disableTabs.add(2);
//					disableTabs.add(3);
//					disableTabs.add(4);
//					disableTabs.add(5);
//					disableTabs.add(6);
//					disableTabs.add(7);
//					disableTabs.add(8);
//					disableTabs.add(9);
//					disableTabs.add(10);
//					disableTabs.add(11);
//					disableTabs.add(12);
//					disableTabs.add(13);
//					disableTabs.add(14);
//					disableTabs.add(15);
//					disableTabs.add(16);
//				} else if (record.toString().equals(
//						"Company Work-with Co-SMR/ Supervisor")) { // done
//					disableTabs.add(1);
//					disableTabs.add(3);
//					disableTabs.add(4);
//					disableTabs.add(5);
//					disableTabs.add(6);
//					disableTabs.add(7);
//					disableTabs.add(8);
//					disableTabs.add(9);
//					disableTabs.add(10);
//					disableTabs.add(11);
//					disableTabs.add(12);
//					disableTabs.add(13);
//					disableTabs.add(14);
//					disableTabs.add(15);
//					disableTabs.add(16);
//				} else if (record.toString().equals("Admin Work")) { // done
//					disableTabs.add(1);
//					disableTabs.add(2);
//					disableTabs.add(4);
//					disableTabs.add(5);
//					disableTabs.add(6);
//					disableTabs.add(7);
//					disableTabs.add(8);
//					disableTabs.add(9);
//					disableTabs.add(10);
//					disableTabs.add(11);
//					disableTabs.add(12);
//					disableTabs.add(13);
//					disableTabs.add(14);
//					disableTabs.add(15);
//					disableTabs.add(16);
//				} else if (record.toString().equals(
//						"Retail Visits (Traditional Hardware)")
//						|| record.toString().equals("Retail Visits (Merienda)")) { // done
//					disableTabs.add(1);
//					disableTabs.add(2);
//					disableTabs.add(3);
//					disableTabs.add(11);
//					disableTabs.add(12);
//					disableTabs.add(13);
//					disableTabs.add(14);
//					disableTabs.add(15);
//				} else if (record.toString().equals("KI Visits - On-site")) { // done
//					disableTabs.add(1);
//					disableTabs.add(2);
//					disableTabs.add(3);
//					disableTabs.add(6);
//					disableTabs.add(7);
//					disableTabs.add(8);
//					disableTabs.add(9);
//					disableTabs.add(13);
//					disableTabs.add(14);
//					disableTabs.add(15);
//				} else if (record.toString().contains("Major Training")) { // done
//					disableTabs.add(1);
//					disableTabs.add(2);
//					disableTabs.add(3);
//					disableTabs.add(6);
//					disableTabs.add(7);
//					disableTabs.add(8);
//					disableTabs.add(9);
//					disableTabs.add(10);
//					disableTabs.add(11);
//					disableTabs.add(12);
//					disableTabs.add(14);
//					disableTabs.add(15);
//				} else if (record.toString().contains("End User Activity")) { // done
//					disableTabs.add(1);
//					disableTabs.add(2);
//					disableTabs.add(3);
//					disableTabs.add(6);
//					disableTabs.add(7);
//					disableTabs.add(8);
//					disableTabs.add(9);
//					disableTabs.add(10);
//					disableTabs.add(11);
//					disableTabs.add(12);
//					disableTabs.add(13);
//					disableTabs.add(15);
//				} else if (record.toString().equals("Full Brand Activation")) { // done
//					disableTabs.add(1);
//					disableTabs.add(2);
//					disableTabs.add(3);
//					disableTabs.add(6);
//					disableTabs.add(7);
//					disableTabs.add(8);
//					disableTabs.add(9);
//					disableTabs.add(10);
//					disableTabs.add(11);
//					disableTabs.add(12);
//					disableTabs.add(13);
//					disableTabs.add(14);
//				} else {
//					disableTabs.add(1);
//					disableTabs.add(2);
//					disableTabs.add(3);
//					disableTabs.add(6);
//					disableTabs.add(7);
//					disableTabs.add(8);
//					disableTabs.add(9);
//					disableTabs.add(10);
//					disableTabs.add(11);
//					disableTabs.add(12);
//					disableTabs.add(13);
//					disableTabs.add(14);
//					disableTabs.add(15);
//				}
//			}
//		}
		 tabs.setViewPager(pager, true);
//		tabs.setViewPagerForDisableSpecificTab(pager, false, disableTabs);
		tabs.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				firstPos = position;

				ft.setCustomAnimations(R.anim.slide_in_left,
						R.anim.slide_out_left);

				if (position == 0) {

					// General Information
					ActivityGeneralInfoFragment editActInfoFrag = new ActivityGeneralInfoFragment();
					editActInfoFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, editActInfoFrag);
					ft.commit();
				} else if (position == 1) {

					// Travel or Waiting
					ActivityTravelOrWaitingFragment travelORwaitingFrag = new ActivityTravelOrWaitingFragment();
					travelORwaitingFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, travelORwaitingFrag);
					ft.commit();
				} else if (position == 2) {

					// WithCoSMRs
					ActivityWithCoSMRsFragment withCoSMRsFrag = new ActivityWithCoSMRsFragment();
					withCoSMRsFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, withCoSMRsFrag);
					ft.commit();
				} else if (position == 3) {

					// Admin Works
					ActivityAdminWorksFragment adminWorksFrag = new ActivityAdminWorksFragment();
					adminWorksFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, adminWorksFrag);
					ft.commit();
				} else if (position == 4) {

					// Activity Details and Notes
					ActivityDetailedInfoFragment detailsANDnotesFrag = new ActivityDetailedInfoFragment();
					detailsANDnotesFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, detailsANDnotesFrag);
					ft.commit();
				} else if (position == 5) {

					// Customer Contact Person
					CustomerContactPersonFragment customerContactPersonFrag = new CustomerContactPersonFragment();
					customerContactPersonFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag,
							customerContactPersonFrag);
					ft.commit();
				} else if (position == 6) {

					// JDI Product Stock Check
					JDIProductStockFragment jdiProductStockFrag = new JDIProductStockFragment();
					jdiProductStockFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, jdiProductStockFrag);
					ft.commit();
				} else if (position == 7) {

					// Product Supplier
					ProductSupplierFragment productSupplierFrag = new ProductSupplierFragment();
					productSupplierFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, productSupplierFrag);
					ft.commit();
				} else if (position == 8) {

					// JDI Merchandising Check
					JDIMerchandisingCheckFragment jdiMerchandisingCheckFrag = new JDIMerchandisingCheckFragment();
					jdiMerchandisingCheckFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag,
							jdiMerchandisingCheckFrag);
					ft.commit();
				} else if (position == 9) {

					// JDI Competitor Product Stock Check
					CompetitorStockCheckFragment jdiCompetitorProductStockCheckFrag = new CompetitorStockCheckFragment();
					jdiCompetitorProductStockCheckFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag,
							jdiCompetitorProductStockCheckFrag);
					ft.commit();
				} else if (position == 10) {

					// Marketing Intel
					MarketingIntelFragment marketingIntelFrag = new MarketingIntelFragment();
					marketingIntelFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, marketingIntelFrag);
					ft.commit();
				} else if (position == 11) {

					// Project Visit
					ActivityProjectVisitFragment projectVisitFrag = new ActivityProjectVisitFragment();
					projectVisitFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, projectVisitFrag);
					ft.commit();
				} else if (position == 12) {

					// Project Requirements
					ProjectRequirementsFragment projectRequirementsFrag = new ProjectRequirementsFragment();
					projectRequirementsFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag,
							projectRequirementsFrag);
					ft.commit();
				} else if (position == 13) {

					// Trainings
					ActivityTrainingsFragment trainingsFrag = new ActivityTrainingsFragment();
					trainingsFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, trainingsFrag);
					ft.commit();
				} else if (position == 14) {

					// Identify Product Focus
					ProductFocusFragment identifyProductFocusFrag = new ProductFocusFragment();
					identifyProductFocusFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag,
							identifyProductFocusFrag);
					ft.commit();
				} else if (position == 15) {

					// Full Brand Activation
					ActivityBrandActivationFragment fullBrandActivationFrag = new ActivityBrandActivationFragment();
					fullBrandActivationFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag,
							fullBrandActivationFrag);
					ft.commit();
				} else if (position == 16) {

					// Activity Photos and Attachments
					PhotosAndAttachmentsFragment activityPhotosAndAttachments = new PhotosAndAttachmentsFragment();
					activityPhotosAndAttachments.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag,
							activityPhotosAndAttachments);
					ft.commit();
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		ActivityGeneralInfoFragment editActInfoFrag = new ActivityGeneralInfoFragment();
		editActInfoFrag.setArguments(bundle);

		ft.replace(R.id.layoutForAddingFrag, editActInfoFrag);
		ft.commit();

		return myFragmentView;
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "General Information",
				"Travel or Waiting", "With CoSMRs", "Admin Works",
				"Activity Details and Notes", "Customer Contact Person",
				"JDI Product Stock Check", "Product Supplier",
				"JDI Merchandising Check",
				"JDI Competitor Product Stock Check", "Marketing Intel",
				"Project Visit", "Project Requirements", "Trainings",
				"Identify Product Focus", "Full Brand Activation",
				"Activity Photos and Attachments" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			return SuperAwesomeCardFragment.newInstance(firstPos);
		}
	}
}
