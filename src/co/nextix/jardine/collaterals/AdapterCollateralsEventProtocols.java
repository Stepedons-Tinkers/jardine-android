package co.nextix.jardine.collaterals;

import java.util.List;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;

public class AdapterCollateralsEventProtocols extends
		ArrayAdapter<EventProtocolRecord> {

	private View view;
	private Context context;
	private int layout;

	public AdapterCollateralsEventProtocols(Context context, int resource,
			List<EventProtocolRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layout = resource;
	}

	private static class ViewHolder {
		public EventProtocolRecord record;
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

		holder.txtCrmNo.setText(holder.record.getCrm());
		holder.txtDescription.setText(holder.record.getDescription());

		if (holder.record.getNo() == null) {
			holder.txtEventType.setText("");
			holder.txtIsActive.setText("");
		} else {
			PicklistRecord rec = JardineApp.DB.getEventProtocolType().getById(
					holder.record.getEventType());
			holder.txtEventType.setText(rec.getName());
			if (holder.record.getIsActive() == 0) {
				holder.txtIsActive.setText("No");
			} else if (holder.record.getIsActive() == 1) {
				holder.txtIsActive.setText("Yes");
			}
		}

		return view;
	}
	
	
	
}
