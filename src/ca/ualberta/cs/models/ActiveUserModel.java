/**
 * 
 */
package ca.ualberta.cs.models;

import android.content.Context;
import android.util.Log;

/**
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
		this.theUser.setActiveUserModel(this);
	}

	/*
	 * Creates a new shared object
	 */
	public static ActiveUserModel createInstance(Context theContext) {
		if (singleton == null) {
			singleton = new ActiveUserModel(theContext);
		}

		return singleton;
	}

	public static ActiveUserModel getInstance() {
		if (singleton == null) {
			throw new RuntimeException(
					"Shared ActiveUserModel not created yet! Can't getShared().");
		}

		return singleton;
	}

	public void performLogin(String theUsername) {
		if (isLoggedIn()) {
			performLogout();
		}

		theUser = new UserModel(theUsername);

		// Save the user in preferences
		theSavedUserModel.save(theUser);
	}

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

	public Boolean isLoggedIn() {
		if (theUser == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Get the user from the active user model
	 * @return
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
