package ca.ualberta.cs.views;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.TopicModelController;
import ca.ualberta.cs.models.CurrentUserPostModelFactory;
import ca.ualberta.cs.models.TopicModel;

public class EditTopicActivity extends EditPostActivity<TopicModel> {
	private TopicModelController theController;
		
	private OnClickListener newOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			TopicModel theTopicModel = CurrentUserPostModelFactory.newTopicModel();

			// Get the title
			EditText titleField = (EditText) findViewById(R.id.titleTextField);
			String theTitle = titleField.getText().toString();
			
			if (theTitle.length() <= 0) {
				failedDueToReason("Cannot create a topic with an empty title!");
				return;
			}
			
			theTopicModel.setTitle(theTitle);

			// Get the comment
			EditText commentField = (EditText) findViewById(R.id.commentTextField);
			String theComment = commentField.getText().toString();
			
			if (theComment.length() <= 0) {
				failedDueToReason("Cannot create a topic with an empty comment!");
				return;
			}
			
			theTopicModel.setCommentText(theComment);

			// add the picture
			theTopicModel.setPicture(imageBitmap);
			theTopicModel.setLocation(theLocation);
			theController.newTopic(theTopicModel);

			finish();
		}
	};

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.views.EditPostActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Get the controller
		this.theController = new TopicModelController();
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.views.EditPostActivity#getSaveButtonText()
	 */
	@Override
	protected String getSaveButtonText() {
		if (theEditPostModel.isNewPost()) {
			return "New Topic";
		}
		else {
			return "Update Topic";
		}
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.views.EditPostActivity#getNewOnClickListener()
	 */
	@Override
	protected OnClickListener getNewOnClickListener() {
		return newOnClickListener;
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.views.EditPostActivity#getUpdateOnClickListener()
	 */
	@Override
	protected OnClickListener getUpdateOnClickListener() {
		// TODO Auto-generated method stub
		return null;
	}
}
