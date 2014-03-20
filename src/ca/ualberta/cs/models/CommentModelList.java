/**
 * 
 */
package ca.ualberta.cs.models;

/**
 * @author wyatt
 * 
 */
public class CommentModelList extends PostModelList<CommentModel> {
	private static CommentModelList singleton = null;

	public static CommentModelList getInstance(PostModel parentModel) {
		// Ensure that we can't get an instance without a parent model
		if (parentModel == null) {
			throw new RuntimeException("Cannot create a comment list without a parent!");
		}
		
		// Create the singleton
		if (singleton == null) {
			singleton = new CommentModelList();
		}
		
		// Update the arrayList with it's children comments
		singleton.setArrayList(parentModel.getChildrenComments());

		// Return the singleton
		return singleton;
	}

}
