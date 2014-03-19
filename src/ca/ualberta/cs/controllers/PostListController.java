package ca.ualberta.cs.controllers;

import java.util.ArrayList;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UserModel;

public class PostListController {

	/* sort values */
	public static final int SORT_PROXIMITY = 0;
	public static final int SORT_PICTURE = 1;
	public static final int SORT_DATE	 = 2;
	public static final int SORT_SCORE = 3;
	public static final int SORT_DEFAULT = 4;
	
	public static void setSort(final int theSortOrder){
		switch (theSortOrder) {
		case SORT_DATE:
			TopicModelList.getInstance().sortByDate();
			break;
		case SORT_SCORE:
			TopicModelList.getInstance().sortByScore();
			break;
		case SORT_PROXIMITY:
			TopicModelList.getInstance().sortByLocation();
		default:
			break;
		}
	}

	public static void createTopicList(UserModel theUser){
		// init array list
		ArrayList<TopicModel> theModelList = new ArrayList<TopicModel>();
		if (theUser == null){
			// populate topic with test entries
			TopicModel theTopic1 = new TopicModel();
			theTopic1.setTitle("TestTitle1");
			theTopic1.setCommentText("TestText1");
			theTopic1.setScore(3);
			theModelList.add(theTopic1);
			
			TopicModel theTopic2 = new TopicModel();
			theTopic2.setTitle("TestTitle2");
			theTopic2.setCommentText("TestText2");
			theTopic2.setScore(2);
			theModelList.add(theTopic2);
			
			TopicModel theTopic3 = new TopicModel();
			theTopic3.setTitle("TestTitle3");
			theTopic3.setCommentText("TestText3");
			theTopic3.setScore(1);
			theModelList.add(theTopic3);
		}
		
		else {
			// populate topic with test entries
			TopicModel theTopic1 = new TopicModel(theUser);
			theTopic1.setTitle("TestTitle1");
			theTopic1.setCommentText("TestText1");
			theTopic1.setScore(1);
			theModelList.add(theTopic1);
			
			TopicModel theTopic2 = new TopicModel(theUser);
			theTopic2.setTitle("TestTitle2");
			theTopic2.setCommentText("TestText2");
			theTopic2.setScore(2);
			theModelList.add(theTopic2);
			
			TopicModel theTopic3 = new TopicModel(theUser);
			theTopic3.setTitle("TestTitle3");
			theTopic3.setCommentText("TestText3");
			theTopic3.setScore(3);
			theModelList.add(theTopic3);
		}
		
		TopicModelList.getInstance().setTopicModelArrayList(theModelList);

	}
	
	public static ArrayList<CommentModel> createCommentlist(UserModel theUser){
		//build comment list
		ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
		
		if (theUser == null){
			// build giant list items
			CommentModel theComment1 = new CommentModel();
			theComment1.setCommentText("Test body text1");
			theComment1.setScore(1);
			commentList.add(theComment1);
			
			CommentModel theComment2 = new CommentModel();
			theComment2.setCommentText("Test body text2");
			theComment2.setScore(2);
			commentList.add(theComment2);
			
			CommentModel theComment3 = new CommentModel();
			theComment3.setCommentText("Test body text3");
			theComment3.setScore(3);
			commentList.add(theComment3);
		}
		
		else {
			// build giant list items
			CommentModel theComment1 = new CommentModel(theUser);
			theComment1.setCommentText("Test body text1");
			theComment1.setScore(1);
			commentList.add(theComment1);
			
			CommentModel theComment2 = new CommentModel(theUser);
			theComment2.setCommentText("Test body text2");
			theComment2.setScore(2);
			commentList.add(theComment2);
			
			CommentModel theComment3 = new CommentModel(theUser);
			theComment3.setCommentText("Test body text3");
			theComment3.setScore(3);
			commentList.add(theComment3);
		}
		
		return commentList;
	}
	
	/**
	 * create test models with comments
	 * @param theUser
	 * @return
	 */
	public static void createCommentedTopics(UserModel theUser){
		// build up static test models
		createTopicList(theUser);
		
		ArrayList<CommentModel> commentList = createCommentlist(theUser);
		
		// assign topics the comments
		for (int i = 0; i < TopicModelList.getInstance().getTopicModelArrayList().size(); i++) {
			for (int j = 0; j < commentList.size(); j++){
				TopicModelList.getInstance().getTopicModelArrayList().get(i).addChildComment(commentList.get(j));
			}
		}
	}
}
