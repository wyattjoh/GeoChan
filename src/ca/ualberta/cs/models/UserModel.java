/**
 * 
 */
package ca.ualberta.cs.models;

import java.util.ArrayList;

import android.location.Location;

/**
 * @author wyatt
 * 
 */
public class UserModel {
	private String userName;
	private String userHash;
	private Location location;
	private ArrayList<String> upVoteList = new ArrayList<String>();
	private ArrayList<String> downVoteList = new ArrayList<String>();

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
	}

	/**
	 * @param upVoteList
	 *            the upVoteList to set
	 */
	public void setUpVoteList(ArrayList<String> upVoteList) {
		this.upVoteList = upVoteList;
	}

	/**
	 * @param downVoteList
	 *            the downVoteList to set
	 */
	public void setDownVoteList(ArrayList<String> downVoteList) {
		this.downVoteList = downVoteList;
	}

	public ArrayList<String> getUpVoteList() {
		return upVoteList;
	}

	public void addPostIdUpVoteList(String id) {
		upVoteList.add(id);
	}

	public void removePostIdUpVoteList(String id) {
		upVoteList.remove(id);
	}

	public ArrayList<String> getDownVoteList() {
		return downVoteList;
	}

	public void addPostIdDownVoteList(String id) {
		downVoteList.add(id);
	}

	public void removePostIdDownVoteList(String id) {
		downVoteList.remove(id);
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

	public String getUserHash() {
		return userHash;
	}
}
