package co.nextix.jardine.activities.add.fragments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.collaterals.AdapterCollateralsFiles;
import co.nextix.jardine.customers.AddCustomerContactsFragment2;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class ActivitiesDocumentList extends Fragment implements
		View.OnClickListener {

	private View view, header;
	private TextView col1, col2, col3, col4;
	private TableRow trow;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;

	private Button btnAdd, btnNext;

	private List<DocumentRecord> tempRecord;
	private List<DocumentRecord> realRecord;
	private AdapterCollateralsFiles finalAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(
				R.layout.activity_add_customer_contact_list_step1, container,
				false);
		header = inflater.inflate(R.layout.collaterals_marketing_materials_row,
				null, false);

		initLayout();
		return view;
	}

	private void initLayout() {
		// Header Data

		trow = (TableRow) header.findViewById(R.id.trCollateralsMMRowH);
		trow.setBackgroundResource(R.color.tab_pressed);

		col1 = (TextView) header.findViewById(R.id.tvCollateralsMMCol1);
		col2 = (TextView) header.findViewById(R.id.tvCollateralsMMCol2);
		col3 = (TextView) header.findViewById(R.id.tvCollateralsMMCol3);
		col4 = (TextView) header.findViewById(R.id.tvCollateralsMMCol4);

		trow.setGravity(Gravity.CENTER);
		col1.setGravity(Gravity.CENTER);
		col2.setGravity(Gravity.CENTER);
		col3.setGravity(Gravity.CENTER);
		col4.setGravity(Gravity.CENTER);

		col1.setText(getResources().getString(R.string.collaterals_file_title));
		col2.setText(getResources().getString(R.string.collaterals_filename));
		col3.setText(getResources().getString(
				R.string.collaterals_file_modified_time));
		col4.setText(getResources().getString(R.string.collaterals_file_type));

		col1.setTypeface(null, Typeface.BOLD);
		col2.setTypeface(null, Typeface.BOLD);
		col3.setTypeface(null, Typeface.BOLD);
		col4.setTypeface(null, Typeface.BOLD);

		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);
		//
		btnAdd = (Button) view.findViewById(R.id.bActivityAddCustomerContact);
		btnNext = (Button) view.findViewById(R.id.bActivityContactNext);
		btnNext.setOnClickListener(this);
		btnAdd.setOnClickListener(this);

		btnAdd.setText("Add Document Record");

		realRecord = new ArrayList<DocumentRecord>();
		tempRecord = new ArrayList<DocumentRecord>();

		finalAdapter = new AdapterCollateralsFiles(getActivity(),
				R.layout.collaterals_marketing_materials_row,
				ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST);

		list = (ListView) view.findViewById(R.id.lvActiviyCustomerContactList);

		txtPage = (TextView) view.findViewById(R.id.tvActivityCustomerPage);

		arrowLeft = (ImageButton) view
				.findViewById(R.id.ibActivityCustomerLeft);
		arrowRight = (ImageButton) view
				.findViewById(R.id.ibActivityCustomerRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		list.addHeaderView(header);
		list.setAdapter(finalAdapter);

	}

	public void populate() {

		finalAdapter.notifyDataSetChanged();
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);
		// if (menuVisible) {
		// populate();
		// }

	}

	public void removeFragment() {
		this.getChildFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.remove(fragment).addToBackStack(JardineApp.TAG).commit();
	}

	ActivitiesDocumentsAddNew fragment = new ActivitiesDocumentsAddNew();

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bActivityAddCustomerContact:
			fragment = new ActivitiesDocumentsAddNew();
			fragment.setTargetFragment(this, 15);
			this.getFragmentManager().beginTransaction()
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					.add(R.id.flActivityAddCustomerRootView, fragment)
					.addToBackStack(JardineApp.TAG).commit();
			break;
		case R.id.bActivityContactNext:

			break;
		}

	}

	// @Override
	// public void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// fragment.onActivityResult(requestCode, resultCode, data);
	// }

}
