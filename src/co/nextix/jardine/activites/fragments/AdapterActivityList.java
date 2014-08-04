package co.nextix.jardine.activites.fragments;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.utils.MyDateUtils;

public class AdapterActivityList extends ArrayAdapter<ActivityRecord> {
	private View view;
	private Context context;
	private int layout;

	public AdapterActivityList(Context context, int resource,
			List<ActivityRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}

	private static class ViewHolder {
		public ActivityRecord record;
		public TextView col1;
		public TextView col2;
		public TextView col3;
		public TextView col4;
		public TextView col5;
		public TextView col6;
		public TableRow row;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		view = convertView;
		ViewHolder holder = null;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		if (view == null) {
			view = inflater.inflate(this.layout, parent, false);
			holder = new ViewHolder();

			holder.row = (TableRow) view.findViewById(R.id.trActivityRow);
			holder.col1 = (TextView) view.findViewById(R.id.tvActivityCol1);
			holder.col2 = (TextView) view.findViewById(R.id.tvActivityCol2);
			holder.col3 = (TextView) view.findViewById(R.id.tvActivityCol3);
			holder.col4 = (TextView) view.findViewById(R.id.tvActivityCol4);
			holder.col5 = (TextView) view.findViewById(R.id.tvActivityCol5);
			holder.col6 = (TextView) view.findViewById(R.id.tvActivityCol6);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.record = getItem(position);

		if (position % 2 == 0) {
			holder.row.setBackgroundResource(R.color.white);
		} else {
			holder.row
					.setBackgroundResource(R.color.collaterals_tablerow_color1);
		}

		if (holder.record.getNo() == null) {
			holder.col1.setText("");
			holder.col2.setText("");
			holder.col3.setText("");
			holder.col4.setText("");
			holder.col5.setText("");
			holder.col6.setText("");

		} else {
			holder.col1.setText(holder.record.getCrm());

			WorkplanEntryRecord wEntry = JardineApp.DB.getWorkplanEntry()
					.getById(holder.record.getWorkplanEntry());

			if (wEntry != null) {
				WorkplanRecord wRecord = JardineApp.DB.getWorkplan().getById(
						wEntry.getWorkplan());
				if (wRecord != null)
					holder.col2.setText(wRecord.getCrm());
			}

			ActivityTypeRecord aType = JardineApp.DB.getActivityType().getById(
					holder.record.getActivityType());

			if (aType != null)
				holder.col3.setText(aType.getName());

			holder.col4.setText(MyDateUtils.convertDateTime(holder.record
					.getCheckIn()));
			holder.col5.setText(MyDateUtils.convertDateTime(holder.record
					.getCheckOut()));

			UserRecord uRecord = JardineApp.DB.getUser().getById(
					holder.record.getCreatedBy());
			if (uRecord != null)
				holder.col6.setText(uRecord.getFirstNameName() + " "
						+ uRecord.getLastname());

		}
		return view;
	}
}
