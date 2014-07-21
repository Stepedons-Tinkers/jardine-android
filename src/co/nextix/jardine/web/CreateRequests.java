package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.database.records.ProjectRequirementRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.BusinessUnitTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.WorkplanEntryTable;
import co.nextix.jardine.database.tables.picklists.PAreaTable;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PComptProdStockStatusTable;
import co.nextix.jardine.database.tables.picklists.PCustConPositionTable;
import co.nextix.jardine.database.tables.picklists.PCustSizeTable;
import co.nextix.jardine.database.tables.picklists.PCustTypeTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;
import co.nextix.jardine.database.tables.picklists.PProjReqTypeTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.web.requesters.DefaultRequester;
import co.nextix.jardine.web.requesters.WebCreateModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class CreateRequests {

	private final String TAG = "Webservice";
	private final String operation = "creates";
	private final String charset = "UTF-8";
	private final DatabaseAdapter DB = DatabaseAdapter.getInstance();

	public List<WebCreateModel> competitorProduct(
			List<CompetitorProductRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			// CompetitorTable compTable = DB.getCompetitor();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// String competitor = compTable.getNoById(records.get(x)
				// .getCompetitor());
				// requestObject.put("z_cmp_comp", competitor);
				requestObject.put("z_cmp_prbrnd", records.get(x)
						.getProductBrand());
				requestObject.put("z_cmp_prdesc", records.get(x)
						.getProductDescription());
				requestObject.put("z_cmp_prsize", records.get(x)
						.getProductSize());
				requestObject.put("z_cmp_isactive", records.get(x)
						.getIsActive());

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// DataOutputStream dos = new DataOutputStream(
			// JardineApp.httpConnection.getOutputStream());
			//
			// dos.writeBytes(requestList.toString());
			//
			// dos.flush();
			// dos.close();

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.CompetitorProduct));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

