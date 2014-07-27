package co.nextix.jardine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;
import co.nextix.jardine.utils.NetworkUtils;
import co.nextix.jardine.web.LogRequests;
import co.nextix.jardine.web.requesters.LoginModel;

public class LoginActivity extends Activity {

	EditText editUsername, editPassword;
	Spinner spinnArea;
	int areaChoice = 0;
	
	// CENTRAL LUZON AREA
	// NORTHEAST LUZON AREA
	// NORTHWEST LUZON AREA
	// SOUTHEAST LUZON
	// SOUTHWEST LUZON
	// CENTRAL NCR AREA
	// NORTHERN NCR AREA
	// SOUTHERN NCR AREA
	// CENTRAL VISAYAS
	// EASTERN VISAYAS
	// WESTERN VISAYAS
	// CENTRAL MINDANAO
	// NORTH WEST MINDANAO
	// SOUTH CENTRAL MINDANAO
	final String[] AREA_LIST = { "CENTRAL LUZON AREA", "NORTHEAST LUZON AREA",
			"NORTHWEST LUZON AREA", "SOUTHEAST LUZON", "SOUTHWEST LUZON",
			"CENTRAL NCR AREA", "NORTHERN NCR AREA", "SOUTHERN NCR AREA",
			"CENTRAL VISAYAS", "EASTERN VISAYAS", "WESTERN VISAYAS",
			"CENTRAL MINDANAO", "NORTH WEST MINDANAO", "SOUTH CENTRAL MINDANAO" };
	ArrayAdapter<String> spinnerAdapter;
	String SELECTED_AREA = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		DatabaseAdapter.init(this);
		JardineApp.DB = DatabaseAdapter.getInstance();
		JardineApp.DB.open();

		editUsername = (EditText) findViewById(R.id.login_email);
		editPassword = (EditText) findViewById(R.id.login_password);

		spinnArea = (Spinner) findViewById(R.id.login_spinner_area);
		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, AREA_LIST);
		SELECTED_AREA = AREA_LIST[0];

		spinnArea.setAdapter(spinnerAdapter);
		spinnArea.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				SELECTED_AREA = (String) parentView.getAdapter().getItem(
						position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				SELECTED_AREA = AREA_LIST[0];
			}

		});

		editUsername.setText("test_smr1");
		editPassword.setText("1212");

		if (StoreAccount.exists(getApplicationContext())) {
			Intent i = new Intent(getApplicationContext(),
					DashBoardActivity.class);
			i.putExtra("JUSTLOGGED", false);
			Log.w(JardineApp.TAG, "LoginActivity: justLoggedIn: false");
			finish();
			startActivity(i);
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

	private void showDialogButtonClick(final String[] choices) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				LoginActivity.this);
		builder.setTitle("Choose Area");

		// final CharSequence[] choiceList = { "Coke", "Pepsi", "Sprite",
		// "Seven Up" };

		builder.setSingleChoiceItems(choices, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// set to buffKey instead of selected
						// (when cancel not save to selected)
						// buffKey = which;
						areaChoice = which;
						Log.d(JardineApp.TAG, choices[which]);

					}
				})
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.d(JardineApp.TAG, "Selected " + choices[areaChoice]);
						long rowID = Long.parseLong(StoreAccount.restore(
								LoginActivity.this).getString(Account.ROWID));
						UserTable table = JardineApp.DB.getUser();
						table.updateLoggedArea(rowID, choices[areaChoice]);

						Intent i =new Intent(getApplicationContext(),
								DashBoardActivity.class);
						i.putExtra("JUSTLOGGED", true);
						Log.w(JardineApp.TAG, "LoginActivity: justLoggedIn: true");
						finish();
						startActivity(i);
						overridePendingTransition(R.anim.slide_in_left,
								R.anim.slide_out_left);

						dialog.dismiss();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

		AlertDialog alert = builder.create();
		alert.show();
	}

	private class LoginTask extends AsyncTask<Void, Void, Boolean> {
		ProgressDialog dialog;
		long rowid = 0;
//		String[] choiceList;

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
						editPassword.getText().toString(), SELECTED_AREA);
				if (model != null) {
					Log.i(JardineApp.TAG, "session: " + model.getSessionName());

					JardineApp.SESSION_NAME = model.getSessionName();
					
					
					UserTable userTable = JardineApp.DB.getUser();

					String areas = model.getDetails().getArea()
							.replace("|##|", ",");

					if (!userTable.isExisting(model.getUserId())) {
						rowid = userTable.insertUser(model.getUserId(),
								editUsername.getText().toString(), editPassword
										.getText().toString(), model
										.getDetails().getEmail(), model
										.getDetails().getLastName(), "", model
										.getDetails().getFirstName(), 1, 1, "",
								areas, SELECTED_AREA, MyDateUtils
										.getCurrentTimeStamp());
					} else {
						rowid = userTable.getByWebId(model.getUserId()).getId();
						userTable.updateLogStatus(rowid, 1);
						userTable.updateUser(rowid, model.getUserId(), model
								.getDetails().getUserName(), editPassword
								.getText().toString(), model.getDetails()
								.getEmail(), model.getDetails().getLastName(),
								"", model.getDetails().getFirstName(), areas,
								SELECTED_AREA, 1);
					}
					StoreAccount.save(getApplicationContext(), editUsername
							.getText().toString(), editPassword.getText()
							.toString(), model.getUserId(), String
							.valueOf(rowid), model.getSessionName(),
							SELECTED_AREA);

//					choiceList = areas.split("\\s*,\\s*");

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
				Intent i = new Intent(getApplicationContext(),
						DashBoardActivity.class);
				i.putExtra("JUSTLOGGED", true);
				Log.w(JardineApp.TAG, "LoginActivity: justLoggedIn: true");
				finish();
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);

				// if (choiceList != null)
				// showDialogButtonClick(choiceList);

				// if (models != null)
				// Toast.makeText(getApplicationContext(),
				// "Workplan: " + models.get(0).getCrmNo(),
				// Toast.LENGTH_LONG).show();

			} else {
				Toast.makeText(getApplicationContext(), "Connection Timeout",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
