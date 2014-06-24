package co.nextix.jardine.contactperson.fragments;

import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactPersonGeneralInformation extends Fragment {
	private View view;
	private TextView crmNo, firstName, lastName, position, 
	mobileNo, birthday, customer, isActive;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		view = inflater.inflate(
				R.layout.contact_person_details, null);
		initLayout();
		return view;
	}
	
	private void initLayout() {
		crmNo = (TextView) view.findViewById(R.id.tvContactPersonCrmNoInfo);
		firstName = (TextView) view.findViewById(R.id.tvContactPersonFirstName);
		lastName = (TextView) view.findViewById(R.id.tvContactPersonLastNameInfo);
		position = (TextView) view.findViewById(R.id.tvContactPersonPositionInfo);
		mobileNo = (TextView) view.findViewById(R.id.tvContactPersonMobileNoInfo);
		birthday = (TextView) view.findViewById(R.id.tvContactPersonBirthdayInfo);
//		customer = (TextView) view.findViewById(R.id.tvContactPersonCustomerInfo);
//		isActive = (TextView) view.findViewById(R.id.tvContactPersonIsActiveInfo);
	
		CustomerContactRecord record = new CustomerContactRecord();
		record.setNo("EVP0001");
		record.setFirstName("John");
		record.setLastName("Doe");
		record.setPosition(0001);
		record.setMobileNo("0922-000-0000");
		record.setBirthday("12-06-1898");
//		record.setCustomer(001);
//		record.setIsActive(1);
		record.setCreatedTime("2014");
		record.setModifiedTime("2014");
		record.setUser(10000);
	
		crmNo.setText(record.getNo());
		firstName.setText(record.getFirstName());
		lastName.setText(String.valueOf(record.getLastName()));
		position.setText(String.valueOf(record.getPosition()));
		mobileNo.setText(String.valueOf(record.getMobileNo()));
		birthday.setText(String.valueOf(record.getBirthday()));
//		customer.setText(String.valueOf(record.getCustomer()));
//		isActive.setText(String.valueOf(record.getIsActive()));
	
	}
}
