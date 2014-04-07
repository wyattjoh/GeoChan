package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.SelectedTopicModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.ElasticSearchProvider;

public class CommentModelController {
	private PostModelList<TopicModel> thePostModelList;

	public CommentModelController(PostModelList<TopicModel> thePostModelList) {
		this.thePostModelList = thePostModelList;
	}

	public void addComment(CommentModel theComment, PostModel theParent) {
		theParent.addChildComment(theComment);
		theComment.setMyParent(theParent);
		theComment.setParentId(theParent.getId());

		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider.getProvider();

		theProvider.updateTopic(SelectedTopicModelList.getTopicList()
				.getLastSelection(), thePostModelList);
	}

	public void updateComment(CommentModel theModel) {
		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider.getProvider();
		Integer index;

		// check to see if the selection stack would have the comment
		// if it returns the currently selected element then the comment is in
		// the array list immediately under the currently selected topic
		if (CommentModelList.getInstance().getSelectionOffsetFromEnd(1).getId()
				.equals(theModel.getId())) {
			// get the index of our comment from the topic children comments
			index = TopicModelList.getInstance().getLastSelection()
					.getChildrenComments().indexOf(theModel);
			//
			TopicModelList.getInstance().getLastSelection()
					.getChildrenComments().set(index, theModel);
		}

		else {
			// other wise we will have the exact comment list needed and just
			// need the index
			index = CommentModelList.getInstance().getSelectionOffsetFromEnd(1)
					.getChildrenComments().indexOf(theModel);
			CommentModelList.getInstance().getSelectionOffsetFromEnd(1)
					.getChildrenComments().set(index, theModel);
		}

		// tell the topic we need to update
		theProvider.updateTopic(
				TopicModelList.getInstance().getLastSelection(),
				thePostModelList);
	}
}
