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
	
	private CommentModelList selectedCommentModel = null;
	
	public static CommentModelList getInstance() {
		if (singleton == null) {
			singleton = new CommentModelList();
		}
		return singleton;
	}
	
}
