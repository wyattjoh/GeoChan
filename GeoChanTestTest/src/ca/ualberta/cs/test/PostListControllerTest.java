package ca.ualberta.cs.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.UserModel;
import ca.ualberta.cs.views.MainActivity;

public class PostListControllerTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public PostListControllerTest() {
		super(MainActivity.class);
	}
	
	public void testControllerGetList(){
		ArrayList<Object> commentList = PostListController.getList();
		assertNotNull(commentList);
	}
	
	public void testControllerCreateTopicList(){
		ArrayList<TopicModel> model = PostListController.createTopicList(new UserModel("testUser"));
		assertNotSame("make sure objects are not all the same", model.get(0), model.get(1));
	}
}
