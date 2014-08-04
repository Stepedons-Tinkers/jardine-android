package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;
import java.util.List;

import com.dd.CircularProgressButton;

import android.animation.ValueAnimator;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.activites.fragments.adapters.CompetitorProductStockCheckCustomAdapter;
import co.nextix.jardine.activites.fragments.detail.CompetitorProductStockCheckDetailFragment;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.tables.CompetitorProductStockCheckTable;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class CompetitorStockCheckFragmentAdd extends Fragment {

	private CompetitorProductStockCheckCustomAdapterAdd adapter = null;
	private ArrayList<CompetitorProductStockCheckRecord> realRecord = null;
	private ArrayList<CompetitorProductStockCheckRecord> tempRecord = null;
	private Context CustomListView = null;
	private View view = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;

	private Bundle bundle;
	private int frag_layout_id;
	private boolean flag = false;

	// private ActivityRecord activityRecord = null;
	// private SharedPreferences pref = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_activity_competitor_stock_check_add, container, false);
		setListData();

		bundle = getArguments();

		if (bundle != null) {
			frag_layout_id = bundle.getInt("layoutID");
		}

		bundle = new Bundle();

		frag_layout_id = ActivityInfoFragment.fragmentLayout_2id;

		// ONCLICK sa mga buttons sa fragment
		((Button) view.findViewById(R.id.add_competitor_product_stock_check)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// v.getBackground().setColorFilter(new
				// LightingColorFilter(0x0033FF, 0x0066FF));

				if (Constant.addCompetitorProductRecords.size() < 5) {
					Fragment newFragment = new AddCompetitorStockCheckFragment(CompetitorStockCheckFragmentAdd.this);

					// Create new transaction
					FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
							.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

					// Replace whatever is in the fragment_container view
					// with this
					// fragment,
					// and add the transaction to the back stack
					transaction.replace(R.id.layoutForAddingFrag, newFragment);
					transaction.addToBackStack(null);

					// Commit the transaction
					transaction.commit();
				} else {
					Toast.makeText(getActivity().getApplicationContext(), "Can't add more than 5 items", Toast.LENGTH_SHORT).show();
				}
			}
		});

		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

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
					if (Constant.addCompetitorProductRecords.size() > 0) {
						flag = true;
						v.setClickable(true);
						v.setEnabled(true);
					} else {
						flag = false;
						v.setClickable(true);
						v.setEnabled(true);

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);

							}
						}, 750);
					}

				} else {

					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);

					if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retails
						DashBoardActivity.tabIndex.add(7, 10);
						AddActivityFragment.pager.setCurrentItem(10);
					}
				}
			}
		});

		// // ONCLICK sa mga buttons sa fragment
		// ((Button)
		// myFragmentView.findViewById(R.id.add_competitor_product_stock_check)).setOnClickListener(new
		// OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF,
		// 0x0066FF));
		//
		// Fragment newFragment = new AddCompetitorStockCheckFragment();
		//
		// // Create new transaction
		// FragmentTransaction transaction =
		// getActivity().getSupportFragmentManager().beginTransaction()
		// .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
		//
		// // Replace whatever is in the fragment_container view
		// // with this
		// // fragment,
		// // and add the transaction to the back stack
		// transaction.replace(frag_layout_id, newFragment);
		// transaction.addToBackStack(null);
		//
		// // Commit the transaction
		// transaction.commit();
		// }
		// });

		((ImageButton) view.findViewById(R.id.left_arrow)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getActivity(), "<==== ni sud here",
				// Toast.LENGTH_SHORT).show();
				if (currentPage > 0) {
					currentPage--;
					addItem(currentPage);
				}
			}
		});

		((ImageButton) view.findViewById(R.id.right_arrow)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getActivity(), "ni sud here ====>",
				// Toast.LENGTH_SHORT).show();
				if (currentPage < totalPage - 1) {
					currentPage++;
					addItem(currentPage);
				}
			}
		});

		return view;
	}

	public void setListData() {
		this.realRecord = new ArrayList<CompetitorProductStockCheckRecord>();
		this.tempRecord = new ArrayList<CompetitorProductStockCheckRecord>();

		// CompetitorProductStockCheckTable table =
		// JardineApp.DB.getCompetitorProductStockCheck();
		// List<CompetitorProductStockCheckRecord> records =
		// table.getAllRecords();

		if (Constant.addCompetitorProductRecords != null && Constant.addCompetitorProductRecords.size() > 0) {
			this.realRecord.addAll(Constant.addCompetitorProductRecords);

			if (realRecord.size() > 0) {
				int remainder = realRecord.size() % rowSize;
				if (remainder > 0) {
					for (int i = 0; i < rowSize - remainder; i++) {
						CompetitorProductStockCheckRecord rec = new CompetitorProductStockCheckRecord();
						realRecord.add(rec);
					}
				}

				this.totalPage = realRecord.size() / rowSize;
				addItem(currentPage);

			} else {

				this.setView();
				this.isListHasNoData();
				((TextView) this.view.findViewById(R.id.status_list_view)).setText("No Data.");
			}
		} else {

			this.setView();
			this.isListHasNoData();
			((TextView) this.view.findViewById(R.id.status_list_view)).setText("No Data");
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) this.view.findViewById(R.id.status_count_text)).setText(temp + " of " + totalPage);

		int rows = rowSize;
		if (realRecord.size() < rows)
			rows = realRecord.size();
		for (int j = 0; j < rows; j++) {
			// for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		this.list = (ListView) this.view.findViewById(R.id.list);
		this.adapter = new CompetitorProductStockCheckCustomAdapterAdd(this.CustomListView, getActivity(), list, this.tempRecord, this);
		this.list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	public void onItemClick(int mPosition) {
		CompetitorProductStockCheckRecord tempValues = (CompetitorProductStockCheckRecord) this.tempRecord.get(mPosition);

		// SharedPreferences pref =
		// getActivity().getApplicationContext().getSharedPreferences("ActivityInfo",
		// 0);
		// Editor editor = pref.edit();
		// editor.putLong("activity_id", tempValues.getId());
		// editor.putString("crm_no", tempValues.getCrm());
		// editor.putString("latitude",
		// String.valueOf(tempValues.getLatitude()));
		// editor.putString("longitude",
		// String.valueOf(tempValues.getLongitude()));
		// editor.putString("notes", tempValues.getNotes());
		// editor.putString("competitor_activities",
		// "getCompetitorActivities()");
		// editor.putString("highlights", tempValues.getHighlights());
		// editor.putString("nextSteps", tempValues.getNextSteps());
		// editor.putString("follow_up_commitment_date",
		// tempValues.getFollowUpCommitmentDate());
		// editor.putString("activity_type",
		// String.valueOf(tempValues.getActivityType()));
		// editor.putString("others", "getOthers()");
		// editor.putString("business_unit", "getBusinessUnit()");
		// editor.putString("workplan_entry",
		// String.valueOf(tempValues.getWorkplanEntry()));
		// editor.putString("customer",
		// String.valueOf(tempValues.getCustomer()));
		// editor.putString("area", "getArea()");
		// editor.putString("province", "getProvince");
		// editor.putString("city_town", "getCityTown()");
		// editor.putString("first_time_visit",
		// String.valueOf(tempValues.getFirstTimeVisit()));
		// editor.putString("planned_visit",
		// String.valueOf(tempValues.getPlannedVisit()));
		// editor.putString("reason_remarks", "getReasonRemarks()");
		// editor.putString("details_admin_works", "getDetailsAdminWorks()");
		// editor.putString("source", "getSource()");
		// editor.putString("created_time", tempValues.getCreatedTime());
		// editor.putString(
		// "assigned_to",
		// String.valueOf(JardineApp.DB.getUser().getCurrentUser().getLastname()
		// + ", "
		// + JardineApp.DB.getUser().getCurrentUser().getFirstNameName()));
		//
		// editor.commit();

		// Fragment fragment = new CompetitorProductStockCheckDetailFragment();
		// bundle.putLong("product_id", tempValues.getId());
		// fragment.setArguments(bundle);
		// FragmentManager fragmentManager =
		// getActivity().getSupportFragmentManager();
		// fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,
		// R.anim.slide_out_left)
		// .replace(frag_layout_id, fragment).addToBackStack(null).commit();
	}

	public void isListHasNoData() {
		this.list.setVisibility(View.GONE);
		((View) this.view.findViewById(R.id.view_stub)).setVisibility(View.GONE);
		((TextView) this.view.findViewById(R.id.status_list_view)).setVisibility(View.VISIBLE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) this.view.findViewById(R.id.view_stub)).setVisibility(View.VISIBLE);
		((TextView) this.view.findViewById(R.id.status_list_view)).setVisibility(View.INVISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}
}
