/**
 * 
 */
package ca.ualberta.cs.views;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.adapters.TopicListViewAdapter;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.TopicModelList;

/**
 * @author wyatt
 * 
 */
public enum MainActivityFragmentComponent {
	TOPICS_LIST {
		public TopicListViewAdapter adapter;
		
		@Override
		public void setupView(LayoutInflater theInflater, LinearLayout theLayout, FragmentActivity theActivity) {
			View rootView = theInflater.inflate(R.layout.fragment_single_post_list, null);
			theLayout.addView(rootView);

			// Populate list view
			ListView topicListView = (ListView) rootView
					.findViewById(R.id.postListView);

			// Setup the adapter
			TopicModelList theTopicModelList = TopicModelList.getInstance();

			adapter = new TopicListViewAdapter(theActivity,
					theTopicModelList.getArrayList());
			topicListView.setAdapter(adapter);
			
			theTopicModelList.registerListeningAdapter(adapter);
		}

		@Override
		public void destroy() {
			TopicModelList theTopicModelList = TopicModelList.getInstance();
			theTopicModelList.unRegisterListeningAdapter(adapter);
		}

	},
	
	FAVORITES_VIEW {
		public TopicListViewAdapter adapter;

		@Override
		public void setupView(LayoutInflater theInflater,
				LinearLayout theLayout, FragmentActivity theActivity) {
			View rootView = theInflater.inflate(R.layout.fragment_split_post_list, null);
			theLayout.addView(rootView);

			// Populate list view for topics
			ListView topicListView = (ListView) rootView
					.findViewById(R.id.listTopics);

			// Setup the adapter
			FavoriteTopicModelList theFavoriteTopicModelList = FavoriteTopicModelList.getInstance();

			adapter = new TopicListViewAdapter(theActivity,
					theFavoriteTopicModelList.getArrayList());
			topicListView.setAdapter(adapter);
			
			theFavoriteTopicModelList.registerListeningAdapter(adapter);
			
		}

		@Override
		public void destroy() {
			FavoriteTopicModelList theFavoriteTopicModelList = FavoriteTopicModelList.getInstance();
			theFavoriteTopicModelList.unRegisterListeningAdapter(adapter);			
		}
		
	},
	
	READ_LATER {
		public TopicListViewAdapter adapter;

		@Override
		public void setupView(LayoutInflater theInflater,
				LinearLayout theLayout, FragmentActivity theActivity) {
			View rootView = theInflater.inflate(R.layout.fragment_split_post_list, null);
			theLayout.addView(rootView);

			// Populate list view for topics
			ListView topicListView = (ListView) rootView
					.findViewById(R.id.listTopics);

			// Setup the adapter
			TopicModelList theTopicModelList = TopicModelList.getInstance();

			adapter = new TopicListViewAdapter(theActivity,
					theTopicModelList.getArrayList());
			topicListView.setAdapter(adapter);
			
			theTopicModelList.registerListeningAdapter(adapter);
			
		}

		@Override
		public void destroy() {
			TopicModelList theTopicModelList = TopicModelList.getInstance();
			theTopicModelList.unRegisterListeningAdapter(adapter);			
		}
		
	};

	abstract public void setupView(LayoutInflater theInflater, LinearLayout theLayout, FragmentActivity theActivity);
	abstract public void destroy();

	public static MainActivityFragmentComponent getComponentForPosition(
			int position) {
		switch (position) {
		case 1: return TOPICS_LIST;
		case 2: return FAVORITES_VIEW;
		case 3: return READ_LATER;
		}
		throw new RuntimeException("Invalid position passed.");
	}
}
