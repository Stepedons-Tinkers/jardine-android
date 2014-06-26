package co.nextix.jardine.workplan;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.WorkplanEntryRecord;

public class AdapterWorkplanEntry extends ArrayAdapter<WorkplanEntryRecord> {

	private View view;
	private Context context;
	private int layout;

	public AdapterWorkplanEntry(Context context, int resource,
			List<WorkplanEntryRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}

	private static class ViewHolder {
		public WorkplanEntryRecord record;
		public TextView txtCrmNo;
		public TextView txtDescription;
		public TextView txtEventType;
		public TextView txtIsActive;
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

			holder.row = (TableRow) view
					.findViewById(R.id.trCollateralsEventerProtocolRow);
			holder.txtCrmNo = (TextView) view
					.findViewById(R.id.tvCollateralsEventerProtocolCrmNo);
			holder.txtDescription = (TextView) view
					.findViewById(R.id.tvCollateralsEventerProtocolDescription);
			holder.txtEventType = (TextView) view
					.findViewById(R.id.tvCollateralsEventerProtocolEventType);
			holder.txtIsActive = (TextView) view
					.findViewById(R.id.tvCollateralsEventerProtocolIsActive);

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

		holder.txtCrmNo.setText(holder.record.getNo());
		holder.txtDescription.setText(holder.record.getCustomer() + "");

		holder.txtEventType.setText("");
		holder.txtIsActive.setText("");
		holder.txtEventType.setText(holder.record.getDate() + "");
		holder.txtIsActive.setText(holder.record.getActivityType() + "");

		return view;
	}
}
