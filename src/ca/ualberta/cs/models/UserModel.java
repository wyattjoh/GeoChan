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
	private ArrayList<String> upVoteList;
	private ArrayList<String> downVoteList;
	
	public ArrayList<String> getUpVoteList() {
		return upVoteList;
	}
	
	public void addPostIdUpVoteList(String id) {
		upVoteList.add(id);
	}
	
	public ArrayList<String> getdownVoteList() {
		return downVoteList;
	}
	
	public void addPostIdDownVoteList(String id) {
		downVoteList.add(id);
	}

	public UserModel(String theUserName) {
		userName = theUserName;
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
