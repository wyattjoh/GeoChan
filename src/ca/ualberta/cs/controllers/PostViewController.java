package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;

abstract public class PostViewController<T extends PostModel> {

	protected PostModelList<T> favModelList;
	protected PostModelList<T> readModelList;

	public void toggleFavorite(T theModel) {
		if (theModel.isFavorite()) {
			theModel.setIsFavorite(false);
			favModelList.remove(theModel);
		} else {
			theModel.setIsFavorite(true);
			favModelList.add(theModel);
		}
	}

	public void toggleReadLater(T theModel) {
		if (theModel.isReadLater()) {
			theModel.setIsReadLater(false);
			;
			readModelList.remove(theModel);
		} else {
			theModel.setIsReadLater(true);
			readModelList.add(theModel);
		}
	}

	private void modifyScore(int value) {

	}

	public void increaseScore() {
		modifyScore(1);
	}

	public void decreaseScore() {
		modifyScore(-1);
	}

}