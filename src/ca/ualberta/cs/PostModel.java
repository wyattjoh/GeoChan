package ca.ualberta.cs;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;
import android.location.Location;

public class PostModel {
	private String bodyText;
	private Location location;
	private Bitmap picture;
	private UserModel postedBy;
	private Date datePosted;
	private Integer score;
	private ArrayList<CommentModel> childrenComments;
	
	public PostModel(){
		this.postedBy = new UserModel("default");
		this.datePosted = new Date();
		this.score = 0;
		
	}
	
	public PostModel(UserModel user){
		this.postedBy = user;
		this.datePosted = new Date();
		this.score = 0;
	}
	
	
	/**
	 *  Auto generated setters and getters
	 *  */
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Bitmap getPicture() {
		return picture;
	}
	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}
	public UserModel getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(UserModel postedBy) {
		this.postedBy = postedBy;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public ArrayList<CommentModel> getChildrenComments() {
		return childrenComments;
	}
	public void setChildrenComments(ArrayList<CommentModel> childrenComments) {
		this.childrenComments = childrenComments;
	}
	
	/**
	 * end of auto generated setters and getters
	 */
	
}
