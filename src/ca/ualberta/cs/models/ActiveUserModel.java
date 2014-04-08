/**
 * 
 */
package ca.ualberta.cs.models;

import android.content.Context;
import android.util.Log;

/**
 * Manages a user model that is currently logged in
 * 
 * @author wyatt
 * 
 */
public class ActiveUserModel {

	private static ActiveUserModel singleton = null;
	private UserModel theUser = null;
	private SavedUserModel theSavedUserModel;

	private ActiveUserModel(Context theContext) {
		// Setup SavedUserModel
		this.theSavedUserModel = new SavedUserModel(theContext);

		// Load UserModel if already existing
		this.theUser = theSavedUserModel.load();

		if (this.theUser != null) {
			this.theUser.setActiveUserModel(this);
		}
	}

	/**
	 * Creates a new shared object
	 */
	public static ActiveUserModel createInstance(Context theContext) {
		if (singleton == null) {
			singleton = new ActiveUserModel(theContext);
		}

		return singleton;
	}

	/**
	 * Gets an instance of an active user model
	 * 
	 * @return
	 */
	public static ActiveUserModel getInstance() {
		if (singleton == null) {
			throw new RuntimeException(
					"Shared ActiveUserModel not created yet! Can't getShared().");
		}

		return singleton;
	}

	/**
	 * Performs a login process for the user
	 * 
	 * @param theUsername
	 */
	public void performLogin(String theUsername) {
		if (isLoggedIn()) {
			performLogout();
		}

		theUser = new UserModel(theUsername);

		this.theUser.setActiveUserModel(this);

		// Save the user in preferences
		theSavedUserModel.save(theUser);
	}

	/**
	 * Performs a logout process for the user
	 */
	public void performLogout() {
		theUser = null;

		// Remove the user from preferences
		theSavedUserModel.remove();

		// Remove the users details

		// Favorites
		FavoriteCommentModelList.getInstance().delete();
		FavoriteTopicModelList.getInstance().delete();

		// Read later
		ReadLaterCommentModelList.getInstance().delete();
		ReadLaterTopicModelList.getInstance().delete();
	}

	/**
	 * @return true if a user is logged in, false if not
	 */
	public Boolean isLoggedIn() {
		if (theUser == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Get the user from the active user model
	 * 
	 * @return the active user
	 */
	public final UserModel getUser() {
		return theUser;
	}

	/**
	 * Notify that the user was modified
	 */
	public void notifiedUserMutated() {
		// Save the user in preferences
		theSavedUserModel.save(theUser);

		Log.w("ActiveUserModel", "Update was called.");
	}
}
