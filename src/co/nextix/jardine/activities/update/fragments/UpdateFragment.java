package co.nextix.jardine.activities.update.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityAdminWorksFragment;
import co.nextix.jardine.activites.fragments.ActivityBrandActivationFragment;
import co.nextix.jardine.activites.fragments.ActivityDetailedInfoFragment;
import co.nextix.jardine.activites.fragments.ActivityGeneralInfoFragment;
import co.nextix.jardine.activites.fragments.ActivityProjectVisitFragment;
import co.nextix.jardine.activites.fragments.ActivityTrainingsFragment;
import co.nextix.jardine.activites.fragments.CompetitorStockCheckFragment;
import co.nextix.jardine.activites.fragments.CustomerContactPersonFragment;
import co.nextix.jardine.activites.fragments.JDIMerchandisingCheckFragment;
import co.nextix.jardine.activites.fragments.JDIProductStockFragment;
import co.nextix.jardine.activites.fragments.MarketingIntelFragment;
import co.nextix.jardine.activites.fragments.PhotosAndAttachmentsFragment;
import co.nextix.jardine.activites.fragments.ProductFocusFragment;
import co.nextix.jardine.activites.fragments.ProjectRequirementsFragment;
import co.nextix.jardine.activities.add.fragments.ActivitiesCustomerContactList;
import co.nextix.jardine.database.tables.ActivityTypeTable;

import com.astuetz.PagerSlidingTabStrip;

public class UpdateFragment extends Fragment {
	
	private View view;
	
	public static PagerSlidingTabStrip tabs;
	public static ViewPager pager;
	protected MyPagerAdapter adapter;
	
