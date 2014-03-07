package ca.ualberta.cs.providers;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

/*
 *  Implement such as:
 *  	http://developer.android.com/reference/android/app/Service.html#LocalServiceSample
 *  
 */

public class LocationProvider extends Service {

	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			// Called when a new location is found by the network location
			// provider.
			// TODO

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
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

		return super.onStartCommand(intent, flags, startId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
