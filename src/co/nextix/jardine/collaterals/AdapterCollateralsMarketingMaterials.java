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
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;

public class AdapterCollateralsMarketingMaterials extends
		ArrayAdapter<MarketingMaterialsRecord> {

	private View view;
	private Context context;
	private int layout;

	public AdapterCollateralsMarketingMaterials(Context context, int resource,
			List<MarketingMaterialsRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}

	private static class ViewHolder {
		public MarketingMaterialsRecord record;
		public TextView txtCrmNo;
		public TextView txtDescription;
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

			holder.row = (TableRow) view.findViewById(R.id.trCollateralsMMRow);
			holder.txtCrmNo = (TextView) view
					.findViewById(R.id.tvCollateralsMMCrmNo);
			holder.txtDescription = (TextView) view
					.findViewById(R.id.tvCollateralsMMDescription);
			holder.txtIsActive = (TextView) view
					.findViewById(R.id.tvCollateralsMMIsActive);

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

		holder.txtCrmNo.setText(holder.record.getCrm());
		holder.txtDescription.setText(holder.record.getDescription());
		holder.txtIsActive.setText(holder.record.getTags());

		return view;
	}
}
