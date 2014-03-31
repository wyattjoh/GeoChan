package ca.ualberta.cs.controllers;

import android.content.Context;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.providers.ElasticSearchProvider;

public class CommentModelController {
	private PostModelList<TopicModel> thePostModelList;
	private Context theContext;

	public CommentModelController(PostModelList<TopicModel> thePostModelList,
			Context theContext) {
		this.thePostModelList = thePostModelList;
		this.theContext = theContext;
	}

	public void addComment(CommentModel theComment, PostModel theParent) {
		theParent.addChildComment(theComment);
		theComment.setMyParent(theParent);

		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider
				.getProviderWithContext(theContext);

		theProvider.updateTopic((TopicModel) theParent.getMyFirstAncestor(),
				thePostModelList);
	}
}
