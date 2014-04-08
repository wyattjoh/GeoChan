package ca.ualberta.cs.models;

import java.util.ArrayList;

import ca.ualberta.cs.controllers.TopicModelController;

public class DummyPostListFactory {

	public static void createTopicList(UserModel theUser) {
		if (theUser == null) {
			throw new RuntimeException("Factory got an empty user!");
		}

		TopicModelController theTopicModelController = new TopicModelController();

		// init array list
		ArrayList<TopicModel> theModelList = new ArrayList<TopicModel>();

		TopicModel theTopic1 = populateTestTopic(theUser);
		theModelList.add(theTopic1);

		theTopicModelController.newTopic(theTopic1);
	}

	public static ArrayList<TopicModel> getTopicList(UserModel theUser) {
		if (theUser == null) {
			throw new RuntimeException("Factory got an empty user!");
		}
		// init array list
		ArrayList<TopicModel> theModelList = new ArrayList<TopicModel>();

		TopicModel theTopic1 = populateTestTopic(theUser);
		theModelList.add(theTopic1);

		return theModelList;
	}

	private static TopicModel populateTestTopic(UserModel theUser) {
		TopicModel theTopic1 = new TopicModel(theUser);
		theTopic1.setTitle("TestTitle1");
		theTopic1.setCommentText("TestText1");
		theTopic1.setScore(1);
		theTopic1.setPostedBy(theUser);
		CommentModel theComment1 = new CommentModel();
		theComment1.setCommentText("Test body text1");
		theComment1.setScore(1);
		theComment1.setPostedBy(theUser);
		theTopic1.addChildComment(theComment1);
		theComment1.setMyParent(theTopic1);
		return theTopic1;
	}

	public static ArrayList<CommentModel> getCommentList(UserModel theUser) {
		ArrayList<CommentModel> theCommentList = new ArrayList<CommentModel>();

		CommentModel theComment1 = populateComent(theUser);
		theCommentList.add(theComment1);

		return theCommentList;
	}

	private static CommentModel populateComent(UserModel theUser) {
		CommentModel theComment = new CommentModel();
		theComment.setCommentText("Test body text1");
		theComment.setScore(1);
		theComment.setPostedBy(theUser);
		return theComment;
	}

	/**
	 * create test models with comments
	 * 
	 * @param theUser
	 * @return
	 */
	public static void createCommentedTopics(UserModel theUser) {
		// build up static test models
		createTopicList(theUser);
	}

}