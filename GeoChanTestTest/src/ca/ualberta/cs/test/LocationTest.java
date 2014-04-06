package ca.ualberta.cs.test;

import android.app.Activity;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.UserModel;
import ca.ualberta.cs.providers.LocationProvider;

public class LocationTest extends ActivityInstrumentationTestCase2<Activity> {

	public LocationTest() {
		super(Activity.class);
	}

	/**
	 * TestCase 17.1
	 * test whether provider will get me the default location
	 */
	public void testDefaultLocation() {
		LocationProvider provider = ca.ualberta.cs.providers.LocationProvider.getInstance(getActivity());
		assertNotNull(provider.getLocation());
	}
	
	/**
	 * TestCase 18.1
	 * test whether I can send the location to the user
	 */
	public void testChangeLocation() {
		UserModel user = new UserModel("LactionTestUser");		
		Location location  = new Location("1000.0000");
		user.setLocation(location);
		
		assertEquals(location, user.getLocation());
	}
	
	/**
	 * TestCase 18.2
	 * test whether location provider will send different locations 
	 */
	public void testUpdatedLocation() {
		UserModel user = new UserModel("LactionTestUser");
		assertNotNull(user.getLocation());
		
		Location location  = new Location("1000.0000");
		Location newLocation = new Location("1234.00");
		
		assertNotSame(location, newLocation);
	}
	
	
}
