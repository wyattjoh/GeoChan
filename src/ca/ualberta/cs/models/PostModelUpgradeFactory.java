/**
 * 
 */
package ca.ualberta.cs.models;

/**
 * @author wyatt
 * 
 */
public class PostModelUpgradeFactory {

	public static TopicModel upgradePostModel(TopicModel thePost) {
		FavoriteTopicModelList theFavTopicList = FavoriteTopicModelList
				.getInstance();

		if (theFavTopicList.contains(thePost)) {
			thePost.setIsFavorite(true);
		}

		ReadLaterTopicModelList theReadTopicList = ReadLaterTopicModelList
				.getInstance();

		if (theReadTopicList.contains(thePost)) {
			thePost.setIsReadLater(true);
		}

		return thePost;
	}

	public static CommentModel upgradePostModel(CommentModel thePost) {
		FavoriteCommentModelList theFavCommentList = FavoriteCommentModelList
				.getInstance();

		if (theFavCommentList.contains(thePost)) {
			thePost.setIsFavorite(true);
		}

		ReadLaterCommentModelList theReadCommentList = ReadLaterCommentModelList
				.getInstance();

		if (theReadCommentList.contains(thePost)) {
			thePost.setIsReadLater(true);
		}

		return thePost;
	}

}
