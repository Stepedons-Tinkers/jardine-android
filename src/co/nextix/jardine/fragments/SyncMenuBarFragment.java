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
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.BusinessUnitTable;
import co.nextix.jardine.database.tables.CompetitorProductStockCheckTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.CompetitorTable;
import co.nextix.jardine.database.tables.CustomerContactTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.EventProtocolTable;
import co.nextix.jardine.database.tables.JDImerchandisingCheckTable;
import co.nextix.jardine.database.tables.JDIproductStockCheckTable;
import co.nextix.jardine.database.tables.MarketingIntelTable;
import co.nextix.jardine.database.tables.MarketingMaterialsTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.ProjectRequirementTable;
import co.nextix.jardine.database.tables.SMRTable;
import co.nextix.jardine.database.tables.SupplierTable;
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
import co.nextix.jardine.web.LogRequests;
import co.nextix.jardine.web.PicklistRequests;
import co.nextix.jardine.web.SyncRequests;
import co.nextix.jardine.web.models.ActivityModel;
import co.nextix.jardine.web.models.ActivityTypeModel;
import co.nextix.jardine.web.models.BusinessUnitModel;
import co.nextix.jardine.web.models.CompetitorModel;
import co.nextix.jardine.web.models.CompetitorProductModel;
import co.nextix.jardine.web.models.CompetitorProductStockCheckModel;
import co.nextix.jardine.web.models.CustomerContactModel;
import co.nextix.jardine.web.models.CustomerModel;
import co.nextix.jardine.web.models.EventProtocolModel;
import co.nextix.jardine.web.models.JDImerchandisingCheckModel;
import co.nextix.jardine.web.models.JDIproductStockCheckModel;
import co.nextix.jardine.web.models.MarketingIntelModel;
import co.nextix.jardine.web.models.MarketingMaterialsModel;
import co.nextix.jardine.web.models.PicklistDependencyModel;
import co.nextix.jardine.web.models.PicklistModel;
import co.nextix.jardine.web.models.ProductModel;
import co.nextix.jardine.web.models.ProjectRequirementModel;
import co.nextix.jardine.web.models.SMRModel;
import co.nextix.jardine.web.models.SupplierModel;
import co.nextix.jardine.web.models.WorkplanEntryModel;
import co.nextix.jardine.web.models.WorkplanModel;
import co.nextix.jardine.web.requesters.LoginModel;
import co.nextix.jardine.web.requesters.SyncRequester;

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
		List<PicklistDependencyModel> areas, provinces;
		long aId = 0, pId = 0;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Please wait...");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
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
										Log.i(TAG, "Province: " + prov);
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
					String province = a.getSourceValue().replace("&quot;", "");
					pId = pTable.getIdByName(province);
					if (pId != 0) {
						String ct = a.getTargetValues();
						if (ct != null) {
							String SAcities = ct.replace("[", "")
									.replace("]", "").replace("&quot;", "");
							Log.w(TAG, "cities: " + SAcities);
							List<String> cities = Arrays.asList(SAcities
									.split("\\s*,\\s*"));
							if (cities != null) {
								for (String c : cities) {
									String cty = c.replace("&quot;", "");
									if (!cTable.isExisting(cty)) {
										cTable.insert(cty, pId);
										Log.i(TAG, "City: " + cty);
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
				new PicklistsTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class PicklistsTask extends AsyncTask<Void, Void, Boolean> {
		List<String> picklist;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Please wait...");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			PicklistRequests request = new PicklistRequests();
			for (String module : Modules.picklists) {
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
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
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
			results = request.EventProtocols(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (EventProtocolModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						PicklistRecord type = eventTypeTable.getByName(model
								.getType());
						if (type != null) {
							long eventType = type.getId();

							// if (eventType > 0)
							table.insert(model.getCrmNo(),
									model.getDescription(),
									model.getLastUpdat(), model.getTags(),
									eventType,
									Integer.parseInt(model.getIsActive()),
									model.getCreatedTime(),
									model.getModifiedTime(), USER_ID);
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
			results = request.Product(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (ProductModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long businessUnit = busTable.getIdByNo(model
								.getBusinessUnit());

						// if (businessUnit > 0)
						table.insert(model.getCrmNo(), model.getProdNum(),
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
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
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

						table.insert(model.getCrmNo(), model.getName(),
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
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
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

						table.insert(model.getCrmNo(), model.getName(),
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
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
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

						long competitor = comptTable.getIdByNo(model
								.getCompetitor());

						// if (competitor > 0)
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
			results = request.SMR(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (SMRModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long area = areaTable.getIdByName(model.getArea());

						// if (area > 0)
						table.insert(model.getCrmNo(), model.getFirstname(),
								model.getLastname(), area,
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
				new RetrieveCustomerTask().execute();
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
			BusinessUnitTable businessUnitTable = JardineApp.DB
					.getBusinessUnit();

			SyncRequests request = new SyncRequests();
			results = request.Customer(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (CustomerModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long area = areaTable.getIdByName(model.getArea());
						long customerType = 0;
						if (customerTypeTable.getByName(model.getType()) != null)
							customerType = customerTypeTable.getByName(
									model.getType()).getId();
						long businessUnit = businessUnitTable.getIdByNo(model
								.getBusinessunit());
						long province = provinceTable.getIdByName(model
								.getProvince());
						long cityTown = cityTable.getIdByName(model.getCity());

						// if ((area > 0) && (customerType > 0)
						// && (businessUnit > 0) && (province > 0)
						// && (cityTown > 0))
						table.insert(model.getCrmNo(), model.getName(),
								model.getChainname(), model.getLandline(),
								model.getFax(), model.getCustomerSize(),
								model.getStreetadd(), customerType,
								businessUnit, area, province, cityTown,
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
				new RetrieveCustomerContactTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveCustomerContactTask extends
			AsyncTask<Void, Void, Boolean> {
		List<CustomerContactModel> results = new ArrayList<CustomerContactModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving CustomerContact");
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
			results = request.CustomerContact(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (CustomerContactModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long position = custPositionTable.getIdByName(model
								.getPosition());
						long customer = 0;
						if (customerTable.getByWebId(model.getCustomer()) != null)
							customer = customerTable.getByWebId(
									model.getCustomer()).getId();

						// if ((position > 0) && (customer > 0))
						table.insert(model.getCrmNo(), model.getFirstname(),
								model.getLastname(), position,
								model.getMobileno(), model.getBirthday(),
								model.getEmail(), customer,
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
				new RetrieveActivityTypeTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveActivityTypeTask extends
			AsyncTask<Void, Void, Boolean> {
		List<ActivityTypeModel> results = new ArrayList<ActivityTypeModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving ActivityType");
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
			results = request.ActivityType(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (ActivityTypeModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						// long type = acttypeTypeTable.getIdByName(model
						// .getActivitytype());
						long category = actCategoryTable.getIdByName(model
								.getActivitytypeCategory());

						// if ((type > 0) && (category > 0))
						table.insert(model.getCrmNo(), category,
								Integer.parseInt(model.getIsActive()), USER_ID);
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveWorkplanTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveWorkplanTask extends AsyncTask<Void, Void, Boolean> {
		List<WorkplanModel> results = new ArrayList<WorkplanModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving Workplan");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			WorkplanTable table = JardineApp.DB.getWorkplan();

			SyncRequests request = new SyncRequests();
			results = request.Workplan(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (WorkplanModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {

						table.insert(model.getCrmNo(), model.getFromDate(),
								model.getToDate(), model.getCreatedTime(),
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
				new RetrieveWorkplanEntryTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveWorkplanEntryTask extends
			AsyncTask<Void, Void, Boolean> {
		List<WorkplanEntryModel> results = new ArrayList<WorkplanEntryModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving WorkplanEntry");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			WorkplanEntryTable table = JardineApp.DB.getWorkplanEntry();
			CustomerTable customerTable = JardineApp.DB.getCustomer();
			PAreaTable areaTable = JardineApp.DB.getArea();
			PProvinceTable provinceTable = JardineApp.DB.getProvince();
			PCityTownTable cityTable = JardineApp.DB.getCityTown();
			ActivityTypeTable activityTypeTable = JardineApp.DB
					.getActivityType();
			WorkplanTable workplanTable = JardineApp.DB.getWorkplan();

			SyncRequests request = new SyncRequests();
			results = request.WorkplanEntry(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (WorkplanEntryModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
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
						long activityType = activityTypeTable.getByWebId(
								model.getActivityType()).getId();
						long workplan = workplanTable.getByWebId(
								model.getWorkplan()).getId();

						// if ((area > 0) && (province > 0) && (cityTown > 0)
						// && (activityType > 0) && (workplan > 0))
						table.insert(model.getCrmNo(), 0, model.getDate(),
								Integer.parseInt(model.getStatus()), area,
								province, cityTown, "remarks", activityType,
								workplan, model.getCreatedTime(),
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
				new RetrieveActivityTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveActivityTask extends AsyncTask<Void, Void, Boolean> {
		List<ActivityModel> results = new ArrayList<ActivityModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving Activity");
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

			SyncRequests request = new SyncRequests();
			results = request.Activity(MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (ActivityModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long workplanEntry = 0, customer = 0, workplan = 0, activityType = 0;
						if (workplanEntryTable.getByWebId(model
								.getWorkplanEntry()) != null)
							workplanEntry = workplanEntryTable.getByWebId(
									model.getWorkplanEntry()).getId();
						if (customerTable.getByWebId(model.getCustomer()) != null)
							customer = customerTable.getByWebId(
									model.getCustomer()).getId();
						long smr = smrTable.getIdByNo(model.getSmr());
						if (workplanTable.getByWebId(model.getWorkplan()) != null)
							workplan = workplanTable.getByWebId(
									model.getWorkplan()).getId();
						if (activityTypeTable.getByWebId(model
								.getActivityType()) != null)
							activityType = activityTypeTable.getByWebId(
									model.getActivityType()).getId();

						// if ((workplanEntry > 0) && (customer > 0) && (smr >
						// 0)
						// && (workplan > 0) && (activityType > 0))
						table.insert(model.getCrmNo(), workplan,
								model.getStartTime(), model.getEndTime(),
								Double.parseDouble(model.getLongitude()),
								Double.parseDouble(model.getLatitude()),
								model.getObjective(), model.getNotes(),
								model.getHighlights(), model.getNextsteps(),
								model.getFollowupcomdate(), activityType,
								workplanEntry, customer,
								Integer.parseInt(model.getFirstTimeVisit()),
								Integer.parseInt(model.getPlannedvisit()),
								model.getCreatedTime(),
								model.getModifiedTime(), USER_ID, smr,
								model.getIssuesIdentified(),
								model.getFeedbackFromCu(),
								model.getOngoingCampaigns(),
								model.getLastDelivery(),
								model.getPromoStubsDetails(),
								model.getProjectName(),
								model.getProjectCategory(),
								model.getProjectStage(), model.getDate(),
								model.getTime(), model.getVenue(),
								model.getNoofattenees());
					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				new RetrieveJDImerchTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveJDImerchTask extends AsyncTask<Void, Void, Boolean> {
		List<JDImerchandisingCheckModel> results = new ArrayList<JDImerchandisingCheckModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving JDImerchandisingCheck");
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
			results = request.JDImerchandising(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (JDImerchandisingCheckModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long activity = 0;
						if (activityTable.getByWebId(model.getActivity()) != null)
							activity = activityTable.getByWebId(
									model.getActivity()).getId();
						long product = productTable.getIdByNo(model
								.getProduct());

						// if ((activity > 0) && (product > 0))
						table.insert(model.getCrmNo(), activity, product,
								Integer.parseInt(model.getStatus()),
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
				new RetrieveJDIproductTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveJDIproductTask extends AsyncTask<Void, Void, Boolean> {
		List<JDIproductStockCheckModel> results = new ArrayList<JDIproductStockCheckModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving JDIproductStockCheck");
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
			SupplierTable supplierTable = JardineApp.DB.getSupplier();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			ProductTable productTable = JardineApp.DB.getProduct();

			SyncRequests request = new SyncRequests();
			results = request.JDIproduct(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (JDIproductStockCheckModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long activity = 0;
						long stockStatus = jdiProdStatusTable.getIdByName(model
								.getStockstatus());
						long supplier = supplierTable.getIdByNo(model
								.getSupplier());
						if (activityTable.getByWebId(model.getActivity()) != null)
							activity = activityTable.getByWebId(
									model.getActivity()).getId();
						long product = productTable.getIdByNo(model
								.getProduct());

						// if ((stockStatus > 0) && (supplier > 0)
						// && (activity > 0) && (product > 0))
						table.insert(model.getCrmNo(), activity, product,
								stockStatus, 0,
								Integer.parseInt(model.getLoadedonshelves()),
								supplier, model.getCreatedTime(),
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
				new RetrieveCompetitorProductStockTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveCompetitorProductStockTask extends
			AsyncTask<Void, Void, Boolean> {
		List<CompetitorProductStockCheckModel> results = new ArrayList<CompetitorProductStockCheckModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving CompetitorProductStockCheck");
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
			results = request.CompetitorProductStockCheck(
					MyDateUtils.getYesterday()).getUpdated();
			if (results != null) {
				for (CompetitorProductStockCheckModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
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
						table.insert(model.getCrmNo(), activity,
								competitorProduct, stockStatus,
								Integer.parseInt(model.getLoadedonshelves()),
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
				new RetrieveMarketingIntelTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveMarketingIntelTask extends
			AsyncTask<Void, Void, Boolean> {
		List<MarketingIntelModel> results = new ArrayList<MarketingIntelModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving MarketingIntel");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			MarketingIntelTable table = JardineApp.DB.getMarktingIntel();
			CompetitorTable compProdTable = JardineApp.DB.getCompetitor();
			ActivityTable activityTable = JardineApp.DB.getActivity();

			SyncRequests request = new SyncRequests();
			results = request.MarketingIntel(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (MarketingIntelModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long activity = 0;
						long competitor = compProdTable.getIdByNo(model
								.getCompetitor());
						if (activityTable.getByWebId(model.getActivity()) != null)
							activity = activityTable.getByWebId(
									model.getActivity()).getId();

						// if ((activity > 0) && (competitor > 0))
						table.insert(model.getCrmNo(), activity, competitor,
								model.getDetails(), "remarks",
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
				new RetrieveProjRequirementTask().execute();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class RetrieveProjRequirementTask extends
			AsyncTask<Void, Void, Boolean> {
		List<ProjectRequirementModel> results = new ArrayList<ProjectRequirementModel>();

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("Retrieving ProjectRequirement");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			ProjectRequirementTable table = JardineApp.DB
					.getProjectRequirement();
			PProjReqTypeTable projReqTypeTable = JardineApp.DB
					.getProjectRequirementType();

			SyncRequests request = new SyncRequests();
			results = request.ProjectRequirement(MyDateUtils.getYesterday())
					.getUpdated();
			if (results != null) {
				for (ProjectRequirementModel model : results) {
					if (!table.isExisting(model.getCrmNo())) {
						long projectRequirementType = projReqTypeTable
								.getIdByName(model.getProjectReqType());

						// if (projectRequirementType > 0)
						table.insert(model.getCrmNo(), projectRequirementType,
								model.getDateNeeded(), model.getSquaremeters(),
								model.getProductsUsed(),
								model.getOtherDetails(),
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

	private class LoginTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Connecting...");
			dialog.setMessage("Please wait...");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
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
