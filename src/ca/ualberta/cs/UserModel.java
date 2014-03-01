/**
 * 
 */
package ca.ualberta.cs;

import android.location.Location;

/**
 * @author wyatt
 *
 */
public class UserModel {
	private String userName;
	private Location location;
	
	public UserModel(String theUserName) {
		userName = theUserName;
	}
	
	public String getUserName() {
		return userName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
