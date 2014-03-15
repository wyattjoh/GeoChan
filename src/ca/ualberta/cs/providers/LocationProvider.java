package ca.ualberta.cs.providers;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/*
 *  Implement such as:
 *  	http://developer.android.com/reference/android/app/Service.html#LocalServiceSample
 *  
 */

public class LocationProvider {
	private static LocationProvider singleton = null;
	private LocationManager locationManager = null;
	private Location theLocation = null;

	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			// Called when a new location is found by the network location
			// provider.
			// TODO
			theLocation = location;
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO
		}

		public void onProviderEnabled(String provider) {
			// TODO
		}

		public void onProviderDisabled(String provider) {
			// TODO
		}
	};
	
	private LocationProvider(Context theContext) {
		// Register the listener with the Location Manager to receive location updates
		locationManager = (LocationManager) theContext.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
	}
	
	public static LocationProvider shared(Context theContext) {
		if (singleton == null) {
			singleton = new LocationProvider(theContext);
		}
		
		return singleton;
	}

	public Location getLocation() {
		return theLocation;
	}

}
