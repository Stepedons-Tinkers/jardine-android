package co.nextix.jardine.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;
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
import co.nextix.jardine.web.models.DocumentModel;
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
import co.nextix.jardine.web.requesters.DocumentRequester;
import co.nextix.jardine.web.requesters.RetrieveRequester;
import co.nextix.jardine.web.requesters.RetrieveRequester.Result;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class RetrieveRequests {

	private final String TAG = "Webservice";
	private final String operation = "retrieves";

	public List<RetrieveRequester<BusinessUnitModel>.Result> BusinessUnit(
			String[] ids) {
		// GET
		// http://115.85.42.163/Jardine/webservice.php?elementType=XMarketingIntel&sessionName=11290558539c19875257b&ids={"0":412}&operation=retrieves

		List<RetrieveRequester<BusinessUnitModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<BusinessUnitModel>>() {
				}.getType();
				RetrieveRequester<BusinessUnitModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<BusinessUnitModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<MarketingMaterialsModel>.Result> MarketingMaterials(
			String[] ids) {

		List<RetrieveRequester<MarketingMaterialsModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<MarketingMaterialsModel>>() {
				}.getType();
				RetrieveRequester<MarketingMaterialsModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<MarketingMaterialsModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<EventProtocolModel>.Result> EventProtocol(
			String[] ids) {

		List<RetrieveRequester<EventProtocolModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<EventProtocolModel>>() {
				}.getType();
				RetrieveRequester<EventProtocolModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<EventProtocolModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<ProductModel>.Result> Product(String[] ids) {

		List<RetrieveRequester<ProductModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<ProductModel>>() {
				}.getType();
				RetrieveRequester<ProductModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<ProductModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<SupplierModel>.Result> Supplier(String[] ids) {

		List<RetrieveRequester<SupplierModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<SupplierModel>>() {
				}.getType();
				RetrieveRequester<SupplierModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<SupplierModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<CompetitorModel>.Result> Competitor(
			String[] ids) {

		List<RetrieveRequester<CompetitorModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<CompetitorModel>>() {
				}.getType();
				RetrieveRequester<CompetitorModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<CompetitorModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<CompetitorProductModel>.Result> CompetitorProduct(
			String[] ids) {

		List<RetrieveRequester<CompetitorProductModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<CompetitorProductModel>>() {
				}.getType();
				RetrieveRequester<CompetitorProductModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<CompetitorProductModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<SMRModel>.Result> SMR(String[] ids) {

		List<RetrieveRequester<SMRModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<SMRModel>>() {
				}.getType();
				RetrieveRequester<SMRModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<SMRModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<SMRtimeCardModel>.Result> SMRtimeCard(
			String[] ids) {

		List<RetrieveRequester<SMRtimeCardModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<SMRtimeCardModel>>() {
				}.getType();
				RetrieveRequester<SMRtimeCardModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<SMRtimeCardModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<CustomerModel>.Result> Customer(String[] ids) {

		List<RetrieveRequester<CustomerModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<CustomerModel>>() {
				}.getType();
				RetrieveRequester<CustomerModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<CustomerModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<CustomerModel>.Result> CustomerContact(
			String[] ids) {

		List<RetrieveRequester<CustomerModel>.Result> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CustomerContact + "&sessionName="
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
				Type typeOfT = new TypeToken<RetrieveRequester<CustomerModel>>() {
				}.getType();
				RetrieveRequester<CustomerModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<CustomerModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<ActivityTypeModel>.Result> ActivityType(
			String[] ids) {

		List<RetrieveRequester<ActivityTypeModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<ActivityTypeModel>>() {
				}.getType();
				RetrieveRequester<ActivityTypeModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<ActivityTypeModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<WorkplanModel>.Result> Workplan(String[] ids) {
		// GET
		// http://115.85.42.163/Jardine/webservice.php?elementType=XMarketingIntel&sessionName=11290558539c19875257b&ids={"0":412}&operation=retrieves

		List<RetrieveRequester<WorkplanModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<WorkplanModel>>() {
				}.getType();
				RetrieveRequester<WorkplanModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<WorkplanModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<WorkplanEntryModel>.Result> WorkplanEntry(
			String[] ids) {

		List<RetrieveRequester<WorkplanEntryModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<WorkplanEntryModel>>() {
				}.getType();
				RetrieveRequester<WorkplanEntryModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<WorkplanEntryModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<ActivityModel>.Result> Activity(String[] ids) {

		List<RetrieveRequester<ActivityModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<ActivityModel>>() {
				}.getType();
				RetrieveRequester<ActivityModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<ActivityModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<JDImerchandisingCheckModel>.Result> JDImerch(
			String[] ids) {

		List<RetrieveRequester<JDImerchandisingCheckModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<JDImerchandisingCheckModel>>() {
				}.getType();
				RetrieveRequester<JDImerchandisingCheckModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<JDImerchandisingCheckModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<JDIproductStockCheckModel>.Result> JDIproduct(
			String[] ids) {

		List<RetrieveRequester<JDIproductStockCheckModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<JDIproductStockCheckModel>>() {
				}.getType();
				RetrieveRequester<JDIproductStockCheckModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<JDIproductStockCheckModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<CompetitorProductStockCheckModel>.Result> CompetitorProductStockCheck(
			String[] ids) {

		List<RetrieveRequester<CompetitorProductStockCheckModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<CompetitorProductStockCheckModel>>() {
				}.getType();
				RetrieveRequester<CompetitorProductStockCheckModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<CompetitorProductStockCheckModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<MarketingIntelModel>.Result> MarketingIntel(
			String[] ids) {

		List<RetrieveRequester<MarketingIntelModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<MarketingIntelModel>>() {
				}.getType();
				RetrieveRequester<MarketingIntelModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<MarketingIntelModel>.Result>) requester
						.getResult();

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

	public List<RetrieveRequester<MarketingIntelModel>.Result> ProjectRequirement(
			String[] ids) {

		List<RetrieveRequester<MarketingIntelModel>.Result> model = null;
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
				Type typeOfT = new TypeToken<RetrieveRequester<MarketingIntelModel>>() {
				}.getType();
				RetrieveRequester<MarketingIntelModel> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<RetrieveRequester<MarketingIntelModel>.Result>) requester
						.getResult();

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

	public DocumentModel Document(String id) {

		DocumentModel model = null;
		// JSONArray jsonArray = new JSONArray();
		// try {
		// // for (int x = 0; x < ids.length; x++) {
		// jsonArray.put(0, id);
		// // }
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }

		String urlString = JardineApp.WEB_URL + "?elementType=" + "Documents"
				+ "&sessionName=" + JardineApp.SESSION_NAME + "&ids={\"0\":"
				+ id + "}&operation=" + operation;
		// {"0":487}
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
				Type typeOfT = new TypeToken<DocumentRequester>() {
				}.getType();
				DocumentRequester requester = gson.fromJson(getReader(),
						typeOfT);
				model = (DocumentModel) requester.getResult().get(0)
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

	public boolean DownloadFile(int fileSize, String UrlPath,
			String moduleName, String fileName) {

		boolean result = false;

		File file = new File(JardineApp.JARDINE_DIRECTORY + "/" + moduleName
				+ "/" + fileName);
		File fPath = new File(JardineApp.JARDINE_DIRECTORY + "/" + moduleName);

		// create directory path if needed
		if (!fPath.exists())
			fPath.mkdirs();
		// if (!fPriorities.exists())
		// fPriorities.mkdirs();
		// if (!fPath.exists())
		// fPath.mkdirs();

		long startTime = System.currentTimeMillis();
		Log.w(JardineApp.TAG, "file download beginning: " + UrlPath);

		if (!file.exists()) {
			URL url = null;
			try {
				url = new URL(UrlPath);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			// Open a connection to that URL.
			URLConnection ucon = null;

			// Define InputStreams to read from the URLConnection.
			// uses 3KB download buffer
			InputStream is = null;

			try {

				ucon = url.openConnection();

				// this timeout affects how long it takes for the app to realize
				// there's
				// a connection problem
				// ucon.setReadTimeout(TIMEOUT_CONNECTION);
				// ucon.setConnectTimeout(TIMEOUT_SOCKET);

				is = ucon.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// int BUFFER_SIZE = 50 * 1024;
			int BUFFER_SIZE = fileSize + 50;
			byte[] buff = new byte[BUFFER_SIZE];
			BufferedInputStream inStream = new BufferedInputStream(is,
					BUFFER_SIZE);
			FileOutputStream outStream = null;
			try {

				outStream = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			// Read bytes (and store them) until there is nothing more to
			// read(-1)
			// int len;
			try {
				// while ((len = inStream.read(buff)) != -1) {
				// outStream.write(buff, 0, len);
				// }

				int len1 = 0;
				while ((len1 = inStream.read(buff)) > 0) {
					outStream.write(buff, 0, len1);
				}

				// clean up
				outStream.flush();
				outStream.close();
				inStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (file.length() > 0)
			result = true;
		Log.e(JardineApp.TAG, "download saved in: " + file.getAbsolutePath());
		Log.e(JardineApp.TAG,
				"download completed in "
						+ ((System.currentTimeMillis() - startTime) / 1000)
						+ " sec. length: " + file.length());

		return result;
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
