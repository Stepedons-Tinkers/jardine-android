package co.nextix.jardine.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.LoginActivity;
import co.nextix.jardine.R;
import co.nextix.jardine.security.StoreAccount;

public class LogoutMenuBarFragment extends Fragment {

	public LogoutMenuBarFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		View rootView = inflater.inflate(R.layout.fragment_logout, container,
				false);

		logoutAlert();

		return rootView;
	}

	private class LogoutTask extends AsyncTask<Void, Void, Boolean> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Signing out");
			dialog.setMessage("Please wait...");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			StoreAccount.clear(getActivity());
			// JardineApp.DB.close();
			JardineApp.DB.clearDatabase();
			JardineApp.SESSION_NAME = null;
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				getActivity().finish();
				startActivity(new Intent(getActivity(), LoginActivity.class));
				getActivity().overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);

			} else {
				Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private void logoutAlert() {

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
				getActivity());
		builderSingle.setTitle("Logout");
		builderSingle.setMessage("Are you sure?");
		builderSingle.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
						new LogoutTask().execute();
					}
				});
		builderSingle.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						getFragmentManager();
						getFragmentManager().popBackStack("logout",
								FragmentManager.POP_BACK_STACK_INCLUSIVE);
					}
				});
		builderSingle.show();

	}

}
