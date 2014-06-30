package co.nextix.jardine.customers;

import java.util.List;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;

public class AdapterCustomers extends ArrayAdapter<CustomerRecord> {

	private View view;
	private Context context;
	private int layout;

	public AdapterCustomers(Context context, int resource,
			List<CustomerRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}

	private static class ViewHolder {
		public CustomerRecord record;
		public TextView txtCrmNo;
		public TextView txtCustomerName;
		public TextView txtBusinessUnit;
		public TextView txtArea;
		public TextView txtProvince;
		public TextView txtCityOrTown;
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

			holder.row = (TableRow) view.findViewById(R.id.trCustomersRow);
			holder.txtCrmNo = (TextView) view
					.findViewById(R.id.tvCustomerCRMNo);
			holder.txtCustomerName = (TextView) view
					.findViewById(R.id.tvCustomerName);
			holder.txtBusinessUnit = (TextView) view
					.findViewById(R.id.tvBusinessUnit);
			holder.txtArea = (TextView) view.findViewById(R.id.tvArea);
			holder.txtProvince = (TextView) view.findViewById(R.id.tvProvince);
			holder.txtCityOrTown = (TextView) view
					.findViewById(R.id.tvCityOrTown);

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
			holder.txtCustomerName.setText(holder.record.getCustomerName());

			BusinessUnitRecord business = JardineApp.DB.getBusinessUnit()
					.getById(holder.record.getBusinessUnit());
			holder.txtBusinessUnit.setText(business.getBusinessUnitName());

			PicklistRecord area = JardineApp.DB.getArea().getById(
					holder.record.getArea());
			holder.txtArea.setText(area.getName());

			ProvinceRecord prov = JardineApp.DB.getProvince().getById(
					holder.record.getProvince());
			holder.txtProvince.setText(prov.getName());

			CityTownRecord city = JardineApp.DB.getCityTown().getById(
					holder.record.getCityTown());
			holder.txtCityOrTown.setText(city.getName());
		}

		return view;
	}

}
