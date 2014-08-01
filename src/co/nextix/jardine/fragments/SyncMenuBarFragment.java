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
import co.nextix.jardine.database.records.ProductSupplierRecord;
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
import co.nextix.jardine.database.tables.EntityRelationshipTable;
import co.nextix.jardine.database.tables.EventProtocolTable;
import co.nextix.jardine.database.tables.JDImerchandisingCheckTable;
import co.nextix.jardine.database.tables.JDIproductStockCheckTable;
import co.nextix.jardine.database.tables.MarketingIntelTable;
import co.nextix.jardine.database.tables.MarketingMaterialsTable;
import co.nextix.jardine.database.tables.ProductFocusTable;
import co.nextix.jardine.database.tables.ProductSupplierTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.ProjectRequirementTable;
import co.nextix.jardine.database.tables.SMRTable;
import co.nextix.jardine.database.tables.SalesProtocolTable;
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
import co.nextix.jardine.database.tables.picklists.PCustRecordStatusTable;
import co.nextix.jardine.database.tables.picklists.PCustSizeTable;
import co.nextix.jardine.database.tables.picklists.PCustTypeTable;
import co.nextix.jardine.database.tables.picklists.PEventTypeTable;
import co.nextix.jardine.database.tables.picklists.PJDImerchCheckStatusTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;
import co.nextix.jardine.database.tables.picklists.PProjReqTypeTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.database.tables.picklists.PSalesProtocolTypeTable;
import co.nextix.jardine.database.tables.picklists.PWorkEntryStatusTable;
import co.nextix.jardine.database.tables.picklists.PactEndUserActTypeTable;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.utils.Tools;
import co.nextix.jardine.web.CreateRequests;
import co.nextix.jardine.web.CreateResult;
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
import co.nextix.jardine.web.models.EntityRelationshipModel;
import co.nextix.jardine.web.models.EventProtocolModel;
import co.nextix.jardine.web.models.JDImerchandisingCheckModel;
import co.nextix.jardine.web.models.JDIproductStockCheckModel;
import co.nextix.jardine.web.models.MarketingIntelModel;
import co.nextix.jardine.web.models.MarketingMaterialsModel;
import co.nextix.jardine.web.models.PicklistDependencyModel;
import co.nextix.jardine.web.models.ProductModel;
import co.nextix.jardine.web.models.ProductSupplierModel;
import co.nextix.jardine.web.models.ProjectRequirementModel;
import co.nextix.jardine.web.models.SMRModel;
import co.nextix.jardine.web.models.SalesProtocolModel;
import co.nextix.jardine.web.models.UserModel;
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
import co.nextix.jardine.web.requesters.sync.SproductSupplierRequester.ProductSupplierResult;
import co.nextix.jardine.web.requesters.sync.SprojreqRequester.ProjReqResult;
import co.nextix.jardine.web.requesters.sync.SsalesProtocolRequester.SalesProtocolResult;
import co.nextix.jardine.web.requesters.sync.SsmrRequester.SmrResult;
import co.nextix.jardine.web.requesters.sync.SworkplanRequester.WorkResult;
import co.nextix.jardine.web.requesters.sync.SworkplanentryRequester.WorkEntryResult;

public class SyncMenuBarFragment extends Fragment {

	private final String TAG = "Webservice";
	ProgressDialog dialog;
	long USER_ID = JardineApp.DB.getUser().getCurrentUserId();
	String LAST_SYNC = JardineApp.DB.getUser().getLastSync();
	List<FileRecord> Event_Files_IDs = new ArrayList<FileRecord>();
	List<FileRecord> Marketing_Files_IDs = new ArrayList<FileRecord>();
	List<FileRecord> Sales_Files_IDs = new ArrayList<FileRecord>();
	List<FileRecord> Activities_Files_IDs = new ArrayList<FileRecord>();
	protected PowerManager.WakeLock mWakeLock;
	boolean isCancelled = false;
	boolean isConnected = true;

	public SyncMenuBarFragment() {
	}

	@SuppressWarnings("deprecation")
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

	private class CreateDocumentsTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("Documents");
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

			DocumentTable table = JardineApp.DB.getDocument();
			List<DocumentRecord> records = table.getUnsyncedRecords();

