package ca.ualberta.cs.test;

import ca.ualberta.cs.views.MainActivity;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.UserModel;
import android.test.ActivityInstrumentationTestCase2;

public class PostModelTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public PostModelTest() {
		super(MainActivity.class);
	}

	public void testCommentModel() {
		UserModel user = new UserModel("Wyatt");
		PostModel commentModel = new CommentModel(user);

		assertNotNull("Built post model isn't null", commentModel);
		assertEquals("Post user is assigned", user, commentModel.getPostedBy());
		
		commentModel.incrementScore();
		assertEquals("Increment score test", commentModel.getScore().intValue(), 1);
		
		commentModel.decrementScore();
		assertEquals("Descrement score test", commentModel.getScore().intValue(), 0);
	}
	
	public void testTopicModel() {
		UserModel user = new UserModel("Wyatt");
		TopicModel topicModel = new TopicModel(user);

		assertNotNull("Built post model isn't null", topicModel);
		assertEquals("Post user is assigned", user, topicModel.getPostedBy());
		
		topicModel.setTitle("Test1");
		assertEquals("Title is equal to value set", topicModel.getTitle(), "Test1");
		
		topicModel.incrementScore();
		assertEquals("Increment score test", topicModel.getScore().intValue(), 1);
		
		topicModel.decrementScore();
		assertEquals("Descrement score test", topicModel.getScore().intValue(), 0);
	}
}
