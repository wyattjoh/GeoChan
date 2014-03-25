package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.TopicModel;

public class TopicViewController {
	public static void toggleFavorite(TopicModel theModel) {
		FavoriteTopicModelList favoriteTopicModelList = FavoriteTopicModelList.getInstance();
		
		if (theModel.isFavorite()) {
			theModel.setIsFavorite(false);
			favoriteTopicModelList.remove(theModel);
		}
		else {
			favoriteTopicModelList.add(theModel);
			theModel.setIsFavorite(true);
		}
	}
}
