package ca.ualberta.cs.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * A dummy fragment representing a section of the app, but that simply displays
 * dummy text.
 */
public class TopicListActivityFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	private PostListViewAdapter<TopicModel> listAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// random generated code...
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);

		// get fragment number
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

		switch (sectionNumber) {
		case 1:
			// create topic list is a stand in for the actual data
			PostListController.createCommentedTopics(null);
			populateFragment(rootView, TopicModelList.getInstance());
			setConnectionStatus(rootView);
			break;
		case 2:
			break;
		case 3:
			break;
		}

		return rootView;
	}

	public void populateFragment(View theRootView, TopicModelList topicModelList) {
		// get title & list view adapter
		ListView listView = (ListView) theRootView
				.findViewById(R.id.postListView);

		listAdapter = new TopicListViewAdapter<TopicModel>(getActivity(),
				topicModelList.getTopicModelArrayList());

		// set adapter
		listView.setAdapter(listAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 * 
	 * Added a refresh call
	 */
	public void onResume() {
		super.onResume();

		// Update
		update();
	}
	
	/*
	 * Updates all lists
	 */
	public void update() {
		// Refresh the list
		if (this.listAdapter != null) {
			this.listAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * set the connection text taken from http://stackoverflow.com/questions/
	 * 14198605/how-to-hide-a-textview-in-simpleadapter
	 * 
	 * @param theRootView
	 */
	public void setConnectionStatus(View theRootView) {
		TextView connectionStatus = (TextView) theRootView
				.findViewById(R.id.connectionStatusText);

		// TODO toggle text based on connection status
		if (null != null) {
			connectionStatus.setVisibility(View.GONE);
		} else {
			connectionStatus.setTextColor(Color.RED);
		}
	}
}