package co.nextix.jardine;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.utils.NetworkUtils;
import co.nextix.jardine.web.LogRequests;
import co.nextix.jardine.web.models.WorkplanModel;
import co.nextix.jardine.web.requesters.LoginModel;

public class LoginActivity extends Activity {

	EditText editUsername, editPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		DatabaseAdapter.init(this);
		JardineApp.DB = DatabaseAdapter.getInstance();
		JardineApp.DB.open();

		editUsername = (EditText) findViewById(R.id.login_email);
		editPassword = (EditText) findViewById(R.id.login_password);
		
		editUsername.setText("test_smr1");
		editPassword.setText("1212");
		
		if (StoreAccount.exists(getApplicationContext())) {
			finish();
			startActivity(new Intent(getApplicationContext(),
					DashBoardActivity.class));
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_left);
		}
	}

	public void signInClicked(View view) {
		if (NetworkUtils.isNetworkAvailable(getApplicationContext()))
			new LoginTask().execute();

		/*
		 * login account details username: test_smr1 pass: 1212
		 */

		else
			Toast.makeText(getApplicationContext(),
					"Please check internet connection", Toast.LENGTH_LONG)
					.show();

		// startActivity(new Intent(getApplicationContext(),
		// DashBoardActivity.class));
		// overridePendingTransition(R.anim.slide_in_left,
		// R.anim.slide_out_left);

	}

	private class LoginTask extends AsyncTask<Void, Void, Boolean> {
		ProgressDialog dialog;
		List<WorkplanModel> models;
		long rowid = 0;

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

					JardineApp.SESSION_NAME = model.getSessionName();
					UserTable userTable = JardineApp.DB.getUser();
					if (!userTable.isExisting(model.getUserId())) {
						rowid = userTable.insertUser(model.getUserId(),
								editUsername.getText().toString(), editPassword
										.getText().toString(), model
										.getDetails().getEmail(), model
										.getDetails().getLastName(), "", model
										.getDetails().getFirstName(), 1, 1, "",
								model.getDetails().getArea(), MyDateUtils
										.getCurrentTimeStamp());
					} else {
						// rowid = Long.parseLong(StoreAccount.restore(
						// getApplicationContext()).getString(
						// Account.ROWID));
						rowid = userTable.getByWebId(model.getUserId()).getId();
						userTable.updateLogStatus(rowid, 1);
						userTable.updateUser(rowid, model.getUserId(), model
								.getDetails().getUserName(), editPassword
								.getText().toString(), model.getDetails()
								.getEmail(), model.getDetails().getLastName(),
								"", model.getDetails().getFirstName(), model
										.getDetails().getArea(), 1);
					}
					StoreAccount.save(getApplicationContext(), editUsername
							.getText().toString(), editPassword.getText()
							.toString(), model.getUserId(), String
							.valueOf(rowid), model.getSessionName());
					// RetrieveRequests retrieve = new RetrieveRequests();
					// models = retrieve.Workplan(new String[] { "422", "432"
					// });
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
				finish();
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
