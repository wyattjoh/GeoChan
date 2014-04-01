/**
 * 
 */
package ca.ualberta.cs.views;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * @author wyatt
 * 
 */
public class CommentViewActivity extends PostViewActivity<CommentModel> {

	@Override
	void setTitleText() {
		// Hide the titleView
		TextView titleView = (TextView) findViewById(R.id.titleTextView);
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
		CommentModelList.getInstance().popFromSelectionStack();
	}

	@Override
	protected OnClickListener getFavoriteOnClickListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CommentModel getSelectedModel() {
		// TODO Auto-generated method stub
		return getPostModelList(null).getLastSelection();
	}

	@Override
	protected PostModelList<CommentModel> getPostModelList(
			MainActivityFragmentComponent component) {
		if (this.thePostModelList != null) {
			return this.thePostModelList;
		} else {
			if (component != null) {
				return this.thePostModelList = component
						.provideCommentPostModelList();
			} else {
				return this.thePostModelList = CommentModelList.getInstance();
			}
		}
	}
}
