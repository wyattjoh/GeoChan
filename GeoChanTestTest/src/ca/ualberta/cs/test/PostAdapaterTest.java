package ca.ualberta.cs.test;

import java.util.ArrayList;

import android.support.v4.app.FragmentActivity;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.adapters.TopicListViewAdapter;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;
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
		TopicModel topic = new TopicModel(user);
		ArrayList<TopicModel> topicList = new ArrayList<TopicModel>();
		topicList.add(topic);
		
		PostModelList<TopicModel> topics = TopicModelList.getInstance();
		topics.setArrayList(topicList);
		TopicListViewAdapter adapter = new TopicListViewAdapter(getActivity(), topics);

		// make sure the adapter isnt null
		assertNotNull("The object is not null", adapter);
	}
}
