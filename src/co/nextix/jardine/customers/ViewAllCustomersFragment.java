package co.nextix.jardine.customers;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddActivityDetailsAndNotesFragment;
import co.nextix.jardine.collaterals.AdapterCollateralsMarketingMaterials;
import co.nextix.jardine.collaterals.CustomSpinnerAdapter;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class ViewAllCustomersFragment extends Fragment implements
		OnClickListener {
	private View view;
	private ListView list;
	private int rowSize = 10;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<CustomerRecord> realRecord;
	private List<CustomerRecord> tempRecord;
	private List<CustomerRecord> searchRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtCustomerName, txtBusinessUnit, txtArea,
			txtProvince, txtCityOrTown;
	private TableRow tablerow;
	private EditText search;
	private Button btnAddCustomer;
	private Spinner spinSearch;
	private List<String> strSearcher;
	private SearchView searchView;
	private boolean searchMode = false;

	public ViewAllCustomersFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		view = inflater.inflate(R.layout.customer_view_all, container, false);
		header = inflater.inflate(R.layout.table_row_customers, null);

		initLayout();
		return view;
	}

	private void initLayout() {

		AddActivityDetailsAndNotesFragment.fromOther = false;
		// Header Data
		tablerow = (TableRow) header.findViewById(R.id.trCustomersRow);
		txtCrm = (TextView) header.findViewById(R.id.tvCustomerCRMNo);
		txtCustomerName = (TextView) header.findViewById(R.id.tvCustomerName);
		txtBusinessUnit = (TextView) header.findViewById(R.id.tvBusinessUnit);
		txtArea = (TextView) header.findViewById(R.id.tvArea);
		txtProvince = (TextView) header.findViewById(R.id.tvProvince);
		txtCityOrTown = (TextView) header.findViewById(R.id.tvCityOrTown);

		tablerow.setGravity(Gravity.CENTER);
		txtCrm.setGravity(Gravity.CENTER);
		txtCustomerName.setGravity(Gravity.CENTER);
		txtBusinessUnit.setGravity(Gravity.CENTER);
		txtArea.setGravity(Gravity.CENTER);
		txtProvince.setGravity(Gravity.CENTER);
		txtCityOrTown.setGravity(Gravity.CENTER);

		txtCrm.setTypeface(null, Typeface.BOLD);
		txtCustomerName.setTypeface(null, Typeface.BOLD);
		txtBusinessUnit.setTypeface(null, Typeface.BOLD);
		txtArea.setTypeface(null, Typeface.BOLD);
		txtProvince.setTypeface(null, Typeface.BOLD);
		txtCityOrTown.setTypeface(null, Typeface.BOLD);

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
		txtPage = (TextView) view.findViewById(R.id.tvCustomersPage);

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

	private void addItemFromSearch(int count) {

		if (searchRecord.size() > 0) {
			int remainder = searchRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					CustomerRecord rec = new CustomerRecord();
					searchRecord.add(rec);
				}
			}
			totalPage = searchRecord.size() / rowSize;

		}

		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		txtPage.setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, searchRecord.get(count));
			count = count + 1;
		}

		setView();
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

					CustomerDetailsFragment fragment = CustomerDetailsFragment
							.newInstance(cr.getId());
					fragment.setTargetFragment(ViewAllCustomersFragment.this,
							15);

					act.getSupportFragmentManager()
							.beginTransaction()
							.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
							.add(R.id.frame_container, fragment, JardineApp.TAG)
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
				if (searchMode)
					addItemFromSearch(currentPage);
				else
					addItem(currentPage);
			}
			break;
		case R.id.ibCustomersRight:
			if (currentPage < totalPage - 1) {
				currentPage++;
				if (searchMode)
					addItemFromSearch(currentPage);
				else
					addItem(currentPage);
			}
			break;
		case R.id.btnAddCustomer:
			// add customer here
			// Intent intent = new Intent(JardineApp.context,
			// AddCustomer.class);
			// startActivity(intent);

			getActivity()
					.getSupportFragmentManager()
					.beginTransaction()
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					.replace(R.id.frame_container, new AddCustomerFragment(),
							JardineApp.TAG).addToBackStack(JardineApp.TAG)
					.commit();
			break;
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.customer_menu, menu);

		searchView = (SearchView) menu.findItem(R.id.itemCustomerSearch)
				.getActionView();
		spinSearch = (Spinner) menu.findItem(R.id.itemCustomerSpinner)
				.getActionView();

		strSearcher = new ArrayList<String>();

		strSearcher.add(getResources()
				.getString(R.string.collaterals_ep_crm_no));
		strSearcher.add(getResources().getString(R.string.customer));
		strSearcher.add(getResources().getString(
				R.string.customer_business_unit));
		strSearcher.add(getResources().getString(R.string.customer_area));
		strSearcher.add(getResources().getString(
				R.string.customer_header_province));
		strSearcher.add(getResources()
				.getString(R.string.customer_city_or_town));

		ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(getActivity(),
				R.layout.workplan_spinner_row, strSearcher);

		CustomSpinnerAdapter cus = new CustomSpinnerAdapter(getActivity(),
				R.layout.workplan_spinner_row, strSearcher);
		spinSearch.setAdapter(cus);

		searchView.setOnCloseListener(new OnCloseListener() {

			@Override
			public boolean onClose() {
				searchView.clearFocus();
				currentPage = 0;
				addItem(currentPage);
				searchView.onActionViewCollapsed();
				searchMode = false;
				return true;
			}
		});
		searchView.setOnSearchClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tempRecord.clear();
				AdapterCustomers adapter = new AdapterCustomers(getActivity(),
						R.layout.table_row_customers, tempRecord);
				list.setAdapter(adapter);
				searchView.clearFocus();
				searchMode = true;
			}
		});
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextChange(String arg0) {

				return true;
			}

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				currentPage = 0;
				try {
					searchRecord = JardineApp.DB.getCustomer()
							.getAllRecordsBySearch(arg0,
									spinSearch.getSelectedItemPosition());

					if (searchRecord.size() > 0)
						addItemFromSearch(currentPage);
					else
						Toast.makeText(getActivity(), "No records found!",
								Toast.LENGTH_SHORT).show();

				} catch (SQLiteException e) {

					Log.e("Tugs", e.toString());
				}

				searchView.clearFocus();
				return true;
			}

		});
	}

}
