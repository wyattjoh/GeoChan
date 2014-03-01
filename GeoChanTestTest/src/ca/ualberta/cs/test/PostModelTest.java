package ca.ualberta.cs.test;

import java.util.ArrayList;

import ca.ualberta.cs.CommentModel;
import ca.ualberta.cs.MainActivity;
import ca.ualberta.cs.PostModel;
import android.test.ActivityInstrumentationTestCase2;

public class PostModelTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public PostModelTest() {
		super(MainActivity.class);
	}

	public void testPostModel(){
		PostModel model = new PostModel();
		assertNotNull(model);
	}
	
	public static ArrayList<CommentModel> createCommentList(){
		// init array list
		ArrayList<CommentModel> modelList = new ArrayList<CommentModel>();
		
		// populate array list with comments
		CommentModel comment  = new CommentModel();
		
		modelList.add(comment);
		
		return modelList;
	}
}
