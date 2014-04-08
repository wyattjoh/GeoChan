/**
 * 
 */
package ca.ualberta.cs.views;

import java.util.ArrayList;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.TopicViewController;
import ca.ualberta.cs.models.EditPostModel;
import ca.ualberta.cs.models.PostModelUpgradeFactory;
import ca.ualberta.cs.models.SelectedTopicModelList;
import ca.ualberta.cs.models.TopicModel;

/**
 * @author wyatt
 * 
 *         Responsible for dispalying a TopicModel
 */
public class TopicViewActivity extends PostViewActivity<TopicModel> {

	private static final int IS_TOPIC = 0;

	/**
	 * Creates a new topic activity
	 */
	public TopicViewActivity() {
		theController = new TopicViewController();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.PostViewActivity#getSelectedModel()
	 */
	@Override
	protected TopicModel getSelectedModel() {
		TopicModel theTopic = SelectedTopicModelList.getTopicList()
				.getLastSelection();
		return PostModelUpgradeFactory.upgradePostModel(theTopic);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.PostViewActivity#setTitleText()
	 */
	@Override
	void setTitleText() {
		TextView titleView = (TextView) this.headerView
				.findViewById(R.id.titleTextView);
		titleView.setText(theModel.getTitle());

		// Fix action bar
		getActionBar().setTitle(theModel.getTitle());
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
		SelectedTopicModelList.getTopicList().popFromSelectionStack();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.PostViewActivity#editPost()
	 */
	@Override
	protected void editPost() {
		// TODO Auto-generated method stub
		EditPostModel theEditPostModel = EditPostModel.getInstance();
		theEditPostModel.setTheParent(null);
		theEditPostModel.setThePost(theModel);

		Intent intent = new Intent(this, EditTopicActivity.class);
		startActivity(intent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.ualberta.cs.views.PostViewActivity#onClick_OpenMap(android.view.View)
	 */
	@Override
	public void onClick_OpenMap(View theView) {
		// Build intent
		Intent mapIntent = new Intent(this, MapViewActivity.class);

		// Get the locations array
		ArrayList<Location> allLocations = theModel.getLocationMapArray();

		// Put it into a bundle
		Bundle b = new Bundle();
		b.putParcelableArrayList("allPostLocations", allLocations);

		// Add it to the intent
		mapIntent.putExtra("locationBundle", b);
		mapIntent.putExtra("selfLocation", this.theModel.getLocation());
		mapIntent.putExtra("postType", IS_TOPIC);

		// Start the activity
		startActivity(mapIntent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.views.PostViewActivity#getTitleString()
	 */
	@Override
	protected String getTitleString() {
		return this.theModel.getTitle();
	}
}