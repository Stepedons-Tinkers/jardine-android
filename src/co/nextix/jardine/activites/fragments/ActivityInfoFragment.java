package co.nextix.jardine.activites.fragments;

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
import co.nextix.jardine.R;
import co.nextix.jardine.SuperAwesomeCardFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityDIYorSupermarketFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityProjectVisitFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityRetailVisitFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityTrainingsFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityWithCoSMRsFragment;
import co.nextix.jardine.activities.update.fragments.EditActivityInfoFragment;

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

		tabs.setViewPager(pager, true);
		
		tabs.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				firstPos = position;

				ft.setCustomAnimations(R.anim.slide_in_left,
						R.anim.slide_out_left);

				if (position == 0) {

					
					// General Information
					EditActivityInfoFragment editActInfoFrag = new EditActivityInfoFragment();
					editActInfoFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, editActInfoFrag);
					ft.commit();
				} else if (position == 1) {

					// Travel or Waiting TODO
//					TravelOrWaitingFragment travelORwaitingFrag = new TravelOrWaitingFragment();
//					travelORwaitingFrag.setArguments(bundle);
//
//					ft = getFragmentManager().beginTransaction();
//					ft.replace(R.id.layoutForAddingFrag, travelORwaitingFrag);
//					ft.commit();
				} else if (position == 2) {
					
					// WithCoSMRs
					AddActivityWithCoSMRsFragment  withCoSMRsFrag = new AddActivityWithCoSMRsFragment ();
					withCoSMRsFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, withCoSMRsFrag);
					ft.commit();
				} else if (position == 3) {
					
					// Admin Works TODO
//					AdminWorksFragment adminWorksFrag = new AdminWorksFragment();
//					adminWorksFrag.setArguments(bundle);
//					
//					ft = getFragmentManager().beginTransaction();
//					ft.replace(R.id.layoutForAddingFrag, adminWorksFrag);
//					ft.commit();
				} else if (position == 4) {
					
					
					// Activity Details and Notes TODO
//					ActivityDetailsAndNotesFragment detailsANDnotesFrag = new ActivityDetailsAndNotesFragment();
//					detailsANDnotesFrag.setArguments(bundle);
//					
//					ft = getFragmentManager().beginTransaction();
//					ft.replace(R.id.layoutForAddingFrag, detailsANDnotesFrag);
//					ft.commit();
				} else if (position == 5) {
					
					// Customer Contact Person
					CustomerContactPersonFragment customerContactPersonFrag = new CustomerContactPersonFragment();
					customerContactPersonFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, customerContactPersonFrag);
					ft.commit();
				} else if (position == 6) {
					
					// JDI Product Stock Check
					JDIProductStockFragment jdiProductStockFrag = new JDIProductStockFragment();
					jdiProductStockFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, jdiProductStockFrag);
					ft.commit();
				} else if (position == 7) {
					
					// Product Supplier TODO
//					ProductSupplierFragment productSupplierFrag = new ProductSupplierFragment();
//					productSupplierFrag.setArguments(bundle);
//					
//					ft = getFragmentManager().beginTransaction();
//					ft.replace(R.id.layoutForAddingFrag, productSupplierFrag);
//					ft.commit();
				} else if (position == 8) {
					
					// JDI Merchandising Check
					JDIMerchandisingCheckFragment jdiMerchandisingCheckFrag = new JDIMerchandisingCheckFragment();
					jdiMerchandisingCheckFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, jdiMerchandisingCheckFrag);
					ft.commit();
				} else if (position == 9) {
					
					// JDI Competitor Product Stock Check
					CompetitorStockCheckFragment jdiCompetitorProductStockCheckFrag = new CompetitorStockCheckFragment();
					jdiCompetitorProductStockCheckFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, jdiCompetitorProductStockCheckFrag);
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
					AddActivityProjectVisitFragment  projectVisitFrag = new AddActivityProjectVisitFragment ();
					projectVisitFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, projectVisitFrag);
					ft.commit();
				} else if (position == 12) {
					
					// Project Requirements
					ProjectRequirementsFragment projectRequirementsFrag = new ProjectRequirementsFragment();
					projectRequirementsFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, projectRequirementsFrag);
					ft.commit();
				} else if (position == 13) {
					
					// Trainings
					AddActivityTrainingsFragment  trainingsFrag = new AddActivityTrainingsFragment ();
					trainingsFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, trainingsFrag);
					ft.commit();
				} else if (position == 14){
					
					// Identify Product Focus
					ProductFocusFragment identifyProductFocusFrag = new ProductFocusFragment();
					identifyProductFocusFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, identifyProductFocusFrag);
					ft.commit();
				} else if (position == 15){
					
					// Full Brand Activation TODO
//					FullBrandActivationFragment fullBrandActivationFrag = new FullBrandActivationFragment();
//					fullBrandActivationFrag.setArguments(bundle);
//					
//					ft = getFragmentManager().beginTransaction();
//					ft.replace(R.id.layoutForAddingFrag, fullBrandActivationFrag);
//					ft.commit();
				} else if (position == 16){
					
					// Activity Photos and Attachments TODO
//					ActivityPhotosAndAttachments activityPhotosAndAttachments = new ActivityPhotosAndAttachments();
//					activityPhotosAndAttachments.setArguments(bundle);
//					
//					ft = getFragmentManager().beginTransaction();
//					ft.replace(R.id.layoutForAddingFrag, activityPhotosAndAttachments);
//					ft.commit();
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		EditActivityInfoFragment editActInfoFrag = new EditActivityInfoFragment();
		editActInfoFrag.setArguments(bundle);

		ft.replace(R.id.layoutForAddingFrag, editActInfoFrag);
		ft.commit();

		return myFragmentView;
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "General Information", "Travel or Waiting", "With CoSMRs", "Admin Works",
				"Activity Details and Notes", "Customer Contact Person", "JDI Product Stock Check", "Product Supplier",
				"JDI Merchandising Check", "JDI Competitor Product Stock Check", "Marketing Intel", "Project Visit",
				"Project Requirements", "Trainings", "Identify Product Focus", "Full Brand Activation", "Activity Photos and Attachments" };

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
