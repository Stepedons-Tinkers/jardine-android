package co.nextix.jardine.activites.fragments.adapters;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.JDIProductStockFragment;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class JDIProductStockCheckCustomAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Context context;
	private FragmentActivity activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private JDIproductStockCheckRecord tempValues = null;
	private View vi = null;

	/************* CustomAdapter Constructor *****************/
	public JDIProductStockCheckCustomAdapter(Context a, FragmentActivity act, ArrayList<?> d, Fragment fragment) {

		/********** Take passed values **********/
		this.context = a;
		this.activity = act;
		this.frag = fragment;
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
		
//		public LinearLayout 
		public TextView crm_no_txt;
		public TextView activity_txt;
		public TextView product_brand_txt;
		public TextView stock_status_txt;
		public TextView loaded_on_shelves_txt;
		public TextView created_by_txt;
		public TextView action_txt;
		public TextView edit_txt;
		public TextView delete_txt;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(final int position, final View convertView, ViewGroup parent) {

		this.vi = convertView;
		final int pos = position;
		final ViewHolder holder;
		JDIProductStockFragment sct = (JDIProductStockFragment) frag;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			this.vi = inflater.inflate(R.layout.table_row_items_seven_columns, null);

			/******** View Holder Object to contain table_row_item.xml file elements ************/
			holder = new ViewHolder();
			holder.crm_no_txt = (TextView) vi.findViewById(R.id.column_one);
			holder.activity_txt = (TextView) vi.findViewById(R.id.column_two);
			holder.product_brand_txt = (TextView) vi.findViewById(R.id.column_three);
			holder.stock_status_txt = (TextView) vi.findViewById(R.id.column_four);
			holder.loaded_on_shelves_txt = (TextView) vi.findViewById(R.id.column_five);
			holder.created_by_txt = (TextView) vi.findViewById(R.id.column_six);
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
			this.tempValues = (JDIproductStockCheckRecord) this.data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.crm_no_txt.setText(this.tempValues.getCrm());
			holder.activity_txt.setText(String.valueOf(this.tempValues.getActivity()));
			holder.product_brand_txt.setText(String.valueOf(this.tempValues.getProductBrand()));
			holder.stock_status_txt.setText(String.valueOf(this.tempValues.getStockStatus()));
			holder.loaded_on_shelves_txt.setText(String.valueOf(this.tempValues.getLoadedOnShelves()));
			holder.created_by_txt.setText(String.valueOf(this.tempValues.getCreatedBy()));

			if (holder.crm_no_txt.getText().toString().equals("")) {
				holder.edit_txt.setText(null);
				holder.delete_txt.setText(null);
				holder.activity_txt.setText(null);
				holder.product_brand_txt.setText(null);
				holder.stock_status_txt.setText(null);
				holder.loaded_on_shelves_txt.setText(null);
				holder.created_by_txt.setText(null);
				holder.edit_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			}

			/******** Set Item Click Listener for LayoutInflater for each row ***********/
			holder.edit_txt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(activity.getApplicationContext(), "Edit here", Toast.LENGTH_SHORT).show();
					// JDIproductStockCheckRecord tempValues =
					// (JDIproductStockCheckRecord) data.get(position);

					// Saving acquired activity details
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
					showDeleteDialog(position);
				}
			});

			// Events
			
		}

		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	/********* Called when Item click in ListView ************/
	private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {
			JDIProductStockFragment sct = (JDIProductStockFragment) frag;
			sct.onItemClick(mPosition);
		}
	}

	private void showDeleteDialog(final int mPosition) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this.activity);
		dialog.setTitle("Delete JDI Product Stock");
		dialog.setMessage("Are you sure you want to delete this stock?");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				JDIproductStockCheckRecord tempValues = (JDIproductStockCheckRecord) data.get(mPosition);
				if (JardineApp.DB.getJDIproductStockCheck().delete(tempValues.getId())) {

					activity.runOnUiThread(new Runnable() {
						public void run() {
							JDIProductStockFragment sct = (JDIProductStockFragment) frag;
							sct.refreshListView();
						}
					});

					Toast.makeText(activity, "Successfully deleted stock", Toast.LENGTH_LONG).show();

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
