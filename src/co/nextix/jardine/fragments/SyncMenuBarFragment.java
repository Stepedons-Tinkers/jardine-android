package co.nextix.jardine.fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.database.records.FileRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProjectRequirementRecord;
import co.nextix.jardine.database.records.SMRRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.BusinessUnitTable;
import co.nextix.jardine.database.tables.CalendarTable;
import co.nextix.jardine.database.tables.CompetitorProductStockCheckTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.CustomerContactTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.DocumentTable;
import co.nextix.jardine.database.tables.EventProtocolTable;
import co.nextix.jardine.database.tables.JDImerchandisingCheckTable;
import co.nextix.jardine.database.tables.JDIproductStockCheckTable;
import co.nextix.jardine.database.tables.MarketingIntelTable;
import co.nextix.jardine.database.tables.MarketingMaterialsTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.ProjectRequirementTable;
import co.nextix.jardine.database.tables.SMRTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.WorkplanEntryTable;
import co.nextix.jardine.database.tables.WorkplanTable;
import co.nextix.jardine.database.tables.picklists.PActProjCategoryTable;
import co.nextix.jardine.database.tables.picklists.PActProjStageTable;
import co.nextix.jardine.database.tables.picklists.PActtypeCategoryTable;
import co.nextix.jardine.database.tables.picklists.PAreaTable;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PComptProdStockStatusTable;
import co.nextix.jardine.database.tables.picklists.PCustConPositionTable;
import co.nextix.jardine.database.tables.picklists.PCustSizeTable;
import co.nextix.jardine.database.tables.picklists.PCustTypeTable;
import co.nextix.jardine.database.tables.picklists.PEventTypeTable;
import co.nextix.jardine.database.tables.picklists.PJDImerchCheckStatusTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;
import co.nextix.jardine.database.tables.picklists.PProjReqTypeTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.database.tables.picklists.PSMRentryTypeTable;
import co.nextix.jardine.database.tables.picklists.PWorkEntryStatusTable;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.web.CreateRequests;
import co.nextix.jardine.web.LogRequests;
import co.nextix.jardine.web.PicklistRequests;
import co.nextix.jardine.web.RetrieveRequests;
import co.nextix.jardine.web.SyncRequests;
import co.nextix.jardine.web.UpdateRequests;
import co.nextix.jardine.web.models.ActivityModel;
import co.nextix.jardine.web.models.ActivityTypeModel;
import co.nextix.jardine.web.models.BusinessUnitModel;
import co.nextix.jardine.web.models.CalendarModel;
import co.nextix.jardine.web.models.CompetitorProductModel;
import co.nextix.jardine.web.models.CompetitorProductStockCheckModel;
import co.nextix.jardine.web.models.CustomerContactModel;
import co.nextix.jardine.web.models.CustomerModel;
import co.nextix.jardine.web.models.DocuRelModel;
import co.nextix.jardine.web.models.DocumentModel;
import co.nextix.jardine.web.models.EventProtocolModel;
import co.nextix.jardine.web.models.JDImerchandisingCheckModel;
import co.nextix.jardine.web.models.JDIproductStockCheckModel;
import co.nextix.jardine.web.models.MarketingIntelModel;
import co.nextix.jardine.web.models.MarketingMaterialsModel;
import co.nextix.jardine.web.models.PicklistDependencyModel;
import co.nextix.jardine.web.models.ProductModel;
import co.nextix.jardine.web.models.ProjectRequirementModel;
import co.nextix.jardine.web.models.SMRModel;
import co.nextix.jardine.web.models.WorkplanEntryModel;
import co.nextix.jardine.web.models.WorkplanModel;
import co.nextix.jardine.web.requesters.LoginModel;
import co.nextix.jardine.web.requesters.WebCreateModel;
import co.nextix.jardine.web.requesters.sync.SactRequester.ActResult;
import co.nextix.jardine.web.requesters.sync.SacttypeRequester.ActTypeResult;
import co.nextix.jardine.web.requesters.sync.SbuRequester.BuResult;
import co.nextix.jardine.web.requesters.sync.ScalendarRequester.CalendarResult;
import co.nextix.jardine.web.requesters.sync.ScompetrprodstockRequester.ComptProdStockResult;
import co.nextix.jardine.web.requesters.sync.ScustRequester.CustResult;
import co.nextix.jardine.web.requesters.sync.ScustconRequester.CustConResult;
import co.nextix.jardine.web.requesters.sync.SeventRequester.EventResult;
import co.nextix.jardine.web.requesters.sync.SjdimerchRequester.JdiMerchResult;
import co.nextix.jardine.web.requesters.sync.SjdiprodRequester.JdiProdResult;
import co.nextix.jardine.web.requesters.sync.SmarkintRequester.MarketIntResult;
import co.nextix.jardine.web.requesters.sync.SmarmatRequester.MarketMatResult;
import co.nextix.jardine.web.requesters.sync.SproductRequester.ProdResult;
import co.nextix.jardine.web.requesters.sync.SprojreqRequester.ProjReqResult;
import co.nextix.jardine.web.requesters.sync.SsmrRequester.SmrResult;
import co.nextix.jardine.web.requesters.sync.SworkplanRequester.WorkResult;
import co.nextix.jardine.web.requesters.sync.SworkplanentryRequester.WorkEntryResult;

public class SyncMenuBarFragment extends Fragment {

	private final String TAG = "Webservice";
	ProgressDialog dialog;
	long USER_ID = JardineApp.DB.getUser().getCurrentUser().getId();
	String LAST_SYNC = JardineApp.DB.getUser().getLastSync();
	List<FileRecord> Event_Files_IDs = new ArrayList<FileRecord>();
	List<FileRecord> Marketing_Files_IDs = new ArrayList<FileRecord>();
	protected PowerManager.WakeLock mWakeLock;
	boolean isCancelled = false;
	boolean isConnected = true;

	private final Context CONTEXT = getActivity();

	public SyncMenuBarFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		View rootView = inflater.inflate(R.layout.fragment_sync, container,
				false);

