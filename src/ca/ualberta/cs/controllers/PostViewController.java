package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;

abstract public class PostViewController<T extends PostModel> {

	protected PostModelList<T> modelList;

	public void toggleFavorite(T theModel) {
		if (theModel.isFavorite()) {
			theModel.setIsFavorite(false);
			modelList.remove(theModel);
		} else {
			theModel.setIsFavorite(true);
			modelList.add(theModel);
		}
	}

}