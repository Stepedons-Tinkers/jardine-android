package co.nextix.jardine.fragments;

import java.util.List;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.tables.picklists.PAreaTable;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.web.PicklistDependencyModel;
import co.nextix.jardine.web.PicklistModel;
import co.nextix.jardine.web.PicklistRequests;
import co.nextix.jardine.web.models.WorkplanModel;

public class SyncMenuBarFragment extends Fragment {

	private final String TAG = "Webservice";

	public SyncMenuBarFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		View rootView = inflater.inflate(R.layout.fragment_sync, container,
				false);

		new PicklistDependencyTask().execute();

		return rootView;
	}

	private class PicklistDependencyTask extends AsyncTask<Void, Void, Boolean> {
		ProgressDialog dialog;
		List<PicklistDependencyModel> areas, provinces, cities;
		long aId = 0;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Syncing...");
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
//						aId = aTable.insertArea(area);
						Log.d(TAG, "AREA: " + area);

						String SAprovinces = a.getTargetValues()
								.replace("[", "").replace("]", "")
								.replace("&quot;", "");
						Log.d(TAG, "provinces: " + SAprovinces);
						// if (provinces != null) {
						// for (String p : provinces) {
						// pTable.insertProvince(p.replace("&quot;", ""),
						// aId);
						// Log.i(TAG,
						// "Province: "
						// + a.getSourceValue().replace(
						// "&quot;", ""));
						// }
						// }
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

			} else {
				Toast.makeText(getActivity(), "Invalid credentials",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
