/**
 * 
 */
package ca.ualberta.cs.models;

import java.util.HashMap;

import android.location.Location;

/**
 * Stores a model for a user
 * 
 * @author wyatt
 * 
 */
public class UserModel {
	private String userName;
	private String userHash;
	private Location location;
	private HashMap<String, Integer> userVoteList = new HashMap<String, Integer>();
	private transient ActiveUserModel activeUserModel = null;

	public UserModel(String theUserName) {
		userName = theUserName;
		this.location = new Location("");
		this.location.setLatitude(0);
		this.location.setLongitude(0);
		this.userHash = userName + this.location.toString();
	}

	/**
	 * @param userHash
	 *            the userHash to set
	 */
	public void setUserHash(String userHash) {
		this.userHash = userHash;

		wasMutated();
	}

	/**
	 * Gets the username from the user model
	 * 
	 * @return the username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Gets the location from the user model
	 * 
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
		wasMutated();
	}

	/**
	 * Gets the hash from the user model
	 * 
	 * @return hash
	 */
	public String getUserHash() {
		return userHash;
	}

	/**
	 * Called by the function when there is a mutation to the model
	 */
	private void wasMutated() {
		if (this.activeUserModel != null) {
			this.activeUserModel.notifiedUserMutated();
		}
	}

	/**
	 * @param activeUserModel
	 *            the activeUserModel to set
	 */
	public void setActiveUserModel(ActiveUserModel activeUserModel) {
		this.activeUserModel = activeUserModel;
	}

	protected Boolean canVote(String id, Integer mod) {
		if (userVoteList.containsKey(id)) {
			Integer vote = userVoteList.get(id);

			if (vote == mod) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * Determines whether a user can upvote a post
	 * 
	 * @param id
	 * @return true if can vote up, false if cannot
	 */
	public Boolean canUpVote(String id) {
		return canVote(id, 1);
	}

	public Boolean canDownVote(String id) {
		return canVote(id, -1);
	}

	protected void performVote(String id, Integer mod) {
		if (canVote(id, mod)) {
			if (userVoteList.containsKey(id)) {
				Integer theScore = userVoteList.get(id);

				if (theScore == 0) {
					userVoteList.put(id, mod);
				} else {
					userVoteList.put(id, 0);
					;
				}
			} else {
				userVoteList.put(id, mod);
			}
		}
	}

	public void performUpvote(String id) {
		performVote(id, 1);
	}

	public void performDownVote(String id) {
		performVote(id, -1);
	}
}
