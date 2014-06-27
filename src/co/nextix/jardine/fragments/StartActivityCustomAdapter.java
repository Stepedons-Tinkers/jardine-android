package co.nextix.jardine.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
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
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class StartActivityCustomAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Context activity;
	private Fragment frag;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	private ActivityRecord tempValues = null;
	private View vi = null;

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
		public TextView edit_txt;
		public TextView delete_txt;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		vi = convertView;
		final int pos = position;
		final ViewHolder holder;
		StartActivityFragment sct = (StartActivityFragment) frag;

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
			holder.edit_txt = (TextView) vi.findViewById(R.id.action_edit_txt);
			holder.delete_txt = (TextView) vi.findViewById(R.id.action_delete_txt);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);

		} else
			holder = (ViewHolder) vi.getTag();

		// Checking of the data gathered
		if (data.size() <= 0) {
			sct.isListHasNoData();

		} else {
			sct.isListHasData();

			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (ActivityRecord) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.crm_no_txt.setText(tempValues.getCrm());
			holder.workplan_txt.setText(String.valueOf(tempValues.getWorkplan()));
			holder.activity_type_txt.setText(String.valueOf(tempValues.getActivityType()));
			holder.start_time_txt.setText(tempValues.getStartTime());
			holder.end_time_txt.setText(tempValues.getEndTime());
			holder.assigned_to_txt.setText(String.valueOf(tempValues.getCustomer()));

			if (holder.crm_no_txt.getText().toString().equals("")) {
				holder.edit_txt.setText(null);
				holder.delete_txt.setText(null);
				holder.edit_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			}

			if (holder.start_time_txt.getText().toString().equals(null) 
			 || holder.start_time_txt.getText().toString().equals("")
			 || holder.end_time_txt.getText().toString().equals(null)
			 || holder.end_time_txt.getText().toString().equals("")) {
				
				holder.workplan_txt.setText(null);
				holder.activity_type_txt.setText(null);
				holder.assigned_to_txt.setText(null);
			}

			/******** Set Item Click Listener for LayoutInflater for each row ***********/
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

			// Events
			((HorizontalScrollView) vi.findViewById(R.id.crm_no_hsv)).setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_DOWN)
							|| (event.getAction() == MotionEvent.ACTION_MOVE) || (MotionEvent.ACTION_OUTSIDE == event.getAction())) {

						v.findViewById(R.id.crm_no_txt).setClickable(true);
						v.findViewById(R.id.crm_no_txt).setOnClickListener(new OnItemClickListener(pos));
					}

					return false;
				}
			});

			((HorizontalScrollView) vi.findViewById(R.id.workplan_hsv)).setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_DOWN)
							|| (event.getAction() == MotionEvent.ACTION_MOVE) || (MotionEvent.ACTION_OUTSIDE == event.getAction())) {

						v.findViewById(R.id.workplan_txt).setClickable(true);
						v.findViewById(R.id.workplan_txt).setOnClickListener(new OnItemClickListener(pos));
					}
					return false;
				}
			});

			((HorizontalScrollView) vi.findViewById(R.id.activity_type_hsv)).setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_DOWN)
							|| (event.getAction() == MotionEvent.ACTION_MOVE) || (MotionEvent.ACTION_OUTSIDE == event.getAction())) {

						v.findViewById(R.id.activity_type_txt).setClickable(true);
						v.findViewById(R.id.activity_type_txt).setOnClickListener(new OnItemClickListener(pos));
					}
					return false;
				}
			});

			((HorizontalScrollView) vi.findViewById(R.id.start_time_hsv)).setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_DOWN)
							|| (event.getAction() == MotionEvent.ACTION_MOVE) || (MotionEvent.ACTION_OUTSIDE == event.getAction())) {

						v.findViewById(R.id.start_time_txt).setClickable(true);
						v.findViewById(R.id.start_time_txt).setOnClickListener(new OnItemClickListener(pos));
					}

					return false;
				}
			});

			((HorizontalScrollView) vi.findViewById(R.id.end_time_hsv)).setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_DOWN)
							|| (event.getAction() == MotionEvent.ACTION_MOVE) || (MotionEvent.ACTION_OUTSIDE == event.getAction())) {

						v.findViewById(R.id.end_time_txt).setClickable(true);
						v.findViewById(R.id.end_time_txt).setOnClickListener(new OnItemClickListener(pos));
					}
					return false;
				}
			});

			((HorizontalScrollView) vi.findViewById(R.id.assigned_to_hsv)).setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_DOWN)
							|| (event.getAction() == MotionEvent.ACTION_MOVE) || (MotionEvent.ACTION_OUTSIDE == event.getAction())) {

						v.findViewById(R.id.assigned_to_txt).setClickable(true);
						v.findViewById(R.id.assigned_to_txt).setOnClickListener(new OnItemClickListener(pos));
					}

					return false;
				}
			});
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
