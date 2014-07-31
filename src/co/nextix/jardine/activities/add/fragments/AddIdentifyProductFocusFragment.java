package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;
import java.util.List;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.adapters.AddIdentifyProductFocusCustomAdapter;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;

import com.dd.CircularProgressButton;

public class AddIdentifyProductFocusFragment extends Fragment {

	private AddIdentifyProductFocusCustomAdapter adapter = null;
	private ArrayList<ProductRecord> realRecord = null;
	private ArrayList<ProductRecord> tempRecord = null;
	private ArrayList<ProductRecord> itemSearch = null;
	private Context CustomListView = null;
	private View view = null;
	private ListView list = null;
	private int rowSize = 5;
	private int totalPage = 0;
	private int currentPage = 0;

	public static ArrayList<ProductRecord> passValues;

	private Bundle bundle;
	private int frag_layout_id;
	private boolean flag = false;

	public AddIdentifyProductFocusFragment() {
		this.itemSearch = new ArrayList<ProductRecord>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		passValues = new ArrayList<ProductRecord>();
		view = inflater.inflate(R.layout.fragment_activity_add_identify_product_focus, container, false);
		setListData();

		bundle = getArguments();

		if (bundle != null) {
			frag_layout_id = bundle.getInt("layoutID");
		}

		// ONCLICK sa mga buttons sa fragment
		((Button) view.findViewById(R.id.select_products)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));

				android.support.v4.app.Fragment newFragment = new AddCompetitorStockCheckFragment();

				// Create new transaction
				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Replace whatever is in the fragment_container view with this
				// fragment,
				// and add the transaction to the back stack
				transaction.replace(frag_layout_id, newFragment);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
			}
		});

		((ImageButton) view.findViewById(R.id.imageButton1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentPage > 0) {
					currentPage--;
					addItem(currentPage);
				}
				// Toast.makeText(getActivity(), "<==== ni sud here",
				// Toast.LENGTH_SHORT).show();
			}
		});

		((ImageButton) view.findViewById(R.id.imageButton3)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentPage < totalPage - 1) {
					currentPage++;
					addItem(currentPage);
				}
				// Toast.makeText(getActivity(), "ni sud here ====>",
				// Toast.LENGTH_SHORT).show();
			}
		});

		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new View.OnClickListener() {

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
					final SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (passValues.size() > 1) {

						flag = true;
						Editor editor = pref.edit();
						// editor.putLong("identify_focus",
						// identifyProductFocus);
						editor.commit();

						v.setClickable(true);
						v.setEnabled(true);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please select at least 1", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);

					// insert then pop all backstack
				}

				if (AddActivityGeneralInformationFragment.ActivityType == 102) { // product
																					// focus
					DashBoardActivity.tabIndex.add(4, 16);
					AddActivityFragment.pager.setCurrentItem(16);
				}

				// String names = "";
				// for (int i = 0; i < passValues.size(); i++) {
				// names = names + passValues.get(i) + "\n";
				// }
				// Toast.makeText(getActivity(), names,
				// Toast.LENGTH_SHORT).show();
			}
		});

		return view;
	}

	public void setListData() {
		this.realRecord = new ArrayList<ProductRecord>();
		this.tempRecord = new ArrayList<ProductRecord>();

		ProductTable table = JardineApp.DB.getProduct();
		List<ProductRecord> records = table.getAllRecords();
		this.realRecord.addAll(records);

		Log.d("Jardine", "ActivityRecord" + String.valueOf(records.size()));

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					ProductRecord rec = new ProductRecord();
					realRecord.add(rec);
				}
			}

			this.totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		} else {

			this.setView();
			this.isListHasNoData();
			// ((TextView)
			// this.myFragmentView.findViewById(R.id.status_list_view)).setText("The database is still empty. Wanna sync first?");
		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		((TextView) this.view.findViewById(R.id.status_count_text)).setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		this.setView();
	}

	private void setView() {

		/**************** Create Custom Adapter *********/
		this.CustomListView = getActivity().getApplicationContext();
		this.list = (ListView) this.view.findViewById(R.id.list);

		this.adapter = new AddIdentifyProductFocusCustomAdapter(CustomListView, getActivity(), list, this.tempRecord, this);
		// this.adapter = new MarketingIntelCustomAdapter(this.CustomListView,
		// getActivity(), list, this.tempRecord, this);

		this.list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		this.list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// CheckBox check = (CheckBox)view.findViewById(R.id.checkbox1);
				// if(check.isChecked())
				// check.setChecked(true);
				// else
				// check.setChecked(false);
			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	public void onItemClick(int mPosition) {
		// ProductRecord tempValues = (ProductRecord)
		// this.tempRecord.get(mPosition);

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
		//
		// android.support.v4.app.Fragment fragment = new
		// ActivityInfoFragment();
		// android.support.v4.app.FragmentManager fragmentManager =
		// getActivity().getSupportFragmentManager();
		// fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,
		// R.anim.slide_out_left)
		// .replace(R.id.frame_container,
		// fragment).addToBackStack(null).commit();
		// Fragment fragment = new ProductFocusDetailFragment();
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
		// ((TextView)
		// this.myFragmentView.findViewById(R.id.status_list_view)).setVisibility(View.VISIBLE);
	}

	public void isListHasData() {
		this.list.setVisibility(View.VISIBLE);
		((View) this.view.findViewById(R.id.view_stub)).setVisibility(View.VISIBLE);
		// ((TextView)
		// this.myFragmentView.findViewById(R.id.status_list_view)).setVisibility(View.INVISIBLE);
	}

	public void refreshListView() {
		this.setListData();
	}
}
