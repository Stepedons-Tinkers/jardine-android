package co.nextix.jardine;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.activities.add.fragments.AddMarketingIntelFragment.OnHeadlineSelectedListener;
import co.nextix.jardine.adapter.NavDrawerListAdapter;
import co.nextix.jardine.collaterals.CollateralsMenuBarFragment;
import co.nextix.jardine.customers.ViewAllCustomersFragment;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.MarketingIntelRecord;
import co.nextix.jardine.fragments.DashboardFragment;
import co.nextix.jardine.fragments.ProfileFragment;
import co.nextix.jardine.fragments.StartActivityFragment;
import co.nextix.jardine.fragments.SyncMenuBarFragment;
import co.nextix.jardine.model.NavDrawerItem;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.workplan.WorkplanMenuBarFragment;

import com.squareup.timessquare.CalendarPickerView;

public class DashBoardActivity extends FragmentActivity implements OnHeadlineSelectedListener {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	private static final String TAG = "DashBoardActivity";
	private AlertDialog theDialog;
	private CalendarPickerView dialogView;

	public static boolean fromAddActivities = false;
	public static ArrayList<Integer> tabIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		tabIndex = new ArrayList<Integer>();

		this.mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		this.navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		this.navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		this.mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		this.navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// DashBoard
		this.navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));

		// Profile
		this.navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

		// Sync
		this.navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));

		// Workplan
		this.navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));

		// Activities
		this.navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));

		// Customers
		this.navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

		// Collaterals
		this.navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));

		// Collaterals
		this.navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));

		// Recycle the typed array
		this.navMenuIcons.recycle();

		this.mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		this.adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
		this.mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		this.mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, // nav
																									// menu
																									// toggle
																									// icon
				R.string.app_name, // nav drawer open - description for
									// accessibility

				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);

				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);

				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};

		this.mDrawerLayout.setDrawerListener(mDrawerToggle);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		boolean justLogged = extras.getBoolean("JUSTLOGGED", false);
		Log.w(JardineApp.TAG, "DashboardActivity: justLoggedIn: " + justLogged);
		// if (savedInstanceState == null) {
		// // on first time display view for first nav item
		if (justLogged)
			displayView(2);
		else
			displayView(0);
		// }

	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new DashboardFragment();
			break;
		case 1:
			fragment = new ProfileFragment();
			break;
		case 2:
			fragment = SyncMenuBarFragment.newInstance(DashBoardActivity.this);
			break;
		case 3:
			fragment = new WorkplanMenuBarFragment();
			break;
		case 4:
			fragment = new StartActivityFragment();
			break;
		case 5:
			// fragment = new CustomersMenuBarFragment();
			fragment = new ViewAllCustomersFragment();
			break;

		case 6:
			fragment = new CollateralsMenuBarFragment();
			break;

		case 7:
			// fragment = new LogoutMenuBarFragment();
			logoutAlert();
			break;

		default:
			break;
		}

		if (fragment != null) {

			clearStack();

			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);

			int itemPosition = 0;

			switch (position) {
			case 0:
				itemPosition = 0;
				ft.replace(R.id.frame_container, fragment, "dashboard-name");
				ft.commit();
				break;
			case 1:
				itemPosition = 1;
				ft.replace(R.id.frame_container, fragment, "profile-name");
				ft.addToBackStack("profile");
				ft.commit();
				break;
			case 2:
				itemPosition = 2;
				ft.replace(R.id.frame_container, fragment, "sync-name");
				ft.addToBackStack("sync");
				ft.commit();
				break;
			case 3:
				itemPosition = 3;
				ft.replace(R.id.frame_container, fragment, "workplan-name");
				ft.addToBackStack("workplan");
				ft.commit();
				break;
			case 4:
				itemPosition = 4;
				ft.replace(R.id.frame_container, fragment, "activities-name");
				ft.addToBackStack("activities");
				ft.commit();
				break;
			case 5:
				itemPosition = 5;
				ft.replace(R.id.frame_container, fragment, "customers-name");
				ft.addToBackStack("customers");
				ft.commit();
				break;
			case 6:
				itemPosition = 6;
				ft.replace(R.id.frame_container, fragment, "collaterlas-name");
				ft.addToBackStack("collaterals");
				ft.commit();
				break;
			case 7:
				itemPosition = 7;
				ft.replace(R.id.frame_container, fragment, "logout-name");
				ft.addToBackStack("logout");
				ft.commit();
				break;
			default:
				ft.replace(R.id.frame_container, fragment, "dashboard-name");
				ft.commit();
				break;
			}

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(itemPosition, true);
			mDrawerList.setSelection(itemPosition);
			setTitle(navMenuTitles[itemPosition]);
			mDrawerLayout.closeDrawer(mDrawerList);

		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void onBackPressed() {
		if (fromAddActivities) {
			int size = tabIndex.size();

			if (size == 1) {
				super.onBackPressed();
			} else {
				AddActivityFragment.pager.setCurrentItem(tabIndex.get(size - 2));
				tabIndex.remove(size - 1);
			}

		} else {
			super.onBackPressed();
		}

		if (getSupportFragmentManager().findFragmentByTag("dashboard-name") instanceof DashboardFragment) {
			mDrawerList.setItemChecked(0, true);
			mDrawerList.setSelection(0);
			setTitle(navMenuTitles[0]);
			mDrawerLayout.closeDrawer(mDrawerList);
		}

		// super.onBackPressed();
	}

	private void clearStack() {
		getSupportFragmentManager().popBackStack();
		getSupportFragmentManager().popBackStack("profile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		getSupportFragmentManager().popBackStack("sync", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		getSupportFragmentManager().popBackStack("workplan", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		getSupportFragmentManager().popBackStack("activities", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		getSupportFragmentManager().popBackStack("customers", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		getSupportFragmentManager().popBackStack("collaterals", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		getSupportFragmentManager().popBackStack("logout", FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
		// this.startAnimationPopOut();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void openCalendarView(View view) {
		final Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, 1);

		final Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.YEAR, -1);

		dialogView = (CalendarPickerView) getLayoutInflater().inflate(R.layout.custom_calendarview_dialog, null, false);
		dialogView.init(lastYear.getTime(), nextYear.getTime()) //
				.withSelectedDate(new Date());
		theDialog = new AlertDialog.Builder(DashBoardActivity.this).setTitle(null).setView(dialogView)
				.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						dialogInterface.dismiss();
					}
				}).create();

		theDialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialogInterface) {
				Log.d(TAG, "onShow: fix the dimens!");
				dialogView.fixDialogDimens();
			}
		});

		theDialog.show();
	}

	public void checkin(View view) {
		Toast.makeText(getApplicationContext(), "Checking in..", Toast.LENGTH_SHORT).show();
	}

	public void checkout(View view) {
		Toast.makeText(getApplicationContext(), "Checking out..", Toast.LENGTH_LONG).show();
	}

	/************* Activites *********************/
	public void startActivity(View view) {
		android.support.v4.app.Fragment fragment = new StartActivityFragment();
		android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("activities").commit();
		setTitle("Activities");
	}

	public void viewTodaysWorkplan(View view) {
		android.support.v4.app.Fragment fragment = new WorkplanMenuBarFragment();
		android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("workplan").commit();
		setTitle("Workplan");
	}

	public static String toddMMyy(Date day) {
		String date = WorkplanMenuBarFragment.df.format(day);
		return date;
	}

	private class LogoutTask extends AsyncTask<Void, Void, Boolean> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(DashBoardActivity.this);
			dialog.setTitle("Signing out");
			dialog.setMessage("Please wait...");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {

			StoreAccount.clear(DashBoardActivity.this);
			DatabaseAdapter.getInstance().clearDatabase();
			// deleteDatabase();
			// JardineApp.DB.close();
			JardineApp.SESSION_NAME = null;
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				DashBoardActivity.this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

			} else {
				Toast.makeText(DashBoardActivity.this, "Error", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void deleteDatabase() {
		JardineApp.JARDINE_DIRECTORY.concat("/databases/" + DatabaseAdapter.DATABASE_NAME);
		File dbFile = new File(JardineApp.JARDINE_DIRECTORY);
		boolean result = dbFile.delete();
		Log.w(JardineApp.TAG, "database delete: " + result);
	}

	private void logoutAlert() {

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(DashBoardActivity.this);
		builderSingle.setTitle("Logout");
		builderSingle.setMessage("Are you sure?");
		builderSingle.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();
				new LogoutTask().execute();
			}
		});
		builderSingle.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				getFragmentManager();
				getFragmentManager().popBackStack("logout", FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});
		builderSingle.show();

	}

	public void onArticleSelected(ArrayList<MarketingIntelRecord> rec) {
		// The user selected the headline of an article from the
		// HeadlinesFragment
		// Do something here to display that article
		
		//Toast.makeText(getApplicationContext(), rec.toString(), Toast.LENGTH_SHORT).show();
	}
}
