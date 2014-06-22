package co.nextix.jardine.customers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CustomerRecord;

public class CustomerGeneralInformation extends Fragment {
	private View view;
	private TextView crmNo, customerName, businessUnit, area, 
	province, cityOrTown, chainName, customerSize, streetAddress, 
	landline, fax, customerType, isActive;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(
				R.layout.customer_details, null);
		initLayout();
		return view;
	}

	private void initLayout() {
		crmNo = (TextView) view.findViewById(R.id.tvWorkPlanInfoCrmNo);
		customerName = (TextView) view.findViewById(R.id.tvCustomerName);
		businessUnit = (TextView) view.findViewById(R.id.tvCustomerBusinessUnitInfo);
		area = (TextView) view.findViewById(R.id.tvCustomerAreaInfo);
		province = (TextView) view.findViewById(R.id.tvCustomerProvinceInfo);
		cityOrTown = (TextView) view.findViewById(R.id.tvCustomerCityOrTownInfo);
		chainName = (TextView) view.findViewById(R.id.tvCustomerChainNameInfo);
		customerSize = (TextView) view.findViewById(R.id.tvCustomerSizeInfo);
		streetAddress = (TextView) view.findViewById(R.id.tvCustomerStreetAddressInfo);
		landline = (TextView) view.findViewById(R.id.tvCustomerLandlineInfo);
		fax = (TextView) view.findViewById(R.id.tvCustomerFaxInfo);
		customerType = (TextView) view.findViewById(R.id.tvCustomerTypeInfo);
		isActive = (TextView) view.findViewById(R.id.tvCustomerIsActiveInfo);

		CustomerRecord record = new CustomerRecord();
		record.setNo("EVP0001");
		record.setCustomerName("Customer");
		record.setBusinessUnit(0001);
		record.setArea(0001);
		record.setProvince(0001);
		record.setCityTown(0001);
		record.setChainName("Chain Name");
		record.setCustomerSize("Customer Size");
		record.setStreetAddress("Cebu City");
		record.setLandline("236-0000");
		record.setFax("253-0000");
		record.setCustomerType(001);
		record.setIsActive(1);
		record.setCreatedTime("2014");
		record.setModifiedTime("2014");
		record.setUser(10000);

		crmNo.setText(record.getNo());
		customerName.setText(record.getCustomerName());
		businessUnit.setText(String.valueOf(record.getBusinessUnit()));
		area.setText(String.valueOf(record.getArea()));
		province.setText(String.valueOf(record.getProvince()));
		cityOrTown.setText(String.valueOf(record.getCityTown()));
		chainName.setText(record.getChainName());
		customerSize.setText(record.getCustomerSize());
		streetAddress.setText(record.getStreetAddress());
		landline.setText(record.getLandline());
		fax.setText(record.getFax());
		customerType.setText(String.valueOf(record.getCustomerType()));
		isActive.setText(String.valueOf(record.getIsActive()));

	}
}
