package co.nextix.jardine.profile;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import co.nextix.jardine.R;
import co.nextix.jardine.model.ProfileNotification;

public class ProfileNoticationsFragment extends Fragment {

	private ListView listView;
	private ViewGroup header;
	private View view;
	private ProfileNotifAdapter listAdapter;
	private List<ProfileNotification> list;

	private TableRow tRow;
	private TextView h1, h2;

	public static Fragment newInstance() {
		ProfileNoticationsFragment fragment = new ProfileNoticationsFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.profile_notifications_layout, null);

		listView = (ListView) view.findViewById(R.id.profilenotif_listview);
		header = (ViewGroup) inflater.inflate(
				R.layout.profile_notif_list_header, listView, false);

		initLayout();

		return view;
	}

	private void initLayout() {

		// for header data of table
		tRow = (TableRow) header.findViewById(R.id.trProfileRow);
		h1 = (TextView) header.findViewById(R.id.tvProfileCol1);
		h2 = (TextView) header.findViewById(R.id.tvProfileCol2);

		h1.setText(getResources().getString(R.string.profile_notif_date));
		h2.setText(getResources()
				.getString(R.string.profile_notif_notification));

		listView.addHeaderView(header);
		list = new ArrayList<ProfileNotification>();
		listAdapter = new ProfileNotifAdapter(getActivity(),
				R.layout.profile_notif_list_header, list);

		listView.setAdapter(listAdapter);

	}

}
