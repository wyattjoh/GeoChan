package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.FavoriteCommentModelList;

public class CommentViewController extends PostViewController<CommentModel> {
	public CommentViewController() {
		modelList = FavoriteCommentModelList.getInstance();
	}
}
