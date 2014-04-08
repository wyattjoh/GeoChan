package ca.ualberta.cs.test;

import android.app.Activity;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.ReadLaterTopicModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.UserModel;

public class ReadLaterTest extends ActivityInstrumentationTestCase2<Activity> {
	public ReadLaterTest(){
		super(Activity.class);
	}
	
	/**
	 * Test case 10.1
	 * Did the file save when the user pressed the 'add to readlist' button
	 */
	public void testReadLater() {
		ReadLaterTopicModelList.createInstance(getInstrumentation().getContext());
		if (ReadLaterTopicModelList.getInstance().getArrayList().isEmpty()) {
			UserModel user = new UserModel("Testing");
			Location location = new Location("");
			location.setLatitude(0);
			location.setLongitude(0);
			user.setLocation(location);
			TopicModel testTopic = new TopicModel(user);
			testTopic.setTitle("Test topic");
			testTopic.setLocation(location);
			testTopic.setCommentText("This is an auto generated test topic");
			testTopic.setIsReadLater(true);
			CommentModel testComment = new CommentModel(user);
			testComment.setLocation(location);
			testComment.setCommentText("This is an auto generated test topic");
			testTopic.addChildComment(testComment);
			ReadLaterTopicModelList.getInstance().add(testTopic);
		}
		assertFalse("Topics not displayed in favorite topic model list", ReadLaterTopicModelList.getInstance().getArrayList().isEmpty());
	}
}
