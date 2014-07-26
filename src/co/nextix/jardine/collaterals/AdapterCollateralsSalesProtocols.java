package co.nextix.jardine.collaterals;

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
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.SalesProtocolRecord;

public class AdapterCollateralsSalesProtocols extends
		ArrayAdapter<SalesProtocolRecord> {

	private View view;
	private Context context;
	private int layout;

	public AdapterCollateralsSalesProtocols(Context context, int resource,
			List<SalesProtocolRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}

	private static class ViewHolder {
		public SalesProtocolRecord record;
		public TextView txtCol1;
		public TextView txtCol2;
		public TextView txtCol3;
		public TextView txtCol4;
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
			holder.txtCol1 = (TextView) view
					.findViewById(R.id.tvCollateralsEventerProtocolCrmNo);
			holder.txtCol2 = (TextView) view
					.findViewById(R.id.tvCollateralsEventerProtocolDescription);
			holder.txtCol3 = (TextView) view
					.findViewById(R.id.tvCollateralsEventerProtocolEventType);
			holder.txtCol4 = (TextView) view
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

		holder.txtCol1.setText(holder.record.getCrm());
		holder.txtCol2.setText(holder.record.getDescription());

		if (holder.record.getNo() == null) {
			holder.txtCol3.setText("");
			holder.txtCol4.setText("");
		} else {
			PicklistRecord rec = JardineApp.DB.getSalesProtocolType().getById(
					holder.record.getProtocolType());
			try {
				holder.txtCol3.setText(rec.getName());
			} catch (Exception e) {
				holder.txtCol3.setText("");
			}

			if (holder.record.getIsActive() == 0) {
				holder.txtCol4.setText("No");
			} else if (holder.record.getIsActive() == 1) {
				holder.txtCol4.setText("Yes");
			}
		}

		return view;
	}

}
