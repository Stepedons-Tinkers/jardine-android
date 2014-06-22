package co.nextix.jardine.fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.LoginActivity;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.tables.BusinessUnitTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.CompetitorTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.EventProtocolTable;
import co.nextix.jardine.database.tables.MarketingMaterialsTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.SMRTable;
import co.nextix.jardine.database.tables.SupplierTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PAreaTable;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PEventTypeTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.web.LogRequests;
import co.nextix.jardine.web.PicklistDependencyModel;
import co.nextix.jardine.web.PicklistModel;
import co.nextix.jardine.web.PicklistRequests;
import co.nextix.jardine.web.SyncRequests;
import co.nextix.jardine.web.models.BusinessUnitModel;
import co.nextix.jardine.web.models.CompetitorModel;
import co.nextix.jardine.web.models.CompetitorProductModel;
import co.nextix.jardine.web.models.CustomerModel;
import co.nextix.jardine.web.models.EventProtocolModel;
import co.nextix.jardine.web.models.MarketingMaterialsModel;
import co.nextix.jardine.web.models.ProductModel;
import co.nextix.jardine.web.models.SMRModel;
import co.nextix.jardine.web.models.SupplierModel;
import co.nextix.jardine.web.models.WorkplanModel;
import co.nextix.jardine.web.requesters.LoginModel;

public class SyncMenuBarFragment extends Fragment {

	private final String TAG = "Webservice";
	ProgressDialog dialog;
	long USER_ID = JardineApp.DB.getUser().getCurrentUser().getId();

	public SyncMenuBarFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		View rootView = inflater.inflate(R.layout.fragment_sync, container,
				false);

		new LoginTask().execute();

