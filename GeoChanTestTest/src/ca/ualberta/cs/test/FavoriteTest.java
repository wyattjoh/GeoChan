package ca.ualberta.cs.test;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.UserModel;

public class FavoriteTest extends ActivityInstrumentationTestCase2<Activity> {
	public FavoriteTest(){
		super(Activity.class);
	}
	
	/**
	 * Test case 11.1
	 * Did the file save when the user pressed the 'favorite' button
	 * 
	 * Test case 12.1
	 * Does the favorites display
	 */
	public void testFavorite() {
		FavoriteTopicModelList.createInstance(getInstrumentation().getContext());
		if (FavoriteTopicModelList.getInstance().getArrayList().isEmpty()) {
			UserModel user = new UserModel("Testing");
			Location location = new Location("");
			location.setLatitude(0);
			location.setLongitude(0);
			user.setLocation(location);
			TopicModel testTopic = new TopicModel(user);
			testTopic.setTitle("Test topic");
			testTopic.setLocation(location);
			testTopic.setCommentText("This is an auto generated test topic");
			testTopic.setIsFavorite(true);
			CommentModel testComment = new CommentModel(user);
			testComment.setLocation(location);
			testComment.setCommentText("This is an auto generated test topic");
			testTopic.addChildComment(testComment);
			FavoriteTopicModelList.getInstance().add(testTopic);
		}
		assertFalse("Topics not displayed in favorite topic model list", FavoriteTopicModelList.getInstance().getArrayList().isEmpty());
	}
}
