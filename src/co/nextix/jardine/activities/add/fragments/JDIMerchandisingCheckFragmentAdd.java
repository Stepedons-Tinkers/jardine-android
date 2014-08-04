package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.activites.fragments.JDIMerchandisingCheckFragment;
import co.nextix.jardine.activites.fragments.detail.JDIMerchandisingCheckDetailFragment;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.keys.Constant;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.view.group.utils.ListViewUtility;

import com.dd.CircularProgressButton;

public class JDIMerchandisingCheckFragmentAdd extends Fragment {

	private JDIMerchandisingCheckCustomAdapterAdd adapter = null;
	private ArrayList<JDImerchandisingCheckRecord> realRecord = null;
	private ArrayList<JDImerchandisingCheckRecord> tempRecord = null;
	private ArrayList<JDImerchandisingCheckRecord> itemSearch = null;
	private Context CustomListView = null;
	private View view = null;
	private ListView list = null;

	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;

	private FragmentTransaction ft;
	private Fragment fragmentForTransition;
	private boolean flag = false;
	private int frag_layout_id = 0;
	private Bundle bundle;

	public JDIMerchandisingCheckFragmentAdd() {
		this.itemSearch = new ArrayList<JDImerchandisingCheckRecord>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/

		this.view = inflater.inflate(R.layout.fragment_activity_jdi_merchandising_check_add, container, false);
		setListData();

		bundle = getArguments();

		if (bundle != null) {
			frag_layout_id = bundle.getInt("layoutID");
		}

		bundle = new Bundle();

		frag_layout_id = ActivityInfoFragment.fragmentLayout_2id;

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
					if (Constant.addJDImerchandisingCheckRecords.size() > 0) {
						flag = true;
						v.setClickable(true);
						v.setEnabled(true);
					} else {
						flag = false;
						v.setClickable(true);
						v.setEnabled(true);
						Toast.makeText(getActivity().getApplicationContext(), "Can't proceed", Toast.LENGTH_SHORT).show();
						((CircularProgressButton) v).setProgress(0);
					}

				} else {

					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);

					if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retails
						DashBoardActivity.tabIndex.add(6, 9);
						AddActivityFragment.pager.setCurrentItem(9);
					}
				}
			}
		});

		((Button) this.view.findViewById(R.id.add_jdi_merchandising_check)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// v.getBackground().setColorFilter(new
				// LightingColorFilter(0x0033FF, 0x0066FF));

				if (Constant.addJDImerchandisingCheckRecords.size() < 5) {

					Fragment newFragment = new AddJDIMerchandisingStockFragment(JDIMerchandisingCheckFragmentAdd.this);

					// Create new transaction
					FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
							.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

					// Replace whatever is in the fragment_container view with
					// this
					// fragment,
					// and add the transaction to the back stack

					/**
					 * NATNAT KANI AKO PASABOT NGA PATABANG KO GE UNSA NI NGA
					 * MAWALA MAN NIG BALIK
					 */

					transaction.replace(R.id.fake_layout, newFragment);
					transaction.addToBackStack(null);

					// Commit the transaction
					transaction.commit();

				} else {
					Toast.makeText(getActivity().getApplicationContext(), "Can't add more than 5 items", Toast.LENGTH_SHORT).show();
				}
			}
		});

		((ImageButton) this.view.findViewById(R.id.left_arrow)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (currentPage > 0) {
					currentPage--;
					addItem(currentPage);
				}
			}
		});

		((ImageButton) this.view.findViewById(R.id.right_arrow)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (currentPage < totalPage - 1) {
					currentPage++;
					addItem(currentPage);
				}
			}
		});

		return this.view;
	}

	/****** Function to set data in ArrayList *************/
	public void setListData() {
		this.realRecord = new ArrayList<JDImerchandisingCheckRecord>();
		this.tempRecord = new ArrayList<JDImerchandisingCheckRecord>();

		// JDImerchandisingCheckTable table =
		// JardineApp.DB.getJDImerchandisingCheck();
		// List<JDImerchandisingCheckRecord> records = table.getAllRecords();

		if (Constant.addJDImerchandisingCheckRecords != null && Constant.addJDImerchandisingCheckRecords.size() > 0) {
			this.realRecord.addAll(Constant.addJDImerchandisingCheckRecords);

			if (realRecord.size() > 0) {
				int remainder = realRecord.size() % rowSize;
				if (remainder > 0) {
					for (int i = 0; i < rowSize - remainder; i++) {
						JDImerchandisingCheckRecord rec = new JDImerchandisingCheckRecord();
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
		this.adapter = new JDIMerchandisingCheckCustomAdapterAdd(this.CustomListView, getActivity(), list, this.tempRecord, this);
		this.list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	// Event item listener
	public void onItemClick(int mPosition) {
		JDImerchandisingCheckRecord tempValues = (JDImerchandisingCheckRecord) this.tempRecord.get(mPosition);

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

		// Fragment fragment = new JDIMerchandisingCheckDetailFragment();
		// bundle.putLong("merchandising_id", tempValues.getId());
		// fragment.setArguments(bundle);
		// FragmentManager fragmentManager =
		// getActivity().getSupportFragmentManager();
		// fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,
		// R.anim.slide_out_left)
		// .replace(R.id.layoutForAddingFrag,
		// fragment).addToBackStack(null).commit();
	}

	// TODO Protected before but was changed to public due to some issues
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
