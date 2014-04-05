package ca.ualberta.cs.test;

import java.util.ArrayList;

import android.support.v4.app.FragmentActivity;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.adapters.CommentListViewAdapter;
import ca.ualberta.cs.adapters.PostListViewAdapter;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UserModel;

public class PostApaterTest extends
		ActivityInstrumentationTestCase2<FragmentActivity> {
	public PostApaterTest() {
		super(FragmentActivity.class);
		// TODO Auto-generated constructor stub
	}

	public void testPostAdapterExists() {
		// build comment object list
		UserModel user = new UserModel("Wyatt");
		CommentModel comment = new CommentModel(user);
		ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
		commentList.add(comment);
		CommentListViewAdapter adapter = new CommentListViewAdapter(getActivity(), null);

		// make sure the adapter isnt null
		assertNotNull("The objcet is not null", adapter);
	}
}
