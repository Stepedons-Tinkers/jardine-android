package co.nextix.jardine.contactperson.fragments;

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
import co.nextix.jardine.database.records.CustomerContactRecord;

public class AdapterContactPerson extends ArrayAdapter<CustomerContactRecord> {
	private View view;
	private Context context;
	private int layout;
	
	public AdapterContactPerson(Context context, int resource,
			List<CustomerContactRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}
	
	private static class ViewHolder {
		public CustomerContactRecord record;
		public TextView txtCrmNo;
		public TextView txtFirstName;
		public TextView txtLastName;
		public TextView txtPosition;
		public TextView txtMobileNo;
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
					.findViewById(R.id.trContactPersonRow);
			holder.txtCrmNo = (TextView) view
					.findViewById(R.id.tvContactPersonCRMNo);
			holder.txtFirstName = (TextView) view
					.findViewById(R.id.tvContactPersonFirstName);
			holder.txtLastName = (TextView) view
					.findViewById(R.id.tvContactPersonLastName);
			holder.txtPosition = (TextView) view
					.findViewById(R.id.tvContactPersonPosition);
			holder.txtMobileNo = (TextView) view
					.findViewById(R.id.tvContactPersonMobileNo);
	
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
			
		} else {
			holder.txtCrmNo.setText(holder.record.getNo());
			holder.txtFirstName.setText(holder.record.getFirstName());
			holder.txtLastName.setText(holder.record.getLastName());
			holder.txtPosition.setText(String.valueOf(holder.record.getPosition()));
			holder.txtMobileNo.setText(holder.record.getMobileNo());
		}
	
		return view;
	}
}
