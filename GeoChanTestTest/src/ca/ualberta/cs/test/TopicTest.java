package ca.ualberta.cs.test;

import android.app.Activity;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UserModel;

public class TopicTest extends ActivityInstrumentationTestCase2<Activity> {
	public TopicTest(){
		super(Activity.class);
	}
	
	/**
	 * TestCase 3.2
	 * Test whether a new topic can be created
	 */
	public void testTopicCreation() {
		TopicModel testTopic = new TopicModel();
		assertNotNull("Topic is not created", testTopic);
	}
	
	/**
	 * TestCase 4.1
	 * Test whether topics are displayed
	 */
	public void testTopicDisplay() {
		if (TopicModelList.getInstance().getArrayList().isEmpty()) {
			UserModel user = new UserModel("Testing");
			Location location = new Location("");
			location.setLatitude(0);
			location.setLongitude(0);
			user.setLocation(location);
			TopicModel testTopic = new TopicModel(user);
			testTopic.setTitle("Test topic");
			testTopic.setLocation(location);
			testTopic.setCommentText("This is an auto generated test topic");
			TopicModelList.getInstance().add(testTopic);
		}
		assertFalse("Topics not displayed in topic model list", TopicModelList.getInstance().getArrayList().isEmpty());
	}
}