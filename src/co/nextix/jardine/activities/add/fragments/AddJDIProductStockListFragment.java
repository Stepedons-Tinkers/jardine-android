package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.JDIProductStockCheckCustomAdapter;
import co.nextix.jardine.activites.fragments.adapters.JDIproductStockListAdapter;
import co.nextix.jardine.customers.CustomerDetailsFragment;
import co.nextix.jardine.customers.ViewAllCustomersFragment;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

import android.animation.ValueAnimator;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddJDIProductStockListFragment extends Fragment {

	private JDIproductStockListAdapter adapter = null;
	private Context CustomListView = null;
	private View myFragmentView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;
	private int frag_layout_id;

	private Button addButton, cancelButton;

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

		addButton.setVisibility(View.VISIBLE);
		cancelButton.setVisibility(View.VISIBLE);

		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DashBoardActivity act = (DashBoardActivity) getActivity();
				AddJDIProductStockFragment fragment = new AddJDIProductStockFragment();

				act.getSupportFragmentManager()
						.beginTransaction()
						.setTransition(
								FragmentTransaction.TRANSIT_FRAGMENT_FADE)
						.add(R.id.frame_container, fragment, JardineApp.TAG)
						.addToBackStack(JardineApp.TAG).commit();
			}

		});

		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO erase lists?
			}

		});

		return view;
	}

	@Override
	public void onStart() {
		if (Constant.addJDIproductStockCheckRecords != null) {
			adapter = new JDIproductStockListAdapter(getActivity(),
					R.layout.table_row_items_six_columns,
					Constant.addJDIproductStockCheckRecords);
			list.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
		super.onStart();
	}

}