			if (records != null) {
				CreateRequests request = new CreateRequests();
				for (DocumentRecord rec : records) {
					if (isCancelled()) {
						break;
					}
					CreateResult results = request.documents(rec);
					if (results != null) {
						List<WebCreateModel> webModels = results.getCreate();
						if (webModels != null) {
							for (WebCreateModel model : webModels) {
								table.update(model.getMobileId(),
										String.valueOf(model.getNo()),
										model.getModifiedTime(),
										model.getCrmId());
								// table.updateModifiedTime(model.getMobileId(),
								// model.getModifiedTime());
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
				new CreateCompetitorProductTask().execute();
				// createCompetitorProduct();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	// private void createCompetitorProduct() {
	// dialog.setTitle("Create");
	// dialog.setMessage("CompetitorProduct");
	// dialog.setCancelable(false);
	// dialog.setCanceledOnTouchOutside(false);
	// dialog.show();
	// final CompetitorProductTable table = JardineApp.DB
	// .getCompetitorProduct();
	// List<CompetitorProductRecord> records = table.getUnsyncedRecords();
	// JSONObject requestList = new JSONObject();
	// try {
	//
	// UserTable userTable = JardineApp.DB.getUser();
	//
	// for (int x = 0; x < records.size(); x++) {
	// JSONObject requestObject = new JSONObject();
	//
	// // get user id from db
	// String id = userTable.getNoById(records.get(x).getCreatedBy());
	// requestObject.put("assigned_user_id", id);
	// requestObject.put("cf_1143", records.get(x).getCompetitor());
	// requestObject.put("z_cmp_prbrnd", records.get(x)
	// .getProductBrand());
	// requestObject.put("z_cmp_prdesc", records.get(x)
	// .getProductDescription());
	// requestObject.put("z_cmp_prsize", records.get(x)
	// .getProductSize());
	// requestObject.put("z_cmp_isactive", records.get(x)
	// .getIsActive());
	//
	// requestList.put(String.valueOf(records.get(x).getId()),
	// requestObject);
	// }
	//
	// } catch (JSONException e1) {
	// e1.printStackTrace();
	// }
	// String urlString = JardineApp.WEB_URL;
	// Log.d(JardineApp.TAG, urlString);
	//
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("sessionName", JardineApp.SESSION_NAME);
	// params.put("operation", "creates");
	// params.put("elementType", Modules.CompetitorProduct);
	// params.put("elements", requestList.toString());
	//
	// JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
	// urlString, new JSONObject(params),
	// new Response.Listener<JSONObject>() {
	//
	// @Override
	// public void onResponse(JSONObject response) {
	// Gson gson = new Gson();
	// Type typeOfT = new TypeToken<DefaultRequester<CreateResult>>() {
	// }.getType();
	// DefaultRequester<CreateResult> requester = gson
	// .fromJson(NetworkUtils.getReader(response
	// .toString()), typeOfT);
	// CreateResult results = (CreateResult) requester
	// .getResult();
	//
	// if (results != null) {
	// List<WebCreateModel> models = results.getCreate();
	// if (models != null) {
	// for (WebCreateModel model : models) {
	// table.updateNo(model.getMobileId(),
	// String.valueOf(model.getCrmNo()),
	// model.getModifiedTime());
	// }
	// }
	// }
	//
	// new CreateCustomerTask().execute();
	// }
	// }, new Response.ErrorListener() {
	//
	// @Override
	// public void onErrorResponse(VolleyError error) {
	// VolleyLog.d(JardineApp.WEBTAG, error.getMessage());
	// Log.e(JardineApp.WEBTAG, "Error: " + error.toString());
	//
	// }
	// }) {
	//
	// // @Override
	// // public Map<String, String> getHeaders() throws AuthFailureError {
	// //
	// // Map<String, String> headers = super.getHeaders();
	// //
	// // if (headers == null || headers.equals(Collections.emptyMap())) {
	// // headers = new HashMap<String, String>();
	// // }
	// //
	// // headers.put(MiimoveApp.COOKIES_KEY,
	// // "miimove=" + StoreAccount.restoreSession(CONTEXT));
	// //
	// // return headers;
	// // }
	// //
	// @Override
	// public String getBodyContentType() {
	// return "application/json; charset=utf-8";
	// }
	//
	// };
	//
	// JardineApp.addToRequestQueue(jsonObjReq, JardineApp.WEBTAG);
	// }

	private class CreateCompetitorProductTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("CompetitorProduct");
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

			CompetitorProductTable table = JardineApp.DB.getCompetitorProduct();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.competitorProduct(table
					.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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

			CustomerTable table = JardineApp.DB.getCustomer();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.customer(table.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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

			CustomerContactTable table = JardineApp.DB.getCustomerContact();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.customerContact(table
					.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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

			ActivityTable table = JardineApp.DB.getActivity();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.activity(table.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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

			JDImerchandisingCheckTable table = JardineApp.DB
					.getJDImerchandisingCheck();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.jdiMerchandising(table
					.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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

			JDIproductStockCheckTable table = JardineApp.DB
					.getJDIproductStockCheck();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.jdiProductStock(table
					.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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

			CompetitorProductStockCheckTable table = JardineApp.DB
					.getCompetitorProductStockCheck();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.competitorProductStock(table
					.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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

			MarketingIntelTable table = JardineApp.DB.getMarketingIntel();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.marketingIntel(table
					.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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

			ProjectRequirementTable table = JardineApp.DB
					.getProjectRequirement();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.projectRequirements(table
					.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
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
				new CreateProductSupplierTask().execute();

			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class CreateProductSupplierTask extends
			AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Create");
			dialog.setMessage("ProductSupplier");
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

			ProductSupplierTable table = JardineApp.DB.getProductSupplier();

			CreateRequests request = new CreateRequests();
			CreateResult results = request.productSupplier(table
					.getUnsyncedRecords());
			if (results != null) {
				List<WebCreateModel> models = results.getCreate();
				if (models != null) {
					for (WebCreateModel model : models) {
						table.update(model.getMobileId(),
								String.valueOf(model.getNo()),
								model.getModifiedTime(), model.getCrmId());
					}
				}
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				new PicklistDependencyTask().execute();

			} else {
				dialog.dismiss();
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
					// if (module.equals(Modules.smrtimecard_entry)) {
					// PSMRentryTypeTable table = JardineApp.DB
					// .getSMRentryType();
					// for (String p : picklist) {
					// if (!table.isExisting(p))
					// table.insert(p);
					// }
					// } else
					if (module.equals(Modules.customer_size)) {
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
					} else if (module.equals(Modules.customer_record_status)) {
						PCustRecordStatusTable table = JardineApp.DB
								.getCustomerRecordStatus();
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
					} else if (module
							.equals(Modules.competitorproductstock_status)) {
						PComptProdStockStatusTable table = JardineApp.DB
								.getCompetitorProductStockStatus();
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
					} else if (module.equals(Modules.salesprotocol_type)) {
						PSalesProtocolTypeTable table = JardineApp.DB
								.getSalesProtocolType();
						for (String p : picklist) {
							if (!table.isExisting(p))
								table.insert(p);
						}
					} else if (module.equals(Modules.end_user_activity_types)) {
						PactEndUserActTypeTable table = JardineApp.DB
								.getActEndUserActivityType();
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
				new SyncUsersTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncUsersTask extends AsyncTask<Void, Void, Boolean> {

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
			UserTable table = JardineApp.DB.getUser();

			PicklistRequests request = new PicklistRequests();
			List<UserModel> results = request.user();
			if (results != null) {
				for (UserModel model : results) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getCrmId())) {

						table.insertUser(model.getCrmId(), model.getUsername(),
								"", "", model.getLastName(), "",
								model.getFirstName(), 0, 1, "", "", "", "");
					}
					// else {
					// long id = table.getIdByNo(model.getCrmId());
					// if (id != 0) {
					//
					// table.updateUser(id, model.getCrmId(),
					// model.getUsername(), "", "",
					// model.getLastName(), "",
					// model.getFirstName(), "", "", 0);
					// }
					// }
				}
			}
			if (isCancelled()) {
				cancel(true);
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
			UserTable userTable = JardineApp.DB.getUser();

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
						int isActive = Tools.parseIntWithDefault(
								model.getIsactive(), 0);
						long user = userTable.getIdByNo(model.getUserId());

						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getName(), model.getCode(), isActive,
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());
						if (id != 0) {
							BusinessUnitRecord record = table.getById(id);
							if (MyDateUtils.isTimeAfter(
									model.getModifiedTime(),
									record.getModifiedTime()) > 0) {
								int isActive = Tools.parseIntWithDefault(
										model.getIsactive(), 0);
								long user = userTable.getIdByNo(model
										.getUserId());

								if (table.update(id, model.getRecordId(),
										model.getCrmNo(), model.getName(),
										model.getCode(), isActive,
										model.getCreatedTime(),
										model.getModifiedTime(), user))
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

			MarketingMaterialsTable table = JardineApp.DB
					.getMarketingMaterials();
			BusinessUnitTable businessTable = JardineApp.DB.getBusinessUnit();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			MarketMatResult result = request.MarketingMaterials(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();

			if (updated != null) {
				for (MarketingMaterialsModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long businessUnit = businessTable.getIdByNo(model
								.getBusinessUnit());
						int isNew = Tools.parseIntWithDefault(model.getIsNew(),
								0);
						int isActive = Tools.parseIntWithDefault(
								model.getIsActive(), 0);
						long user = userTable.getIdByNo(model.getUserId());

						// if(businessUnit > 0){
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getDescription(), model.getLastUpdat(),
								model.getTags(), businessUnit, isNew, isActive,
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						MarketingMaterialsRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long businessUnit = businessTable.getIdByNo(model
									.getBusinessUnit());
							int isNew = Tools.parseIntWithDefault(
									model.getIsNew(), 0);
							int isActive = Tools.parseIntWithDefault(
									model.getIsActive(), 0);
							long user = userTable.getIdByNo(model.getUserId());

							// if(businessUnit > 0){
							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getDescription(),
									model.getLastUpdat(), model.getTags(),
									businessUnit, isNew, isActive,
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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

			EventProtocolTable table = JardineApp.DB.getEventProtocol();
			// event type
			PEventTypeTable eventTypeTable = JardineApp.DB
					.getEventProtocolType();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			EventResult result = request.EventProtocols(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (EventProtocolModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long eventType = eventTypeTable.getIdByName(model
								.getType());
						long user = userTable.getIdByNo(model.getUserId());

						// if (eventType > 0)
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getDescription(), model.getLastUpdat(),
								model.getTags(), eventType, Tools
										.parseIntWithDefault(
												model.getIsActive(), 0), model
										.getCreatedTime(), model
										.getModifiedTime(), user);

					} else {
						long id = table.getIdByNo(model.getRecordId());

						EventProtocolRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {
							long eventType = eventTypeTable.getIdByName(model
									.getType());
							long user = userTable.getIdByNo(model.getUserId());

							// if (eventType > 0)
							if (table.update(
									id,
									model.getRecordId(),
									model.getCrmNo(),
									model.getDescription(),
									model.getLastUpdat(),
									model.getTags(),
									eventType,
									Tools.parseIntWithDefault(
											model.getIsActive(), 0),
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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

			ProductTable table = JardineApp.DB.getProduct();
			BusinessUnitTable busTable = JardineApp.DB.getBusinessUnit();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			ProdResult result = request.Product(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (ProductModel model : updated) {
					if (!table.isExisting(model.getRecordId())) {
						if (isCancelled()) {
							break;
						}
						long businessUnit = busTable.getIdByNo(model
								.getBusinessUnit());
						long user = userTable.getIdByNo(model.getUserId());

						// if (businessUnit > 0)
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getProdNum(), model.getProdBrand(), model
										.getDescription(), model.getProdSize(),
								businessUnit, Tools.parseIntWithDefault(
										model.getIsActive(), 0), model
										.getCreatedTime(), model
										.getModifiedTime(), user);

					} else {
						long id = table.getIdByNo(model.getRecordId());

						ProductRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {
							long businessUnit = busTable.getIdByNo(model
									.getBusinessUnit());
							long user = userTable.getIdByNo(model.getUserId());

							// if (businessUnit > 0)
							table.update(
									id,
									model.getRecordId(),
									model.getCrmNo(),
									model.getProdNum(),
									model.getProdBrand(),
									model.getDescription(),
									model.getProdSize(),
									businessUnit,
									Tools.parseIntWithDefault(
											model.getIsActive(), 0),
									model.getCreatedTime(),
									model.getModifiedTime(), user);
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

			CompetitorProductTable table = JardineApp.DB.getCompetitorProduct();
			// CompetitorTable comptTable = JardineApp.DB.getCompetitor();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			updated = request.CompetitorProduct(LAST_SYNC).getUpdated();
			if (updated != null) {
				for (CompetitorProductModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {

						long user = userTable.getIdByNo(model.getUserId());
						// long competitor = comptTable.getIdByNo(model
						// .getCompetitor());

						// if (competitor > 0)
						// **changed competitor to string
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getCompetitor(), model.getProdBrand(),
								model.getProdDesc(), model.getProdSize(), Tools
										.parseIntWithDefault(
												model.getIsActive(), 0), model
										.getCreatedTime(), model
										.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						CompetitorProductRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long user = userTable.getIdByNo(model.getUserId());
							// long competitor = comptTable.getIdByNo(model
							// .getCompetitor());

							// if (competitor > 0)
							// **changed competitor to string
							table.update(
									id,
									model.getRecordId(),
									model.getCrmNo(),
									model.getCompetitor(),
									model.getProdBrand(),
									model.getProdDesc(),
									model.getProdSize(),
									Tools.parseIntWithDefault(
											model.getIsActive(), 0),
									model.getCreatedTime(),
									model.getModifiedTime(), user);
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

			SMRTable table = JardineApp.DB.getSMR();
			BusinessUnitTable businessTable = JardineApp.DB.getBusinessUnit();
			PAreaTable areaTable = JardineApp.DB.getArea();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			SmrResult result = request.SMR(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (SMRModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long area = areaTable.getIdByName(model.getArea());
						long businessUnit = businessTable.getIdByNo(model
								.getBusinessUnit());
						int isActive = Tools.parseIntWithDefault(
								model.getIsActive(), 0);
						long user = userTable.getIdByNo(model.getUserId());

						// if (area > 0 && businessUnit > 0)
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getFirstname(), model.getLastname(),
								area, isActive, businessUnit,
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						SMRRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long area = areaTable.getIdByName(model.getArea());
							long businessUnit = businessTable.getIdByNo(model
									.getBusinessUnit());
							int isActive = Tools.parseIntWithDefault(
									model.getIsActive(), 0);
							long user = userTable.getIdByNo(model.getUserId());

							// if (area > 0 && businessUnit > 0)
							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getFirstname(),
									model.getLastname(), area, isActive,
									businessUnit, model.getCreatedTime(),
									model.getModifiedTime(), user))
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

			CustomerTable table = JardineApp.DB.getCustomer();
			PAreaTable areaTable = JardineApp.DB.getArea();
			PProvinceTable provinceTable = JardineApp.DB.getProvince();
			PCityTownTable cityTable = JardineApp.DB.getCityTown();
			PCustTypeTable customerTypeTable = JardineApp.DB.getCustomerType();
			PCustSizeTable customerSizeTable = JardineApp.DB.getCustomerSize();
			PCustRecordStatusTable customerRecordStatusTable = JardineApp.DB
					.getCustomerRecordStatus();
			BusinessUnitTable businessUnitTable = JardineApp.DB
					.getBusinessUnit();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			CustResult result = request.Customer(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (CustomerModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long area = areaTable.getIdByName(model.getArea());
						long customerType = customerTypeTable.getIdByName(model
								.getType());
						long customerSize = customerSizeTable.getIdByName(model
								.getCustomerSize());
						long businessUnit = businessUnitTable.getIdByNo(model
								.getBusinessunit());
						long province = provinceTable.getIdByName(model
								.getProvince());
						long cityTown = cityTable.getIdByName(model.getCity());
						long customerRecordStatus = customerRecordStatusTable
								.getIdByName(model.getCustomerRecStat());
						int isActive = Tools.parseIntWithDefault(
								model.getIsActive(), 0);
						long user = userTable.getIdByNo(model.getUserId());

						// if ((area > 0) && (customerType > 0) && (customerSize
						// > 0)
						// && (businessUnit > 0) && (province > 0)
						// && (cityTown > 0)&& (customerRecordStatus > 0))
						table.insert(
								model.getRecordId(),
								model.getCrmNo(),
								model.getName(),
								model.getChainname(),
								model.getLandline(),
								model.getFax(),
								customerSize,
								model.getStreetadd(),
								customerRecordStatus,
								customerType,
								businessUnit,
								area,
								province,
								cityTown,
								isActive,
								Tools.parseIntWithDefault(
										model.getDaysUnchanged(), 0),
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						CustomerRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long area = areaTable.getIdByName(model.getArea());
							long customerType = customerTypeTable
									.getIdByName(model.getType());
							long customerSize = customerSizeTable
									.getIdByName(model.getCustomerSize());
							long businessUnit = businessUnitTable
									.getIdByNo(model.getBusinessunit());
							long province = provinceTable.getIdByName(model
									.getProvince());
							long cityTown = cityTable.getIdByName(model
									.getCity());
							long customerRecordStatus = customerRecordStatusTable
									.getIdByName(model.getCustomerRecStat());
							int isActive = Tools.parseIntWithDefault(
									model.getIsActive(), 0);
							long user = userTable.getIdByNo(model.getUserId());

							// if ((area > 0) && (customerType > 0) &&
							// (customerSize
							// > 0)
							// && (businessUnit > 0) && (province > 0)
							// && (cityTown > 0)&& (customerRecordStatus > 0))
							if (table.update(
									id,
									model.getRecordId(),
									model.getCrmNo(),
									model.getName(),
									model.getChainname(),
									model.getLandline(),
									model.getFax(),
									customerSize,
									model.getStreetadd(),
									customerRecordStatus,
									customerType,
									businessUnit,
									area,
									province,
									cityTown,
									isActive,
									Tools.parseIntWithDefault(
											model.getDaysUnchanged(), 0),
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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
				List<WebCreateModel> mod = update.customer(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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

			CustomerContactTable table = JardineApp.DB.getCustomerContact();
			PCustConPositionTable custPositionTable = JardineApp.DB
					.getCustomerContactPosition();
			CustomerTable customerTable = JardineApp.DB.getCustomer();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			CustConResult result = request.CustomerContact(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (CustomerContactModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long position = custPositionTable.getIdByName(model
								.getPosition());
						long customer = customerTable.getIdByNo(model
								.getCustomer());
						long user = userTable.getIdByNo(model.getUserId());

						// if ((position > 0) && (customer > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getFirstname(), model.getLastname(),
								position, model.getMobileno(), model
										.getBirthday(), model.getEmail(),
								customer, Tools.parseIntWithDefault(
										model.getIsActive(), 0), model
										.getCreatedTime(), model
										.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						CustomerContactRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long position = custPositionTable.getIdByName(model
									.getPosition());
							long customer = customerTable.getIdByNo(model
									.getCustomer());
							long user = userTable.getIdByNo(model.getUserId());

							// if ((position > 0) && (customer > 0))
							if (table.update(
									id,
									model.getRecordId(),
									model.getCrmNo(),
									model.getFirstname(),
									model.getLastname(),
									position,
									model.getMobileno(),
									model.getBirthday(),
									model.getEmail(),
									customer,
									Tools.parseIntWithDefault(
											model.getIsActive(), 0),
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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
				List<WebCreateModel> mod = update.customerContact(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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

			ActivityTypeTable table = JardineApp.DB.getActivityType();
			PActtypeCategoryTable actCategoryTable = JardineApp.DB
					.getActivitytypeCategory();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			ActTypeResult result = request.ActivityType(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (ActivityTypeModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long category = actCategoryTable.getIdByName(model
								.getActivitytypeCategory());
						int isActive = Tools.parseIntWithDefault(
								model.getIsActive(), 0);
						long user = userTable.getIdByNo(model.getUserId());

						// if (category > 0)
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getActivitytype(), category, isActive,
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						ActivityTypeRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {
							long category = actCategoryTable.getIdByName(model
									.getActivitytypeCategory());
							int isActive = Tools.parseIntWithDefault(
									model.getIsActive(), 0);
							long user = userTable.getIdByNo(model.getUserId());

							// if (category > 0)
							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getActivitytype(),
									category, isActive, model.getCreatedTime(),
									model.getModifiedTime(), user))
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

			WorkplanTable table = JardineApp.DB.getWorkplan();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			WorkResult result = request.Workplan(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (WorkplanModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {

						long user = userTable.getIdByNo(model.getUserId());

						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getFromDate(), model.getToDate(),
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long user = userTable.getIdByNo(model.getUserId());
						long id = table.getIdByNo(model.getRecordId());

						WorkplanRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getFromDate(),
									model.getToDate(), model.getCreatedTime(),
									model.getModifiedTime(), user))
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

			WorkplanEntryTable table = JardineApp.DB.getWorkplanEntry();
			// CustomerTable customerTable = JardineApp.DB.getCustomer();
			PAreaTable areaTable = JardineApp.DB.getArea();
			PProvinceTable provinceTable = JardineApp.DB.getProvince();
			PCityTownTable cityTable = JardineApp.DB.getCityTown();
			ActivityTypeTable activityTypeTable = JardineApp.DB
					.getActivityType();
			WorkplanTable workplanTable = JardineApp.DB.getWorkplan();
			BusinessUnitTable businessUnitTable = JardineApp.DB
					.getBusinessUnit();
			PWorkEntryStatusTable entryStatusTable = JardineApp.DB
					.getWorkplanEntryStatus();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			WorkEntryResult result = request.WorkplanEntry(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (WorkplanEntryModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long area = areaTable.getIdByName(model.getArea());
						long province = provinceTable.getIdByName(model
								.getProvince());
						long cityTown = cityTable.getIdByName(model.getCity());
						long activityType = activityTypeTable.getIdByNo(model
								.getActivityType());
						long workplan = workplanTable.getIdByNo(model
								.getWorkplan());
						long businessUnit = businessUnitTable.getIdByNo(model
								.getBusinessUnit());
						int quantity = Tools.parseIntWithDefault(
								model.getActivityQuantity(), 0);
						long status = entryStatusTable.getIdByName(model
								.getStatus());
						long user = userTable.getIdByNo(model.getUserId());

						// if ((area > 0) && (province > 0) && (cityTown > 0)
						// && (activityType > 0) && (workplan > 0)&&
						// (businessUnit > 0)&& (status > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								model.getDate(), status, area, province,
								cityTown, activityType,
								model.getOthersRemarks(), workplan, quantity,
								businessUnit, model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());
						WorkplanEntryRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long area = areaTable.getIdByName(model.getArea());
							long province = provinceTable.getIdByName(model
									.getProvince());
							long cityTown = cityTable.getIdByName(model
									.getCity());
							long activityType = activityTypeTable
									.getIdByNo(model.getActivityType());
							long workplan = workplanTable.getIdByNo(model
									.getWorkplan());
							long businessUnit = businessUnitTable
									.getIdByNo(model.getBusinessUnit());
							int quantity = Tools.parseIntWithDefault(
									model.getActivityQuantity(), 0);
							long status = entryStatusTable.getIdByName(model
									.getStatus());
							long user = userTable.getIdByNo(model.getUserId());

							// if ((area > 0) && (province > 0) && (cityTown >
							// 0)
							// && (activityType > 0) && (workplan > 0)&&
							// (businessUnit > 0)&& (status > 0))
							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), model.getDate(), status,
									area, province, cityTown, activityType,
									model.getOthersRemarks(), workplan,
									quantity, businessUnit,
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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

			ActivityTable table = JardineApp.DB.getActivity();
			CustomerTable customerTable = JardineApp.DB.getCustomer();
			SMRTable smrTable = JardineApp.DB.getSMR();
			WorkplanEntryTable workplanEntryTable = JardineApp.DB
					.getWorkplanEntry();
			ActivityTypeTable activityTypeTable = JardineApp.DB
					.getActivityType();
			BusinessUnitTable businessUnitTable = JardineApp.DB
					.getBusinessUnit();
			PAreaTable areaTable = JardineApp.DB.getArea();
			PProvinceTable provTable = JardineApp.DB.getProvince();
			PCityTownTable cityTable = JardineApp.DB.getCityTown();
			PActProjCategoryTable projectCategoryTable = JardineApp.DB
					.getActivityProjectCategory();
			PActProjStageTable projectStageTable = JardineApp.DB
					.getActivityProjectStage();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			ActResult result = request.Activity(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (ActivityModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {

						long workplanEntry = workplanEntryTable.getIdByNo(model
								.getWorkplanEntry());
						long customer = customerTable.getIdByNo(model
								.getCustomer());
						long area = areaTable.getIdByName(model.getArea());
						long province = provTable.getIdByName(model
								.getProvince());
						long city = cityTable.getIdByName(model.getCity());
						long activityType = activityTypeTable.getIdByNo(model
								.getActivityType());

						long businesUnit = businessUnitTable.getIdByNo(model
								.getBusinessunit());
						long smr = smrTable.getIdByNo(model.getSmr());
						long projectCategory = projectCategoryTable
								.getIdByName(model.getProjectCategory());
						long projectStage = projectStageTable.getIdByName(model
								.getProjectStage());
						long user = userTable.getIdByNo(model.getUserId());

						// if ((workplanEntry > 0) && (customer > 0) && (smr >
						// 0)
						// && (area > 0) && (province > 0) && (city > 0)
						// && (activityType > 0) && (businesUnit > 0)
						// && (projectCategory > 0) && (projectStage > 0))

						table.insert(model.getRecordId(), model.getCrmNo(),
								activityType, model.getStartTime(), model
										.getEndTime(), businesUnit, user, Tools
										.parseLongWithDefault(
												model.getLatitude(), 0), Tools
										.parseLongWithDefault(
												model.getLongitude(), 0), model
										.getCreatedTime(), model
										.getModifiedTime(), model
										.getReasonsRemarks(), smr, model
										.getAdminWorkDetails(), customer, area,
								province, city, workplanEntry, model
										.getObjective(), Tools
										.parseIntWithDefault(
												model.getFirstTimeVisit(), 0),
								Tools.parseIntWithDefault(
										model.getPlannedvisit(), 0), model
										.getNotes(), model.getHighlights(),
								model.getNextsteps(), model
										.getFollowupcomdate(), model
										.getProjectName(), projectStage,
								projectCategory, model.getVenue(), Tools
										.parseIntWithDefault(
												model.getNoOfAttendees(), 0),
								model.getEndUserActivityType());
						Log.i(TAG, "add: " + model.getRecordId());
					} else {
						long id = table.getIdByNo(model.getRecordId());
						ActivityRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long workplanEntry = workplanEntryTable
									.getIdByNo(model.getWorkplanEntry());
							long customer = customerTable.getIdByNo(model
									.getCustomer());
							long area = areaTable.getIdByName(model.getArea());
							long province = provTable.getIdByName(model
									.getProvince());
							long city = cityTable.getIdByName(model.getCity());
							long activityType = activityTypeTable
									.getIdByNo(model.getActivityType());

							long businesUnit = businessUnitTable
									.getIdByNo(model.getBusinessunit());
							long smr = smrTable.getIdByNo(model.getSmr());
							long projectCategory = projectCategoryTable
									.getIdByName(model.getProjectCategory());
							long projectStage = projectStageTable
									.getIdByName(model.getProjectStage());
							long user = userTable.getIdByNo(model.getUserId());

							// if ((workplanEntry > 0) && (customer > 0) && (smr
							// >
							// 0) && (area > 0) && (province > 0)&& (city > 0)&&
							// (activityType > 0)&& (businesUnit > 0)&&
							// (projectCategory > 0)&& (projectStage > 0))
							if (table.update(
									id,
									model.getRecordId(),
									model.getCrmNo(),
									activityType,
									model.getStartTime(),
									model.getEndTime(),
									businesUnit,
									user,
									Tools.parseLongWithDefault(
											model.getLatitude(), 0),
									Tools.parseLongWithDefault(
											model.getLongitude(), 0),
									model.getCreatedTime(),
									model.getModifiedTime(),
									model.getReasonsRemarks(),
									smr,
									model.getAdminWorkDetails(),
									customer,
									area,
									province,
									city,
									workplanEntry,
									model.getObjective(),
									Tools.parseIntWithDefault(
											model.getFirstTimeVisit(), 0),
									Tools.parseIntWithDefault(
											model.getPlannedvisit(), 0),
									model.getNotes(),
									model.getHighlights(),
									model.getNextsteps(),
									model.getFollowupcomdate(),
									model.getProjectName(),
									projectStage,
									projectCategory,
									model.getVenue(),
									Tools.parseIntWithDefault(
											model.getNoOfAttendees(), 0),
									model.getEndUserActivityType()))
								Log.i(TAG, "add: " + model.getRecordId());
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
				List<WebCreateModel> mod = update.activity(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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

			JDImerchandisingCheckTable table = JardineApp.DB
					.getJDImerchandisingCheck();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			ProductTable productTable = JardineApp.DB.getProduct();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			JdiMerchResult result = request.JDImerchandising(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (JDImerchandisingCheckModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long activity = activityTable.getIdByNo(model
								.getActivity());
						long product = productTable.getIdByNo(model
								.getProduct());
						long user = userTable.getIdByNo(model.getUserId());

						// if ((activity > 0) && (product > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, product, 1, model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						JDImerchandisingCheckRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = activityTable.getIdByNo(model
									.getActivity());
							long product = productTable.getIdByNo(model
									.getProduct());
							long user = userTable.getIdByNo(model.getUserId());

							// if ((activity > 0) && (product > 0))
							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), activity, product, 1,
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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
				List<WebCreateModel> mod = update.jdiMerchandising(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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

			JDIproductStockCheckTable table = JardineApp.DB
					.getJDIproductStockCheck();
			PJDIprodStatusTable jdiProdStatusTable = JardineApp.DB
					.getJDIproductStatus();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			ProductTable productTable = JardineApp.DB.getProduct();
			PCustTypeTable cTypeTable = JardineApp.DB.getCustomerType();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			JdiProdResult result = request.JDIproduct(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (JDIproductStockCheckModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long stockStatus = jdiProdStatusTable.getIdByName(model
								.getStockstatus());
						long customerType = cTypeTable.getIdByName(model
								.getSupplier());
						long activity = activityTable.getIdByNo(model
								.getActivity());
						long product = productTable.getIdByNo(model
								.getProduct());
						int loadedOnShelves = Tools.parseIntWithDefault(
								model.getLoadedonshelves(), 0);
						long user = userTable.getIdByNo(model.getUserId());

						// if ((stockStatus > 0) && (customerType > 0)
						// && (activity > 0) && (product > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, product, stockStatus,
								loadedOnShelves, customerType,
								model.getOtherRemarks(),
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						JDIproductStockCheckRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = 0;
							long stockStatus = jdiProdStatusTable
									.getIdByName(model.getStockstatus());
							long customerType = cTypeTable.getIdByName(model
									.getSupplier());
							if (activityTable.getByWebId(model.getActivity()) != null)
								activity = activityTable.getByWebId(
										model.getActivity()).getId();
							long product = productTable.getIdByNo(model
									.getProduct());
							int loadedOnShelves = Tools.parseIntWithDefault(
									model.getLoadedonshelves(), 0);
							long user = userTable.getIdByNo(model.getUserId());

							// if ((stockStatus > 0) && (customerType > 0)
							// && (activity > 0) && (product > 0))
							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), activity, product,
									stockStatus, loadedOnShelves, customerType,
									model.getOtherRemarks(),
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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
				List<WebCreateModel> mod = update.jdiProduct(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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

			CompetitorProductStockCheckTable table = JardineApp.DB
					.getCompetitorProductStockCheck();
			PComptProdStockStatusTable compProdStatusTable = JardineApp.DB
					.getCompetitorProductStockStatus();
			CompetitorProductTable compProdTable = JardineApp.DB
					.getCompetitorProduct();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			ComptProdStockResult result = request
					.CompetitorProductStockCheck(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (CompetitorProductStockCheckModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long activity = activityTable.getIdByNo(model
								.getActivity());
						long stockStatus = compProdStatusTable
								.getIdByName(model.getStockstatus());
						long competitorProduct = compProdTable.getIdByNo(model
								.getCompetitorProduct());
						long user = userTable.getIdByNo(model.getUserId());

						// if ((activity > 0) && (stockStatus > 0)
						// && (competitorProduct > 0))
						table.insert(
								model.getRecordId(),
								model.getCrmNo(),
								activity,
								competitorProduct,
								stockStatus,
								Tools.parseIntWithDefault(
										model.getLoadedonshelves(), 0),
								model.getOtherRemarks(),
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						CompetitorProductStockCheckRecord record = table
								.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = activityTable.getIdByNo(model
									.getActivity());
							long stockStatus = compProdStatusTable
									.getIdByName(model.getStockstatus());
							long competitorProduct = compProdTable
									.getIdByNo(model.getCompetitorProduct());
							long user = userTable.getIdByNo(model.getUserId());

							// if ((activity > 0) && (stockStatus > 0)
							// && (competitorProduct > 0))
							if (table.update(
									id,
									model.getRecordId(),
									model.getCrmNo(),
									activity,
									competitorProduct,
									stockStatus,
									Tools.parseIntWithDefault(
											model.getLoadedonshelves(), 0),
									model.getOtherRemarks(),
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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
				List<WebCreateModel> mod = update
						.competitorProductStock(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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

			MarketingIntelTable table = JardineApp.DB.getMarketingIntel();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			CompetitorProductTable compProdTable = JardineApp.DB
					.getCompetitorProduct();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			MarketIntResult result = request.MarketingIntel(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (MarketingIntelModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long competitor = compProdTable.getIdByNo(model
								.getCompetitor());
						long activity = activityTable.getIdByNo(model
								.getActivity());
						long user = userTable.getIdByNo(model.getUserId());

						// if ((activity > 0) && (competitor > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, competitor, model.getDetails(),
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						MarketingIntelRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long competitor = compProdTable.getIdByNo(model
									.getCompetitor());
							long activity = activityTable.getIdByNo(model
									.getActivity());
							long user = userTable.getIdByNo(model.getUserId());

							// if ((activity > 0) && (competitor > 0))
							table.update(id, model.getRecordId(),
									model.getCrmNo(), activity, competitor,
									model.getDetails(), model.getCreatedTime(),
									model.getModifiedTime(), user);
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
				List<WebCreateModel> mod = update.marketingIntel(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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

			ProjectRequirementTable table = JardineApp.DB
					.getProjectRequirement();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			PProjReqTypeTable projReqTypeTable = JardineApp.DB
					.getProjectRequirementType();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			ProjReqResult result = request.ProjectRequirement(LAST_SYNC);
			updated = result.getUpdated();
			deleted = result.getDeleted();
			if (updated != null) {
				for (ProjectRequirementModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long activity = activityTable.getIdByNo(model
								.getActivity());
						long projectRequirementType = projReqTypeTable
								.getIdByName(model.getProjectReqType());
						long user = userTable.getIdByNo(model.getUserId());

						// if (activity > 0 && projectRequirementType > 0)
						table.insert(model.getRecordId(), model.getCrmNo(),
								activity, projectRequirementType,
								model.getDateNeeded(), model.getSquaremeters(),
								model.getProductsUsed(),
								model.getOtherDetails(),
								model.getCreatedTime(),
								model.getModifiedTime(), user);
					} else {
						long id = table.getIdByNo(model.getRecordId());

						ProjectRequirementRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long activity = activityTable.getIdByNo(model
									.getActivity());
							long projectRequirementType = projReqTypeTable
									.getIdByName(model.getProjectReqType());
							long user = userTable.getIdByNo(model.getUserId());

							// if (activity > 0 && projectRequirementType > 0)
							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), activity,
									projectRequirementType,
									model.getDateNeeded(),
									model.getSquaremeters(),
									model.getProductsUsed(),
									model.getOtherDetails(),
									model.getCreatedTime(),
									model.getModifiedTime(), user))
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
				List<WebCreateModel> mod = update
						.projectRequirement(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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
				new SyncProductSupplierTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncProductSupplierTask extends
			AsyncTask<Void, Void, Boolean> {

		List<ProductSupplierRecord> sendUpdate = new ArrayList<ProductSupplierRecord>();
		List<ProductSupplierModel> updated = new ArrayList<ProductSupplierModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("ProductSuppliers");
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

			ProductSupplierTable table = JardineApp.DB.getProductSupplier();
			CustomerTable customerTable = JardineApp.DB.getCustomer();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			ProductTable productTable = JardineApp.DB.getProduct();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			ProductSupplierResult result = request.ProductSuppliers(LAST_SYNC);
			updated = result.getUpdated();
			if (result != null) {
				for (ProductSupplierModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long supplier = customerTable.getIdByNo(model
								.getSupplier());
						long activity = activityTable.getIdByNo(model
								.getActivity());
						long product = productTable.getIdByNo(model
								.getProductbrand());
						long user = userTable.getIdByNo(model.getUserId());

						// if((supplier > 0) && (activity > 0) && (product > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								product, supplier, model.getOthersRemarks(),
								activity, user, model.getCreatedTime(),
								model.getModifiedTime());
					} else {
						long id = table.getIdByNo(model.getRecordId());

						ProductSupplierRecord record = table.getById(id);
						if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
								record.getModifiedTime()) > 0) {

							long supplier = customerTable.getIdByNo(model
									.getSupplier());
							long activity = activityTable.getIdByNo(model
									.getActivity());
							long product = productTable.getIdByNo(model
									.getProductbrand());
							long user = userTable.getIdByNo(model.getUserId());

							// if((supplier > 0) && (activity > 0) && (product >
							// 0))
							if (table.update(id, model.getRecordId(),
									model.getCrmNo(), product, supplier,
									model.getOthersRemarks(), activity, user,
									model.getCreatedTime(),
									model.getModifiedTime()))
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
				List<WebCreateModel> mod = update.productSupplier(sendUpdate);
				if (mod != null) {
					for (WebCreateModel updated : mod) {
						table.updateModifiedTime(updated.getMobileId(),
								updated.getModifiedTime());
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
				new SyncSalesProtocolTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncSalesProtocolTask extends AsyncTask<Void, Void, Boolean> {

		// List<SalesProtocolRecord> sendUpdate = new
		// ArrayList<SalesProtocolRecord>();
		List<SalesProtocolModel> updated = new ArrayList<SalesProtocolModel>();
		List<String> deleted = new ArrayList<String>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("SalesProtocol");
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

			SalesProtocolTable table = JardineApp.DB.getSalesProtocol();
			BusinessUnitTable businessUnitTable = JardineApp.DB
					.getBusinessUnit();
			PSalesProtocolTypeTable protocolTypeTable = JardineApp.DB
					.getSalesProtocolType();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			SalesProtocolResult result = request.SalesProtocol(LAST_SYNC);
			updated = result.getUpdated();
			if (result != null) {
				for (SalesProtocolModel model : updated) {
					if (isCancelled()) {
						break;
					}
					if (!table.isExisting(model.getRecordId())) {
						long businessUnit = businessUnitTable.getIdByNo(model
								.getBusinessUnit());
						long protocolType = protocolTypeTable.getIdByName(model
								.getProtocoltype());
						long user = userTable.getIdByNo(model.getUserId());

						// if((businessUnit > 0) && (protocolType > 0))
						table.insert(model.getRecordId(), model.getCrmNo(),
								businessUnit, model.getDescription(), model
										.getLastupdate(), model.getTags(),
								protocolType, Tools.parseIntWithDefault(
										model.getIsactive(), 0), user, model
										.getCreatedTime(), model
										.getModifiedTime());
					} else {
						long id = table.getIdByNo(model.getRecordId());

						// SalesProtocolRecord record = table.getById(id);
						// if (MyDateUtils.isTimeAfter(model.getModifiedTime(),
						// record.getModifiedTime()) > 0) {

						long businessUnit = businessUnitTable.getIdByNo(model
								.getBusinessUnit());
						long protocolType = protocolTypeTable.getIdByName(model
								.getProtocoltype());
						long user = userTable.getIdByNo(model.getUserId());

						// if((businessUnit > 0) && (protocolType > 0))
						if (table.update(id, model.getRecordId(), model
								.getCrmNo(), businessUnit, model
								.getDescription(), model.getLastupdate(), model
								.getTags(), protocolType, Tools
								.parseIntWithDefault(model.getIsactive(), 0),
								user, model.getCreatedTime(), model
										.getModifiedTime()))
							Log.i(TAG, "update: " + id);
						// } else if (MyDateUtils.isTimeAfter(
						// model.getModifiedTime(),
						// record.getModifiedTime()) < 0) {
						// sendUpdate.add(record);
						// }
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
				new SyncCalendarTask().execute();
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

			CalendarTable table = JardineApp.DB.getCalendar();
			ActivityTable actTable = JardineApp.DB.getActivity();
			UserTable userTable = JardineApp.DB.getUser();

			SyncRequests request = new SyncRequests();
			CalendarResult result = request.Calendar(LAST_SYNC);
			List<CalendarModel> models = result.getUpdated();
			if (result != null) {
				for (CalendarModel model : models) {
					if (isCancelled()) {
						break;
					}
					long activity = actTable.getIdByNo(model.getActivityId());
					if (!table.isExisting(activity, model.getActivityType(),
							model.getSubject(), model.getDateStart(),
							model.getDueDate())) {
						long user = userTable.getIdByNo(model.getUserId());

						if (activity > 0) {
							table.insert(model.getActivityType(),
									model.getDateStart(), model.getDueDate(),
									model.getDescription(), model.getSubject(),
									model.getTimeStart(), model.getTimeEnd(),
									activity, model.getCreatedTime(),
									model.getModifiedTime(), user);
						}
					} else {

					}
				}
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				new SyncEntityRelationshipTask().execute();
			} else {
				dialog.dismiss();
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private class SyncEntityRelationshipTask extends
			AsyncTask<Void, Void, Boolean> {

		// List<EntityRelationshipRecord> sendUpdate = new
		// ArrayList<EntityRelationshipRecord>();

		@Override
		protected void onPreExecute() {
			// dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing");
			dialog.setMessage("EntityRelationship");
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

			EntityRelationshipTable table = JardineApp.DB
					.getEntityRelationship();
			ProductFocusTable productFocusTable = JardineApp.DB
					.getProductFocus();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			ProductTable productTable = JardineApp.DB.getProduct();
			List<String> activities = activityTable.getNos();

			if (activities != null) {
				for (String id : activities) {
					if (isCancelled()) {
						break;
					}
					SyncRequests request = new SyncRequests();
					List<EntityRelationshipModel> result = request
							.EntityRelationships(id, Modules.Product);
					if (result != null) {
						for (EntityRelationshipModel model : result) {
							if (isCancelled()) {
								break;
							}
							// RetrieveRequests retrieve = new
							// RetrieveRequests();
							// DocumentModel data = retrieve.Document(model
							// .getNotesid());
							if (model != null) {
								// for (DocumentModel mod : data) {
								Log.w(TAG, "EntityRelationship: ProductFocus");
								if (!table.isExisting(model.getCrmId(),
										model.getRelationCrmId(),
										model.getModule(),
										model.getRelationModule())) {
									long rowid = table.insert(model.getCrmId(),
											model.getModule(),
											model.getRelationCrmId(),
											model.getRelationModule());
									Log.w(TAG,
											"EntityRelationship: ProductFocus ** Added ** "
													+ rowid);
									if (rowid > 0) {
										long activity = activityTable
												.getIdByNo(id);
										long product = productTable
												.getIdByNo(model
														.getRelationCrmId());

										long pfId = productFocusTable.insert(
												product, activity);
										Log.i(TAG, "ProductFocus ** Added ** "
												+ pfId);
									}

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

			DocumentTable table = JardineApp.DB.getDocument();
			EventProtocolTable eventTable = JardineApp.DB.getEventProtocol();
			MarketingMaterialsTable marketingMatTable = JardineApp.DB
					.getMarketingMaterials();
			SalesProtocolTable salesProtocolsTable = JardineApp.DB
					.getSalesProtocol();
			ActivityTable activityTable = JardineApp.DB.getActivity();
			UserTable userTable = JardineApp.DB.getUser();

			List<String> eventProtocols = eventTable.getNos();
			List<String> marketingMats = marketingMatTable.getNos();
			List<String> salesProtocols = salesProtocolsTable.getNos();
			List<String> activities = activityTable.getNos();

			if (eventProtocols != null) {
				for (String id : eventProtocols) {
					if (isCancelled()) {
						break;
					}
					SyncRequests request = new SyncRequests();
					List<DocuRelModel> result = request
							.DocumentRelationships(id);
					if (result != null) {
						for (DocuRelModel model : result) {
							if (isCancelled()) {
								break;
							}
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
									long user = userTable.getIdByNo(data
											.getUserId());
									long eventProtoc = eventTable
											.getIdByNo(model.getCrmId());
									long rowid = table.insert(
											String.valueOf(data.getRecordId()),
											data.getNoteNo(),
											data.getNotesTitle(),
											Modules.EventProtocol,
											model.getCrmId(),
											data.getFilename(),
											data.getFileType(),
											data.getFilePath(), 1, eventProtoc,
											data.getCreatedTime(),
											data.getModifiedTime(), user);
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
					if (isCancelled()) {
						break;
					}
					SyncRequests request = new SyncRequests();
					List<DocuRelModel> result = request
							.DocumentRelationships(id);
					if (result != null) {
						for (DocuRelModel model : result) {
							if (isCancelled()) {
								break;
							}
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
									long user = userTable.getIdByNo(data
											.getUserId());
									long marketingMat = marketingMatTable
											.getIdByNo(model.getCrmId());
									long rowid = table.insert(
											String.valueOf(data.getRecordId()),
											data.getNoteNo(),
											data.getNotesTitle(),
											Modules.MarketingMaterials,
											model.getCrmId(),
											data.getFilename(),
											data.getFileType(),
											data.getFilePath(), 1,
											marketingMat,
											data.getCreatedTime(),
											data.getModifiedTime(), user);
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

			if (salesProtocols != null) {
				for (String id : salesProtocols) {
					if (isCancelled()) {
						break;
					}
					SyncRequests request = new SyncRequests();
					List<DocuRelModel> result = request
							.DocumentRelationships(id);
					if (result != null) {
						for (DocuRelModel model : result) {
							if (isCancelled()) {
								break;
							}
							RetrieveRequests retrieve = new RetrieveRequests();
							DocumentModel data = retrieve.Document(model
									.getNotesid());
							if (data != null) {
								// for (DocumentModel mod : data) {
								Log.w(TAG,
										"Document: salesprotocol ** "
												+ data.getNoteNo());
								if (!table.isExisting(String.valueOf(data
										.getRecordId()))) {
									long user = userTable.getIdByNo(data
											.getUserId());
									long salesProtoc = salesProtocolsTable
											.getIdByNo(model.getCrmId());
									long rowid = table.insert(
											String.valueOf(data.getRecordId()),
											data.getNoteNo(),
											data.getNotesTitle(),
											Modules.SalesProtocols,
											model.getCrmId(),
											data.getFilename(),
											data.getFileType(),
											data.getFilePath(), 1, salesProtoc,
											data.getCreatedTime(),
											data.getModifiedTime(), user);
									Log.w(TAG, "Document: event ** Added ** "
											+ rowid);
									Sales_Files_IDs.add(new FileRecord(data
											.getFilename(), data.getFilePath(),
											data.getFileSize(),
											Modules.SalesProtocols));
								}
								// }
							}
						}
					}
				}
			}

			if (activities != null) {
				for (String id : activities) {
					if (isCancelled()) {
						break;
					}
					SyncRequests request = new SyncRequests();
					List<DocuRelModel> result = request
							.DocumentRelationships(id);
					if (result != null) {
						for (DocuRelModel model : result) {
							if (isCancelled()) {
								break;
							}
							RetrieveRequests retrieve = new RetrieveRequests();
							DocumentModel data = retrieve.Document(model
									.getNotesid());
							if (data != null) {
								// for (DocumentModel mod : data) {
								Log.w(TAG,
										"Document: activity ** "
												+ data.getNoteNo());
								if (!table.isExisting(String.valueOf(data
										.getRecordId()))) {
									long user = userTable.getIdByNo(data
											.getUserId());
									long activity = activityTable
											.getIdByNo(model.getCrmId());
									long rowid = table.insert(
											String.valueOf(data.getRecordId()),
											data.getNoteNo(),
											data.getNotesTitle(),
											Modules.Activity, model.getCrmId(),
											data.getFilename(),
											data.getFileType(),
											data.getFilePath(), 1, activity,
											data.getCreatedTime(),
											data.getModifiedTime(), user);
									Log.w(TAG,
											"Document: activity ** Added ** "
													+ rowid);
									Activities_Files_IDs.add(new FileRecord(
											data.getFilename(), data
													.getFilePath(), data
													.getFileSize(),
											Modules.Activity));
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
				// if (JardineApp.DB.getCalendar() != null) {
				// if (JardineApp.DB.getCalendar().getAllRecords().size() == 0)
				new SyncFilesTask().execute();
				// } else {
				// new SyncFilesTask().execute();
				// }
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

			if (Event_Files_IDs != null) {
				for (FileRecord record : Event_Files_IDs) {
					if (isCancelled()) {
						break;
					}
					RetrieveRequests request = new RetrieveRequests();
					request.DownloadFile(
							Integer.parseInt(record.getFileSize()),
							record.getFilePath(), record.getModuleName(),
							record.getFileName());
				}
			}

			if (Marketing_Files_IDs != null) {
				for (FileRecord record : Marketing_Files_IDs) {
					if (isCancelled()) {
						break;
					}
					RetrieveRequests request = new RetrieveRequests();
					request.DownloadFile(
							Integer.parseInt(record.getFileSize()),
							record.getFilePath(), record.getModuleName(),
							record.getFileName());
				}
			}

			if (Sales_Files_IDs != null) {
				for (FileRecord record : Sales_Files_IDs) {
					if (isCancelled()) {
						break;
					}
					RetrieveRequests request = new RetrieveRequests();
					request.DownloadFile(
							Integer.parseInt(record.getFileSize()),
							record.getFilePath(), record.getModuleName(),
							record.getFileName());
				}
			}

			if (Activities_Files_IDs != null) {
				for (FileRecord record : Activities_Files_IDs) {
					if (isCancelled()) {
						break;
					}
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
				finishAlert();
			} else {
				Toast.makeText(getActivity(), "Check Internet connection",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void finishAlert() {

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
				getActivity());
		builderSingle.setMessage("Syncing Complete!");
		builderSingle.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}
				});
		builderSingle.show();

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
									Account.PASSWORD),
							StoreAccount.restore(getActivity()).getString(
									Account.AREA));
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
				// new CreateCompetitorProductTask().execute();
				new CreateDocumentsTask().execute();
			} else {
				dialog.dismiss();
				buildAlertMessage();
			}
		}
	}

	private void buildAlertMessage() {
		// final AlertDialog.Builder builder = new AlertDialog.Builder(
		// getActivity());
		// builder.setMessage("Please check internet connection.")
		// .setCancelable(false).setPositiveButton("Ok", null);
		// final AlertDialog alert = builder.create();
		// alert.show();

		Toast.makeText(getActivity(), "Please Check Internet Connection!",
				Toast.LENGTH_LONG).show();
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

}
