package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.JDImerchandisingCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class JDIMerchandisingCheckCustomAdapterAdd extends BaseAdapter {
	/*********** Declare Used Variables *********/
	private Context context;
	private FragmentActivity activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private JDImerchandisingCheckRecord tempValues = null;
	private View vi = null;
	private ListView listView = null;

	/************* CustomAdapter Constructor *****************/
	public JDIMerchandisingCheckCustomAdapterAdd(Context a, FragmentActivity act, ListView listView, ArrayList<?> d, Fragment fragment) {

		/********** Take passed values **********/
		this.context = a;
		this.activity = act;
		this.frag = fragment;
		this.listView = listView;
		this.data = d;

		/*********** Layout inflator to call external xml layout () **********************/
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	/******** What is the size of Passed Arraylist Size ************/
	public int getCount() {

		if (this.data.size() <= 0)
			return 1;
		return this.data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	/********* Create a holder to contain inflated xml file elements ***********/
	public static class ViewHolder {

		public LinearLayout clickable_item_container;
		public TextView crm_no_txt;
		public TextView activity_type_txt;
		public TextView product_txt;
		public TextView status_txt;
		public TextView created_by;
		public TextView edit_txt;
		public TextView delete_txt;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(final int position, final View convertView, ViewGroup parent) {

		this.vi = convertView;
		final int pos = position;
		final ViewHolder holder;
		JDIMerchandisingCheckFragmentAdd sct = (JDIMerchandisingCheckFragmentAdd) frag;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			this.vi = inflater.inflate(R.layout.table_row_items_five_columns, null);

			/******** View Holder Object to contain table_row_item.xml file elements ************/
			holder = new ViewHolder();
			holder.clickable_item_container = (LinearLayout) vi.findViewById(R.id.table_row_clickable);
			holder.crm_no_txt = (TextView) vi.findViewById(R.id.column_one);
			holder.activity_type_txt = (TextView) vi.findViewById(R.id.column_two);
			holder.product_txt = (TextView) vi.findViewById(R.id.column_three);
			holder.status_txt = (TextView) vi.findViewById(R.id.column_four);
			holder.created_by = (TextView) vi.findViewById(R.id.column_five);
			holder.edit_txt = (TextView) vi.findViewById(R.id.action_edit_txt);
			holder.delete_txt = (TextView) vi.findViewById(R.id.action_delete_txt);

			/************ Set holder with LayoutInflater ************/
			this.vi.setTag(holder);

		} else
			holder = (ViewHolder) vi.getTag();

		// Checking of the data gathered
		if (this.data.size() <= 0) {
			sct.isListHasNoData();

		} else {
			sct.isListHasData();

			/***** Get each Model object from Arraylist ********/
			this.tempValues = (JDImerchandisingCheckRecord) this.data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.crm_no_txt.setText(this.tempValues.getCrm());

			ActivityTable act = JardineApp.DB.getActivity();
			if (act != null) {
				ActivityRecord rec = act.getById(this.tempValues.getActivity());
				holder.activity_type_txt.setText("AUTO_GEN_ON_SAVE");
				if (rec != null) {
					holder.activity_type_txt.setText(rec.toString());
				}
			}

			ProductTable product = JardineApp.DB.getProduct();
			if (product != null) {
				ProductRecord rec = product.getById(this.tempValues.getProductBrand());
				holder.product_txt.setText("");
				if (rec != null) {
					holder.product_txt.setText(rec.toString());
				}
			}

			PJDIprodStatusTable status = JardineApp.DB.getJDIproductStatus();
			if (status != null) {
				PicklistRecord pick = status.getById((int) this.tempValues.getStatus());
				holder.status_txt.setText("");
				if (pick != null) {
					holder.status_txt.setText(pick.toString());
				}
			}

			UserTable user = JardineApp.DB.getUser();
			if (user != null) {
				UserRecord rec = user.getById(this.tempValues.getCreatedBy());
				holder.created_by.setText("");
				if (rec != null) {
					holder.created_by.setText(rec.toString());
				}
			}

			if (holder.crm_no_txt.getText().toString().equals("")) {
				holder.activity_type_txt.setText(null);
				holder.product_txt.setText(null);
				holder.status_txt.setText(null);
				holder.created_by.setText(null);
				holder.edit_txt.setText(null);
				holder.delete_txt.setText(null);
				holder.edit_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			} else {
				holder.clickable_item_container.setOnClickListener(new OnItemClickListener(pos));
			}

			/******** Set Item Click Listener for LayoutInflater for each row ***********/
			holder.edit_txt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(activity.getApplicationContext(), "Edit here", Toast.LENGTH_SHORT).show();
					// JDImerchandisingCheckRecord tempValues =
					// (JDImerchandisingCheckRecord) data.get(position);
					//
					// // Saving acquired activity details
					// SharedPreferences pref =
					// activity.getApplicationContext().getSharedPreferences("ActivityInfo",
					// 0);
					// Editor editor = pref.edit();
					// editor.putLong("activity_id", tempValues.getId());
					// editor.commit(); // commit changes
					//
					// android.support.v4.app.Fragment fragment = new
					// ActivityInfoFragment();
					// android.support.v4.app.FragmentManager fragmentManager =
					// activity.getSupportFragmentManager();
					// fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,
					// R.anim.slide_out_left)
					// .replace(R.id.frame_container,
					// fragment).addToBackStack(null).commit();
				}
			});

			holder.delete_txt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(activity.getApplicationContext(), "Delete here", Toast.LENGTH_SHORT).show();
					showDeleteDialog(position, listView);
				}
			});

		}

		return vi;
	}

	/********* Called when Item click in ListView ************/
	private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {
			JDIMerchandisingCheckFragmentAdd sct = (JDIMerchandisingCheckFragmentAdd) frag;
			sct.onItemClick(mPosition);
		}
	}

	private void showDeleteDialog(final int mPosition, final ListView list) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this.activity);
		dialog.setTitle("Delete Activity");
		dialog.setMessage("Are you sure you want to delete Activity?");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				JDImerchandisingCheckRecord tempValues = (JDImerchandisingCheckRecord) data.get(mPosition);
				if (JardineApp.DB.getActivity().delete(tempValues.getId())) {

					activity.runOnUiThread(new Runnable() {
						public void run() {
							JDIMerchandisingCheckFragmentAdd sct = (JDIMerchandisingCheckFragmentAdd) frag;
							sct.refreshListView();
						}
					});

					Toast.makeText(activity, "Successfully deleted activity", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(activity, "Failed to delete!", Toast.LENGTH_LONG).show();
				}
			}
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		dialog.show();
	}
}
