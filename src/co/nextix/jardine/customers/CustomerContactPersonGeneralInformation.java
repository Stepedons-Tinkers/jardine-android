package co.nextix.jardine.customers;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;

public class CustomerContactPersonGeneralInformation extends Fragment {
	private View view;

	private TextView field1, field2, field3, field4, field5, field6, field7,
			field8, field9, field10, field11, field12;
	private long customerId = 0;
	private Button edit, delete;
	private CustomerContactRecord record;

	public static CustomerContactPersonGeneralInformation newInstance(
			long custId) {
		CustomerContactPersonGeneralInformation fragment = new CustomerContactPersonGeneralInformation();
		Bundle bundle = new Bundle();
		bundle.putLong(CustomerConstants.KEY_CUSTOMER_LONG_ID, custId);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		customerId = getArguments().getLong(
				CustomerConstants.KEY_CUSTOMER_LONG_ID);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		view = inflater.inflate(R.layout.customer_contact_person_geninfo,
				container, false);
		initLayout();
		return view;
	}

	private void initLayout() {

		field1 = (TextView) view.findViewById(R.id.tvCustomerContactField1);
		field2 = (TextView) view.findViewById(R.id.tvCustomerContactField2);
		field3 = (TextView) view.findViewById(R.id.tvCustomerContactField3);
		field4 = (TextView) view.findViewById(R.id.tvCustomerContactField4);
		field5 = (TextView) view.findViewById(R.id.tvCustomerContactField5);
		field6 = (TextView) view.findViewById(R.id.tvCustomerContactField6);
		field7 = (TextView) view.findViewById(R.id.tvCustomerContactField7);
		field8 = (TextView) view.findViewById(R.id.tvCustomerContactField8);
		field9 = (TextView) view.findViewById(R.id.tvCustomerContactField9);
		field10 = (TextView) view.findViewById(R.id.tvCustomerContactField10);
		field11 = (TextView) view.findViewById(R.id.tvCustomerContactField11);
		field12 = (TextView) view.findViewById(R.id.tvCustomerContactField12);

		edit = (Button) view.findViewById(R.id.btnEditContactPerson);
		delete = (Button) view.findViewById(R.id.btnDeleteContactPerson);

		edit.setOnClickListener(click);
		delete.setOnClickListener(click);

//		record = JardineApp.DB.getCustomerContact().getById(customerId);
		record = CustomerConstants.CUSTOMER_CONTACT_RECORD;

		field1.setText(record.getCrm());
		field2.setText(record.getFirstName());
		field3.setText(record.getMobileNo());
		field4.setText(record.getEmailAddress());
		field5.setText(MyDateUtils.convertDateTime(record.getCreatedTime()));

		CustomerRecord cust = JardineApp.DB.getCustomer().getById(
				record.getCustomer());
		field6.setText(cust.getCustomerName());

		String username = StoreAccount.restore(JardineApp.context).getString(
				Account.USERNAME);
		UserTable u = DatabaseAdapter.getInstance().getUser();
		if (u != null) {
			UserRecord user = u.getCurrentUser();
			if (user != null) {
				field7.setText(user.getFirstNameName() + " "
						+ user.getLastname());
			}
		}

		PicklistRecord pos = JardineApp.DB.getCustomerContactPosition()
				.getById(record.getPosition());
		field8.setText(pos.getName());

		field9.setText(record.getLastName());
		field10.setText(MyDateUtils.convertDate(record.getBirthday()));

		if (record.getIsActive() == 1) {
			field11.setText("Yes");
		} else {
			field11.setText("No");
		}

		field12.setText(MyDateUtils.convertDateTime(record.getModifiedTime()));

	}

	private View.OnClickListener click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnDeleteContactPerson:

				showDeleteDialog();
				break;
			case R.id.btnEditContactPerson:
				CustomerConstants.CUSTOMER_CONTACT_RECORD = record;
//				Intent intent = new Intent(getActivity(),
//						EditCustomerContacts.class);
//				intent.putExtra(CustomerConstants.KEY_CUSTOMER_LONG_ID,
//						customerId);
//				getActivity().startActivity(intent);

				EditCustomerContactsFragment editFragment = new EditCustomerContactsFragment();
				
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.setTransition(
								FragmentTransaction.TRANSIT_FRAGMENT_FADE)
						.replace(R.id.frame_container, editFragment)
						.addToBackStack(null)
						.commit();

				break;
			}

		}
	};

	private void showDeleteDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		dialog.setTitle("Delete Customer Contact");
		dialog.setMessage("Are you sure you want to delete Customer Contact?");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (JardineApp.DB.getCustomerContact().delete(record.getId())) {
					Toast.makeText(getActivity(),
							"Successfully delete customer", Toast.LENGTH_LONG)
							.show();
					CustomerContactList frag = (CustomerContactList) getTargetFragment();
					frag.initLayout();
					getActivity().onBackPressed();
				} else {
					Toast.makeText(getActivity(), "Failed to delete!",
							Toast.LENGTH_LONG).show();
				}

			}
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		dialog.show();
	}
}
