package ca.ualberta.cs.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.CommentModelController;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.TopicModelList;

public class EditCommentActivity extends EditPostActivity<CommentModel> {

	private CommentModelController theController;
	OnClickListener newOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// Get the comment
			EditText commentField = (EditText) findViewById(R.id.commentTextField);
			String theComment = commentField.getText().toString();

			if (theComment.length() <= 0) {
				failedDueToReason("Cannot create a comment without any text!");
				return;
			}

			theModel.setCommentText(theComment);

			if (imageBitmap != null) {
				// add the picture
				theModel.setPicture(imageBitmap);
			}
			theModel.setLocation(theLocation);
			theController.addComment(theModel, theEditPostModel.getTheParent());

			finish();
		}
	};

	private OnClickListener updateCommentOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CommentModel comparatorModel = theModel;
			//System.out.println(theModel.getMyParent().getChildrenComments().get(0));

			// Get the comment
			EditText commentField = (EditText) findViewById(R.id.commentTextField);
			String theComment = commentField.getText().toString();

			if (theComment.length() <= 0) {
				failedDueToReason("Cannot create a comment without any text!");
				return;
			}

			theModel.setCommentText(theComment);

			if (imageBitmap != null) {
				// add the picture
				theModel.setPicture(imageBitmap);
			}
			theModel.setLocation(theLocation);
			theController.updateComment(comparatorModel, theModel, theModel.getMyParent());

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

		if (theEditPostModel.isNewPost()) {
			theModel = new CommentModel(ActiveUserModel.getInstance().getUser());
			theModel.setMyParent(theEditPostModel.getTheParent());
		} else {
			theModel = (CommentModel) theEditPostModel.getThePost();
		}

		// Get the controller
		this.theController = new CommentModelController(
				TopicModelList.getInstance());

		// customize UI
		EditText commentEditTitle = (EditText) findViewById(R.id.titleTextField);
		commentEditTitle.setVisibility(View.GONE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.EditPostActivity#getSaveButtonText()
	 */
	@Override
	protected String getSaveButtonText() {
		if (theEditPostModel.isNewPost()) {
			return "Add Comment";
		} else {
			return "Update Comment";
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
		return updateCommentOnClickListener;
	}

	@Override
	protected void populateTitle(PostModel theModel) {
		// lol no comment title
	}
}
