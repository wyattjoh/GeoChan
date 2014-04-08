package ca.ualberta.cs.controllers;

import java.util.ArrayList;

import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.FavoriteCommentModelList;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.FollowingPostModelList;
import ca.ualberta.cs.models.ReadLaterCommentModelList;
import ca.ualberta.cs.models.ReadLaterTopicModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.UpdatePackage;
import ca.ualberta.cs.providers.ElasticSearchProvider;

/**
 * Manages the network interface
 * 
 * @author wyatt
 * 
 */
public class NetworkInterfaceController {
	/**
	 * Performs a refresh
	 */
	public static void refreshPosts() {
		// Refresh TopicModelList
		ElasticSearchProvider.getProvider().getTopics(0, 30);

		// Refresh FavoriteTopicTopicModelList
		FollowingPostModelList<TopicModel> topicModelFavsList = FavoriteTopicModelList
				.getInstance();
		ArrayList<UpdatePackage<TopicModel>> topicModelFavs = topicModelFavsList
				.getUpdateablePackages();

		if (topicModelFavs.size() > 0) {
			ElasticSearchProvider.getProvider().performMultiGetTopics(
					topicModelFavs, topicModelFavsList);
		}

		// Refresh FavoriteCommentModelList
		FollowingPostModelList<CommentModel> commentModelFavsList = FavoriteCommentModelList
				.getInstance();
		ArrayList<UpdatePackage<CommentModel>> commentModelFavs = commentModelFavsList
				.getUpdateablePackages();

		if (commentModelFavs.size() > 0) {
			ElasticSearchProvider.getProvider().performMultiGetComments(
					commentModelFavs, commentModelFavsList);
		}

		// Refresh Read later comments
		ReadLaterCommentModelList commentReadLater = ReadLaterCommentModelList
				.getInstance();
		ArrayList<UpdatePackage<CommentModel>> commentReadLaterUpdates = commentReadLater
				.getUpdateablePackages();

		if (commentReadLaterUpdates.size() > 0) {
			ElasticSearchProvider.getProvider().performMultiGetComments(
					commentReadLaterUpdates, commentReadLater);
		}

		// Refresh Read later topics
		ReadLaterTopicModelList topicReadLater = ReadLaterTopicModelList
				.getInstance();
		ArrayList<UpdatePackage<TopicModel>> topicReadLaterUpdate = topicReadLater
				.getUpdateablePackages();

		if (topicReadLaterUpdate.size() > 0) {
			ElasticSearchProvider.getProvider().performMultiGetTopics(
					topicReadLaterUpdate, topicReadLater);
		}
	}
}
