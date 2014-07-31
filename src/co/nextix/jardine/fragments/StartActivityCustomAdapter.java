package co.nextix.jardine.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.ActivityInfoFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.SMRTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.WorkplanTable;
import co.nextix.jardine.utils.MyDateUtils;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class StartActivityCustomAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Context context;
	private FragmentActivity activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private ActivityRecord tempValues = null;
	private View vi = null;
	private ListView listView = null;

	/************* CustomAdapter Constructor *****************/
	public StartActivityCustomAdapter(Context a, FragmentActivity act, ListView listView, ArrayList<?> d, Fragment fragment) {

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
		public LinearLayout clickable_area;
		public TextView crm_no_txt;
		public TextView workplan_txt;
		public TextView activity_type_txt;
		public TextView start_time_txt;
		public TextView end_time_txt;
		public TextView created_by;
		public TextView action_txt;
		public TextView edit_txt;
		public TextView delete_txt;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(final int position, final View convertView, ViewGroup parent) {

		this.vi = convertView;
		final int pos = position;
		final ViewHolder holder;
		StartActivityFragment sct = (StartActivityFragment) frag;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			this.vi = inflater.inflate(R.layout.table_row_items_six_columns, null);

			/******** View Holder Object to contain table_row_item.xml file elements ************/
			holder = new ViewHolder();
			holder.clickable_area = (LinearLayout) vi.findViewById(R.id.table_row_clickable);
			holder.crm_no_txt = (TextView) vi.findViewById(R.id.column_one);
			holder.workplan_txt = (TextView) vi.findViewById(R.id.column_two);
			holder.activity_type_txt = (TextView) vi.findViewById(R.id.column_three);
			holder.start_time_txt = (TextView) vi.findViewById(R.id.column_four);
			holder.end_time_txt = (TextView) vi.findViewById(R.id.column_five);
			holder.created_by = (TextView) vi.findViewById(R.id.column_six);
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
			this.tempValues = (ActivityRecord) this.data.get(position);

			/************ Set Model values in Holder elements ***********/
			
			if (this.tempValues.toString() == null) {
				holder.workplan_txt.setText(null);
				holder.activity_type_txt.setText(null);
				holder.start_time_txt.setText(null);
				holder.end_time_txt.setText(null);
				holder.created_by.setText(null);
				holder.edit_txt.setText(null);
				holder.crm_no_txt.setText(null);
				holder.delete_txt.setText(null);
				holder.edit_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				
			}else{
				holder.start_time_txt.setText(MyDateUtils.convertDateTime(this.tempValues.getCheckIn()));
				holder.end_time_txt.setText(MyDateUtils.convertDateTime(this.tempValues.getCheckOut()));
				holder.crm_no_txt.setText(this.tempValues.getCrm());
				

				
				WorkplanTable wor = JardineApp.DB.getWorkplan();
				if(wor != null){
					WorkplanRecord rec = wor.getById(this.tempValues
							.getWorkplanEntry());
					holder.workplan_txt.setText("");
					if(rec != null){
						holder.workplan_txt.setText(rec.toString());
					}
				}
				
				
				ActivityTypeTable act = JardineApp.DB.getActivityType();
				if(act != null){
					ActivityTypeRecord rec = act.getById(this.tempValues
							.getActivityType());
					holder.activity_type_txt.setText("");
					if(rec != null){
						holder.activity_type_txt.setText(rec.toString());
					}
				}
				
				
				UserTable user = JardineApp.DB.getUser();
				if(user != null){
					UserRecord rec = user.getById(this.tempValues
							.getCreatedBy());
					holder.created_by.setText("");
					if(rec != null){
						holder.created_by.setText(rec.toString());
					}
				}
			}

			if (holder.start_time_txt.getText().toString().equals(null) || holder.start_time_txt.getText().toString().equals("")
					|| holder.end_time_txt.getText().toString().equals(null) || holder.end_time_txt.getText().toString().equals("")) {

				holder.workplan_txt.setText(null);
				holder.activity_type_txt.setText(null);
				holder.created_by.setText(null);
			}

			/******** Set Item Click Listener for LayoutInflater for each row ***********/
			holder.edit_txt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(activity.getApplicationContext(), "Edit here", Toast.LENGTH_SHORT).show();
					ActivityRecord tempValues = (ActivityRecord) data.get(position);

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
			
			holder.clickable_area.setOnClickListener(new OnItemClickListener(position));
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
			StartActivityFragment sct = (StartActivityFragment) frag;
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
				ActivityRecord tempValues = (ActivityRecord) data.get(mPosition);
				if (JardineApp.DB.getActivity().delete(tempValues.getId())) {

					activity.runOnUiThread(new Runnable() {
						public void run() {
							StartActivityFragment sct = (StartActivityFragment) frag;
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
