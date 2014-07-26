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

					EditActivityInfoFragment editActInfoFrag = new EditActivityInfoFragment();
					editActInfoFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, editActInfoFrag);
					ft.commit();
				} else if (position == 1) {

					AddActivityWithCoSMRsFragment addActWCoSmrFrag = new AddActivityWithCoSMRsFragment();
					addActWCoSmrFrag.setArguments(bundle);

					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActWCoSmrFrag);
					ft.commit();
				} else if (position == 2) {
					
					AddActivityDIYorSupermarketFragment addActDiyOrSupFrag = new AddActivityDIYorSupermarketFragment();
					addActDiyOrSupFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActDiyOrSupFrag);
					ft.commit();
				} else if (position == 3) {
					
					AddActivityRetailVisitFragment addActRetVisFrag = new AddActivityRetailVisitFragment();
					addActRetVisFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActRetVisFrag);
					ft.commit();
				} else if (position == 4) {
					
					AddActivityProjectVisitFragment addActProjVisFrag = new AddActivityProjectVisitFragment();
					addActProjVisFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActProjVisFrag);
					ft.commit();
				} else if (position == 5) {
					
					AddActivityTrainingsFragment addActTraiFrag = new AddActivityTrainingsFragment();
					addActTraiFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActTraiFrag);
					ft.commit();
				} else if (position == 6) {
					
					JDIProductStockFragment jdiProStoFrag = new JDIProductStockFragment();
					jdiProStoFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, jdiProStoFrag);
					ft.commit();
				} else if (position == 7) {
					
					JDIMerchandisingCheckFragment jdiMerCheFrag = new JDIMerchandisingCheckFragment();
					jdiMerCheFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, jdiMerCheFrag);
					ft.commit();
				} else if (position == 8) {
					
					CompetitorStockCheckFragment compStoCheFrag = new CompetitorStockCheckFragment();
					compStoCheFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, compStoCheFrag);
					ft.commit();
				} else if (position == 9) {
					
					MarketingIntelFragment markIntFrag = new MarketingIntelFragment();
					markIntFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, markIntFrag);
					ft.commit();
				} else if (position == 10) {
					
					ProjectRequirementsFragment projReqFrag = new ProjectRequirementsFragment();
					projReqFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, projReqFrag);
					ft.commit();
				} else if (position == 11) {
					
					DIYSupermarketPhotosFragment diySupPhoFrag = new DIYSupermarketPhotosFragment();
					diySupPhoFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, diySupPhoFrag);
					ft.commit();
				} else if (position == 12) {
					
					CustomerContactPersonFragment custConPerFrag = new CustomerContactPersonFragment();
					custConPerFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, custConPerFrag);
					ft.commit();
				} else if (position == 13) {
					
					ProductsFragment proFrag = new ProductsFragment();
					proFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, proFrag);
					ft.commit();
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

		private final String[] TITLES = { "General Information", "With CoSMRs",
				"DIY or Supermarket", "Retail Visit", "Project Visit",
				"Trainings", "JDI Product Stock Check",
				"JDI Merchandising Check", "Competitor Product Stock Check",
				"Marketing Intel", "Project Requirements",
				"DIY or Supermarket Photos", "Customer Contact Person",
				"Products" };

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
