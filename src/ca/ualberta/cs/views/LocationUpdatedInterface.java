/**
 * 
 */
package ca.ualberta.cs.views;

import android.location.Location;

/**
 * Describes an interface to indicate that a location was updaed
 * 
 * @author wyatt
 * 
 */
public interface LocationUpdatedInterface {
	/**
	 * When a location is updated, this method is called on all registered
	 * listeners
	 * 
	 * @param theNewLocation
	 */
	public void locationWasUpdated(Location theNewLocation);
}
