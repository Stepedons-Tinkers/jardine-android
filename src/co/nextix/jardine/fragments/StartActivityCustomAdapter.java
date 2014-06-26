package co.nextix.jardine.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.R;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class StartActivityCustomAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Context activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private StartActivityListModel tempValues = null;

	/************* CustomAdapter Constructor *****************/
	public StartActivityCustomAdapter(Context a, ArrayList<?> d, Fragment fragment) {

		/********** Take passed values **********/
		activity = a;
		frag = fragment;
		data = d;

		/*********** Layout inflator to call external xml layout () **********************/
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	/******** What is the size of Passed Arraylist Size ************/
	public int getCount() {

		if (data.size() <= 0)
			return 1;
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	/********* Create a holder to contain inflated xml file elements ***********/
	public static class ViewHolder {
		public TextView crm_no_txt;
		public TextView workplan_txt;
		public TextView activity_type_txt;
		public TextView start_time_txt;
		public TextView end_time_txt;
		public TextView assigned_to_txt;
		public TextView action_txt;
		public TextView status;
		public TextView edit_txt;
		public TextView delete_txt;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.table_row_item, null);

			/******** View Holder Object to contain table_row_item.xml file elements ************/
			holder = new ViewHolder();
			holder.crm_no_txt = (TextView) vi.findViewById(R.id.crm_no_txt);
			holder.workplan_txt = (TextView) vi.findViewById(R.id.workplan_txt);
			holder.activity_type_txt = (TextView) vi.findViewById(R.id.activity_type_txt);
			holder.start_time_txt = (TextView) vi.findViewById(R.id.start_time_txt);
			holder.end_time_txt = (TextView) vi.findViewById(R.id.end_time_txt);
			holder.assigned_to_txt = (TextView) vi.findViewById(R.id.assigned_to_txt);
			holder.status = (TextView) vi.findViewById(R.id.status_list_view);
			holder.edit_txt = (TextView) vi.findViewById(R.id.action_edit_txt);
			holder.delete_txt = (TextView) vi.findViewById(R.id.action_delete_txt);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);

		} else
			holder = (ViewHolder) vi.getTag();

		holder.edit_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(activity.getApplicationContext(), "Edit here", Toast.LENGTH_SHORT).show();

			}
		});

		holder.delete_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(activity.getApplicationContext(), "Delete here", Toast.LENGTH_SHORT).show();
			}
		});

		if (data.size() <= 0) {
			holder.status.setVisibility(View.VISIBLE);

		} else {
			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (StartActivityListModel) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.crm_no_txt.setText(tempValues.getCrmNo());
			holder.workplan_txt.setText(String.valueOf(tempValues.getWorkplan()));
			holder.activity_type_txt.setText(String.valueOf(tempValues.getActivityType()));
			holder.start_time_txt.setText(tempValues.getStartTime());
			holder.end_time_txt.setText(tempValues.getEndTime());
			holder.assigned_to_txt.setText(String.valueOf(tempValues.getAssignedTo()));

			/******** Set Item Click Listner for LayoutInflater for each row ***********/
			vi.setOnClickListener(new OnItemClickListener(position));
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
}
