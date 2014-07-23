package co.nextix.jardine.collaterals;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;
import co.nextix.jardine.database.tables.MarketingMaterialsTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class CollateralsMarketingMaterials extends Fragment implements
		OnClickListener {

	private View view;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<MarketingMaterialsRecord> realRecord;
	private List<MarketingMaterialsRecord> tempRecord;
	private List<MarketingMaterialsRecord> searchRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtDesc, txtIsActive;
	private TableRow trow;
	private SearchView searchView;

	private List<String> strSearcher;
	private Spinner spinner;
	private boolean searchMode = false;
	private long userId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		strSearcher = new ArrayList<String>();
		view = inflater.inflate(R.layout.collaterals_marketing_materials, null);
		header = inflater.inflate(R.layout.collaterals_marketing_materials_row,
				null);
		initLayout();
		return view;
	}

	private void initLayout() {

		// Header Data
		trow = (TableRow) header.findViewById(R.id.trCollateralsMMRow);
		txtCrm = (TextView) header.findViewById(R.id.tvCollateralsMMCrmNo);
		txtDesc = (TextView) header
				.findViewById(R.id.tvCollateralsMMDescription);
		txtIsActive = (TextView) header
				.findViewById(R.id.tvCollateralsMMIsActive);

		txtCrm.setText(getResources().getString(R.string.collaterals_ep_crm_no));
		txtDesc.setText(getResources().getString(
				R.string.collaterals_ep_description));
		txtIsActive.setText(getResources().getString(
				R.string.collaterals_ep_tags));
		trow.setBackgroundResource(R.color.tab_pressed);

		trow.setGravity(Gravity.CENTER);
		txtCrm.setTypeface(null, Typeface.BOLD);
		txtDesc.setTypeface(null, Typeface.BOLD);
		txtIsActive.setTypeface(null, Typeface.BOLD);

		txtCrm.setGravity(Gravity.CENTER);
		txtDesc.setGravity(Gravity.CENTER);
		txtIsActive.setGravity(Gravity.CENTER);

		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);
		//

		list = (ListView) view
				.findViewById(R.id.lvCollateralsMarketingMaterialsList);

		list.addHeaderView(header);

		txtPage = (TextView) view
				.findViewById(R.id.tvColatteralsMarketingMaterialsPage);
		arrowLeft = (ImageButton) view
				.findViewById(R.id.ibColatteralsMarketingMaterialsLeft);
		arrowRight = (ImageButton) view
				.findViewById(R.id.ibColatteralsMarketingMaterialsRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		MarketingMaterialsTable table = JardineApp.DB.getMarketingMaterials();

		realRecord = new ArrayList<MarketingMaterialsRecord>();
		tempRecord = new ArrayList<MarketingMaterialsRecord>();

		String id = StoreAccount.restore(getActivity())
				.getString(Account.ROWID);
		userId = Long.parseLong(id);
		realRecord.addAll(table.getAllRecordsByUser(userId));

		// for (int i = 1; i <= 89; i++) {
		// MarketingMaterialsRecord rec = new MarketingMaterialsRecord();
		// rec.setNo("EVP00" + i);
		// rec.setDescription("Description " + i);
		// rec.setTags("TAGS00" + i);
		//
		// realRecord.add(rec);
		// }

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					MarketingMaterialsRecord rec = new MarketingMaterialsRecord();
					realRecord.add(rec);
				}
			}
			totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		} else {
			AdapterCollateralsMarketingMaterials adapter = new AdapterCollateralsMarketingMaterials(
					getActivity(), R.layout.collaterals_event_protocol_row,
					realRecord);

			list.setAdapter(adapter);
		}
	}

	private void addItemFromSearch(int count) {

		if (searchRecord.size() > 0) {
			int remainder = searchRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					MarketingMaterialsRecord rec = new MarketingMaterialsRecord();
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

		AdapterCollateralsMarketingMaterials adapter = new AdapterCollateralsMarketingMaterials(
				getActivity(), R.layout.collaterals_marketing_materials_row,
				tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MarketingMaterialsRecord epr = (MarketingMaterialsRecord) parent
						.getAdapter().getItem(position);
				CollateralsConstants.FROM_WHERE = 2;
				if (epr.getNo() != null) {

					CollateralsDetails frag = CollateralsDetails.newInstance(
							epr.getId(), epr.getNo());
					frag.setTargetFragment(CollateralsMarketingMaterials.this,
							15);

					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager().beginTransaction()
							.add(R.id.frame_container, frag)
							.addToBackStack(JardineApp.TAG).commit();
				}

			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ibColatteralsMarketingMaterialsLeft:
			if (currentPage > 0) {
				currentPage--;
				currentPage++;
				if (searchMode)
					addItemFromSearch(currentPage);
				else
					addItem(currentPage);
			}
			break;
		case R.id.ibColatteralsMarketingMaterialsRight:
			if (currentPage < totalPage - 1) {
				currentPage++;
				currentPage++;
				if (searchMode)
					addItemFromSearch(currentPage);
				else
					addItem(currentPage);
			}
			break;
		}

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.collaterals_menu, menu);

		searchView = (SearchView) menu.findItem(R.id.itemCollateralSearch)
				.getActionView();
		spinner = (Spinner) menu.findItem(R.id.itemCollateralSpinner)
				.getActionView();

		strSearcher = new ArrayList<String>();

		strSearcher.add(getResources()
				.getString(R.string.collaterals_ep_crm_no));
		strSearcher.add(getResources().getString(
				R.string.collaterals_ep_description));
		strSearcher.add(getResources().getString(R.string.collaterals_ep_tags));
		ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(getActivity(),
				R.layout.workplan_spinner_row, strSearcher);

		CustomSpinnerAdapter cus = new CustomSpinnerAdapter(getActivity(),
				R.layout.workplan_spinner_row, strSearcher);
		spinner.setAdapter(cus);

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
				AdapterCollateralsMarketingMaterials adapter = new AdapterCollateralsMarketingMaterials(
						getActivity(),
						R.layout.collaterals_marketing_materials_row,
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
					searchRecord = JardineApp.DB.getMarketingMaterials()
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
	}
}
