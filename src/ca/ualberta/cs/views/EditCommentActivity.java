package ca.ualberta.cs.views;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.CommentModelController;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.TopicModelList;

public class EditCommentActivity extends EditPostActivity<CommentModel> {
	
	private CommentModelController theController;
	OnClickListener newOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// Get the comment
			EditText commentField = (EditText) findViewById(R.id.commentTextField);
			theModel.setCommentText(commentField.getText().toString());
			
			if (imageBitmap != null) {
				// add the picture
				theModel.setPicture(imageBitmap);
			}

			// add to the selected model and then reset the selected
			// model so as to reply to the correct topic
			if (CommentModelList.getInstance().getSelection() == null) {
				theController.addComment(theModel, TopicModelList
						.getInstance().getSelection());
			} else {
				theController.addComment(theModel, CommentModelList
						.getInstance().getSelection());
			}
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
		
		if (this.isNew) {
			theModel = new CommentModel(ActiveUserModel.getInstance().getUser());
		}

		// Get the controller
		this.theController = new CommentModelController(
				TopicModelList.getInstance());

		// customize UI
		EditText commentEditTitle = (EditText) findViewById(R.id.titleTextField);
		commentEditTitle.setVisibility(View.INVISIBLE);
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.views.EditPostActivity#getSaveButtonText()
	 */
	@Override
	protected String getSaveButtonText() {
		if (this.isNew) {
			return "Add Comment";
		}
		else {
			return "Update Comment";
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
