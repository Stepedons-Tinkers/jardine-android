package co.nextix.jardine.activities.update.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.PhotosAndAttachmentsCustomAdapter;
import co.nextix.jardine.collaterals.CollateralsConstants;
import co.nextix.jardine.collaterals.CollateralsFileViewer;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.database.tables.DocumentTable;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class UpdatePhotosAndAttachmentsListFragment extends Fragment {

	private View view;

	private boolean flag = false;

	private Button bDone;
	private Button bAdd;

	private ArrayList<DocumentRecord> realRecord = null;
	private ArrayList<DocumentRecord> tempRecord = null;
	private PhotosAndAttachmentsCustomAdapter adapter = null;

	private Context CustomListView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_photos_and_attachments_list,
				container, false);

		bDone = (Button) view.findViewById(R.id.done);
		bDone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
			}
		});

		bAdd = (Button) view.findViewById(R.id.add);
		bAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fragment = new UpdateAddPhotosAndAttachmentsFragment();
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.update_add_photos_fake_layout, fragment);
				ft.addToBackStack("to_add_photos");
				ft.commit();
			}
		});

		return view;
	}

	public void setListData() {
		realRecord = new ArrayList<DocumentRecord>();
		tempRecord = new ArrayList<DocumentRecord>();

		if (realRecord.size() > 0) {
			totalPage = realRecord.size() / rowSize;
			addItem(currentPage);
		} else {
			setView();
			isListHasNoData();
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) view.findViewById(R.id.status_count_text)).setText(temp
				+ " of " + totalPage);

		int rows = rowSize;
		if (realRecord.size() < rows)
			rows = realRecord.size();
		for (int j = 0; j < rows; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		CustomListView = getActivity().getApplicationContext();
		list = (ListView) view.findViewById(R.id.list);
		adapter = new PhotosAndAttachmentsCustomAdapter(CustomListView,
				getActivity(), tempRecord, this);
		list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	// Event item listener
	public void onItemClick(int mPosition) {
		DocumentRecord tempValues = (DocumentRecord) tempRecord.get(mPosition);

		DocumentRecord epr = (DocumentRecord) tempRecord.get(mPosition);

		if (epr.getNo() != null) {

			Intent intent = new Intent(getActivity(),
					CollateralsFileViewer.class);
			CollateralsConstants.FileRecord = epr;
			getActivity().startActivity(intent);
		}

	}

	public void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) view.findViewById(R.id.view_stub)).setVisibility(View.GONE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) view.findViewById(R.id.view_stub)).setVisibility(View.VISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}
}
