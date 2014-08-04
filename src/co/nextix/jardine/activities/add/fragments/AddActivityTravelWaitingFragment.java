package co.nextix.jardine.activities.add.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.fragments.StartActivityFragment;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;

import com.dd.CircularProgressButton;

public class AddActivityTravelWaitingFragment extends Fragment implements
		LocationListener {

	private boolean flag = false;
	private CircularProgressButton saveBtn = null;
	private EditText editTxtReasons;
	private double dLongitude, dLatitude;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(
				R.layout.add_activity_travel_or_waiting, container, false);
		editTxtReasons = (EditText) view.findViewById(R.id.reason_remarks);
		// SharedPreferences pref =
		// getActivity().getApplicationContext().getSharedPreferences("ActivityInfo",
		// 0);
		//
		// String reasonRemarks = pref.getString("activity_id_reasons_remarks",
		// null);
		// if (reasonRemarks != null) {
		// ((EditText)
		// view.findViewById(R.id.reason_remarks)).setText(reasonRemarks);
		// }

		this.saveBtn = (CircularProgressButton) view
				.findViewById(R.id.btnWithText1);
		this.saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation
							.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation
							.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
								@Override
								public void onAnimationUpdate(
										ValueAnimator animation) {

									Integer value = (Integer) animation
											.getAnimatedValue();
									((CircularProgressButton) v)
											.setProgress(value);

									if (!flag) {
										((CircularProgressButton) v)
												.setProgress(-1);
									}
								}
							});

					widthAnimation.start();

					String reasons = null;

					try {
						reasons = editTxtReasons.getText().toString();
					} catch (Exception e) {

					}

					if (reasons == null)
						Constant.activityGeneralInfo.setReasonRemarks("");
					else
						Constant.activityGeneralInfo.setReasonRemarks(reasons);

					/** Checking of required fields **/
					// final SharedPreferences pref = getActivity()
					// .getApplicationContext().getSharedPreferences(
					// "ActivityInfo", 0);
					if (reasons != null && !reasons.isEmpty()) {
						flag = true;

						new InsertDBTask().execute();

						// new InsertTask("0", pref.getString("crm_no", null),
						// pref.getLong("activity_type", 0), pref
						// .getString("check_in", null), pref
						// .getString("check_out", null).concat(
						// displayCheckOut()), pref
						// .getLong("business_unit", 0),
						// StoreAccount.restore(getActivity()).getLong(
						// Account.ROWID), 123.894882, 10.310235,
						// pref.getString("check_in", null), pref
						// .getString("check_out", null).concat(
						// displayCheckOut()), reasons, 0,
						// "", 0, 0, 0, 0,
						// AddActivityFragment.WORKPLAN_ENTRY_ID, "", 0,
						// 0, "", "", "", "", "", 0, 0, "", 0, "")
						// .execute();
						//
						// Handler handler = new Handler();
						// handler.postDelayed(new Runnable() {
						//
						// @Override
						// public void run() {
						// pref.edit().clear().commit();
						// getFragmentManager().popBackStackImmediate();
						// v.setClickable(true);
						// v.setEnabled(true);
						// }
						//
						// }, 1500);

					} else {

						flag = false;
						Toast.makeText(getActivity(),
								"Please fill up required (RED COLOR) fields",
								Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);
				}
			}
		});

		return view;
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
					.replace(R.id.frame_container, new StartActivityFragment())
					.commit();
			super.onPostExecute(result);
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			saveStuff();
			return null;
		}

	}

	private void saveStuff() {
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

	// protected class InsertTask extends AsyncTask<Void, Void, Boolean> {
	//
	// private ValueAnimator widthAnimation = null;
	//
	// // String to be populated
	// private String no = null;
	// private String crmNo = null;
	//
	// private long activityType = 0000;
	//
	// private String checkIn = null;
	// private String checkOut = null;
	//
	// private long businessUnit = 0000;
	// private long createdBy = 000;
	//
	// private double longitude = 0.00;
	// private double latitude = 0.00;
	//
	// private String createdTime = null;
	// private String modifiedTime = null;
	// private String reasonsRemarks = null;
	//
	// private long smr = 0000;
	// private String adminDetails = null;
	//
	// private long customer = 0000;
	// private long area = 0000;
	// private long province = 0000;
	// private long city = 0000;
	// private long workplanEntry = 0000;
	//
	// private String objective = null;
	//
	// private int firstTimeVisit = 0;
	// private int plannedVisit = 0;
	//
	// private String notes = null;
	// private String highlights = null;
	// private String nextSteps = null;
	// private String followUpCommitmentDate = null;
	// private String projectName = null;
	// private long projectStage = 0;
	// private long projectCategory = 0;
	// private String venue = null;
	//
	// private int numberOfAttendees = 0;
	// private String endUserActivityTypes = null;
	//
	// private boolean flag;
	//
	// private InsertTask(String no, String crmNo, long activityType,
	// String checkIn, String checkOut, long businessUnit,
	// long createdBy, double longitude, double latitude,
	// String createdTime, String modifiedTime, String reasonsRemarks,
	// long smr, String adminDetails, long customer, long area,
	// long province, long city, long workplanEntry, String objective,
	// int firstTimeVisit, int plannedVisit, String notes,
	// String highlights, String nextSteps,
	// String followUpCommitmentDate, String projectName,
	// long projectStage, long projectCategory, String venue,
	// int numberOfAttendees, String endUserActivityTypes) {
	//
	// this.no = no;
	// this.crmNo = crmNo;
	// this.activityType = activityType;
	// this.checkIn = checkIn;
	// this.checkOut = checkOut;
	// this.businessUnit = businessUnit;
	// this.createdBy = createdBy;
	// this.longitude = longitude;
	// this.latitude = latitude;
	// this.createdTime = createdTime;
	// this.modifiedTime = modifiedTime;
	// this.reasonsRemarks = reasonsRemarks;
	// this.smr = smr;
	// this.adminDetails = adminDetails;
	// this.customer = customer;
	// this.area = area;
	// this.province = province;
	// this.city = city;
	// this.workplanEntry = workplanEntry;
	// this.objective = objective;
	// this.firstTimeVisit = firstTimeVisit;
	// this.plannedVisit = plannedVisit;
	// this.notes = notes;
	// this.highlights = highlights;
	// this.nextSteps = nextSteps;
	// this.followUpCommitmentDate = followUpCommitmentDate;
	// this.projectName = projectName;
	// this.projectStage = projectStage;
	// this.projectCategory = projectCategory;
	// this.venue = venue;
	// this.numberOfAttendees = numberOfAttendees;
	// this.endUserActivityTypes = endUserActivityTypes;
	// }
	//
	// @Override
	// protected void onPreExecute() {
	// // Animate Button
	// this.widthAnimation = ValueAnimator.ofInt(1, 100);
	// this.widthAnimation.setDuration(1500);
	// this.widthAnimation
	// .setInterpolator(new AccelerateDecelerateInterpolator());
	// this.widthAnimation
	// .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
	// @Override
	// public void onAnimationUpdate(ValueAnimator animation) {
	// Integer value = (Integer) animation
	// .getAnimatedValue();
	// saveBtn.setProgress(value);
	//
	// if (!flag) {
	// saveBtn.setProgress(-1);
	// }
	// }
	// });
	//
	// this.widthAnimation.start();
	// }
	//
	// @Override
	// protected Boolean doInBackground(Void... params) {
	// this.flag = false;
	// try {
	//
	// saveActivity(this.no, this.crmNo, this.activityType,
	// this.checkIn, this.checkOut, this.businessUnit,
	// this.createdBy, this.longitude, this.latitude,
	// this.createdTime, this.modifiedTime,
	// this.reasonsRemarks, this.smr, this.adminDetails,
	// this.customer, this.area, this.province, this.city,
	// this.workplanEntry, this.objective,
	// this.firstTimeVisit, this.plannedVisit, this.notes,
	// this.highlights, this.nextSteps,
	// this.followUpCommitmentDate, this.projectName,
	// this.projectStage, this.projectCategory, this.venue,
	// this.numberOfAttendees, this.endUserActivityTypes);
	//
	// this.flag = true;
	// } catch (Exception e) {
	// this.flag = false;
	// Log.e("Jardine", e.toString());
	// }
	//
	// return this.flag;
	// }
	//
	// @Override
	// protected void onPostExecute(Boolean result) {
	// if (result) {
	// saveBtn.setProgress(100);
	// } else {
	// this.flag = false;
	// }
	// }
	// }
	//
	// protected String displayCheckOut() {
	// Calendar calendar = Calendar.getInstance();
	// SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	// return " " + df.format(calendar.getTime());
	// }
	//
	// protected void saveActivity(String no, String crmNo, long activityType,
	// String checkIn, String checkOut, long businessUnit, long createdBy,
	// double longitude, double latitude, String createdTime,
	// String modifiedTime, String reasonsRemarks, long smr,
	// String adminDetails, long customer, long area, long province,
	// long city, long workplanEntry, String objective,
	// int firstTimeVisit, int plannedVisit, String notes,
	// String highlights, String nextSteps, String followUpCommitmentDate,
	// String projectName, long projectStage, long projectCategory,
	// String venue, int numberOfAttendees, String endUserActivityTypes) {
	//
	// // Insert to the database
	// JardineApp.DB.getActivity()
	// .insert(no, crmNo, activityType, checkIn, checkOut,
	// businessUnit, createdBy, longitude, latitude,
	// createdTime, modifiedTime, reasonsRemarks, smr,
	// adminDetails, customer, area, province, city,
	// workplanEntry, objective, firstTimeVisit, plannedVisit,
	// notes, highlights, nextSteps, followUpCommitmentDate,
	// projectName, projectStage, projectCategory, venue,
	// numberOfAttendees, endUserActivityTypes);
	// }
}
