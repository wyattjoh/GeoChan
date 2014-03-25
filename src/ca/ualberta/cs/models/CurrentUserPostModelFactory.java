package ca.ualberta.cs.models;

public class CurrentUserPostModelFactory {
	public static TopicModel newTopicModel() {
		UserModel theUser = ActiveUserModel.getShared().getUser();
		
		TopicModel theTopicModel = new TopicModel();
		theTopicModel.setPostedBy(theUser);
		
		return theTopicModel;
	}
}
