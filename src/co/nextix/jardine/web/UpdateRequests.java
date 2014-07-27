package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
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
				int active = records.get(x).getIsActive();
				String fax = records.get(x).getFax();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				if (!customerName.equals(""))
					requestObject.put("z_cu_customername", customerName);
				if (!streetAdd.equals(""))
					requestObject.put("z_cu_streetadd", streetAdd);
				if (!chain.equals(""))
					requestObject.put("z_cu_chainname", chain);
				if (!land.equals(""))
					requestObject.put("z_cu_landline", land);
				if (!size.equals(""))
					requestObject.put("z_cu_customersize", size);
				if (!type.equals(""))
					requestObject.put("z_cu_customertype", type);
				if (!businessUnit.equals(""))
					requestObject.put("z_cu_businessunit", businessUnit);
				if (!area.equals(""))
					requestObject.put("z_area", area);
				if (!prov.equals(""))
					requestObject.put("z_province", prov);
				if (!city.equals(""))
					requestObject.put("z_city ", city);
				requestObject.put("z_cu_isactive", active);
				if (!fax.equals(""))
					requestObject.put("z_cu_fax ", fax);

				requestList.put(String.valueOf(x), requestObject);
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
			params.add(new BasicNameValuePair("elementType", Modules.Customers));
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
				if (!firstname.equals(""))
					requestObject.put("z_cuc_firstname", firstname);
				if (!lastname.equals(""))
					requestObject.put("z_cuc_lastname", lastname);
				if (!position.equals(""))
					requestObject.put("z_cuc_position", position);
				if (!mobile.equals(""))
					requestObject.put("z_cuc_mobileno", mobile);
				if (!bday.equals(""))
					requestObject.put("z_cuc_birthday", bday);
				if (!email.equals(""))
					requestObject.put("z_cuc_email", email);
				if (!customer.equals(""))
					requestObject.put("z_cuc_customer", customer);
				requestObject.put("z_cuc_isactive", active);

				requestList.put(String.valueOf(x), requestObject);
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
				// String workplan = workplanTable.getNoById(records.get(x)
				// .getWorkplan());
				// if (!workplan.equals(""))
				// requestObject.put("z_ac_workplan", workplan);
				// if (!records.get(x).getStartTime().equals(""))
				// requestObject.put("z_ac_starttime", records.get(x)
				// .getStartTime());
				// if (!records.get(x).getEndTime().equals(""))
				// requestObject.put("z_ac_endtime", records.get(x)
				// .getEndTime());
				if (records.get(x).getLatitude() != 0)
					requestObject.put("z_ac_latitude", records.get(x)
							.getLatitude());
				if (records.get(x).getLongitude() != 0)
					requestObject.put("z_ac_longitude", records.get(x)
							.getLongitude());
				// if (!records.get(x).getObjectives().equals(""))
				// requestObject.put("z_ac_objective", records.get(x)
				// .getObjectives());
				if (!records.get(x).getNotes().equals(""))
					requestObject.put("z_ac_notes", records.get(x).getNotes());
				if (!records.get(x).getHighlights().equals(""))
					requestObject.put("z_ac_highlights", records.get(x)
							.getHighlights());
				if (!records.get(x).getNextSteps().equals(""))
					requestObject.put("z_ac_nextsteps", records.get(x)
							.getNextSteps());
				if (!records.get(x).getFollowUpCommitmentDate().equals(""))
					requestObject.put("z_ac_followupcomdate", records.get(x)
							.getFollowUpCommitmentDate());
				// get type from db
				String type = activityTypeTable.getNoById(records.get(x)
						.getActivityType());
				if (!type.equals(""))
					requestObject.put("z_ac_activitytype", type);
				// get workplanEntry from db
				String workplanEntry = workplanEntryTable.getNoById(records
						.get(x).getWorkplanEntry());
				if (!workplanEntry.equals(""))
					requestObject.put("z_ac_workplanentry", workplanEntry);
				// get customer from db
				String customer = customerTable.getNoById(records.get(x)
						.getCustomer());
				if (!customer.equals(""))
					requestObject.put("z_ac_customer", customer);
				requestObject.put("z_ac_firsttimevisit", records.get(x)
						.getFirstTimeVisit());
				requestObject.put("z_ac_plannedvisit", records.get(x)
						.getPlannedVisit());
				// get smr from db
				// String smr = smrTable.getNoById(records.get(x).getSMR());
				// if (!smr.equals(""))
				// requestObject.put("z_ac_smr", smr);
				// if (!records.get(x).getIssuesIdentified().equals(""))
				// requestObject.put("z_ac_issuesidentified", records.get(x)
				// .getIssuesIdentified());
				// if (!records.get(x).getFeedbackFromCustomer().equals(""))
				// requestObject.put("z_ac_feedbackfromcu", records.get(x)
				// .getFeedbackFromCustomer());
				// if (!records.get(x).getOngoingCampaigns().equals(""))
				// requestObject.put("z_ac_ongoingcampaigns", records.get(x)
				// .getOngoingCampaigns());
				// if (!records.get(x).getLastDelivery().equals(""))
				// requestObject.put("z_ac_lastdelivery", records.get(x)
				// .getLastDelivery());
				// if (!records.get(x).getPromoStubsDetails().equals(""))
				// requestObject.put("z_ac_promostubsdetails", records.get(x)
				// .getPromoStubsDetails());
				// if (!records.get(x).getProjectName().equals(""))
				// requestObject.put("z_ac_projectname", records.get(x)
				// .getProjectName());
				// if (!records.get(x).getProjectStage().equals(""))
				// requestObject.put("z_ac_projectstage", records.get(x)
				// .getProjectStage());
				// if (!records.get(x).getProjectCategory().equals(""))
				// requestObject.put("projectCategory", records.get(x)
				// .getProjectCategory());
				// if (!records.get(x).getDate().equals(""))
				// requestObject.put("z_ac_date", records.get(x).getDate());
				// if (!records.get(x).getTime().equals(""))
				// requestObject.put("z_ac_time", records.get(x).getTime());
				// if (!records.get(x).getVenue().equals(""))
				// requestObject.put("z_ac_venue", records.get(x).getVenue());
				// if (!records.get(x).getNoOfAttendees().equals(""))
				// requestObject.put("z_ac_noofattenees", records.get(x)
				// .getNoOfAttendees());

				requestList.put(String.valueOf(x), requestObject);
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

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			ProductTable productTable = DB.getProduct();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				// get product id from db
				String product = productTable.getNoById(records.get(x)
						.getProductBrand());
				// int active = records.get(x).getIsActive();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				if (!activity.equals(""))
					requestObject.put("z_jmc_activity", activity);
				if (!product.equals(""))
					requestObject.put("z_jmc_product", product);
				// requestObject.put("z_jmc_status", active);

				requestList.put(String.valueOf(x), requestObject);
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

	public List<WebCreateModel> jdiProduct(
			List<JDIproductStockCheckRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			ProductTable productTable = DB.getProduct();
			PJDIprodStatusTable jStatusTable = DB.getJDIproductStatus();
			// SupplierTable supplierTable = DB.getSupplier();

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
				// get product no from db
				// String supplier = supplierTable.getNoById(records.get(x)
				// .getSupplier());

				// get user no from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				if (!activity.equals(""))
					requestObject.put("z_jps_activity", activity);
				if (!product.equals(""))
					requestObject.put("z_jps_product", product);
				if (!status.equals(""))
					requestObject.put("z_jps_stockstatus", status);
				requestObject.put("z_jps_loadedonshelves", loaded);
				// if (!supplier.equals(""))
				// requestObject.put("z_jps_supplier", supplier);

				requestList.put(String.valueOf(x), requestObject);
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
					Modules.JDIProductStockCheck));
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

				// get user no from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				if (!activity.equals(""))
					requestObject.put("z_cps_activity", activity);
				if (!product.equals(""))
					requestObject.put("z_cps_competitorprod", product);
				if (!status.equals(""))
					requestObject.put("z_cps_stockstatus", status);
				requestObject.put("z_cps_loadedonshelves", loaded);

				requestList.put(String.valueOf(x), requestObject);
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
					Modules.CompetitorProductStockCheck));
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

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			// CompetitorTable compTable = DB.getCompetitor();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get activity no from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				// get product no from db
				// String compt = compTable.getNoById(records.get(x)
				// .getCompetitor());
				String details = records.get(x).getDetails();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				if (!activity.equals(""))
					requestObject.put("z_min_activity", activity);
				// if (!compt.equals(""))
				// requestObject.put("z_min_competitor", compt);
				if (!details.equals(""))
					requestObject.put("z_min_details", details);

				requestList.put(String.valueOf(x), requestObject);
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
					Modules.MarketingIntel));
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
				if (!activity.equals(""))
					requestObject.put("z_pr_activity", activity);
				if (!type.equals(""))
					requestObject.put("z_pr_prtype", type);
				if (!date.equals(""))
					requestObject.put("z_pr_dateneeded", date);
				if (!squaremeters.equals(""))
					requestObject.put("z_pr_squaremtrs", squaremeters);
				// if (!products.equals(""))
				// requestObject.put("z_pr_prodused", products);
				if (!others.equals(""))
					requestObject.put("z_pr_otherdet", others);

				requestList.put(String.valueOf(x), requestObject);
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
					Modules.ProjectRequirement));
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

	public List<WebCreateModel> productSupplier(
			List<ProductSupplierRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			ActivityTable activityTable = DB.getActivity();
			CustomerTable customerTable = DB.getCustomer();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getCreatedBy());
				requestObject.put("assigned_user_id", id);
				// get activity id from db
				String activity = activityTable.getNoById(records.get(x)
						.getActivity());
				requestObject.put("z_ps_activity", activity);
				requestObject.put("z_ps_productbrand", records.get(x)
						.getProductBrand());
				// get customer id from db
				String customer = customerTable.getNoById(records.get(x)
						.getSupplier());
				requestObject.put("z_ps_supplier", customer);
				requestObject.put("z_ps_othersremarks", records.get(x)
						.getOthersRemarks());

				requestList.put(String.valueOf(x), requestObject);
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
					Modules.ProductSupplier));
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
