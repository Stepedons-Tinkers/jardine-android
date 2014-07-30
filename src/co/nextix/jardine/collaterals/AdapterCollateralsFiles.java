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
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.utils.MyDateUtils;

public class AdapterCollateralsFiles extends ArrayAdapter<DocumentRecord> {

	private View view;
	private Context context;
	private int layout;

	public AdapterCollateralsFiles(Context context, int resource,
			List<DocumentRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}

	private static class ViewHolder {
		public DocumentRecord record;
		public TextView col1;
		public TextView col2;
		public TextView col3;
		public TextView col4;
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

			holder.row = (TableRow) view.findViewById(R.id.trCollateralsMMRowH);
			holder.col1 = (TextView) view
					.findViewById(R.id.tvCollateralsMMCol1);
			holder.col2 = (TextView) view
					.findViewById(R.id.tvCollateralsMMCol2);
			holder.col3 = (TextView) view
					.findViewById(R.id.tvCollateralsMMCol3);
			holder.col4 = (TextView) view
					.findViewById(R.id.tvCollateralsMMCol4);
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

		holder.col1.setText(holder.record.getTitle());
		holder.col2.setText(holder.record.getFileName());
		holder.col3.setText(MyDateUtils.convertDateTime(holder.record
				.getModifiedTime()));
		holder.col4.setText(holder.record.getFileType());

		return view;
	}
}
