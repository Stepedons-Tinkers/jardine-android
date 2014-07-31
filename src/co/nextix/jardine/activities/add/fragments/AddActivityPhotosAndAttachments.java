package co.nextix.jardine.activities.add.fragments;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
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
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.DocumentRecord;

import com.dd.CircularProgressButton;

public class AddActivityPhotosAndAttachments extends Fragment {

	private boolean flag = false;

	private View view = null;
	private CircularProgressButton saveBtn = null;
	private final int REQUEST_CODE_FROM_GALLERY = 1;
	private final int REQUEST_CODE_FROM_CAMERA = 2;

	private Uri imageUri = null;
	private String imagePath = "";
	private long row_id = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.view = inflater.inflate(R.layout.add_activity_photos_attachments, container, false);
		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

		SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

		long id = pref.getLong("activity_id_edit", 0);
		ActivityRecord actRecord = JardineApp.DB.getActivity().getById(id);
		DocumentRecord activityPhotosAndAttachments = JardineApp.DB.getDocument().getRecordsForActivity(actRecord.getNo());
		
		if(activityPhotosAndAttachments != null){
			
		}

		((TextView) this.view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);
		((TextView) this.view.findViewById(R.id.filename)).setOnClickListener(new View.OnClickListener() {

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

		this.saveBtn = (CircularProgressButton) this.view.findViewById(R.id.btnWithText1);
		this.saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
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
					final SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (title != null && !title.isEmpty() && filename != null && !filename.isEmpty()) {

						flag = true;
						new InsertTask("", "", pref.getLong("activity_type", 0), pref.getString("check_in", null), pref.getString(
								"check_out", null).concat(displayCheckOut()), pref.getLong("business_unit", 0), pref
								.getLong("createdBy", 0), 123.894882, 10.310235, pref.getString("check_in", null), pref.getString(
								"check_out", null).concat(displayCheckOut()), "", 0, "", pref.getLong("customer", 0), pref.getLong("area",
								0), pref.getLong("province", 0), pref.getLong("city_town", 0), pref.getLong("workplan_entry", 0), pref
								.getString("objective", null), pref.getInt("first_time_visit", 0), pref.getInt("planned_time_visit", 0),
								pref.getString("notes", null), pref.getString("highlights", null), pref.getString("next_steps", null), pref
										.getString("follow_up_committment_date", null), "", 0, 0, "", 0, pref.getString(
										"end_user_activity_types", null)).execute();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								pref.edit().clear().commit();
								getFragmentManager().popBackStackImmediate();
							}

						}, 1500);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);

					// insert then pop all backstack
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
		if (cursor == null) {
			result = contentURI.getPath();
		} else {
			cursor.moveToFirst();
			int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
			result = cursor.getString(idx);
			cursor.close();
		}
		return result;
	}

	protected class InsertTask extends AsyncTask<Void, Void, Boolean> {

		private ValueAnimator widthAnimation = null;

		// String to be populated
		private String customerContactNo = null;
		private String customerContactCrmNo = null;
		private String customerContactFirstName = null;
		private String customerContactLastName = null;
		private long customerContactposition = 0;
		private String customerContactMobileNo = null;
		private String customerContactBirthday = null;
		private String customerContactEmailAddress = null;
		private long customerContactCustomer = 0;
		private int customerContactIsActive = 0;
		private String customerContactCreatedTime = null;
		private String customerContactModifiedTime = null;
		private long customerContactUser = 0;

		private String jdiProductStockCheckNo = null;
		private String jdiProductStockCheckCrmNo = null;
		private long jdiProductStockCheckActivity = 0;
		private long jdiProductStockCheckProduct = 0;
		private long jdiProductStockStatus = 0;
		private int jdiProductStockCheckLoadedOnShelves = 0;
		private long jdiProductStockCheckCustomerType = 0;
		private String jdiProductStockCheckOtherRemarks = null;
		private String jdiProductStockCheckCreatedTime = null;
		private String jdiProductStockCheckModifiedTime = null;
		private long jdiProductStockCheckCreatedBy = 0;

		private String productSupplierNo = null;
		private String productSupplierCrmNo = null;
		private long productSupplierProductBrand = 0;
		private long productSupplierSupplier = 0;
		private String productSupplierOthersRemarks = null;
		private long productSupplierActivity = 0;
		private long productSupplierCreatedBy = 0;
		private String productSupplierCreatedTime = null;
		private String productSupplierModifiedTime = null;

		private String jdiMerchandisingCheckNo = null;
		private String jdiMerchandisingCrmNo = null;
		private long jdiMerchandisingActivity = 0;
		private long jdiMerchandisingProductBrand = 0;
		private long jdiMerchandisingStatus = 0;
		private String jdiMerchandisingCreatedTime = null;
		private String jdiMerchandisingModifiedTime = null;
		private long jdiMerchandisingUser = 0;

		private String competitorProductStockCheckNo = null;
		private String competitorProductStockCheckCrmNo = null;
		private long competitorProductStockCheckActivity = 0;
		private long competitorProduct = 0;
		private long competitorProductStockStatus = 0;
		private int competitorProductLoadedOnShelves = 0;
		private String competitorProductOtherRemarks = null;
		private String competitorProductCreatedTime = null;
		private String competitorProductModifiedTime = null;
		private long competitorProductCreatedBy = 0;

		private String marketingIntelNo = null;
		private String marketingIntelCrmNo = null;
		private long marketingIntelActivity = 0;
		private long marketingIntelcompetitorProduct = 0;
		private String marketingIntelDetails = null;
		private String marketingIntelCreatedTime = null;
		private String marketingIntelModifiedTime = null;
		private long marketingIntelCreatedBy = 0;

		private String projectRequirementsNo = null;
		private String projectRequirementsCrmNo = null;
		private long projectRequirementsActivity = 0;
		private long projectRequirementType = 0;
		private String projectRequirementsDateNeeded = null;
		private String projectRequirementsSquareMeters = null;
		private String projectRequirementsProductsBrand = null;
		private String projectRequirementsOtherDetails = null;
		private String projectRequirementsCreatedTime = null;
		private String projectRequirementsModifiedTime = null;
		private long projectRequirementsCreatedBy = 0;

		private String activityPhotosAndAttachmentsNo = null;
		private String activityPhotosAndAttachmentsCrmNo = null;
		private String activityPhotosAndAttachmentsTitle = null;
		private String activityPhotosAndAttachmentsModuleName = null;
		private String activityPhotosAndAttachmentsModuleId = null;
		private String activityPhotosAndAttachmentsFileName = null;
		private String activityPhotosAndAttachmentsFileType = null;
		private String activityPhotosAndAttachmentsFilePath = null;
		private int activityPhotosAndAttachmentsIsActive = 0;
		private String activityPhotosAndAttachmentsCreatedTime = null;
		private String activityPhotosAndAttachmentsModifiedTime = null;
		private long activityPhotosAndAttachmentsUser = 0;

		private String no = null;
		private String crmNo = null;

		private long activityType = 0000;

		private String checkIn = null;
		private String checkOut = null;

		private long businessUnit = 0000;
		private long createdBy = 000;

		private double longitude = 0.00;
		private double latitude = 0.00;

		private String createdTime = null;
		private String modifiedTime = null;
		private String reasonsRemarks = null;

		private long smr = 0000;
		private String adminDetails = null;

		private long customer = 0000;
		private long area = 0000;
		private long province = 0000;
		private long city = 0000;
		private long workplanEntry = 0000;

		private String objective = null;

		private int firstTimeVisit = 0;
		private int plannedVisit = 0;

		private String notes = null;
		private String highlights = null;
		private String nextSteps = null;
		private String followUpCommitmentDate = null;
		private String projectName = null;
		private long projectStage = 0;
		private long projectCategory = 0;
		private String venue = null;

		private int numberOfAttendees = 0;
		private String endUserActivityTypes = null;

		private boolean flag;

		// For activity adding
		private InsertTask(String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit, long createdBy,
				double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
				String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
				int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
				String projectName, long projectStage, long projectCategory, String venue, int numberOfAttendees,
				String endUserActivityTypes) {

			this.no = no;
			this.crmNo = crmNo;
			this.activityType = activityType;
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.businessUnit = businessUnit;
			this.createdBy = createdBy;
			this.longitude = longitude;
			this.latitude = latitude;
			this.createdTime = createdTime;
			this.modifiedTime = modifiedTime;
			this.reasonsRemarks = reasonsRemarks;
			this.smr = smr;
			this.adminDetails = adminDetails;
			this.customer = customer;
			this.area = area;
			this.province = province;
			this.city = city;
			this.workplanEntry = workplanEntry;
			this.objective = objective;
			this.firstTimeVisit = firstTimeVisit;
			this.plannedVisit = plannedVisit;
			this.notes = notes;
			this.highlights = highlights;
			this.nextSteps = nextSteps;
			this.followUpCommitmentDate = followUpCommitmentDate;
			this.projectName = projectName;
			this.projectStage = projectStage;
			this.projectCategory = projectCategory;
			this.venue = venue;
			this.numberOfAttendees = numberOfAttendees;
			this.endUserActivityTypes = endUserActivityTypes;
		}

		// For customer contact adding
		private InsertTask(String no, String crmNo, String firstName, String lastName, long position, String mobileNo, String birthday,
				String emailAddress, long customer, int isActive, String createdTime, String modifiedTime, long user) {

		}

		@Override
		protected void onPreExecute() {
			// Animate Button
			this.widthAnimation = ValueAnimator.ofInt(1, 100);
			this.widthAnimation.setDuration(1500);
			this.widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
			this.widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					Integer value = (Integer) animation.getAnimatedValue();
					saveBtn.setProgress(value);

					if (!flag) {
						saveBtn.setProgress(-1);
					}
				}
			});

			this.widthAnimation.start();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			this.flag = false;
			try {

				saveActivity(this.no, this.crmNo, this.activityType, this.checkIn, this.checkOut, this.businessUnit, this.createdBy,
						this.longitude, this.latitude, this.createdTime, this.modifiedTime, this.reasonsRemarks, this.smr,
						this.adminDetails, this.customer, this.area, this.province, this.city, this.workplanEntry, this.objective,
						this.firstTimeVisit, this.plannedVisit, this.notes, this.highlights, this.nextSteps, this.followUpCommitmentDate,
						this.projectName, this.projectStage, this.projectCategory, this.venue, this.numberOfAttendees,
						this.endUserActivityTypes);

				this.flag = true;
			} catch (Exception e) {
				this.flag = false;
				Log.e("Jardine", e.toString());
			}

			return this.flag;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				saveBtn.setProgress(100);
			} else {
				this.flag = false;
			}
		}
	}

	protected String displayCheckOut() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return " " + df.format(calendar.getTime());
	}

	protected void saveActivity(String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit,
			long createdBy, double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
			String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
			int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
			String projectName, long projectStage, long projectCategory, String venue, int numberOfAttendees, String endUserActivityTypes) {

		// Insert to the database
		this.row_id = JardineApp.DB.getActivity().insert(no, crmNo, activityType, checkIn, checkOut, businessUnit, createdBy, longitude,
				latitude, createdTime, modifiedTime, reasonsRemarks, smr, adminDetails, customer, area, province, city, workplanEntry,
				objective, firstTimeVisit, plannedVisit, notes, highlights, nextSteps, followUpCommitmentDate, projectName, projectStage,
				projectCategory, venue, numberOfAttendees, endUserActivityTypes);
	}

	protected void saveCustomerContactPerson(String no, String crmNo, String firstName, String lastName, long position, String mobileNo,
			String birthday, String emailAddress, long customer, int isActive, String createdTime, String modifiedTime, long user) {

		// Inset to the database
		JardineApp.DB.getCustomerContact().insert(no, crmNo, firstName, lastName, position, mobileNo, birthday, emailAddress, customer,
				isActive, createdTime, modifiedTime, user);
	}

	protected void saveJDIProductStockCheck(String no, String crmNo, long activity, long product, long stockStatus, int loadedOnShelves,
			long customerType, String otherRemarks, String createdTime, String modifiedTime, long createdBy) {

		// Insert to the database
		JardineApp.DB.getJDIproductStockCheck().insert(no, crmNo, activity, product, stockStatus, loadedOnShelves, customerType,
				otherRemarks, createdTime, modifiedTime, createdBy);
	}

	protected void saveProductSupplier(String no, String crmNo, long productBrand, long supplier, String othersRemarks, long activity,
			long createdBy, String createdTime, String modifiedTime) {

		// Insert to the database
		JardineApp.DB.getProductSupplier().insert(no, crmNo, productBrand, supplier, othersRemarks, activity, createdBy, createdTime,
				modifiedTime);
	}

	protected void saveJDIMerchandisingCheck(String no, String crmNo, long activity, long productBrand, long status, String createdTime,
			String modifiedTime, long user) {

		// Insert to the database
		JardineApp.DB.getJDImerchandisingCheck().insert(no, crmNo, activity, productBrand, status, createdTime, modifiedTime, user);
	}

	protected void saveCompetitorProductStockCheck(String no, String crmNo, long activity, long competitorProduct, long stockStatus,
			int loadedOnShelves, String otherRemarks, String createdTime, String modifiedTime, long createdBy) {

		// Insert to the database
		JardineApp.DB.getCompetitorProductStockCheck().insert(no, crmNo, activity, competitorProduct, stockStatus, loadedOnShelves,
				otherRemarks, createdTime, modifiedTime, createdBy);
	}

	protected void saveMarketingIntel(String no, String crmNo, long activity, long competitorProduct, String details, String createdTime,
			String modifiedTime, long createdBy) {

		// Insert to the database
		JardineApp.DB.getMarketingIntel().insert(no, crmNo, activity, competitorProduct, details, createdTime, modifiedTime, createdBy);
	}

	protected void saveEndUserActivityType(String no) {

		// Insert to the database
		JardineApp.DB.getActEndUserActivityType().insert(no);
	}

	protected void saveProjectRequirements(String no, String crmNo, long activity, long projectRequirementType, String dateNeeded,
			String squareMeters, String productsBrand, String otherDetails, String createdTime, String modifiedTime, long createdBy) {

		// Insert to the database
		JardineApp.DB.getProjectRequirement().insert(no, crmNo, activity, projectRequirementType, dateNeeded, squareMeters, productsBrand,
				otherDetails, createdTime, modifiedTime, createdBy);
	}

	protected void saveIdentifyProductFocus(long product, long activity) {

		// Insert to the database
		JardineApp.DB.getProductFocus().insert(product, activity);
	}

	protected void saveActivityPhotosAndAttachments(String no, String crmNo, String title, String moduleName, String moduleId,
			String fileName, String fileType, String filePath, int isActive, String createdTime, String modifiedTime, long user) {

		// Insert to the database
		JardineApp.DB.getDocument().insert(no, crmNo, title, moduleName, moduleId, fileName, fileType, filePath, isActive, createdTime,
				modifiedTime, user);
	}
}
