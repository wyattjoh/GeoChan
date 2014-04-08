/**
 * 
 */
package ca.ualberta.cs.models;

/**
 * @author wyatt
 * 
 */
public class UpdatePackage<T extends PostModel> {
	private String parentId;
	private String myId;
	private T theUpdatedModel;

	/**
	 * 
	 */
	public UpdatePackage(String parentId, String myId) {
		this.parentId = parentId;
		this.myId = myId;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the myId
	 */
	public String getMyId() {
		return myId;
	}

	/**
	 * @param myId
	 *            the myId to set
	 */
	public void setMyId(String myId) {
		this.myId = myId;
	}

	/**
	 * @return the theUpdatedModel
	 */
	public T getTheUpdatedModel() {
		return theUpdatedModel;
	}

	/**
	 * @param theUpdatedModel
	 *            the theUpdatedModel to set
	 */
	public void setTheUpdatedModel(T theUpdatedModel) {
		this.theUpdatedModel = theUpdatedModel;
	}

}
