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

public class LocationProvider extends Service {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Register the listener with the Location Manager to receive location
		// updates
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		Log.w("LocationProvider", "Service Started.");
		
		

		return super.onStartCommand(intent, flags, startId);
	}
	
	public Location getLocation() {
		return theLocation;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
