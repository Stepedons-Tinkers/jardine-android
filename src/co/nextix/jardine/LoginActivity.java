package co.nextix.jardine;

import java.util.List;

import co.nextix.jardine.utils.NetworkUtils;
import co.nextix.jardine.web.LogRequests;
import co.nextix.jardine.web.RetrieveRequests;
import co.nextix.jardine.web.models.WorkplanModel;
import co.nextix.jardine.web.requesters.LoginModel;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText editUsername, editPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		editUsername = (EditText) findViewById(R.id.login_email);
		editPassword = (EditText) findViewById(R.id.login_password);
	}

	public void signInClicked(View view) {
		// if (NetworkUtils.isNetworkAvailable(getApplicationContext()))
		// new LoginTask().execute();
		// else
		// Toast.makeText(getApplicationContext(),
		// "Please check internet connection", Toast.LENGTH_LONG)
		// .show();

		startActivity(new Intent(getApplicationContext(),
				DashBoardActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

	}

	private class LoginTask extends AsyncTask<Void, Void, Boolean> {
		ProgressDialog dialog;
		List<WorkplanModel> models;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(LoginActivity.this);
			dialog.setTitle("Signing in");
			dialog.setMessage("Verifying account. Please wait...");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				LogRequests log = new LogRequests();
				LoginModel model = log.login(editUsername.getText().toString(),
						editPassword.getText().toString());
				if (model != null) {
					Log.i(JardineApp.TAG, "session: " + model.getSessionName());

//					RetrieveRequests retrieve = new RetrieveRequests();
//					models = retrieve.Workplan(new String[] { "422", "432" });
					return true;
				} else {
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				startActivity(new Intent(getApplicationContext(),
						DashBoardActivity.class));
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				// if (models != null)
				// Toast.makeText(getApplicationContext(),
				// "Workplan: " + models.get(0).getCrmNo(),
				// Toast.LENGTH_LONG).show();

			} else {
				Toast.makeText(getApplicationContext(), "Invalid credentials",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
