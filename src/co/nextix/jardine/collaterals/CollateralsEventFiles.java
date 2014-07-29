package co.nextix.jardine.collaterals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CollateralsEventFiles extends Fragment implements OnClickListener {

	private View view;
	private ListView list;
	private int rowSize = 8;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<DocumentRecord> realRecord;
	private List<DocumentRecord> tempRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtDesc, txtIsActive;
	private TableRow trow;
	private Spinner spinner;
	private SearchView searchView;

	private String moduleID = "";

	public static CollateralsEventFiles newInstance(String crm) {
		CollateralsEventFiles fragment = new CollateralsEventFiles();
		Bundle b = new Bundle();
		b.putString(CollateralsConstants.KEY_ROW_ID, crm);
		fragment.setArguments(b);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		moduleID = getArguments().getString(CollateralsConstants.KEY_ROW_ID);
		view = inflater.inflate(R.layout.collaterals_marketing_materials, null);
		header = inflater.inflate(R.layout.collaterals_marketing_materials_row,
				null);
		// Toast.makeText(getActivity(), moduleID, Toast.LENGTH_SHORT).show();
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

		trow.setGravity(Gravity.CENTER);
		txtCrm.setTypeface(null, Typeface.BOLD);
		txtDesc.setTypeface(null, Typeface.BOLD);
		txtIsActive.setTypeface(null, Typeface.BOLD);

		txtCrm.setGravity(Gravity.CENTER);
		txtDesc.setGravity(Gravity.CENTER);
		txtIsActive.setGravity(Gravity.CENTER);

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
		ListViewUtility.setListViewHeightBasedOnChildren(list);

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
			realRecord.addAll(JardineApp.DB.getDocument().getAllByCrmNo(
					moduleID));
		} catch (Exception e) {
		}

		// Toast.makeText(
		// getActivity(),
		// JardineApp.DB.getDocument().getAllByCrmNo(moduleID).size() + "",
		// Toast.LENGTH_SHORT).show();

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

		} else {

			AdapterCollateralsFiles adapter = new AdapterCollateralsFiles(
					getActivity(),
					R.layout.collaterals_marketing_materials_row, realRecord);

			list.setAdapter(adapter);
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
				DocumentRecord epr = (DocumentRecord) parent.getAdapter()
						.getItem(position);

				if (epr.getNo() != null) {
					String[] imgT = CollateralsConstants.IMAGE_TYPE;
					String[] vidT = CollateralsConstants.VIDEO_TYPE;
					File theFile = new File(JardineApp.JARDINE_DIRECTORY + "/"
							+ epr.getModuleName(), epr.getFileName());
					if (theFile.exists()) {

						if (theFile.toString().endsWith(imgT[0])
								|| theFile.toString().endsWith(imgT[1])
								|| theFile.toString().endsWith(imgT[2])
								|| theFile.toString().endsWith(imgT[3])
								|| theFile.toString().endsWith(imgT[4])
								|| theFile.toString().endsWith(vidT[0])
								|| theFile.toString().endsWith(vidT[1])
								|| theFile.toString().endsWith(vidT[2])
								|| theFile.toString().endsWith(vidT[3])
								|| theFile.toString().endsWith(vidT[4])) {
							Intent intent = new Intent(getActivity(),
									CollateralsFileViewer.class);
							CollateralsConstants.FileRecord = epr;
							getActivity().startActivity(intent);
						} else {
							Intent newIntent = new Intent(
									android.content.Intent.ACTION_VIEW);

							MimeTypeMap myMime = MimeTypeMap.getSingleton();

							String mimeType = myMime
									.getMimeTypeFromExtension(fileExt(
											theFile.toString()).substring(1));
							newIntent.setDataAndType(Uri.fromFile(theFile),
									mimeType);
							newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							try {
								getActivity().startActivity(newIntent);
							} catch (android.content.ActivityNotFoundException e) {
								Toast.makeText(getActivity(),
										"No handler for this type of file.",
										Toast.LENGTH_SHORT).show();
							}

						}
					}

				}

			}
		});

		// list.setOnItemLongClickListener(longClick);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	private OnItemLongClickListener longClick = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			DocumentRecord epr = (DocumentRecord) parent.getAdapter().getItem(
					position);

			return true;
		}
	};

	private String fileExt(String url) {
		if (url.indexOf("?") > -1) {
			url = url.substring(0, url.indexOf("?"));
		}
		if (url.lastIndexOf(".") == -1) {
			return null;
		} else {
			String ext = url.substring(url.lastIndexOf("."));
			if (ext.indexOf("%") > -1) {
				ext = ext.substring(0, ext.indexOf("%"));
			}
			if (ext.indexOf("/") > -1) {
				ext = ext.substring(0, ext.indexOf("/"));
			}
			return ext.toLowerCase();

		}
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
