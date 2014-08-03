package co.nextix.jardine.activities.add.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.JDIproductStockListAdapter;
import co.nextix.jardine.customers.AddCustomerContactsFragment2;
import co.nextix.jardine.keys.Constant;

public class AddJDIProductStockListFragment extends Fragment implements
		OnClickListener {

	private JDIproductStockListAdapter adapter = null;
	private Context CustomListView = null;
	private View myFragmentView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;
	private int frag_layout_id;

	private Button addButton, cancelButton, nextButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(
				R.layout.fragment_activity_jdi_product_stock_check, container,
				false);
		list = (ListView) view.findViewById(R.id.list);
		addButton = (Button) view.findViewById(R.id.jdiprodadd_button_add);
		cancelButton = (Button) view
				.findViewById(R.id.jdiprodadd_button_cancel);
		nextButton = (Button) view.findViewById(R.id.jdiprodadd_button_next);

		addButton.setVisibility(View.VISIBLE);
		cancelButton.setVisibility(View.VISIBLE);
		nextButton.setVisibility(View.VISIBLE);

		addButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		populate();
	}

	public void populate() {
		Log.e(JardineApp.TAG, "onStart: size "
				+ Constant.addJDIproductStockCheckRecords.size());
		if (Constant.addJDIproductStockCheckRecords != null) {
			adapter = new JDIproductStockListAdapter(getActivity(),
					R.layout.table_row_items_six_columns,
					Constant.addJDIproductStockCheckRecords);
			list.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}

	private OnBackStackChangedListener getListener() {
		OnBackStackChangedListener result = new OnBackStackChangedListener() {
			public void onBackStackChanged() {
				DashBoardActivity act = (DashBoardActivity) getActivity();
				FragmentManager manager = act.getSupportFragmentManager();

				if (manager != null) {
					int backStackEntryCount = manager.getBackStackEntryCount();
					if (backStackEntryCount == 0) {
						// finish();
					}
					Fragment fragment = manager.getFragments().get(
							backStackEntryCount - 1);
					fragment.onResume();
				}
			}
		};

		return result;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jdiprodadd_button_add:
			//
			// DashBoardActivity act = (DashBoardActivity) getActivity();
			// AddJDIProductStockFragment fragment = new
			// AddJDIProductStockFragment();
			//
			// FragmentManager frag = act.getSupportFragmentManager();
			// frag.addOnBackStackChangedListener(getListener());
			// frag.beginTransaction()
			// .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
			// .add(R.id.frame_container, fragment, JardineApp.TAG)
			// .addToBackStack(JardineApp.TAG).commit();
			AddJDIProductStockFragment fragment = new AddJDIProductStockFragment();
			fragment.setTargetFragment(AddJDIProductStockListFragment.this,
					987654);

			// this.getChildFragmentManager().beginTransaction()
			// .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
			// .attach(fragment)
			// .addToBackStack(JardineApp.TAG).commit();

			this.getChildFragmentManager().beginTransaction()
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					.add(R.id.jdiprodadd_frame_fakelayout, fragment)
					.addToBackStack(JardineApp.TAG).commit();
			break;
		case R.id.jdiprodadd_button_next:
			if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retails

				DashBoardActivity.tabIndex.add(4, 7);
				AddActivityFragment.pager.setCurrentItem(7);
			}
			break;

		case R.id.jdiprodadd_button_cancel:
			//
			// TODO erase lists?
			break;
		}
	}

	public void removeFragment() {
		AddJDIProductStockFragment fragment = new AddJDIProductStockFragment();
		this.getChildFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.remove(fragment).addToBackStack(JardineApp.TAG).commit();
	}

}
