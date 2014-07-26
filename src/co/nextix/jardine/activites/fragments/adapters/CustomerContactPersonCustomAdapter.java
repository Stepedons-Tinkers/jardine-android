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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.activites.fragments.CompetitorStockCheckFragment;
import co.nextix.jardine.activites.fragments.CustomerContactPersonFragment;
import co.nextix.jardine.database.records.CompetitorProductStockCheckRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.CustomerContactTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PCustConPositionTable;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomerContactPersonCustomAdapter extends BaseAdapter {

	/*********** Declare Used Variables *********/
	private Context context;
	private FragmentActivity activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private CustomerContactRecord tempValues = null;
	private View vi = null;
	private ListView listView = null;

	/************* CustomAdapter Constructor *****************/
	public CustomerContactPersonCustomAdapter(Context a, FragmentActivity act, ListView listView, ArrayList<?> d, Fragment fragment) {

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
		public TextView first_name;
		public TextView last_name;
		public TextView position;
		public TextView mobile_no;
		public TextView created_by;
		public TextView edit_txt;
		public TextView delete_txt;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(final int position, final View convertView, ViewGroup parent) {

		this.vi = convertView;
		final int pos = position;
		final ViewHolder holder;
		CustomerContactPersonFragment sct = (CustomerContactPersonFragment) frag;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			this.vi = inflater.inflate(R.layout.table_row_items_six_columns, null);

			/******** View Holder Object to contain table_row_item.xml file elements ************/
			holder = new ViewHolder();
			holder.clickable_item_container    = (LinearLayout) vi.findViewById(R.id.table_row_clickable);
			holder.crm_no_txt        = (TextView) vi.findViewById(R.id.column_one);
			holder.first_name = (TextView) vi.findViewById(R.id.column_two);
			holder.last_name       = (TextView) vi.findViewById(R.id.column_three);
			holder.position        = (TextView) vi.findViewById(R.id.column_four);
			holder.mobile_no   = (TextView) vi.findViewById(R.id.column_five);
			holder.created_by   = (TextView) vi.findViewById(R.id.column_six);
			holder.edit_txt          = (TextView) vi.findViewById(R.id.action_edit_txt);
			holder.delete_txt        = (TextView) vi.findViewById(R.id.action_delete_txt);

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
			this.tempValues = (CustomerContactRecord) this.data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.crm_no_txt.setText(this.tempValues.getCrm());
			holder.first_name.setText(String.valueOf(this.tempValues.getFirstName()));
			holder.last_name.setText(String.valueOf(this.tempValues.getLastName()));
			
			holder.mobile_no.setText(String.valueOf(this.tempValues.getMobileNo()));
			
			 PCustConPositionTable customer = JardineApp.DB.getCustomerContactPosition();
			if(customer != null){
				PicklistRecord rec = customer.getById(this.tempValues.getPosition());
				holder.position.setText("");
				if(rec != null){
					holder.position.setText(rec.toString());
				}
			}

			UserTable user = JardineApp.DB.getUser();
			if(user != null){
				UserRecord rec = user.getById(this.tempValues.getCreatedBy());
				holder.created_by.setText("");
				if(rec != null){
					holder.created_by.setText(rec.toString());
				}
			}
			
			
			if (holder.crm_no_txt.getText().toString().equals("")) {
				holder.first_name.setText(null);				
				holder.last_name.setText(null);
				holder.crm_no_txt.setText(null);
				holder.position.setText(null);
				holder.mobile_no.setText(null);
				holder.created_by.setText(null);
//				holder.edit_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			}else
			{
				holder.clickable_item_container.setOnClickListener(new OnItemClickListener(pos));	
			}
			
			
			/******** Set Item Click Listener for LayoutInflater for each row ***********/
			holder.edit_txt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(activity.getApplicationContext(), "Edit here", Toast.LENGTH_SHORT).show();
					CompetitorProductStockCheckRecord tempValues = (CompetitorProductStockCheckRecord) data.get(position);

					// Saving acquired activity details
					SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putLong("activity_id", tempValues.getId());
					editor.commit(); // commit changes

					android.support.v4.app.Fragment fragment = new ActivityInfoFragment();
					android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
					fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
							.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
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
			CompetitorStockCheckFragment sct = (CompetitorStockCheckFragment) frag;
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
