package co.nextix.jardine.activites.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.JDIProductStockCheckCustomAdapter;
import co.nextix.jardine.activites.fragments.detail.JDIMerchandisingCheckDetailFragment;
import co.nextix.jardine.activites.fragments.detail.JDIProductStockCheckDetailFragment;
import co.nextix.jardine.activities.add.fragments.AddJDIProductStockFragment;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.tables.JDIproductStockCheckTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class JDIProductStockFragment extends Fragment {
	private ArrayList<JDIproductStockCheckRecord> realRecord = null;
	private ArrayList<JDIproductStockCheckRecord> tempRecord = null;
	private JDIProductStockCheckCustomAdapter adapter = null;

	private Context CustomListView = null;
	private View myFragmentView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;
	
	private Bundle bundle;
	private int frag_layout_id;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.myFragmentView = inflater.inflate(
				R.layout.fragment_activity_jdi_product_stock_check, container,
				false);
		this.setListData();
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}
		
		Log.e("jdi"," size");
		((Button) myFragmentView
				.findViewById(R.id.add_btn_jdi_product_stock_check))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						v.getBackground().setColorFilter(
								new LightingColorFilter(0x0033FF, 0x0066FF));

						android.support.v4.app.Fragment newFragment = new AddJDIProductStockFragment();

						// Create new transaction
						android.support.v4.app.FragmentTransaction transaction = getActivity()
								.getSupportFragmentManager()
								.beginTransaction()
								.setCustomAnimations(R.anim.slide_in_left,
										R.anim.slide_out_left);

						// Replace whatever is in the fragment_container view
						// with this
						// fragment,
						// and add the transaction to the back stack
						transaction
								.replace(frag_layout_id, newFragment);
						transaction.addToBackStack(null);

						// Commit the transaction
						transaction.commit();
					}
				});

		((ImageButton) myFragmentView.findViewById(R.id.imageButton1))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(getActivity(), "<==== ni sud here",
								Toast.LENGTH_SHORT).show();
						if (currentPage > 0) {
							currentPage--;
//							addItem(currentPage);
						}
					}
				});

		((ImageButton) myFragmentView.findViewById(R.id.imageButton3))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(getActivity(), "ni sud here ====>",
								Toast.LENGTH_SHORT).show();
						if (currentPage < totalPage - 1) {
							currentPage++;
//							addItem(currentPage);
						}
					}
				});

		return myFragmentView;
	}

	/****** Function to set data in ArrayList *************/
	public void setListData() {
		this.realRecord = new ArrayList<JDIproductStockCheckRecord>();
		this.tempRecord = new ArrayList<JDIproductStockCheckRecord>();

		JDIproductStockCheckTable table = JardineApp.DB
				.getJDIproductStockCheck();
		List<JDIproductStockCheckRecord> records = table.getAllRecords();
		this.realRecord.addAll(records);

		Log.d("Jardine",
				"JDIProductStockCheck" + String.valueOf(records.size()));

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					// realRecord.add(new JDIproductStockCheckRecord(i, "", "",
					// i,
					// i, i, i, i, i, "", "", i));
				}
			}

			this.totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		} else {

			this.setView();
			this.isListHasNoData();
			((TextView) this.myFragmentView.findViewById(R.id.status_list_view))
					.setText("The database is still empty. Wanna sync first?");
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) this.myFragmentView.findViewById(R.id.status_count_text))
				.setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		this.list = (ListView) this.myFragmentView.findViewById(R.id.list);
		this.adapter = new JDIProductStockCheckCustomAdapter(
				this.CustomListView, getActivity(), this.tempRecord, this);
		this.list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	// Event item listener
	public void onItemClick(int mPosition) {
		JDIproductStockCheckRecord tempValues = (JDIproductStockCheckRecord) this.tempRecord
				.get(mPosition);

		// JDIproductStockCheckRecord ar = (JDIproductStockCheckRecord)
		// this.list.getAdapter().getItem(mPosition);
		// if (ar.getNo() != null) {
		//
		// ActivitiesConstant.ACTIVITY_ID = ar.getId();
		// DashBoardActivity act = (DashBoardActivity) getActivity();
		// act.getSupportFragmentManager().beginTransaction()
		// .add(R.id.frame_container,
		// CustomerDetailsFragment.newInstance(ar.getId()), JardineApp.TAG)
		// .addToBackStack(JardineApp.TAG).commit();
		// }
		
		Fragment fragment = new JDIProductStockCheckDetailFragment();
		bundle.putInt("product_id", (int)tempValues.getId());
		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
				.replace(frag_layout_id, fragment).addToBackStack(null).commit();

	}

	public void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) this.myFragmentView.findViewById(R.id.view_stub))
				.setVisibility(View.GONE);
		((TextView) this.myFragmentView.findViewById(R.id.status_list_view))
				.setVisibility(View.VISIBLE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) this.myFragmentView.findViewById(R.id.view_stub))
				.setVisibility(View.VISIBLE);
		((TextView) this.myFragmentView.findViewById(R.id.status_list_view))
				.setVisibility(View.INVISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}
}
