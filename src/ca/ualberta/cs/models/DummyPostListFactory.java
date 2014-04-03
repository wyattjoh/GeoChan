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

		// populate topic with test entries
		TopicModel theTopic1 = new TopicModel(theUser);
		theTopic1.setTitle("TestTitle1");
		theTopic1.setCommentText("TestText1");
		theTopic1.setScore(1);
		theTopic1.setPostedBy(theUser);
		theModelList.add(theTopic1);

		// Create a comment
		CommentModel theComment1 = new CommentModel();
		theComment1.setCommentText("Test body text1");
		theComment1.setScore(1);
		theComment1.setPostedBy(theUser);

		theTopic1.addChildComment(theComment1);
		theComment1.setMyParent(theTopic1);

		theTopicModelController.newTopic(theTopic1);
	}

	public static ArrayList<TopicModel> getTopicList(UserModel theUser) {
		if (theUser == null) {
			throw new RuntimeException("Factory got an empty user!");
		}
		// init array list
		ArrayList<TopicModel> theModelList = new ArrayList<TopicModel>();

		// populate topic with test entries
		TopicModel theTopic1 = new TopicModel(theUser);
		theTopic1.setTitle("TestTitle1");
		theTopic1.setCommentText("TestText1");
		theTopic1.setScore(1);
		theTopic1.setPostedBy(theUser);
		theModelList.add(theTopic1);

		// Create a comment
		CommentModel theComment1 = new CommentModel();
		theComment1.setCommentText("Test body text1");
		theComment1.setScore(1);
		theComment1.setPostedBy(theUser);

		theTopic1.addChildComment(theComment1);
		theComment1.setMyParent(theTopic1);

		return theModelList;
	}

	public static ArrayList<CommentModel> getCommentList(UserModel theUser) {
		ArrayList<CommentModel> theCommentList = new ArrayList<CommentModel>();

		// Create a comment
		CommentModel theComment1 = new CommentModel();
		theComment1.setCommentText("Test body text1");
		theComment1.setScore(1);
		theComment1.setPostedBy(theUser);
		theCommentList.add(theComment1);

		return theCommentList;
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
