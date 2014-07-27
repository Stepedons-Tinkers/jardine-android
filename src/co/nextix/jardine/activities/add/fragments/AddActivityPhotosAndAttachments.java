package co.nextix.jardine.activities.add.fragments;

import java.io.File;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.R;

import com.dd.CircularProgressButton;

public class AddActivityPhotosAndAttachments extends Fragment {

	private boolean flag = false;

	private View view = null;

	private final int REQUEST_CODE_FROM_GALLERY = 1;
	private final int REQUEST_CODE_FROM_CAMERA = 2;

	private Uri imageUri = null;
	private String imagePath = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.add_activity_photos_attachments, container, false);

		((TextView) view.findViewById(R.id.filename)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
				alert.setMessage("Choose from: ");
				alert.setPositiveButton("Camera", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String appName = getResources().getString(R.string.app_name);
						String folder = Environment.getExternalStorageDirectory().toString() + "/" + appName;
						String imageName = "jardine_image.jpg";

						Intent takeFromCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

						File file = new File(folder);

						if (file.mkdir()) {
							Log.d("folder", "created");
						} else {
							Log.d("folder", "not created");
						}

						File fileToSave = new File(folder, imageName);

						imageUri = Uri.fromFile(fileToSave);

						takeFromCamera.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
						takeFromCamera.putExtra("return-data", true);
						startActivityForResult(takeFromCamera, REQUEST_CODE_FROM_CAMERA);
					}
				}).setNegativeButton("Gallery", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent pickFromGallery = new Intent(Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(pickFromGallery, REQUEST_CODE_FROM_GALLERY);
					}
				});
				alert.create();
				alert.show();
			}
		});

		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(1500);
					widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {

							Integer value = (Integer) animation.getAnimatedValue();
							((CircularProgressButton) v).setProgress(value);

							if (!flag) {

								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					String title = ((EditText) view.findViewById(R.id.title)).getText().toString();
					String filename = ((TextView) view.findViewById(R.id.filename)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (title != null && !title.isEmpty() && filename != null && !filename.isEmpty()) {

						flag = true;
						Editor editor = pref.edit();
						editor.putString("filename", filename);
						editor.putString("title", title);
						editor.commit(); // commit changes

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								getFragmentManager().popBackStackImmediate();
							}

						}, 2700);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);

							}
						}, 1500);
					}

				} else {

					// Code here
					((CircularProgressButton) v).setProgress(0);
				}
			}
		});

		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		getActivity();

		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == REQUEST_CODE_FROM_CAMERA) {
				imagePath = imageUri.getPath();
				((TextView) view.findViewById(R.id.filename)).setText(imagePath);
			} else if (requestCode == REQUEST_CODE_FROM_GALLERY) {
				imageUri = data.getData();
				imagePath = getRealPathFromURI(imageUri);
				((TextView) view.findViewById(R.id.filename)).setText(imagePath);
			}
		} else {
			Toast.makeText(getActivity(), "Attachment not found", Toast.LENGTH_SHORT).show();
		}
	};

	private String getRealPathFromURI(Uri contentURI) {
		String result;
		Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
		if (cursor == null) { // Source is Dropbox or other similar local file
								// path
			result = contentURI.getPath();
		} else {
			cursor.moveToFirst();
			int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
			result = cursor.getString(idx);
			cursor.close();
		}
		return result;
	}
}
