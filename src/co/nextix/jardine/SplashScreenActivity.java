package co.nextix.jardine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		try { // Simulate a long loading process on app startup.
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		finish();
		startActivity(new Intent(getApplicationContext(),
				LoginActivity.class));
		overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
	}

}
