package co.nextix.jardine.fragments;

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
import co.nextix.jardine.web.LogRequests;
import co.nextix.jardine.web.RetrieveRequests;
import co.nextix.jardine.web.models.WorkplanModel;
import co.nextix.jardine.web.requesters.LoginModel;

public class SyncMenuBarFragment extends Fragment {

	public SyncMenuBarFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		View rootView = inflater.inflate(R.layout.fragment_sync, container,
				false);

		return rootView;
	}

	private class SyncTask extends AsyncTask<Void, Void, Boolean> {
		ProgressDialog dialog;
		List<WorkplanModel> models;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Signing in");
			dialog.setMessage("Verifying account. Please wait...");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {

//				if (model != null) {
					Log.i(JardineApp.TAG, "session: ");

					return true;
//				} else {
//					return false;
//				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
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