	private int titles;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_activity_edit, container, false);
		
		Bundle b = getArguments();
		if(b != null){
			UpdateConstants.ACTIVITY_ID = b.getLong("activityID");
		}
		
		UpdateConstants.ACTIVITY_RECORD = JardineApp.DB.getActivity().getById(UpdateConstants.ACTIVITY_ID);
		
		List<Fragment> fragment = null;
		ActivityTypeTable type = JardineApp.DB.getActivityType();
		
		if(type != null){
			UpdateConstants.RECORD = type.getById(UpdateConstants.ACTIVITY_RECORD.getActivityType());
			if(UpdateConstants.RECORD != null){
				
				UpdateConstants.NO = UpdateConstants.ACTIVITY_RECORD.getNo();
				
				UpdateConstants.CRM_NO = UpdateConstants.ACTIVITY_RECORD.getCrm();
				UpdateConstants.ACTIVITY_TYPE = UpdateConstants.ACTIVITY_RECORD.getActivityType();
				UpdateConstants.CHECK_IN = UpdateConstants.ACTIVITY_RECORD.getCheckIn();
				UpdateConstants.CHECK_OUT = UpdateConstants.ACTIVITY_RECORD.getCheckOut();
				UpdateConstants.BUSINESS_UNIT = UpdateConstants.ACTIVITY_RECORD.getBusinessUnit();
				UpdateConstants.CREATED_BY = UpdateConstants.ACTIVITY_RECORD.getCreatedBy();
				
				UpdateConstants.REASON_REMARKS = UpdateConstants.ACTIVITY_RECORD.getReasonRemarks();
				
				UpdateConstants.CO_SMR = UpdateConstants.ACTIVITY_RECORD.getSmr();
				
				UpdateConstants.ADMIN_WORK_DETAILS = UpdateConstants.ACTIVITY_RECORD.getAdminWorkDetails();
				
				UpdateConstants.CUSTOMER = UpdateConstants.ACTIVITY_RECORD.getCustomer();
				UpdateConstants.AREA = UpdateConstants.ACTIVITY_RECORD.getArea();
				UpdateConstants.PROVINCE = UpdateConstants.ACTIVITY_RECORD.getProvince();
				UpdateConstants.CITY_TOWN = UpdateConstants.ACTIVITY_RECORD.getCity();
				UpdateConstants.WORKPLAN_ENTRY = UpdateConstants.ACTIVITY_RECORD.getWorkplanEntry();
				UpdateConstants.OBJECTIVE = UpdateConstants.ACTIVITY_RECORD.getObjective();
				UpdateConstants.HIGHLIGHTS = UpdateConstants.ACTIVITY_RECORD.getHighlights();
				UpdateConstants.NOTES = UpdateConstants.ACTIVITY_RECORD.getNotes();
				UpdateConstants.NEXT_STEPS = UpdateConstants.ACTIVITY_RECORD.getNextSteps();
				UpdateConstants.FIRST_TIME = UpdateConstants.ACTIVITY_RECORD.getFirstTimeVisit();
				UpdateConstants.PLANNED = UpdateConstants.ACTIVITY_RECORD.getPlannedVisit();
				UpdateConstants.FOLLOW_UP_DATE = UpdateConstants.ACTIVITY_RECORD.getFollowUpCommitmentDate();
				
				if (UpdateConstants.RECORD.toString().equals("Travel")
						|| UpdateConstants.RECORD.toString().equals("Waiting")) { // done
					fragment = getFragments(0);
					titles = 0;
				} else if (UpdateConstants.RECORD.toString().equals(
						"Company Work-with Co-SMR/ Supervisor")) { // done
					titles = 1;
					fragment = getFragments(1);
				} else if (UpdateConstants.RECORD.toString().equals("Admin Work")) { // done
					fragment = getFragments(2);
					titles = 2;
				} else if (UpdateConstants.RECORD.toString().equals(
						"Retail Visits (Traditional Hardware)")
						|| UpdateConstants.RECORD.toString().equals("Retail Visits (Merienda)")) { // done
					fragment = getFragments(3);
					titles = 3;
				} else if (UpdateConstants.RECORD.toString().equals("KI Visits - On-site")) { // done
					fragment = getFragments(4);
					titles = 4;
				} else if (UpdateConstants.RECORD.toString().contains("Major Training")) { // done
					fragment = getFragments(5);
					titles = 5;
				} else if (UpdateConstants.RECORD.toString().contains("End User Activity")) { // done
					fragment = getFragments(6);
					titles = 6;
				} else if (UpdateConstants.RECORD.toString().equals("Full Brand Activation")) { // done
					fragment = getFragments(7);
					titles = 7;
				} else {
					fragment = getFragments(8);
					titles = 8;
				}
			}
		}
		
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs_update);
		pager = (ViewPager) view.findViewById(R.id.non_swipeable_pager);
		adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(), fragment);
		
		pager.setAdapter(adapter);
		pager.setCurrentItem(0);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager, false);
		
		
		
		return view;
	}
	
	public class MyPagerAdapter extends FragmentStatePagerAdapter {

		private ArrayList<String> TITLES = new ArrayList<String>();
		private List<Fragment> fragments;

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
			switch (titles) {

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
				TITLES.add("Identify Product Focus");
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
		case 0: // travel/waiting
			flist.add(UpdateGeneralInformationFragment.instantiate(getActivity(),UpdateGeneralInformationFragment.class.getName()));
			flist.add(UpdateTravelWaitingFragment.instantiate(getActivity(),UpdateTravelWaitingFragment.class.getName()));
			break;
		case 1: // co smr
			flist.add(UpdateGeneralInformationFragment.instantiate(getActivity(),UpdateGeneralInformationFragment.class.getName()));
			flist.add(UpdateWithCoSMRFragment.instantiate(getActivity(),UpdateWithCoSMRFragment.class.getName()));
			break;
		case 2: // admin works
			flist.add(ActivityGeneralInfoFragment.instantiate(getActivity(),ActivityGeneralInfoFragment.class.getName()));
			flist.add(ActivityAdminWorksFragment.instantiate(getActivity(),ActivityAdminWorksFragment.class.getName()));
			break;
		case 3: // retail visits
			flist.add(UpdateGeneralInformationFragment.instantiate(getActivity(),UpdateGeneralInformationFragment.class.getName()));
			flist.add(UpdateDetailsAndNotesFragment.instantiate(getActivity(),UpdateDetailsAndNotesFragment.class.getName()));
			
			flist.add(ActivitiesCustomerContactList.instantiate(getActivity(),ActivitiesCustomerContactList.class.getName()));
			
			flist.add(JDIProductStockFragment.instantiate(getActivity(),JDIProductStockFragment.class.getName()));
			
			flist.add(UpdateProductSupplierList.instantiate(getActivity(),UpdateProductSupplierList.class.getName()));
			
			flist.add(UpdateJDIMerchandisingStockCheckListFragment.instantiate(getActivity(),UpdateJDIMerchandisingStockCheckListFragment.class.getName()));
			
			flist.add(CompetitorStockCheckFragment.instantiate(getActivity(),CompetitorStockCheckFragment.class.getName()));
			flist.add(MarketingIntelFragment.instantiate(getActivity(),MarketingIntelFragment.class.getName()));
			flist.add(PhotosAndAttachmentsFragment.instantiate(getActivity(),PhotosAndAttachmentsFragment.class.getName()));
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
			flist.add(ProductFocusFragment.instantiate(
					getActivity(),
					ProductFocusFragment.class.getName()));
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
