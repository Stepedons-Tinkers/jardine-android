package co.nextix.jardine.profile;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CalendarRecord;
import co.nextix.jardine.utils.MyDateUtils;

public class ProfileNotifAdapter extends ArrayAdapter<CalendarRecord> {

	private Context theContext;
	private ViewHolder holder;
	private int layout;

	public ProfileNotifAdapter(Context context, int textViewResourceId,
			List<CalendarRecord> objects) {
		super(context, textViewResourceId, objects);
		theContext = context;
		this.layout = textViewResourceId;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		holder = null;
		LayoutInflater layoutInflator = LayoutInflater.from(theContext);

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflator.inflate(layout, parent, false);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.col1 = (TextView) convertView.findViewById(R.id.tvProfileCol1);
		holder.col2 = (TextView) convertView.findViewById(R.id.tvProfileCol2);
		holder.tRow = (TableRow) convertView.findViewById(R.id.trProfileRow);

		if (position % 2 == 0) {
			holder.tRow.setBackgroundResource(R.color.white);
		} else {
			holder.tRow
					.setBackgroundResource(R.color.collaterals_tablerow_color1);
		}

		holder.list = getItem(position);

		holder.col1.setGravity(Gravity.CENTER);

		final String dateRange = MyDateUtils.convertDate(holder.list
				.getDateStart())
				+ "\n to \n"
				+ MyDateUtils.convertDate(holder.list.getDateEnd());

		holder.col1.setText(dateRange);
		holder.col2.setText(holder.list.getSubject());

		return convertView;
	}

	public class ViewHolder {

		CalendarRecord list;
		TextView col1;
		TextView col2;
		TableRow tRow;
	}
}