package co.nextix.jardine.activities.add.fragments;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.DocumentRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.utils.MyDateUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitiesDocumentsAddNew extends Fragment implements
		View.OnClickListener {

	private View view;
	private Button btnAdd, btnCancel;
	private TextView txt1, txt3, txt4;
	private EditText txt2;
	private String filename;
	private String filepath;
	private Uri imageUri = null;
	private long userId;

	private File theFile;
	private final int REQUEST_CODE_FROM_GALLERY = 1;
	private final int REQUEST_CODE_FROM_CAMERA = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_add_document_form, container,
				false);

		initLayout();
		return view;
	}

	private void initLayout() {

		this.filename = fileName();
		btnAdd = (Button) view.findViewById(R.id.bActivityAddDocumentAdd);
		btnCancel = (Button) view.findViewById(R.id.bActivityAddDocumentCancel);

		btnAdd.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		txt1 = (TextView) view.findViewById(R.id.tvActivityDocumentAddField1);
		txt3 = (TextView) view.findViewById(R.id.tvActivityDocumentAddField3);
		txt4 = (TextView) view.findViewById(R.id.tvActivityDocumentAddField4);

		txt3.setOnClickListener(this);
		txt3.setText("");

		txt2 = (EditText) view.findViewById(R.id.etActivityDocumentAddField2);

		UserRecord userRecord = JardineApp.DB.getUser().getCurrentUser();
		userId = userRecord.getId();
		txt4.setText(userRecord.getFirstNameName() + " "
				+ userRecord.getFirstNameName());

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bActivityAddDocumentAdd:
			if (checker()) {
				save();
			}

			break;
		case R.id.bActivityAddDocumentCancel:
			dismiss();
			break;

		case R.id.tvActivityDocumentAddField3:
			showOption();
			break;
		}

	}

	public void dismiss() {
		ActivitiesDocumentList fragment = (ActivitiesDocumentList) getTargetFragment();
		fragment.populate();
		fragment.removeFragment();

	}

	public void save() {

		DocumentRecord docRecord = new DocumentRecord();
		docRecord.setCreatedTime(MyDateUtils.getCurrentTimeStamp());
		docRecord.setCrmNo("");
		docRecord.setFileName(filename);
		docRecord.setFilePath(filepath);
		docRecord.setFileType("image/jpeg");
		docRecord.setIsActive(1);
		docRecord.setModifiedTime(MyDateUtils.getCurrentTimeStamp());
		docRecord.setModuleId("");
		docRecord.setModuleName(Modules.Activity);
		docRecord.setNo("");
		docRecord.setTitle(txt2.getText().toString());
		docRecord.setUser(userId);

		ActivitiesConstant.ACTIVITY_DOCUMENT_MAIN_LIST.add(docRecord);

		Toast.makeText(getActivity(), "Successfully added document",
				Toast.LENGTH_LONG).show();
		dismiss();
	}

	public String fileName() {
		String ts = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
				.format(new Date());

		return "Jardine_" + ts + ".jpg";
	}

	public static String getMimeType(String url) {
		String type = null;
		String extension = MimeTypeMap.getFileExtensionFromUrl(url);
		if (extension != null) {
			MimeTypeMap mime = MimeTypeMap.getSingleton();
			type = mime.getMimeTypeFromExtension(extension);
		}
		return type;
	}

	public void showOption() {
		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
		alert.setTitle("Please choose");
		alert.setMessage("Choose from: ");
		alert.setPositiveButton("Camera", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String appName = getResources().getString(R.string.app_name);
				String folder = Environment.getExternalStorageDirectory()
						.toString() + "/" + appName;

				Intent takeFromCamera = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);

				File file = new File(folder);

				if (file.mkdir()) {
					Log.d("folder", "created");
				} else {
					Log.d("folder", "not created");
				}

				theFile = new File(folder, filename);

				imageUri = Uri.fromFile(theFile);

				takeFromCamera.putExtra(
						android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
				takeFromCamera.putExtra("return-data", true);
				startActivityForResult(takeFromCamera, REQUEST_CODE_FROM_CAMERA);

			}
		}).setNegativeButton("Gallery", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent pickFromGallery = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(pickFromGallery,
						REQUEST_CODE_FROM_GALLERY);
			}
		});

		alert.create();
		alert.show();

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// getActivity();

		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == REQUEST_CODE_FROM_CAMERA) {
				filepath = imageUri.getPath();
				txt3.setText(filepath);
			} else if (requestCode == REQUEST_CODE_FROM_GALLERY) {
				imageUri = data.getData();
				filepath = getRealPathFromURI(imageUri);
				txt3.setText(filepath);
			}
		} else {
			Toast.makeText(getActivity(), "Attachment not found",
					Toast.LENGTH_SHORT).show();
		}

	}

	private String getRealPathFromURI(Uri contentURI) {
		String result;
		Cursor cursor = getActivity().getContentResolver().query(contentURI,
				null, null, null, null);
		if (cursor == null) {
			result = contentURI.getPath();
		} else {
			cursor.moveToFirst();
			int idx = cursor
					.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
			result = cursor.getString(idx);
			cursor.close();
		}

		theFile = new File(result);
		return result;
	}

	public boolean checker() {
		boolean flag = false;
		if (theFile != null) {
			if (theFile.exists()) {
				if (!txt3.getText().toString().contentEquals("")) {
					flag = true;
				} else {
					flag = false;
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							getActivity());
					dialog.setTitle("Warning");
					dialog.setMessage("Please input Title!");
					dialog.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();

								}
							});
					dialog.show();
				}
			}
		} else {
			flag = false;
			AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
			dialog.setTitle("Warning");
			dialog.setMessage("File does not exist!");
			dialog.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();

						}
					});
			dialog.show();

		}

		return flag;
	}

}
