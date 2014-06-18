package co.nextix.jardine.profile;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import co.nextix.jardine.R;
import co.nextix.jardine.model.ProfileNotification;

public class ProfileNoticationsFragment extends Fragment {

	ListView listView;
	ProfileNotifAdapter listAdapter;
	List<ProfileNotification> dummyList = new ArrayList<ProfileNotification>();

	public static Fragment newInstance() {
		ProfileNoticationsFragment fragment = new ProfileNoticationsFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.profile_notifications_layout,
				null);

		listView = (ListView) view.findViewById(R.id.profilenotif_listview);
		ViewGroup header = (ViewGroup) inflater.inflate(
				R.layout.profile_notif_list_header, listView, false);
		listView.addHeaderView(header, null, false);

		populateList();

		return view;
	}

	private void populateList() {
		dummyList
				.add(new ProfileNotification("2014-06-12", "Independence day"));
		dummyList.add(new ProfileNotification("2014-06-13",
				"Friday the 13th special!"));
		dummyList.add(new ProfileNotification("2014-06-14",
				"Beach beach beach!"));
		dummyList.add(new ProfileNotification("2014-06-15", "Weekend work!"));
		dummyList.add(new ProfileNotification("2014-06-16", "Back to work!"));

		listAdapter = new ProfileNotifAdapter(getActivity(),
				R.layout.profile_notif_list_item, dummyList);
		listView.setAdapter(listAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ProfileNotification item = (ProfileNotification) parent
						.getItemAtPosition(position);
				Toast.makeText(getActivity(), item.getNotification(),
						Toast.LENGTH_SHORT).show();
			}

		});
	}
}
