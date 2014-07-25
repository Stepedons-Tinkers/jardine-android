package co.nextix.jardine.activities.add.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import co.nextix.jardine.R;
import co.nextix.jardine.SuperAwesomeCardFragment;

import com.astuetz.PagerSlidingTabStrip;

public class AddActivityFragment extends Fragment {

	private View rootView = null;

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	private int firstPos;

	private FragmentTransaction ft;

	private FrameLayout fl;
	
	private Bundle bundle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.add_activity_fragment, container,
				false);

		ft = getFragmentManager().beginTransaction();
		fl = (FrameLayout) rootView.findViewById(R.id.layoutForAddingFrag);
		
		bundle = new Bundle();
		bundle.putInt("layoutID", fl.getId());

		tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
		pager = (ViewPager) rootView.findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		pager.setCurrentItem(0);

		tabs.setViewPager(pager, true);
		
		if(tabs.getId() == 2){
			Log.e("tabID","2");
		}

		tabs.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				firstPos = position;

				ft.setCustomAnimations(R.anim.slide_in_left,
						R.anim.slide_out_left);

				if (position == 0) {
					
					AddActivityGeneralInformationFragment addActGenInfoFrag = new AddActivityGeneralInformationFragment();
					addActGenInfoFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActGenInfoFrag);
					ft.commit();
				}else if(position == 1){
					
					AddActivityWithCoSMRsFragment addActWCoSmrFrag = new AddActivityWithCoSMRsFragment();
					addActWCoSmrFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActWCoSmrFrag);
					ft.commit();
				}else if(position == 2){
					
					AddActivityDIYorSupermarketFragment addActDiyOrSupFrag = new AddActivityDIYorSupermarketFragment();
					addActDiyOrSupFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActDiyOrSupFrag);
					ft.commit();
				}else if(position == 3){
					
					AddActivityRetailVisitFragment addActRetVisFrag = new AddActivityRetailVisitFragment();
					addActRetVisFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActRetVisFrag);
					ft.commit();
				}else if(position == 4){
					
					AddActivityProjectVisitFragment addActProjVisFrag = new AddActivityProjectVisitFragment();
					addActProjVisFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActProjVisFrag);
					ft.commit();
				}else if(position == 5){
					
					AddActivityTrainingsFragment addActTrainFrag = new AddActivityTrainingsFragment();
					addActTrainFrag.setArguments(bundle);
					
					ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.layoutForAddingFrag, addActTrainFrag);
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
		
		AddActivityGeneralInformationFragment addActGenInfoFrag = new AddActivityGeneralInformationFragment();
		addActGenInfoFrag.setArguments(bundle);

		ft.replace(R.id.layoutForAddingFrag, addActGenInfoFrag);
		ft.commit();

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
	
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "General Information", "With CoSMRs",
				"DIY or Supermarket", "Retail Visit", "Project Visit",
				"Trainings" };

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
