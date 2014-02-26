/**
 * 
 */
package ca.ualberta.cs;

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
}
