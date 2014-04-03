package ca.ualberta.cs.views;

import java.util.ArrayList;

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
import ca.ualberta.cs.models.EditPostModel;
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

			System.out.println(theEditPostModel.getTheParent().getCommentText());
			theController.addComment(theModel, theEditPostModel.getTheParent());

			finish();
		}
	};
	
	OnClickListener upateOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// Get the comment
			EditText commentField = (EditText) findViewById(R.id.commentTextField);
			String theComment = commentField.getText().toString();
			
			if (theComment.length() <= 0) {
				failedDueToReason("Cannot create a comment without any text!");
				return;
			}
			
			// get model index
			System.out.println("parent model "+ ((PostModel) CommentModelList.getInstance().getLastSelection()).getMyParent() == null);
			Integer modelIndex = TopicModelList.getInstance().getLastSelection().getChildrenComments().indexOf(theModel);

			// set the model text
			theModel.setCommentText(theComment);

			if (imageBitmap != null) {
				// add the picture
				theModel.setPicture(imageBitmap);
			}

			theController.updateComment(theModel, TopicModelList.getInstance().getLastSelection(), modelIndex);

			finish();
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.EditPostActivity#onCreate(android.os.Bundle)
	 */
	protected void setupEditPost() {
		if (theEditPostModel.isNewPost()) {
			theModel = new CommentModel(ActiveUserModel.getInstance().getUser());
		}
		else {
			theModel = (CommentModel) theEditPostModel.getThePost();
		}

		// Get the controller
		this.theController = new CommentModelController(
				TopicModelList.getInstance());

		// customize UI
		EditText commentEditTitle = (EditText) findViewById(R.id.titleTextField);
		commentEditTitle.setVisibility(View.INVISIBLE);
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
		// TODO Auto-generated method stub
		return upateOnClickListener;
	}
}
