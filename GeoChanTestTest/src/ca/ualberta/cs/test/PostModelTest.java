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
		assertEquals("Increment score test", commentModel.getScore(), Integer.getInteger("1"));
		
		commentModel.decrementScore();
		assertEquals("Descrement score test", commentModel.getScore(), Integer.getInteger("0"));
	}
	
	public void testTopicModel() {
		UserModel user = new UserModel("Wyatt");
		TopicModel postModel = new TopicModel(user);

		assertNotNull("Built post model isn't null", postModel);
		assertEquals("Post user is assigned", user, postModel.getPostedBy());
		
		postModel.setTitle("Test1");
		assertEquals("Title is equal to value set", postModel.getTitle(), "Test1");
		
		postModel.incrementScore();
		assertEquals("Increment score test", postModel.getScore(), Integer.getInteger("1"));
		
		postModel.decrementScore();
		assertEquals("Descrement score test", postModel.getScore(), Integer.getInteger("0"));
	}
}
