/**
 * 
 */
package ca.ualberta.cs;

/**
 * @author wyatt
 *
 */
public class UserModel {
	private String userName;
	
	public UserModel(String theUserName) {
		userName = theUserName;
	}
	
	public String getUserName() {
		return userName;
	}
}
