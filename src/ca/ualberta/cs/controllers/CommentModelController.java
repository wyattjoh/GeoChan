package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.providers.ElasticSearchProvider;

public class CommentModelController {
	private PostModelList<TopicModel> thePostModelList;

	public CommentModelController(PostModelList<TopicModel> thePostModelList) {
		this.thePostModelList = thePostModelList;
	}

	public void addComment(CommentModel theComment, PostModel theParent) {
		theParent.addChildComment(theComment);
		theComment.setMyParent(theParent);

		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider.getProvider();

		theProvider.updateTopic((TopicModel) theParent.getMyFirstAncestor(),
				thePostModelList);
	}
}
