package ca.ualberta.cs.models;

/**
 * Generates a post from the active user
 * @author wyatt
 *
 */
public class CurrentUserPostModelFactory {
	public static TopicModel newTopicModel() {
		UserModel theUser = ActiveUserModel.getInstance().getUser();

		TopicModel theTopicModel = new TopicModel();
		theTopicModel.setPostedBy(theUser);

		return theTopicModel;
	}
}
