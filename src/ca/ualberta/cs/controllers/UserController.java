/**
 * 
 */
package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.UserModel;

/**
 * @author wyatt
 *
 */
public class UserController {
	private static UserController singleton = null;
	private UserModel theUser = null;
	
	public static UserController shared() {
		if (singleton == null) {
			singleton = new UserController();
		}
		return singleton;
	}
	
	public void performLogin(String theUsername) {
		if (isLoggedIn()) {
			performLogout();
		}
		
		theUser = new UserModel(theUsername);
	}
	
	public void performLogout() {
		theUser = null;
	}
	
	public Boolean isLoggedIn() {
		if (theUser == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public UserModel getLoggedInUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
