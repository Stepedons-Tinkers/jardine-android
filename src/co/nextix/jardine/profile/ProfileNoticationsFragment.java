package co.nextix.jardine.profile;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CalendarRecord;
import co.nextix.jardine.model.ProfileNotification;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;

public class ProfileNoticationsFragment extends Fragment {

	private ListView listView;
	private View view, header;
	private ProfileNotifAdapter listAdapter;

	private TableRow tRow;
	private TextView h1, h2;
	private List<CalendarRecord> calendarRecords;

	private long userId;

	public static Fragment newInstance() {
		ProfileNoticationsFragment fragment = new ProfileNoticationsFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.profile_notifications_layout, null,
				false);

		header = (View) inflater.inflate(R.layout.profile_row, null, false);

		initLayout();

		populateData();

		return view;
	}

	private void initLayout() {

		// for header data of table
		tRow = (TableRow) header.findViewById(R.id.trProfileRow);
		h1 = (TextView) header.findViewById(R.id.tvProfileCol1);
		h2 = (TextView) header.findViewById(R.id.tvProfileCol2);

		h1.setTypeface(null, Typeface.BOLD);
		h2.setTypeface(null, Typeface.BOLD);

		h1.setGravity(Gravity.CENTER);
		h2.setGravity(Gravity.CENTER);

		tRow.setBackgroundResource(R.color.tab_pressed);
		h1.setText(getResources().getString(R.string.profile_notif_date));
		h2.setText(getResources()
				.getString(R.string.profile_notif_notification));

		listView = (ListView) view
				.findViewById(R.id.lvProfileListNotifListView);
		listView.addHeaderView(header);
		calendarRecords = new ArrayList<CalendarRecord>();
		listAdapter = new ProfileNotifAdapter(getActivity(),
				R.layout.profile_row, calendarRecords);

		listView.setAdapter(listAdapter);

	}

	private void populateData() {
//		String id = StoreAccount.restore(getActivity())
//				.getString(Account.ROWID);
		userId = StoreAccount.restore(getActivity()).getLong(Account.ROWID);

		List<CalendarRecord> dummy = JardineApp.DB.getCalendar()
				.getAllRecordsByUserId(userId, MyDateUtils.getCurrentDate());

		if (dummy != null) {
			calendarRecords.addAll(dummy);
			listAdapter.notifyDataSetChanged();
		}

	}

}
