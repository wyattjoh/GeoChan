package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.TopicModel;

public class TopicViewController {
	FavoriteTopicModelList favoriteTopicModelList;
	
	public TopicViewController() {
		favoriteTopicModelList = FavoriteTopicModelList.getInstance();
	}
	
	public void toggleFavorite(TopicModel theModel) {
		if (theModel.isFavorite()) {
			theModel.setIsFavorite(false);
			favoriteTopicModelList.remove(theModel);
		}
		else {
			theModel.setIsFavorite(true);
			favoriteTopicModelList.add(theModel);
		}
	}
}
