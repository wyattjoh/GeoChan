package ca.ualberta.cs.models;

import java.util.ArrayList;

public class DummyPostListFactory {

	public static ArrayList<TopicModel> createTopicList(UserModel theUser) {
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

		TopicModel theTopic2 = new TopicModel(theUser);
		theTopic2.setTitle("TestTitle2");
		theTopic2.setCommentText("TestText2");
		theTopic2.setScore(2);
		theTopic2.setPostedBy(theUser);
		theModelList.add(theTopic2);

		TopicModel theTopic3 = new TopicModel(theUser);
		theTopic3.setTitle("TestTitle3");
		theTopic3.setCommentText("TestText3");
		theTopic3.setScore(3);
		theTopic3.setPostedBy(theUser);
		theModelList.add(theTopic3);

		return theModelList;
	}

	public static ArrayList<CommentModel> createCommentlist(UserModel theUser) {
		// build comment list
		ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();

		CommentModel theComment1 = new CommentModel();
		theComment1.setCommentText("Test body text1");
		theComment1.setScore(1);
		theComment1.setPostedBy(theUser);
		commentList.add(theComment1);

		CommentModel theComment2 = new CommentModel();
		theComment2.setCommentText("Test body text2");
		theComment2.setScore(2);
		theComment2.setPostedBy(theUser);
		commentList.add(theComment2);

		CommentModel theComment3 = new CommentModel();
		theComment3.setCommentText("Test body text3");
		theComment3.setScore(3);
		theComment3.setPostedBy(theUser);
		commentList.add(theComment3);

		return commentList;
	}

	/**
	 * create test models with comments
	 * 
	 * @param theUser
	 * @return
	 */
	public static ArrayList<TopicModel> createCommentedTopics(UserModel theUser) {
		// build up static test models
		ArrayList<TopicModel> theModelList = createTopicList(theUser);

		ArrayList<CommentModel> commentList = createCommentlist(theUser);

		// assign topics the comments
		for (int i = 0; i < theModelList.size(); i++) {
			for (int j = 0; j < commentList.size(); j++) {
				theModelList.get(i).addChildComment(commentList.get(j));
			}
		}
		return theModelList;
	}

}
