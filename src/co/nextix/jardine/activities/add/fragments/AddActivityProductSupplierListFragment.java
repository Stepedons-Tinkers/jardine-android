package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;
import java.util.List;

import com.dd.CircularProgressButton;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.activites.fragments.adapters.AddProductSupplierCustomAdapter;
import co.nextix.jardine.activites.fragments.adapters.JDIProductStockCheckCustomAdapter;
import co.nextix.jardine.activites.fragments.adapters.ProductSupplierCustomAdapter;
import co.nextix.jardine.activites.fragments.detail.JDIMerchandisingCheckDetailFragment;
import co.nextix.jardine.activites.fragments.detail.JDIProductStockCheckDetailFragment;
import co.nextix.jardine.activites.fragments.detail.ProductSupplierDetailFragment;
import co.nextix.jardine.activities.add.fragments.AddJDIProductStockFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.JDImerchandisingCheckTable;
import co.nextix.jardine.database.tables.JDIproductStockCheckTable;
import co.nextix.jardine.database.tables.ProductSupplierTable;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class AddActivityProductSupplierListFragment extends Fragment {
	private ArrayList<ProductSupplierRecord> realRecord = null;
	private ArrayList<ProductSupplierRecord> tempRecord = null;
	private AddProductSupplierCustomAdapter adapter = null;

	private Context CustomListView = null;
	private View myFragmentView = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;

	private Bundle bundle;
	private int frag_layout_id;
	
	private boolean flag = false;

	private ActivityRecord activityRecord = null;
	private SharedPreferences pref = null;

	AddActivityProductSupplierFragment fragment = new AddActivityProductSupplierFragment();

	public void populate() {
		adapter.notifyDataSetChanged();
	}

	public void removeFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.myFragmentView = inflater.inflate(
				R.layout.fragment_activity_add_product_supplier_list,
				container, false);
		this.setListData();

		bundle = new Bundle();

		frag_layout_id = R.id.layoutForAddingFrag;
		final AddActivityProductSupplierListFragment frag = this;

		Log.e("jdi", " size");
		((Button) myFragmentView
				.findViewById(R.id.add_btn_jdi_product_stock_check))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (Constant.addProductSupplierRecords.size() < 5) {
							v.getBackground()
									.setColorFilter(
											new LightingColorFilter(0x0033FF,
													0x0066FF));

							fragment.setTargetFragment(
									AddActivityProductSupplierListFragment.this,
									12345);

							FragmentTransaction transaction = frag
									.getChildFragmentManager()
									.beginTransaction()
									.setTransition(
											FragmentTransaction.TRANSIT_FRAGMENT_FADE);
							transaction.add(R.id.fake_layout, fragment);
							transaction.addToBackStack(JardineApp.TAG);
							transaction.commit();
						} else {
							Toast.makeText(
									getActivity(),
									"Cannot Add More than five Product Suppliers",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		
		((CircularProgressButton) myFragmentView.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

						@Override
						public void onAnimationUpdate(ValueAnimator animation) {

							Integer value = (Integer) animation.getAnimatedValue();
							((CircularProgressButton) v).setProgress(value);

							if (!flag) {
								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					
					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (Constant.addProductSupplierRecords.size() != 0) {
						
						flag = true;
						
						v.setClickable(true);
						v.setEnabled(true);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please add at least one Product Supplier", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(-1);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
						
						
					}

				} else {
					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);

					if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retails
						DashBoardActivity.tabIndex.add(5, 8);
						AddActivityFragment.pager.setCurrentItem(8);
					}
//					getFragmentManager().popBackStackImmediate();

				}
			}
		});

//		((ImageButton) myFragmentView.findViewById(R.id.imageButton1))
//				.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						// Toast.makeText(getActivity(), "<==== ni sud here",
//						// Toast.LENGTH_SHORT).show();
//						if (currentPage > 0) {
//							currentPage--;
//							addItem(currentPage);
//						}
//					}
//				});
//
//		((ImageButton) myFragmentView.findViewById(R.id.imageButton3))
//				.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						// Toast.makeText(getActivity(), "ni sud here ====>",
//						// Toast.LENGTH_SHORT).show();
//						if (currentPage < totalPage - 1) {
//							currentPage++;
//							addItem(currentPage);
//						}
//					}
//				});

		return myFragmentView;
	}

	/****** Function to set data in ArrayList *************/
	public void setListData() {
		this.realRecord = new ArrayList<ProductSupplierRecord>();
		this.tempRecord = new ArrayList<ProductSupplierRecord>();
		this.realRecord.addAll(Constant.addProductSupplierRecords);

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {

				}
			}
			this.totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		} else {

			this.setView();
			this.isListHasNoData();
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) this.myFragmentView.findViewById(R.id.status_count_text))
				.setText(temp + " of " + totalPage);

		int rows = rowSize;
		if (realRecord.size() < rows)
			rows = realRecord.size();
		for (int j = 0; j < rows; j++) {
			// for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}
		adapter.notifyDataSetChanged();
		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		this.list = (ListView) this.myFragmentView.findViewById(R.id.list);
		this.adapter = new AddProductSupplierCustomAdapter(this.CustomListView,
				getActivity(), this.tempRecord, this);
		this.list.setAdapter(adapter);
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	// Event item listener
	public void onItemClick(int mPosition) {
//		ProductSupplierRecord tempValues = (ProductSupplierRecord) this.tempRecord
//				.get(mPosition);

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

//		Fragment fragment = new ProductSupplierDetailFragment();
//		bundle.putLong("product_id", tempValues.getId());
//		fragment.setArguments(bundle);
//		FragmentManager fragmentManager = getActivity()
//				.getSupportFragmentManager();
//		fragmentManager
//				.beginTransaction()
//				.setCustomAnimations(R.anim.slide_in_left,
//						R.anim.slide_out_left)
//				.replace(frag_layout_id, fragment).addToBackStack(null)
//				.commit();

	}

	public void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) this.myFragmentView.findViewById(R.id.view_stub))
				.setVisibility(View.GONE);
		// ((TextView) this.myFragmentView.findViewById(R.id.status_list_view))
		// .setVisibility(View.VISIBLE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) this.myFragmentView.findViewById(R.id.view_stub))
				.setVisibility(View.VISIBLE);
		// ((TextView) this.myFragmentView.findViewById(R.id.status_list_view))
		// .setVisibility(View.INVISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}

	public void remove() {
		this.getChildFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.remove(fragment).addToBackStack(JardineApp.TAG).commit();
	}
}
