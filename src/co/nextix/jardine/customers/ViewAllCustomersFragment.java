package co.nextix.jardine.customers;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class ViewAllCustomersFragment extends Fragment implements
		OnClickListener {
	private View view;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<CustomerRecord> realRecord;
	private List<CustomerRecord> tempRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtCustomerName, txtBusinessUnit, txtArea,
			txtProvince, txtCityOrTown;
	private TableRow tablerow;
	private EditText search;
	private Button btnAddCustomer;

	public ViewAllCustomersFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		view = inflater.inflate(R.layout.fragment_customers_view_all,
				container, false);
		header = inflater.inflate(R.layout.table_row_customers, null);

		initLayout();
		return view;
	}

	private void initLayout() {

		// Header Data
		tablerow = (TableRow) header.findViewById(R.id.trCustomersRow);
		txtCrm = (TextView) header.findViewById(R.id.tvCustomerCRMNo);
		txtCustomerName = (TextView) header.findViewById(R.id.tvCustomerName);
		txtBusinessUnit = (TextView) header.findViewById(R.id.tvBusinessUnit);
		txtArea = (TextView) header.findViewById(R.id.tvArea);
		txtProvince = (TextView) header.findViewById(R.id.tvProvince);
		txtCityOrTown = (TextView) header.findViewById(R.id.tvCityOrTown);

		txtCrm.setText(getResources().getString(R.string.customer_crm_no));
		txtCustomerName.setText(getResources()
				.getString(R.string.customer_name));
		txtBusinessUnit.setText(getResources().getString(
				R.string.customer_business_unit));
		txtArea.setText(getResources().getString(R.string.customer_area));
		txtProvince.setText(getResources()
				.getString(R.string.customer_province));
		txtCityOrTown.setText(getResources().getString(
				R.string.customer_city_or_town));
		tablerow.setBackgroundResource(R.color.tab_pressed);
		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);

		list = (ListView) view.findViewById(R.id.lvCustomers);

		list.addHeaderView(header);
		ListViewUtility.setListViewHeightBasedOnChildren(list);

		btnAddCustomer = (Button) view.findViewById(R.id.btnAddCustomer);
		txtPage = (TextView) view.findViewById(R.id.ibCustomersPage);

		arrowLeft = (ImageButton) view.findViewById(R.id.ibCustomersLeft);
		arrowRight = (ImageButton) view.findViewById(R.id.ibCustomersRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);
		btnAddCustomer.setOnClickListener(this);

		CustomerTable table = JardineApp.DB.getCustomer();
		realRecord = new ArrayList<CustomerRecord>();
		tempRecord = new ArrayList<CustomerRecord>();
		realRecord.addAll(table.getAllRecords());

		// for (int i = 1; i <= 37; i++) {
		// CustomerRecord rec = new CustomerRecord();
		// rec.setNo("CUST000" + i);
		// rec.setCustomerName("Customer " + i);
		// rec.setBusinessUnit(i);
		// rec.setArea(i);
		// rec.setProvince(i);
		// rec.setCityTown(i);
		//
		// realRecord.add(rec);
		// }

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					CustomerRecord rec = new CustomerRecord();
					realRecord.add(rec);
				}
			}
			totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		}

	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		txtPage.setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		setView();
	}

	private void setView() {

		AdapterCustomers adapter = new AdapterCustomers(getActivity(),
				R.layout.table_row_customers, tempRecord);
		list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CustomerRecord cr = (CustomerRecord) parent.getAdapter()
						.getItem(position);

				if (cr.getNo() != null) {
					
					CustomerConstants.CUSTOMER_NAME = cr.getCustomerName();
					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.frame_container,
									CustomerDetailsFragment.newInstance(cr
											.getId()), JardineApp.TAG)
							.addToBackStack(JardineApp.TAG).commit();
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibCustomersLeft:
			if (currentPage > 0) {
				currentPage--;
				addItem(currentPage);
			}
			break;
		case R.id.ibCustomersRight:
			if (currentPage < totalPage - 1) {
				currentPage++;
				addItem(currentPage);
			}
			break;
		case R.id.btnAddCustomer:
			// add customer here
			Intent intent = new Intent(JardineApp.context,
					AddCustomer.class);
			startActivity(intent);
			break;
		}
	}

}
