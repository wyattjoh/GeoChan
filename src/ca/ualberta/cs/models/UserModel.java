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
	private Location location;
	private ArrayList<String> upVoteList = new ArrayList<String>();
	private ArrayList<String> downVoteList = new ArrayList<String>();

	public UserModel(String theUserName) {
		userName = theUserName;
		this.location = new Location("");
		this.location.setLatitude(0);
		this.location.setLongitude(0);
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
}
