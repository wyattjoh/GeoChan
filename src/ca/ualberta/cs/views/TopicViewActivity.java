/**
 * 
 */
package ca.ualberta.cs.views;

import java.util.ArrayList;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
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
	
	private static final int IS_TOPIC = 0;
	private static final int Location = 0;
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
		theEditPostModel.setTheParent(null);
		theEditPostModel.setThePost(theModel);
		
		Intent intent = new Intent(this, EditTopicActivity.class);
		startActivity(intent);
	}

	@Override
	public void onClick_OpenMap(View theView) {
		// TODO Auto-generated method stub
		Intent mapIntent = new Intent(this, MapViewActivity.class);
		ArrayList<Location> allLocations = theModel.getLocationMapArray();
		for(Location loc: allLocations){
			Log.i("Passing Locations", String.valueOf(loc.getLatitude()+","+String.valueOf(loc.getLongitude())));
		}
		Bundle b = new Bundle();
		b.putParcelableArrayList("allPostLocations", allLocations);
		mapIntent.putExtra("locationBundle", b);
		mapIntent.putExtra("selfLocation", this.theModel.getLocation());
		mapIntent.putExtra("postType", IS_TOPIC);
		startActivity(mapIntent);
	}
}