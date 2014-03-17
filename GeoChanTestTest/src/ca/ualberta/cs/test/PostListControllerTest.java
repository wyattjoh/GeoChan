package ca.ualberta.cs.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
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
		PostListController.createTopicList(new UserModel("testUser"));
		assertNotSame("make sure objects are not all the same", TopicModelList.shared().getTopicModelArrayList().get(0), TopicModelList.shared().getTopicModelArrayList().get(1));
	}
}
