package co.nextix.jardine.activities.add.fragments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddActivityPhotosAndAttachments.InsertTask;
import co.nextix.jardine.collaterals.AdapterCollateralsFiles;
import co.nextix.jardine.customers.AddCustomerContactsFragment2;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.fragments.StartActivityFragment;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class ActivitiesDocumentList extends Fragment implements
		View.OnClickListener, LocationListener {

	private View view, header;
	private TextView col1, col2, col3, col4;
	private TableRow trow;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;

	private Button btnAdd, btnNext;

	private List<DocumentRecord> tempRecord;
	private List<DocumentRecord> realRecord;
	private AdapterCollateralsFiles finalAdapter;
	private double dLatitude, dLongitude;
	LocationManager locationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(
				R.layout.activity_add_customer_contact_list_step1, container,
				false);
		header = inflater.inflate(R.layout.collaterals_marketing_materials_row,
				null, false);

		initLayout();
		return view;
	}

	private void initLayout() {
		// Header Data

		trow = (TableRow) header.findViewById(R.id.trCollateralsMMRowH);
		trow.setBackgroundResource(R.color.tab_pressed);

		col1 = (TextView) header.findViewById(R.id.tvCollateralsMMCol1);
		col2 = (TextView) header.findViewById(R.id.tvCollateralsMMCol2);
		col3 = (TextView) header.findViewById(R.id.tvCollateralsMMCol3);
		col4 = (TextView) header.findViewById(R.id.tvCollateralsMMCol4);

		trow.setGravity(Gravity.CENTER);
		col1.setGravity(Gravity.CENTER);
		col2.setGravity(Gravity.CENTER);
		col3.setGravity(Gravity.CENTER);
		col4.setGravity(Gravity.CENTER);

		col1.setText(getResources().getString(R.string.collaterals_file_title));
		col2.setText(getResources().getString(R.string.collaterals_filename));
		col3.setText(getResources().getString(
				R.string.collaterals_file_modified_time));
		col4.setText(getResources().getString(R.string.collaterals_file_type));

		col1.setTypeface(null, Typeface.BOLD);
		col2.setTypeface(null, Typeface.BOLD);
		col3.setTypeface(null, Typeface.BOLD);
		col4.setTypeface(null, Typeface.BOLD);

		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);
		//
		btnAdd = (Button) view.findViewById(R.id.bActivityAddCustomerContact);
		btnNext = (Button) view.findViewById(R.id.bActivityContactNext);
		btnNext.setOnClickListener(this);
		btnAdd.setOnClickListener(this);

		btnAdd.setText("Add Document Record");

		realRecord = new ArrayList<DocumentRecord>();
		tempRecord = new ArrayList<DocumentRecord>();

		finalAdapter = new AdapterCollateralsFiles(getActivity(),
				R.layout.collaterals_marketing_materials_row,
				ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST);

		list = (ListView) view.findViewById(R.id.lvActiviyCustomerContactList);

		txtPage = (TextView) view.findViewById(R.id.tvActivityCustomerPage);

		arrowLeft = (ImageButton) view
				.findViewById(R.id.ibActivityCustomerLeft);
		arrowRight = (ImageButton) view
				.findViewById(R.id.ibActivityCustomerRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		list.addHeaderView(header);
		list.setAdapter(finalAdapter);

		getGpsCoordinates();
	}

	public void populate() {

		finalAdapter.notifyDataSetChanged();
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);
		// if (menuVisible) {
		// populate();
		// }

	}

	public void removeFragment() {
		this.getChildFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.remove(fragment).addToBackStack(JardineApp.TAG).commit();
	}

	ActivitiesDocumentsAddNew fragment = new ActivitiesDocumentsAddNew();

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bActivityAddCustomerContact:
			fragment = new ActivitiesDocumentsAddNew();
			fragment.setTargetFragment(this, 15);
			this.getFragmentManager().beginTransaction()
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					.add(R.id.flActivityAddCustomerRootView, fragment)
					.addToBackStack(JardineApp.TAG).commit();
			break;
		case R.id.bActivityContactNext:
			// Save All Stuff to DB
			new InsertDBTask().execute();
			break;
		}

	}

	private class InsertDBTask extends AsyncTask<Void, Void, Boolean> {
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Saving...");
			progressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			progressDialog.dismiss();
			Toast.makeText(getActivity(), "Activity saved!", Toast.LENGTH_SHORT)
					.show();
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.frame_container, new StartActivityFragment()).commit();
			super.onPostExecute(result);
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			saveStuff();
			return null;
		}

	}

	private void saveStuff() {
		// final SharedPreferences pref = getActivity().getApplicationContext()
		// .getSharedPreferences("ActivityInfo", 0);
		//
		// if (pref.getString("details_marketing_intel", null) != null
		// && pref.getLong("competitor_product_marketing_intel", 0) != 0) {
		// Activity Info
		// long activityType = pref.getLong("activity_type", 0);
		// String checkIn = pref.getString("check_in", "");
		// String checkOut = MyDateUtils.getCurrentTimeStamp();
		// long businessUnit = pref.getLong("business_unit", 0);
		// long createdBy = pref.getLong("createdBy", 0);
		// long customer = pref.getLong("customerlong", 0);
		// long area = pref.getLong("area", 0);
		// long province = pref.getLong("province", 0);
		// long city = pref.getLong("city_town", 0);
		// long workplanEntry = pref.getLong("workplan_entry", 0);
		// String objective = pref.getString("objective", "");
		// int firstTimeVisit = pref.getInt("first_time_visit", 0);
		// int plannedVisit = pref.getInt("planned_time_visit", 0);
		// String notes = pref.getString("notes", "");
		// String highlights = pref.getString("highlights", "");
		// String nextSteps = pref.getString("next_steps", "");
		// String commitmentDate = pref.getString(
		// "follow_up_committment_date", "");
		ActivityRecord rec = Constant.activityGeneralInfo;

		// General Details

		long activityType = rec.getActivityType();
		String checkIn = rec.getCheckIn();
		String checkOut = MyDateUtils.getCurrentTimeStamp();
		long businessUnit = rec.getBusinessUnit();
		long createdBy = rec.getCreatedBy();

		// travel waiting
		String reasonsRemarks = rec.getReasonRemarks();

		// with co smr
		long smr = rec.getSmr();

		// Admin work details
		String adminDetails = rec.getAdminWorkDetails();

		// Activity details and Notes
		long customer = rec.getCustomer();
		long area = rec.getArea();
		long province = rec.getProvince();
		long city = rec.getCity();
		long workplanEntry = rec.getWorkplanEntry();
		String objective = rec.getObjective();
		int firstTimeVisit = rec.getFirstTimeVisit();
		int plannedVisit = rec.getPlannedVisit();
		String notes = rec.getNotes();
		String highlights = rec.getHighlights();
		String nextSteps = rec.getNextSteps();
		String followUpCommitmentDate = rec.getFollowUpCommitmentDate();

		// Project visit
		String projectName = rec.getProjectName();
		long projectStage = rec.getProjectStage();
		long projectCategory = rec.getProjectCategory();

		// Trainings
		String venue = rec.getVenue();
		int numberOfAttendees = rec.getNumberOfAttendees();

		// Full brand activitation
		String endUserActivityTypes = rec.getEndUserActivityTypes();

		if (AddActivityGeneralInformationFragment.ActivityType == 1) { // travel
																		// waiting

			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);

		} else if (AddActivityGeneralInformationFragment.ActivityType == 43) { // with
																				// co
																				// smr
			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);
		} else if (AddActivityGeneralInformationFragment.ActivityType == 47) {// Admin
																				// work
																				// details
			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);
			if (actRowID > 0) {

			}
		} else if (AddActivityGeneralInformationFragment.ActivityType == 4) { // Retail
																				// Visits
			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);
			if (actRowID > 0) {

				// Customer Contact
				for (CustomerContactRecord customerContact : ActivitiesConstant.ACTIVITY_CUSTOMER_CONTACT_FILTERED) {
					JardineApp.DB.getEntityRelationship().insert(actRowID, "",
							Modules.Activity, customerContact.getId(), "",
							Modules.CustomerContact, 0);
				}

				// JDI Product Stock Check
				for (JDIproductStockCheckRecord jdiProdStockRec : Constant.addJDIproductStockCheckRecords) {
					JardineApp.DB.getJDIproductStockCheck().insert("", "",
							actRowID, jdiProdStockRec.getProductBrand(),
							jdiProdStockRec.getStockStatus(),
							jdiProdStockRec.getLoadedOnShelves(),
							jdiProdStockRec.getSupplier(),
							jdiProdStockRec.getOtherRemarks(),
							jdiProdStockRec.getCreatedTime(),
							jdiProdStockRec.getModifiedTime(),
							jdiProdStockRec.getCreatedBy());
				}

				// Product Supplier
				for (ProductSupplierRecord prodSupRec : Constant.addProductSupplierRecords) {
					JardineApp.DB.getProductSupplier().insert("", "",
							prodSupRec.getProductBrand(),
							prodSupRec.getSupplier(),
							prodSupRec.getOthersRemarks(), actRowID,
							prodSupRec.getCreatedBy(),
							prodSupRec.getCreatedTime(),
							prodSupRec.getModifiedTime());
				}

				// JDI Merchandising
				for (JDImerchandisingCheckRecord jdiMerchRec : Constant.addJDImerchandisingCheckRecords) {
					JardineApp.DB.getJDImerchandisingCheck().insert("", "",
							actRowID, jdiMerchRec.getProductBrand(),
							jdiMerchRec.getStatus(),
							MyDateUtils.getCurrentTimeStamp(),
							MyDateUtils.getCurrentTimeStamp(),
							jdiMerchRec.getCreatedBy());
				}

				// Competitor Product Stock Check
				for (CompetitorProductStockCheckRecord compProdRec : Constant.addCompetitorProductRecords) {
					JardineApp.DB.getCompetitorProductStockCheck().insert("",
							"", actRowID, compProdRec.getCompetitorProduct(),
							compProdRec.getStockStatus(),
							compProdRec.getLoadedOnShelves(),
							compProdRec.getOtherRemarks(),
							MyDateUtils.getCurrentTimeStamp(),
							MyDateUtils.getCurrentTimeStamp(),
							compProdRec.getCreatedBy());
				}

				// Competitor Product Stock Check
				for (MarketingIntelRecord marketingIntelRec : Constant.addMarketingIntelRecords) {
					JardineApp.DB.getMarketingIntel().insert("", "", actRowID,
							marketingIntelRec.getCompetitorProduct(),
							marketingIntelRec.getDetails(),
							MyDateUtils.getCurrentTimeStamp(),
							MyDateUtils.getCurrentTimeStamp(),
							marketingIntelRec.getCreatedBy());
				}

				// product focus
				for (ProductRecord productFocusRec : Constant.addProductFocusRecords) {
					JardineApp.DB.getEntityRelationship().insert(actRowID, "",
							Modules.Activity, productFocusRec.getId(), "",
							Modules.Product, 0);
					// JardineApp.DB.getProductFocus().insert(productFocusRec.getId(),
					// actRowID);
				}

				// Photos and Attachements
				for (DocumentRecord docRec : ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST) {
					JardineApp.DB.getDocument().insert("", "",
							docRec.getTitle(), docRec.getModuleName(),
							docRec.getModuleId(), docRec.getFileName(),
							docRec.getFileType(), docRec.getFileType(),
							docRec.getIsActive(), actRowID,
							docRec.getCreatedTime(), docRec.getModifiedTime(),
							docRec.getUser());
				}
			}
		} else if (AddActivityGeneralInformationFragment.ActivityType == 9) {// KI
																				// Visits
																				// -
																				// On-site
			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);
			if (actRowID > 0) {

				// Customer Contact
				for (CustomerContactRecord customerContact : ActivitiesConstant.ACTIVITY_CUSTOMER_CONTACT_FILTERED) {
					JardineApp.DB.getEntityRelationship().insert(actRowID, "",
							Modules.Activity, customerContact.getId(), "",
							Modules.CustomerContact, 0);
				}

				// Photos and Attachements
				for (DocumentRecord docRec : ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST) {
					JardineApp.DB.getDocument().insert("", "",
							docRec.getTitle(), docRec.getModuleName(),
							docRec.getModuleId(), docRec.getFileName(),
							docRec.getFileType(), docRec.getFileType(),
							docRec.getIsActive(), actRowID,
							docRec.getCreatedTime(), docRec.getModifiedTime(),
							docRec.getUser());
				}
			}
		} else if (AddActivityGeneralInformationFragment.ActivityType == 101) { // Major
																				// Training
			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);
			if (actRowID > 0) {
				// Customer Contact
				for (CustomerContactRecord customerContact : ActivitiesConstant.ACTIVITY_CUSTOMER_CONTACT_FILTERED) {
					JardineApp.DB.getEntityRelationship().insert(actRowID, "",
							Modules.Activity, customerContact.getId(), "",
							Modules.CustomerContact, 0);
				}

				// Photos and Attachements
				for (DocumentRecord docRec : ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST) {
					JardineApp.DB.getDocument().insert("", "",
							docRec.getTitle(), docRec.getModuleName(),
							docRec.getModuleId(), docRec.getFileName(),
							docRec.getFileType(), docRec.getFileType(),
							docRec.getIsActive(), actRowID,
							docRec.getCreatedTime(), docRec.getModifiedTime(),
							docRec.getUser());
				}
			}
		} else if (AddActivityGeneralInformationFragment.ActivityType == 102) { // End
																				// User
																				// Activity
			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);
			if (actRowID > 0) {
				// Customer Contact
				for (CustomerContactRecord customerContact : ActivitiesConstant.ACTIVITY_CUSTOMER_CONTACT_FILTERED) {
					JardineApp.DB.getEntityRelationship().insert(actRowID, "",
							Modules.Activity, customerContact.getId(), "",
							Modules.CustomerContact, 0);
				}

				// product focus
				for (ProductRecord productFocusRec : Constant.addProductFocusRecords) {
					JardineApp.DB.getEntityRelationship().insert(actRowID, "",
							Modules.Activity, productFocusRec.getId(), "",
							Modules.Product, 0);
					// JardineApp.DB.getProductFocus().insert(productFocusRec.getId(),
					// actRowID);
				}

				// Photos and Attachements
				for (DocumentRecord docRec : ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST) {
					JardineApp.DB.getDocument().insert("", "",
							docRec.getTitle(), docRec.getModuleName(),
							docRec.getModuleId(), docRec.getFileName(),
							docRec.getFileType(), docRec.getFileType(),
							docRec.getIsActive(), actRowID,
							docRec.getCreatedTime(), docRec.getModifiedTime(),
							docRec.getUser());
				}
			}
		} else if (AddActivityGeneralInformationFragment.ActivityType == 102) { // Full
																				// Brand
																				// Activation
			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);
			if (actRowID > 0) {
				// Customer Contact
				for (CustomerContactRecord customerContact : ActivitiesConstant.ACTIVITY_CUSTOMER_CONTACT_FILTERED) {
					JardineApp.DB.getEntityRelationship().insert(actRowID, "",
							Modules.Activity, customerContact.getId(), "",
							Modules.CustomerContact, 0);
				}

				// Photos and Attachements
				for (DocumentRecord docRec : ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST) {
					JardineApp.DB.getDocument().insert("", "",
							docRec.getTitle(), docRec.getModuleName(),
							docRec.getModuleId(), docRec.getFileName(),
							docRec.getFileType(), docRec.getFileType(),
							docRec.getIsActive(), actRowID,
							docRec.getCreatedTime(), docRec.getModifiedTime(),
							docRec.getUser());
				}
			}
		} else {
			long actRowID = JardineApp.DB.getActivity().insert("", "",
					activityType, checkIn, checkOut, businessUnit, createdBy,
					dLongitude, dLatitude, MyDateUtils.getCurrentTimeStamp(),
					MyDateUtils.getCurrentTimeStamp(), reasonsRemarks, smr,
					adminDetails, customer, area, province, city,
					workplanEntry, objective, firstTimeVisit, plannedVisit,
					notes, highlights, nextSteps, followUpCommitmentDate,
					projectName, projectStage, projectCategory, venue,
					numberOfAttendees, endUserActivityTypes);
			if (actRowID > 0) {
				// Customer Contact
				for (CustomerContactRecord customerContact : ActivitiesConstant.ACTIVITY_CUSTOMER_CONTACT_FILTERED) {
					JardineApp.DB.getEntityRelationship().insert(actRowID, "",
							Modules.Activity, customerContact.getId(), "",
							Modules.CustomerContact, 0);
				}

				// Photos and Attachements
				for (DocumentRecord docRec : ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST) {
					JardineApp.DB.getDocument().insert("", "",
							docRec.getTitle(), docRec.getModuleName(),
							docRec.getModuleId(), docRec.getFileName(),
							docRec.getFileType(), docRec.getFileType(),
							docRec.getIsActive(), actRowID,
							docRec.getCreatedTime(), docRec.getModifiedTime(),
							docRec.getUser());
				}
			}
		}
		// }
	}

	// @Override
	// public void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// fragment.onActivityResult(requestCode, resultCode, data);
	// }

	@Override
	public void onLocationChanged(Location location) {
		try {
			// uLongitude = round(location.getLongitude(), 1);
			// uLatitude = round(location.getLatitude(), 1);

			dLongitude = location.getLongitude();
			dLatitude = location.getLatitude();

		} catch (Exception e) {

		}

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	private void getGpsCoordinates() {
		LocationManager lm = (LocationManager) getActivity().getSystemService(
				getActivity().LOCATION_SERVICE);
		List<String> providers = lm.getProviders(true);

		/*
		 * Loop over the array backwards, and if you get an accurate location,
		 * then break out the loop
		 */
		Location l = null;

		for (int i = providers.size() - 1; i >= 0; i--) {
			l = lm.getLastKnownLocation(providers.get(i));
			if (l != null)
				break;
		}

		if (l != null) {
			dLatitude = l.getLatitude();
			dLongitude = l.getLongitude();
		}

	}

}