//	public List<WebCreateModel> smrTimecard(List<SMRtimeCardRecord> records) {
//
//		List<WebCreateModel> model = null;
//
//		JSONObject requestList = new JSONObject();
//		try {
//
//			UserTable userTable = DB.getUser();
//			PSMRentryTypeTable smrEntry = DB.getSMRentryType();
//
//			for (int x = 0; x < records.size(); x++) {
//				JSONObject requestObject = new JSONObject();
//
//				// get user id from db
//				String id = userTable.getNoById(records.get(x).getCreatedBy());
//				requestObject.put("assigned_user_id", id);
//				requestObject.put("z_stc_date", records.get(x).getDate());
//				requestObject.put("z_stc_time", records.get(x).getTimestamp());
//				// get entry type from db
//				String entryType = smrEntry.getById(
//						records.get(x).getEntryType()).getName();
//				requestObject.put("z_stc_picklist", entryType);
//
//				requestList.put(String.valueOf(records.get(x).getId()),
//						requestObject);
//			}
//
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		}
//
//		BufferedWriter writer;
//		URL url;
//		String urlString = JardineApp.WEB_URL;
//		Log.d(TAG, urlString);
//
//		try {
//
//			url = new URL(urlString);
//			getConnection(url, "POST");
//
//			// appending
//			List<NameValuePair> params = new ArrayList<NameValuePair>();
//			params.add(new BasicNameValuePair("sessionName",
//					JardineApp.SESSION_NAME));
//			params.add(new BasicNameValuePair("operation", operation));
//			params.add(new BasicNameValuePair("elementType",
//					Modules.SMRTimeCard));
//			params.add(new BasicNameValuePair("elements", requestList
//					.toString()));
//
//			// sending
//			OutputStream os = JardineApp.httpConnection.getOutputStream();
//			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
//			writer.write(getQuery(params));
//			writer.flush();
//			writer.close();
//			os.close();
//
//			// status
//			int status = JardineApp.httpConnection.getResponseCode();
//
//			if (status == 200) {
//
//				Gson gson = new Gson();
//				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
//				}.getType();
//				DefaultRequester<List<WebCreateModel>> requester = gson
//						.fromJson(getReader(), typeOfT);
//				model = (List<WebCreateModel>) requester.getResult();
//
//			} else {
//				// getResponse();
//			}
//		} catch (ProtocolException e) {
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return model;
//	}

	public List<WebCreateModel> customer(List<CustomerRecord> records) {

		List<WebCreateModel> model = null;

		JSONObject requestList = new JSONObject();
		try {

			// firstname z_cuc_firstname
			// lastname z_cuc_lastname
			// position(picklist) z_cuc_position
			// mobileno size z_cuc_mobileno
			// birthday z_cuc_birthday
			// email z_cuc_email
			// customer(table) z_cuc_customer
			// isactive z_cuc_isactive
			// assignedto assigned_user_id

			UserTable userTable = DB.getUser();
			PCustSizeTable cSizeTable = DB.getCustomerSize();
			PCustTypeTable cTypeTable = DB.getCustomerType();
			BusinessUnitTable buTable = DB.getBusinessUnit();
			PAreaTable areaTable = DB.getArea();
			PProvinceTable provinceTable = DB.getProvince();
			PCityTownTable cityTable = DB.getCityTown();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				requestObject.put("z_cu_customername", records.get(x)
						.getCustomerName());
				requestObject.put("z_cu_streetadd", records.get(x)
						.getStreetAddress());
				requestObject.put("z_cu_chainname", records.get(x)
						.getChainName());
				requestObject
						.put("z_cu_landline", records.get(x).getLandline());
				String size = cSizeTable.getNameById(records.get(x)
						.getCustomerSize());
				requestObject.put("z_cu_customersize", size);
				String type = cTypeTable.getNameById(records.get(x)
						.getCustomerType());
				requestObject.put("z_cu_customertype", type);
				// get customer from db
				String businessUnit = buTable.getNoById(records.get(x)
						.getBusinessUnit());
				requestObject.put("z_cu_businessunit", businessUnit);
				String area = areaTable.getNameById(records.get(x).getArea());
				requestObject.put("z_area", area);
				String prov = provinceTable.getNameById(records.get(x)
						.getProvince());
				requestObject.put("z_province", prov);
				String city = cityTable.getNameById(records.get(x)
						.getCityTown());
				requestObject.put("z_city ", city);
				requestObject
						.put("z_cu_isactive", records.get(x).getIsActive());
				requestObject.put("z_cu_fax ", records.get(x).getFax());

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.CustomerContact));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

			// firstname z_cuc_firstname
			// lastname z_cuc_lastname
			// position(picklist) z_cuc_position
			// mobileno size z_cuc_mobileno
			// birthday z_cuc_birthday
			// email z_cuc_email
			// customer(table) z_cuc_customer
			// isactive z_cuc_isactive
			// assignedto assigned_user_id

			UserTable userTable = DB.getUser();
			PCustConPositionTable ccPositiontable = DB
					.getCustomerContactPosition();
			CustomerTable customerTable = DB.getCustomer();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				requestObject.put("z_cuc_firstname", records.get(x)
						.getFirstName());
				requestObject.put("z_cuc_lastname", records.get(x)
						.getLastName());
				String position = ccPositiontable.getNameById(records.get(x)
						.getPosition());
				requestObject.put("z_cuc_position", position);
				requestObject.put("z_cuc_mobileno", records.get(x)
						.getMobileNo());
				requestObject.put("z_cuc_birthday", records.get(x)
						.getBirthday());
				requestObject.put("z_cuc_email", records.get(x)
						.getEmailAddress());
				// get customer from db
				String customer = customerTable.getNoById(records.get(x)
						.getCustomer());
				requestObject.put("z_cuc_customer", customer);
				requestObject.put("z_cuc_isactive", records.get(x)
						.getIsActive());

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.CustomerContact));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

			// workplan(table) z_ac_workplan
			// startTime z_ac_starttime
			// endTime z_ac_endtime
			// latitude z_ac_latitude
			// longitude z_ac_longitude
			// objective z_ac_objective
			// notes z_ac_notes
			// highlights z_ac_highlights
			// nextSteps z_ac_nextsteps
			// follow-upCommitmentDate z_ac_followupcomdate
			// activityType(table) z_ac_activitytype
			// workplanEntry(table) z_ac_workplanentry
			// customer(table) z_ac_customer
			// firstTimeVisit z_ac_firsttimevisit
			// plannedVisit z_ac_plannedvisit
			// assignedto assigned_user_id
			// smr z_ac_smr
			// issuesIdentified z_ac_issuesidentified
			// feedbackFromcustomer z_ac_feedbackfromcu
			// ongoingCampaigns z_ac_ongoingcampaigns
			// lastDelivery z_ac_lastdelivery
			// promoStubs z_ac_promostubsdetails
			// projectName z_ac_projectname
			// projectStage z_ac_projectstage
			// projectCategory z_ac_projectcategory
			// date z_ac_date
			// time z_ac_time
			// venue z_ac_venue
			// numberAttendees z_ac_noofattenees

			UserTable userTable = DB.getUser();
			// WorkplanTable workplanTable = DB.getWorkplan();
			ActivityTypeTable activityTypeTable = DB.getActivityType();
			WorkplanEntryTable workplanEntryTable = DB.getWorkplanEntry();
			CustomerTable customerTable = DB.getCustomer();
			// SMRTable smrTable = DB.getSMR();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// get user id from db
				// String workplan = workplanTable.getNoById(records.get(x)
				// .getWorkplan());
				// requestObject.put("z_ac_workplan", workplan);
				// requestObject.put("z_ac_starttime", records.get(x)
				// .getStartTime());
				// requestObject.put("z_ac_endtime",
				// records.get(x).getEndTime());
				requestObject
						.put("z_ac_latitude", records.get(x).getLatitude());
				requestObject.put("z_ac_longitude", records.get(x)
						.getLongitude());
				// requestObject.put("z_ac_objective", records.get(x)
				// .getObjectives());
				requestObject.put("z_ac_notes", records.get(x).getNotes());
				requestObject.put("z_ac_highlights", records.get(x)
						.getHighlights());
				requestObject.put("z_ac_nextsteps", records.get(x)
						.getNextSteps());
				requestObject.put("z_ac_followupcomdate", records.get(x)
						.getFollowUpCommitmentDate());
				// get type from db
				String type = activityTypeTable.getNoById(records.get(x)
						.getActivityType());
				requestObject.put("z_ac_activitytype", type);
				// get workplanEntry from db
				String workplanEntry = workplanEntryTable.getNoById(records
						.get(x).getWorkplanEntry());
				requestObject.put("z_ac_workplanentry", workplanEntry);
				// get customer from db
				String customer = customerTable.getNoById(records.get(x)
						.getCustomer());
				requestObject.put("z_ac_customer", customer);
				requestObject.put("z_ac_firsttimevisit", records.get(x)
						.getFirstTimeVisit());
				requestObject.put("z_ac_plannedvisit", records.get(x)
						.getPlannedVisit());
				// get smr from db
				// String smr = smrTable.getNoById(records.get(x).getSMR());
				// requestObject.put("z_ac_smr", smr);

				// requestObject.put("z_ac_issuesidentified", records.get(x)
				// .getIssuesIdentified());
				// requestObject.put("z_ac_feedbackfromcu", records.get(x)
				// .getFeedbackFromCustomer());
				// requestObject.put("z_ac_ongoingcampaigns", records.get(x)
				// .getOngoingCampaigns());
				// requestObject.put("z_ac_lastdelivery", records.get(x)
				// .getLastDelivery());
				// requestObject.put("z_ac_promostubsdetails", records.get(x)
				// .getPromoStubsDetails());
				// requestObject.put("z_ac_projectname", records.get(x)
				// .getProjectName());
				// requestObject.put("z_ac_projectstage", records.get(x)
				// .getProjectStage());
				// requestObject.put("projectCategory", records.get(x)
				// .getProjectCategory());
				// requestObject.put("z_ac_date", records.get(x).getDate());
				// requestObject.put("z_ac_time", records.get(x).getTime());
				// requestObject.put("z_ac_venue", records.get(x).getVenue());
				// requestObject.put("z_ac_noofattenees", records.get(x)
				// .getNoOfAttendees());

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType", Modules.Activity));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

			// activity z_jmc_activity
			// product z_jmc_product
			// status z_jmc_status
			// assignedto assigned_user_id

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			ProductTable productTable = DB.getProduct();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				requestObject.put("z_jmc_activity", activity);
				// get product id from db
				String product = productTable.getNoById(records.get(x)
						.getProductBrand());
				requestObject.put("z_jmc_product", product);
				// requestObject.put("z_jmc_status", records.get(x).getst);

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.JDIMerchCheck));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

	public List<WebCreateModel> jdiProductStock(
			List<JDIproductStockCheckRecord> records) {

		List<WebCreateModel> model = null;

		JSONObject requestList = new JSONObject();
		try {

			// activity z_jmc_activity
			// product z_jmc_product
			// status z_jmc_status
			// assignedto assigned_user_id

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			ProductTable productTable = DB.getProduct();
			PJDIprodStatusTable jStatusTable = DB.getJDIproductStatus();
//			SupplierTable supplierTable = DB.getSupplier();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				requestObject.put("z_jps_activity", activity);
				// get product id from db
				String product = productTable.getNoById(records.get(x)
						.getProductBrand());
				requestObject.put("z_jps_product", product);
				String status = jStatusTable.getNameById(records.get(x)
						.getStockStatus());
				requestObject.put("z_jps_stockstatus", status);
				requestObject.put("z_jps_loadedonshelves", records.get(x)
						.getLoadedOnShelves());
//				String supplier = supplierTable.getNoById(records.get(x)
//						.getSupplier());
//				requestObject.put("z_jps_supplier", supplier);
				// requestObject.put("quantity", records.get(x).getQuantity());

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.JDIMerchCheck));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

			// activity z_jmc_activity
			// product z_jmc_product
			// status z_jmc_status
			// assignedto assigned_user_id

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			CompetitorProductTable productTable = DB.getCompetitorProduct();
			PComptProdStockStatusTable comptProdStatusTable = DB
					.getCompetitorProductStockStatus();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				requestObject.put("z_cps_activity", activity);
				// get product id from db
				String product = productTable.getNoById(records.get(x)
						.getCompetitorProduct());
				requestObject.put("z_cps_competitorprod", product);
				String status = comptProdStatusTable.getNameById(records.get(x)
						.getStockStatus());
				requestObject.put("z_cps_stockstatus", status);
				requestObject.put("z_cps_loadedonshelves", records.get(x)
						.getLoadedOnShelves());

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.JDIMerchCheck));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

			// activity z_jmc_activity
			// product z_jmc_product
			// status z_jmc_status
			// assignedto assigned_user_id

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
//			CompetitorTable compTable = DB.getCompetitor();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				requestObject.put("z_min_activity", activity);
				// get product id from db
