package ca.ualberta.cs.views;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.EditTopicController;
import ca.ualberta.cs.models.CurrentUserPostModelFactory;
import ca.ualberta.cs.models.TopicModel;

public class EditTopicActivity extends Activity {
	public static final String IS_NEW_TOPIC_KEY = "IS_NEW_TOPIC";

	private EditTopicController theController;
	private Boolean isNewTopic = true;

	private static final int SELECT_PICTURE = 1;
	private byte[] imageByteArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_topic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_topic, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// Get the extras
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			this.isNewTopic = extras.getBoolean(IS_NEW_TOPIC_KEY);
		}

		// Get the controller
		this.theController = new EditTopicController();

		// Populate the view
		populateView();
	}

	private void populateView() {
		Button saveButton = (Button) findViewById(R.id.saveOrAddButton);

		if (this.isNewTopic) {
			saveButton.setText("Add Topic");
			saveButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					TopicModel theTopicModel = CurrentUserPostModelFactory
							.newTopicModel();

					// Get the title
					EditText titleField = (EditText) findViewById(R.id.titleTextField);
					theTopicModel.setTitle(titleField.getText().toString());

					// Get the comment
					EditText commentField = (EditText) findViewById(R.id.commentTextField);
					theTopicModel.setCommentText(commentField.getText()
							.toString());

					theController.newTopic(theTopicModel);

					finish();
				}
			});

			// hide gallery thumbnail
			ImageView galeryThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
			// galeryThumbnail.setVisibility(View.INVISIBLE);

		} else {
			saveButton.setText("Update Topic");

		}

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

	}

	public Boolean getIsNewTopic() {
		return isNewTopic;
	}

	/**
	 * github http://stackoverflow.com/questions/2169649/get-pick-an-image-from-
	 * androids-built-in-gallery-app-programmatically
	 */
	public void getPictureIntent() {
		// select a file
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				SELECT_PICTURE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				// get picture path from intent
				Uri selectedImageUri = data.getData();
				String selectedImagePath = (String) getPath(selectedImageUri);

				// get picture object from path
				Bitmap imageBitmap = BitmapFactory
						.decodeFile(selectedImagePath);

				// get and set image view
				ImageView galleryThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
				// galleryThumbnail.setVisibility(View.VISIBLE);
				
				// create scaled image for display
				Bitmap scaledBitmap =  Bitmap.createScaledBitmap(imageBitmap,
						galleryThumbnail.getWidth(),
						galleryThumbnail.getHeight(),
						galleryThumbnail.getFilterTouchesWhenObscured());
				
				// set the view image o the selected image
				galleryThumbnail.setImageBitmap(scaledBitmap);

				// compress and output
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
				imageByteArray = outStream.toByteArray();
			}
		}
	}

	/**
	 * helper to retrieve the path of an image URI
	 */
	public String getPath(Uri uri) {
		// just some safety built in
		if (uri == null) {
			// TODO perform some logging or show user feedback
			return null;
		}
		// try to retrieve the image from the media store first
		// this will only work for images selected from gallery
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		if (cursor != null) {
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		}
		// this is our fallback here
		return uri.getPath();
	}

}
