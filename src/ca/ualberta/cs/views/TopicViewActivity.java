/**
 * 
 */
package ca.ualberta.cs.views;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.TopicViewController;
import ca.ualberta.cs.models.EditPostModel;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * @author wyatt
 * 
 */
public class TopicViewActivity extends PostViewActivity<TopicModel> {
	private TopicViewController theTopicViewController;

	public TopicViewActivity() {
		theTopicViewController = new TopicViewController();
	}

	@Override
	protected TopicModel getSelectedModel() {
		return TopicModelList.getInstance().getLastSelection();
	}

	@Override
	void setTitleText() {
		TextView titleView = (TextView) findViewById(R.id.titleTextView);
		titleView.setText(theModel.getTitle());

		// Fix action bar
		getActionBar().setTitle(theModel.getTitle());
	}

	@Override
	protected OnClickListener getFavoriteOnClickListener() {
		return favoriteOnClickListener;
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
		TopicModelList.getInstance().popFromSelectionStack();
	}

	private OnClickListener favoriteOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ImageView favoritesButton = (ImageView) v;
			theTopicViewController.toggleFavorite(theModel);

			if (theModel.isFavorite()) {
				// Is already a favorite! Must unfavorite now...
				favoritesButton
						.setImageResource(android.R.drawable.btn_star_big_on);
			} else {
				// Not a favorite! Lets add it!
				favoritesButton
						.setImageResource(android.R.drawable.btn_star_big_off);
			}
		}
	};

	@Override
	protected void editPost() {
		// TODO Auto-generated method stub
		EditPostModel theEditPostModel = EditPostModel.getInstance();
		theEditPostModel.setThePost(theModel);
		
		Intent intent = new Intent(this, EditTopicActivity.class);
		startActivity(intent);
	}
}