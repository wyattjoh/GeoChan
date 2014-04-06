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
import ca.ualberta.cs.adapters.CommentListViewAdapter;
import ca.ualberta.cs.adapters.TopicListViewAdapter;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.FavoriteCommentModelList;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.ReadLaterTopicModelList;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.ElasticSearchProvider;

/**
 * @author wyatt
 * 
 */
public enum MainActivityFragmentComponent {
	TOPICS_LIST {
		public TopicListViewAdapter adapter;

		@Override
		public void setupView(LayoutInflater theInflater,
				LinearLayout theLayout, FragmentActivity theActivity) {
			View rootView = theInflater.inflate(
					R.layout.fragment_single_post_list, null);
			theLayout.addView(rootView);

			// Populate list view
			ListView topicListView = (ListView) rootView
					.findViewById(R.id.postListView);

			// Setup the adapter
			TopicModelList theTopicModelList = TopicModelList.getInstance();

			adapter = new TopicListViewAdapter(theActivity, theTopicModelList);
			topicListView.setAdapter(adapter);

			theTopicModelList.registerListeningAdapter(adapter);

			ElasticSearchProvider.getProvider().getTopics(0, 30);
		}

		@Override
		public void destroy() {
			TopicModelList theTopicModelList = TopicModelList.getInstance();
			theTopicModelList.unRegisterListeningAdapter(adapter);
		}

		@Override
		public String getTitle() {
			return "Topics";
		}

	},

	FAVORITE_TOPICS {
		public TopicListViewAdapter favoriteTopicAdapter;

		@Override
		public void setupView(LayoutInflater theInflater,
				LinearLayout theLayout, FragmentActivity theActivity) {
			View rootView = theInflater.inflate(
					R.layout.fragment_single_post_list, null);
			theLayout.addView(rootView);

			// Populate list view for topics
			ListView topicListView = (ListView) rootView
					.findViewById(R.id.postListView);

			// Setup the topic adapter
			FavoriteTopicModelList theFavoriteTopicModelList = FavoriteTopicModelList
					.getInstance();

			favoriteTopicAdapter = new TopicListViewAdapter(theActivity,
					theFavoriteTopicModelList);
			topicListView.setAdapter(favoriteTopicAdapter);

			theFavoriteTopicModelList
					.registerListeningAdapter(favoriteTopicAdapter);
		}

		@Override
		public void destroy() {
			// Unregister the topics
			FavoriteTopicModelList theFavoriteTopicModelList = FavoriteTopicModelList
					.getInstance();
			theFavoriteTopicModelList
					.unRegisterListeningAdapter(favoriteTopicAdapter);
		}

		@Override
		public String getTitle() {
			return "Favorite Topics";
		}

	},
	FAVORITE_COMMENTS {
		public CommentListViewAdapter favoriteCommentAdapter;

		@Override
		public void setupView(LayoutInflater theInflater,
				LinearLayout theLayout, FragmentActivity theActivity) {
			View rootView = theInflater.inflate(
					R.layout.fragment_single_post_list, null);
			theLayout.addView(rootView);

			// Setup the comment adapter
			FavoriteCommentModelList theFavoriteCommentModelList = FavoriteCommentModelList
					.getInstance();

			ListView commentsListView = (ListView) rootView.findViewById(R.id.postListView);

			favoriteCommentAdapter = new CommentListViewAdapter(theActivity,
					theFavoriteCommentModelList);
			commentsListView.setAdapter(favoriteCommentAdapter);

			theFavoriteCommentModelList
					.registerListeningAdapter(favoriteCommentAdapter);

		}

		@Override
		public void destroy() {
			// Unregister the comments
			FavoriteCommentModelList theFavoriteCommentModelList = FavoriteCommentModelList
					.getInstance();
			theFavoriteCommentModelList
					.unRegisterListeningAdapter(favoriteCommentAdapter);
		}

		@Override
		public String getTitle() {
			return "Favrorite Comments";
		}

	};

	abstract public void setupView(LayoutInflater theInflater,
			LinearLayout theLayout, FragmentActivity theActivity);

	abstract public void destroy();

	public static MainActivityFragmentComponent getComponentForPosition(
			int position) {
		switch (position) {
		case 1:
			return TOPICS_LIST;
		case 2:
			return FAVORITE_TOPICS;
		case 3:
			return FAVORITE_COMMENTS;
		}
		return null;
	}

	public static int getSize() {
		return 3;
	}
	
	abstract public String getTitle(); 
}
