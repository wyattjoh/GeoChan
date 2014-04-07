package ca.ualberta.cs.providers;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.UserModel;
import ca.ualberta.cs.views.LocationUpdatedInterface;

/*
 *  Implement such as:
 *  	http://developer.android.com/reference/android/app/Service.html#LocalServiceSample
 *  
 */

public class LocationProvider {
	private static LocationProvider singleton = null;
	private LocationManager locationManager = null;
	private Location theLocation = null;
	protected ArrayList<LocationUpdatedInterface> activities = new ArrayList<LocationUpdatedInterface>();

	private final LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			// Called when a new location is found by the network location
			// provider.
			theLocation = location;

			// update the location
			ActiveUserModel theActiveUserModel = ActiveUserModel.getInstance();
			UserModel theLoggedInUser = theActiveUserModel.getUser();

			if (theLoggedInUser != null) {
				theLoggedInUser.setLocation(location);
			}

			for (LocationUpdatedInterface item : LocationProvider.this.activities) {
				if (item != null) {
					item.locationWasUpdated(location);
				}
			}
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}
	};

	private LocationProvider(Context theContext) {
		// Register the listener with the Location Manager to receive location
		// updates
		locationManager = (LocationManager) theContext
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 0, locationListener);
	}
	
	public void registerForLocationUpdates(LocationUpdatedInterface activity) {
		if (!this.activities.contains(activity)) {
			this.activities.add(activity);
		}
	}
	
	public void unregisterForLocationUpdates(LocationUpdatedInterface activity) {
		this.activities.remove(activity);
	}

	public static LocationProvider getInstance(Context theContext) {
		if (singleton == null) {
			singleton = new LocationProvider(theContext);
		}

		return singleton;
	}

	public Location getLocation() {
		return theLocation;
	}

}
