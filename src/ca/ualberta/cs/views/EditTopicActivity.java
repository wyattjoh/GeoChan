package ca.ualberta.cs.views;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.TopicModelController;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.CurrentUserPostModelFactory;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

public class EditTopicActivity extends EditPostActivity<TopicModel> {
	private TopicModelController theController;

	private OnClickListener newOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			theModel = CurrentUserPostModelFactory.newTopicModel();

			// Get the title
			EditText titleField = (EditText) findViewById(R.id.titleTextField);
			String theTitle = titleField.getText().toString();

			if (theTitle.length() <= 0) {
				failedDueToReason("Cannot create a topic with an empty title!");
				return;
			}

			theModel.setTitle(theTitle);

			// Get the comment
			EditText commentField = (EditText) findViewById(R.id.commentTextField);
			String theComment = commentField.getText().toString();

			if (theComment.length() <= 0) {
				failedDueToReason("Cannot create a topic with an empty comment!");
				return;
			}

			theModel.setCommentText(theComment);

			// add the picture
			theModel.setPicture(imageBitmap);
			theModel.setLocation(theLocation);
			theController.newTopic(theModel);

			finish();
		}
	};

	private OnClickListener updateTopicOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// Get the title
			EditText titleField = (EditText) findViewById(R.id.titleTextField);
			String theTitle = titleField.getText().toString();

			if (theTitle.length() <= 0) {
				failedDueToReason("Cannot create a topic with an empty title!");
				return;
			}

			theModel.setTitle(theTitle);

			// Get the comment
			EditText commentField = (EditText) findViewById(R.id.commentTextField);
			String theComment = commentField.getText().toString();

			if (theComment.length() <= 0) {
				failedDueToReason("Cannot create a topic with an empty comment!");
				return;
			}

			theModel.setCommentText(theComment);

			// add the picture
			theModel.setPicture(imageBitmap);
			theModel.setLocation(theLocation);
			theController.updateTopic(TopicModelList.getInstance());

			finish();
		}

	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.EditPostActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Get the controller
		this.theController = new TopicModelController();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.EditPostActivity#getSaveButtonText()
	 */
	@Override
	protected String getSaveButtonText() {
		if (theEditPostModel.isNewPost()) {
			return "New Topic";
		} else {
			return "Update Topic";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.EditPostActivity#getNewOnClickListener()
	 */
	@Override
	protected OnClickListener getNewOnClickListener() {
		return newOnClickListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.EditPostActivity#getUpdateOnClickListener()
	 */
	@Override
	protected OnClickListener getUpdateOnClickListener() {
		return updateTopicOnClickListener;
	}

	@Override
	protected void populateTitle(PostModel theModel) {
		TextView title = (TextView) findViewById(R.id.titleTextField);
		title.setText(((TopicModel) theModel).getTitle());
	}

	@Override
	protected TopicModel getUpcastedModel() {
		// TODO Auto-generated method stub
		return (TopicModel) theEditPostModel.getThePost();
	}

	/**
	 * In the event that we are editing a topic, have it's location default to the users location
	 */
	@Override
	protected Location getNewLocation() {
		return ActiveUserModel.getInstance().getUser().getLocation();
	}
}
