package co.nextix.jardine.customers.fragments;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;

public class AdapterCustomerContacts extends
		ArrayAdapter<CustomerContactRecord> {

	private View view;
	private Context context;
	private int layout;

	public AdapterCustomerContacts(Context context, int resource,
			List<CustomerContactRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}

	private static class ViewHolder {
		public CustomerContactRecord record;
		public TextView col1;
		public TextView col2;
		public TextView col3;
		public TextView col4;
		public TextView col5;
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
					.findViewById(R.id.trCustomerContactRow);
			holder.col1 = (TextView) view
					.findViewById(R.id.tvCustomerContactCol1);
			holder.col2 = (TextView) view
					.findViewById(R.id.tvCustomerContactCol2);
			holder.col3 = (TextView) view
					.findViewById(R.id.tvCustomerContactCol3);
			holder.col4 = (TextView) view
					.findViewById(R.id.tvCustomerContactCol4);
			holder.col5 = (TextView) view
					.findViewById(R.id.tvCustomerContactCol5);

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

		} else {
			holder.col1.setText(holder.record.getNo());
			holder.col2.setText(holder.record.getFirstName());

			holder.col3.setText(holder.record.getLastName());

			PicklistRecord cPos = JardineApp.DB.getCustomerContactPosition()
					.getById(holder.record.getPosition());
			holder.col4.setText(cPos.getName());

			holder.col5.setText(holder.record.getMobileNo());

		}
		return view;
	}
}
