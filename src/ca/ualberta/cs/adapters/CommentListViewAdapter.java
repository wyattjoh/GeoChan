package ca.ualberta.cs.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.SelectedCommentModelList;
import ca.ualberta.cs.views.CommentViewActivity;

/**
 * 
 * Manages a list of comments
 * 
 * @author wyatt
 * 
 */
public class CommentListViewAdapter extends PostListViewAdapter<CommentModel> {
	private final static int excerptLength = 50;

	public CommentListViewAdapter(Activity activity,
			PostModelList<CommentModel> arrayList) {
		super(activity, arrayList);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.ualberta.cs.adapters.PostListViewAdapter#populateCellTitle(android
	 * .view.View, ca.ualberta.cs.models.PostModel)
	 */
	@Override
	protected void populateCellTitle(View theView, CommentModel thePost) {
		// List of comments, display an excerpt of their comment

		String commentText = thePost.getCommentText();

		if (commentText != null) {
			TextView titleText = (TextView) theView
					.findViewById(R.id.textViewTitle);
			titleText.setText(trimString(commentText, excerptLength));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.adapters.PostListViewAdapter#getViewClass()
	 */
	@Override
	protected Class<?> getViewClass() {
		return CommentViewActivity.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.adapters.PostListViewAdapter#setSelectedList()
	 */
	@Override
	protected void setSelectedList() {
		SelectedCommentModelList.setTopicList(theArrayList);
	}
}