//				String compt = compTable.getNoById(records.get(x)
//						.getCompetitorProduct());
//				requestObject.put("z_min_competitor", compt);
				requestObject.put("z_min_details", records.get(x).getDetails());

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.JDIMerchCheck));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

	public List<WebCreateModel> projectRequirements(
			List<ProjectRequirementRecord> records) {

		List<WebCreateModel> model = null;

		JSONObject requestList = new JSONObject();
		try {

			// activity z_jmc_activity
			// product z_jmc_product
			// status z_jmc_status
			// assignedto assigned_user_id

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			PProjReqTypeTable projReqTypeTable = DB.getProjectRequirementType();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				requestObject.put("z_pr_activity", activity);
				// get product id from db
				String type = projReqTypeTable.getNameById(records.get(x)
						.getProjectRequirementType());
				requestObject.put("z_pr_prtype", type);
				requestObject.put("z_pr_dateneeded", records.get(x)
						.getDateNeeded());
				requestObject.put("z_pr_squaremtrs", records.get(x)
						.getSquareMeters());
				// requestObject.put("z_pr_prodused", records.get(x).getpro);
				requestObject.put("z_pr_otherdet", records.get(x)
						.getOtherDetails());

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.JDIMerchCheck));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

	public List<WebCreateModel> documents(DocumentRecord records) {

		List<WebCreateModel> model = null;

		JSONObject requestList = new JSONObject();
		try {

			// { "0" : { "notes_title" : "gideon test", "assigned_user_id" :
			// "1",
			// "notecontent" : "gideon test", "filelocationtype" : "I",
			// "fileversion" : "1",
			// "filestatus" : "on", "folderid": "1", "mobile_id": "10",
			// "forModule" : "XMarketingIntel", "forEntityId" : "412" } }

			UserTable userTable = DB.getUser();

			JSONObject requestObject = new JSONObject();

			// get user id from db
			String id = userTable.getNoById(records.getUser());
			requestObject.put("assigned_user_id", id);
			requestObject.put("notes_title", records.getTitle());
			requestObject.put("notecontent", records.getFileName());
			requestObject.put("filelocationtype", "I");
			requestObject.put("fileversion", "1");
			requestObject.put("filestatus", "on");
			requestObject.put("folderid", "1");
			requestObject.put("mobile_id", String.valueOf(records.getId()));
			requestObject.put("forModule", records.getModuleName());
			requestObject.put("forEntityId", records.getModuleId());

			requestList.put(String.valueOf(0), requestObject);

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		FileInputStream fileInputStream = null;
		File file = new File(JardineApp.JARDINE_DIRECTORY + "/"
				+ records.getModuleName() + "/" + records.getFileName());

		try {
			fileInputStream = new FileInputStream(file);

			url = new URL(urlString);
			getConnection(url, "POST");

			// // appending
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("sessionName",
			// JardineApp.SESSION_NAME));
			// params.add(new BasicNameValuePair("operation", operation));
			// params.add(new BasicNameValuePair("elementType", "Documents"));
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

			// **start
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
			dos.writeBytes("Documents");
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
			dos.writeBytes(requestList.toString());
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			// **end

			// **start
			dos.writeBytes(JardineApp.REQUEST_TWOHYPHENS
					+ JardineApp.REQUEST_BOUNDARY + JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
					+ file.toString() + "\"" + JardineApp.REQUEST_LINEEND);
			dos.writeBytes("Content-Type: image/jpeg"
					+ JardineApp.REQUEST_LINEEND);
			dos.writeBytes(JardineApp.REQUEST_LINEEND);
			Log.e(JardineApp.TAG, "Headers are written 1");
			// create a buffer of maximum size
			int bytesAvailable = fileInputStream.available();
			int maxBufferSize = 1024;
			int bufferSize = Math.min(bytesAvailable, maxBufferSize);
			byte[] buffer = new byte[bufferSize];
			// read file and write it into form...
			int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}
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
			fileInputStream.close();
			dos.flush();
			dos.close();

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

	// create documents
	// {
	// "success": true,
	// "result": {
	// "create": [
	// {
	// "id": 491,
	// "mobile_id": "10",
	// "modifiedtime": null
	// }
	// ]
	// }
	// }

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
