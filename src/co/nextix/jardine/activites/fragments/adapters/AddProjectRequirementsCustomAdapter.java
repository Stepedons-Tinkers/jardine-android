package co.nextix.jardine.activites.fragments.adapters;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activites.fragments.JDIProductStockFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityProjectRequirementsListFragment;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProjectRequirementRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ProjectRequirementTable;
import co.nextix.jardine.database.tables.UserTable;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class AddProjectRequirementsCustomAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Context context;
	private FragmentActivity activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private ProjectRequirementRecord tempValues = null;
	private View vi = null;

	/************* CustomAdapter Constructor *****************/
	public AddProjectRequirementsCustomAdapter(Context a, FragmentActivity act, ArrayList<?> d, Fragment fragment) {

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
		
//		public LinearLayout table_row_clickable;
		public TextView crm_no_txt;
		public TextView activity_txt;
		public TextView project_requirement_txt;
		public TextView date_needed;
		public TextView created_by_txt;
//		public TextView action_txt;
//		public TextView edit_txt;
//		public TextView delete_txt;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(final int position, final View convertView, ViewGroup parent) {

		this.vi = convertView;
		final int pos = position;
		final ViewHolder holder;
		AddActivityProjectRequirementsListFragment sct = (AddActivityProjectRequirementsListFragment) frag;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			this.vi = inflater.inflate(R.layout.table_row_items_five_columns, null);

			/******** View Holder Object to contain table_row_item.xml file elements ************/
			holder = new ViewHolder();
//			holder.table_row_clickable = (LinearLayout) vi.findViewById(R.id.table_row_clickable);
			holder.crm_no_txt = (TextView) vi.findViewById(R.id.column_one);
			holder.activity_txt = (TextView) vi.findViewById(R.id.column_two);
			holder.project_requirement_txt = (TextView) vi.findViewById(R.id.column_three);
			holder.date_needed = (TextView) vi.findViewById(R.id.column_four);
			holder.created_by_txt = (TextView) vi.findViewById(R.id.column_five);

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
			this.tempValues = (ProjectRequirementRecord) this.data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.crm_no_txt.setText(this.tempValues.getCrm());
				
				ActivityTable act = JardineApp.DB.getActivity();
				if(act != null){
					ActivityRecord rec = act.getById(this.tempValues.getActivity());
					holder.activity_txt.setText("");
					if(rec != null){
						holder.activity_txt.setText(rec.toString());	
					}
				}
				
				List<PicklistRecord> projectRequirementList = JardineApp.DB.getProjectRequirementType().getAllRecords();
				//ProjectRequirementTypeTable project = JardineApp.DB.getProjectRequirementType();
				if(projectRequirementList != null){
					holder.project_requirement_txt.setText("");
					PicklistRecord rec = projectRequirementList.get((int) this.tempValues.getProjectRequirementType());
					if(rec != null){
						holder.project_requirement_txt.setText(rec.toString());
					}
				}
				

				holder.date_needed.setText(this.tempValues.getDateNeeded());
				
				UserTable user = JardineApp.DB.getUser();
				if(user != null){
					UserRecord rec = user.getById(this.tempValues.getCreatedBy());
					holder.created_by_txt.setText("");
					if(rec != null){
						holder.created_by_txt.setText(rec.toString());
					}
				}
				
				
//				holder.table_row_clickable.setOnClickListener(new OnItemClickListener(pos));				
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
			AddActivityProjectRequirementsListFragment sct = (AddActivityProjectRequirementsListFragment) frag;
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
