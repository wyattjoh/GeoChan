package ca.ualberta.cs.views;

import java.util.ArrayList;
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
import ca.ualberta.cs.adapters.CommentListViewAdapter;
import ca.ualberta.cs.adapters.PostListViewAdapter;
import ca.ualberta.cs.adapters.TopicListViewAdapter;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.DummyPostListFactory;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.NetworkModel;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UserModel;
import ca.ualberta.cs.providers.LocationProvider;

/**
 * A dummy fragment representing a section of the application, but that simply
 * displays dummy text.
 */
public class TopicListActivityFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	private PostListViewAdapter<?> listAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// get fragment number
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

		// get fragment view
		View rootView = inflater.inflate(R.layout.fragment_post_list,
				container, false);

		switch (sectionNumber) {

		// TOPICS case
		case 1:
			// get specific fragment view and populate
			populateFragment(
					(ListView) rootView.findViewById(R.id.postListView),
					TopicModelList.getInstance().getArrayList());
			// Populate list view
			// TODO: Get list!

			// get specific fragment view and populate
			// TODO: Populate
			break;

		// FAVORITES case
		case 2:
			// get specific fragment view and populate
			populateFragment(
					(ListView) rootView.findViewById(R.id.postListView),
					FavoriteTopicModelList.getInstance().getArrayList());
			
			TextView commentText = (TextView) rootView.findViewById(R.id.commentListText);
			commentText.setVisibility(View.VISIBLE);
			
			populateFragment(
					(ListView) rootView.findViewById(R.id.commentListView),
					DummyPostListFactory.getCommentList(new UserModel(
							"GeoChanComment")));
			
			// Populate list view
			// TODO: Get list!

			// get specific fragment view and populate
			// TODO: Populate
			break;

		// READ LATER case
		case 3:

			// get specific fragment view and populate
			populateFragment(
					(ListView) rootView.findViewById(R.id.postListView),
					TopicModelList.getInstance().getArrayList());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onStart()
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		TopicModelList.getInstance().registerListeningAdapter(listAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onStop()
	 */
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		TopicModelList.getInstance().unRegisterListeningAdapter(listAdapter);
	}

	/**
	 * Takes the ListView element and a model list and sets the appropriate
	 * adapter
	 * 
	 * @param theListView
	 * @param theModelList
	 */
	public void populateFragment(ListView theListView, ArrayList<?> theModelList) {

		if (!theModelList.isEmpty()) {
			if (theModelList.get(0).getClass().equals(TopicModel.class)) {
				listAdapter = new TopicListViewAdapter<TopicModel>(
						getActivity(), (ArrayList<TopicModel>) theModelList);
			} else if (theModelList.get(0).getClass()
					.equals(CommentModel.class)) {
				listAdapter = new CommentListViewAdapter<CommentModel>(
						getActivity(), (ArrayList<CommentModel>) theModelList);
			}
		}
		// set adapter
		theListView.setAdapter(listAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 * 
	 * Added a refresh call
	 */
	@Override
	public void onResume() {
		super.onResume();

		// Update
		refresh();
	}

	/**
	 * Updates all lists
	 */
	public void refresh() {
		// Refresh the list
		if (this.listAdapter != null) {
			this.listAdapter.notifyDataSetChanged();
		}

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

		// update the location
		ActiveUserModel
				.getShared()
				.getUser()
				.setLocation(
						LocationProvider.shared(getActivity()).getLocation());
	}

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