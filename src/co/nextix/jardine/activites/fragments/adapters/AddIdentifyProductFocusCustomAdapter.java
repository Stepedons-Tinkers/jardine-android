package co.nextix.jardine.activites.fragments.adapters;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.activites.fragments.CompetitorStockCheckFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.activities.add.fragments.AddIdentifyProductFocusFragment;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.keys.Constant;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class AddIdentifyProductFocusCustomAdapter extends BaseAdapter {

	/*********** Declare Used Variables *********/
	private Context context;
	private FragmentActivity activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private ProductRecord tempValues = null;
	private View vi = null;
	private ListView listView = null;

	/************* CustomAdapter Constructor *****************/
	public AddIdentifyProductFocusCustomAdapter(Context a, FragmentActivity act, ListView listView, ArrayList<?> d, Fragment fragment) {

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
		public TextView product_no;
		public TextView product_brand;
		public TextView product_description;
		public TextView is_active;
		public TextView created_by;
		public CheckBox checker;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(final int position, final View convertView, ViewGroup parent) {

		this.vi = convertView;
		final int pos = position;
		final ViewHolder holder;
		AddIdentifyProductFocusFragment sct = (AddIdentifyProductFocusFragment) frag;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			this.vi = inflater.inflate(R.layout.table_row_items_seven_columns_checkbox, null);
			/******** View Holder Object to contain table_row_item.xml file elements ************/
			holder = new ViewHolder();
			holder.clickable_item_container = (LinearLayout) vi.findViewById(R.id.table_row_clickable);
			holder.crm_no_txt = (TextView) vi.findViewById(R.id.column_one);
			holder.product_no = (TextView) vi.findViewById(R.id.column_two);
			holder.product_brand = (TextView) vi.findViewById(R.id.column_three);
			holder.product_description = (TextView) vi.findViewById(R.id.column_four);
			holder.is_active = (TextView) vi.findViewById(R.id.column_five);
			holder.created_by = (TextView) vi.findViewById(R.id.column_six);
			holder.checker = (CheckBox) vi.findViewById(R.id.checkbox1);

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
			this.tempValues = (ProductRecord) this.data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.crm_no_txt.setText(String.valueOf(this.tempValues.getCrm()));
			holder.product_no.setText(String.valueOf(this.tempValues.getProductNumber()));
			holder.product_description.setText(String.valueOf(this.tempValues.getProductDescription()));
			holder.product_brand.setText(String.valueOf(this.tempValues.getProductBrand()));
			holder.is_active.setText(String.valueOf(this.tempValues.getIsActive()));

			UserTable user = JardineApp.DB.getUser();
			if (user != null) {
				UserRecord rec = user.getById(this.tempValues.getCreatedBy());
				holder.created_by.setText("");
				if (rec != null) {
					holder.created_by.setText(rec.toString());
				}
			}

			if (holder.crm_no_txt.getText().toString().equals("") || holder.crm_no_txt.getText().toString() == null
					|| holder.crm_no_txt.getText().toString().equals("null")) {
				holder.product_no.setText("");
				holder.created_by.setText("");
				holder.product_brand.setText("");
				holder.crm_no_txt.setText("");
				holder.product_description.setText("");
				holder.is_active.setText("");
				holder.checker.setVisibility(CheckBox.GONE);
				// holder.edit_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				// 0, 0);
			} else {
				holder.clickable_item_container.setOnClickListener(new OnItemClickListener(pos));
			}

			/******** Set Item Click Listener for LayoutInflater for each row ***********/

			if (Constant.addProductFocusRecords.contains(((ProductRecord) data.get(position))))
				holder.checker.setChecked(true);
			else
				holder.checker.setChecked(false);

			holder.checker.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					
						if (isChecked){
							if (Constant.addProductFocusRecords.size() < 5) 
								Constant.addProductFocusRecords.add(((ProductRecord) data.get(position)));
							 else {
								Constant.addProductFocusRecords.remove(((ProductRecord) data.get(position)));
								Toast.makeText(context, "Cannot select more than 5 Brands.", Toast.LENGTH_SHORT).show();
									holder.checker.setChecked(false);
							 }
						}
						else
							Constant.addProductFocusRecords.remove(((ProductRecord) data.get(position)));

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
			AddIdentifyProductFocusFragment sct = (AddIdentifyProductFocusFragment) frag;
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
				CompetitorProductStockCheckRecord tempValues = (CompetitorProductStockCheckRecord) data.get(mPosition);
				if (JardineApp.DB.getActivity().delete(tempValues.getId())) {

					activity.runOnUiThread(new Runnable() {
						public void run() {
							CompetitorStockCheckFragment sct = (CompetitorStockCheckFragment) frag;
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
