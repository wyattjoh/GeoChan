package ca.ualberta.cs.views;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.TopicModelController;
import ca.ualberta.cs.models.CurrentUserPostModelFactory;
import ca.ualberta.cs.models.TopicModel;

public class EditTopicActivity extends Activity {
	public static final String IS_NEW_TOPIC_KEY = "IS_NEW_TOPIC";

	private TopicModelController theController;
	private Boolean isNewTopic = true;
	private byte[] imageData;
	
	static final int REQUEST_IMAGE_CAPTURE = 1;

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
		this.theController = new TopicModelController();

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
					TopicModel theTopicModel = CurrentUserPostModelFactory.newTopicModel();

					// Get the title
					EditText titleField = (EditText) findViewById(R.id.titleTextField);
					theTopicModel.setTitle(titleField.getText().toString());

					// Get the comment
					EditText commentField = (EditText) findViewById(R.id.commentTextField);
					theTopicModel.setCommentText(commentField.getText().toString());

					theController.newTopic(theTopicModel);

					finish();
				}
			});
		
		// hide gallery thumbnail
		ImageView galeryThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
		//galeryThumbnail.setVisibility(View.INVISIBLE);
		
		} else {
			saveButton.setText("Update Topic");
			
		}
		
		//get photo button
		Button cameraButton = (Button) findViewById(R.id.pictureButton);
		
		// set onclick listener
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// start camera activity
				dispatchTakePictureIntent();
			}
		});

	}

	public Boolean getIsNewTopic() {
		return isNewTopic;
	}
	
	public void dispatchTakePictureIntent() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent,0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK && data != null) {
	    	// get bitmap
	    	Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
	        
	        // get and set image view
	        ImageView galleryThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
	        //galleryThumbnail.setVisibility(View.VISIBLE);
	        galleryThumbnail.setImageBitmap(imageBitmap);
	        
	        // compress and output
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
	        imageData = outStream.toByteArray();
	        
	    }
	}

}
