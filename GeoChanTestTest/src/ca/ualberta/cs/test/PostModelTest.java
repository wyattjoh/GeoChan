package ca.ualberta.cs.test;

import java.util.ArrayList;
import java.util.Date;

import ca.ualberta.cs.CommentModel;
import ca.ualberta.cs.MainActivity;
import ca.ualberta.cs.PostModel;
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
	}

	public static ArrayList<CommentModel> createCommentList() {
		// init array list
		ArrayList<CommentModel> modelList = new ArrayList<CommentModel>();

		// populate array list with comments
		CommentModel comment = new CommentModel();

		modelList.add(comment);

		return modelList;
	}
}
