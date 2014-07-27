package co.nextix.jardine.customers;

import java.util.Calendar;
import java.util.List;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddCustomer extends Activity implements OnClickListener {

	private Button save, cancel;

	private TextView field1;
	private EditText field2, field3, field4, field5;
	private Spinner field6, field7;
	private Spinner field8, field9, field10, field11;
	private EditText field12;
	private TextView field13;
	private Spinner field14;

	private long userId;
	private String userName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_add_new);
		String id = StoreAccount.restore(JardineApp.context).getString(
				Account.ROWID);
		userName = StoreAccount.restore(JardineApp.context).getString(
				Account.USERNAME);
		userId = Long.parseLong(id);
		initLayout();
	}

	private void initLayout() {}

	public void insert() {

		Calendar c = Calendar.getInstance();

//		JardineApp.DB.getCustomer().insert("", "",
//				field2.getText().toString(), field4.getText().toString(),
//				field5.getText().toString(), field12.getText().toString(),
//				((PicklistRecord) field6.getSelectedItem()).getId(),
//				field3.getText().toString(),
//				((PicklistRecord) field7.getSelectedItem()).getId(),
//				((BusinessUnitRecord) field8.getSelectedItem()).getId(),
//				((PicklistRecord) field9.getSelectedItem()).getId(),
//				((ProvinceRecord) field10.getSelectedItem()).getId(),
//				((CityTownRecord) field11.getSelectedItem()).getId(), 1,
//				c.getTime().toString(), c.getTime().toString(), userId);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bCustomerAddCancel:
			finish();
			break;
		case R.id.bCustomerAddCreate:
			new InsertTask().execute();
			break;
		}
	}

	private class InsertTask extends AsyncTask<Void, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(AddCustomer.this);
			dialog.setMessage("Saving new Customer");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			boolean flag = false;
			try {
				insert();
				flag = true;
			} catch (Exception e) {
				flag = false;
				Log.e("Jardine", e.toString());
			}

			return flag;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				showMsg("Successfully added Customer");
			} else {
				showMsg("Something went wrong, failed to add customer");
			}
		}

	}

	private void showMsg(String txt) {
		Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
	}

}
