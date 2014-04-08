package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.FavoriteCommentModelList;
import ca.ualberta.cs.models.ReadLaterCommentModelList;
import ca.ualberta.cs.models.TopicModelList;

/**
 * Maintains the view of comments
 * 
 * @author wyatt
 * 
 */
public class CommentViewController extends PostViewController<CommentModel> {
	public CommentViewController() {
		favModelList = FavoriteCommentModelList.getInstance();
		readModelList = ReadLaterCommentModelList.getInstance();
	}

	@Override
	protected void updatePost(CommentModel thePost) {
		CommentModelController theController = new CommentModelController(
				TopicModelList.getInstance());
		theController.updateComment(thePost);
	}
}
