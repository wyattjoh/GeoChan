package ca.ualberta.cs.test;

import java.util.ArrayList;

import ca.ualberta.cs.CommentModel;
import ca.ualberta.cs.MainActivity;
import ca.ualberta.cs.PostModel;
import ca.ualberta.cs.TopicModel;
import ca.ualberta.cs.UserModel;
import android.test.ActivityInstrumentationTestCase2;

public class PostModelTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public PostModelTest() {
		super(MainActivity.class);
	}

	public void testPostModel() {
		UserModel user = new UserModel("Wyatt");
		PostModel postModel = new PostModel(user);

		assertNotNull("Built post model isn't null", postModel);
		assertEquals("Post user is assigned", user, postModel.getPostedBy());
		
		postModel.incrementScore();
		assertEquals("Increment score test", postModel.getScore(), Integer.getInteger("1"));
		
		postModel.decrementScore();
		assertEquals("Descrement score test", postModel.getScore(), Integer.getInteger("0"));
	}

	public static ArrayList<CommentModel> createCommentList() {
		// init array list
		ArrayList<CommentModel> theModelList = new ArrayList<CommentModel>();

		// populate an array list with comments
		CommentModel theComment = new CommentModel();

		// change text for each comment posted by the same user
		theComment.setBodyText("Test1");
		theModelList.add(theComment);
		theComment.setBodyText("Test2");
		theModelList.add(theComment);
		theComment.setBodyText("Test3");
		theModelList.add(theComment);

		return theModelList;
	}
	
	public static ArrayList<TopicModel> createTopicList(){
		// init array list
		ArrayList<TopicModel> theModelList = new ArrayList<TopicModel>();
		// init topic model
		TopicModel theTopic = new TopicModel();
		
		// populate topic with test entries
		theTopic.setBodyText("Test1");
		theModelList.add(theTopic);
		theTopic.setBodyText("Test2");
		theModelList.add(theTopic);
		theTopic.setBodyText("Test3");
		theModelList.add(theTopic);
		
		return theModelList;
	}
}
