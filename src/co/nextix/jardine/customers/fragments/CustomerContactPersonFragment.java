package co.nextix.jardine.customers.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.PicklistRecord;

public class CustomerContactPersonFragment extends Fragment {
	private View view;
	private TextView crmNo, firstName, lastName, mobileNo, email, position,
			birthday, isActive;
	private long customerId = 0;
	private Button edit, delete;

	public static CustomerContactPersonFragment newInstance(long custId) {
		CustomerContactPersonFragment fragment = new CustomerContactPersonFragment();
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

		view = inflater.inflate(R.layout.contact_person_details, container,
				false);
		initLayout();
		return view;
	}

	private void initLayout() {
		crmNo = (TextView) view.findViewById(R.id.tvContactPersonCrmNoInfo);
		firstName = (TextView) view
				.findViewById(R.id.tvContactPersonFirstNameInfo);
		lastName = (TextView) view
				.findViewById(R.id.tvContactPersonLastNameInfo);
		mobileNo = (TextView) view
				.findViewById(R.id.tvContactPersonMobileNoInfo);
		email = (TextView) view.findViewById(R.id.tvContactPersonEmailInfo);
		position = (TextView) view
				.findViewById(R.id.tvContactPersonPositionInfo);
		birthday = (TextView) view
				.findViewById(R.id.tvContactPersonBirthdayInfo);
		isActive = (TextView) view
				.findViewById(R.id.tvContactPersonIsActiveInfo);

		edit = (Button) view.findViewById(R.id.btnEditContactPerson);
		delete = (Button) view.findViewById(R.id.btnDeleteContactPerson);

		edit.setOnClickListener(click);
		delete.setOnClickListener(click);

		CustomerContactRecord record = JardineApp.DB.getCustomerContact()
				.getById(customerId);

		crmNo.setText(record.getNo());
		firstName.setText(record.getFirstName());
		lastName.setText(record.getLastName());
		mobileNo.setText(record.getMobileNo());
		email.setText(record.getEmailAddress());

		PicklistRecord cPos = JardineApp.DB.getCustomerContactPosition()
				.getById(record.getPosition());
		position.setText(cPos.getName());

		birthday.setText(record.getBirthday());

	}

	private View.OnClickListener click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnDeleteContactPerson:
				AlertDialog.Builder alert = new AlertDialog.Builder(
						JardineApp.context);
				alert.setTitle("Delete Customer");
				alert.setMessage("Are yout sure you want to delete the Customer?");
				alert.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				alert.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (JardineApp.DB.getCustomer().delete(
										customerId)) {
									Toast.makeText(JardineApp.context,
											"Successfully deleted Customer",
											Toast.LENGTH_LONG).show();
								} else {
									Toast.makeText(
											JardineApp.context,
											"Failed, please do the process again.",
											Toast.LENGTH_LONG).show();
								}

							}
						});

				AlertDialog dialog = alert.create();

				dialog.show();

				break;
			case R.id.btnEditContactPerson:

				break;
			}

		}
	};
}
