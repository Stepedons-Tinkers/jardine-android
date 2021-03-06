package co.nextix.jardine.customers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;

public class CustomerGeneralInformation extends Fragment {
	private View view;
	private TextView crmNo, customerName, businessUnit, area, province,
			cityOrTown, chainName, customerSize, streetAddress, landline, fax,
			customerType, isActive;
	private Button edit, delete;
	private long customerId = 0;
	private CustomerRecord record;

	public static CustomerGeneralInformation newInstance(long custId) {
		CustomerGeneralInformation fragment = new CustomerGeneralInformation();
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

		view = inflater.inflate(R.layout.customer_geninfo, null);
		initLayout();
		return view;
	}

	public void initLayout() {
		crmNo = (TextView) view.findViewById(R.id.tvCustomerCrmNoInfo);
		customerName = (TextView) view.findViewById(R.id.tvCustomerNameInfo);
		businessUnit = (TextView) view
				.findViewById(R.id.tvCustomerBusinessUnitInfo);
		area = (TextView) view.findViewById(R.id.tvCustomerAreaInfo);
		province = (TextView) view.findViewById(R.id.tvCustomerProvinceInfo);
		cityOrTown = (TextView) view
				.findViewById(R.id.tvCustomerCityOrTownInfo);
		chainName = (TextView) view.findViewById(R.id.tvCustomerChainNameInfo);
		customerSize = (TextView) view.findViewById(R.id.tvCustomerSizeInfo);
		streetAddress = (TextView) view
				.findViewById(R.id.tvCustomerStreetAddressInfo);
		landline = (TextView) view.findViewById(R.id.tvCustomerLandlineInfo);
		fax = (TextView) view.findViewById(R.id.tvCustomerFaxInfo);
		customerType = (TextView) view.findViewById(R.id.tvCustomerTypeInfo);
		isActive = (TextView) view.findViewById(R.id.tvCustomerIsActiveInfo);

		edit = (Button) view.findViewById(R.id.btnEditCustomer);
		delete = (Button) view.findViewById(R.id.btnDeleteCustomer);

		edit.setOnClickListener(click);

		record = JardineApp.DB.getCustomer().getById(customerId);

		crmNo.setText(record.getCrm());
		customerName.setText(record.getCustomerName());

		BusinessUnitRecord business = JardineApp.DB.getBusinessUnit().getById(
				record.getBusinessUnit());
		if (business != null) {
			businessUnit.setText(business.getBusinessUnitName());
		} else {
			businessUnit.setText("");
		}
		PicklistRecord areaS = JardineApp.DB.getArea()
				.getById(record.getArea());
		area.setText(areaS.getName());

		ProvinceRecord prov = JardineApp.DB.getProvince().getById(
				record.getProvince());

		if (prov == null) {
			province.setText("");
		} else {
			province.setText(prov.getName());

			CityTownRecord city = JardineApp.DB.getCityTown().getById(
					record.getCityTown());
			if (city == null) {
				cityOrTown.setText("");
			} else {
				cityOrTown.setText(city.getName());
			}

		}

		chainName.setText(record.getChainName());
		PicklistRecord cSize = JardineApp.DB.getCustomerSize().getById(
				record.getCustomerSize());
		if (cSize == null) {
			customerSize.setText("");
		} else {
			customerSize.setText(cSize.getName());
		}

		streetAddress.setText(record.getStreetAddress());
		landline.setText(record.getLandline());
		fax.setText(record.getFax());

		PicklistRecord cType = JardineApp.DB.getCustomerType().getById(
				record.getCustomerType());

		if (cType != null)
			customerType.setText(cType.getName());

		if (record.getIsActive() == 1) {
			isActive.setText("Yes");

		} else {
			isActive.setText("No");
		}

		if (!record.getCrm().contentEquals("")) {
			delete.setEnabled(false);
			delete.setOnClickListener(null);
			delete.setClickable(false);
			delete.setBackgroundColor(Color.DKGRAY);
		} else {
			delete.setEnabled(true);
			delete.setOnClickListener(click);
			delete.setClickable(true);
			delete.setBackgroundColor(Color.RED);
		}

	}

	private View.OnClickListener click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnDeleteCustomer:
				showDeleteDialog();
				break;
			case R.id.btnEditCustomer:
				CustomerConstants.CUSTOMER_RECORD = record;
				// Intent intent = new Intent(getActivity(),
				// EditCustomer.class);
				// getActivity().startActivity(intent);

				EditCustomerFragment fragment = new EditCustomerFragment();
				fragment.setTargetFragment(CustomerGeneralInformation.this, 15);

				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.setTransition(
								FragmentTransaction.TRANSIT_FRAGMENT_FADE)
						.add(R.id.frame_container, fragment, JardineApp.TAG)
						.addToBackStack(JardineApp.TAG).commit();
				break;
			}

		}
	};

	private void showDeleteDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		dialog.setTitle("Delete Customer");
		dialog.setMessage("Are you sure you want to delete Customer?");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (JardineApp.DB.getCustomer().delete(customerId)) {
					Toast.makeText(getActivity(),
							"Successfully deleted customer", Toast.LENGTH_LONG)
							.show();

					getActivity()
							.getSupportFragmentManager()
							.beginTransaction()
							.setTransition(
									FragmentTransaction.TRANSIT_FRAGMENT_FADE)
							.replace(R.id.frame_container,
									new ViewAllCustomersFragment(),
									"customers-name").commit();

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
