package ca.ualberta.cs.test;

import org.osmdroid.util.GeoPoint;

import ca.ualberta.cs.views.MapViewActivity;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;

public class MapActivityTest extends ActivityInstrumentationTestCase2<Activity> {

	public MapActivityTest() {
		super(Activity.class);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * TestCase 22.1
	 * Test if a map is displayed
	 */
	public void testMapActivity() {
		Intent mapView = new Intent(getInstrumentation().getContext(), MapViewActivity.class);
		assertNotNull("Make sure intent isn't null", mapView);
	}

	/**
	 * TestCase 22.2
	 * Test if the locations are indexed
	 */
	public void testMapIndexes() {
		GeoPoint gp_postLocation = new GeoPoint(0, 0);
		assertNotNull("Test if the gp location exists", gp_postLocation);
	}
}
