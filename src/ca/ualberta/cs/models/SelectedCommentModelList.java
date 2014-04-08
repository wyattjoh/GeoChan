/**
 * 
 */
package ca.ualberta.cs.models;

/**
 * Stores selected comment model list
 * 
 * @author wyatt
 * 
 */
public class SelectedCommentModelList extends
		SelectedPostModelList<CommentModel> {
	public static SelectedCommentModelList singleton = new SelectedCommentModelList();

	public static void setTopicList(PostModelList<CommentModel> theList) {
		singleton.setSelectedPostModelList(theList);
	}

	public static PostModelList<CommentModel> getCommentList() {
		return singleton.getSelectedPostModelList();
	}
}
