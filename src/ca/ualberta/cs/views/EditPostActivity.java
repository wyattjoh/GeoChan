package ca.ualberta.cs.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.EditPostModel;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;

public abstract class EditPostActivity<T extends PostModel> extends Activity {
	public static final String IS_NEW = "IS_NEW_TOPIC";

	// image vars
	private static final int SELECT_PICTURE = 1;
	private static final int GET_LOCATION = 2;
	protected static final EditPostModel theEditPostModel = EditPostModel
			.getInstance();
	protected Bitmap imageBitmap = null;
	protected Location theLocation = null;

	protected T theModel;
	protected String postId = null;
	protected ArrayList<CommentModel> commentList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_post);

		this.theLocation = ActiveUserModel.getInstance().getUser()
				.getLocation();
		
		// Add title
		setTitle(getSaveButtonText());
		// Populate the views
		populateView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_post, menu);
		return true;
	}

	protected abstract String getSaveButtonText();

	protected abstract OnClickListener getNewOnClickListener();

	protected abstract OnClickListener getUpdateOnClickListener();

	/**
	 * update and populate the view so as to display the newest information
	 */
	protected void populateView() {
		Button saveButton = (Button) findViewById(R.id.saveOrAddButton);
		saveButton.setText(getSaveButtonText());

		if (theEditPostModel.isNewPost()) {
			saveButton.setOnClickListener(getNewOnClickListener());
		} else {
			saveButton.setOnClickListener(getUpdateOnClickListener());
		}
		
		if (EditPostModel.getInstance().isNewPost()){
			populateNew();
		} else{
			populateEdit();
		}
	}

	/**
	 * fill in edit post with new values
	 */
	private void populateNew() {
		// set distance button
		Button distanceButton = (Button) findViewById(R.id.currentLocationButton);
		Location temploc = ActiveUserModel.getInstance().getUser()
				.getLocation();
		distanceButton.setText(String.valueOf(temploc.getLatitude() + " , "
				+ String.valueOf(temploc.getLongitude())));

		// get photo button
		Button cameraButton = (Button) findViewById(R.id.pictureButton);

		// set onclick listener
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// start camera activity
				getPictureIntent();
			}
		});

		// get cancel button
		Button cancelButton = (Button) findViewById(R.id.distanceButton);

		// set onclick listener
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// return to previous activity
				finish();
			}
		});
		
	}

	/**
	 * fill in edit post view with post values
	 */
	private void populateEdit() {
		// get the post to fill values from
		theModel = (T) theEditPostModel.getThePost();
		postId = theEditPostModel.getThePost().getId();
		commentList = theEditPostModel.getThePost().getChildrenComments();
		
		EditPostModel.getInstance().setThePost(null);
		
		// set distance button to post value
		Button distanceButton = (Button) findViewById(R.id.currentLocationButton);
		Location tempLocation = theModel.getLocation();
		distanceButton.setText(String.valueOf(tempLocation.getLatitude() + " , "
				+ String.valueOf(tempLocation.getLongitude())));
		
		// set the comment text
		TextView commentText = (TextView) findViewById(R.id.commentTextField);
		commentText.setText(theModel.getCommentText());
		
		// set the title
		populateTitle(theModel);
		
		// get photo button
		Button cameraButton = (Button) findViewById(R.id.pictureButton);
		
		// set onclick listener
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// start camera activity
				getPictureIntent();
			}
		});
		
		// set the camera text if and get the picture
		if (theModel.hasPicture()){
			cameraButton.setText("Change Picture");
			
			// set the picture
			ImageView immageThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
			immageThumbnail.setImageBitmap(theModel.getPicture());
		}
		
		// get cancel button
		Button cancelButton = (Button) findViewById(R.id.distanceButton);

		// set onclick listener
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// return to previous activity
				finish();
			}
		});
	}
	
	protected abstract void populateTitle(PostModel theModel);

	/**
	 * start up the gallery activity, and set to receive the on activity result
	 * 
	 * github http://stackoverflow.com/questions/2169649/get-pick-an-image-from-
	 * androids-built-in-gallery-app-programmatically
	 * 
	 */
	public void getPictureIntent() {
		// select a file
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				SELECT_PICTURE);
	}

	/**
	 * start the location activity
	 * 
	 * @param theView
	 */
	public void onClick_StartLocationActivity(View theView) {
		Intent locationIntent = new Intent(this, LocationActivity.class);
		// locationIntent.putExtra(EXTRA_LOCATION, extraLocation);
		startActivityForResult(locationIntent, GET_LOCATION);
	}

	/**
	 * Creates a notification due to a failed response
	 * 
	 * @param theReason
	 */
	protected void failedDueToReason(String theReason) {
		Toast.makeText(getApplicationContext(), theReason, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SELECT_PICTURE) {
			if (resultCode == RESULT_OK) {
				// get picture path from intent
				Uri selectedImageUri = data.getData();
				String selectedImagePath = getPath(selectedImageUri);

				// get picture object from path
				imageBitmap = BitmapFactory.decodeFile(selectedImagePath);

				// get and set image view
				ImageView galleryThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
				// galleryThumbnail.setVisibility(View.VISIBLE);

				// create scaled image for display
				Bitmap scaledBitmap = scaleBitMapToFit(imageBitmap,
						galleryThumbnail);

				// set the view image o the selected image
				galleryThumbnail.setImageBitmap(scaledBitmap);
				imageBitmap = scaledBitmap;
			}
		} else if (requestCode == GET_LOCATION) {
			if (resultCode == RESULT_OK) {
				try {
					Double retLatitude = data.getDoubleExtra("extLatitude", 0);
					Double retLongitude = data
							.getDoubleExtra("extLongitude", 0);

					Location theCurrentLocation = new Location("");
					theCurrentLocation.setLatitude(retLatitude);
					theCurrentLocation.setLongitude(retLongitude);
					this.theLocation = theCurrentLocation;

					Button distanceButton = (Button) findViewById(R.id.currentLocationButton);
					distanceButton.setText(String.valueOf(this.theLocation.getLatitude()
							+ " , "
							+ String.valueOf(this.theLocation.getLongitude())));

					Toast.makeText(
							this,
							"Current Location is now:"
									+ String.valueOf(this.theLocation
											.getLatitude())
									+ " , "
									+ String.valueOf(this.theLocation
											.getLongitude()), Toast.LENGTH_LONG)
							.show();

				} catch (Exception e) {
					Toast.makeText(
							this,
							"FAILED "
									+ Double.toString(data.getDoubleExtra(
											"extLatitude", 0))
									+ Double.toString(data.getDoubleExtra(
											"extLongitude", 0)),
							Toast.LENGTH_LONG).show();
				}
			}
			if (resultCode == RESULT_CANCELED) {
				Toast.makeText(
						this,
						"Location is still: "
								+ String.valueOf(this.theLocation.getLatitude())
								+ " , "
								+ String.valueOf(this.theLocation
										.getLongitude()), Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	/**
	 * returns a scaled image where the image will be sized so that it will fit
	 * the image view by scaling to the largest length, either width or height
	 * and setting that to the size of the image view
	 * 
	 * @param bitmapImage
	 * @param imageViewScale
	 * @return
	 */
	public Bitmap scaleBitMapToFit(Bitmap bitmapImage, ImageView imageViewScale) {
		Bitmap scaledBitmap = null;

		if (bitmapImage.getWidth() > bitmapImage.getHeight()) {
			// if the image is bigger horizontally scale vertically
			scaledBitmap = Bitmap.createScaledBitmap(bitmapImage,
					bitmapImage.getWidth(), imageViewScale.getHeight(),
					imageViewScale.getFilterTouchesWhenObscured());

		} else {
			// otherwise scale horizontally
			scaledBitmap = Bitmap.createScaledBitmap(bitmapImage,
					imageViewScale.getWidth(), bitmapImage.getHeight(),
					imageViewScale.getFilterTouchesWhenObscured());
		}

		return scaledBitmap;
	}

	/**
	 * helper to retrieve the path of an image URI
	 * 
	 */
	public String getPath(Uri uri) {
		// just some safety built in
		if (uri == null) {
			// TODO perform some logging or show user feedback
			return null;
		}
		// try to retrieve the image from the media store first
		// this will only work for images selected from gallery
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		if (cursor != null) {
			int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		}
		// this is our fallback here
		return uri.getPath();
	}
}
