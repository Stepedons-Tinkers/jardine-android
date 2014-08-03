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
	private long activityID = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		AddActivityFragment.fromOther = true;

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

		if (activityID != 0) {
			// query

			Log.e("activityID", "not zero");

			ActivitiesConstant.ACTIVITY_RECORD = JardineApp.DB.getActivity().getById(this.activityID);
			SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
			Editor editor = pref.edit();

			editor.putLong("activity_id_edit", ActivitiesConstant.ACTIVITY_RECORD.getId());
			editor.putString("activity_id_crm_no", ActivitiesConstant.ACTIVITY_RECORD.getCrm());
			editor.putLong("activity_id_activity_type", ActivitiesConstant.ACTIVITY_RECORD.getActivityType());
			editor.putString("activity_id_check_in", ActivitiesConstant.ACTIVITY_RECORD.getCheckIn());
			editor.putString("activity_id_check_out", ActivitiesConstant.ACTIVITY_RECORD.getCheckOut());
			editor.putLong("activity_id_business_unit", ActivitiesConstant.ACTIVITY_RECORD.getBusinessUnit());
			editor.putLong("activity_id_created_by", ActivitiesConstant.ACTIVITY_RECORD.getCreatedBy());
			editor.putString("activity_id_longitude", String.valueOf(ActivitiesConstant.ACTIVITY_RECORD.getLongitude()));
			editor.putString("activity_id_latitude", String.valueOf(ActivitiesConstant.ACTIVITY_RECORD.getLatitude()));
			editor.putString("activity_id_created_time", ActivitiesConstant.ACTIVITY_RECORD.getCreatedTime());
			editor.putString("activities_id_modified_time", ActivitiesConstant.ACTIVITY_RECORD.getModifiedTime());
			editor.putString("activity_id_reasons_remarks", ActivitiesConstant.ACTIVITY_RECORD.getReasonRemarks());
			editor.putLong("activity_id_smr", ActivitiesConstant.ACTIVITY_RECORD.getSmr());
			editor.putString("activity_id_admin_details", ActivitiesConstant.ACTIVITY_RECORD.getAdminWorkDetails());
			editor.putLong("activity_id_customer", ActivitiesConstant.ACTIVITY_RECORD.getCustomer());
			editor.putLong("activitiy_id_area", ActivitiesConstant.ACTIVITY_RECORD.getArea());
			editor.putLong("activity_id_province", ActivitiesConstant.ACTIVITY_RECORD.getProvince());
			editor.putLong("activity_id_city", ActivitiesConstant.ACTIVITY_RECORD.getCity());
			editor.putLong("activity_id_workplan_entry", ActivitiesConstant.ACTIVITY_RECORD.getWorkplanEntry());
			editor.putString("activity_id_objective", ActivitiesConstant.ACTIVITY_RECORD.getObjective());
			editor.putInt("activity_id_first_time_visit", ActivitiesConstant.ACTIVITY_RECORD.getFirstTimeVisit());
			editor.putInt("activity_id_planned_visit", ActivitiesConstant.ACTIVITY_RECORD.getPlannedVisit());
			editor.putString("activity_id_notes", ActivitiesConstant.ACTIVITY_RECORD.getNotes());
			editor.putString("activity_id_highlights", ActivitiesConstant.ACTIVITY_RECORD.getHighlights());
			editor.putString("activity_id_next_steps", ActivitiesConstant.ACTIVITY_RECORD.getNextSteps());
			editor.putString("activity_id_followup_committment_date", ActivitiesConstant.ACTIVITY_RECORD.getFollowUpCommitmentDate());
			editor.putString("activity_id_project_name", ActivitiesConstant.ACTIVITY_RECORD.getProjectName());
			editor.putLong("activity_id_project_stage", ActivitiesConstant.ACTIVITY_RECORD.getProjectStage());
			editor.putLong("activity_id_project_category", ActivitiesConstant.ACTIVITY_RECORD.getProjectCategory());
			editor.putString("activity_id_venue", ActivitiesConstant.ACTIVITY_RECORD.getVenue());
			editor.putInt("activity_id_number_of_attendees", ActivitiesConstant.ACTIVITY_RECORD.getNumberOfAttendees());
			editor.putString("activity_id_end_user_activity_types", ActivitiesConstant.ACTIVITY_RECORD.getEndUserActivityTypes());
			editor.commit(); // commit changes
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

		/* 0 */flist.add(AddActivityGeneralInformationFragment.instantiate(getActivity(),
				AddActivityGeneralInformationFragment.class.getName()));
		/* 1 */flist.add(AddActivityTravelWaitingFragment.instantiate(getActivity(), AddActivityTravelWaitingFragment.class.getName()));
		/* 2 */flist.add(AddActivityWithCoSMRsFragment.instantiate(getActivity(), AddActivityWithCoSMRsFragment.class.getName()));
		/* 3 */flist.add(AddActivityAdminWorksFragment.instantiate(getActivity(), AddActivityAdminWorksFragment.class.getName()));
		/* 4 */flist.add(AddActivityDetailsAndNotesFragment.instantiate(getActivity(), AddActivityDetailsAndNotesFragment.class.getName()));
		/* 5 */flist.add(ActivitiesCustomerContactList.instantiate(getActivity(), ActivitiesCustomerContactList.class.getName()));
//		/* 6 */flist.add(AddJDIProductStockFragment.instantiate(getActivity(), AddJDIProductStockFragment.class.getName()));
		/* 6 */flist.add(AddJDIProductStockListFragment.instantiate(getActivity(), AddJDIProductStockListFragment.class.getName()));
		/* 7 */flist.add(AddActivityProductSupplierFragment.instantiate(getActivity(), AddActivityProductSupplierFragment.class.getName()));
		/* 8 */flist.add(JDIMerchandisingCheckFragmentAdd.instantiate(getActivity(), JDIMerchandisingCheckFragmentAdd.class.getName()));
		/* 9 */flist.add(CompetitorStockCheckFragmentAdd.instantiate(getActivity(), CompetitorStockCheckFragmentAdd.class.getName()));
		/* 10 */flist.add(MarketingIntelFragmentAdd.instantiate(getActivity(), MarketingIntelFragmentAdd.class.getName()));
		/* 11 */flist.add(AddActivityProjectVisitFragment.instantiate(getActivity(), AddActivityProjectVisitFragment.class.getName()));
		/* 12 */flist.add(AddProjectRequirementsFragment.instantiate(getActivity(), AddProjectRequirementsFragment.class.getName()));
		/* 13 */flist.add(AddActivityTrainingsFragment.instantiate(getActivity(), AddActivityTrainingsFragment.class.getName()));
		/* 14 */flist.add(AddIdentifyProductFocusFragment.instantiate(getActivity(), AddIdentifyProductFocusFragment.class.getName()));
		/* 15 */flist.add(AddActivityFullBrandActivationFragment.instantiate(getActivity(),
				AddActivityFullBrandActivationFragment.class.getName()));
		/* 16 */flist.add(ActivitiesDocumentList.instantiate(getActivity(), ActivitiesDocumentList.class.getName()));
		return flist;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		fragmentStart = false;
	}
}
