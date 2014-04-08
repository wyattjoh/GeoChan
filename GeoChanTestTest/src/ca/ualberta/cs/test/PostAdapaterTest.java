package ca.ualberta.cs.test;

import java.util.ArrayList;

import android.support.v4.app.FragmentActivity;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.adapters.CommentListViewAdapter;
import ca.ualberta.cs.adapters.PostListViewAdapter;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.ReadLaterTopicModelList;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UserModel;

public class PostAdapaterTest extends
		ActivityInstrumentationTestCase2<FragmentActivity> {
	public PostAdapaterTest() {
		super(FragmentActivity.class);
		// TODO Auto-generated constructor stub
	}

	public void testPostAdapterExists() {
		// build comment object list
		UserModel user = new UserModel("Wyatt");
		CommentModel comment = new CommentModel(user);
		ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
		commentList.add(comment);
		
		PostModelList<CommentModel> comments = CommentModelList.getInstance();
		comments.setArrayList(commentList);
		CommentListViewAdapter adapter = new CommentListViewAdapter(getActivity(), comments);

		// make sure the adapter isnt null
		assertNotNull("The object is not null", adapter);
	}
}
