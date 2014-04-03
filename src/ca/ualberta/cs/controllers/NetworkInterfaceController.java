package ca.ualberta.cs.controllers;

import ca.ualberta.cs.providers.ElasticSearchProvider;

public class NetworkInterfaceController {
	public static void refreshPosts() {
		// Refresh TopicModelList
		ElasticSearchProvider.getProvider().getTopics(0, 30);

		// TODO: Refresh FavoriteTopicTopicModelList
		// TODO: Refresh FavoriteCommentModelList
	}
}
