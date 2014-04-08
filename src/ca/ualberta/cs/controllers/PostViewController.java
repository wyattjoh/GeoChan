package ca.ualberta.cs.controllers;

import android.util.Log;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.UserModel;

abstract public class PostViewController<T extends PostModel> {

	protected PostModelList<T> favModelList;
	protected PostModelList<T> readModelList;

	abstract protected void updatePost(T thePost);

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
			readModelList.remove(theModel);
		} else {
			theModel.setIsReadLater(true);
			readModelList.add(theModel);
		}
	}

	public Boolean increaseScore(T theModel) {
		UserModel theUser = ActiveUserModel.getInstance().getUser();

		String postId = theModel.getId();

		if (theUser.canUpVote(postId)) {
			theUser.performUpvote(postId);
			theModel.incrementScore();
			updatePost(theModel);
			return true;
		} else {
			return false;
		}
	}

	public Boolean decreaseScore(T theModel) {
		UserModel theUser = ActiveUserModel.getInstance().getUser();

		String postId = theModel.getId();

		if (theUser.canDownVote(postId)) {
			Log.w("PostViewController", "Can perform down vote!");
			theUser.performDownVote(postId);
			theModel.decrementScore();
			updatePost(theModel);
			return true;
		} else {
			return false;
		}
	}

}