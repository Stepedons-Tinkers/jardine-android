package co.nextix.jardine.collaterals;

import co.nextix.jardine.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class CollateralsFileViewer extends Activity {

	private VideoView video;
	private ImageView image;
	private MediaController control;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.collaterals_file_view);
		initLayout();
	}

	private void initLayout() {

		video = (VideoView) findViewById(R.id.vvCollateralsFileVideoView);
		image = (ImageView) findViewById(R.id.ivCollateralsFileImageView);
		control = new MediaController(this) {

			@Override
			public void show() {
				control.show(3000);
			}

		};
	}

}
