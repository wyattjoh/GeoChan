package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.FavoriteCommentModelList;
import ca.ualberta.cs.models.ReadLaterCommentModelList;

public class CommentViewController extends PostViewController<CommentModel> {
	public CommentViewController() {
		favModelList = FavoriteCommentModelList.getInstance();
		readModelList = ReadLaterCommentModelList.getInstance();
	}
}
