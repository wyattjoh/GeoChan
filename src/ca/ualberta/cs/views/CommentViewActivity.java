/**
 * 
 */
package ca.ualberta.cs.views;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.CommentViewController;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.EditPostModel;
import ca.ualberta.cs.models.SelectedCommentModelList;

/**
 * @author wyatt
 * 
 */
public class CommentViewActivity extends PostViewActivity<CommentModel> {

	private static final int IS_COMMENT = 1;

	public CommentViewActivity() {
		theController = new CommentViewController();
	}

	@Override
	protected CommentModel getSelectedModel() {
		return SelectedCommentModelList.getCommentList().getLastSelection();
	}

	@Override
	void setTitleText() {
		// Hide the titleView
		TextView titleView = (TextView) headerView.findViewById(R.id.titleTextView);
		titleView.setVisibility(View.GONE);

		// Fix action bar
		getActionBar().setTitle(theModel.getCommentText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SelectedCommentModelList.getCommentList().popFromSelectionStack();
	}

	@Override
	protected void editPost() {
		EditPostModel theEditPostModel = EditPostModel.getInstance();
		theEditPostModel.setThePost(theModel);

		Intent intent = new Intent(this, EditCommentActivity.class);
		startActivity(intent);
	}

	@Override
	public void onClick_OpenMap(View theView) {
		// TODO Auto-generated method stub
		Intent mapIntent = new Intent(this, MapViewActivity.class);
		mapIntent.putExtra("selfLocation", this.theModel.getLocation());
		mapIntent.putExtra("postType", IS_COMMENT);
		startActivity(mapIntent);
	}

	@Override
	protected String getTitleString() {
		return this.theModel.getCommentText();
	}
}
