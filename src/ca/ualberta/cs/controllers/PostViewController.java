package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.UserModel;

/**
 * Maintains the views for posts
 * 
 * @author wyatt
 * 
 * @param <T>
 */
abstract public class PostViewController<T extends PostModel> {

	protected PostModelList<T> favModelList;
	protected PostModelList<T> readModelList;

	abstract protected void updatePost(T thePost);

	/**
	 * Toggles a favorite post
	 * 
	 * @param theModel
	 */
	public void toggleFavorite(T theModel) {
		if (theModel.isFavorite()) {
			theModel.setIsFavorite(false);
			favModelList.remove(theModel);
		} else {
			theModel.setIsFavorite(true);
			favModelList.add(theModel);
		}
	}

	/**
	 * Toggles a read later post
	 * 
	 * @param theModel
	 */
	public void toggleReadLater(T theModel) {
		if (theModel.isReadLater()) {
			theModel.setIsReadLater(false);
			readModelList.remove(theModel);
		} else {
			theModel.setIsReadLater(true);
			readModelList.add(theModel);
		}
	}

	/**
	 * Increases the score of a post
	 * 
	 * @param theModel
	 * @return
	 */
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

	/**
	 * Decreases the score of a post
	 * 
	 * @param theModel
	 * @return
	 */
	public Boolean decreaseScore(T theModel) {
		UserModel theUser = ActiveUserModel.getInstance().getUser();

		String postId = theModel.getId();

		if (theUser.canDownVote(postId)) {
			theUser.performDownVote(postId);
			theModel.decrementScore();
			updatePost(theModel);
			return true;
		} else {
			return false;
		}
	}

}