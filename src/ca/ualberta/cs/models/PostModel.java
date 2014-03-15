package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;
import android.location.Location;

public abstract class PostModel {
	private String id;
	private String bodyText;
	private Location location;
	private Bitmap picture;
	private UserModel postedBy;
	private Date datePosted;
	private Integer score;
	private ArrayList<CommentModel> childrenComments;
	
	/**
	 * Constructors
	 */
	public PostModel(){
		this.postedBy = ActiveUserModel.getShared().getUser();
		this.datePosted = new Date();
		this.score = 0;
		this.picture = null;
		this.childrenComments = null;
	}
	
	public PostModel(UserModel theUser){
		this.postedBy = theUser;
		this.datePosted = new Date();
		this.score = 0;
		this.picture = null;
		this.childrenComments = null;
	}
	
	public void incrementScore(){
		this.score += 1;
	}
	
	public void decrementScore(){
		this.score -= 1;
	}
	
	public boolean hasPicture(){
		if (picture != null){
			return true;
		}
		return false;
		
	}
	/**
	 *  Auto generated setters and getters
	 *  */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public void addChildComment(CommentModel theChildComment) {
		if (this.childrenComments == null) {
			this.childrenComments = new ArrayList<CommentModel>();
		}
		this.childrenComments.add(theChildComment);
	}
	/**
	 * end of auto generated setters and getters
	 */
	
}
