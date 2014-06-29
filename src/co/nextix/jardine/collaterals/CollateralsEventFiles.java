package co.nextix.jardine.collaterals;

import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CollateralsEventFiles extends Fragment implements OnClickListener {

	private View view;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<DocumentRecord> realRecord;
	private List<DocumentRecord> tempRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtDesc, txtIsActive;
	private TableRow trow;

	private long moduleID = 0;

	public static CollateralsEventFiles newInstance(long id) {
		CollateralsEventFiles fragment = new CollateralsEventFiles();
		Bundle b = new Bundle();
		b.putLong(CollateralsConstants.KEY_ROW_ID, id);
		fragment.setArguments(b);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		moduleID = getArguments().getLong(CollateralsConstants.KEY_ROW_ID);
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

		txtCrm.setText(getResources()
				.getString(R.string.collaterals_file_title));
		txtDesc.setText(getResources().getString(R.string.collaterals_filename));
		txtIsActive.setText(getResources().getString(
				R.string.collaterals_file_modified_time));
		trow.setBackgroundResource(R.color.tab_pressed);
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

		realRecord = new ArrayList<DocumentRecord>();
		tempRecord = new ArrayList<DocumentRecord>();

		try {
			realRecord.addAll(JardineApp.DB.getDocument()
					.getAllRecordsByModuleID(moduleID));
		} catch (Exception e) {
		}

		Toast.makeText(getActivity(),
				JardineApp.DB.getDocument().getAllRecords().size() + "",
				Toast.LENGTH_SHORT).show();

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
					DocumentRecord rec = new DocumentRecord();
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

		AdapterCollateralsFiles adapter = new AdapterCollateralsFiles(
				getActivity(), R.layout.collaterals_marketing_materials_row,
				tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MarketingMaterialsRecord epr = (MarketingMaterialsRecord) parent
						.getAdapter().getItem(position);

				if (epr.getNo() != null) {

					Intent intent = new Intent(getActivity(),
							CollateralsFileViewer.class);
					getActivity().startActivity(intent);
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
				addItem(currentPage);
			}
			break;
		case R.id.ibColatteralsMarketingMaterialsRight:
			if (currentPage < totalPage - 1) {
				currentPage++;
				addItem(currentPage);
			}
			break;
		}

	}
}
