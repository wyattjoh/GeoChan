package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import android.graphics.Bitmap;
import android.location.Location;

public abstract class PostModel {
	private String id;
	private String commentText;
	private Location location;
	private Bitmap picture;
	private UserModel postedBy;
	private Date datePosted;
	private Integer score;
	private ArrayList<CommentModel> childrenComments;
	
	private transient Boolean isFavorite = false;
	
	/**
	 * Comparators
	 */
	public static Comparator<PostModel> COMPARE_BY_DATE = new Comparator<PostModel>() {
		@Override
		public int compare(PostModel one, PostModel other) {
			return one.datePosted.compareTo(other.datePosted);
		}
	};
	public static Comparator<PostModel> COMPARE_BY_SCORE = new Comparator<PostModel>() {
		@Override
		public int compare(PostModel one, PostModel other) {
			return one.score.compareTo(other.score);
		}
	};
	
	/**
	 * Constructors
	 */
	public PostModel(){
		this.id = "";
		this.postedBy = null;
		this.datePosted = new Date();
		this.score = 0;
		this.picture = null;
		this.childrenComments = null;
	}
	
	public PostModel(UserModel theUser){
		this.id = "";
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
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String bodyText) {
		this.commentText = bodyText;
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

	/**
	 * @return the isFavorite
	 */
	public Boolean isFavorite() {
		return isFavorite;
	}

	/**
	 * @param isFavorite the isFavorite to set
	 */
	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof PostModel) {
			PostModel thePostModelBeingEvaluated = (PostModel) o;

			if (thePostModelBeingEvaluated.getId().equals(id)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
}