		final PowerManager pm = (PowerManager) getActivity().getSystemService(
				Context.POWER_SERVICE);
		this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"My Tag");
		this.mWakeLock.acquire();

		if (LAST_SYNC.equals("")) {
			LAST_SYNC = MyDateUtils.getOneYearAgo();
		}

		new LoginTask().execute();

		return rootView;
	}

	@Override
	public void onDestroy() {
		this.mWakeLock.release();
		getFragmentManager();
		getFragmentManager().popBackStack("sync",
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
		super.onDestroy();
	}

	private class CreateCompetitorProductTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("CompetitorProduct");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CompetitorProductTable table = JardineApp.DB.getCompetitorProduct();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.competitorProduct(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new CreateCustomerTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	// private class CreateSMRtimeCardTask extends AsyncTask<Void, Void,
	// Boolean> {
	//
	// @Override
	// protected void onPreExecute() {
	// // dialog = new ProgressDialog(getActivity());
	// dialog.setTitle("Create");
	// dialog.setMessage("SMRtimeCard");
	// dialog.setCancelable(false);
	// dialog.setCanceledOnTouchOutside(false);
	// dialog.show();
	// super.onPreExecute();
	// }
	//
	// @Override
	// protected Boolean doInBackground(Void... arg0) {
	//
	// SMRtimeCardTable table = JardineApp.DB.getSMRTimeCard();
	//
	// CreateRequests request = new CreateRequests();
	// List<WebCreateModel> results = request.smrTimecard(table
	// .getUnsyncedRecords());
	// if (results != null) {
	// for (WebCreateModel model : results) {
	// table.updateNo(model.getMobileId(),
	// String.valueOf(model.getCrmNo()));
	// }
	// }
	//
	// return true;
	// }
	//
	// @Override
	// protected void onPostExecute(Boolean result) {
	//
	// if (result) {
	// new CreateCustomerTask().execute();
	// } else {
	// dialog.dismiss();
	// Toast.makeText(getActivity(), "Check Internet connection",
	// Toast.LENGTH_SHORT).show();
	// }
	// }
	// }

	private class CreateCustomerTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("Customer");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CustomerTable table = JardineApp.DB.getCustomer();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.customer(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new CreateCustomerContactTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateCustomerContactTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("CustomerContact");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CustomerContactTable table = JardineApp.DB.getCustomerContact();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.customerContact(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new CreateActivityTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateActivityTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("Activity");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			ActivityTable table = JardineApp.DB.getActivity();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.activity(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new CreateJDImerchCheckTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateJDImerchCheckTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("JDImerchandisingCheck");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			JDImerchandisingCheckTable table = JardineApp.DB
					.getJDImerchandisingCheck();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.jdiMerchandising(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new CreateJDIproductCheckTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateJDIproductCheckTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("JDIproductCheck");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			JDIproductStockCheckTable table = JardineApp.DB
					.getJDIproductStockCheck();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.jdiProductStock(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new CreateCompetitorProductCheckTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateCompetitorProductCheckTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("CompetitorProductStockCheck");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CompetitorProductStockCheckTable table = JardineApp.DB
					.getCompetitorProductStockCheck();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.competitorProductStock(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new CreateMarketingIntelTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateMarketingIntelTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("MarketingIntel");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			MarketingIntelTable table = JardineApp.DB.getMarketingIntel();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.marketingIntel(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new CreateProjectRequirementTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateProjectRequirementTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("ProjectRequirementTask");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			ProjectRequirementTable table = JardineApp.DB
					.getProjectRequirement();

			CreateRequests request = new CreateRequests();
			List<WebCreateModel> results = request.projectRequirements(table
					.getUnsyncedRecords());
			if (results != null) {
				for (WebCreateModel model : results) {
					table.updateNo(model.getMobileId(),
							String.valueOf(model.getCrmNo()));
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// UserTable userTable = JardineApp.DB.getUser();
			// userTable
			// .updateLastsync(USER_ID, MyDateUtils.getCurrentTimeStamp());
			//
			// dialog.dismiss();
			if (result) {
				new CreateDocumentsTask().execute();

			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateDocumentsTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("Documents");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			DocumentTable table = JardineApp.DB.getDocument();
			List<DocumentRecord> records = table.getUnsyncedRecords();

			if (records != null) {
				CreateRequests request = new CreateRequests();
				for (DocumentRecord rec : records) {
					List<WebCreateModel> results = request.documents(rec);
					if (results != null) {
						for (WebCreateModel model : results) {
							table.updateNo(model.getMobileId(),
									String.valueOf(model.getCrmNo()));
						}
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			dialog.dismiss();
			if (result) {
				new PicklistDependencyTask().execute();

			} else {

				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class PicklistDependencyTask extends AsyncTask<Void, Void, Boolean> {
		List<PicklistDependencyModel> areas, provinces;
		long aId = 0, pId = 0;

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Please wait...");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Log.i(TAG, "cancel clicked");
							cancel(true);
						}
					});
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onCancelled() {
			isCancelled = true;
			Log.e(TAG, "### onCancelled ###");
			dialog.dismiss();
			super.onCancelled();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			PAreaTable aTable = JardineApp.DB.getArea();
			PProvinceTable pTable = JardineApp.DB.getProvince();
			PCityTownTable cTable = JardineApp.DB.getCityTown();

			PicklistRequests request = new PicklistRequests();
			areas = request.area();
			if (areas != null) {

				for (PicklistDependencyModel a : areas) {
					if (isCancelled()) {
						break;
					}
					String area = a.getSourceValue().replace("&quot;", "");
					if (!aTable.isExisting(area)) {
						aId = aTable.insertArea(area);
						// Log.d(TAG, "AREA: " + area);
						String pr = a.getTargetValues();
						if (pr != null) {
							String SAprovinces = pr.replace("[", "")
									.replace("]", "").replace("&quot;", "");
							// Log.w(TAG, "provinces: " + SAprovinces);
							List<String> provinces = Arrays.asList(SAprovinces
									.split("\\s*,\\s*"));
							if (provinces != null) {
								for (String p : provinces) {
									String prov = p.replace("&quot;", "");
									if (!pTable.isExisting(prov)) {
										pTable.insertProvince(prov, aId);
										// Log.i(TAG, "Province: " + prov);
									}
								}
							}
						}
					}

				}
			}

			provinces = request.province();

			if (provinces != null) {
				for (PicklistDependencyModel a : provinces) {
					if (isCancelled()) {
						break;
					}
					String province = a.getSourceValue().replace("&quot;", "");
					pId = pTable.getIdByName(province);
					if (pId != 0) {
						String ct = a.getTargetValues();
						if (ct != null) {
							String SAcities = ct.replace("[", "")
									.replace("]", "").replace("&quot;", "");
							// Log.w(TAG, "cities: " + SAcities);
							List<String> cities = Arrays.asList(SAcities
									.split("\\s*,\\s*"));
							if (cities != null) {
								for (String c : cities) {
									String cty = c.replace("&quot;", "");
									if (!cTable.isExisting(cty)) {
										cTable.insert(cty, pId);
										// Log.i(TAG, "City: " + cty);
									}
								}
							}
						}
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				new PicklistsTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class PicklistsTask extends AsyncTask<Void, Void, Boolean> {
		List<String> picklist;

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Please wait...");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Log.i(TAG, "cancel clicked");
							cancel(true);
						}
					});
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onCancelled() {
			isCancelled = true;
			Log.e(TAG, "### onCancelled ###");
			dialog.dismiss();
			super.onCancelled();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			PicklistRequests request = new PicklistRequests();
			for (String module : Modules.picklists) {
				if (isCancelled()) {
					break;
				}
				picklist = request.picklists(module);
				if (picklist != null) {
					if (module.equals(Modules.smrtimecard_entry)) {
						PSMRentryTypeTable table = JardineApp.DB
								.getSMRentryType();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.customer_size)) {
						PCustSizeTable table = JardineApp.DB.getCustomerSize();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.customer_type)) {
						PCustTypeTable table = JardineApp.DB.getCustomerType();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.customercontact_position)) {
						PCustConPositionTable table = JardineApp.DB
								.getCustomerContactPosition();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.eventprotocol_eventtype)) {
						PEventTypeTable table = JardineApp.DB
								.getEventProtocolType();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.activitytype_category)) {
						PActtypeCategoryTable table = JardineApp.DB
								.getActivitytypeCategory();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.workplanentry_status)) {
						PWorkEntryStatusTable table = JardineApp.DB
								.getWorkplanEntryStatus();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.activity_projectstage)) {
						PActProjStageTable table = JardineApp.DB
								.getActivityProjectStage();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.activity_projectcategory)) {
						PActProjCategoryTable table = JardineApp.DB
								.getActivityProjectCategory();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.jdiprodstock_status)) {
						PJDIprodStatusTable table = JardineApp.DB
								.getJDIproductStatus();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.jdimerchcheck_status)) {
						PJDImerchCheckStatusTable table = JardineApp.DB
								.getJDImerchCheckStatus();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.projrequirement_type)) {
						PProjReqTypeTable table = JardineApp.DB
								.getProjectRequirementType();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					}
				}
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncBusinessUnitTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncBusinessUnitTask extends AsyncTask<Void, Void, Boolean> {
		List<BusinessUnitModel> updated = new ArrayList<BusinessUnitModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("BusinessUnit");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Log.i(TAG, "cancel clicked");
							cancel(true);
						}
					});
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onCancelled() {
			isCancelled = true;
			Log.e(TAG, "### onCancelled ###");
			dialog.dismiss();
			super.onCancelled();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			Log.e(TAG, "###********************************** businessUnit ###");
			BusinessUnitTable table = JardineApp.DB.getBusinessUnit();

			SyncRequests request = new SyncRequests();
			BuResult result = request.BusinessUnit(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (BusinessUnitModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getName(), model.getCode(),
								Integer.parseInt(model.getIsactive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());
						if (id != 0) {
							BusinessUnitRecord record = table.getById(id);
							if (MyDateUtils.isTimeAfter(
									model.getModifiedTime(),
									record.getModifiedTime()) > 0) {
								table.update(id, model.getRecordId(),
										model.getCrmNo(), model.getName(),
										model.getCode(),
										Integer.parseInt(model.getIsactive()),
										model.getCreatedTime(),
										model.getModifiedTime(), USER_ID);
								Log.i(TAG, "update: " + id);
							}
						}
					}
				}
			}
			if (isCancelled()) {
				cancel(true);
			} else {
				if (deleted.size() > 0) {
					int num = table.deleteByCrmNo(deleted
							.toArray(new String[deleted.size()]));
					Log.d(TAG, num + " records deleted");
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncMarketingMaterialsTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncMarketingMaterialsTask extends
			AsyncTask<Void, Void, Boolean> {
		List<MarketingMaterialsModel> updated = new ArrayList<MarketingMaterialsModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("MarketingMaterials");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			MarketingMaterialsTable table = JardineApp.DB
					.getMarketingMaterials();

			SyncRequests request = new SyncRequests();
			MarketMatResult result = request.MarketingMaterials(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();

			if (updated != null) {
				for (MarketingMaterialsModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						// TODO add business unit, isnew, isactive
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getDescription(), model.getLastUpdat(),
								model.getTags(), 0, 1, 1,
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						MarketingMaterialsRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {
							// TODO add business unit, isnew, isactive
							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getDescription(),
									model.getLastUpdat(), model.getTags(), 0,
									1, 1, model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						}

					}
				}
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncEventProtocolTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncEventProtocolTask extends AsyncTask<Void, Void, Boolean> {
		List<EventProtocolModel> updated = new ArrayList<EventProtocolModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("EventProtocol");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			EventProtocolTable table = JardineApp.DB.getEventProtocol();
			// event type
			PEventTypeTable eventTypeTable = JardineApp.DB
					.getEventProtocolType();

			SyncRequests request = new SyncRequests();
			EventResult result = request.EventProtocols(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (EventProtocolModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long eventType = eventTypeTable.getIdByName(model
								.getType());

						// if (eventType > 0)
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getDescription(), model.getLastUpdat(),
								model.getTags(), eventType,
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);

					} else {
						long id = table.getIdByNo(model.getRecordId());

						EventProtocolRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {
							long eventType = eventTypeTable.getIdByName(model
									.getType());

							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getDescription(),
									model.getLastUpdat(), model.getTags(),
									eventType,
									Integer.parseInt(model.getIsActive()),
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						}

					}
				}
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncProductTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncProductTask extends AsyncTask<Void, Void, Boolean> {
		List<ProductModel> updated = new ArrayList<ProductModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Product");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			ProductTable table = JardineApp.DB.getProduct();
			BusinessUnitTable busTable = JardineApp.DB.getBusinessUnit();

			SyncRequests request = new SyncRequests();
			ProdResult result = request.Product(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (ProductModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long businessUnit = busTable.getIdByNo(model
								.getBusinessUnit());

						// if (businessUnit > 0)
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getProdNum(), model.getProdBrand(),
								model.getDescription(), model.getProdSize(),
								businessUnit,
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);

					} else {
						long id = table.getIdByNo(model.getRecordId());

						ProductRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {
							long businessUnit = busTable.getIdByNo(model
									.getBusinessUnit());

							// if (businessUnit > 0)
							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getProdNum(),
									model.getProdBrand(),
									model.getDescription(),
									model.getProdSize(), businessUnit,
									Integer.parseInt(model.getIsActive()),
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						}

					}
				}
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncCompetitorProductTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	// private class SyncSupplierTask extends AsyncTask<Void, Void, Boolean> {
	// List<SupplierModel> updated = new ArrayList<SupplierModel>();
	// List<String> deleted = new ArrayList<String>();
	//
	// @Override
	// protected void onPreExecute() {
	// // dialog = new ProgressDialog(getActivity());
	// dialog.setTitle("Syncing");
	// dialog.setMessage("Supplier");
	// dialog.setCancelable(false);
	// dialog.setCanceledOnTouchOutside(false);
	// dialog.show();
	// super.onPreExecute();
	// }
	//
	// @Override
	// protected Boolean doInBackground(Void... arg0) {
	//
	// SupplierTable table = JardineApp.DB.getSupplier();
	//
	// SyncRequests request = new SyncRequests();
	// SuppResult result = request.Supplier(LAST_SYNC);
	// updated = result.getUpdated();
	// deleted = result.getDeleted();
	// if (updated != null) {
	// for (SupplierModel model : updated) {
	// if (!table.isExisting(model.getRecordId())) {
	//
	// table.insert(model.getRecordId(), model.getCrmNo(),
	// model.getName(), model.getLandline(),
	// model.getAddress(), model.getCreditLine(),
	// model.getCreditTerm(),
	// model.getContactPerson(),
	// Integer.parseInt(model.getIsActive()),
	// model.getCreatedTime(),
	// model.getModifiedTime(), USER_ID);
	// } else {
	// long id = table.getIdByNo(model.getRecordId());
	//
	// SupplierRecord record = table.getById(id);
	// if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
	// record.getModifiedTime()) > 0) {
	//
	// table.update(id, model.getRecordId(),
	// model.getCrmNo(), model.getName(),
	// model.getLandline(), model.getAddress(),
	// model.getCreditLine(),
	// model.getCreditTerm(),
	// model.getContactPerson(),
	// Integer.parseInt(model.getIsActive()),
	// model.getCreatedTime(),
	// model.getModifiedTime(), USER_ID);
	// Log.i(TAG, "update: " + id);
	// }
	//
	// }
	// }
	// }
	//
	// if (deleted.size() > 0) {
	// int num = table.deleteByCrmNo(deleted
	// .toArray(new String[deleted.size()]));
	// Log.d(TAG, num + " records deleted");
	// }
	//
	// return true;
	// }
	//
	// @Override
	// protected void onPostExecute(Boolean result) {
	//
	// if (result) {
	// new SyncCompetitorTask().execute();
	// } else {
	// dialog.dismiss();
	// Toast.makeText(getActivity(), "Check Internet connection",
	// Toast.LENGTH_SHORT).show();
	// }
	// }
	// }
	//
	// private class SyncCompetitorTask extends AsyncTask<Void, Void, Boolean> {
	// List<CompetitorModel> updated = new ArrayList<CompetitorModel>();
	// List<String> deleted = new ArrayList<String>();
	//
	// @Override
	// protected void onPreExecute() {
	// // dialog = new ProgressDialog(getActivity());
	// dialog.setTitle("Syncing");
	// dialog.setMessage("Competitor");
	// dialog.setCancelable(false);
	// dialog.setCanceledOnTouchOutside(false);
	// dialog.show();
	// super.onPreExecute();
	// }
	//
	// @Override
	// protected Boolean doInBackground(Void... arg0) {
	//
	// CompetitorTable table = JardineApp.DB.getCompetitor();
	//
	// SyncRequests request = new SyncRequests();
	// ComptResult result = request.Competitor(LAST_SYNC);
	// updated = result.getUpdated();
	// deleted = result.getDeleted();
	// if (updated != null) {
	// for (CompetitorModel model : updated) {
	// if (!table.isExisting(model.getRecordId())) {
	//
	// table.insert(model.getRecordId(), model.getCrmNo(),
	// model.getName(),
	// Integer.parseInt(model.getIsActive()),
	// model.getCreatedTime(),
	// model.getModifiedTime(), USER_ID);
	// } else {
	// long id = table.getIdByNo(model.getRecordId());
	//
	// CompetitorRecord record = table.getById(id);
	// if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
	// record.getModifiedTime()) > 0) {
	//
	// table.update(id, model.getRecordId(),
	// model.getCrmNo(), model.getName(),
	// Integer.parseInt(model.getIsActive()),
	// model.getCreatedTime(),
	// model.getModifiedTime(), USER_ID);
	// Log.i(TAG, "update: " + id);
	// }
	//
	// }
	// }
	// }
	//
	// if (deleted.size() > 0) {
	// int num = table.deleteByCrmNo(deleted
	// .toArray(new String[deleted.size()]));
	// Log.d(TAG, num + " records deleted");
	// }
	//
	// return true;
	// }
	//
	// @Override
	// protected void onPostExecute(Boolean result) {
	//
	// if (result) {
	// new SyncCompetitorProductTask().execute();
	// } else {
	// dialog.dismiss();
	// Toast.makeText(getActivity(), "Check Internet connection",
	// Toast.LENGTH_SHORT).show();
	// }
	// }
	// }

	private class SyncCompetitorProductTask extends
			AsyncTask<Void, Void, Boolean> {
		List<CompetitorProductModel> updated = new ArrayList<CompetitorProductModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("CompetitorProduct");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CompetitorProductTable table = JardineApp.DB.getCompetitorProduct();
			// CompetitorTable comptTable = JardineApp.DB.getCompetitor();

			SyncRequests request = new SyncRequests();
			updated = request.CompetitorProduct(LAST_SYNC).getUpdated();
			if (updated != null) {
				for (CompetitorProductModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {

						// long competitor = comptTable.getIdByNo(model
						// .getCompetitor());

						// if (competitor > 0)
						// TODO change competitor to string
						table.insert(model.getRecordId(), model.getCrmNo(), "",
								model.getProdBrand(), model.getProdDesc(),
								model.getProdSize(),
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						CompetitorProductRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							// long competitor = comptTable.getIdByNo(model
							// .getCompetitor());

							// if (competitor > 0)
							// TODO change competitor to string
							table.update(id, model.getRecordId(),
									model.getCrmNo(), "", model.getProdBrand(),
									model.getProdDesc(), model.getProdSize(),
									Integer.parseInt(model.getIsActive()),
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						}
					}
				}
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncSMRTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncSMRTask extends AsyncTask<Void, Void, Boolean> {
		List<SMRModel> updated = new ArrayList<SMRModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("SMR");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			SMRTable table = JardineApp.DB.getSMR();
			PAreaTable areaTable = JardineApp.DB.getArea();

			SyncRequests request = new SyncRequests();
			SmrResult result = request.SMR(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (SMRModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long area = areaTable.getIdByName(model.getArea());

						// if (area > 0)
						// TODO add business unit
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getFirstname(), model.getLastname(),
								area, Integer.parseInt(model.getIsActive()), 0,
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						SMRRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long area = areaTable.getIdByName(model.getArea());

							// if (area > 0)
							// TODO add business unit
							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getFirstname(),
									model.getLastname(), area,
									Integer.parseInt(model.getIsActive()), 0,
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						}
					}
				}
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncCustomerTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	// private class SyncSMRtimeCardTask extends AsyncTask<Void, Void, Boolean>
	// {
	// List<SMRtimeCardModel> updated = new ArrayList<SMRtimeCardModel>();
	// List<String> deleted = new ArrayList<String>();
	//
	// @Override
	// protected void onPreExecute() {
	// // dialog = new ProgressDialog(getActivity());
	// dialog.setTitle("Syncing");
	// dialog.setMessage("SMRTimecard");
	// dialog.setCancelable(false);
	// dialog.setCanceledOnTouchOutside(false);
	// dialog.show();
	// super.onPreExecute();
	// }
	//
	// @Override
	// protected Boolean doInBackground(Void... arg0) {
	//
	// SMRtimeCardTable table = JardineApp.DB.getSMRTimeCard();
	// PSMRentryTypeTable entryTable = JardineApp.DB.getSMRentryType();
	//
	// SyncRequests request = new SyncRequests();
	// SmrEntryResult result = request.SMRTimecard(LAST_SYNC);
	// updated = result.getUpdated();
	// deleted = result.getDeleted();
	// if (updated != null) {
	// for (SMRtimeCardModel model : updated) {
	// if (!table.isExisting(model.getRecordId())) {
	// long entryType = entryTable.getIdByName(model
	// .getEntry());
	//
	// // if (entryType > 0)
	// table.insert(model.getRecordId(), model.getCrmNo(),
	// model.getDate(), model.getTime(), entryType,
	// model.getCreatedTime(),
	// model.getModifiedTime(), USER_ID);
	// } else {
	// long id = table.getIdByNo(model.getRecordId());
	//
	// SMRtimeCardRecord record = table.getById(id);
	// if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
	// record.getModifiedTime()) > 0) {
	//
	// long entryType = entryTable.getIdByName(model
	// .getEntry());
	//
	// // if (entryType > 0)
	// table.update(id, model.getRecordId(),
	// model.getCrmNo(), model.getDate(),
	// model.getTime(), entryType,
	// model.getCreatedTime(),
	// model.getModifiedTime(), USER_ID);
	// Log.i(TAG, "update: " + id);
	// }
	// }
	// }
	// }
	//
	// if (deleted.size() > 0) {
	// int num = table.deleteByCrmNo(deleted
	// .toArray(new String[deleted.size()]));
	// Log.d(TAG, num + " records deleted");
	// }
	//
	// return true;
	// }
	//
	// @Override
	// protected void onPostExecute(Boolean result) {
	//
	// if (result) {
	// new SyncCustomerTask().execute();
	// } else {
	// dialog.dismiss();
	// Toast.makeText(getActivity(), "Check Internet connection",
	// Toast.LENGTH_SHORT).show();
	// }
	// }
	// }

	private class SyncCustomerTask extends AsyncTask<Void, Void, Boolean> {
		List<CustomerModel> updated = new ArrayList<CustomerModel>();
		List<String> deleted = new ArrayList<String>();
		List<CustomerRecord> sendUpdate = new ArrayList<CustomerRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Customer");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CustomerTable table = JardineApp.DB.getCustomer();
			PAreaTable areaTable = JardineApp.DB.getArea();
			PProvinceTable provinceTable = JardineApp.DB.getProvince();
			PCityTownTable cityTable = JardineApp.DB.getCityTown();
			PCustTypeTable customerTypeTable = JardineApp.DB.getCustomerType();
			PCustSizeTable customerSizeTable = JardineApp.DB.getCustomerSize();
			BusinessUnitTable businessUnitTable = JardineApp.DB
					.getBusinessUnit();

			SyncRequests request = new SyncRequests();
			CustResult result = request.Customer(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (CustomerModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long area = areaTable.getIdByName(model.getArea());
						long customerType = 0, customerSize = 0;
						if (customerTypeTable.getByName(model.getType()) != null)
							customerType = customerTypeTable.getByName(
									model.getType()).getId();
						if (customerSizeTable
								.getByName(model.getCustomerSize()) != null)
							customerSize = customerSizeTable.getByName(
									model.getCustomerSize()).getId();
						long businessUnit = businessUnitTable.getIdByNo(model
								.getBusinessunit());
						long province = provinceTable.getIdByName(model
								.getProvince());
						long cityTown = cityTable.getIdByName(model.getCity());

						// if ((area > 0) && (customerType > 0)
						// && (businessUnit > 0) && (province > 0)
						// && (cityTown > 0))
						// TODO add customerRecordStatus
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getName(), model.getChainname(),
								model.getLandline(), model.getFax(),
								customerSize, model.getStreetadd(), 0,
								customerType, businessUnit, area, province,
								cityTown,
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						CustomerRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long area = areaTable.getIdByName(model.getArea());
							long customerType = 0, customerSize = 0;
							if (customerTypeTable.getByName(model.getType()) != null)
								customerType = customerTypeTable.getByName(
										model.getType()).getId();
							if (customerSizeTable.getByName(model
									.getCustomerSize()) != null)
								customerSize = customerSizeTable.getByName(
										model.getCustomerSize()).getId();
							long businessUnit = businessUnitTable
									.getIdByNo(model.getBusinessunit());
							long province = provinceTable.getIdByName(model
									.getProvince());
							long cityTown = cityTable.getIdByName(model
									.getCity());

							// if ((area > 0) && (customerType > 0)
							// && (businessUnit > 0) && (province > 0)
							// && (cityTown > 0))
							// TODO add customerRecordStatus
							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getName(),
									model.getChainname(), model.getLandline(),
									model.getFax(), customerSize,
									model.getStreetadd(), 0, customerType,
									businessUnit, area, province, cityTown,
									Integer.parseInt(model.getIsActive()),
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						} else if (MyDateUtils.isTimeAfter(
								model.getModifiedTime(),
								record.getModifiedTime()) < 0) {
							sendUpdate.add(record);
						}

					}
				}
			}

			if (sendUpdate.size() > 0) {
				UpdateRequests update = new UpdateRequests();
				update.customer(sendUpdate);
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncCustomerContactTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncCustomerContactTask extends
			AsyncTask<Void, Void, Boolean> {
		List<CustomerContactModel> updated = new ArrayList<CustomerContactModel>();
		List<String> deleted = new ArrayList<String>();
		List<CustomerContactRecord> sendUpdate = new ArrayList<CustomerContactRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("CustomerContact");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CustomerContactTable table = JardineApp.DB.getCustomerContact();
			PCustConPositionTable custPositionTable = JardineApp.DB
					.getCustomerContactPosition();
			CustomerTable customerTable = JardineApp.DB.getCustomer();

			SyncRequests request = new SyncRequests();
			CustConResult result = request.CustomerContact(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (CustomerContactModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long position = custPositionTable.getIdByName(model
								.getPosition());
						long customer = 0;
						if (customerTable.getByWebId(model.getCustomer()) != null)
							customer = customerTable.getByWebId(
									model.getCustomer()).getId();

						// if ((position > 0) && (customer > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getFirstname(), model.getLastname(),
								position, model.getMobileno(),
								model.getBirthday(), model.getEmail(),
								customer,
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						CustomerContactRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long position = custPositionTable.getIdByName(model
									.getPosition());
							long customer = 0;
							if (customerTable.getByWebId(model.getCustomer()) != null)
								customer = customerTable.getByWebId(
										model.getCustomer()).getId();

							// if ((position > 0) && (customer > 0))
							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getFirstname(),
									model.getLastname(), position,
									model.getMobileno(), model.getBirthday(),
									model.getEmail(), customer,
									Integer.parseInt(model.getIsActive()),
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						} else if (MyDateUtils.isTimeAfter(
								model.getModifiedTime(),
								record.getModifiedTime()) < 0) {
							sendUpdate.add(record);
						}

					}
				}
			}

			if (sendUpdate.size() > 0) {
				UpdateRequests update = new UpdateRequests();
				update.customerContact(sendUpdate);
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncActivityTypeTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncActivityTypeTask extends AsyncTask<Void, Void, Boolean> {
		List<ActivityTypeModel> updated = new ArrayList<ActivityTypeModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("ActivityType");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			ActivityTypeTable table = JardineApp.DB.getActivityType();
			// PActtypeTypeTable acttypeTypeTable = JardineApp.DB
			// .getActivitytypeType();
			PActtypeCategoryTable actCategoryTable = JardineApp.DB
					.getActivitytypeCategory();

			SyncRequests request = new SyncRequests();
			ActTypeResult result = request.ActivityType(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (ActivityTypeModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						// long type = acttypeTypeTable.getIdByName(model
						// .getActivitytype());
						long category = actCategoryTable.getIdByName(model
								.getActivitytypeCategory());

						// if ((type > 0) && (category > 0))
						// TODO Add
						// Toast.makeText(CONTEXT, model.getActivitytype(),
						// Toast.LENGTH_SHORT).show();
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getActivitytype(), category,
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						ActivityTypeRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							// Toast.makeText(CONTEXT, model.getActivitytype(),
							// Toast.LENGTH_SHORT).show();

							// long type = acttypeTypeTable.getIdByName(model
							// .getActivitytype());
							long category = actCategoryTable.getIdByName(model
									.getActivitytypeCategory());

							// if ((type > 0) && (category > 0))
							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getActivitytype(),
									category,
									Integer.parseInt(model.getIsActive()),
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						}

					}
				}
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncWorkplanTask().execute();
				// new SyncWorkplanEntryTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncWorkplanTask extends AsyncTask<Void, Void, Boolean> {
		List<WorkplanModel> updated = new ArrayList<WorkplanModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Workplan");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			WorkplanTable table = JardineApp.DB.getWorkplan();

			SyncRequests request = new SyncRequests();
			WorkResult result = request.Workplan(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (WorkplanModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {

						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getFromDate(), model.getToDate(),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						WorkplanRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getFromDate(),
									model.getToDate(), model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						}

					}
				}
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncWorkplanEntryTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncWorkplanEntryTask extends AsyncTask<Void, Void, Boolean> {
		List<WorkplanEntryModel> updated = new ArrayList<WorkplanEntryModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("WorkplanEntry");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			WorkplanEntryTable table = JardineApp.DB.getWorkplanEntry();
			// CustomerTable customerTable = JardineApp.DB.getCustomer();
			PAreaTable areaTable = JardineApp.DB.getArea();
			PProvinceTable provinceTable = JardineApp.DB.getProvince();
			PCityTownTable cityTable = JardineApp.DB.getCityTown();
			ActivityTypeTable activityTypeTable = JardineApp.DB
					.getActivityType();
			WorkplanTable workplanTable = JardineApp.DB.getWorkplan();

			SyncRequests request = new SyncRequests();
			WorkEntryResult result = request.WorkplanEntry(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (WorkplanEntryModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						// long customer =
						// customerTable.getByWebId(model.getCustomer());
						// long customer = 0;
						// if
						// (customerTable.getByWebId(customerTable.getByWebId(
						// model.getCustomer())) != null)
						// customer = .getId();
						long area = areaTable.getIdByName(model.getArea());
						long province = provinceTable.getIdByName(model
								.getProvince());
						long cityTown = cityTable.getIdByName(model.getCity());
						long activityType = activityTypeTable.getIdByNo(model
								.getActivityType());
						long workplan = workplanTable.getIdByNo(model
								.getWorkplan());

						// if ((area > 0) && (province > 0) && (cityTown > 0)
						// && (activityType > 0) && (workplan > 0))
						// TODO add status, activityQuantity & businessunit
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getDate(), 1, area, province, cityTown,
								activityType, model.getOthersRemarks(),
								workplan, 0, 0, model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());
						WorkplanEntryRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							// long customer =
							// customerTable.getByWebId(model.getCustomer());
							// long customer = 0;
							// if
							// (customerTable.getByWebId(customerTable.getByWebId(
							// model.getCustomer())) != null)
							// customer = .getId();
							long area = areaTable.getIdByName(model.getArea());
							long province = provinceTable.getIdByName(model
									.getProvince());
							long cityTown = cityTable.getIdByName(model
									.getCity());
							long activityType = activityTypeTable
									.getIdByNo(model.getActivityType());
							long workplan = workplanTable.getByWebId(
									model.getWorkplan()).getId();

							// if ((area > 0) && (province > 0) && (cityTown >
							// 0)
							// && (activityType > 0) && (workplan > 0))
							// TODO add status, activityQuantity & businessunit
							table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getDate(), 1, area,
									province, cityTown, activityType,
									model.getOthersRemarks(), workplan, 0, 0,
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						}

					}
				}
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncActivityTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncActivityTask extends AsyncTask<Void, Void, Boolean> {
		List<ActivityModel> updated = new ArrayList<ActivityModel>();
		List<String> deleted = new ArrayList<String>();
		List<ActivityRecord> sendUpdate = new ArrayList<ActivityRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Activity");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			ActivityTable table = JardineApp.DB.getActivity();
			CustomerTable customerTable = JardineApp.DB.getCustomer();
			SMRTable smrTable = JardineApp.DB.getSMR();
			WorkplanEntryTable workplanEntryTable = JardineApp.DB
					.getWorkplanEntry();
			WorkplanTable workplanTable = JardineApp.DB.getWorkplan();
			ActivityTypeTable activityTypeTable = JardineApp.DB
					.getActivityType();
			BusinessUnitTable businessUnitTable = JardineApp.DB
					.getBusinessUnit();
			PAreaTable areaTable = JardineApp.DB.getArea();
			PProvinceTable provTable = JardineApp.DB.getProvince();
			PCityTownTable cityTable = JardineApp.DB.getCityTown();

			// added
			CustomerContactTable customerContactTable = JardineApp.DB
					.getCustomerContact();
			JDIproductStockCheckTable jdiproductStockCheckTable = JardineApp.DB
					.getJDIproductStockCheck();
			JDImerchandisingCheckTable jdiMerchandisingCheckTable = JardineApp.DB
					.getJDImerchandisingCheck();
			CompetitorProductStockCheckTable competitorProductStockCheckTable = JardineApp.DB
					.getCompetitorProductStockCheck();
			MarketingIntelTable marketingIntelTable = JardineApp.DB
					.getMarketingIntel();

			SyncRequests request = new SyncRequests();
			ActResult result = request.Activity(MyDateUtils.getOneYearAgo());
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (ActivityModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {

						long workplanEntry = workplanEntryTable.getIdByNo(model
								.getWorkplanEntry());
						long customer = customerTable.getIdByNo(model
								.getCustomer());
						// long businessUnit = businessUnitTable.getIdByNo(model
						// .getBusinessunit());
						long area = areaTable.getIdByName(model.getArea());
						long province = provTable.getIdByName(model
								.getProvince());
						long city = cityTable.getIdByName(model.getCity());
						// long smr = smrTable.getIdByNo(model.getSmr());
						long workplan = workplanTable.getIdByNo(model
								.getWorkplan());
						long activityType = activityTypeTable.getIdByNo(model
								.getActivityType());

						long contactPerson = 0;
						long jdiProductStockCheck = 0;
						long productSupplier = 0;
						long jdiMerchandising = 0;
						long jdiCompetitorProductStockCheck = 0;
						long marketingIntel = 0;

						// long source = 0;
						// if (model.getSource() == "Web")
						// source = 1;

						// if ((workplanEntry > 0) && (customer > 0) && (smr >
						// 0)
						// && (workplan > 0) && (activityType > 0))

						// TODO add lacking fields

						// table.insert(model.getRecordId(), model.getCrmNo(),
						// workplan, model.getStartTime(),
						// model.getEndTime(),
						// Double.parseDouble(model.getLongitude()),
						// Double.parseDouble(model.getLatitude()),
						// model.getObjective(), model.getNotes(),
						// model.getHighlights(), model.getNextsteps(),
						// model.getFollowupcomdate(), activityType,
						// workplanEntry, customer,
						// Integer.parseInt(model.getFirstTimeVisit()),
						// Integer.parseInt(model.getPlannedvisit()),
						// model.getCreatedTime(),
						// model.getModifiedTime(), USER_ID, smr,
						// model.getIssuesIdentified(),
						// model.getFeedbackFromCu(),
						// model.getOngoingCampaigns(),
						// model.getLastDelivery(),
						// model.getPromoStubsDetails(),
						// model.getProjectName(),
						// model.getProjectCategory(),
						// model.getProjectStage(), model.getDate(),
						// model.getTime(), model.getVenue(),
						// model.getNoofattenees(), businessUnit, area,
						// province, city, source);
						table.insert("", "", "smrUserId", activityType,
								"checkIn", "checkOut",
								Double.parseDouble(model.getLongitude()),
								Double.parseDouble(model.getLatitude()),
								USER_ID, model.getCreatedTime(),
								model.getModifiedTime(), "reasonRemarks",
								"workWith", "adminWorkDetails", customer, area,
								province, city, workplanEntry,
								model.getObjective(),
								Integer.parseInt(model.getFirstTimeVisit()),
								Integer.parseInt(model.getPlannedvisit()),
								model.getNotes(), model.getHighlights(),
								model.getNextsteps(),
								model.getFollowupcomdate(), contactPerson,
								jdiProductStockCheck, productSupplier,
								jdiMerchandising,
								jdiCompetitorProductStockCheck, marketingIntel,
								"projectVisitDetails", "projectRequirements",
								"trainings", "identifyProductFocus",
								"fullBrandActivation",
								"activityPhotosAttachement");
						Log.i(TAG, "add: " + model.getRecordId());
					} else {
						long id = table.getIdByNo(model.getRecordId());
						ActivityRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long workplanEntry = workplanEntryTable
									.getIdByNo(model.getWorkplanEntry());
							// long businessUnit = businessUnitTable
							// .getIdByNo(model.getBusinessunit());
							long area = areaTable.getIdByName(model.getArea());
							long province = provTable.getIdByName(model
									.getProvince());
							long city = cityTable.getIdByName(model.getCity());
							long customer = customerTable.getIdByNo(model
									.getCustomer());
							// long smr = smrTable.getIdByNo(model.getSmr());
							// long workplan = workplanTable.getIdByNo(model
							// .getWorkplan());
							long activityType = activityTypeTable
									.getIdByNo(model.getActivityType());

							long contactPerson = 0;
							long jdiProductStockCheck = 0;
							long productSupplier = 0;
							long jdiMerchandising = 0;
							long jdiCompetitorProductStockCheck = 0;
							long marketingIntel = 0;

							// long source = 0;
							// if (model.getSource() == "Web")
							// source = 1;

							// if ((workplanEntry > 0) && (customer > 0) && (smr
							// >
							// 0)
							// && (workplan > 0) && (activityType > 0))
							// TODO add lacking fields

							// table.update(
							// id,
							// model.getRecordId(),
							// model.getCrmNo(),
							// workplan,
							// model.getStartTime(),
							// model.getEndTime(),
							// Double.parseDouble(model.getLongitude()),
							// Double.parseDouble(model.getLatitude()),
							// model.getObjective(),
							// model.getNotes(),
							// model.getHighlights(),
							// model.getNextsteps(),
							// model.getFollowupcomdate(),
							// activityType,
							// workplanEntry,
							// customer,
							// Integer.parseInt(model.getFirstTimeVisit()),
							// Integer.parseInt(model.getPlannedvisit()),
							// model.getCreatedTime(),
							// model.getModifiedTime(), USER_ID, smr,
							// model.getIssuesIdentified(),
							// model.getFeedbackFromCu(),
							// model.getOngoingCampaigns(),
							// model.getLastDelivery(),
							// model.getPromoStubsDetails(),
							// model.getProjectName(),
							// model.getProjectCategory(),
							// model.getProjectStage(), model.getDate(),
							// model.getTime(), model.getVenue(),
							// model.getNoofattenees(), businessUnit,
							// area, province, city, source);
							table.update(
									id,
									"",
									"",
									"smrUserId",
									activityType,
									"checkIn",
									"checkOut",
									Double.parseDouble(model.getLongitude()),
									Double.parseDouble(model.getLatitude()),
									USER_ID,
									model.getCreatedTime(),
									model.getModifiedTime(),
									"reasonRemarks",
									"workWith",
									"adminWorkDetails",
									customer,
									area,
									province,
									city,
									workplanEntry,
									model.getObjective(),
									Integer.parseInt(model.getFirstTimeVisit()),
									Integer.parseInt(model.getPlannedvisit()),
									model.getNotes(), model.getHighlights(),
									model.getNextsteps(),
									model.getFollowupcomdate(), contactPerson,
									jdiProductStockCheck, productSupplier,
									jdiMerchandising,
									jdiCompetitorProductStockCheck,
									marketingIntel, "projectVisitDetails",
									"projectRequirements", "trainings",
									"identifyProductFocus",
									"fullBrandActivation",
									"activityPhotosAttachement");
							Log.i(TAG, "update: " + id);
						} else if (MyDateUtils.isTimeAfter(
								model.getModifiedTime(),
								record.getModifiedTime()) < 0) {
							sendUpdate.add(record);
						}

					}
				}
			}

			if (sendUpdate.size() > 0) {
				UpdateRequests update = new UpdateRequests();
				update.activity(sendUpdate);
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncJDImerchTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncJDImerchTask extends AsyncTask<Void, Void, Boolean> {
		List<JDImerchandisingCheckModel> updated = new ArrayList<JDImerchandisingCheckModel>();
		List<String> deleted = new ArrayList<String>();
		List<JDImerchandisingCheckRecord> sendUpdate = new ArrayList<JDImerchandisingCheckRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("JDImerchandisingCheck");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			JDImerchandisingCheckTable table = JardineApp.DB
					.getJDImerchandisingCheck();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			ProductTable productTable = JardineApp.DB.getProduct();

			SyncRequests request = new SyncRequests();
			JdiMerchResult result = request.JDImerchandising(MyDateUtils
					.getOneYearAgo());
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (JDImerchandisingCheckModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long activity = 0;
						if (activityTable.getByWebId(model.getActivity()) != null)
							activity = activityTable.getByWebId(
									model.getActivity()).getId();
						long product = productTable.getIdByNo(model
								.getProduct());

						// if ((activity > 0) && (product > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, product, 1, model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						JDImerchandisingCheckRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = 0;
							if (activityTable.getByWebId(model.getActivity()) != null)
								activity = activityTable.getByWebId(
										model.getActivity()).getId();
							long product = productTable.getIdByNo(model
									.getProduct());

							// if ((activity > 0) && (product > 0))
							table.update(id, model.getRecordId(),
									model.getCrmNo(), activity, product, 1,
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						} else if (MyDateUtils.isTimeAfter(
								model.getModifiedTime(),
								record.getModifiedTime()) < 0) {
							sendUpdate.add(record);
						}

					}
				}
			}

			if (sendUpdate.size() > 0) {
				UpdateRequests update = new UpdateRequests();
				update.jdiMerchandising(sendUpdate);
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncJDIproductTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncJDIproductTask extends AsyncTask<Void, Void, Boolean> {
		List<JDIproductStockCheckModel> updated = new ArrayList<JDIproductStockCheckModel>();
		List<String> deleted = new ArrayList<String>();
		List<JDIproductStockCheckRecord> sendUpdate = new ArrayList<JDIproductStockCheckRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("JDIproductStockCheck");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			JDIproductStockCheckTable table = JardineApp.DB
					.getJDIproductStockCheck();
			PJDIprodStatusTable jdiProdStatusTable = JardineApp.DB
					.getJDIproductStatus();
			// SupplierTable supplierTable = JardineApp.DB.getSupplier();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			ProductTable productTable = JardineApp.DB.getProduct();
			PCustTypeTable cTypeTable = JardineApp.DB.getCustomerType();

			SyncRequests request = new SyncRequests();
			JdiProdResult result = request.JDIproduct(MyDateUtils
					.getOneYearAgo());
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (JDIproductStockCheckModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long activity = 0;
						long stockStatus = jdiProdStatusTable.getIdByName(model
								.getStockstatus());
						// long supplier = supplierTable.getIdByNo(model
						// .getSupplier());
						long customerType = cTypeTable.getIdByName(model
								.getSupplier());
						if (activityTable.getByWebId(model.getActivity()) != null)
							activity = activityTable.getByWebId(
									model.getActivity()).getId();
						long product = productTable.getIdByNo(model
								.getProduct());

						// if ((stockStatus > 0) && (supplier > 0)
						// && (activity > 0) && (product > 0))
						// TODO add remarks, change supplier to customerType
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, product, stockStatus,
								Integer.parseInt(model.getLoadedonshelves()),
								customerType, "", model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						JDIproductStockCheckRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = 0;
							long stockStatus = jdiProdStatusTable
									.getIdByName(model.getStockstatus());
							// long supplier = supplierTable.getIdByNo(model
							// .getSupplier());
							long customerType = cTypeTable.getIdByName(model
									.getSupplier());
							if (activityTable.getByWebId(model.getActivity()) != null)
								activity = activityTable.getByWebId(
										model.getActivity()).getId();
							long product = productTable.getIdByNo(model
									.getProduct());

							// if ((stockStatus > 0) && (supplier > 0)
							// && (activity > 0) && (product > 0))
							// TODO add remarks, change supplier to customerType
							table.update(id, model.getRecordId(), model
									.getCrmNo(), activity, product,
									stockStatus, Integer.parseInt(model
											.getLoadedonshelves()),
									customerType, "", model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						} else if (MyDateUtils.isTimeAfter(
								model.getModifiedTime(),
								record.getModifiedTime()) < 0) {
							sendUpdate.add(record);
						}

					}
				}
			}

			if (sendUpdate.size() > 0) {
				UpdateRequests update = new UpdateRequests();
				update.jdiProduct(sendUpdate);
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncCompetitorProductStockTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncCompetitorProductStockTask extends
			AsyncTask<Void, Void, Boolean> {
		List<CompetitorProductStockCheckModel> updated = new ArrayList<CompetitorProductStockCheckModel>();
		List<String> deleted = new ArrayList<String>();
		List<CompetitorProductStockCheckRecord> sendUpdate = new ArrayList<CompetitorProductStockCheckRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("CompetitorProductStockCheck");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CompetitorProductStockCheckTable table = JardineApp.DB
					.getCompetitorProductStockCheck();
			PComptProdStockStatusTable compProdStatusTable = JardineApp.DB
					.getCompetitorProductStockStatus();
			CompetitorProductTable compProdTable = JardineApp.DB
					.getCompetitorProduct();
			ActivityTable activityTable = JardineApp.DB.getActivity();

			SyncRequests request = new SyncRequests();
			ComptProdStockResult result = request
					.CompetitorProductStockCheck(MyDateUtils.getOneYearAgo());
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (CompetitorProductStockCheckModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long activity = 0;
						long stockStatus = compProdStatusTable
								.getIdByName(model.getStockstatus());
						long competitorProduct = compProdTable.getIdByNo(model
								.getCompetitorProduct());
						if (activityTable.getByWebId(model.getActivity()) != null)
							activity = activityTable.getByWebId(
									model.getActivity()).getId();

						// if ((activity > 0) && (stockStatus > 0)
						// && (competitorProduct > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, competitorProduct, stockStatus,
								Integer.parseInt(model.getLoadedonshelves()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						CompetitorProductStockCheckRecord record = table
								.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = 0;
							long stockStatus = compProdStatusTable
									.getIdByName(model.getStockstatus());
							long competitorProduct = compProdTable
									.getIdByNo(model.getCompetitorProduct());
							if (activityTable.getByWebId(model.getActivity()) != null)
								activity = activityTable.getByWebId(
										model.getActivity()).getId();

							// if ((activity > 0) && (stockStatus > 0)
							// && (competitorProduct > 0))
							table.update(id, model.getRecordId(), model
									.getCrmNo(), activity, competitorProduct,
									stockStatus, Integer.parseInt(model
											.getLoadedonshelves()), model
											.getCreatedTime(), model
											.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						} else if (MyDateUtils.isTimeAfter(
								model.getModifiedTime(),
								record.getModifiedTime()) < 0) {
							sendUpdate.add(record);
						}

					}
				}
			}

			if (sendUpdate.size() > 0) {
				UpdateRequests update = new UpdateRequests();
				update.competitorProductStock(sendUpdate);
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncMarketingIntelTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncMarketingIntelTask extends AsyncTask<Void, Void, Boolean> {
		List<MarketingIntelModel> updated = new ArrayList<MarketingIntelModel>();
		List<String> deleted = new ArrayList<String>();
		List<MarketingIntelRecord> sendUpdate = new ArrayList<MarketingIntelRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("MarketingIntel");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			MarketingIntelTable table = JardineApp.DB.getMarketingIntel();
			// CompetitorTable compProdTable = JardineApp.DB.getCompetitor();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			CompetitorProductTable compProdTable = JardineApp.DB
					.getCompetitorProduct();

			SyncRequests request = new SyncRequests();
			MarketIntResult result = request.MarketingIntel(MyDateUtils
					.getOneYearAgo());
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (MarketingIntelModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long activity = 0;
						long competitor = compProdTable.getIdByNo(model
								.getCompetitor());
						if (activityTable.getByWebId(model.getActivity()) != null)
							activity = activityTable.getByWebId(
									model.getActivity()).getId();

						// if ((activity > 0) && (competitor > 0))
						// TODO removed remarks
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, competitor, model.getDetails(),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						MarketingIntelRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = 0;
							long competitor = compProdTable.getIdByNo(model
									.getCompetitor());
							if (activityTable.getByWebId(model.getActivity()) != null)
								activity = activityTable.getByWebId(
										model.getActivity()).getId();

							// if ((activity > 0) && (competitor > 0))
							// TODO removed remarks
							table.update(id, model.getRecordId(),
									model.getCrmNo(), activity, competitor,
									model.getDetails(), model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						} else if (MyDateUtils.isTimeAfter(
								model.getModifiedTime(),
								record.getModifiedTime()) < 0) {
							sendUpdate.add(record);
						}

					}
				}
			}

			if (sendUpdate.size() > 0) {
				UpdateRequests update = new UpdateRequests();
				update.marketingIntel(sendUpdate);
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncProjRequirementTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncProjRequirementTask extends
			AsyncTask<Void, Void, Boolean> {
		List<ProjectRequirementModel> updated = new ArrayList<ProjectRequirementModel>();
		List<String> deleted = new ArrayList<String>();
		List<ProjectRequirementRecord> sendUpdate = new ArrayList<ProjectRequirementRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("ProjectRequirement");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			ProjectRequirementTable table = JardineApp.DB
					.getProjectRequirement();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			PProjReqTypeTable projReqTypeTable = JardineApp.DB
					.getProjectRequirementType();

			SyncRequests request = new SyncRequests();
			ProjReqResult result = request.ProjectRequirement(MyDateUtils
					.getOneYearAgo());
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (ProjectRequirementModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						long activity = activityTable.getIdByNo(model
								.getActivity());
						long projectRequirementType = projReqTypeTable
								.getIdByName(model.getProjectReqType());

						// if (activity > 0 && projectRequirementType > 0)
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, projectRequirementType,
								model.getDateNeeded(), model.getSquaremeters(),
								model.getProductsUsed(),
								model.getOtherDetails(),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						ProjectRequirementRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = activityTable.getIdByNo(model
									.getActivity());
							long projectRequirementType = projReqTypeTable
									.getIdByName(model.getProjectReqType());

							// if (activity > 0 && projectRequirementType > 0)
							table.update(id, model.getRecordId(),
									model.getCrmNo(), activity,
									projectRequirementType,
									model.getDateNeeded(),
									model.getSquaremeters(),
									model.getProductsUsed(),
									model.getOtherDetails(),
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
							Log.i(TAG, "update: " + id);
						} else if (MyDateUtils.isTimeAfter(
								model.getModifiedTime(),
								record.getModifiedTime()) < 0) {
							sendUpdate.add(record);
						}

					}
				}
			}

			if (sendUpdate.size() > 0) {
				UpdateRequests update = new UpdateRequests();
				update.projectRequirement(sendUpdate);
			}

			if (deleted.size() > 0) {
				int num = table.deleteByCrmNo(deleted
						.toArray(new String[deleted.size()]));
				Log.d(TAG, num + " records deleted");
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncDocumentsTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncDocumentsTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Documents");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			DocumentTable table = JardineApp.DB.getDocument();
			EventProtocolTable eventTable = JardineApp.DB.getEventProtocol();
			MarketingMaterialsTable marketingMatTable = JardineApp.DB
					.getMarketingMaterials();
			List<String> eventProtocols = eventTable.getNos();
			List<String> marketingMats = marketingMatTable.getNos();

			if (eventProtocols != null) {
				for (String id : eventProtocols) {
					SyncRequests request = new SyncRequests();
					List<DocuRelModel> result = request
							.DocumentRelationships(id);
					if (result != null) {
						for (DocuRelModel model : result) {
							RetrieveRequests retrieve = new RetrieveRequests();
							DocumentModel data = retrieve.Document(model
									.getNotesid());
							if (data != null) {
								// for (DocumentModel mod : data) {
								Log.w(TAG,
										"Document: event ** "
												+ data.getNoteNo());
								if (!table.isExisting(String.valueOf(data
										.getRecordId()))) {
									long rowid = table.insert(
											String.valueOf(data.getRecordId()),
											data.getNoteNo(),
											data.getNotesTitle(),
											Modules.EventProtocol,
											model.getCrmId(),
											data.getFilename(),
											data.getFileType(),
											data.getFilePath(), 1,
											data.getCreatedTime(),
											data.getModifiedTime(), USER_ID);
									Log.w(TAG, "Document: event ** Added ** "
											+ rowid);
									Event_Files_IDs.add(new FileRecord(data
											.getFilename(), data.getFilePath(),
											data.getFileSize(),
											Modules.EventProtocol));
								}
								// }
							}
						}
					}
				}
			}

			if (marketingMats != null) {
				for (String id : marketingMats) {
					SyncRequests request = new SyncRequests();
					List<DocuRelModel> result = request
							.DocumentRelationships(id);
					if (result != null) {
						for (DocuRelModel model : result) {
							RetrieveRequests retrieve = new RetrieveRequests();
							DocumentModel data = retrieve.Document(model
									.getNotesid());
							if (data != null) {
								Log.w(TAG,
										"Document: market ** "
												+ data.getNoteNo());
								// for (DocumentModel mod : data) {
								if (!table.isExisting(String.valueOf(data
										.getRecordId()))) {
									long rowid = table.insert(
											String.valueOf(data.getRecordId()),
											data.getNoteNo(),
											data.getNotesTitle(),
											Modules.MarketingMaterials,
											model.getCrmId(),
											data.getFilename(),
											data.getFileType(),
											data.getFilePath(), 1,
											data.getCreatedTime(),
											data.getModifiedTime(), USER_ID);
									Log.w(TAG, "Document: market ** Added ** "
											+ rowid);
									Marketing_Files_IDs.add(new FileRecord(data
											.getFilename(), data.getFilePath(),
											data.getFileSize(),
											Modules.MarketingMaterials));
								}
								// }
							}
						}
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				if (JardineApp.DB.getCalendar() != null) {
					if (JardineApp.DB.getCalendar().getAllRecords().size() == 0)
						new SyncCalendarTask().execute();
				} else {
					new SyncFilesTask().execute();
				}
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncCalendarTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Notifications");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CalendarTable table = JardineApp.DB.getCalendar();
			// ActivityTable actTable = JardineApp.DB.getActivity();

			SyncRequests request = new SyncRequests();
			CalendarResult result = request.Calendar(LAST_SYNC);
			List<CalendarModel> models = result.getUpdated();
			if (result != null) {
				for (CalendarModel model : models) {
					Log.w(TAG, "Calendar: actid ** " + model.getActivityId());
					long rowid = table.insert(model.getActivityType(),
							model.getDateStart(), model.getDueDate(),
							model.getDescription(), model.getSubject(),
							model.getTimeStart(), model.getTimeEnd(), 0,
							model.getCreatedTime(), model.getModifiedTime(),
							USER_ID);
					Log.w(TAG, "Calendar ** Added ** " + rowid);
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncFilesTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncFilesTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Files");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			if (Event_Files_IDs != null) {
				for (FileRecord record : Event_Files_IDs) {
					RetrieveRequests request = new RetrieveRequests();
					request.DownloadFile(
							Integer.parseInt(record.getFileSize()),
							record.getFilePath(), record.getModuleName(),
							record.getFileName());
				}
			}

			if (Marketing_Files_IDs != null) {
				for (FileRecord record : Marketing_Files_IDs) {
					RetrieveRequests request = new RetrieveRequests();
					request.DownloadFile(
							Integer.parseInt(record.getFileSize()),
							record.getFilePath(), record.getModuleName(),
							record.getFileName());
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			UserTable userTable = JardineApp.DB.getUser();
			userTable
					.updateLastsync(USER_ID, MyDateUtils.getCurrentTimeStamp());
			dialog.dismiss();
			if (result) {
				// new CreateCompetitorProductTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class LoginTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			isCancelled = false;

			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Connecting...");
			dialog.setMessage("Please wait...");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Log.i(TAG, "cancel clicked");
							cancel(true);
						}
					});
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onCancelled() {
			isCancelled = true;
			Log.e(TAG, "### onCancelled ###");
			dialog.dismiss();
			super.onCancelled();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			boolean successful = false;
			try {
				if (isNetworkAvailable()) {
					LogRequests log = new LogRequests();
					LoginModel model = log.login(
							StoreAccount.restore(getActivity()).getString(
									Account.USERNAME),
							StoreAccount.restore(getActivity()).getString(
									Account.PASSWORD));
					if (model != null) {
						Log.i(JardineApp.TAG,
								"session: " + model.getSessionName());

						JardineApp.SESSION_NAME = model.getSessionName();
						StoreAccount.saveSession(getActivity(),
								model.getSessionName());

						successful = true;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			return successful;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				// new PicklistDependencyTask().execute();
				new CreateCompetitorProductTask().execute();
			} else {
				dialog.dismiss();
				buildAlertMessage();
			}
		}
	}

	private void buildAlertMessage() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				getActivity());
		builder.setMessage("Please check internet connection.")
				.setCancelable(false).setPositiveButton("Ok", null);
		final AlertDialog alert = builder.create();
		alert.show();
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

}
