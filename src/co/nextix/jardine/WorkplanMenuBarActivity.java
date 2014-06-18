package co.nextix.jardine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WorkplanMenuBarActivity extends Activity {

	private TextView customerName;
	private TextView activityType;
	private TextView assignedTo;
	private EditText editMonth;

	private Calendar c = null;
	private Date today = null;
	private static SimpleDateFormat df = null;
	private String formattedDate = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workplan_menubar);

		c = Calendar.getInstance();
		df = new SimpleDateFormat("MM/dd/yyyy");
		today = new Date();

		editMonth = (EditText) this.findViewById(R.id.editMonth);
		customerName = (TextView) this.findViewById(R.id.customer);
		activityType = (TextView) this.findViewById(R.id.activity_type);
		assignedTo = (TextView) this.findViewById(R.id.assigned_to);

		customerName.setSelected(true);
		activityType.setSelected(true);
		assignedTo.setSelected(true);
		formattedDate = df.format(c.getTime());

		// Now formattedDate have current date/time
		Toast.makeText(this, "" + today, Toast.LENGTH_SHORT).show();
		editMonth.setText(formattedDate);
	}

	public void displayDetailedWorkplanView(View view) {
		Toast.makeText(this, "ni sud here", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(getApplicationContext(),
				DetailWorkPlanActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

	public void next(View view) {
		// adding one day to current date
		c.add(Calendar.DAY_OF_MONTH, 1);
		Date tommrrow = c.getTime();
		editMonth.setText(toddMMyy(tommrrow));
	}

	public void prev(View view) {
		c.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = c.getTime();
		editMonth.setText(toddMMyy(yesterday));
	}

	public static String toddMMyy(Date day) {
		String date = df.format(day);
		return date;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
