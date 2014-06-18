package co.nextix.jardine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DetailWorkPlanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_workplan);
	}

	public void goBackWorkplan(View view) {
		finish();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}

}
