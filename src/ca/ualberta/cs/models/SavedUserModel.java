package ca.ualberta.cs.models;

import ca.ualberta.cs.providers.GeoChanGson;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;

public class SavedUserModel {
	private static final String PREFS_NAME = "ActiveUserModel";
	private Context theContext;
	
	/*
	 * Creates a new SavedUserModel
	 */
	public SavedUserModel(Context theContext) {
		this.theContext = theContext;
	}
	
	/*
	 * Gets a new SharedPreferences object for users
	 */
	private SharedPreferences getSharedPreferences() {
		SharedPreferences settings = theContext.getSharedPreferences(PREFS_NAME, 0);
		return settings;
	}
	
	/*
	 * Removes the user from the SharedPreferences
	 */
	public void remove() {
		// Get editor
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		
		// Remove the user key
		editor.remove("theUser");
		
		// Commit edits!
		editor.commit();
	}
	
	/*
	 * Saves a user to SharedPreferences
	 */
	public void save(UserModel theUser) {
		// Serialize the UserModel object
		Gson gson = GeoChanGson.getGson();
		String jsonString = gson.toJson(theUser);
		
		// Get editor
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		
		// Put the json string for the user
		editor.putString("theUser", jsonString);
		
		// Commit the edit
		editor.commit();
	}
	
	/*
	 * Loads a user from shared preferences, returns null if no user found
	 * returns user if user found.
	 */
	public UserModel load() {
		// Serialize the UserModel object
		Gson gson = GeoChanGson.getGson();
		
		// Get preferences
		SharedPreferences settings = getSharedPreferences();
		
		// Get user object
		String jsonString = settings.getString("theUser", null);
		
		if (jsonString == null) {
			// No user is saved, lets pass that up
			return null;
		}
		else {
			// User was in the preferences file, lets load it
			UserModel theUser = gson.fromJson(jsonString, UserModel.class);
			return theUser;
		}
	}
}
