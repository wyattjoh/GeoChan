package ca.ualberta.cs.views;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.adapters.TopicListViewAdapter;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.NetworkModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * A dummy fragment representing a section of the application, but that simply
 * displays dummy text.
 */
public class MainActivityFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	private TopicListViewAdapter topicListViewAdapter;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// get fragment number
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

		// get fragment view
		View rootView = inflater.inflate(R.layout.fragment_post_list, container, false);

		switch (sectionNumber) {
		// TOPICS case
		case 1:
			// Populate list view
			ListView topicListView = (ListView) rootView.findViewById(R.id.postListView);
			TopicModelList theTopicModelList = TopicModelList.getInstance();

			// get specific fragment view and populate
			populateFragment(topicListView, theTopicModelList);
			break;

		// FAVORITES case
		case 2:
			// Populate list view
			// TODO: Get list!

			// get specific fragment view and populate
			// TODO: Populate
			break;

		// READ LATER case
		case 3:
			// Populate list view
			// TODO: Get list!

			// get specific fragment view and populate
			// TODO: Populate
			break;
		}

		// Update the status
		setConnectionStatus(rootView);

		return rootView;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		
		// get fragment number
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

		switch (sectionNumber) {
		// TOPICS case
		case 1:
			// Deconstruct the adapter
			TopicModelList.getInstance().unRegisterListeningAdapter(topicListViewAdapter);
			break;

		// FAVORITES case
		case 2:
			// TODO: Deconstruct the adapter
			break;

		// READ LATER case
		case 3:
			// TODO: Deconstruct the adapter
			break;
		}
	}

	public void populateFragment(ListView theListView, FavoriteTopicModelList theFavoriteTopicModelList) {
		// TODO: Setup the adapter
		
		// TODO: Register the adapter
	}
	
	public void populateFragment(ListView theListView, TopicModelList theTopicModelList) {
		// Setup the adapter
		topicListViewAdapter = new TopicListViewAdapter(getActivity(), theTopicModelList.getArrayList());
		theListView.setAdapter(topicListViewAdapter);
		
		// Register the adapter
		theTopicModelList.registerListeningAdapter(topicListViewAdapter);
	}

	/**
	 * Updates all lists
	 */
	public void notifyNetworkStateChanged() {
		// Refresh views
		List<Fragment> fragments = getFragmentManager().getFragments();

		for (Fragment fragment : fragments) {
			// Get the view
			View theView = fragment.getView();

			if (theView != null) {
				// Update connection status
				setConnectionStatus(fragment.getView());
			}
		}
	}

	// TODO: Refactor this whole thing...
	private static enum STATE {
		CONNECTED, NOT_CONNECTED, LOADING
	};

	/**
	 * set the connection text taken from http://stackoverflow.com/questions/
	 * 14198605/how-to-hide-a-textview-in-simpleadapter
	 * 
	 * @param theRootView
	 */
	public void setConnectionStatus(View theRootView) {
		Drawable theConnectionIcon = null;
		String statusText = null;

		STATE state = null;
		if (NetworkModel.isNetworkAvailable(getActivity()
				.getApplicationContext())) {
			state = STATE.CONNECTED;
		} else {
			state = STATE.NOT_CONNECTED;
		}

		switch (state) {
		case CONNECTED:
			break;

		case NOT_CONNECTED:
			theConnectionIcon = getResources().getDrawable(
					R.drawable.ic_action_warning);
			statusText = "Not Connected";
			break;

		case LOADING:
			theConnectionIcon = getResources().getDrawable(
					R.drawable.ic_action_refresh);
			statusText = "Loading...";
			break;
		}

		if (statusText != null) {
			// Make visible
			LinearLayout listStatusBox = (LinearLayout) theRootView
					.findViewById(R.id.listStatusBox);
			listStatusBox.setVisibility(View.VISIBLE);

			// Set the text
			TextView listStatusText = (TextView) theRootView
					.findViewById(R.id.listStatusText);
			listStatusText.setText(statusText);

			// Set the image
			ImageView listStatusIcon = (ImageView) theRootView
					.findViewById(R.id.listStatusIcon);
			listStatusIcon.setImageDrawable(theConnectionIcon);
		} else {
			// Hide bar, its connected!
			LinearLayout listStatusBox = (LinearLayout) theRootView
					.findViewById(R.id.listStatusBox);
			listStatusBox.setVisibility(View.INVISIBLE);
		}
	}
}