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
	private ViewPager pager;
	private MyPagerAdapter adapter;
	
	private int firstPos;
	
	private FragmentTransaction ft;
	
	public static FrameLayout fl;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.myFragmentView = inflater.inflate(R.layout.fragment_activity_static_fields, container, false);
		
		ft = getFragmentManager().beginTransaction();
		fl = (FrameLayout) this.myFragmentView.findViewById(R.id.layoutForAddingFrag);
		
		tabs = (PagerSlidingTabStrip) this.myFragmentView.findViewById(R.id.tabs);
		pager = (ViewPager) this.myFragmentView.findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		pager.setCurrentItem(0);
		
		tabs.setViewPager(pager);
		
		tabs.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				firstPos = position;
				
				if(position == 0){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new EditActivityInfoFragment());
					ft.commit();
				}else if(position == 1){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new AddActivityWithCoSMRsFragment());
					ft.commit();
				}else if(position == 2){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new AddActivityDIYorSupermarketFragment());
					ft.commit();
				}else if(position == 3){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new AddActivityRetailVisitFragment());
					ft.commit();
				}else if(position == 4){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new AddActivityProjectVisitFragment());
					ft.commit();
				}else if(position == 5){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new AddActivityTrainingsFragment());
					ft.commit();
				}else if(position == 6){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new JDIProductStockFragment());
					ft.commit();
				}else if(position == 7){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new JDIMerchandisingCheckFragment());
					ft.commit();
				}else if(position == 8){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new CompetitorStockCheckFragment());
					ft.commit();
				}else if(position == 9){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new MarketingIntelFragment());
					ft.commit();
				}else if(position == 10){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new ProjectRequirementsFragment());
					ft.commit();
				}else if(position == 11){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new DIYSupermarketPhotosFragment());
					ft.commit();
				}else if(position == 12){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new CustomerContactPersonFragment());
					ft.commit();
				}else if(position == 13){
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, new ProductsFragment());
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
		
		ft.replace(R.id.layoutForAddingFrag, new EditActivityInfoFragment());
		ft.commit();

		return myFragmentView;
	}
	
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = {"General Information", "With CoSMRs", "DIY or Supermarket", 
				"Retail Visit", "Project Visit", "Trainings", "JDI Product Stock Check",
				"JDI Merchandising Check", "Competitor Product Stock Check", "Marketing Intel",
				"Project Requirements", "DIY or Supermarket Photos", "Customer Contact Person", "Products"};

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
