package co.nextix.jardine.activities.add.fragments;

import android.graphics.drawable.Drawable;
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
import co.nextix.jardine.workplan.WorkPlanConstants;

import com.astuetz.PagerSlidingTabStrip;

public class AddActivityFragment extends Fragment {

	private View rootView = null;

	protected PagerSlidingTabStrip tabs;
	protected ViewPager pager;
	protected MyPagerAdapter adapter;

	private int firstPos;

	private FragmentTransaction ft;
	private FrameLayout fl;

	private Bundle bundle;
	private Bundle b;
	
	public static long WORKPLAN_ENTRY_ID = 0;
	public static long ACTIVITY_TYPE = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.add_activity_fragment, container, false);
		
		b = getArguments();
		
		if(b != null){
			WORKPLAN_ENTRY_ID = b.getLong(WorkPlanConstants.WORKPLAN_ENTRY_ROW_ID);
		}

		ft = getFragmentManager().beginTransaction();
		fl = (FrameLayout) rootView.findViewById(R.id.layoutForAddingFrag);

		bundle = new Bundle();
		bundle.putInt("layoutID", fl.getId());

		tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
		pager = (ViewPager) rootView.findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager, true);

		tabs.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				firstPos = position;
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

		AddActivityGeneralInformationFragment addActGenInfoFrag = new AddActivityGeneralInformationFragment(AddActivityFragment.this);
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
