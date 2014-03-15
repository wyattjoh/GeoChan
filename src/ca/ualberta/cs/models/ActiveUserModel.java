/**
 * 
 */
package ca.ualberta.cs.models;


/**
 * @author wyatt
 *
 */
public class ActiveUserModel {
	private static ActiveUserModel singleton = null;
	private UserModel theUser = null;
	
	public static ActiveUserModel shared() {
		if (singleton == null) {
			singleton = new ActiveUserModel();
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

	public UserModel getUser() {
		return theUser;
	}
}
