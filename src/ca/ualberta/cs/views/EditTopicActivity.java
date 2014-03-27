package ca.ualberta.cs.views;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.TopicModelController;
import ca.ualberta.cs.models.CurrentUserPostModelFactory;
import ca.ualberta.cs.models.TopicModel;

public class EditTopicActivity extends EditPostActivity {
	public static final String IS_NEW_TOPIC_KEY = "IS_NEW_TOPIC";

	private TopicModelController theController;
	private Boolean isNewTopic = true;

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

	@Override
	protected void populateView() {
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
			//ImageView galeryThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
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
}
