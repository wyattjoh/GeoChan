package ca.ualberta.cs.views;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.CommentModelController;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CurrentUserPostModelFactory;
import ca.ualberta.cs.models.TopicModel;

public class EditCommentActivity extends EditPostActivity {

	public static final String IS_NEW_COMMENT_KEY = "IS_NEW_COMMENT";

	private CommentModelController theController;
	private Boolean isNewComment = true;

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
			this.isNewComment = extras.getBoolean(IS_NEW_COMMENT_KEY);
		}

		// Get the controller
		this.theController = new CommentModelController(null);

		// Populate the view
		populateView();
	}

	protected void populateView() {
		Button saveButton = (Button) findViewById(R.id.saveOrAddButton);

		if (this.isNewComment) {
			saveButton.setText("Add Comment");
			saveButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					CommentModel theCommentModel = new CommentModel();

					// Get the comment
					EditText commentField = (EditText) findViewById(R.id.commentTextField);
					theCommentModel.setCommentText(commentField.getText()
							.toString());

					theController.addComment(theCommentModel, null);

					finish();
				}
			});

			// hide gallery thumbnail
			//ImageView galeryThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
			// galeryThumbnail.setVisibility(View.INVISIBLE);

		} else {
			saveButton.setText("Update Comment");

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
		return isNewComment;
	}
}
