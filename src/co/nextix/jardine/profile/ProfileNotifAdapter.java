package co.nextix.jardine.profile;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.model.ProfileNotification;

public class ProfileNotifAdapter extends ArrayAdapter<ProfileNotification> {

	private Context theContext;
	List<ProfileNotification> listModel;
	ViewHolder holder;

	String USER_ROLE;

	public ProfileNotifAdapter(Context context, int textViewResourceId,
			List<ProfileNotification> objects) {
		super(context, textViewResourceId, objects);
		listModel = objects;
		theContext = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		holder = null;
		LayoutInflater layoutInflator = LayoutInflater.from(getContext());

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflator.inflate(
					R.layout.profile_notif_list_item, parent, false);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtDate = (TextView) convertView
				.findViewById(R.id.profilenotif_date);
		holder.txtNotification = (TextView) convertView
				.findViewById(R.id.profilenotif_notif);
		holder.txtAction = (TextView) convertView
				.findViewById(R.id.profilenotif_action);
		holder.lDate = (LinearLayout) convertView
				.findViewById(R.id.profilenotif_ldate);
		holder.lNotification = (LinearLayout) convertView
				.findViewById(R.id.profilenotif_lnotif);
		holder.lAction = (LinearLayout) convertView
				.findViewById(R.id.profilenotif_laction);

		if (position % 2 == 0) {
			holder.lDate.setBackground(theContext.getResources().getDrawable(
					R.drawable.notif_list_item_left));
			holder.lNotification.setBackground(theContext.getResources()
					.getDrawable(R.drawable.notif_list_item_center));
			holder.lAction.setBackground(theContext.getResources().getDrawable(
					R.drawable.notif_list_item_right));
		}

		holder.txtDate.setText(getItem(position).getDate());
		holder.txtNotification.setText(getItem(position).getNotification());

		holder.lAction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(theContext, "removed!", Toast.LENGTH_SHORT)
						.show();
			}

		});

		return convertView;
	}

	@Override
	public ProfileNotification getItem(int position) {

		return listModel.get(position);
		// return super.getItem(position);
	}

	@Override
	public int getCount() {
		return listModel != null ? listModel.size() : 0;
		// return super.getCount();
	}

	public class ViewHolder {
		TextView txtDate;
		TextView txtNotification;
		TextView txtAction;
		LinearLayout lDate;
		LinearLayout lNotification;
		LinearLayout lAction;
	}
}