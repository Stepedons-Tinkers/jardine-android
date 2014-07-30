package co.nextix.jardine.collaterals;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.utils.MyDateUtils;

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
		public TextView txtCol1;
		public TextView txtCol2;
		public TextView txtCol3;
		public TextView txtCol4;
		public TableRow row;
		public ImageView imgNew;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		view = convertView;
		ViewHolder holder = null;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		if (view == null) {
			view = inflater.inflate(this.layout, parent, false);
			holder = new ViewHolder();

			holder.row = (TableRow) view.findViewById(R.id.trCollateralsMMRowH);
			holder.txtCol1 = (TextView) view
					.findViewById(R.id.tvCollateralsMMCol1);
			holder.txtCol2 = (TextView) view
					.findViewById(R.id.tvCollateralsMMCol2);
			holder.txtCol3 = (TextView) view
					.findViewById(R.id.tvCollateralsMMCol3);
			holder.txtCol4 = (TextView) view
					.findViewById(R.id.tvCollateralsMMCol4);
			holder.imgNew = (ImageView) view
					.findViewById(R.id.ivCollateralsMMNewIndicator);

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
		holder.txtCol3.setText(holder.record.getTags());

		if (MyDateUtils.isDateNew(holder.record.getCreatedTime())) {
			holder.imgNew.setVisibility(View.VISIBLE);
		} else {
			holder.imgNew.setVisibility(View.GONE);

		}

		if (holder.record.getNo() == null) {
			holder.txtCol4.setText("");
		} else {

			if (holder.record.getIsActive() == 0) {
				holder.txtCol4.setText("No");
			} else if (holder.record.getIsActive() == 1) {
				holder.txtCol4.setText("Yes");
			}
		}

		return view;
	}
}
