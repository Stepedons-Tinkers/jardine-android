package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.SMRtimeCardRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.CompetitorTable;
import co.nextix.jardine.database.tables.CustomerContactTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.SMRTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.WorkplanEntryTable;
import co.nextix.jardine.database.tables.WorkplanTable;
import co.nextix.jardine.database.tables.picklists.PCustConPositionTable;
import co.nextix.jardine.database.tables.picklists.PSMRentryTypeTable;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.web.requesters.DefaultRequester;
import co.nextix.jardine.web.requesters.WebCreateModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class CreateRequests {

	private final String TAG = "Webservice_Create";
	private final String operation = "creates";
	private final String charset = "UTF-8";
	private final DatabaseAdapter DB = DatabaseAdapter.getInstance();

	public List<WebCreateModel> competitorProduct(
			List<CompetitorProductRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			CompetitorTable compTable = DB.getCompetitor();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getUser());
				requestObject.put("assigned_user_id", id);
				String competitor = compTable.getNoById(records.get(x)
						.getCompetitor());
				requestObject.put("z_cmp_comp", competitor);
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

	public List<WebCreateModel> smrTimecard(List<SMRtimeCardRecord> records) {

		List<WebCreateModel> model = null;

		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			PSMRentryTypeTable smrEntry = DB.getSMRentryType();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getUser());
				requestObject.put("assigned_user_id", id);
				requestObject.put("z_stc_date", records.get(x).getDate());
				requestObject.put("z_stc_time", records.get(x).getTimestamp());
				// get entry type from db
				String entryType = smrEntry.getById(
						records.get(x).getEntryType()).getName();
				requestObject.put("z_stc_picklist", entryType);

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
					Modules.SMRTimeCard));
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
				String id = userTable.getNoById(records.get(x).getUser());
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
			WorkplanTable workplanTable = DB.getWorkplan();
			ActivityTypeTable activityTypeTable = DB.getActivityType();
			WorkplanEntryTable workplanEntryTable = DB.getWorkplanEntry();
			CustomerTable customerTable = DB.getCustomer();
			SMRTable smrTable = DB.getSMR();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getUser());
				requestObject.put("assigned_user_id", id);
				// get user id from db
				String workplan = workplanTable.getNoById(records.get(x)
						.getWorkplan());
				requestObject.put("z_ac_workplan", workplan);
				requestObject.put("z_ac_starttime", records.get(x)
						.getStartTime());
				requestObject.put("z_ac_endtime", records.get(x).getEndTime());
				requestObject
						.put("z_ac_latitude", records.get(x).getLatitude());
				requestObject.put("z_ac_longitude", records.get(x)
						.getLongitude());
				requestObject.put("z_ac_objective", records.get(x)
						.getObjectives());
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
				String smr = smrTable.getNoById(records.get(x).getSMR());
				requestObject.put("z_ac_smr", smr);

				requestObject.put("z_ac_issuesidentified", records.get(x)
						.getIssuesIdentified());
				requestObject.put("z_ac_feedbackfromcu", records.get(x)
						.getFeedbackFromCustomer());
				requestObject.put("z_ac_ongoingcampaigns", records.get(x)
						.getOngoingCampaigns());
				requestObject.put("z_ac_lastdelivery", records.get(x)
						.getLastDelivery());
				requestObject.put("z_ac_promostubsdetails", records.get(x)
						.getPromoStubsDetails());
				requestObject.put("z_ac_projectname", records.get(x)
						.getProjectName());
				requestObject.put("z_ac_projectstage", records.get(x)
						.getProjectStage());
				requestObject.put("projectCategory", records.get(x)
						.getProjectCategory());
				requestObject.put("z_ac_date", records.get(x).getDate());
				requestObject.put("z_ac_time", records.get(x).getTime());
				requestObject.put("z_ac_venue", records.get(x).getVenue());
				requestObject.put("z_ac_noofattenees", records.get(x)
						.getNoOfAttendees());

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
				String id = userTable.getNoById(records.get(x).getUser());
				requestObject.put("assigned_user_id", id);
				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getUser());
				requestObject.put("z_jmc_activity", activity);
				// get product id from db
				String product = productTable.getNoById(records.get(x)
						.getUser());
				requestObject.put("z_jmc_product", product);
				requestObject.put("z_jmc_status", records.get(x).getIsActive());

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

//	public List<WebCreateModel> jdiProductStock(
//			List<JDIproductStockCheckRecord> records) {
//
//		List<WebCreateModel> model = null;
//
//		JSONObject requestList = new JSONObject();
//		try {
//
//			// activity z_jmc_activity
//			// product z_jmc_product
//			// status z_jmc_status
//			// assignedto assigned_user_id
//
//			UserTable userTable = DB.getUser();
//			ActivityTable activityTable = DB.getActivity();
//			ProductTable productTable = DB.getProduct();
//
//			for (int x = 0; x < records.size(); x++) {
//				JSONObject requestObject = new JSONObject();
//
//				// get user id from db
//				String id = userTable.getNoById(records.get(x).getUser());
//				requestObject.put("assigned_user_id", id);
//				// get activity id from db
//				String activity = activityTable.getNoById(records.get(x)
//						.getUser());
//				requestObject.put("z_jmc_activity", activity);
//				// get product id from db
//				String product = productTable.getNoById(records.get(x)
//						.getUser());
//				requestObject.put("z_jmc_product", product);
//				requestObject.put("z_jmc_status", records.get(x).getIsActive());
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
//					Modules.JDIMerchCheck));
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