		return rootView;
	}

	private class PicklistDependencyTask extends AsyncTask<Void, Void, Boolean> {
		List<PicklistDependencyModel> areas, provinces, cities;
		long aId = 0;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Please wait...");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			PAreaTable aTable = JardineApp.DB.getArea();
			PProvinceTable pTable = JardineApp.DB.getProvince();
			PCityTownTable cTable = JardineApp.DB.getCityTown();

			PicklistRequests arequest = new PicklistRequests();
			areas = arequest.area();
			if (areas != null) {

				for (PicklistDependencyModel a : areas) {
					String area = a.getSourceValue().replace("&quot;", "");
					if (!aTable.isExisting(area)) {
						aId = aTable.insertArea(area);
						Log.d(TAG, "AREA: " + area);
						String pr = a.getTargetValues();
						if (pr != null) {
							String SAprovinces = pr.replace("[", "")
									.replace("]", "").replace("&quot;", "");
							Log.w(TAG, "provinces: " + SAprovinces);
							List<String> provinces = Arrays.asList(SAprovinces
									.split("\\s*,\\s*"));
							if (provinces != null) {
								for (String p : provinces) {
									String prov = p.replace("&quot;", "");
									if (!pTable.isExisting(prov)) {
										pTable.insertProvince(prov, aId);
										Log.i(TAG, "Province: " + p);
									}
								}
							}
						}
					}

				}
			}

			// List<PicklistRecord> aRecords = aTable.getRecords();
			//
			// PicklistRequests prequest = new PicklistRequests();
			// provinces = prequest.province();
			// if (provinces != null) {
			//
			// for (PicklistDependencyModel a : provinces) {
			//
			// }
			// }

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveBusinessUnitTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveBusinessUnitTask extends
			AsyncTask<Void, Void, Boolean> {
		List<BusinessUnitModel> results = new ArrayList<BusinessUnitModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving BusinessUnit");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			BusinessUnitTable table = JardineApp.DB.getBusinessUnit();

			SyncRequests request = new SyncRequests();
			results = request.BusinessUnit(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (BusinessUnitModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						table.insert(model.getCrmNo(), model.getName(),
								model.getCode(),
								Integer.parseInt(model.getIsactive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveMarketingMaterialsTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveMarketingMaterialsTask extends
			AsyncTask<Void, Void, Boolean> {
		List<MarketingMaterialsModel> results = new ArrayList<MarketingMaterialsModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving MarketingMaterials");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			MarketingMaterialsTable table = JardineApp.DB
					.getMarketingMaterials();

			SyncRequests request = new SyncRequests();
			results = request.MarketingMaterials(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (MarketingMaterialsModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						table.insert(model.getCrmNo(), model.getDescription(),
								model.getLastUpdat(), model.getTags(),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveEventProtocolTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveEventProtocolTask extends
			AsyncTask<Void, Void, Boolean> {
		List<EventProtocolModel> results = new ArrayList<EventProtocolModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving EventProtocol");
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
			results = request.EventProtocols(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (EventProtocolModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long eventType = eventTypeTable.getByWebId(
								model.getType()).getId();

						table.insert(model.getCrmNo(), model.getDescription(),
								model.getLastUpdat(), model.getTags(),
								eventType,
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveProductTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveProductTask extends AsyncTask<Void, Void, Boolean> {
		List<ProductModel> results = new ArrayList<ProductModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving Product");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			ProductTable table = JardineApp.DB.getProduct();
			BusinessUnitTable busTable = JardineApp.DB.getBusinessUnit();

			SyncRequests request = new SyncRequests();
			results = request.Product(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (ProductModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long businessUnit = busTable.getByWebId(
								model.getBusinessUnit()).getId();

						table.insertUser(model.getCrmNo(), model.getProdNum(),
								model.getProdBrand(), model.getDescription(),
								model.getProdSize(), businessUnit,
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveSupplierTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveSupplierTask extends AsyncTask<Void, Void, Boolean> {
		List<SupplierModel> results = new ArrayList<SupplierModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving Supplier");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			SupplierTable table = JardineApp.DB.getSupplier();

			SyncRequests request = new SyncRequests();
			results = request.Supplier(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (SupplierModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {

						table.insertUser(model.getCrmNo(), model.getName(),
								model.getLandline(), model.getAddress(),
								model.getCreditLine(), model.getCreditTerm(),
								model.getContactPerson(),
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveCompetitorTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveCompetitorTask extends AsyncTask<Void, Void, Boolean> {
		List<CompetitorModel> results = new ArrayList<CompetitorModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving Competitor");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CompetitorTable table = JardineApp.DB.getCompetitor();

			SyncRequests request = new SyncRequests();
			results = request.Competitor(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (CompetitorModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {

						table.insertUser(model.getCrmNo(), model.getName(),
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveCompetitorProductTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveCompetitorProductTask extends
			AsyncTask<Void, Void, Boolean> {
		List<CompetitorProductModel> results = new ArrayList<CompetitorProductModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving CompetitorProduct");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CompetitorProductTable table = JardineApp.DB.getCompetitorProduct();
			CompetitorTable comptTable = JardineApp.DB.getCompetitor();

			SyncRequests request = new SyncRequests();
			results = request.CompetitorProduct(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (CompetitorProductModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long competitor = comptTable.getByWebId(
								model.getCompetitor()).getId();

						table.insert(model.getCrmNo(), competitor,
								model.getProdBrand(), model.getProdDesc(),
								model.getProdSize(),
								Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveSMRTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveSMRTask extends AsyncTask<Void, Void, Boolean> {
		List<SMRModel> results = new ArrayList<SMRModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving SMR");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			SMRTable table = JardineApp.DB.getSMR();
			PAreaTable areaTable = JardineApp.DB.getArea();

			SyncRequests request = new SyncRequests();
			results = request.SMR(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (SMRModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long area = areaTable.getIdByName(model.getArea());

						table.insertUser(model.getCrmNo(),
								model.getFirstname(), model.getLastname(), 0,
								area, Integer.parseInt(model.getIsActive()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {

			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveCustomerTask extends AsyncTask<Void, Void, Boolean> {
		List<CustomerModel> results = new ArrayList<CustomerModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving Customer");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			CustomerTable table = JardineApp.DB.getCustomer();
			PAreaTable areaTable = JardineApp.DB.getArea();

			SyncRequests request = new SyncRequests();
			results = request.Customer(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (CustomerModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
//						long area = comptTable.getByWebId(model.getArea())
//								.getId();
//						long customerRecordStatus =
//
//						table.insertUser(model.getCrmNo(), model.getName(),
//								model.getChainname(), model.getLandline(),
//								model.getFax(), model.getCustomerSize(),
//								model.getStreetadd(), customerRecordStatus,
//								customerType, businessUnit, area, province,
//								cityTown,
//								Integer.parseInt(model.getIsActive()),
//								model.getCreatedTime(),
//								model.getModifiedTime(), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {

			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class LoginTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Connecting...");
			dialog.setMessage("Please wait...");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				LogRequests log = new LogRequests();
				LoginModel model = log.login(
						StoreAccount.restore(getActivity()).getString(
								Account.USERNAME),
						StoreAccount.restore(getActivity()).getString(
								Account.PASSWORD));
				if (model != null) {
					Log.i(JardineApp.TAG, "session: " + model.getSessionName());

					JardineApp.SESSION_NAME = model.getSessionName();
					StoreAccount.saveSession(getActivity(),
							model.getSessionName());

					return true;
				} else {
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
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

}
