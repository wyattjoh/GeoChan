package ca.ualberta.cs.test;

import java.util.ArrayList;

import org.osmdroid.tileprovider.modules.NetworkAvailabliltyCheck;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.NetworkModel;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.ElasticSearchProvider;
import ca.ualberta.cs.views.MainActivityFragment;

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
		assertTrue("Test whether singleton returns true",NetworkModel.getInstance().isNetworkAvailable(getActivity()));
		
		// TODO add broadcast receiver
	}
	
	/**
	 * TestCase 19.2
	 * Test gaining connection
	 */
	public void testUpdatingConnection() {
		NetworkModel.getInstance();
		assertTrue("Test wether we have a connection", NetworkModel.getInstance().isNetworkAvailable(getActivity()));
		
		TopicModelList.getInstance();
		
		ElasticSearchProvider.getProvider().getTopics(0, 1);
		assertNotNull("Test whether our Topic List is null",TopicModelList.getInstance());
		
		// TODO add broadcast receiver and then recast it as connected
	}
}
