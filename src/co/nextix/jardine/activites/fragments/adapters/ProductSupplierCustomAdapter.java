package co.nextix.jardine.activites.fragments.adapters;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.JDIProductStockFragment;
import co.nextix.jardine.activites.fragments.ProductSupplierFragment;
import co.nextix.jardine.activities.update.fragments.UpdateConstants;
import co.nextix.jardine.activities.update.fragments.UpdateProductSupplier;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.ProductSupplierRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.ProductSupplierTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class ProductSupplierCustomAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Context context;
	private FragmentActivity activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private ProductSupplierRecord tempValues = null;
	private View vi = null;

	/************* CustomAdapter Constructor *****************/
	public ProductSupplierCustomAdapter(Context a, FragmentActivity act, ArrayList<?> d, Fragment fragment) {

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
		
		public LinearLayout table_row_clickable;
		public TextView crm_no_txt;
		public TextView activity_txt;
		public TextView product_brand_txt;
		public TextView supplier;
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
		
		ProductSupplierFragment sct = null;
		UpdateProductSupplier ups = null;
		
		if(UpdateConstants.RECORD != null){
			ups = (UpdateProductSupplier) frag;
		}else{
			sct = (ProductSupplierFragment) frag;
		}

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			this.vi = inflater.inflate(R.layout.table_row_items_five_columns, null);

			/******** View Holder Object to contain table_row_item.xml file elements ************/
			holder = new ViewHolder();
			holder.table_row_clickable = (LinearLayout) vi.findViewById(R.id.table_row_clickable);
			holder.crm_no_txt = (TextView) vi.findViewById(R.id.column_one);
			holder.activity_txt = (TextView) vi.findViewById(R.id.column_two);
			holder.product_brand_txt = (TextView) vi.findViewById(R.id.column_three);
			holder.supplier = (TextView) vi.findViewById(R.id.column_four);
			holder.created_by_txt = (TextView) vi.findViewById(R.id.column_five);
			holder.edit_txt = (TextView) vi.findViewById(R.id.action_edit_txt);
			holder.delete_txt = (TextView) vi.findViewById(R.id.action_delete_txt);

			/************ Set holder with LayoutInflater ************/
			this.vi.setTag(holder);

		} else
			holder = (ViewHolder) vi.getTag();

		// Checking of the data gathered
		if (this.data.size() <= 0) {
			if(UpdateConstants.RECORD != null){
				ups.isListHasNoData();
			}else{
				sct.isListHasNoData();
			}
		} else {
			if(UpdateConstants.RECORD != null){
				ups.isListHasData();
			}else{
				sct.isListHasData();
			}

			/***** Get each Model object from Arraylist ********/
			this.tempValues = (ProductSupplierRecord) this.data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.crm_no_txt.setText(this.tempValues.getCrm());

			if (holder.crm_no_txt.getText().toString().equals("")) {
				holder.edit_txt.setText(null);
				holder.delete_txt.setText(null);
				holder.activity_txt.setText(null);
				holder.product_brand_txt.setText(null);
				holder.supplier.setText(null);
				holder.created_by_txt.setText(null);
				holder.edit_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			}else{
				
				ActivityTable act = JardineApp.DB.getActivity();
				if(act != null){
					ActivityRecord rec = act.getById(this.tempValues.getActivity());
					holder.activity_txt.setText("");
					if(rec != null){
						holder.activity_txt.setText(rec.toString());	
					}
				}
				
				ProductTable product = JardineApp.DB.getProduct();
				if(product != null){
					holder.product_brand_txt.setText("");
					ProductRecord rec = product.getById(this.tempValues.getProductBrand());
					if(rec != null){
						holder.product_brand_txt.setText(rec.toString());
					}
				}
				
				CustomerTable supplier = JardineApp.DB.getCustomer();
				if(supplier != null){
					CustomerRecord rec = supplier.getById(this.tempValues.getSupplier());
					holder.supplier.setText("");
					if(rec != null){
						holder.supplier.setText(rec.toString());
					}
				}
				
				UserTable user = JardineApp.DB.getUser();
				if(user != null){
					UserRecord rec = user.getById(this.tempValues.getCreatedBy());
					holder.created_by_txt.setText("");
					if(rec != null){
						holder.created_by_txt.setText(rec.toString());
					}
				}
				
				
				holder.table_row_clickable.setOnClickListener(new OnItemClickListener(pos));				
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
			ProductSupplierFragment sct = (ProductSupplierFragment) frag;
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
