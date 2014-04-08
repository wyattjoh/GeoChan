package ca.ualberta.cs.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.NetworkModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.ElasticSearchProvider;

public class ConnectionTesting extends
		ActivityInstrumentationTestCase2<Activity> {

	public ConnectionTesting() {
		super(Activity.class);
	}

	/**
	 * TestCase 19.1
	 * Test loosing connection during operation
	 */
	public void testLostConnection() {
		NetworkModel.getInstance();
		assertTrue("Test whether singleton returns true", NetworkModel.isNetworkAvailable(getInstrumentation().getContext()));
		
		// TODO add broadcast receiver
	}
	
	/**
	 * TestCase 19.2
	 * Test gaining connection
	 */
	public void testUpdatingConnection() {
		NetworkModel.getInstance();
		assertTrue("Test wether we have a connection", NetworkModel.isNetworkAvailable(getInstrumentation().getContext()));
		
		TopicModelList.getInstance();
		
		ElasticSearchProvider.getProvider().getTopics(0, 1);
		assertNotNull("Test whether our Topic List is null",TopicModelList.getInstance());
		
		// TODO add broadcast receiver and then recast it as connected
	}
}
