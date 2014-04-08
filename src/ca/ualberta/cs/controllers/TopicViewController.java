package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.ReadLaterTopicModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

public class TopicViewController extends PostViewController<TopicModel> {
	public TopicViewController() {
		favModelList = FavoriteTopicModelList.getInstance();
		readModelList = ReadLaterTopicModelList.getInstance();
	}

	@Override
	protected void updatePost(TopicModel thePost) {
		TopicModelController theController = new TopicModelController();
		theController.updateTopic(TopicModelList.getInstance());
	}
}
