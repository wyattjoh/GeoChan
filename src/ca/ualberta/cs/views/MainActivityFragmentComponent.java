/**
 * 
 */
package ca.ualberta.cs.views;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.adapters.TopicListViewAdapter;
import ca.ualberta.cs.controllers.NetworkInterfaceController;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.ReadLaterTopicModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * @author wyatt
 * 
 */
public enum MainActivityFragmentComponent implements OnItemClickListener {
	TOPICS_LIST {
		public TopicListViewAdapter adapter;

		@Override
		public void setupView(LayoutInflater theInflater,
				LinearLayout theLayout, FragmentActivity theActivity) {
			// Save the activity
			this.theActivity = theActivity;
			
			View rootView = theInflater.inflate(
					R.layout.fragment_single_post_list, null);
			theLayout.addView(rootView);

			// Populate list view
			ListView topicListView = (ListView) rootView
					.findViewById(R.id.postListView);

			// Setup the adapter
			TopicModelList theTopicModelList = TopicModelList.getInstance();

			adapter = new TopicListViewAdapter(theActivity,
					theTopicModelList.getArrayList());
			topicListView.setAdapter(adapter);
			topicListView.setOnItemClickListener(this);

			theTopicModelList.registerListeningAdapter(adapter);

			// Get the network controller
			NetworkInterfaceController nic = NetworkInterfaceController
					.getControllerFromContext(theActivity
							.getApplicationContext());

			// Refresh the posts
			nic.refreshPosts();
		}

		@Override
		public void destroy() {
			TopicModelList theTopicModelList = TopicModelList.getInstance();
			theTopicModelList.unRegisterListeningAdapter(adapter);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// Mark the selected model
			provideTopicPostModelList().addToSelectionStackFromPosition(position);

			// Start intent
			Intent intent = new Intent(this.theActivity, TopicViewActivity.class);
			intent.putExtra(COMPONENT_STRING, this);
			this.theActivity.startActivity(intent);
		}

		@Override
		public PostModelList<TopicModel> provideTopicPostModelList() {
			return TopicModelList.getInstance();
		}

		@Override
		public PostModelList<CommentModel> provideCommentPostModelList() {
			return null;
		}

	},

	FAVORITES_VIEW {
		public TopicListViewAdapter adapter;

		@Override
		public void setupView(LayoutInflater theInflater,
				LinearLayout theLayout, FragmentActivity theActivity) {
			// Save the activity
			this.theActivity = theActivity;
			
			View rootView = theInflater.inflate(
					R.layout.fragment_split_post_list, null);
			theLayout.addView(rootView);

			// Populate list view for topics
			ListView topicListView = (ListView) rootView
					.findViewById(R.id.listTopics);

			// Setup the adapter
			adapter = new TopicListViewAdapter(theActivity,
					provideTopicPostModelList().getArrayList());
			topicListView.setAdapter(adapter);
			topicListView.setOnItemClickListener(this);

			provideTopicPostModelList().registerListeningAdapter(adapter);

		}

		@Override
		public void destroy() {
			provideTopicPostModelList().unRegisterListeningAdapter(adapter);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// Get the model list
			FavoriteTopicModelList favoriteTopicModelList = FavoriteTopicModelList.getInstance();

			// Mark the selected model
			favoriteTopicModelList.addToSelectionStackFromPosition(position);

			// Start intent
			Intent intent = new Intent(this.theActivity, TopicViewActivity.class);
			intent.putExtra(COMPONENT_STRING, this);
			this.theActivity.startActivity(intent);
		}

		@Override
		public PostModelList<TopicModel> provideTopicPostModelList() {
			// TODO Auto-generated method stub
			return FavoriteTopicModelList.getInstance();
		}

		@Override
		public PostModelList<CommentModel> provideCommentPostModelList() {
			// TODO Auto-generated method stub
			return null;
		}

	},

	READ_LATER {
		public TopicListViewAdapter adapter;

		@Override
		public void setupView(LayoutInflater theInflater,
				LinearLayout theLayout, FragmentActivity theActivity) {
			// Save the activity
			this.theActivity = theActivity;
			
			View rootView = theInflater.inflate(
					R.layout.fragment_split_post_list, null);
			theLayout.addView(rootView);

			// Populate list view for topics
			ListView topicListView = (ListView) rootView
					.findViewById(R.id.listTopics);

			// Setup the adapter
			adapter = new TopicListViewAdapter(theActivity,
					provideTopicPostModelList().getArrayList());
			topicListView.setAdapter(adapter);
			topicListView.setOnItemClickListener(this);

			provideTopicPostModelList().registerListeningAdapter(adapter);

		}

		@Override
		public void destroy() {
			ReadLaterTopicModelList theReadLaterList = ReadLaterTopicModelList
					.getInstance();
			theReadLaterList.unRegisterListeningAdapter(adapter);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PostModelList<TopicModel> provideTopicPostModelList() {
			// TODO Auto-generated method stub
			return ReadLaterTopicModelList
					.getInstance();
		}

		@Override
		public PostModelList<CommentModel> provideCommentPostModelList() {
			// TODO Auto-generated method stub
			return null;
		}

	};

	abstract public void setupView(LayoutInflater theInflater,
			LinearLayout theLayout, FragmentActivity theActivity);

	abstract public void destroy();
	
	abstract public PostModelList<TopicModel> provideTopicPostModelList();
	abstract public PostModelList<CommentModel> provideCommentPostModelList();
	
	protected FragmentActivity theActivity;
	
	public static final String COMPONENT_STRING = "componentString"; 

	public static MainActivityFragmentComponent getComponentForPosition(
			int position) {
		switch (position) {
		case 1:
			return TOPICS_LIST;
		case 2:
			return FAVORITES_VIEW;
		case 3:
			return READ_LATER;
		}
		throw new RuntimeException("Invalid position passed.");
	}
}
