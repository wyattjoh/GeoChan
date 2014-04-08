package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;
import android.location.Location;

public abstract class PostModel {
	private String commentText = "";
	private Location location;
	private Bitmap theBitmap = null;
	private UserModel postedBy = null;
	private Date datePosted;
	private Integer score = 0;
	private transient PostModel myParent = null;
	private ArrayList<CommentModel> childrenComments = new ArrayList<CommentModel>();

	private transient Boolean isFavorite = false;
	private transient Boolean isReadLater = false;
	private String id;

	abstract public String getQualifyingId();

	/**
	 * Constructors
	 */
	public PostModel() {
		this.datePosted = new Date();
	}

	public PostModel(UserModel theUser) {
		this();
		this.postedBy = theUser;
	}

	public void incrementScore() {
		this.score += 1;
	}

	public void decrementScore() {
		this.score -= 1;
	}

	public boolean hasPicture() {
		return this.theBitmap != null;
	}

	/**
	 * Auto generated setters and getters
	 * */
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String bodyText) {
		this.commentText = bodyText;
	}

	public Location getLocation() {
		return location;
	}

	public String getLocationAsString() {
		if (this.location == null) {
			return "NULL";
		} else {
			return String.valueOf(this.location.getLatitude()) + " , "
					+ String.valueOf(this.location.getLongitude());
		}
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Bitmap getPicture() {
		return this.theBitmap;
	}

	public void setPicture(Bitmap picture) {
		this.theBitmap = picture;
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

	public void setChildComments(ArrayList<CommentModel> commentList) {
		this.childrenComments = commentList;
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
	 * @param isFavorite
	 *            the isFavorite to set
	 */
	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	/**
	 * @return the isReadLater
	 */
	public Boolean isReadLater() {
		return isReadLater;
	}

	/**
	 * @param isReadLater
	 *            the isReadLater to set
	 */
	public void setIsReadLater(Boolean isReadLater) {
		this.isReadLater = isReadLater;
	}

	/**
	 * @return the myParent
	 */
	public PostModel getMyParent() {
		return myParent;
	}

	/**
	 * @param myParent
	 *            the myParent to set
	 */
	public void setMyParent(PostModel myParent) {
		this.myParent = myParent;
	}

	public PostModel getMyFirstAncestor() {
		if (myParent == null) {
			return this;
		}
		return myParent.getMyFirstAncestor();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Finds the comment with the specified id in the tree
	 * 
	 * @param id
	 * @return
	 */
	public CommentModel fetchCommentWithId(String id) {
		for (CommentModel theComment : this.childrenComments) {
			if (theComment.getId().equals(id)) {
				return theComment;
			} else {
				CommentModel theCommentThatMatched = theComment
						.fetchCommentWithId(id);

				if (theCommentThatMatched != null) {
					return theCommentThatMatched;
				}
			}
		}

		return null;
	}

	/**
	 * Location mapping function
	 */
	public ArrayList<Location> getLocationMapArray() {
		ArrayList<Location> locationMapArray = new ArrayList<Location>();
		if (this.childrenComments.isEmpty()) {
			locationMapArray.add(this.location);
		} else {
			for (PostModel child : this.childrenComments) {
				locationMapArray.addAll(child.getLocationMapArray());
			}
			locationMapArray.add(this.location);
		}
		return locationMapArray;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof PostModel)) {
			return false;
		}

		PostModel oModel = (PostModel) o;
		String theId = oModel.getId();

		if (theId != null && theId.equals(getId())) {
			return true;
		} else {
			return false;
		}
	}
}
