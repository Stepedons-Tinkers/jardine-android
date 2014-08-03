package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.database.records.ProjectRequirementRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.BusinessUnitTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.SMRTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.WorkplanEntryTable;
import co.nextix.jardine.database.tables.picklists.PActProjCategoryTable;
import co.nextix.jardine.database.tables.picklists.PActProjStageTable;
import co.nextix.jardine.database.tables.picklists.PAreaTable;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PComptProdStockStatusTable;
import co.nextix.jardine.database.tables.picklists.PCustConPositionTable;
import co.nextix.jardine.database.tables.picklists.PCustRecordStatusTable;
import co.nextix.jardine.database.tables.picklists.PCustSizeTable;
import co.nextix.jardine.database.tables.picklists.PCustTypeTable;
import co.nextix.jardine.database.tables.picklists.PJDImerchCheckStatusTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;
import co.nextix.jardine.database.tables.picklists.PProjReqTypeTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.web.requesters.DefaultRequester;
import co.nextix.jardine.web.requesters.WebCreateModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class UpdateRequests {
	private final String TAG = "Webservice";
	private final String operation = "updates";
	private final String charset = "UTF-8";
	private final DatabaseAdapter DB = DatabaseAdapter.getInstance();

	public List<WebCreateModel> customer(List<CustomerRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			PCustSizeTable cSizeTable = DB.getCustomerSize();
			PCustTypeTable cTypeTable = DB.getCustomerType();
			BusinessUnitTable buTable = DB.getBusinessUnit();
			PAreaTable areaTable = DB.getArea();
			PProvinceTable provinceTable = DB.getProvince();
			PCityTownTable cityTable = DB.getCityTown();
			PCustRecordStatusTable recordStatusTable = DB
					.getCustomerRecordStatus();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				String customerName = records.get(x).getCustomerName();
				String streetAdd = records.get(x).getStreetAddress();
				String chain = records.get(x).getChainName();
				String size = cSizeTable.getNameById(records.get(x)
						.getCustomerSize());
				String land = records.get(x).getLandline();
				String type = cTypeTable.getNameById(records.get(x)
						.getCustomerType());
				String businessUnit = buTable.getNoById(records.get(x)
						.getBusinessUnit());
				String area = areaTable.getNameById(records.get(x).getArea());
				String prov = provinceTable.getNameById(records.get(x)
						.getProvince());
				String city = cityTable.getNameById(records.get(x)
						.getCityTown());
				String recStat = recordStatusTable.getNameById(records.get(x)
						.getCustomerRecordStatus());
				int active = records.get(x).getIsActive();
				String fax = records.get(x).getFax();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// if (!customerName.equals(""))
				requestObject.put("z_cu_customername", customerName);
				// if (!streetAdd.equals(""))
				requestObject.put("z_cu_streetadd", streetAdd);
				// if (!chain.equals(""))
				requestObject.put("z_cu_chainname", chain);
				// if (!land.equals(""))
				requestObject.put("z_cu_landline", land);
				// if (!size.equals(""))
				requestObject.put("z_cu_customersize", size);
				// if (!type.equals(""))
				requestObject.put("z_cu_customertype", type);
				// if (!businessUnit.equals(""))
				requestObject.put("z_cu_businessunit", businessUnit);
				// if (!area.equals(""))
				requestObject.put("z_area", area);
				// if (!prov.equals(""))
				requestObject.put("z_province", prov);
				// if (!city.equals(""))
				requestObject.put("z_city ", city);
				requestObject.put("z_cu_isactive", active);
				// if (!fax.equals(""))
				requestObject.put("z_cu_fax ", fax);
				requestObject.put("z_cu_customerrecstat", recStat);
				requestObject.put("mobile_id ", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.Customers));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(), Modules.Customers);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<WebCreateModel> customerContact(
			List<CustomerContactRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			PCustConPositionTable ccPositiontable = DB
					.getCustomerContactPosition();
			CustomerTable customerTable = DB.getCustomer();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				String firstname = records.get(x).getFirstName();
				String lastname = records.get(x).getLastName();
				String position = ccPositiontable.getNameById(records.get(x)
						.getPosition());
				String mobile = records.get(x).getMobileNo();
				String bday = records.get(x).getBirthday();
				String email = records.get(x).getEmailAddress();
				// get customer from db
				String customer = customerTable.getNoById(records.get(x)
						.getCustomer());
				int active = records.get(x).getIsActive();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// if (!firstname.equals(""))
				requestObject.put("z_cuc_firstname", firstname);
				// if (!lastname.equals(""))
				requestObject.put("z_cuc_lastname", lastname);
				// if (!position.equals(""))
				requestObject.put("z_cuc_position", position);
				// if (!mobile.equals(""))
				requestObject.put("z_cuc_mobileno", mobile);
				// if (!bday.equals(""))
				requestObject.put("z_cuc_birthday", bday);
				// if (!email.equals(""))
				requestObject.put("z_cuc_email", email);
				// if (!customer.equals(""))
				requestObject.put("z_cuc_customer", customer);
				requestObject.put("z_cuc_isactive", active);
				requestObject.put("mobile_id", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.CustomerContact));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(), Modules.CustomerContact);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<WebCreateModel> activity(List<ActivityRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			BusinessUnitTable businessUnitTable = DB.getBusinessUnit();
			ActivityTypeTable activityTypeTable = DB.getActivityType();
			WorkplanEntryTable workplanEntryTable = DB.getWorkplanEntry();
			CustomerTable customerTable = DB.getCustomer();
			SMRTable smrTable = DB.getSMR();
			PAreaTable areaTable = DB.getArea();
			PCityTownTable cityTable = DB.getCityTown();
			PProvinceTable provinceTable = DB.getProvince();
			PActProjCategoryTable projCategoryTable = DB
					.getActivityProjectCategory();
			PActProjStageTable projStageTable = DB.getActivityProjectStage();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				// get type from db
				String type = activityTypeTable.getNoById(records.get(x)
						.getActivityType());
				// get business id from db
				String businessUnit = businessUnitTable.getNoById(records
						.get(x).getBusinessUnit());
				// get customer from db
				String customer = customerTable.getNoById(records.get(x)
						.getCustomer());
				String details = records.get(x).getAdminWorkDetails();
				String startTime = records.get(x).getCheckIn();
				String endTime = records.get(x).getCheckOut();
				String endUserAct = records.get(x).getEndUserActivityTypes();
				String followup = records.get(x).getFollowUpCommitmentDate();
				String highlights = records.get(x).getHighlights();
				String nextsteps = records.get(x).getNextSteps();
				String notes = records.get(x).getNotes();
				String objective = records.get(x).getObjective();
				String remarks = records.get(x).getReasonRemarks();
				// get project category from db
				String projectCategory = projCategoryTable.getNameById(records
						.get(x).getProjectCategory());
				String projectName = records.get(x).getProjectName();
				// get project stage from db
				String projectStage = projStageTable.getNameById(records.get(x)
						.getProjectStage());
				String reasons = records.get(x).getReasonRemarks();
				// get smr from db
				String smr = smrTable.getNoById(records.get(x).getSmr());
				String venue = records.get(x).getVenue();
				// get workplanEntry from db
				String workplanEntry = workplanEntryTable.getNoById(records
						.get(x).getWorkplanEntry());
				// get area from db
				String area = areaTable.getNameById(records.get(x).getArea());
				// get city from db
				String city = cityTable.getNameById(records.get(x).getCity());
				// get province from db
				String province = provinceTable.getNameById(records.get(x)
						.getProvince());

				requestObject.put("assigned_user_id", id);
				// if (!type.equals(""))
				requestObject.put("z_ac_activitytype", type);
				// if (!businessUnit.equals(""))
				requestObject.put("z_ac_businessunit", businessUnit);
				// if (!customer.equals(""))
				requestObject.put("z_ac_customer", customer);
				// if (!details.equals(""))
				requestObject.put("z_ac_details", details);
				// if (!startTime.equals(""))
				requestObject.put("z_ac_starttime", startTime);
				// if (!endTime.equals(""))
				requestObject.put("z_ac_endtime", endTime);
				if (endUserAct != null)
					// if (!endUserAct.equals("")) {
					endUserAct.replace(",", "|##|");
				requestObject.put("z_ac_enduseractype", endUserAct);
				// }
				requestObject.put("z_ac_firsttimevisit", records.get(x)
						.getFirstTimeVisit());
				requestObject.put("z_ac_plannedvisit", records.get(x)
						.getPlannedVisit());
				// if (!followup.equals(""))
				requestObject.put("z_ac_followupcomdate", followup);
				// if (!highlights.equals(""))
				requestObject.put("z_ac_highlights", highlights);
				requestObject
						.put("z_ac_latitude", records.get(x).getLatitude());
				requestObject.put("z_ac_longitude", records.get(x)
						.getLongitude());
				// if (!nextsteps.equals(""))
				requestObject.put("z_ac_nextsteps", nextsteps);
				requestObject.put("z_ac_noofattenees", records.get(x)
						.getNumberOfAttendees());
				// if (!notes.equals(""))
				requestObject.put("z_ac_notes", notes);
				// if (!objective.equals(""))
				requestObject.put("z_ac_objective", objective);
				// if (!remarks.equals(""))
				requestObject.put("z_ac_othersacttypermrk", remarks);
				// if (!projectCategory.equals(""))
				requestObject.put("z_ac_projectcategory", projectCategory);
				// if (!projectName.equals(""))
				requestObject.put("z_ac_projectname", projectName);
				// if (!projectStage.equals(""))
				requestObject.put("z_ac_projectstage", projectStage);
				// if (!reasons.equals(""))
				requestObject.put("z_ac_reasonremarks", reasons);
				// if (!smr.equals(""))
				requestObject.put("z_ac_smr", smr);
				// if (!venue.equals(""))
				requestObject.put("z_ac_venue", venue);
				// if (!workplanEntry.equals(""))
				requestObject.put("z_ac_workplanentry", workplanEntry);
				// if (!area.equals(""))
				requestObject.put("z_area", area);
				// if (!city.equals(""))
				requestObject.put("z_city", city);
				// if (!province.equals(""))
				requestObject.put("z_province", province);
				requestObject.put("mobile_id", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.Activity));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(), Modules.Activity);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<WebCreateModel> jdiMerchandising(
			List<JDImerchandisingCheckRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			ProductTable productTable = DB.getProduct();
			PJDImerchCheckStatusTable statusTable = DB.getJDImerchCheckStatus();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				// get product id from db
				String product = productTable.getNoById(records.get(x)
						.getProductBrand());
				// get status id from db
				String status = statusTable.getNameById(records.get(x)
						.getStatus());

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// if (!activity.equals(""))
				requestObject.put("z_jmc_activity", activity);
				// if (!product.equals(""))
				requestObject.put("z_jmc_product", product);
				// if (!status.equals(""))
				requestObject.put("z_jmc_status", status);
				requestObject.put("mobile_id", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.JDIMerchCheck));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(), Modules.JDIMerchCheck);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<WebCreateModel> jdiProduct(
			List<JDIproductStockCheckRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			ProductTable productTable = DB.getProduct();
			PJDIprodStatusTable jStatusTable = DB.getJDIproductStatus();
			PCustTypeTable customerTypeTable = DB.getCustomerType();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get activity no from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				// get product no from db
				String product = productTable.getNoById(records.get(x)
						.getProductBrand());
				String status = jStatusTable.getNameById(records.get(x)
						.getStockStatus());
				int loaded = records.get(x).getLoadedOnShelves();
				// get customertype id from db
				String supplier = customerTypeTable.getNameById(records.get(x)
						.getSupplier());
				String otherRemarks = records.get(x).getOtherRemarks();

				// get user no from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// if (!activity.equals(""))
				requestObject.put("z_jps_activity", activity);
				// if (!product.equals(""))
				requestObject.put("z_jps_product", product);
				// if (!status.equals(""))
				requestObject.put("z_jps_stockstatus", status);
				requestObject.put("z_jps_loadedonshelves", loaded);
				// if (!otherRemarks.equals(""))
				requestObject.put("z_jps_othertyprmrks", otherRemarks);
				// if (!supplier.equals(""))
				requestObject.put("z_jps_supplier", supplier);
				requestObject.put("mobile_id", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.JDIProductStockCheck));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(), Modules.JDIProductStockCheck);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<WebCreateModel> competitorProductStock(
			List<CompetitorProductStockCheckRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			CompetitorProductTable productTable = DB.getCompetitorProduct();
			PComptProdStockStatusTable comptProdStatusTable = DB
					.getCompetitorProductStockStatus();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get activity no from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				// get product no from db
				String product = productTable.getNoById(records.get(x)
						.getCompetitorProduct());
				String status = comptProdStatusTable.getNameById(records.get(x)
						.getStockStatus());
				int loaded = records.get(x).getLoadedOnShelves();
				String otherRemarks = records.get(x).getOtherRemarks();

				// get user no from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// if (!activity.equals(""))
				requestObject.put("z_cps_activity", activity);
				// if (!product.equals(""))
				requestObject.put("z_cps_competitorprod", product);
				// if (!status.equals(""))
				requestObject.put("z_cps_stockstatus", status);
				requestObject.put("z_cps_loadedonshelves", loaded);
				// if (!otherRemarks.equals(""))
				requestObject.put("z_cps_othertyprmrks", otherRemarks);
				requestObject.put("mobile_id", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.CompetitorProductStockCheck));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(),
					Modules.CompetitorProductStockCheck);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<WebCreateModel> marketingIntel(
			List<MarketingIntelRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			CompetitorProductTable compTable = DB.getCompetitorProduct();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				// get activity no from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				String details = records.get(x).getDetails();
				// get competitor product id from db
				String compt = compTable.getNoById(records.get(x)
						.getCompetitorProduct());

				requestObject.put("assigned_user_id", id);
				// if (!activity.equals(""))
				requestObject.put("z_min_activity", activity);
				// if (!compt.equals(""))
				requestObject.put("z_min_competitorprod", compt);
				// if (!details.equals(""))
				requestObject.put("z_min_details", details);
				requestObject.put("mobile_id", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// // List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.MarketingIntel));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(), Modules.MarketingIntel);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<WebCreateModel> projectRequirement(
			List<ProjectRequirementRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			PProjReqTypeTable projReqTypeTable = DB.getProjectRequirementType();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get activity no from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				// get product no from db
				String type = projReqTypeTable.getNameById(records.get(x)
						.getProjectRequirementType());
				String date = records.get(x).getDateNeeded();
				String squaremeters = records.get(x).getSquareMeters();
				// String products = records.get(x).getProductsUsed();
				String others = records.get(x).getOtherDetails();

				// get user no from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// if (!activity.equals(""))
				requestObject.put("z_pr_activity", activity);
				// if (!type.equals(""))
				requestObject.put("z_pr_prtype", type);
				// if (!date.equals(""))
				requestObject.put("z_pr_dateneeded", date);
				// if (!squaremeters.equals(""))
				requestObject.put("z_pr_squaremtrs", squaremeters);
				// if (!others.equals(""))
				requestObject.put("z_pr_otherdet", others);
				requestObject.put("mobile_id", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.ProjectRequirement));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(), Modules.ProjectRequirement);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<WebCreateModel> productSupplier(
			List<ProductSupplierRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			CustomerTable customerTable = DB.getCustomer();
			ProductTable productTable = DB.getProduct();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				// get customer id from db
				String customer = customerTable.getNoById(records.get(x)
						.getSupplier());
				String otherRemarks = records.get(x).getOthersRemarks();
				// get product id from db
				String productBrand = productTable.getNoById(records.get(x)
						.getProductBrand());

				// if (!activity.equals(""))
				requestObject.put("z_ps_activity", activity);
				// if (!productBrand.equals(""))
				requestObject.put("z_ps_productbrand", productBrand);
				// if (!customer.equals(""))
				requestObject.put("z_ps_supplier", customer);
				// if (!otherRemarks.equals(""))
				requestObject.put("z_ps_othersremarks", otherRemarks);
				requestObject.put("mobile_id", records.get(x).getId());
				requestObject.put("crmid", records.get(x).getNo());

				requestList.put(String.valueOf(x), requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType",
			// Modules.ProductSupplier));
			// params.add(new BasicNameValuePair("elements", requestList
			// .toString()));
			//
			// // sending
			// OutputStream os = JardineApp.httpConnection.getOutputStream();
			// writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			// writer.write(getQuery(params));
			// writer.flush();
			// writer.close();
			// os.close();

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			writeData(dos, requestList.toString(), Modules.ProductSupplier);

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

			} else {
				// getResponse();
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	/**********************************************************/

	private void getConnection(URL url, String requestMethod) {

		try {
			JardineApp.httpConnection = (HttpURLConnection) url
					.openConnection();
			JardineApp.httpConnection.setRequestMethod(requestMethod);
			JardineApp.httpConnection.setDoInput(true);
			if (requestMethod.equals("GET") || requestMethod.equals("DELETE")) {
				JardineApp.httpConnection.setDoOutput(false);
				JardineApp.httpConnection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
			} else {
				JardineApp.httpConnection.setDoOutput(true);
				JardineApp.httpConnection.setRequestProperty("Content-Type",
						"multipart/form-data;boundary="
								+ JardineApp.REQUEST_BOUNDARY);
			}

			if (JardineApp.CookieManager.getCookieStore().getCookies().size() > 0) {
				JardineApp.httpConnection.setRequestProperty("Cookie",
						TextUtils.join(",", JardineApp.CookieManager
								.getCookieStore().getCookies()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeData(DataOutputStream dos, String requestParams,
			String module) {

		// **start
		try {
			dos.writeBytes(JardineApp.REQUEST_TWOHYPHENS
					+ JardineApp.REQUEST_BOUNDARY + JardineApp.REQUEST_LINEEND);

			dos.writeBytes("Content-Disposition: form-data; name=\"sessionName\""
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Type: application/json"
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			dos.writeBytes(JardineApp.SESSION_NAME);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			// **end

			// **start
			dos.writeBytes(JardineApp.REQUEST_TWOHYPHENS
					+ JardineApp.REQUEST_BOUNDARY + JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Disposition: form-data; name=\"operation\""
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Type: application/json"
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			dos.writeBytes(operation);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			// **end

			// **start
			dos.writeBytes(JardineApp.REQUEST_TWOHYPHENS
					+ JardineApp.REQUEST_BOUNDARY + JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Disposition: form-data; name=\"elementType\""
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Type: application/json"
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			dos.writeBytes(module);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			// **end

			// **start
			dos.writeBytes(JardineApp.REQUEST_TWOHYPHENS
					+ JardineApp.REQUEST_BOUNDARY + JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Disposition: form-data; name=\"elements\""
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Type: application/json"
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			dos.writeBytes(requestParams);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			// **end

			dos.writeBytes(JardineApp.REQUEST_TWOHYPHENS
					+ JardineApp.REQUEST_BOUNDARY
					+ JardineApp.REQUEST_TWOHYPHENS
					+ JardineApp.REQUEST_LINEEND); // this
			// is
			// for
			// end
			// of
			// output

			// close streams
			dos.flush();
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JsonReader getReader() {
		InputStream is;
		StringBuffer response = new StringBuffer();
		InputStream in = null;
		JsonReader reader = null;
		try {
			is = JardineApp.httpConnection.getInputStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;

			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			String reply = response.toString();
			Log.i(TAG, reply);

			// reading
			in = new ByteArrayInputStream(reply.getBytes("UTF-8"));
			reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	private String getQuery(List<NameValuePair> params)
			throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}

		return result.toString();
	}

	// private void getResponse() {
	// Gson gson = new Gson();
	// ResponseModel errorModel = gson.fromJson(getReader(),
	// ResponseModel.class);
	// JardineApp.ErrorMessage = errorModel.getMessage();
	// }
}
