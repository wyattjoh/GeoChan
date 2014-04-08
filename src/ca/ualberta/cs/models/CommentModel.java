package ca.ualberta.cs.models;

import java.util.UUID;

/**
 * Stores the comment data
 * 
 * @author wyatt
 *
 */
public class CommentModel extends PostModel {
	private String parentId;

	/**
	 * Constructors
	 * */
	public CommentModel() {
		super();

		// Sets the ID
		setId(UUID.randomUUID().toString());
	}

	public CommentModel(UserModel theUser) {
		super(theUser);
	}

	/**
	 * @return the parentId
	 */
	@Override
	public String getQualifyingId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
