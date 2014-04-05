package ca.ualberta.cs.test;

import android.app.Activity;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.UserModel;
import ca.ualberta.cs.providers.LocationProvider;

public class LocationTest extends ActivityInstrumentationTestCase2<Activity> {

	public LocationTest() {
		super(Activity.class);
		// TODO Auto-generated constructor stub
	}

	// test whether provider will get me the default location
	public void testDefaultLocation() {
		LocationProvider provider = ca.ualberta.cs.providers.LocationProvider.getInstance(getActivity());
		assertNotNull(provider.getLocation());
	}
	
	public void testChangeLocation() {
		UserModel user = new UserModel("LactionTestUser");
		assertNotNull(user.getLocation());
		
		Location location  = new Location("1000.0000");
		user.setLocation(location);
		
		assertEquals(location, user.getLocation());
	}
}
