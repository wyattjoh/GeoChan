package ca.ualberta.cs.providers;

import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.UserModel;
import ca.ualberta.cs.views.LocationUpdatedInterface;

/**
 * 
 * @author wyatt
 * 
 *         Implement such as:
 *         http://developer.android.com/reference/android/app/
 *         Service.html#LocalServiceSample
 * 
 *         Measures the location and keeps listening interfaces up to date
 * 
 */

public class LocationProvider {
	private static LocationProvider singleton = null;

	/**
	 * Fetches a singleton of the LocationProvider class
	 * 
	 * @param theContext
	 * @return
	 */
	public static LocationProvider getInstance(Context theContext) {
		if (singleton == null) {
			singleton = new LocationProvider(theContext);
		}

		return singleton;
	}

	protected ArrayList<LocationUpdatedInterface> listeningInterfaces = new ArrayList<LocationUpdatedInterface>();

	/**
	 * The location listener
	 */
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

			updateListeningInterfaces(location);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// Do nothing
		}

		@Override
		public void onProviderEnabled(String provider) {
			// Do nothing
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// Do nothing
		}
	};

	private LocationManager locationManager = null;

	private Location theLocation = null;

	private LocationProvider(Context theContext) {
		// Register the listener with the Location Manager to receive location
		// updates
		locationManager = (LocationManager) theContext
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 0, locationListener);
	}

	/**
	 * @return the current/last location
	 */
	public Location getLocation() {
		return theLocation;
	}

	/**
	 * Registered the interface with the listening method
	 * 
	 * @param listeningInterface
	 */
	public void registerForLocationUpdates(
			LocationUpdatedInterface listeningInterface) {
		if (!this.listeningInterfaces.contains(listeningInterface)) {
			this.listeningInterfaces.add(listeningInterface);
		}
	}

	/**
	 * Unregisters the interface from location updates
	 * 
	 * @param listeningInterface
	 */
	public void unregisterForLocationUpdates(
			LocationUpdatedInterface listeningInterface) {
		this.listeningInterfaces.remove(listeningInterface);
	}

	/**
	 * Updates the listening interface with the location
	 * 
	 * @param location
	 */
	protected void updateListeningInterfaces(Location location) {
		for (LocationUpdatedInterface item : LocationProvider.this.listeningInterfaces) {
			if (item != null) {
				item.locationWasUpdated(location);
			}
		}
	}

}
