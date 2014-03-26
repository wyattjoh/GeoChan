/**
 * 
 */
package ca.ualberta.cs.views;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.TopicViewController;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * @author wyatt
 *
 */
public class TopicViewActivity extends PostViewActivity<TopicModel> {

	@Override
	protected void getSelectedModel() {
		// Get the model list
		TopicModelList topicModelList = TopicModelList.getInstance();
		
		this.theModel = topicModelList.getSelection();
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.cellActiveArea:
			newPost(EditTopicActivity.class);
			return true;
		case R.id.action_settings:
			startSettingsActivity();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.views.PostViewActivity#populateView()
	 */
	@Override
	protected void populateView() {
		// TODO Auto-generated method stub
		super.populateView();
		
		// Favorite Button
		ImageButton favoriteButton = (ImageButton) findViewById(R.id.favoriteButton);

		if (theModel.isFavorite()) {
			favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
		} else {
			favoriteButton
					.setImageResource(android.R.drawable.btn_star_big_off);
		}
	}

	private OnClickListener favoriteOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			ImageView favoritesButton = (ImageView) v;
			TopicViewController.toggleFavorite(theModel);
			
			if (theModel.isFavorite()) {
				// Is already a favorite! Must unfavorite now...
				favoritesButton.setImageResource(android.R.drawable.btn_star_big_on);
			}
			else {
				// Not a favorite! Lets add it!
				favoritesButton.setImageResource(android.R.drawable.btn_star_big_off);
			}
		}
	};
}