package ca.ualberta.cs.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.MainActivity;
import ca.ualberta.cs.PostListController;
import ca.ualberta.cs.PostModel;

public class PostListControllerTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public PostListControllerTest() {
		super(MainActivity.class);
	}
	public void testControllerGetList(){
		ArrayList<PostModel> commentList = PostListController.getList();
		assertNotNull(commentList);
	}
}
