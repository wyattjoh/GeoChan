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
import ca.ualberta.cs.models.FavoriteCommentModelList;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.ReadLaterCommentModelList;
import ca.ualberta.cs.models.ReadLaterTopicModelList;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.ElasticSearchProvider;

/**
 * @author wyatt
 * 
 */
public enum MainActivityFragmentComponent {
	TOPICS_LIST {
		protected TopicListViewAdapter adapter;

		@Override
		protected void setupAdapter(ListView topicListView,
				FragmentActivity theActivity) {
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
		protected TopicListViewAdapter favoriteTopicAdapter;

		@Override
		protected void setupAdapter(ListView topicListView,
				FragmentActivity theActivity) {
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
		protected CommentListViewAdapter favoriteCommentAdapter;

		@Override
		protected void setupAdapter(ListView topicListView,
				FragmentActivity theActivity) {
			// Setup the comment adapter
			FavoriteCommentModelList theFavoriteCommentModelList = FavoriteCommentModelList
					.getInstance();

			favoriteCommentAdapter = new CommentListViewAdapter(theActivity,
					theFavoriteCommentModelList);
			topicListView.setAdapter(favoriteCommentAdapter);

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
			return "Favorite Comments";
		}

	},
	READ_LATER_TOPICS {
		protected TopicListViewAdapter adapter;

		@Override
		public void destroy() {
			// Unregister the topics
			ReadLaterTopicModelList theReadLaterTopicModelList = ReadLaterTopicModelList
					.getInstance();
			theReadLaterTopicModelList.unRegisterListeningAdapter(adapter);
		}

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "Read Later Topics";
		}

		@Override
		protected void setupAdapter(ListView topicListView,
				FragmentActivity theActivity) {
			// Setup the comment adapter
			ReadLaterTopicModelList theReadLaterTopicModelList = ReadLaterTopicModelList
					.getInstance();

			adapter = new TopicListViewAdapter(theActivity,
					theReadLaterTopicModelList);
			topicListView.setAdapter(adapter);

			theReadLaterTopicModelList.registerListeningAdapter(adapter);
		}

	},
	READ_LATER_COMMENTS {
		protected CommentListViewAdapter adapter;

		@Override
		public void destroy() {
			ReadLaterCommentModelList commentList = ReadLaterCommentModelList
					.getInstance();

			commentList.unRegisterListeningAdapter(adapter);
		}

		@Override
		public String getTitle() {
			return "Read Later Comments";
		}

		@Override
		protected void setupAdapter(ListView topicListView,
				FragmentActivity theActivity) {
			ReadLaterCommentModelList commentList = ReadLaterCommentModelList
					.getInstance();

			adapter = new CommentListViewAdapter(theActivity, commentList);
			topicListView.setAdapter(adapter);

			commentList.registerListeningAdapter(adapter);
		}

	};

	abstract protected void setupAdapter(ListView topicListView,
			FragmentActivity theActivity);

	public void setupView(LayoutInflater theInflater, LinearLayout theLayout,
			FragmentActivity theActivity) {
		View rootView = theInflater.inflate(R.layout.fragment_single_post_list,
				null);
		theLayout.addView(rootView);

		// Populate list view
		ListView topicListView = (ListView) rootView
				.findViewById(R.id.postListView);

		// Setup the adapter
		setupAdapter(topicListView, theActivity);
	}

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
		case 4:
			return READ_LATER_TOPICS;
		case 5:
			return READ_LATER_COMMENTS;
		}
		return null;
	}

	public static int getSize() {
		return 5;
	}

	abstract public String getTitle();
}
