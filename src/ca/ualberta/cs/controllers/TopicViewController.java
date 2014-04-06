package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.TopicModel;

public class TopicViewController extends PostViewController<TopicModel> {
	public TopicViewController() {
		modelList = FavoriteTopicModelList.getInstance();
	}
}
