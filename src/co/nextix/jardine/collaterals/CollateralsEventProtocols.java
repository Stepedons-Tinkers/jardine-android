package co.nextix.jardine.collaterals;

import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.customers.AdapterCustomers;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;
import co.nextix.jardine.database.tables.EventProtocolTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.view.group.utils.ListViewUtility;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CollateralsEventProtocols extends Fragment implements
		OnClickListener {

	private View view;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<EventProtocolRecord> realRecord;
	private List<EventProtocolRecord> tempRecord;
	private List<EventProtocolRecord> searchRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtDesc, txtEvent, txtIsActive;
	private TableRow trow;
	private SearchView searchView;
	private List<String> strSearcher;
	private Spinner spinner;
	private boolean searchMode = false;
	private long userId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		strSearcher = new ArrayList<String>();
		view = inflater.inflate(R.layout.collaterals_event_protocols, null);
		header = inflater
				.inflate(R.layout.collaterals_event_protocol_row, null);
		initLayout();
		return view;
	}

	private void initLayout() {

		searchView = (SearchView) view.findViewById(R.id.svEventProtocols);
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
				AdapterCollateralsEventProtocols adapter = new AdapterCollateralsEventProtocols(
						getActivity(), R.layout.collaterals_event_protocol_row,
						tempRecord);
				list.setAdapter(adapter);
				searchView.clearFocus();
				searchMode = true;
			}
		});
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextChange(String arg0) {

				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				currentPage = 0;
				try {
					searchRecord = JardineApp.DB.getEventProtocol()
							.getAllRecordsBySearch(userId, arg0,
									spinner.getSelectedItemPosition());

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
		// Header Data
		trow = (TableRow) header
				.findViewById(R.id.trCollateralsEventerProtocolRow);
		txtCrm = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolCrmNo);
		txtDesc = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolDescription);
		txtEvent = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolEventType);
		txtIsActive = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolIsActive);

		txtCrm.setText(getResources().getString(R.string.collaterals_ep_crm_no));
		txtDesc.setText(getResources().getString(
				R.string.collaterals_ep_description));
		txtEvent.setText(getResources().getString(
				R.string.collaterals_ep_event_type));
		txtIsActive.setText(getResources().getString(
				R.string.collaterals_ep_is_active));
		trow.setBackgroundResource(R.color.tab_pressed);
		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);
		//

		strSearcher.add(getResources()
				.getString(R.string.collaterals_ep_crm_no));
		strSearcher.add(getResources().getString(
				R.string.collaterals_ep_description));
		strSearcher.add(getResources().getString(
				R.string.collaterals_ep_event_type));
		ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(getActivity(),
				R.layout.workplan_spinner_row, strSearcher);

		list = (ListView) view
				.findViewById(R.id.lvCollateralsEventProtocolsList);

		list.addHeaderView(header);

		txtPage = (TextView) view
				.findViewById(R.id.tvCollateralssEventProtocolPage);

		searchView = (SearchView) view.findViewById(R.id.svEventProtocols);

		spinner = (Spinner) view
				.findViewById(R.id.spiCollateralsEventProtolSpinnerSearch);
		spinner.setAdapter(sAdapter);

		arrowLeft = (ImageButton) view
				.findViewById(R.id.ibColatteralsEventProtocolLeft);
		arrowRight = (ImageButton) view
				.findViewById(R.id.ibColatteralsEventProtocolRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		EventProtocolTable table = JardineApp.DB.getEventProtocol();
		String id = StoreAccount.restore(getActivity())
				.getString(Account.ROWID);
		userId = Long.parseLong(id);

		realRecord = new ArrayList<EventProtocolRecord>();
		tempRecord = new ArrayList<EventProtocolRecord>();
		try {
			realRecord.addAll(table.getAllRecordsByUser(userId));
		} catch (Exception e) {
		}

		// for (int i = 1; i <= 37; i++) {
		// EventProtocolRecord rec = new EventProtocolRecord();
		// rec.setNo("EVP00" + i);
		// rec.setDescription("Description " + i);
		// rec.setEventType(i);
		// if (i % 2 == 0) {
		// rec.setIsActive(1);
		// } else {
		// rec.setIsActive(0);
		// }
		//
		// realRecord.add(rec);
		// }

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					EventProtocolRecord rec = new EventProtocolRecord();
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
					EventProtocolRecord rec = new EventProtocolRecord();
					searchRecord.add(rec);
				}
			}
			totalPage = realRecord.size() / rowSize;

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

		AdapterCollateralsEventProtocols adapter = new AdapterCollateralsEventProtocols(
				getActivity(), R.layout.collaterals_event_protocol_row,
				tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EventProtocolRecord epr = (EventProtocolRecord) parent
						.getAdapter().getItem(position);
				CollateralsConstants.FROM_WHERE = 1;
				if (epr.getNo() != null) {

					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.frame_container,
									CollateralsDetails.newInstance(epr.getId(),
											epr.getNo()), JardineApp.TAG)
							.addToBackStack(JardineApp.TAG).commit();
				}

			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ibColatteralsEventProtocolLeft:
			if (currentPage > 0) {
				currentPage--;
				if (searchMode)
					addItemFromSearch(currentPage);
				else
					addItem(currentPage);
			}
			break;
		case R.id.ibColatteralsEventProtocolRight:
			if (currentPage < totalPage - 1) {
				currentPage++;
				if (searchMode)
					addItemFromSearch(currentPage);
				else
					addItem(currentPage);
			}
			break;
		}

	}
}
