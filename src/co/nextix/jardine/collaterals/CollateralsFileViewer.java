package co.nextix.jardine.collaterals;

import java.io.File;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.DocumentRecord;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class CollateralsFileViewer extends Activity {

	private VideoView video;
	private ImageView image;
	private MediaController control;
	private DocumentRecord file;
	private File theFile;
	private String[] imgT;
	private String[] vidT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.collaterals_file_view);
		initLayout();
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	private void initLayout() {

		imgT = CollateralsConstants.IMAGE_TYPE;
		vidT = CollateralsConstants.VIDEO_TYPE;
		file = CollateralsConstants.FileRecord;
		video = (VideoView) findViewById(R.id.vvCollateralsFileVideoView);
		image = (ImageView) findViewById(R.id.ivCollateralsFileImageView);

		if (file != null) {

			theFile = new File(JardineApp.JARDINE_DIRECTORY + "/"
					+ file.getModuleName(), file.getFileName());
			if (theFile.exists()) {
				if (theFile.toString().endsWith(imgT[0])
						|| theFile.toString().endsWith(imgT[1])
						|| theFile.toString().endsWith(imgT[2])
						|| theFile.toString().endsWith(imgT[3])
						|| theFile.toString().endsWith(imgT[4])) {

					video.setVisibility(View.GONE);
					Bitmap myBitmap = BitmapFactory.decodeFile(theFile
							.getAbsolutePath());
					image.setImageBitmap(myBitmap);

				} else {
					image.setVisibility(View.GONE);
					control = new MediaController(this) {

						@Override
						public void show() {
							control.show(3000);
						}

					};

					control.show();
					video.setVideoPath(theFile.getAbsolutePath());

					video.setOnErrorListener(new OnErrorListener() {

						@Override
						public boolean onError(MediaPlayer mp, int what,
								int extra) {

							finish();
							return true;
						}
					});

					video.setMediaController(control);

					video.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {

							video.seekTo(0);
							control.setEnabled(true);

							control.show();
							video.start();
						}
					});

					video.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							control.show();
						}

					});
				}

			}

		}
	}
}
