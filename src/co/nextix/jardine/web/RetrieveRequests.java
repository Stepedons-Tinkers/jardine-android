package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.keys.Modules;
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
import co.nextix.jardine.web.models.ProductModel;
import co.nextix.jardine.web.models.ProjectRequirementModel;
import co.nextix.jardine.web.models.SMRModel;
import co.nextix.jardine.web.models.SMRtimeCardModel;
import co.nextix.jardine.web.models.SupplierModel;
import co.nextix.jardine.web.models.WorkplanEntryModel;
import co.nextix.jardine.web.models.WorkplanModel;
import co.nextix.jardine.web.requesters.RetrieveRequester;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class RetrieveRequests {

	private final String TAG = "Webservice_Create";
	private final String operation = "retrieves";

	public List<BusinessUnitModel> BusinessUnit(String[] ids) {
		// GET
		// http://115.85.42.163/Jardine/webservice.php?elementType=XMarketingIntel&sessionName=11290558539c19875257b&ids={"0":412}&operation=retrieves

		List<BusinessUnitModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.BusinessUnit + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<BusinessUnitModel>>>() {
				}.getType();
				RetrieveRequester<List<BusinessUnitModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<BusinessUnitModel>) requester.getResult()
						.getDetails();

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

	public List<MarketingMaterialsModel> MarketingMaterials(String[] ids) {

		List<MarketingMaterialsModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.MarketingMaterials + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<MarketingMaterialsModel>>>() {
				}.getType();
				RetrieveRequester<List<MarketingMaterialsModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<MarketingMaterialsModel>) requester.getResult()
						.getDetails();

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

	public List<EventProtocolModel> EventProtocol(String[] ids) {

		List<EventProtocolModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.EventProtocol + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<EventProtocolModel>>>() {
				}.getType();
				RetrieveRequester<List<EventProtocolModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<EventProtocolModel>) requester.getResult()
						.getDetails();

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

	public List<ProductModel> Product(String[] ids) {

		List<ProductModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Product + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&ids=" + jsonArray.toString() + "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<ProductModel>>>() {
				}.getType();
				RetrieveRequester<List<ProductModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<ProductModel>) requester.getResult().getDetails();

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

	public List<SupplierModel> Supplier(String[] ids) {

		List<SupplierModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Supplier + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&ids=" + jsonArray.toString() + "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<SupplierModel>>>() {
				}.getType();
				RetrieveRequester<List<SupplierModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<SupplierModel>) requester.getResult()
						.getDetails();

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

	public List<CompetitorModel> Competitor(String[] ids) {

		List<CompetitorModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Competitor + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<CompetitorModel>>>() {
				}.getType();
				RetrieveRequester<List<CompetitorModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<CompetitorModel>) requester.getResult()
						.getDetails();

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

	public List<CompetitorProductModel> CompetitorProduct(String[] ids) {

		List<CompetitorProductModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CompetitorProduct + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<CompetitorProductModel>>>() {
				}.getType();
				RetrieveRequester<List<CompetitorProductModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<CompetitorProductModel>) requester.getResult()
						.getDetails();

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

	public List<SMRModel> SMR(String[] ids) {

		List<SMRModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType=" + Modules.SMR
				+ "&sessionName=" + JardineApp.SESSION_NAME + "&ids="
				+ jsonArray.toString() + "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<SMRModel>>>() {
				}.getType();
				RetrieveRequester<List<SMRModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<SMRModel>) requester.getResult().getDetails();

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

	public List<SMRtimeCardModel> SMRtimeCard(String[] ids) {

		List<SMRtimeCardModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.SMRTimeCard + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<SMRtimeCardModel>>>() {
				}.getType();
				RetrieveRequester<List<SMRtimeCardModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<SMRtimeCardModel>) requester.getResult()
						.getDetails();

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

	public List<CustomerModel> Customer(String[] ids) {

		List<CustomerModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Customers + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&ids=" + jsonArray.toString() + "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<CustomerModel>>>() {
				}.getType();
				RetrieveRequester<List<CustomerModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<CustomerModel>) requester.getResult()
						.getDetails();

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

	public List<CustomerContactModel> CustomerContact(String[] ids) {

		List<CustomerContactModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.ContactPerson + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<CustomerContactModel>>>() {
				}.getType();
				RetrieveRequester<List<CustomerContactModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<CustomerContactModel>) requester.getResult()
						.getDetails();

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

	public List<ActivityTypeModel> ActivityType(String[] ids) {

		List<ActivityTypeModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.ActivityType + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<ActivityTypeModel>>>() {
				}.getType();
				RetrieveRequester<List<ActivityTypeModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<ActivityTypeModel>) requester.getResult()
						.getDetails();

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

	public List<WorkplanModel> Workplan(String[] ids) {
		// GET
		// http://115.85.42.163/Jardine/webservice.php?elementType=XMarketingIntel&sessionName=11290558539c19875257b&ids={"0":412}&operation=retrieves

		List<WorkplanModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Workplan + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&ids=" + jsonArray.toString() + "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<WorkplanModel>>>() {
				}.getType();
				RetrieveRequester<List<WorkplanModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WorkplanModel>) requester.getResult()
						.getDetails();

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

	public List<WorkplanEntryModel> WorkplanEntry(String[] ids) {

		List<WorkplanEntryModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.WorkplanEntry + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<WorkplanEntryModel>>>() {
				}.getType();
				RetrieveRequester<List<WorkplanEntryModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WorkplanEntryModel>) requester.getResult()
						.getDetails();

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

	public List<ActivityModel> Activity(String[] ids) {

		List<ActivityModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Activity + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&ids=" + jsonArray.toString() + "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<ActivityModel>>>() {
				}.getType();
				RetrieveRequester<List<ActivityModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<ActivityModel>) requester.getResult()
						.getDetails();

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

	public List<JDImerchandisingCheckModel> JDImerch(String[] ids) {

		List<JDImerchandisingCheckModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.JDIMerchCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<JDImerchandisingCheckModel>>>() {
				}.getType();
				RetrieveRequester<List<JDImerchandisingCheckModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<JDImerchandisingCheckModel>) requester
						.getResult().getDetails();

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

	public List<JDIproductStockCheckModel> JDIproduct(String[] ids) {

		List<JDIproductStockCheckModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.JDIProductStockCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<JDIproductStockCheckModel>>>() {
				}.getType();
				RetrieveRequester<List<JDIproductStockCheckModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<JDIproductStockCheckModel>) requester.getResult()
						.getDetails();

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

	public List<CompetitorProductStockCheckModel> CompetitorProductStockCheck(
			String[] ids) {

		List<CompetitorProductStockCheckModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CompetitorProductStockCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<CompetitorProductStockCheckModel>>>() {
				}.getType();
				RetrieveRequester<List<CompetitorProductStockCheckModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<CompetitorProductStockCheckModel>) requester
						.getResult().getDetails();

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

	public List<MarketingIntelModel> MarketingIntel(String[] ids) {

		List<MarketingIntelModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.MarketingIntel + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<MarketingIntelModel>>>() {
				}.getType();
				RetrieveRequester<List<MarketingIntelModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<MarketingIntelModel>) requester.getResult()
						.getDetails();

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

	public List<ProjectRequirementModel> ProjectRequirement(String[] ids) {

		List<ProjectRequirementModel> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.ProjectRequirement + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<List<ProjectRequirementModel>>>() {
				}.getType();
				RetrieveRequester<List<ProjectRequirementModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<ProjectRequirementModel>) requester.getResult()
						.getDetails();

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

	private void getJsonConnection(URL url, String requestMethod) {

		try {
			JardineApp.httpConnection = (HttpURLConnection) url
					.openConnection();
			JardineApp.httpConnection.setRequestMethod(requestMethod);
			JardineApp.httpConnection.setDoInput(true);
			if (requestMethod.equals("GET")) {
				JardineApp.httpConnection.setDoOutput(false);

			} else {
				JardineApp.httpConnection.setDoOutput(true);
			}

			JardineApp.httpConnection.setRequestProperty("Content-Type",
					"application/json");

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

	// private void getResponse() {
	// Gson gson = new Gson();
	// ResponseModel errorModel = gson.fromJson(getReader(),
	// ResponseModel.class);
	// JardineApp.ErrorMessage = errorModel.getMessage();
	// }
}
